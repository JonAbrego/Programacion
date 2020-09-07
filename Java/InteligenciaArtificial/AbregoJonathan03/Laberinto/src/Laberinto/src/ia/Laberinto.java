/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.Random;
import java.util.Stack;
import processing.core.PApplet;
import processing.core.PFont;

/**
 *
 * @author jon
 */
public class Laberinto extends PApplet {

    PFont fuente;
    int alto = 21;
    int ancho = 15;
    int celda = 20;
    Modelo modelo;

    @Override
    public void settings() {
        size(ancho * celda, (alto * celda) + 32);
    }

    @Override
    public void setup() {
        background(20);
        fuente = createFont("Arial", 12, true);
        modelo = new Modelo(ancho, alto, celda);
    }

    @Override
    public void draw() {
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (modelo.posX == j && modelo.posY == i) {
                    fill(255, 67, 88);
                } else if (modelo.mundo[i][j].queda) {
                    //fill(255, 210, 0);
                    fill(255);
                } else if (modelo.mundo[i][j].visitada) {
                    //fill(255);
                    fill(255, 210, 0);
                } else {
                    fill(200);
                }
                rect(j * modelo.tamanio, i * modelo.tamanio, modelo.tamanio, modelo.tamanio);
                if (modelo.mundo[i][j].paredIzq) {
                    stroke(0);
                    line(j * modelo.tamanio, i * modelo.tamanio, j * modelo.tamanio, ((i + 1) * modelo.tamanio));
                }
                if (modelo.mundo[i][j].paredArriba) {
                    stroke(0);
                    line(j * modelo.tamanio, i * modelo.tamanio, ((j + 1) * modelo.tamanio), i * modelo.tamanio);
                }
                if (modelo.mundo[i][j].paredDer) {
                    stroke(0);
                    line((j * modelo.tamanio) + modelo.tamanio, i * modelo.tamanio, (j + 1) * modelo.tamanio, (((i + 1) * modelo.tamanio)));
                }
                if (modelo.mundo[i][j].paredAbajo) {
                    stroke(0);
                    line(j * modelo.tamanio, (i * modelo.tamanio) + modelo.tamanio, ((j + 1) * modelo.tamanio), ((i + 1) * modelo.tamanio));
                }
                stroke(255);
            }
        }
        fill(0);
        rect(0, alto * celda, (ancho * celda), 32);
        fill(255);
        textFont(fuente, 10);
        text("Cuadricula: " + modelo.ancho + " x " + modelo.alto, 5, (alto * celda) + 12);
        modelo.creaLaberinto();
    }

    /**
     * Representación de cada celda de la cuadrícula.
     */
    class Celda {

        int celdaX, celdaY;
        boolean paredIzq;
        boolean paredArriba;
        boolean paredDer;
        boolean paredAbajo;
        boolean queda;
        boolean visitada;

        /**
         * Constructor de una celda
         *
         * @param celdaX Coordenada en x
         * @param celdaY Coordenada en y
         */
        Celda(int celdaX, int celdaY) {
            this.celdaX = celdaX;
            this.celdaY = celdaY;
            this.paredAbajo = true;
            this.paredArriba = true;
            this.paredDer = true;
            this.paredIzq = true;
            this.visitada = false;
            this.queda = false;
        }
    }

    /**
     * Representacion del laberinto
     */
    class Modelo {

        int ancho, alto;
        int tamanio;
        int generacion;
        int posX;
        int posY;
        int direccion;
        Celda[][] mundo;
        Random rnd = new Random();
        Stack<Celda> pila = new Stack<>();

        /**
         * Constructor del modelo, donde elegimos una celda al azar para poder
         * partir de ella, y agregandola al stack y marcandola como visitada
         *
         * @param ancho Cantidad de celdas a lo ancho en la cuadricula.
         * @param ancho Cantidad de celdas a lo largo en la cuadricula.
         * @param tamanio Tamaño (en pixeles) de cada celda cuadrada que compone
         * la cuadricula.
         */
        Modelo(int ancho, int alto, int tamanio) {
            this.ancho = ancho;
            this.alto = alto;
            this.tamanio = tamanio;
            mundo = new Celda[alto][ancho];
            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    mundo[i][j] = new Celda(i, j);
                }
            }
            this.posX = rnd.nextInt(ancho);
            this.posY = rnd.nextInt(alto);
            pila.push(mundo[posY][posX]);
            mundo[posY][posX].visitada = true;
        }

        /**
         * <p>
         * Elegiremos una direccion aleatoria, con numeros entre 1-4 segun lo
         * que salga determina la direccion.<br> Posibles movimientos:</p>
         * | 1 |</br>
         * | 0 | c | 2 |</br>
         * | 3 |</br>
         * <p>
         * Y por ultimos haremos que cada vez que nos movasemos a alguna la
         * agregaremos a nuestra pilad
         * </p>
         *
         * @param direccion sera para indicar hacia donde debemos movernos
         */
        public void mover(int direccion) {
            switch (direccion) {
                case 0:
                    haciaIzquierda();
                    break;
                case 1:
                    haciaArriba();
                    break;
                case 2:
                    haciaDerecha();
                    break;
                case 3:
                    haciaAbajo();
                    break;
            }
        }

        /**
         * Para movernos hacia la izquierda es necesario quitar la pared de la
         * celda sobre la que estemos parados y quitaremos la pared de la nueva
         * celda, la cual sera la contraria de la que hayamos quitado primero,
         * en este caso seria quitar la pared izquierda de nuestra celda actual
         * y quitar la derecha de la nueva a la que nos movemos.</br>
         */
        public boolean haciaIzquierda() {
            int x = posX - 1;
            if (x >= 0 && mundo[posY][posX - 1].visitada == false) {
                mundo[posY][posX].paredIzq = false;
                posX = x;
                pila.push(mundo[posY][posX]);
                mundo[posY][posX].visitada = true;
                mundo[posY][posX].paredDer = false;
                return true;
            }
            return false;
        }

        /**
         * Para movernos hacia arriba es necesario quitar la pared de la celda
         * sobre la que estemos parados y quitaremos la pared de la nueva celda,
         * la cual sera la contraria de la que hayamos quitado primero, en este
         * caso seria quitar la pared arriba de nuestra celda actual y quitar la
         * de abajo de la nueva a la que nos movemos.</br>
         */
        public void haciaArriba() {
            int y = posY - 1;
            if (y >= 0 && mundo[posY - 1][posX].visitada == false) {
                mundo[posY][posX].paredArriba = false;
                posY = y;
                pila.push(mundo[posY][posX]);
                mundo[posY][posX].visitada = true;
                mundo[posY][posX].paredAbajo = false;
            }
        }

        /**
         * Para movernos hacia la derecha es necesario quitar la pared de la
         * celda sobre la que estemos parados y quitaremos la pared de la nueva
         * celda, la cual sera la contraria de la que hayamos quitado primero,
         * en este caso seria quitar la pared derecha de nuestra celda actual y
         * quitar la izquierda de la nueva a la que nos movemos.</br>
         */
        public void haciaDerecha() {
            int x = posX + 1;
            if (x < ancho && mundo[posY][posX + 1].visitada == false) {
                mundo[posY][posX].paredDer = false;
                posX = x;
                pila.push(mundo[posY][posX]);
                mundo[posY][posX].visitada = true;
                mundo[posY][posX].paredIzq = false;
            }
        }

        /**
         * Para movernos hacia la izquierda es necesario quitar la pared de la
         * celda sobre la que estemos parados y quitaremos la pared de la nueva
         * celda, la cual sera la contraria de la que hayamos quitado primero,
         * en este caso seria quitar la pared de abajo de nuestra celda actual y
         * quitar la de arriba de la nueva a la que nos movemos.</br>
         */
        public void haciaAbajo() {
            int y = posY + 1;
            if (y < alto && mundo[posY + 1][posX].visitada == false) {
                mundo[posY][posX].paredAbajo = false;
                posY = y;
                pila.push(mundo[posY][posX]);
                mundo[posY][posX].visitada = true;
                mundo[posY][posX].paredArriba = false;
            }
        }

        /**
         * Usamos el metodo para poder validar si una casilla aun puede moverse
         * hacia alguna direccion valida
         *
         * @param x coordenada x a verificar
         * @param y coordenada y a verificar
         * @return
         */
        public boolean seMueve(int x, int y) {
            x = posX;
            y = posY;
            if (x == 0 && y == 0) {
                if (mundo[posY][posX + 1].visitada == true && mundo[posY + 1][posX].visitada == true) {
                    return false;
                }
            }
            if (x == ancho - 1 && y == alto - 1) {
                if (mundo[posY][posX - 1].visitada == true && mundo[posY - 1][posX].visitada == true) {
                    return false;
                }
            }
            if (x == 0 && y == alto - 1) {
                if (mundo[posY - 1][posX].visitada == true && mundo[posY][posX + 1].visitada == true) {
                    return false;
                }
            }
            if (x == ancho - 1 && y == 0) {
                if (mundo[posY][posX - 1].visitada == true && mundo[posY + 1][posX].visitada == true) {
                    return false;
                }
            }
            if (y > 0 && y < alto - 1 && x == 0) {
                if (mundo[posY - 1][posX].visitada == true && mundo[posY + 1][posX].visitada == true
                        && mundo[posY][posX + 1].visitada == true) {
                    return false;
                }
            }
            if (x > 0 && x < ancho - 1 && y == 0) {
                if (mundo[posY][posX - 1].visitada == true && mundo[posY][posX + 1].visitada == true
                        && mundo[posY + 1][posX].visitada == true) {
                    return false;
                }
            }

            if (y > 0 && y < alto - 1 && x == ancho - 1) {
                if (mundo[posY - 1][posX].visitada == true && mundo[posY + 1][posX].visitada == true
                        && mundo[posY][posX - 1].visitada == true) {
                    return false;
                }
            }

            if (x > 0 && x < ancho - 1 && y == alto - 1) {
                if (mundo[posY][posX - 1].visitada == true && mundo[posY][posX + 1].visitada == true
                        && mundo[posY - 1][posX].visitada == true) {
                    return false;
                }
            }

            if (y > 0 && y < alto - 1 && x > 0 && x < ancho - 1) {
                if (mundo[posY - 1][posX].visitada == true && mundo[posY + 1][posX].visitada == true
                        && mundo[posY][posX - 1].visitada == true && mundo[posY][posX + 1].visitada == true) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Lo que realizamos es ver si aun se pude mover en caso de que no sea
         * posible sacamos de la pila el ultimo elemento y sobre ese verificamos
         * si se puede mover, ademas de marcar cada casilla que se saca queda
         * como<br>True</br>, es decir que ya quedo como parte del laberinto
         */
        public void creaLaberinto() {
            try {
                if (seMueve(posX, posY) == false) {
                    Celda mover = pila.pop();
                    posX = mover.celdaY;
                    posY = mover.celdaX;
                    mundo[posY][posX].queda = true;
                } else {
                    mover(rnd.nextInt(4));
                }
            } catch (Exception e) {
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{"ia.Laberinto"});
    }
}
