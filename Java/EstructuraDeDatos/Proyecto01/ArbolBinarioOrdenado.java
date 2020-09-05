package mx.unam.ciencias.edd;

import java.io.FileWriter;
import java.util.Random;
/**
 * <p>Clase para árboles binarios ordenados. Los árboles son
 * genéricos, pero acotados a la interfaz {@link Comparable}.</p>
 *
 * <p>Un árbol instancia de esta clase siempre cumple que:</p>
 * <ul>
 *   <li>Cualquier elemento en el árbol es mayor o igual que todos
 *       sus descendientes por la izquierda.</li>
 *   <li>Cualquier elemento en el árbol es menor o igual que todos
 *       sus descendientes por la derecha.</li>
 * </ul>
 */
public class ArbolBinarioOrdenado<T extends Comparable<T>> extends ArbolBinario<T> {

    /**
     * Constructor sin parámetros. Sencillamente ejecuta el
     * constructor sin parámetros de {@link ArbolBinario}.
     */
    public ArbolBinarioOrdenado() { super(); }
    
    /**
     * Construye un árbol binario ordenado a partir de un árbol
     * binario. El árbol binario ordenado tiene los mismos elementos
     * que el árbol recibido, pero ordenados.
     * @param arbol el árbol binario a partir del cuál creamos el
     *        árbol binario ordenado.
     */
    public ArbolBinarioOrdenado(ArbolBinario<T> arbol) {
	if(arbol.raiz != null){
	    Cola<Nodo<T>> cola = new Cola<Nodo<T>>();
	    cola.mete(arbol.raiz);
	    while (!cola.esVacia()){
		Nodo<T> n = cola.saca();
		agrega(n.elemento);
		if (n.izquierdo != null)
		    cola.mete(n.izquierdo);
		if (n.derecho != null)
		    cola.mete(n.derecho);
	    }
	}
	arbol=new ArbolBinarioOrdenado<T>();
    }
    
    
    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden
     * in-order.
     * @param elemento el elemento a agregar.
     * @return un iterador que apunta al nodo del nuevo elemento.
     */
    @Override public IteradorArbolBinario<T> agrega(T elemento) {
	numeroDeElementos++;
       	return agrega(raiz, elemento);
    }
    
    private IteradorArbolBinario<T> agrega(Nodo<T> nodo, T elemento) {
	Nodo<T> nvo = new Nodo<T>(elemento);
	if(raiz==null){
	    raiz=nvo;
	    return iterador(raiz);
	}
	if (elemento.compareTo(nodo.elemento) <= 0) {
	    if (nodo.izquierdo == null) {
		nodo.izquierdo = nvo;
		nodo.izquierdo.padre = nodo;
       		return iterador(nodo.izquierdo);
	    }
	    return agrega(nodo.izquierdo, elemento);
	}
        if (elemento.compareTo(nodo.elemento) > 0) {
	    if (nodo.derecho == null) {
		nodo.derecho = nvo;
		nodo.derecho.padre = nodo;
		return iterador(nodo.derecho);
	    }
	    return agrega(nodo.derecho, elemento);
	}
        return null;
    }
    
    /**
     * Elimina un elemento. Si el elemento no está en el árbol, no
     * hace nada; si está varias veces, elimina el primero que
     * encuentre (in-order). El árbol conserva su orden in-order.
     * @param elemento el elemento a eliminar.
     */
    
    @Override public void elimina(T elemento) {	
	Nodo<T> nodo =nodoDeIterador(busca(elemento));
	if(nodo == null) return;
	Nodo<T> anterior=buscaNodoAnterior(nodo);
	numeroDeElementos--;
	if(anterior == null && nodo.padre == null){
	    raiz=nodo.derecho;
	    if(nodo.derecho != null)
		nodo.derecho.padre=null;
	    return ;
	}
	Nodo<T> aux=nodo.padre;
	if(anterior == null && nodo.padre != null){
	    if(esHijoIzquierdo(nodo)){
		aux.izquierdo=nodo.derecho;
	    }
	    if(esHijoDerecho(nodo)){
		aux.derecho=nodo.derecho;
	    }
	    if(nodo.derecho != null)
		nodo.derecho.padre=aux;
	    return;
	}
	if(anterior != null){
	    intercambia(anterior,nodo);
	    if(esHijoIzquierdo(anterior)){
		anterior.padre.izquierdo=anterior.izquierdo;
	    }
	    if(esHijoDerecho(anterior)){
		anterior.padre.derecho=anterior.izquierdo;
	    }
	    if(anterior.izquierdo != null)
		anterior.izquierdo.padre=anterior.padre;
	    return;
	}
    }
    
