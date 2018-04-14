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
            //Glider aanmaken om werking te testen
            veld1.toggleCel(0, 1);
            veld1.toggleCel(1, 2);
            veld1.toggleCel(2, 0);
            veld1.toggleCel(2, 1);
            veld1.toggleCel(2, 2);
            
            //Lijn van drie eenheden aanmaken om de werking te testen
            veld1.toggleCel(5, 6);
            veld1.toggleCel(5, 7);
            veld1.toggleCel(5, 8);
            
            veld1.printVeld();
            SimulatieBestuur simBestuur = new SimulatieBestuur(veld1);
            
            /* Test of aantalBuren werkt 
            System.out.println("0,2 : " + veld1.aantalBuren(0, 2) + " - " + veld1.getCelStatus(0, 2));
            System.out.println("1,0 : " + veld1.aantalBuren(1, 0) + " - " + veld1.getCelStatus(1, 0));
            System.out.println("1,1 : " + veld1.aantalBuren(1, 1) + " - " + veld1.getCelStatus(1, 1));
            */            

            simBestuur.play(1);
            
            //bestandH1.saveVeld(veld1, "output.txt");
            //veld1 = bestandH1.laadVeld("output.txt");
            //veld1.printVeld();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
            
    }
    
}
