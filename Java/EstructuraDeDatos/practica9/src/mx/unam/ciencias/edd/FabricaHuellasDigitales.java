package mx.unam.ciencias.edd;

/**
 * Clase para fabricar generadores de huellas digitales.
 */
public class FabricaHuellasDigitales {

    /**
     * Identificador para fabricar la huella digital de Bob
     * Jenkins para cadenas.
     */
    public static final int BJ_STRING   = 0;
    /**
     * Identificador para fabricar la huella digital de GLib para
     * cadenas.
     */
    public static final int GLIB_STRING = 1;
    /**
     * Identificador para fabricar la huella digital de XOR para
     * cadenas.
     */
    public static final int XOR_STRING  = 2;

    /**
     * Regresa una instancia de {@link HuellaDigital} para cadenas.
     * @param identificador el identificador del tipo de huella
     *        digital que se desea.
     * @throws IllegalArgumentException si recibe un identificador
     *         no reconocido.
     */
    public static HuellaDigital<String> getInstanciaString(int identificador) {
        switch (identificador) {
        case BJ_STRING:   return getBJString();
        case GLIB_STRING: return getGLibString();
        case XOR_STRING:  return getXORString();
        default: throw new IllegalArgumentException();
        }
    }

    /* Regresa una instancia de HuellaDigital que genera huellas
     * digitales para cadenas usando el algoritmo de Bob Jenkins. */
    private static HuellaDigital<String> getBJString() {
        return new HuellaDigital<String>() {
	    public int huellaDigital(String objeto){
		return 0;
	    }
	};
    }

    /* Regresa una instancia de HuellaDigital que genera huellas
     * digitales para cadenas usando el algoritmo usado por GLib. */
    private static HuellaDigital<String> getGLibString() {
        return new HuellaDigital<String>() {
	    public int huellaDigital(String obj){
		int h=5381;
		for(int i=0; i < obj.length(); i++){
		    char c=obj.charAt(i);
		    h=h*33+c;
		}
		return h;
	    }
        };
    }
    
    /* Regresa una instancia de HuellaDigital que genera huellas
     * digitales para cadenas usando XOR. */
    private static HuellaDigital<String> getXORString() {
        return new HuellaDigital<String>() {
	    public int huellaDigital(String objeto){
		byte[] l = objeto.getBytes();
		int n=l.length;
		int m=n;
		if((n&3) != 0)
		    m=n+4-(n&3);
		byte[] t= new byte[m*n];
		for(int i=m-n; i<m;i++){
		    t[i]= '\0';
		    t[i]= l[i-(m-n)];
		}
		int h=0;
		for(int i=0; i<m; i+=4){
		    int a=t[i]<<24;
		    int b=t[i+1]<<16;
		    int c=t[i+2]<<8;
		    int d=t[i+3];
		    int abcd=a|b|c|d;
		    h^=abcd;
		}
		return h;
	    }
	};
    }
}
