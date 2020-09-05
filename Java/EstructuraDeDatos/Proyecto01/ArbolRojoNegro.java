package mx.unam.ciencias.edd;

import java.io.FileWriter;
import java.util.Random;

/**
 * Clase para árboles rojo-negros. Un árbol rojo-negro cumple las
 * siguientes propiedades:
 *
 * <ol>
 *  <li>Todos los nodos son NEGROS o ROJOS.</li>
 *  <li>La raíz es NEGRA.</li>
 *  <li>Todas las hojas (<tt>null</tt>) son NEGRAS (al igual que la
 *      raíz).
 *  <li>Un nodo ROJO siempre tiene dos hijos NEGROS.</li>
 *  <li>Todo camino de un nodo a alguno de sus descendientes tiene
 *      el mismo número de nodos NEGROS.</li>
 * </ol>
 *
 * Los árboles rojo-negros son autobalanceados, y por lo tanto las
 * operaciones de inserción, eliminación y búsqueda pueden
 * realizarse en <i>O</i>(log <i>n</i>).
 */
public class ArbolRojoNegro<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /* Método auxiliar para obtener el tío de un nodo (el hermano de
       su padre). */
    private Nodo<T> tio(Nodo<T> nodo) {
        if (nodo.padre == null || nodo.padre.padre == null)
            /* No hay tío en ambos casos. */
            return null;
        Nodo<T> abuelo = nodo.padre.padre;
        if (nodo.padre == abuelo.izquierdo)
            return abuelo.derecho;
        return abuelo.izquierdo;
    }

    /* Método auxiliar para obtener el hermano de un nodo. */
    private Nodo<T> hermano(Nodo<T> nodo) {
        if (nodo.padre == null)
            /* No hay padre, entonces no hay hermano. */
            return null;
        Nodo<T> padre = nodo.padre;
        if (padre.izquierdo == nodo)
            return padre.derecho;
        return padre.izquierdo;
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método
     * {@link ArbolBinarioOrdenado#agrega}, y después balancea el
     * árbol recoloreando nodos y girando el árbol como sea
     * necesario.
     * @param elemento el elemento a agregar.
     * @return un iterador que apunta al nodo del nuevo elemento.
     */
    @Override public IteradorArbolBinario<T> agrega(T elemento) {
        IteradorArbolBinario<T> iterador = super.agrega(elemento);
        Nodo<T> nodo = nodoDeIterador(iterador);
        /* Inicialmente, siempre coloreamos ROJO al nuevo nodo. */
        nodo.color = Color.ROJO;
        rebalanceaAgregado(nodo);
        return iterador(nodo);
    }

    /* Balancea el árbol rojo-negro después de que agregamos un
       nuevo elemento. */
    private void rebalanceaAgregado(Nodo<T> nodo) {
        /* Caso 1: El padre del nodo es null, por lo tanto es la
           raíz, así que sólo coloreamos el nodo de NEGRO y
           terminamos. */
        if (nodo.padre == null) {
            raiz = nodo;
            nodo.color = Color.NEGRO;
            return;
        }
        /* A partir de aquí, el padre no es nulo. */
        Nodo<T> p = nodo.padre;
        /* Caso 2: El color del padre es NEGRO; como el nuevo nodo
           es ROJO, sencillamente terminamos. */
        if (p.color == Color.NEGRO)
            return;
        /* A partir de aquí, el color del padre es ROJO; por lo
           tanto, como la raíz es NEGRA, el abuelo siempre
           existe. */
        Nodo<T> t = tio(nodo);
        Nodo<T> a = p.padre;
        /* Caso 3: El tío es ROJO. Como el padre también es ROJO,
           los cambiamos a NEGRO, coloreamos el abuelo de ROJO (ya
           podemos hacerlo, porque el tío y el padre a son NEGROS),
           y llamamos al método recursivamente sobre el abuelo. */
        if (t != null && t.color == Color.ROJO) {
            p.color = Color.NEGRO;
            t.color = Color.NEGRO;
            a.color = Color.ROJO;
            rebalanceaAgregado(a);
            return;
        }
        /* A partir de aquí, el tío es negro, o null (que lo tomamos
           como negro), y lo ignoramos. */

        /* Caso 4: El nodo y su padre están "cruzados"
           (izquierdo-derecho o derecho-izquierdo). Los
           enderezamos. */
        if (nodo == p.derecho && p == a.izquierdo) {
            /* Derecho-izquierdo: gira izquierda sobre el padre. */
            giraIzquierda(p);
            nodo = nodo.izquierdo;
        } else if (nodo == p.izquierdo && p == a.derecho) {
            /* Izquierdo-derecho: gira derecha sobre el padre. */
            giraDerecha(p);
            nodo = nodo.derecho;
        }
        /* Las referencias a y p pudieron haber cambiado en el if de
           arriba, así que las actualizamos. */
        p = nodo.padre;
        a = p.padre;
        /* Caso 5: Tenemos al nodo y su padre como
           izquierdo-izquierdo o derecho-derecho, y ambos son
           ROJOS. Coloreamos al padre de NEGRO, al abuelo de ROJO, y
           giramos a la derecha o izquierda dependiendo de qué tipo
           sean el nodo y su padre. */
        p.color = Color.NEGRO;
        a.color = Color.ROJO;
        if (nodo == p.izquierdo) {
            giraDerecha(a);
        } else {
            giraIzquierda(a);
        }
    }

    /* Elimina un nodo si es fantasma. */
    private void eliminaNodoFantasma(Nodo<T> nodo) {
        if (nodo.elemento != null)
            return;
        /* Si el elemento es null, es fantasma. */
        Nodo<T> p = nodo.padre;
        if (p != null) {
            /* Lo desconectamos de su padre, si existe. */
            if (p.izquierdo == nodo)
                    p.izquierdo = null;
            else
                p.derecho = null;
        }
        nodo.padre = null;
        /* Actualizamos la raíz de ser necesario. */
        if (raiz == nodo)
            raiz = null;
    }

    /* Rebalancea a partir de un nodo (tal vez fantasma) que
       reemplazó un eliminado . */
    private void rebalanceaEliminado(Nodo<T> nodo) {
        /* Caso 1: llegamos a la raíz, así que terminamos. */
        if (nodo.padre == null) {
            raiz = nodo;
            return;
        }
        /* A partir de aquí, el padre es distinto de null, y tenemos
           que tener hermano distinto de null, porque el nodo es
           siempre NEGRO, y el árbol era balanceado antes de
           eliminar. */
        Nodo<T> p = nodo.padre;
        Nodo<T> h = hermano(nodo);
        /* Caso 2: el hermano es ROJO; recoloreamos al padre de
           ROJO, al hermano de NEGRO, y giramos sobre el padre de
           acuerdo a si el nodo es izquierdo o derecho. */
        if (h.color == Color.ROJO) {
            p.color = Color.ROJO;
            h.color = Color.NEGRO;
            /* Giramos sobre el padre. */
            if (nodo == p.izquierdo)
                giraIzquierda(p);
            else
                giraDerecha(p);
        }
        /* Pudieron cambiar arriba. */
        p = nodo.padre;
        h = hermano(nodo);
        /* Caso 3: todo mundo (el padre, el hermano, y los hijos del
           hermano) es NEGRO. Recoloreamos el hermano de ROJO, y
           rebalanceamos sobre el padre. */
        if (p.color == Color.NEGRO && h.color == Color.NEGRO &&
            (h.izquierdo == null || h.izquierdo.color == Color.NEGRO) &&
            (h.derecho == null || h.derecho.color == Color.NEGRO)) {
            h.color = Color.ROJO;
            /* Única recursión, terminamos al regresar. */
            rebalanceaEliminado(p);
            return;
        }
        /* Caso 4: el hermano y sus hijos son NEGROS, pero el padre
           es ROJO. Intercambiamos los colores del padre y del
           hermano (lo que balancea el árbol) y terminamos. */
        if (p.color == Color.ROJO && h.color == Color.NEGRO &&
            (h.izquierdo == null || h.izquierdo.color == Color.NEGRO) &&
            (h.derecho == null || h.derecho.color == Color.NEGRO)) {
            h.color = Color.ROJO;
            p.color = Color.NEGRO;
            return;
        }
        /* Caso 5: el hermano es NEGRO y sus hijos bicolores;
           recoloreamos al hermano ROJO y al hijo ROJO como NEGRO, y
           giramos dependiendo de si el nodo es izquierdo o
           derecho. */
        if  (h.color == Color.NEGRO) {
            /* Giramos sobre el hermano (si giramos). */
            if (nodo == p.izquierdo &&
                (h.derecho == null ||
                 h.derecho.color == Color.NEGRO) &&
                (h.izquierdo != null &&
                 h.izquierdo.color == Color.ROJO)) {
                h.color = Color.ROJO;
                h.izquierdo.color = Color.NEGRO;
                giraDerecha(h);
            } else if (nodo == p.derecho &&
                       (h.izquierdo == null ||
                        h.izquierdo.color == Color.NEGRO) &&
                       (h.derecho != null &&
                        h.derecho.color == Color.ROJO)) {
                        h.color = Color.ROJO;
                        h.derecho.color = Color.NEGRO;
                        giraIzquierda(h);
            }
        }
        /* Pudieron cambiar arriba. */
        p = nodo.padre;
        h = hermano(nodo);
        /* Caso 6: Si llegamos aquí, o bien el hijo derecho del
           hermano es ROJO, y el nodo es izquierdo, o el hijo
           izquierdo del hermano es ROJO, y el nodo es
           derecho. Coloreamos al hermano del color del padre y al
           padre NEGRO, al hijo ROJO del hermano como NEGRO, y
           giramos sobre el padre. */
        h.color = p.color;
        p.color = Color.NEGRO;
        /* Giramos sobre el padre. */
        if (nodo == p.izquierdo) {
            h.derecho.color = Color.NEGRO;
            giraIzquierda(p);
        } else {
            h.izquierdo.color = Color.NEGRO;
            giraDerecha(p);
        }
    }

    /**
     * Elimina un elemento del árbol. El método elimina el nodo que
     * contiene el elemento, y recolorea y gira el árbol como sea
     * necesario para rebalancearlo. La complejidad del método es
     * <i>O</i>(log <i>n</i>) siempre.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
        IteradorArbolBinario<T> iterador = busca(elemento);
        if (iterador == null)
            /* Si el elemento no está, no hacemos nada. */
            return;
        /* Pase lo que pase, eliminaremos un elemento. */
        numeroDeElementos--;
        Nodo<T> nodo = nodoDeIterador(iterador);
        /* Buscamos el elemento anterior al que queremos
           eliminar. */
        Nodo<T> anterior = buscaNodoAnterior(nodo);
        /* Si anterior existe, reemplazamos sus valores, dejando la
           coloración y el orden del árbol intactos (una vez que
           hayamos eliminado). */
        if (anterior != null) {
            T temp = nodo.elemento;
            nodo.elemento = anterior.elemento;
            anterior.elemento = temp;
            nodo = anterior;
        }
        /* Sacamos el hijo del nodo, que a lo más tiene un hijo. */
        Nodo<T> hijo = (nodo.derecho == null) ?
            nodo.izquierdo : nodo.derecho;
        /* Si el nodo no tiene ningún hijo, le creamos un nodo hijo
           fantasma para facilitarnos la vida. */
        if (hijo == null) {
            /* Es nodo fantasma. */
            hijo = new ArbolBinario<T>.Nodo<T>(null);
            hijo.padre = nodo;
            /* Un nodo fantasma (como si fuera null) es NEGRO. */
            hijo.color = Color.NEGRO;
            nodo.izquierdo = hijo;
        }
        /* Reemplazamos a nodo con su hijo (lo subimos). */
        hijo.padre = nodo.padre;
        if (nodo.padre != null) {
            if (nodo == nodo.padre.izquierdo)
                nodo.padre.izquierdo = hijo;
            else
                nodo.padre.derecho = hijo;
        }
        /* Actualizamos la raíz de ser necesario. */
        if (nodo == raiz)
            raiz = hijo;
        /* Desconectamos el nodo, por si acaso. */
        nodo.padre = nodo.izquierdo = nodo.derecho = null;
        /* Si el color del nodo es NEGRO, y el de su hijo es ROJO,
           sólo hay que recolorear al hijo. */
        if (nodo.color == Color.NEGRO && hijo.color == Color.ROJO) {
            hijo.color = Color.NEGRO;
            /* No eliminamos el nodo fantasma porque nunca lo
               creamos (el hijo era ROJO). */
            return;
        }
        /* Si el color del nodo es NEGRO, y el de su hijo es NEGRO,
           hay que rebalancear. */
        if (nodo.color == Color.NEGRO && hijo.color == Color.NEGRO) {
            rebalanceaEliminado(hijo);
            /* Eliminamos al nodo fantasma de ser necesario. */
            eliminaNodoFantasma(hijo);
            return;
        }
        /* Si llegamos aquí el color del nodo es ROJO. No hacemos
           nada, excepto eliminar el nodo fantasma. */
        eliminaNodoFantasma(hijo);
    }
    
      public String generaScalableVectorGraphics() {
	double tamImagenY = (profundidad()+1) * 70;
	double tamImagenX = ((getNumeroDeElementos()+1)*2) * 100;
	String svg = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n";
	svg += "<svg width=\""+ tamImagenX +"\" height=\""+ (tamImagenY*2) +"\">\n ";
	svg+="<g>\n";
	svg+= " <rect  x=\"0\" y=\"0\" width=\""+tamImagenX+"\" height=\""+ (tamImagenY*2)+"\" style=\"fill:white;stroke:black;stroke-width:.5;fill-opacity:1;stroke-opacity:1\" /> \n";
	svg += generaSVG(raiz,tamImagenX/2,80);
	svg += "</g>\n"+"</svg>";
	return svg;
    }
    
    private String generaSVG(Nodo<T> nodo, double x, double y){
	double tamImagenX = ((getNumeroDeElementos()*2) + 1) * 100;
	String s = "";
	double d = (y/getNumeroDeElementos())+5;
	double y1=y;
	double x1=x+tamImagenX/d;
	double x2=x-tamImagenX/d;
	double y2=y+90;
	if(nodo.izquierdo!=null){
	    s+="<line x1=\"" + x + "\" y1=\"" + y1 + "\" x2=\"" + x2 + "\" y2=\"" + y2 + "\" stroke=\"black\" stroke-width=\"4\" />\n";
	    s+= generaSVG(nodo.izquierdo,x-tamImagenX/d,y+100);
	}
	if(nodo.derecho!=null){
	    s+="<line x1=\"" + x + "\" y1=\"" + y1 + "\" x2=\"" + x1 + "\" y2=\"" + y2 + "\" stroke=\"black\" stroke-width=\"4\" />\n";
	    s+= generaSVG(nodo.derecho,x+tamImagenX/d,y+100);
	}
	if(nodo.color==Color.ROJO)
	    s+="<circle cx=\"" + x + "\" cy=\"" + y+ "\" r=\"20\" stroke=\"black\" stroke-width=\"3\" fill=\"red\" />\n";
	else
	    s+="<circle cx=\"" + x + "\" cy=\"" + y+ "\" r=\"20\" stroke=\"black\" stroke-width=\"3\" fill=\"black\" />\n";
	s+="<text fill=\"white\" font-family=\"sans-serif\" font-size=\"20\" x=\"" + x + "\" y=\"" + y1 + "\"\n" + "text-anchor=\"middle\">" + nodo.elemento + "</text>";
	return s;
    }
}
