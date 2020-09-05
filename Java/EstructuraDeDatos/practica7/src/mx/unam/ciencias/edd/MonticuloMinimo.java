package mx.unam.ciencias.edd;

import java.util.NoSuchElementException;

/**
 * Clase para montículos mínimos (<i>min heaps</i>). Podemos crear
 * un montículo mínimo con <em>n</em> elementos en tiempo
 * <em>O</em>(<em>n</em>), y podemos agregar y actualizar elementos
 * en tiempo <em>O</em>(log <em>n</em>). Eliminar el elemento mínimo
 * también nos toma tiempo <em>O</em>(log <em>n</em>).
 */
public class MonticuloMinimo<T extends ComparableIndexable<T>> {

    private int ultimo;
    /* Usamos un truco para poder utilizar arreglos genéricos. */
    private T[] arbol;

    /* Truco para crear arreglos genéricos. Es necesario hacerlo así
       por cómo Java implementa sus genéricos; de otra forma
       obtenemos advertencias del compilador. */
    @SuppressWarnings("unchecked") private T[] creaArregloGenerico(int n) {
        return (T[])(new ComparableIndexable[n]);
    }

    /**
     * Constructor sin parámetros. Es más eficiente usar {@link
     * #MonticuloMinimo(Lista)}, pero se ofrece este constructor por
     * completez.
     */
    public MonticuloMinimo() {
	arbol=creaArregloGenerico(0);
	ultimo=-1;
        // Aquí va su código.
    }

    /**
     * Constructor para montículo mínimo que recibe una lista. Es
     * más barato construir un montículo con todos sus elementos de
     * golpe (tiempo <i>O</i>(<i>n</i>)), que el insertándolos uno
     * por uno (tiempo <i>O</i>(<i>n</i> log <i>n</i>)).
     */
    public MonticuloMinimo(Lista<T> lista) {
	int n=lista.getLongitud();
	arbol=creaArregloGenerico(n);
	int i=0;
	for(T e: lista){
	    arbol[i]=e;
	    e.setIndice(i++);
	}
	ultimo=n-1;
	for(i=n/2;i>=0;i--)
	    minimizaMonticulo(i);
        // Aquí va su código.
    }

    /**
     * Agrega un nuevo elemento en el montículo.
     * @param elemento el elemento a agregar en el montículo.
     */
    public void agrega(T elemento) {
	if(ultimo == arbol.length - 1) {
	    T[] nvo = creaArregloGenerico(arbol.length+100);
	    for (int i = 0; i < arbol.length; i++) {
		nvo[i] = arbol[i];
		nvo[i].setIndice(i); 
	    } 
	    arbol = nvo;
	}
	ultimo++; 
	arbol[ultimo] = elemento; 
	arbol[ultimo].setIndice(ultimo); 
	reordena(elemento);
    }
   
    /**
     * Elimina el elemento mínimo del montículo.
     * @return el elemento mínimo del montículo.
     */
    public T elimina() {
	if(ultimo == -1)
	    throw new IllegalStateException();
	intercambia(0,ultimo);
	T t=arbol[ultimo];
	arbol[ultimo]=null;
	ultimo--;
	minimizaMonticulo(0);
	return t;
        // Aquí va su código.
    }

    /**
     * Nos dice si el montículo es vacío.
     * @return <tt>true</tt> si ya no hay elementos en el montículo,
     *         <tt>false</tt> en otro caso.
     */
    public boolean esVacio() {
	if(ultimo == -1)
	    return true;
	return false;
    }

   /**
     * Reordena un elemento en el árbol.
     * @param elemento el elemento que hay que reordenar.
     */
    public void reordena(T elemento) {
	int i=elemento.getIndice();
	while(i>0){
	    T p=arbol[(i-1)/2];
	    if(p.compareTo(elemento)>0){
		intercambia(i,(i-1)/2);
		i=(i-1)/2;
	    }else{
		i=0;
	    }
	}
    }
    
    /**
     * Regresa el número de elementos en el montículo mínimo.
     * @return el número de elementos en el montículo mínimo.
     */
    public int getNumeroDeElementos() {
        return ultimo+1;// Aquí va su código.
    }

    /**
     * Regresa el <i>i</i>-ésimo elemento del árbol, por niveles.
     * @return el <i>i</i>-ésimo elemento del árbol, por niveles.
     * @throws NoSuchElementException si i es menor que cero, o
     *         mayor o igual que el número de elementos.
     */
    public T get(int i) {
	if(i<0 || i>=getNumeroDeElementos())
	    throw new NoSuchElementException();
	return arbol[i];
    }

    private void minimizaMonticulo(int i){
	int izq=(2*i)+1;
	int der=(2*i)+2;
	int min=i;
	if(izq<=ultimo && arbol[izq].compareTo(arbol[min])<0)
	    min=izq;
	if(der<=ultimo && arbol[der].compareTo(arbol[min])<0)
	    min=der;
	if(min != i){
	    intercambia(i,min);
	    minimizaMonticulo(min);
	}
    }
    
    private void intercambia(int i, int j){
	T aux = arbol[i];
	arbol[i]=arbol[j];
	arbol[j]=aux;
	arbol[i].setIndice(i);
	arbol[j].setIndice(j);
				       
    }
}
