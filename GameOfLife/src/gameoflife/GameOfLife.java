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
            Veld veld1 = new Veld(10,10);
            veld1.printVeld();
            SimulatieBestuur simulatieBestuur = new SimulatieBestuur(veld1);
            simulatieBestuur.stap(1);
            veld1.printVeld();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
            
    }
    
}
