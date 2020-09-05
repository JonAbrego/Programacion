package mx.unam.ciencias.edd;

import java.util.NoSuchElementException;

/**
 * Clase para diccionarios (<em>hash tables</em>). Un diccionario
 * generaliza el concepto de arreglo, permitiendo (en general,
 * dependiendo de qué tan buena sea su método para generar huellas
 * digitales) agregar, eliminar, y buscar elementos en <i>O</i>(1)
 * en cada uno de estos casos.
 */
public class Diccionario<K, V> {

    /* Tamaño mínimo; decidido arbitrariamente a 2^6. */
    private static final int MIN_N = 64;

    /* Máscara para no usar módulo. */
    private int mascara;
    /* Huella digital. */
    private HuellaDigital<K> huella;
    /* Nuestro diccionario. */
    private Lista<Entrada<K, V>>[] entradas;
    /* Número de elementos*/
    private int numeroDeElementos;

    /* Clase para las entradas del diccionario. */
    private class Entrada<K, V> {
        public K llave;
        public V valor;
        public Entrada(K llave, V valor) {
            this.llave = llave;
            this.valor = valor;
        }
    }

    /* Truco para crear un arreglo genérico. Es necesario hacerlo
       así por cómo Java implementa sus genéricos; de otra forma
       obtenemos advertencias del compilador. */
    @SuppressWarnings("unchecked") private Lista<Entrada<K, V>>[] nuevoArreglo(int n) {
        Lista[] arreglo = new Lista[n];
        return (Lista<Entrada<K, V>>[])arreglo;
    }

    /**
     * Construye un diccionario con un tamaño inicial y huella
     * digital predeterminados.
     */
    public Diccionario() {
        // Aquí va su código.
    }

    /**
     * Construye un diccionario con un tamaño inicial definido por
     * el usuario, y una huella digital predeterminada.
     * @param tam el tamaño a utilizar.
     */
    public Diccionario(int tam) {
        this (MIN_N, new HuellaDigital<K>() {
        	public int huellaDigital(K llave){
		    return llave.hashCode();
        	}
	    });
    }
    // Aquí va su código.
    
    /**
     * Construye un diccionario con un tamaño inicial
     * predeterminado, y una huella digital definida por el usuario.
     * @param huella la huella digital a utilizar.
     */
    public Diccionario(HuellaDigital<K> huella) {
	 this(MIN_N,huella);
        // Aquí va su código.
    }

    /**
     * Construye un diccionario con un tamaño inicial, y un método
     * de huella digital definidos por el usuario.
     * @param tam el tamaño del diccionario.
     * @param huella la huella digital a utilizar.
     */
    public Diccionario(int tam, HuellaDigital<K> huella) {
	this.huella=huella;
	if(tam<MIN_N)
	    tam=MIN_N;
	mascara=1;
	while(mascara < tam)
	    mascara=(mascara << 1)|1;
	mascara=(mascara<<1)|1;
	tam=mascara+1;
	entradas=nuevoArreglo(tam);
        // Aquí va su código.
    }

    private int indice(K llave){
	return 0;
    
    }

      
    private Lista<Entrada<K,V>> getLista(int i, boolean crea){
	if(entradas[i] == null && crea)
	    entradas[i] = new Lista<Entrada<K,V>>();
	if(entradas[i] != null)
	    return entradas[i];
	return null;
    }   
    
    private void creceArreglo(){
	mascara=(mascara << 1)|1;
	int tam=mascara+1;
	Lista<Entrada<K,V>>[] listas=entradas;
	entradas=nuevoArreglo(tam);
	numeroDeElementos=0;
	for(Lista<Entrada<K,V>> lista: listas)
	    if(lista != null)
		for(Entrada<K,V> e : lista)
		    agrega(e.llave, e.valor);		    
    }
    
    /**
     * Agrega un nuevo elemento al diccionario, usando la llave
     * proporcionada. Si la llave ya había sido utilizada antes para
     * agregar un elemento, el diccionario reemplaza ese elemento
     * con el recibido aquí.
     * @param llave la llave para agregar el elemento.
     * @param valor el elemento a agregar.
     */
    public void agrega(K llave, V valor) {
	if(carga()>0.72)
	    creceArreglo();
	Entrada<K,V> entrada= new Entrada<K,V>(llave, valor);
	int indice=indice(llave);
	Lista<Entrada<K,V>> lista=getLista(indice, true);
	Entrada<K,V> e = null;
	for(Entrada<K,V> ee : lista)
	    if(ee.llave.equals(llave))
		e=ee;
	if(e != null)
	    lista.elimina(e);
	else
	    numeroDeElementos++;
	lista.agregaFinal(entrada);
        // Aquí va su código.
    }

