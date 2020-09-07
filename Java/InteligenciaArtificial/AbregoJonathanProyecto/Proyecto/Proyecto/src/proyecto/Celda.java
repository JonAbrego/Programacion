/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author Jonathan
 */
public class Celda {

    int x;
    int y; 
    boolean conRobot; //Variable que nos dice si la celda tiene al robot.
    double creencia; //Variable que guarda la probabilidad de que se este en esta celda(creencia).
    double distancia; //Variable que guarda la distancia al robot.
    Tipo tipo = Tipo.VACIO;

    /**
     * Constructor de una celda
     *
     * @param celdaX Coordenada en x
     * @param celdaY Coordenada en y
     */
    Celda(int celdaX, int celdaY, double creencia) {
        this.x = celdaX;
        this.y = celdaY;
        this.conRobot = false;
        this.creencia = creencia;
        this.distancia = 0;
    }

}