    /**
     * Indica si el nodo que deseamos es hijo derecho si es
     * el caso regresa un </tt> true</tt> , en caso contrario
     * </tt>false</tt>
     * @param nodo 
     * @return un booleano 
     */
    private boolean esHijoDerecho(Nodo<T> nodo){
	Nodo<T> aux=nodo.padre;
	if(aux != null)
	    if(aux.derecho == nodo)  
		return true;
	return false;
    }
    
    /**
     * Indica si el nodo que deseamos es hijo izquierdo si es
     * el caso regresa un </tt> true</tt> , en caso contrario
     * </tt>false</tt>
     * @param nodo 
     * @return un booleano 
     */
    private boolean esHijoIzquierdo(Nodo<T> nodo){
	Nodo<T> aux=nodo.padre;
	if(aux != null)
	    if(aux.izquierdo ==nodo)
		return true;
	return false;
    }

    /**
     * Intercambio los elementos contenidos de dos nodos
     * @param n un Nodo no vacio
     * @param ant un Nodo no vacio
     */
    
    private void intercambia(Nodo<T> n,Nodo<T> ant){
	T aux = n.elemento;
	n.elemento = ant.elemento;
	ant.elemento = aux;
    }

    /**
     * Busca un elemento en el árbol recorriéndolo in-order. Si lo
     * encuentra, regresa un iterador que apunta a dicho elemento;
     * si no, regresa <tt>null</tt>.
     * @param elemento el elemento a buscar.
     * @return un iterador que apunta al elemento buscado si lo
     *         encuentra; <tt>null</tt> en otro caso.
     */
    @Override public IteradorArbolBinario<T> busca(T elemento) {
	return busca(raiz, elemento);
    }
    
    private IteradorArbolBinario<T> busca(Nodo<T> nodo, T elemento) {
    	if (nodo==null)
	    return null;
	if( elemento.equals(nodo.elemento))
	    return iterador(nodo);
    	if (nodo.izquierdo != null) {
	    if (elemento.compareTo(nodo.elemento) <= 0) {
		return busca(nodo.izquierdo, elemento);
	    }
	}
        if (nodo.derecho != null) {
	    if (elemento.compareTo(nodo.elemento) > 0) {
		return busca(nodo.derecho, elemento);
	    }
        }
        return null;
    }
    
    /**
     * Regresa el nodo anterior (en in-order) al nodo que recibe.
     * @param nodo el nodo del que queremos encontrar el anterior.
     * @return el nodo anterior (en in-order) al nodo que recibe.
     */
    protected Nodo<T> buscaNodoAnterior(Nodo<T> nodo) {
	if(nodo.izquierdo ==null)
	    return null;
	Nodo<T> aux = nodo.izquierdo;
        while (aux.derecho != null)
	    aux = aux.derecho;
	return aux;
	
    }
    
     /**
     * Gira el árbol a la derecha sobre el nodo al que el iterador
     * recibido apunte. Si el nodo al que el iterador apunta no
     * tiene hijo izquierdo, el método no hace nada.
     * @param iterador el iterador que apunta al nodo sobre el que
     *                 vamos a girar.
     */
    public void giraDerecha(IteradorArbolBinario<T> iterador) {
        Nodo<T> nodo = nodoDeIterador(iterador);
        giraDerecha(nodo);
    }

