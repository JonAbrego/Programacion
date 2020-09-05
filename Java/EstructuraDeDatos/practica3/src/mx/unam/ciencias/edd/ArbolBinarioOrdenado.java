package mx.unam.ciencias.edd;

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
public class ArbolBinarioOrdenado<T extends Comparable<T>>
    extends ArbolBinario<T> {

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
      
    
    /**
     * Agrega un nuevo elemento al árbol. El árbol conserva su orden
     * in-order.
     * @param elemento el elemento a agregar.
     * @return un iterador que apunta al nodo del nuevo elemento.
     */
    @Override public IteradorArbolBinario<T> agrega(T elemento) {
	if (raiz == null) {
	    Nodo<T> nuevo = new Nodo<T>(elemento);
	    raiz = nuevo;
	    numeroDeElementos++;
	    return iterador(raiz);
	}
	return agrega(raiz, elemento);
    }

    private IteradorArbolBinario<T> agrega(Nodo<T> n, T elemento) {
	if (elemento.compareTo(n.elemento) < 0) {
	    if (n.izquierdo == null) {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		n.izquierdo = nuevo;
		n.izquierdo.padre = n;
		numeroDeElementos++;
		return iterador(n.izquierdo);
	    }
	    return agrega(n.izquierdo, elemento);
	}
        if (elemento.compareTo(n.elemento) >= 0) {
	    if (n.derecho == null) {
		Nodo<T> nuevo = new Nodo<T>(elemento);
		n.derecho = nuevo;
		n.derecho.padre = n;
		numeroDeElementos++;
		return iterador(n.derecho);
	    }
	    return agrega(n.derecho, elemento);
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
	if (raiz == null)
	    return;
        if (busca(elemento) == null)
	    return;
        if(raiz.elemento.equals(elemento) && raiz.izquierdo == null && raiz.derecho == null) {
	    raiz = null;
	    numeroDeElementos--;
	    return;
        }
	Nodo<T> el = nodoDeIterador(busca(elemento));
        if (buscaNodoAnterior(el) == null) {
	    if (el.derecho != null) {
		el.padre.derecho = el.derecho;
		el.derecho.padre = el.padre;
		numeroDeElementos--;
		return;
	    }
	    el.padre.derecho = null;
	    numeroDeElementos--;
	    return;
        }
        Nodo<T> ant = buscaNodoAnterior(el);
        intercambia(el, ant);
        if(ant.izquierdo != null) {
	    ant.padre.izquierdo = ant.izquierdo;
	    ant.izquierdo.padre = ant.padre;
	    numeroDeElementos--;
	    return;
        }
        ant.padre.izquierdo = null;
        numeroDeElementos--;
        return;
	//Aquí va su código
    }
     
    private void intercambia(Nodo<T> n,Nodo<T> a) {
	T temp = n.elemento;
	n.elemento = a.elemento;
	a.elemento = temp;
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
	   if (raiz == null)
        	return null;
        return busca(raiz, elemento);
    }
    
    private IteradorArbolBinario<T> busca(Nodo<T> n, T elemento) {
    	if (elemento.equals(n.elemento))
	    return iterador(n);
    	if (n.izquierdo != null) {
	    if (elemento.compareTo(n.elemento) < 0) {
		return busca(n.izquierdo, elemento);
	    }
	}
        if (n.derecho != null) {
	    if (elemento.compareTo(n.elemento) >= 0) {
		return busca(n.derecho, elemento);
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
	if (nodo.izquierdo == null)
        	return null;
        Nodo<T> aux = nodo.izquierdo;
        while (aux.derecho != null)
        	aux = aux.derecho;
        return aux;
	// Aquí va su código.
    }
}