    /**
     * Regresa un elemento del diccionario, usando la llave
     * proporcionada. Si la llave no se encuentra en el diccionario,
     * se lanza una excepción.
     * @param llave la llave para buscar el elemento.
     * @return el elemento correspondiente a la llave.
     * @throws <tt>NoSuchElementException</tt> si la llave no está
     *         en el diccionario.
     */
    public V get(K llave) {
	int indice= indice(llave);
	Lista<Entrada<K,V>>lista=getLista(indice, false);
	if(lista == null)
	    throw new NoSuchElementException();
	for(Entrada<K,V> e : lista)
	    if(e.llave.equals(llave))
		return e.valor;
	throw new NoSuchElementException();
        // Aquí va su código.
    }

    /**
     * Nos dice si una llave se encuentra en el diccionario.
     * @param llave la llave que queremos ver si está en el diccionario.
     * @return <tt>true</tt> si la llave está en el diccionario,
     *         <tt>false</tt> en otro caso.
     */
    public boolean contiene(K llave) {
	int indice=indice(llave);
	Lista<Entrada<K,V>>lista=getLista(indice, false);
	if(lista == null)
	    return false;
	for(Entrada<K,V>e : lista)
	    if(e.llave.equals(llave))
		return true;
	return false;
        // Aquí va su código.
    }

    /**
     * Elimina un elemento del diccionario, usando la llave
     * proporcionada.
     * @param llave la llave para buscar el elemento a eliminar.
     * @throws NoSuchElementException si la llave no se encuentra en
     *         el diccionario.
     */
    public void elimina(K llave) {
	int indice=indice(llave);
	Lista<Entrada<K,V>>lista=getLista(indice, false);
	if(lista == null)
	    throw new NoSuchElementException();
	Entrada<K,V> e=null;
	for(Entrada<K,V> ee : lista)
	    if(ee.llave.equals(llave))
		e=ee;
	if(e != null){
	    lista.elimina(e);
	    numeroDeElementos--;
	}else{
	    throw new NoSuchElementException();
	}
        // Aquí va su código.
    }

    /**
     * Regresa una lista con todas las llaves con elementos
     * asociados en el diccionario.
     * @return una lista con todas las llaves.
     */
    public Lista<K> llaves() {
	Lista<K> ll=new Lista<K>();
	for(int i=0; i<entradas.length;i++){
	    Lista<Entrada<K,V>>li=getLista(i,false);
	    if(li!=null)
		for(Entrada<K,V> e : li)
		    ll.agregaFinal(e.llave);
	}
	return ll;
        // Aquí va su código.
    }

    /**
     * Regresa una lista con todos los elementos en el diccionario.
     * @return una lista con todos los elementos.
     */
    public Lista<V> elementos() {
	Lista<V> ee= new Lista<V>();
       	for(int i=0; i<entradas.length;i++){
	    Lista<Entrada<K,V>>li=getLista(i,false);
	    if(li != null)
		for(Entrada<K,V> e : li)
		    ee.agregaFinal(e.valor);
	}
	return ee;
        // Aquí va su código.
    }

    /**
     * Nos dice el máximo número de colisiones para una misma llave
     * que tenemos en el diccionario.
     * @return el máximo número de colisiones para una misma llave.
     */
    public int colisionMaxima() {
	int n=0;
	for(int i=0; i<entradas.length; i++){
	    Lista<Entrada<K,V>> li= getLista(i,false);
	    if(li != null && li.getLongitud() >n)
		n=li.getLongitud();
	}
	return n-1;
        // Aquí va su código.
    }

    /**
     * Nos dice cuántas colisiones hay en el diccionario.
     * @return cuántas colisiones hay en el diccionario.
     */
    public int colisiones() {
	int c=0;
	for(int i=0; i<entradas.length; i++){
	    Lista<Entrada<K,V>> li= getLista(i,false);
	    if(li != null && li.getLongitud() > 1)
		c += li.getLongitud()-1;
	}
	return c;
        // Aquí va su código.
    }

    /**
     * Nos dice la carga del diccionario.
     * @return la carga del diccionario.
     */
    public double carga() {
	return numeroDeElementos/(mascara + 1.0);
        // Aquí va su código.
    }

    /**
     * Regresa el número de elementos en el diccionario.
     * @return el número de elementos en el diccionario.
     */
    public int getNumeroDeElementos() {
	return numeroDeElementos;
        // Aquí va su código.
    }
}