    /**
     * Gira el árbol a la derecha sobre el nodo recibido. Si el nodo
     * no tiene hijo izquierdo, el método no hace nada.
     * @param nodo el nodo sobre el que vamos a girar.
     */
    protected void giraDerecha(Nodo<T> nodo) {
	Nodo<T> aux=nodo.izquierdo;
	nodo.izquierdo=aux.derecho;
       	if(aux.derecho != null)
	    aux.derecho.padre=nodo;
	aux.padre=nodo.padre;
	if(nodo.padre==null)
	    raiz=aux;
	else if(nodo == nodo.padre.derecho)
	    nodo.padre.derecho=aux;
	else nodo.padre.izquierdo=aux;
	aux.derecho=nodo;
	nodo.padre=aux;
    }
       
    /**
     * Gira el árbol a la izquierda sobre el nodo al que el
     * iterador recibido apunte. Si el nodo al que el iterador
     * apunta no tiene hijo derecho, el método no hace nada.
     * @param iterador el iterador que apunta al nodo sobre el que
     *                 vamos a girar.
     */
    public void giraIzquierda(IteradorArbolBinario<T> iterador) {
        Nodo<T> nodo = nodoDeIterador(iterador);
        giraIzquierda(nodo);
    }
    
    /**
     * Gira el árbol a la izquierda sobre el nodo recibido. Si el
     * nodo no tiene hijo derecho, el método no hace nada.
     * @param nodo el nodo sobre el que vamos a girar.
     */
    protected void giraIzquierda(Nodo<T> nodo) {
	Nodo<T> aux=nodo.derecho;
	nodo.derecho=aux.izquierdo;
       	if(aux.izquierdo != null)
	    aux.izquierdo.padre=nodo;
	aux.padre=nodo.padre;
	if(nodo.padre==null)
	    raiz=aux;
	else if(nodo == nodo.padre.izquierdo)
	    nodo.padre.izquierdo=aux;
	else nodo.padre.derecho=aux;
	aux.izquierdo=nodo;
	nodo.padre=aux;
    }
    
    public String generaScalableVectorGraphics() {
	double tamImagenY = (profundidad()+1) * 70;
	double tamImagenX = ((getNumeroDeElementos()*2)+1) * 100;
	String svg = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
	svg += "<svg width=\""+ tamImagenX +"\" height=\""+ (tamImagenY*2) +"\">\n ";
	svg+="<g>\n";
	svg+= " <rect  x=\"0\" y=\"0\" width=\""+tamImagenX+"\" height=\""+ (tamImagenY*2)+"\" style=\"fill:white;stroke:black;stroke-width:.5;fill-opacity:1;stroke-opacity:1\" /> \n";
	svg += generaSVG(raiz,tamImagenX/2,80);
	svg += "</g>\n"+"</svg>";
	return svg;
    }
    
    private String generaSVG(Nodo<T> nodo, double x, double y){
	double tamImagenX = ((getNumeroDeElementos()*2) + 1) * 100;
	String s = "";
	double d =y/getNumeroDeElementos()-1;
	double y1=y;
	double x1=x+tamImagenX/d;
	double x2=x-tamImagenX/d;
	double y2=y+90;
	if(nodo.izquierdo!=null){
	    s+="<line x1=\"" + x + "\" y1=\"" + y1 + "\" x2=\"" + x2 + "\" y2=\"" + y2 + "\" stroke=\"black\" stroke-width=\"4\" />\n";
	    s+= generaSVG(nodo.izquierdo,x-tamImagenX/d,y+100);
	}
	if(nodo.derecho!=null){
	    s+="<line x1=\"" + x + "\" y1=\"" + y1 + "\" x2=\"" + x1 + "\" y2=\"" + y2 + "\" stroke=\"black\" stroke-width=\"4\" />\n";
	    s+= generaSVG(nodo.derecho,x+tamImagenX/d,y+100);
	}
	s+="<circle cx=\"" + x + "\" cy=\"" + y+ "\" r=\"20\" stroke=\"black\" stroke-width=\"3\" fill=\"white\" />\n";
	s+="<text fill=\"red\" font-family=\"sans-serif\" font-size=\"20\" x=\"" + x + "\" y=\"" + y1 + "\"\n" + "text-anchor=\"middle\">" + nodo.elemento + "</text>";
	return s;
    }
}
