package mx.unam.ciencias.edd;

/**
 * Clase para colas genéricas.
 */
public class Cola<T> extends MeteSaca<T> {

    /**
     * Construye una cola vacía.
     */
    public Cola() {
        super();
    }

    /**
     * Elimina el elemento al frente de la cola y lo regresa.
     * @return el elemento al frente de la cola.
     */
    @Override public T saca() {	
	T primero=lista.getUltimo();
	lista.eliminaUltimo();
	return primero;// Aquí va su código.
    }

    /**
     * Nos permite ver el elemento al inicio de la cola, sin sacarlo
     * de la misma.
     */
    @Override public T mira() {
	return lista.getUltimo();
    }
}
