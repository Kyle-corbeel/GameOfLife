/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author kyle-
 */
public class GameOfLife {
    

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try{
            Veldbeheer veldbeheer1 = new Veldbeheer();
            Bestandbeheer bestandH1 = new Bestandbeheer();
            
            Veld veld1 = veldbeheer1.maakVeld(5, 5);
            veld1.printVeld();
            veldbeheer1.vulVeldRandom(veld1, 50);
            veld1.printVeld();
            SimulatieBestuur simulatieBestuur = new SimulatieBestuur(veld1);
            simulatieBestuur.stap(1);
            veld1.printVeld();
            Boolean[][] veldMatrix = bestandH1.laadVeld("bestandsNaam");
            veld1 = veldbeheer1.maakVeldFile(veldMatrix);
            veld1.printVeld();
            
            veld1 = veldbeheer1.maakVeld(255, 255);
            bestandH1.saveVeld(veld1);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
            
    }
    
}
