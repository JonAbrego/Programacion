package mx.unam.ciencias.edd;

import java.util.NoSuchElementException;

/**
 * <p>Clase abstracta para árboles binarios genéricos.</p>
 *
 * <p>La clase proporciona las operaciones básicas para árboles
 * binarios, pero deja la implementación de varios en manos de las
 * clases concretas.</p>
 */
public abstract class ArbolBinario<T> {

    /**
     * Clase interna protegida para nodos.
     */
    protected class Nodo<T> {
        /** El elemento del nodo. */
        public T elemento;
        /** El padre del nodo. */
        public Nodo<T> padre;
        /** El izquierdo del nodo. */
        public Nodo<T> izquierdo;
        /** El derecho del nodo. */
        public Nodo<T> derecho;
	 /** El color del nodo. */
        public Color color;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del nodo.
         */
        public Nodo(T elemento) {
            this.elemento = elemento;
	    color = Color.NINGUNO;
        }

        /**
         * Regresa una representación en cadena del nodo.
         * @return una representación en cadena del nodo.
         */
        public String toString() {
            return elemento.toString();
        }
    }

    /**
     * Clase interna protegida para iteradores.
     */
    private class Iterador<T> implements IteradorArbolBinario<T> {

        /* El árbol a iterar. */
        private ArbolBinario<T> arbol;
        /* Nuestro iterardor. */
        private ArbolBinario<T>.Nodo<T> iterador;

        /* Constructor que pone el iterador en un nodo
         * específico. */
        public Iterador(ArbolBinario<T> arbol, ArbolBinario<T>.Nodo<T> nodo) {
	    this.arbol= arbol;
	    this.iterador=nodo;
	    // Aquí va su código.
        }

        /* Nos dice si el nodo al que apunta el iterador tiene
         * padre. */
        @Override public boolean hayPadre() {
	    if(iterador.padre != null)
		return true;
	    return false;
	    // Aquí va su código.
        }

        /* Nos dice si el nodo al que apunta el iterador tiene
         * izquierdo. */
        @Override public boolean hayIzquierdo() {
	    if(iterador.izquierdo != null)
		return true;
            return false;// Aquí va su código.
        }

        /* Nos dice si el nodo al que apunta el iterador tiene
         * derecho. */
        @Override public boolean hayDerecho() {
	    if(iterador.derecho != null)
		return true;
	    return false;
	    // Aquí va su código.
        }

        /* Mueve el iterador al padre, si el nodo al que apunta
         * tiene padre. */
        @Override public void padre() {
	    if(!hayPadre())
		throw new NoSuchElementException();
	    iterador=iterador.padre;// Aquí va su código.
        }

        /* Mueve el iterador al izquierdo, si el nodo al que apunta
         * tiene izquierdo. */
        @Override public void izquierdo() {
	    if(!hayIzquierdo())
		throw new NoSuchElementException();
	    iterador=iterador.izquierdo;
            // Aquí va su código.
        }

        /* Mueve el iterador al derecho, si el nodo al que apunta
         * tiene derecho. */
        @Override public void derecho() {
	    if(!hayDerecho())
		throw new NoSuchElementException();
	    iterador=iterador.derecho;
            // Aquí va su código.
        }

        /* Regresa el elemento al que el iterador apunta. */
        @Override public T dame() {
	    if(iterador==null)
		throw new NoSuchElementException();
	    return iterador.elemento;
	    // Aquí va su código.
        }

        /* Regresa una copia del iterador. */
        @Override public IteradorArbolBinario<T> copia() {
            Iterador<T> nuevo=new Iterador<T>(arbol, iterador);
	    return nuevo;
	    // Aquí va su código.
        }
	
	@Override public Color color() {
	     if (iterador == null)
		 throw new NoSuchElementException("El iterador no es válido.");
	     return iterador.color;
	 }
    }
    
    /** La raíz del árbol. */
    protected Nodo<T> raiz;
    /** El número de elementos */
    protected int numeroDeElementos;

    /**
     * Construye un árbol con cero elementos.
     */
    public ArbolBinario() {
        raiz = null;
        numeroDeElementos = 0;
    }

    /**
     * Regresa la profundidad del árbol. La profundidad de un árbol
     * es la longitud de la ruta más larga entre la raíz y una hoja.
     * @return la profundidad del árbol.
     */
    public int profundidad() {
        return profundidad(raiz);// Aquí va su código.
    }
   
    private int profundidad(Nodo<T> n){
	if(n==null)
	    return -1;
	return 1+Math.max(profundidad(n.izquierdo),profundidad(n.derecho));
    }
    
    /**
     * Regresa el número de elementos en el árbol. El número de
     * elementos es el número de elementos que se han agregado al
     * árbol.
     * @return el número de elementos en el árbol.
     */
    public int getNumeroDeElementos() {
        return numeroDeElementos;// Aquí va su código.
    }

    /**
     * Agrega un elemento al árbol.
     * @param elemento el elemento a agregar al árbol.
     * @return un iterador que apunta al nodo del árbol que contiene
     *         el elemento.
     */
    public abstract IteradorArbolBinario<T> agrega(T elemento);

