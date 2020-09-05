package mx.unam.ciencias.edd;

/**
 * <p>Clase para árboles binarios por niveles.</p>
 *
 * <p>Un árbol binario por niveles agrega y elimina elementos de tal
 * forma que el árbol siempre es lo más cercano posible a estar
 * completo.<p>
 */
public class ArbolBinarioNiveles<T> extends ArbolBinario<T> {

    /**
     * Constructor sin parámetros. Sencillamente ejecuta el
     * constructor sin parámetros de {@link ArbolBinario}.
     */
    public ArbolBinarioNiveles() { super(); }

    /**
     * Agrega un elemento al árbol binario por niveles. El nuevo
     * elemento se coloca al final del orden correspondiente a
     * recorrer el árbol por niveles.
     * @param elemento el elemento a agregar al árbol.
     * @return un iterador que apunta al nodo del árbol que contiene
     *         el elemento.
     */
    @Override public IteradorArbolBinario<T> agrega(T elemento) {
	Nodo<T> nvo = new Nodo<T>(elemento);
        if (raiz == null) {
	    raiz = nvo;
	    numeroDeElementos++;
	    return iterador(raiz);
        }
        Cola<Nodo<T>> cola = new Cola<Nodo<T>>();
        cola.mete(raiz);
        while (!cola.esVacia()){
	    Nodo<T> n = cola.saca();
	    if (n.izquierdo == null) {
		n.izquierdo = nvo;
		n.izquierdo.padre = n;
		numeroDeElementos++;
		return iterador(n.izquierdo);
	    }
	    if (n.derecho == null) {
		n.derecho = nvo;
		n.derecho.padre = n;
		numeroDeElementos++;
		return iterador(n.derecho);
	    }
	    cola.mete(n.izquierdo);
	    cola.mete(n.derecho);
	}
        return null;
    }
    
    
    /**
     * Elimina un elemento del árbol. El elemento a eliminar cambia
     * lugares con el último elemento del árbol al recorrerlo por
     * niveles, y entonces es eliminado.
     * @param elemento el elemento a eliminar.
     */
    @Override public void elimina(T elemento) {
        if (raiz == null)
	    return;
        if(raiz.elemento.equals(elemento) && raiz.izquierdo == null && raiz.derecho == null) {
	    raiz = null;
	    numeroDeElementos--;
	    return;
        }
        Cola<Nodo<T>> cola = new Cola<Nodo<T>>();
        Nodo<T> el = null;
        Nodo<T> temp = null;
        cola.mete(raiz);
        while (!cola.esVacia()) {
	    temp = cola.saca();
	    if (temp.elemento.equals(elemento))
		el = temp;
	    if (temp.izquierdo != null)
		cola.mete(temp.izquierdo);
	    if (temp.derecho != null)
		cola.mete(temp.derecho);
        }
        if (el != null) {
	    intercambia(el, temp);
	    if (temp.padre != null && temp.padre.izquierdo == temp) {
        	temp.padre.izquierdo = null;
        	numeroDeElementos--;
        	return;
	    } else if (temp.padre != null && temp.padre.derecho == temp) {
		temp.padre.derecho = null;
		numeroDeElementos--;
		return;
	    }
        }
        return;
    }
    
    private void intercambia(Nodo<T> n, Nodo<T> a) {
    	T temp = n.elemento;
    	n.elemento = a.elemento;
    	a.elemento = temp;
    }
}

