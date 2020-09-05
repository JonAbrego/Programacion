package mx.unam.ciencias.edd;

/**
 * Interfaz para iteradores de árboles binarios.
 */
public interface IteradorArbolBinario<T> {

    /**
     * Nos dice si el iterador tiene un padre.
     * @return <tt>true</tt> si el iterador tiene padre,
     *         <tt>false</tt> en otro caso.
     */
    public boolean hayPadre();

    /**
     * Nos dice si el iterador tiene un izquierdo.
     * @return <tt>true</tt> si el iterador tiene izquierdo,
     *         <tt>false</tt> en otro caso.
     */
    public boolean hayIzquierdo();

    /**
     * Nos dice si el iterador tiene un derecho.
     * @return <tt>true</tt> si el iterador tiene derecho,
     *         <tt>false</tt> en otro caso.
     */
    public boolean hayDerecho();

    /**
     * Mueve el iterador a su padre.
     * @throws NoSuchElementException si el iterador no tiene padre.
     */
    public void padre();

    /**
     * Mueve el iterador a su izquierdo.
     * @throws NoSuchElementException si el iterador no tiene izquierdo.
     */
    public void izquierdo();

    /**
     * Mueve el iterador a su derecho.
     * @throws NoSuchElementException si el iterador no tiene derecho.
     */
    public void derecho();

    /**
     * Regresa el elemento al que apunta el iterador.
     * @return el elemento al que apunta el iterador.
     * @throws NoSuchElementException si el iterador no apunta a
     *         ningún elemento.
     */
    public T dame();

    /**
     * Regresa un nuevo iterador que a punta al mismo elemento.
     * @return un nuevo iterador que a punta al mismo elemento.
     * @throws NoSuchElementException si el iterador no apunta a
     *         ningún elemento.
     */
    public IteradorArbolBinario<T> copia();
}

