package mx.unam.ciencias.edd;

/**
 * Clase para excepciones de índices de lista inválidos.
 */
public class ExcepcionIndiceInvalido extends IndexOutOfBoundsException {
    public ExcepcionIndiceInvalido() {
        super();
    }

    public ExcepcionIndiceInvalido(String mensaje) {
        super(mensaje);
    }
}
