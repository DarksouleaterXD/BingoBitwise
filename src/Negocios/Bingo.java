/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocios;

import java.util.Random;
import java.util.BitSet;

/**
 *
 * @author brand
 */
public class Bingo {
   int CantidadCartillas;
    Cartilla[] cartillas;
    public BitSet cartillasCreadas;
    public Random random;
    public VectorNbits numerosSorteados;
    int NumeroMaximo ;

    public Bingo(int cantidadCartillas, int numMax){

        this.CantidadCartillas = cantidadCartillas;
        cartillas = new Cartilla[cantidadCartillas];
        this.cartillasCreadas = new BitSet();
        this.random = new Random();
        this.numerosSorteados = new VectorNbits(numMax,1);
        this.NumeroMaximo = numMax;
    }

    public void CrearBingo(int numMax, int CantidadNumeros) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < CantidadCartillas; i++) {
            Cartilla nuevaCartilla;
            int hash;
            do {
                nuevaCartilla = new Cartilla(numMax, i + 1);
                nuevaCartilla.CrearCartilla(CantidadNumeros, random);

                hash = nuevaCartilla.hashCode();
            } while (cartillasCreadas.get(hash));
            cartillas[i] = nuevaCartilla;
            cartillasCreadas.set(hash);
            if ((i + 1) % 100000 == 0) {
                System.out.println("Creadas " + (i + 1) + " cartillas...");
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo total: " + (endTime - startTime) / 1000.0 + " segundos");

    }
    public int CantDeCartillas()
    {
        return CantidadCartillas;
    }
    public int jugar() {
        int numeroSorteado;
        while ((numeroSorteado = sortearNumero(NumeroMaximo)) != -1) {
            System.out.println("Número sorteado: " + numeroSorteado);
            for (int i = 0; i < CantidadCartillas; i++) {
                cartillas[i].marcarNumero(numeroSorteado);
                if (cartillas[i].estaTodaMarcada()) {
                    System.out.println("¡Bingo! La cartilla #" + (i + 1) + " ha ganado.");
                    return i + 1; 
                }
            }
        }
        System.out.println("El juego ha terminado sin ganadores.");
        return -1;
    }
    
    public int sortearUnNumero() {
        int numeroSorteado = sortearNumero(NumeroMaximo);
        if (numeroSorteado == -1) {
            return -1; 
        }

        System.out.println("Número sorteado: " + numeroSorteado);

        for (int i = 0; i < CantidadCartillas; i++) {
            cartillas[i].marcarNumero(numeroSorteado);
            if (cartillas[i].estaTodaMarcada()) {
                System.out.println("¡Bingo! La cartilla #" + (i + 1) + " ha ganado.");
                return i + 1; 
            }
        }

        return 0; 
    }

    public int sortearNumero(int rangoMaximo) {
        if (contarNumerosSorteados() == rangoMaximo) {
            return -1; 
        }

        int numero;
        do {
            numero = random.nextInt(rangoMaximo) + 1;
        } while (numerosSorteados.sacar(numero) == 1);

        numerosSorteados.insertar(1, numero);
        return numero;
    }

    private int contarNumerosSorteados() {
        int count = 0;
        for (int i = 1; i <= numerosSorteados.cant; i++) {
            if (numerosSorteados.sacar(i) == 1) {
                count++;
            }
        }
        return count;
    }
    
    public String imprimirCartilla(int numeroCartilla) {
        if (numeroCartilla > 0 && numeroCartilla <= CantidadCartillas) {
            return cartillas[numeroCartilla - 1].imprimirCartillaDesordenada();
        } else {
            return "Número de cartilla inválido.";
        }
    }
    
}
    

