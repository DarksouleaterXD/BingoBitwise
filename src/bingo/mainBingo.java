/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bingo;

import Negocios.Bingo;
import Negocios.Cartilla;

/**
 *
 * @author Usuario
 */
public class mainBingo {
    public static void main(String[]args){
       
        int cantidadCartillas = 100000;  
        int numeroMaximo = 50;            //rango de numeros que habra en el bingo
        int numerosPerCartilla = 15;      //numeroPerCartilla

        System.out.println("Iniciando creación de Bingo...");
        Bingo bingo = new Bingo(cantidadCartillas, numeroMaximo);
        bingo.CrearBingo(numeroMaximo, numerosPerCartilla);
        
        System.out.println("Bingo creado. Iniciando juego...");
        int cartillaGanadora = bingo.jugar();
        
        if (cartillaGanadora != -1) {
            System.out.println("La cartilla ganadora es la #" + cartillaGanadora);
            System.out.println(bingo.imprimirCartilla(cartillaGanadora));
        } else {
            System.out.println("El juego terminó sin ganadores.");
        }
        System.out.println(cartillaGanadora);
        
    }
       
        
    }

