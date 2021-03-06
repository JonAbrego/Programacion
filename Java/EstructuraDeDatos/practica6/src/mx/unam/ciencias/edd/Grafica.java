package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para gráficas. Una gráfica es un conjunto de vértices y
 * aristas, tales que las aristas son un subconjunto del producto
 * cruz de los vértices.
 */
public class Grafica<T> {

    /* Vertices para gráficas; implementan la interfaz
     * VerticeGrafica */
    private class Vertice<T> implements VerticeGrafica<T> {

        public T elemento;
        public Color color;
        public Lista<Grafica<T>.Vertice<T>> vecinos;
        public IteradorLista<Grafica<T>.Vertice<T>> iterador;

        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {
            this.elemento = elemento;
            color = Color.NINGUNO;
            vecinos = new Lista<Grafica<T>.Vertice<T>>();
        }

        /* Crea un nuevo iterador para los vecinos, si no existe, o
         * lo mueve al inico. */
        public void inicio() {
            if (iterador == null)
                iterador = vecinos.iteradorLista();
            else
                iterador.start();
        }

        /* Hay un vecino si el iterador tiene un siguiente. */
        public boolean hayVecino() {
            return iterador.hasNext();
        }

        /* Regresa el siguiente vecino. */
        public VerticeGrafica<T> vecino() {
            Grafica<T>.Vertice<T> n = iterador.next();
            n.inicio();
            return n;
        }

        /* Regresa el elemento del vértice. */
        public T getElemento() {
            return elemento;
        }

        /* Regresa el grado del vértice. */
        public int getGrado() {
            return vecinos.getLongitud();
        }

        /* Regresa el color del vértice. */
        public Color getColor() {
            return color;
        }

        /* Define el color del vértice. */
        public void setColor(Color color) {
            this.color = color;
        }
    }

    /* Vértices. */
    private Lista<Vertice<T>> vertices;
    /* Cardinalidad de las aristas. */
    private int cardinalidadAristas;

    /**
     * Constructor único.
     */
    public Grafica() {
	vertices=new Lista<Vertice<T>>();
	cardinalidadAristas=0;
        // Aquí va su código.
    }

    /**
     * Regresa la cardinalidad del conjunto de vértices.
     * @return la cardinalidad del conjunto de vértices.
     */
    public int getCardinalidadVertices() {
        return vertices.getLongitud();
	// Aquí va su código.
    }

    /**
     * Regresa la cardinalidad del conjunto de aristas.
     * @return la cardinalidad del conjunto de aristas.
     */
    public int getCardinalidadAristas() {
	return cardinalidadAristas;
	// Aquí va su código.
    }

