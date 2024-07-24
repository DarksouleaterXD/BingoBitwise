/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author brand
 */
public class Cartilla {
    
    VectorNbits N;
    VectorNbits numeroDeCartilla;
    int NumeroMaximo;
    VectorNbits numerosMarcados;
    int[] numerosDesordenados;

    public Cartilla(int numMax, int numeroCartilla){//rango, numeroOrdinal
        numeroDeCartilla = new VectorNbits(1, 32);
        numeroDeCartilla.insertar(numeroCartilla, 1);
        N = new VectorNbits(numMax, 1);
        this.NumeroMaximo= numMax;
        this.numerosMarcados = new VectorNbits(numMax, 1);
        this.numerosDesordenados = new int[numMax];
    }

    public void CrearCartilla( int cantidadNumeros, Random random){//CantidadDeNumeros
        for (int i = 0; i < cantidadNumeros; i++) {
            int numero;
            do {
                numero = random.nextInt(NumeroMaximo) + 1;
            } while (N.sacar(numero) == 1);
                N.insertar(1, numero);
        }
        desordenarCartilla(random);

    }
    public int getNumeroDeCartilla(){
        return numeroDeCartilla.sacar(1);
    }

    public boolean esIgual(Cartilla otra) {
        if (this.NumeroMaximo != otra.NumeroMaximo) return false;
        for (int i = 1; i <= NumeroMaximo; i++) {
            if (this.N.sacar(i) != otra.N.sacar(i)) {
                return false;
            }
        }
        return true;
    }
    
    public void marcarNumero(int numero) {
        if (N.sacar(numero) == 1) {
            numerosMarcados.insertar(1, numero);
        }
    }

    public boolean estaTodaMarcada() {
        for (int i = 1; i <= NumeroMaximo; i++) {
            if (N.sacar(i) == 1 && numerosMarcados.sacar(i) == 0) {
                return false;
            }
        }
        return true;
    }
    
    public void desordenarCartilla(Random random) {
        int[] numeros = new int[NumeroMaximo];
        int index = 0;

        for (int i = 1; i <= NumeroMaximo; i++) {
            if (N.sacar(i) == 1) {
                numeros[index++] = i;
            }
        }

        for (int i = index - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numeros[i];
            numeros[i] = numeros[j];
            numeros[j] = temp;
        }

        numerosDesordenados = new int[index];
        System.arraycopy(numeros, 0, numerosDesordenados, 0, index);

        N = new VectorNbits(NumeroMaximo, 1);
        for (int i = 0; i < index; i++) {
            N.insertar(1, numeros[i]);
        }
    }

    public String imprimirCartillaOrdenada() {
        StringBuilder sb = new StringBuilder("Cartilla #" + getNumeroDeCartilla() + " [N=");
        for (int i = 1; i <= NumeroMaximo; i++) {
            if (N.sacar(i) == 1) {
                sb.append(i).append(",");
            }
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    public String imprimirCartillaDesordenada() {
        if (numerosDesordenados == null || numerosDesordenados.length == 0) {
            return "Cartilla vacía.";
        }

        StringBuilder sb = new StringBuilder("Cartilla #" + getNumeroDeCartilla() + " [N=");
        for (int i = 0; i < numerosDesordenados.length; i++) {
            sb.append(numerosDesordenados[i]).append(",");
        }
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    }
    


