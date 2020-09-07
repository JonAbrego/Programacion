/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax.ia;

import static java.lang.System.out;
import java.util.LinkedList;

/**
 *
 * @author jon
 */
public class Minimax {
    
    public Minimax() {

    }

    static class Gato {

        int[][] tablero = new int[3][3];     // Tablero del juego
        Gato padre;                          // Quién generó este estado.
        LinkedList<Gato> sucesores;          // Posibles jugadas desde este estado.
        boolean jugador1 = true;            // Jugador que tiró en este tablero.
        boolean hayGanador = false;          // Indica si la última tirada produjo un ganador.
        int tiradas = 0;                     // Número de casillas ocupadas.
        static int MARCA1 = 1;
        static int MARCA2 = 4;    

        /**
         * Constructor del estado inicial.
         */
        public Gato() {
        }

        /**
         * Constructor que copia el tablero de otro gato y el número de tiradas
         */
        public Gato(Gato g) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tablero[i][j] = g.tablero[i][j];
                }
            }
            tiradas = g.tiradas;
        }

        /**
         * Indica si este estado tiene sucesores expandidos.
         */
        public int getNumHijos() {
            if (sucesores != null) {
                return sucesores.size();
            } else {
                return 0;
            }
        }

        /* Función auxiliar.
         * Dada la última posición en la que se tiró y la marca del jugador
         * calcula si esta jugada produjo un ganador y actualiza el atributo correspondiente.
         * 
         * Esta función debe ser lo más eficiente posible para que la generación del árbol no sea demasiado lenta.
         */
        public boolean hayGanador(int x, int y, int marca) {
            // Horizontal
            if (tablero[y][(x + 1) % 3] == marca && tablero[y][(x + 2) % 3] == marca) {
                hayGanador = true;
                return hayGanador;
            }
            // Vertical
            if (tablero[(y + 1) % 3][x] == marca && tablero[(y + 2) % 3][x] == marca) {
                hayGanador = true;
                return hayGanador;
            }
            // Diagonal
            if ((x == 1 && y != 1) || (y == 1 && x != 1)) {
                return hayGanador; // No pueden hacer diagonal
            }            // Centro y esquinas
            if (x == 1 && y == 1) {
                // Diagonal \
                if (tablero[0][0] == marca && tablero[2][2] == marca) {
                    hayGanador = true;
                    return hayGanador;
                }
                if (tablero[2][0] == marca && tablero[0][2] == marca) {
                    hayGanador = true;
                    return hayGanador;
                }
            } else if (x == y) {
                // Diagonal \
                if (tablero[(y + 1) % 3][(x + 1) % 3] == marca && tablero[(y + 2) % 3][(x + 2) % 3] == marca) {
                    hayGanador = true;
                    return hayGanador;
                }
            } else // Diagonal /
            {
                if (tablero[(y + 2) % 3][(x + 1) % 3] == marca && tablero[(y + 1) % 3][(x + 2) % 3] == marca) {
                    hayGanador = true;
                    return hayGanador;
                }
            }
            return hayGanador;
        }

        /* Función auxiliar.
         * Coloca la marca del jugador en turno para este estado en las coordenadas indicadas.
         * Asume que la casilla está libre.
         * Coloca la marca correspondiente, verifica y asigna la variable si hay un ganador.
         */
        public void tiraEn(int x, int y) {
            tiradas++;
            int marca = (jugador1) ? MARCA1 : MARCA2;
            tablero[y][x] = marca;
            hayGanador(x, y, marca);
        }

        /**
         * Crea la lista sucesores y agrega a todos los estados que surjen de
         * tiradas válidas. Se consideran tiradas válidas a aquellas en una
         * casilla libre. Además, se optimiza el proceso no agregando estados
         * con jugadas simétricas. Los estados nuevos tendrán una tirada más y
         * el jugador en turno será el jugador contrario.
         */
        public LinkedList<Gato> generaSucesores() {
            if (hayGanador) {
                return null;
            }
            sucesores = new LinkedList<Gato>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero[i][j] == 0) { //verificamos que la entrada i,j no tenga ya un X o O
                        Gato nuevo = new Gato(this);
                        nuevo.jugador1 = !jugador1;
                        nuevo.padre = this;
                        nuevo.tiraEn(j, i);
                        recorreListaGatos(sucesores, nuevo);
                        if (recorreListaGatos(sucesores, nuevo)) {
                            sucesores.add(nuevo);
                        }
                    }
                }
            }
            this.sucesores = sucesores;
            return sucesores;
        }

        public boolean recorreListaGatos(LinkedList<Gato> gatos, Gato verificar) {
            for (Gato g : gatos) {
                if (verificar.equals(g)) {
                    return false;
                }
            }
            return true;
        }
        
        @Override
        public String toString(){            
            String gs = "";
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    gs += ((tablero[i][j] == MARCA2) ? 'x' : (tablero[i][j] == MARCA1) ? 'o' : "_") + " ";
                }
                gs += '\n';
            }
            return gs;
        }
        
        /**
         * Determina si el estado que le pasamos requerira un minimo o maximo al numero
         * de tiradas que hay en el tablero puesto que si lo vemos como un arbol
         *       Nivel 0:Max      Gato vacio                      
         *       Nivel 1:Min      Todos los gatos con 1 tirada 
         *       Nivel 2:Max      Todos los gatos con 2 tiradas
         *            :
         *            :
         *       Nivel 8:Max      Todos lo gatos con 8 tiras
         * Entonces si hay un numero par de tiradas nuestro estado necesitara un max
         * y caso analogo si hay un numero impar de tiradas nuestro estada necesitara un min
         * @param estado El gato que pasamos con X numero de tiradas         
         * @return booleano si el gato esta en un nivel max
         */
        public boolean esMaximo(Gato estado){            
            if(devuelveNumTiradas(estado)%2==0)
                return true;
            return false;
        }                
        
        /**
         * Puesto que le pasamos un estado ya definido el numero de 
         * tiradas no se actualiza a la cantidad de tiradas que hay en 
         * el tablero que le vayamos a pasar
         * @param estado
         * @return 
         */
        public int devuelveNumTiradas(Gato estado){            
            int valor=0;
            for(int i=0;i<3 ;i++)
                for(int j=0;j<3;j++){                      
                    if(estado.tablero[i][j]==1 || estado.tablero[i][j]==4){                        
                        valor++;
                    } 
                }                                                    
            return valor;
        }
        
        /**
         * Con base al gato que le pasemos elegiremos la opcion que mas le convenga 
         * cabe resaltar que el jugador 1 siempre tirara con X mientras que el jugador 
         * 2 jugara con O, por lo que si hay un numero de tiradas par le tocaria 
         * al jugador con X (jugaror1) mientras que si hay un numero impar de tiradas 
         * tira el jugaro con O (jugador2)
         * @param estado
         * @return gato con la posible accion a tomar 
         */
        public Gato seleccionaMejor(Gato estado){                        
            if(estado.devuelveNumTiradas(estado)==9 || estado.hayGanador)
                return estado;                                    
            int auxi=0;
            int auxj=0;
            int tirada=0;
            if(esMaximo(estado)){                
                for(int j=0;j<3 ;j++){
                    for(int i=0;i<3;i++){
                        if(estado.tablero[j][i] == 0 && estado.hayGanador(j, i, MARCA2)==true){                                                         
                            estado.tablero[j][i]=4;                                
                            return estado;                            
                        }else{
                            tirada=4;
                            auxi=i;
                            auxj=j;
                        }
                    }
                }             
            }else{                                
                for(int j=0;j<3 ;j++){
                    for(int i=0;i<3;i++){                            
                        if(estado.tablero[j][i] == 0 && estado.hayGanador(j, i, MARCA2)==true){                                                         
                            estado.tablero[j][i]=1;                                
                            return estado;                            
                        }else{
                            tirada=1;
                            auxi=i;
                            auxj=j;
                        }
                    }
                }                                
            }                
            estado.tablero[auxj][auxi]=tirada;
            return estado;
        }
        
        public void estado() {                        
            //Segun recuerdo siempre tira primero X o ya ni se
            System.out.println("Estado1: ");
            Gato g = new Gato();            
            int[][] estado1 = {{0,1,4},{4,1,1},{0,0,4}};                          
            g.tablero=estado1;                           
            out.println(g.toString());            
            System.out.println("Accion: ");
            out.println(g.seleccionaMejor(g));
            
            System.out.println("Estado2: ");
            Gato g2 = new Gato();            
            int[][] estado2 = {{0,0,4},{0,0,1},{4,1,4}};                          
            g2.tablero=estado2;                           
            out.println(g2.toString());            
            System.out.println("Accion: ");
            out.println(g2.seleccionaMejor(g2));
        
            System.out.println("Estado3: ");
            Gato g3 = new Gato();                        
            int[][] estado3 = {{1,1,4},{0,0,4},{1,4,0}};                          
            g3.tablero=estado3;                           
            out.println(g3.toString());            
            System.out.println("Accion: ");
            out.println(g3.seleccionaMejor(g3));                        
        }
    }                
                              
    public static void main(String[] args) {
        Gato gato = new Gato();                        
        gato.estado();
    }    
}