    /**
     * Agrega un nuevo elemento a la gráfica.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si el elemento ya había sido
     *         agregado a la gráfica..
     */
    public void agrega(T elemento) {
	Vertice<T> nuevo =new Vertice<T>(elemento);
	if(contiene(elemento))
	    throw new IllegalArgumentException();
	vertices.agregaFinal(nuevo);
	// Aquí va su código.
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben
     * estar en la gráfica.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de
     *         la gráfica.
     * @throws IllegalArgumentException si a o b ya están
     *         conectados, o si a es igual a b.
     */
    public void conecta(T a, T b) {
	Vertice<T> na=buscaVertice(a);
	Vertice<T> nb=buscaVertice(b);
	if(sonVecinos(a,b) || sonIguales(a,b))
	    throw new IllegalArgumentException();
	na.vecinos.agregaFinal(nb);
	nb.vecinos.agregaFinal(na);
	cardinalidadAristas++;
	//Aquí va su código
    }
    
    private boolean sonIguales(T a, T b){
	if(a.equals(b))
	    return true;
	return false;
    }
    
    /**
     * Desconecta dos elementos de la gráfica. Los elementos deben
     * estar en la gráfica y estar conectados entre ellos.
     * @param a el primer elemento a desconectar.
     * @param b el segundo elemento a desconectar.
     * @throws NoSuchElementException si a o b no son elementos de
     *         la gráfica.
     * @throws IllegalArgumentException si a o b no están
     *         conectados.
     */
    public void desconecta(T a, T b) {
	Vertice<T> na=buscaVertice(a);
	Vertice<T> nb=buscaVertice(b);
	if(!na.vecinos.contiene(nb) || !nb.vecinos.contiene(na))
		throw new IllegalArgumentException();
	na.vecinos.elimina(nb);
	nb.vecinos.elimina(na);
	cardinalidadAristas--;
	// Aquí va su código.
    }

    /**
     * Nos dice si el elemento está contenido en la gráfica.
     * @return <tt>true</tt> si el elemento está contenido en la
     *         gráfica, <tt>false</tt> en otro caso.
     */
    public boolean contiene(T elemento) {
	try{
	    buscaVertice(elemento);
	}catch(NoSuchElementException e1){
	    return false;
	}
	return true;
    }

    /**
     * Elimina un elemento de la gráfica. El elemento tiene que
     * estar contenido en la gráfica.
     * @param elemento el elemento a eliminar.
     * @throws NoSuchElementException si el elemento no está
     *         contenido en la gráfica.
     */
    public void elimina(T elemento) {
	for( Vertice<T> k : vertices){
	    if(sonVecinos(elemento,k.elemento))
	       desconecta(k.elemento,elemento);
	}
	Vertice<T>v=buscaVertice(elemento);
	vertices.elimina(v);
	// Aquí va su código.
    }

    /**
     * Nos dice si dos elementos de la gráfica están conectados. Los
     * elementos deben estar en la gráfica.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return <tt>true</tt> si a y b son vecinos, <tt>false</tt> en
     *         otro caso.
     * @throws NoSuchElementException si a o b no son elementos de
     *         la gráfica.
     */
    public boolean sonVecinos(T a, T b) {
        Vertice<T> na=buscaVertice(a);
	Vertice<T> nb=buscaVertice(b);
	if(contiene(a) && contiene(b))
	    if(na.vecinos.contiene(nb) && nb.vecinos.contiene(na))
		return true;
	return false;
	// Aquí va su código.
    }

    /**
     * Regresa el vértice correspondiente el elemento recibido.
     * @throws NoSuchElementException si elemento no es elemento de
     *         la gráfica.
     * @return el vértice correspondiente el elemento recibido.
     */
    public VerticeGrafica<T> vertice(T elemento) {
        Vertice<T> n = buscaVertice(elemento);
        n.inicio();
        return n;
    }

    private Vertice<T> buscaVertice(T elemento){
	for(Vertice<T> k : vertices)
	    if(elemento.equals(k.elemento))
		return  k;
	throw new NoSuchElementException();
    }

    /**
     * Realiza la acción recibida en cada uno de los vértices de la
     * gráfica, en el orden en que fueron agregados.
     * @param accion la acción a realizar.
     */
    public void paraCadaVertice(AccionVerticeGrafica<T> accion) {
	for(Vertice<T> v : vertices)
	    accion.actua(v);
	// Aquí va su código.
    }

    /**
     * Realiza la acción recibida en todos los vértices de la
     * gráfica, en el orden determinado por BFS, comenzando por el
     * vértice correspondiente al elemento recibido. Al terminar el
     * método, todos los vértices tendrán color {@link
     * Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos
     *        comenzar el recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la
     *         gráfica.
     */
  public void bfs(T elemento, AccionVerticeGrafica<T> accion) {
      Vertice<T> n=buscaVertice(elemento);
      for(Vertice<T> v : vertices)
	  v.color=Color.ROJO;
      Cola<Vertice<T>>cola=new Cola<Vertice<T>>();
      n.color=Color.NEGRO; 
      cola.mete(n);
      while(!cola.esVacia()){
	  Vertice<T> aux=cola.saca();
	  accion.actua(aux);
	  for(Vertice<T> k : aux.vecinos)
	      if(k.color==Color.ROJO){
		  k.color=Color.NEGRO;
		  cola.mete(k);
	      }
      }   
  }
  

    /**
     * Realiza la acción recibida en todos los vértices de la
     * gráfica, en el orden determinado por DFS, comenzando por el
     * vértice correspondiente al elemento recibido. Al terminar el
     * método, todos los vértices tendrán color {@link
     * Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos
     *        comenzar el recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la
     *         gráfica.
     */
    public void dfs(T elemento, AccionVerticeGrafica<T> accion) {
	Vertice<T> n=buscaVertice(elemento);
	for(Vertice<T> v : vertices)
	    v.color=Color.ROJO;
	Pila<Vertice<T>>cola=new Pila<Vertice<T>>();
	n.color=Color.NEGRO; 
	cola.mete(n);
	while(!cola.esVacia()){
	    Vertice<T> aux=cola.saca();
	    accion.actua(aux);
	    for(Vertice<T> k : aux.vecinos)
		if(k.color==Color.ROJO){
		    k.color=Color.NEGRO;
		    cola.mete(k);
		}
	}   
    }
}
