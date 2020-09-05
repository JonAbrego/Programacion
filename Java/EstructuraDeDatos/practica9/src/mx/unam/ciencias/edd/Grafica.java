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
    private class Vertice<T> implements
        VerticeGrafica<T>, ComparableIndexable<Vertice<T>> {
	
        public T elemento;
        public double distancia;
        public Color color;
        public int indice;
        public Lista<Grafica<T>.Arista<T>> aristas;
        public IteradorLista<Grafica<T>.Arista<T>> iterador;
	
        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {
	    this.elemento=elemento;
	    color=Color.NINGUNO;
	    distancia=-1;
	    aristas=new Lista<Grafica<T>.Arista<T>>();
	}
	
        /* Crea un nuevo iterador para las aristas, si no existe, o
         * lo mueve al inico. */
        public void inicio() {
	    if(iterador==null)
		iterador = aristas.iteradorLista();
	    else 
		iterador.start();
	}
	
        /* Hay un vecino si el iterador tiene un siguiente. */
        public boolean hayVecino() {
	    return iterador.hasNext();
	}
	
        /* Regresa el siguiente vecino. */
        public VerticeGrafica<T> vecino() {
	    Grafica<T>.Arista<T> a = iterador.next();
            a.vecino.inicio();
            return a.vecino;
	}
	
        /* Regresa el elemento del vértice. */
        public T getElemento() {
	    return elemento;
	}
	
        /* Regresa el grado del vértice. */
        public int getGrado() {
	    return aristas.getLongitud();
            // Aquí va su código.
        }

        /* Regresa el color del vértice. */
        public Color getColor() {
	    return color;
	}

        /* Define el color del vértice. */
        public void setColor(Color color) {
	    this.color=color;
	}

        /* Define el índice del vértice. */
        public void setIndice(int indice) {
	    this.indice=indice;
	}

        /* Regresa el índice del vértice. */
        public int getIndice() {
	    return indice;
	    //Aqui va su código
	}

        /* Compara dos vértices por distancia. */
        public int compareTo(Vertice<T> vertice) {
	    if(distancia == vertice.distancia)
		return 0;
            if(vertice.distancia == -1)
                return -1;
            if(distancia == -1)
                return 1;
            return distancia<vertice.distancia? -1:1;
            // Aquí va su código.
        }
    }

    /* Aristas para poder definir pesos. */
    private class Arista<T> {

        public Grafica<T>.Vertice<T> vecino;
        public double peso;

        public Arista(Grafica<T>.Vertice<T> vecino, double peso) {
            this.vecino = vecino;
            this.peso = peso;
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
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben
     * estar en la gráfica. El peso por omisión de la nueva arista
     * es 1.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de
     *         la gráfica.
     * @throws IllegalArgumentException si a o b ya están
     *         conectados, o si a es igual a b.
     */
    public void conecta(T a, T b) {
        conecta(a, b, 1.0);
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben
     * estar en la gráfica.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @param peso el peso de la nueva arista.
     * @throws NoSuchElementException si a o b no son elementos de
     *         la gráfica.
     * @throws IllegalArgumentException si a o b ya están
     *         conectados, o si a es igual a b.
     */
    public void conecta(T a, T b, double peso) {
	Vertice<T> na=buscaVertice(a);
	Vertice<T> nb=buscaVertice(b);
	if(sonIguales(a,b) || sonVecinos(a,b))
	    throw new IllegalArgumentException();
	Arista<T> a1=new Arista<T>(na,peso);
	Arista<T> b1=new Arista<T>(nb,peso);
	na.aristas.agregaFinal(b1);
	nb.aristas.agregaFinal(a1);
	cardinalidadAristas++;
	// Aquí va su código.
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
	if(!sonVecinos(a,b))
	    throw new IllegalArgumentException();
	Arista<T> a1=buscaArista(na,nb);
	Arista<T> b1=buscaArista(nb,na);
	na.aristas.elimina(a1);
	nb.aristas.elimina(b1);	
	cardinalidadAristas--;
	//Aquí va su código
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
	// Aquí va su código.
    }
    
    /**
     * Elimina un elemento de la gráfica. El elemento tiene que
     * estar contenido en la gráfica.
     * @param elemento el elemento a eliminar.
     * @throws NoSuchElementException si el elemento no está
     *         contenido en la gráfica.
     */
    public void elimina(T elemento) {
	Vertice<T> v = buscaVertice(elemento);
	vertices.elimina(v);
	for(Arista<T> a : v.aristas){
	    Arista<T> aa = buscaArista(a.vecino, v);
	    a.vecino.aristas.elimina(aa);
	    cardinalidadAristas--;
	}
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
	if(buscaArista(na,nb)!=null && buscaArista(nb,na)!=null)
	    return true;
	return false;
        // Aquí va su código.
    }
    
    /**
     * Regresa el peso de la arista que comparten los vértices que
     * contienen a los elementos recibidos.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return el peso de la arista que comparten los vértices que
     *         contienen a los elementos recibidos, o -1 si los
     *         elementos no están conectados.
     * @throws NoSuchElementException si a o b no son elementos de
     *         la gráfica.
     */
    public double getPeso(T a, T b) {
	Vertice<T> na=buscaVertice(a);
	Vertice<T> nb=buscaVertice(b);
	if(sonVecinos(a,b))
	    return buscaArista(na,nb).peso;
	return -1;
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
        // Aquí va su código.
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
	    for(Arista<T> b : aux.aristas){
		if(b.vecino.color==Color.ROJO){
		    b.vecino.setColor(Color.NINGUNO);
		    cola.mete(b.vecino);
		}
	    }
	    /*
	    for(Vertice<T> k : vertices)
		if(k.color==Color.ROJO){
		    k.color=Color.NEGRO;
		    cola.mete(k);
		}
	     */
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
	for(Vertice<T> a : vertices)
	    a.setColor(Color.ROJO);
        Vertice<T> v = buscaVertice(elemento);
        Pila<Vertice<T>> pila = new Pila<Vertice<T>>();
        v.setColor(Color.NEGRO);
        pila.mete(v);
        while(!pila.esVacia()) {
        	Vertice<T> aux = pila.saca();
        	accion.actua(aux);
        	for(Arista<T> b : aux.aristas) {
        		if(b.vecino.color == Color.ROJO) {
        			b.vecino.setColor(Color.NINGUNO);
        			pila.mete(b.vecino);
        		}
        	}
        }
    }
    
    /**
     * Calcula una trayectoria de distancia mínima entre dos
     * vértices.
     * @param origen el vértice de origen.
     * @param destino el vértice de destino.
     * @return Una lista con vértices de la gráfica, tal que forman
     *         una trayectoria de distancia mínima entre los
     *         vértices <tt>a</tt> y <tt>b</tt>. Si los elementos se
     *         encuentran en componentes conexos distintos, el
     *         algoritmo regresa una lista vacía.
     * @throws NoSuchElementException si alguno de los dos elementos
     *         no está en la gráfica.
     */
    public Lista<VerticeGrafica<T>> trayectoriaMinima(T origen, T destino) {
	Vertice<T> va=buscaVertice(origen);
	Vertice<T> vb=buscaVertice(destino);
	Lista<VerticeGrafica<T>> t=new Lista<VerticeGrafica<T>>();
	if(va==vb){
	    t.agregaFinal(va);
	    return t;
	}
	for(Vertice<T> v : vertices)
	    v.distancia=-1;
	va.distancia=0;
	Cola<Vertice<T>> c=new Cola<Vertice<T>>();
	c.mete(va);
	calculaDistancias(c,1.0);
	if(vb.distancia==-1)
	    return t;
	construyeTrayectoria(t,vb,va);
	return t;
    }
    
    /**
     * Calcula la ruta de peso mínimo entre el elemento de origen y
     * el elemento de destino.
     * @param origen el vértice origen.
     * @param destino el vértice destino.
     * @return una trayectoria de peso mínimo entre el vértice
     *         <tt>origen</tt> y el vértice <tt>destino</tt>. Si los
     *         vértices están en componentes conexas distintas,
     *         regresa una lista vacía.
     * @throws NoSuchElementException si alguno de los dos elementos
     *         no está en la gráfica.
     */
 
    public Lista<VerticeGrafica<T>> dijkstra(T origen, T destino) {
        // Aquí va su código.
        Vertice<T> va = buscaVertice(origen);
        Vertice<T> vb = buscaVertice(destino);
        Lista<VerticeGrafica<T>> t = new Lista<VerticeGrafica<T>>();
        if (origen == destino){
	    t.agregaFinal(va);
	    return t;
        }
        for (Vertice<T> v : vertices)
        	v.distancia = -1;
        va.distancia = 0;
        MonticuloMinimo<Vertice<T>> m = new MonticuloMinimo<Vertice<T>>(vertices);
        while (!m.esVacio()) {
        	Vertice<T> aux = m.elimina();
        	for (Arista<T> a : aux.aristas) {
        		if(a.vecino.distancia == -1 || aux.distancia + a.peso < a.vecino.distancia) {
        			a.vecino.distancia = aux.distancia + a.peso;
        			m.get(a.vecino.getIndice()).distancia = aux.distancia + a.peso;
                	m.reordena(a.vecino);
        		}
        	}
        }
        if (vb.distancia == -1)
        	return t;
        construye(t, vb, va);
        return t;
    }
    

    private void construye(Lista<VerticeGrafica<T>> t, Vertice<T> va, Vertice vb) {
    	t.agregaInicio(va);
    	if (va == vb)
    		return;
    	for (Arista<T> a : va.aristas) {
    		if (a.vecino.distancia == va.distancia - a.peso) {
    			construye(t, a.vecino, vb);
    			return;
    		}
    	}
    }
   private Vertice<T> buscaVertice(T elemento){
       for(Vertice<T> k : vertices)
	   if(elemento.equals(k.elemento))
	       return  k;
	throw new NoSuchElementException();
   }
   
   private Arista<T> buscaArista(Vertice<T> a, Vertice<T> b){
       for(Arista<T> r : a.aristas)
	   if(r.vecino.equals(b))
		return r;
       return null;
   }
   
   private boolean sonIguales(T a, T b){
	if(a.equals(b))
	    return true;
	return false;
    }
    
    private void calculaDistancias(Cola<Vertice<T>> c, double d){
	if(c.esVacia())
	    return;
	Cola<Vertice<T>> vec = new Cola<Vertice<T>>();
	while(!c.esVacia()){
	    Vertice<T> v=c.saca();
	    for(Arista <T> a : v.aristas){
		if(a.vecino.distancia==-1){
		    a.vecino.distancia=d;
		    vec.mete(a.vecino);
		} 
	    }
	}
	calculaDistancias(vec,d+1);
    }
    
    private void construyeTrayectoria(Lista<VerticeGrafica<T>> t, Vertice<T> va, Vertice<T> vb){
	t.agregaInicio(va);
	if(va==vb)
	    return;
	for(Arista<T> a : va.aristas){
	    if(a.vecino.distancia==va.distancia-1){
		construyeTrayectoria(t,a.vecino,vb);
		return;
	    }
	}
    }
}
