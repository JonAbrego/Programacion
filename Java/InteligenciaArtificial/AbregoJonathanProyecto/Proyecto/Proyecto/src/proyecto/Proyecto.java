/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PFont;

/**
 *
 * @author Jonathan
 */
public class Proyecto extends PApplet {

    PFont fuente;
    int columnas = 20;         // Altura de la cuadricula.
    int renglones = 20;        // Anchura de la cuadricula.
    int tamanioCelda = 45;         // Tamanio de cada celda cuadrada (en pixeles).
    Modelo modelo;  // El objeto que representa el modelo del Laberinto.
    boolean expande = false;
    DecimalFormat decimales = new DecimalFormat("0.0000");

    @Override
    public void settings() {
        size(columnas * tamanioCelda, renglones * tamanioCelda + 70);
    }

    @Override
    public void setup() {
        background(50);
        modelo = new Modelo(columnas, renglones);
        modelo.inicializa();
        fuente = createFont("Arial", 12, true);
        textFont(fuente, 10);
    }

    @Override
    public void draw() {
        if (expande) {
            modelo.algoritmo();
            expande = false;
        }
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < renglones; j++) {
                if (modelo.mundo[i][j].tipo == Tipo.OBSTACULO) {
                    fill(200);
                    rect(j * tamanioCelda, i * tamanioCelda, tamanioCelda, tamanioCelda);
                } else {
                    if (modelo.mundo[i][j].conRobot) { //con robot pintamos de una forma
                        fill(0, 200, 200);
                        rect(j * tamanioCelda, i * tamanioCelda, tamanioCelda, tamanioCelda);
                    } else { //si no hay robot pero es algun sensor
                        int col = (int) ( 200*modelo.mundo[i][j].creencia+100);
                        fill(10, 70, 50);
                        rect(j * tamanioCelda, i * tamanioCelda, tamanioCelda, tamanioCelda);
                    }
                }
                if (modelo.mundo[i][j].tipo != Tipo.OBSTACULO) {
                    fill(250, 250, 250);
                    text(decimales.format(modelo.mundo[i][j].creencia), j*tamanioCelda+10,  i*tamanioCelda+20);
                }
            }
        }

        fill(0);
        rect(0, renglones * tamanioCelda, columnas * tamanioCelda, 70);

        fill(200);
        rect(10, renglones * tamanioCelda + 10, 20, 20);
        fill(255);
        text("Obstaculo", 35, renglones * tamanioCelda + 30);

        fill(0, 200, 200);
        rect(4 * tamanioCelda, renglones * tamanioCelda + 10, 20, 20);
        fill(255);
        text("Robot", 4 * tamanioCelda + 25, renglones * tamanioCelda + 30);

    }

    @Override
    public void mouseClicked() {
        expande = true;
    }

    class Robot {

        Celda posicion;
        Celda[][] mundo;
        double varianza = 1; //Variable que se utiliza como varianza
        int odometro;
        int l0, l1, l2, l3, l4, l5, l6, l7; 
        int direccion;
        Random rn = new Random();

        /**
         * Costructor de la clase robot que inicializa al robot con sus 8
         * sensores y una direccion al azar.
         */
        Robot(Celda posicion, Celda[][] mundo) {
            this.posicion = posicion;
            this.odometro = 0;
            this.mundo = mundo;
            this.direccion = rn.nextInt(8);
            this.l0 = calculaDistancia(0);
            this.l1 = calculaDistancia(1);
            this.l2 = calculaDistancia(2);
            this.l3 = calculaDistancia(3);
            this.l4 = calculaDistancia(4);
            this.l5 = calculaDistancia(5);
            this.l6 = calculaDistancia(6);
            this.l7 = calculaDistancia(7);
        }

        /**
         * Metodo que se encarga de regresar las posibles posiciones en las que
         * se puede mover el robot, verificando las 8 posibilidades de su
         * alrededor y que esten dentro de los limites de nuestro mundo,
         * haciendo uso de una celda auxiliar para que apartir de esta obtengas
         * las posicion y verifique que esten dentro de los limites y no sean
         * obtaculos, de cumplirse ambas condiciones la guardamos en una lista
         *
         * @return Lista de las direcciones en las que se puede mover.
         */
        LinkedList<Integer> posibles() {
            Celda aux = posicion;
            LinkedList<Integer> lista = new LinkedList<>();
            if (aux.x > 0 && aux.y > 0 && mundo[aux.y - 1][aux.x - 1].tipo != Tipo.OBSTACULO) { //Celda superior izquierda
                lista.add(0);
            }
            if (aux.x >= 0 && aux.y > 0 && mundo[aux.y - 1][aux.x].tipo != Tipo.OBSTACULO) {//Celda superior 
                lista.add(1);
            }
            if (aux.x < columnas - 1 && aux.y > 0 && mundo[aux.y - 1][aux.x + 1].tipo != Tipo.OBSTACULO) { //Celda superior derecha
                lista.add(2);
            }
            if (aux.x < columnas - 1 && aux.y >= 0 && mundo[aux.y][aux.x + 1].tipo != Tipo.OBSTACULO) { //Celda derecha
                lista.add(3);
            }
            if (aux.x < columnas - 1 && aux.y < renglones - 1 && mundo[aux.y + 1][aux.x + 1].tipo != Tipo.OBSTACULO) { //Celda inferior derecha
                lista.add(4);
            }
            if (aux.x >= 0 && aux.y < renglones - 1 && mundo[aux.y + 1][aux.x].tipo != Tipo.OBSTACULO) { //Celda inferior
                lista.add(5);
            }
            if (aux.x > 0 && aux.y < renglones - 1 && mundo[aux.y + 1][aux.x - 1].tipo != Tipo.OBSTACULO) { //Celda inferior izquierda
                lista.add(6);
            }
            if (aux.x > 0 && aux.y >= 0 && mundo[aux.y][aux.x - 1].tipo != Tipo.OBSTACULO) { //Celda izquierda
                lista.add(7);
            }
            return lista;
        }

        /**
         * Metodo que se encarga de hacer el movimiento del robot a una celda a
         * partir de la direccion que se recibe, a lo mas puede tener 7 posibles
         * <br>direcciones el robot R </br>
         * <br>___________________</br>
         * <br>|     |     |     |</br>
         * <br>|  0  |  1  |  2  |</br>
         * <br>|_____|_____|_____|</br>
         * <br>|     |     |     |</br>
         * <br>|  7  |  R  |  3  |</br>
         * <br>|_____|_____|_____|</br>
         * <br>|     |     |     |</br>
         * <br>|  6  |  5  |  4  |</br>
         * <br>|_____|_____|_____|</br>
         *
         *
         * @param direccion en la que se movera.
         */
        void mueve(int direccion) {
            posicion.conRobot = false;
            switch (direccion) {
                case 0:
                    posicion = mundo[posicion.y - 1][posicion.x - 1];
                    break;
                case 1:
                    posicion = mundo[posicion.y - 1][posicion.x];
                    break;
                case 2:
                    posicion = mundo[posicion.y - 1][posicion.x + 1];
                    break;
                case 3:
                    posicion = mundo[posicion.y][posicion.x + 1];
                    break;
                case 4:
                    posicion = mundo[posicion.y + 1][posicion.x + 1];
                    break;
                case 5:
                    posicion = mundo[posicion.y + 1][posicion.x];
                    break;
                case 6:
                    posicion = mundo[posicion.y + 1][posicion.x - 1];
                    break;
                case 7:
                    posicion = mundo[posicion.y][posicion.x - 1];
                    break;
            }
            posicion.conRobot = true;
        }
        
        /**
         * Segun lo leido y lo visto lo que estamos usando es una normal
         * y lo por descrito en el javadoc de Random, nextGaussian nos es util
         * para este caso
         */
        void aplicaGaussiana() {
            odometro = (int) (rn.nextGaussian() * varianza);
            l0 = (int) (rn.nextGaussian() * varianza);
            l1 = (int) (rn.nextGaussian() * varianza);
            l2 = (int) (rn.nextGaussian() * varianza);
            l3 = (int) (rn.nextGaussian() * varianza);
            l4 = (int) (rn.nextGaussian() * varianza);
            l5 = (int) (rn.nextGaussian() * varianza);
            l6 = (int) (rn.nextGaussian() * varianza);
            l7 = (int) (rn.nextGaussian() * varianza);
        }

        /**
         * Metodo que calcula la distancia hasta un obstaculo apartir de una
         * direccion dada. (Representa la lectura de un laser en una direccion).
         *
         * @param direccion en que se haria la lectura(laser que medira la
         * distancia).
         * @return distancia en cubos hasta el obstaculo.
         */
        int calculaDistancia(int direccion) {
            int distancia = 0;
            boolean bandera = true;
            Celda aux = posicion;
            switch (direccion) {
                case 0:
                    while (bandera) {
                        if (aux.x > 0 && aux.y > 0 && mundo[aux.y - 1][aux.x - 1].tipo != Tipo.OBSTACULO) {
                            aux = mundo[aux.y - 1][aux.x - 1];
                            distancia++;
                            aux.distancia = distancia;
                        } else {
                            return distancia;
                        }
                    }
                case 1:
                    while (bandera) {
                        if (aux.x >= 0 && aux.y > 0 && mundo[aux.y - 1][aux.x].tipo != Tipo.OBSTACULO) {
                            aux = mundo[aux.y - 1][aux.x];
                            distancia++;
                            aux.distancia = distancia;
                        } else {
                            return distancia;
                        }
                    }
                case 2:
                    while (bandera) {
                        if (aux.x < columnas - 1 && aux.y > 0 && mundo[aux.y - 1][aux.x + 1].tipo != Tipo.OBSTACULO) {
                            aux = mundo[aux.y - 1][aux.x + 1];
                            distancia++;
                            aux.distancia = distancia;
                        } else {
                            return distancia;
                        }
                    }
                case 3:
                    while (bandera) {
                        if (aux.x < columnas - 1 && aux.y >= 0 && mundo[aux.y][aux.x + 1].tipo != Tipo.OBSTACULO) {
                            aux = mundo[aux.y][aux.x + 1];
                            distancia++;
                            aux.distancia = distancia;
                        } else {
                            return distancia;
                        }
                    }
                case 4:
                    while (bandera) {
                        if (aux.x > columnas - 1 && aux.y < renglones - 1 && mundo[aux.y + 1][aux.x + 1].tipo != Tipo.OBSTACULO) {
                            aux = mundo[aux.y + 1][aux.x + 1];
                            distancia++;
                            aux.distancia = distancia;
                        } else {
                            return distancia;
                        }
                    }

                case 5:
                    while (bandera) {
                        if (aux.x >= 0 && aux.y < renglones - 1 && mundo[aux.y + 1][aux.x].tipo != Tipo.OBSTACULO) {
                            aux = mundo[aux.y + 1][aux.x];
                            distancia++;
                            aux.distancia = distancia;
                        } else {
                            return distancia;
                        }
                    }
                case 6:
                    while (bandera) {
                        if (aux.x > 0 && aux.y < renglones - 1 && mundo[aux.y + 1][aux.x - 1].tipo != Tipo.OBSTACULO) {
                            aux = mundo[aux.y + 1][aux.x - 1];
                            distancia++;
                            aux.distancia = distancia;
                        } else {
                            return distancia;
                        }
                    }

                case 7:
                    while (bandera) {
                        if (aux.x > 0 && aux.y >= 0 && mundo[aux.y][aux.x - 1].tipo != Tipo.OBSTACULO) {
                            aux = mundo[aux.y][aux.x - 1];
                            distancia++;
                            aux.distancia = distancia;
                        } else {
                            return distancia;
                        }
                    }
            }
            return 0;
        }
    }

    /**
     * Clase modelo es empleada para darle toda la froma a nuestro mundo bidimendional 
     * donde este a su vez esta compuesto por celdas con una posicion fija dada en 
     * coordenadas
     */
    class Modelo {

        int columnas, renglones; //numero de renglones y columnas en todo el modelo
        Celda[][] mundo;
        Robot robot; //un robot que usaremos para "ponerlo" en nuestro mudno
        double probaMov = .5; //la probabilidad que se mueva nuestro robot
        int total = 0; //Para determinar el numero de casiilas que no son obtaculos
        Random rn = new Random();
        int c = 0;
        int r = 0;

        /**
         * Constructor del Mundo donde se encontrara el robot
         *
         * @param columnas del mapa
         * @param renglones del mapa         
         */
        Modelo(int columnas, int renglones) {
            this.columnas = columnas;
            this.renglones = renglones;

            this.mundo = new Celda[columnas][renglones];
            for (int i = 0; i < columnas; i++) { //creamos nuestro modelo 
                for (int j = 0; j < renglones; j++) {
                    mundo[i][j] = new Celda(j, i, 0);
                    total++; //incrementamos cada que hay una nueva celda 
                }
            }
            //Generamos nuestros obtaculos de manera aleatoria
            for (int i = 0; i <= columnas; i++) {
                c = rn.nextInt(columnas);
                r = rn.nextInt(columnas);
                if (mundo[r][c].tipo != Tipo.OBSTACULO) {
                    mundo[r][c].tipo = Tipo.OBSTACULO;
                    total--;
                }
            }
            Celda posicion=posicionaRobot(); //determinados una posicion para el robot
                                             //que hace una referencia a una celda
            this.robot = new Robot(posicion, mundo);
        }
        
        /**
         * Con base en sus vecinos que tenga libres moveremos el robot de forma aleatoria
         */
        void mueve() {
            LinkedList<Integer> posibles = robot.posibles();
            int mov = rn.nextInt(posibles.size());
            robot.mueve(posibles.get(mov));
        }
        
        /**
         * Incializamos nuestro modelo, dandole a todas las casiillas la creencia de 1/n
         * donde n son el total de casillas que no son obtaculos
         */
        void inicializa() {
            for (int i = 0; i <= columnas - 1; i++) {
                for (int j = 0; j <= renglones - 1; j++) {
                    if (mundo[i][j].tipo != Tipo.OBSTACULO) {
                        mundo[i][j].creencia = 1 / total;
                    }
                }
            }
        }

        /**
         * Metodo que se encarga de hacer la "aplicacion" del algoritmo
         */
        void algoritmo() {            
            if ((rn.nextInt(100) >= probaMov * 100)==false) {
                robot.odometro=0;
                for (int k = 0; k < 8; k++) {
                    for (int i = 0; i < columnas; i++) {
                        for (int j = 0; j < renglones; j++) {
                            mundo[i][j].creencia =rn.nextGaussian()*((1/total)-1);
                            robot.odometro=robot.odometro+1/total;
                        }                       
                    }
                }
                for (int k = 0; k < 8; k++) {
                    for (int i = 0; i < columnas; i++) {
                        for (int j = 0; j < renglones; j++) {
                            mundo[i][j].creencia =Math.pow(robot.odometro,-1)*(1/total);                            
                        }                       
                    }
                }
            }else{
                for (int i = 0; i < columnas; i++) {
                    for (int j = 0; j < renglones; j++) {
                        mundo[i][j].distancia = 0;
                    }
                }   
                mueve();
                robot.odometro++;
                robot.aplicaGaussiana();
                for (int i = 0; i < 8; i++) {
                    robot.calculaDistancia(i);
                }
                for (int i = 0; i < columnas; i++) {
                    for (int j = 0; j < renglones; j++) {
                        mundo[i][j].creencia = 1 / (mundo[i][j].distancia - (Math.abs(rn.nextGaussian()) * mundo[i][j].distancia));
                    }
                 }
            }
        }
        
        /**
        * Metodo auxiliar que usaremos para determinar 
        * la posicion de nuestro robot al inicio de la ejecucion
        */
        Celda posicionaRobot() {
            boolean bandera = true;
            while (bandera) {
                int x = rn.nextInt(columnas);
                int y = rn.nextInt(renglones);
                if (mundo[x][y].tipo != Tipo.OBSTACULO) {
                    mundo[x][y].conRobot = true;
                    return mundo[x][y];
                }
            }
            return mundo[0][0];
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"proyecto.Proyecto"});
    }

}
