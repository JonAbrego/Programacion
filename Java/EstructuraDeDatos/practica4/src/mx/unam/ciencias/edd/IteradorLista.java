package mx.unam.ciencias.edd;

import java.util.Iterator;

/**
 * Interface para iteradores de listas, que extiende a la interfaz
 * {@link java.util.Iterator}.
 */
public interface IteradorLista<T> extends Iterator<T> {

    /**
     * Nos dice si existe un elemento anterior.
     * @return <tt>true</tt> si sí hay un elemento anterior,
     *         <tt>false</tt> en otro caso.
     */
    public boolean hasPrevious();

    /**
     * Regresa el elemento anterior, moviendo el iterador a la
     * izquierda.
     * @return el elemento anterior, si existe.
     * @throws NoSuchElementException, si no hay elemento anterior.
     */
    public T previous();

    /**
     * Mueve el iterador al inicio de la lista. Después de llamar
     * este método {@link IteradorLista#hasPrevious} regresa
     * <tt>false</tt>, y {@link IteradorLista#next} regresa el
     * primer elemento de la lista.
     */
    public void start();

    /**
     * Mueve el iterador al final de la lista. Después de llamar
     * este método {@link IteradorLista#hasNext} regresa
     * <tt>false</tt>, y {@link IteradorLista#previous} regresa el
     * último elemento de la lista.
     */
    public void end();
}
