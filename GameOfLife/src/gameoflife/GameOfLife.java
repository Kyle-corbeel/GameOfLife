/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.io.IOException;

/**
 *
 * @author kyle-
 */
public class GameOfLife {
    

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException, Exception {
        try{
            Veldbeheer veldbeheer1 = new Veldbeheer();
            Bestandbeheer bestandH1 = new Bestandbeheer();
            Veld veld1 = veldbeheer1.maakVeld(10, 10);
            veldbeheer1.vulVeldRandom(veld1, 50);
            SimulatieBestuur simBestuur = new SimulatieBestuur(veld1);
            
            simBestuur.play(-1);
            //simBestuur.stop();
            veld1.printVeld();
            
            

            //bestandH1.saveVeld(veld1, "output.txt");
            //veld1 = bestandH1.laadVeld("output.txt");
            //veld1.printVeld();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
            
    }
    
}
