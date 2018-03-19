/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author kyle-
 */
public class GameOfLife {
    

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws Exception {
        try{
            Veldbeheer veldbeheer1 = new Veldbeheer();
            Bestandbeheer bestandH1 = new Bestandbeheer();
            Veld veld1 = veldbeheer1.maakVeld(10, 10);
            veldbeheer1.vulVeldRandom(veld1, 50);
            SimulatieBestuur simulatieBestuur = new SimulatieBestuur(veld1);
            Thread simBestuur = new Thread(simulatieBestuur);
            simBestuur.start();
            veld1.printVeld();
            
            simulatieBestuur.play(1);

            //bestandH1.saveVeld(veld1, "output.txt");
            //veld1 = bestandH1.laadVeld("output.txt");
            //veld1.printVeld();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
            
    }
    
}