    /**
     * Elimina un elemento del árbol.
     * @param elemento el elemento a eliminar.
     */
    public abstract void elimina(T elemento);

    /**
     * Busca un elemento en el árbol. Si lo encuentra, regresa un
     * iterador que apunta a dicho elemento; si no, regresa
     * <tt>null</tt>.
     * @param elemento el elemento a buscar.
     * @return un iterador que apunta al elemento buscado si lo
     *         encuentra; <tt>null</tt> en otro caso.
     */
    public IteradorArbolBinario<T> busca(T elemento) {
	if(raiz== null)
	    return null;
	Cola<Nodo<T>> cola=new Cola<Nodo<T>>();
	cola.mete(raiz);
	while(!cola.esVacia()){
	    Nodo<T>n=cola.saca();
	    if(n.izquierdo != null)
		cola.mete(n.izquierdo);
	    if(n.derecho != null)
		cola.mete(n.derecho);
	    if(n.elemento.equals(elemento))
		return iterador(n);
	}
	return null;
    }

    /**
     * Regresa un iterador que apunta a la raíz del árbol.
     * @return un iterador que apunta a la raíz del árbol.
     */
    public IteradorArbolBinario<T> iterador() {
        return new Iterador<T>(this, raiz);
    }

    /**
     * Regresa un iterador que apunta al nodo recibido.
     * @return un iterador que apunta al nodo recibido.
     */
    protected IteradorArbolBinario<T> iterador(Nodo<T> nodo) {
        return new Iterador<T>(this, nodo);
    }

    /**
     * Regresa el nodo correspondiente al iterador recibido.
     * @param iterador el iterador del que queremos su nodo.
     * @return el nodo correspondiente al iterador recibido.
     */
    protected Nodo<T> nodoDeIterador(IteradorArbolBinario<T> iterador) {
        /* Tenemos que suprimir advertencias. */
        @SuppressWarnings("unchecked") Iterador<T> it = (Iterador<T>)iterador;
        return it.iterador;
    }

    /**
     * Regresa una representación en cadena del árbol.
     * @return una representación en cadena del árbol.
     */
    @Override public String toString() {
        /* Necesitamos la profundidad para saber cuántas ramas puede
           haber. */
        if (numeroDeElementos == 0)
            return "";
        int p = profundidad() + 1;
        /* true == dibuja rama, false == dibuja espacio. */
        boolean[] rama = new boolean[p];
        for (int i = 0; i < p; i++)
            /* Al inicio, no dibujamos ninguna rama. */
            rama[i] = false;
        String s = aCadena(raiz, 0, rama);
        return s.substring(0, s.length()-1);
    }

    /* Método auxiliar recursivo que hace todo el trabajo. */
    private String aCadena(Nodo<T> nodo, int nivel, boolean[] rama) {
        /* Primero que nada agregamos el nodo a la cadena. */
        String s = nodo + "\n";
        /* A partir de aquí, dibujamos rama en este nivel. */
        rama[nivel] = true;
        if (nodo.izquierdo != null && nodo.derecho != null) {
            /* Si hay nodo izquierdo Y derecho, dibujamos ramas o
             * espacios. */
            s += espacios(nivel, rama);
            /* Dibujamos el conector al hijo izquierdo. */
            s += "├─›";
            /* Recursivamente dibujamos el hijo izquierdo y sus
               descendientes. */
            s += aCadena(nodo.izquierdo, nivel+1, rama);
            /* Dibujamos ramas o espacios. */
            s += espacios(nivel, rama);
            /* Dibujamos el conector al hijo derecho. */
            s += "└─»";
            /* Como ya dibujamos el último hijo, ya no hay rama en
               este nivel. */
            rama[nivel] = false;
            /* Recursivamente dibujamos el hijo derecho y sus
               descendientes. */
            s += aCadena(nodo.derecho, nivel+1, rama);
        } else if (nodo.izquierdo != null) {
            /* Dibujamos ramas o espacios. */
            s += espacios(nivel, rama);
            /* Dibujamos el conector al hijo izquierdo. */
            s += "└─›";
            /* Como ya dibujamos el último hijo, ya no hay rama en
               este nivel. */
            rama[nivel] = false;
            /* Recursivamente dibujamos el hijo izquierdo y sus
               descendientes. */
            s += aCadena(nodo.izquierdo, nivel+1, rama);
        } else if (nodo.derecho != null) {
            /* Dibujamos ramas o espacios. */
            s += espacios(nivel, rama);
            /* Dibujamos el conector al hijo derecho. */
            s += "└─»";
            /* Como ya dibujamos el último hijo, ya no hay rama en
               este nivel. */
            rama[nivel] = false;
            /* Recursivamente dibujamos el hijo derecho y sus
               descendientes. */
            s += aCadena(nodo.derecho, nivel+1, rama);
        }
        return s;
    }

    /* Dibuja los espacios (incluidas las ramas, de ser necesarias)
       que van antes de un nodo. */
    private String espacios(int n, boolean[] rama) {
        String s = "";
        for (int i = 0; i < n; i++)
            if (rama[i])
                /* Rama: dibújala. */
                s += "│  ";
            else
                /* No rama: dibuja espacio. */
                s += "   ";
        return s;
    }
}
