/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dieter Nuytemans en Kyle Corbeel
 */
public class SimulatieThread implements Runnable{
    
    //Speelt of niet
    private boolean play = true;
    
    //Wachttijd tussen simulaties
    private long wachttijd;
    
    //Simulatieveld
    private Veld veld;
    
    //De spelregels
    //Hoeveel levende buren er min/max nodig zijn om levend te blijven
    private int minBlijfLevend;
    private int maxBlijfLevend;
    //Het bereik van aantal levende buren dat nodig is om een dood veld levend te maken
    private int minWordtLevend;
    private int maxWordtLevend;
    
    /**
     * Constructor van de simulatie
     * @param veld Het huidig simulatieveld
     * Regels van Game Of Life (of eigen custom regels)
     * @param minBlijfL 
     * @param maxBlijfL
     * @param minWordtL
     * @param maxWordtL
     */
    SimulatieThread(Veld veld, int minBlijfL, int maxBlijfL, int minWordtL, int maxWordtL, int snelheid) {
        
        this.veld = veld;
        
        long correctieSnelheid;
        
        if (snelheid == -1) 
            correctieSnelheid = 1000;
        else if (snelheid < 1)
            correctieSnelheid = 1;
        else
            correctieSnelheid = snelheid;
        
        //Wachttijd berekenen
        this.wachttijd = 1000 / correctieSnelheid; //in ms
        
        //Instellingen doorgeven
        this.minBlijfLevend = minBlijfL;
        this.maxBlijfLevend = maxBlijfL;
        this.minWordtLevend = minWordtL;
        this.maxWordtLevend = maxWordtL;
    }
    
    /**
     * Voer één simulatiestap uit
     */
    public void stap()
    {
        int numBuren;
        Veld tempveld = null;
        
        this.play = true;
        
        try {
            tempveld = this.veld.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(SimulatieThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (tempveld != null)
        {
            for (int i = 0; i < tempveld.getHoogte(); i++) {

                for (int j = 0; j < tempveld.getBreedte(); j++) {

                    numBuren = veld.aantalBuren(i, j);
                    System.out.print(numBuren + " ");
                    
                    //Als het veld levend is
                    if (veld.getCelStatus(i,j)) {

                        //Checken of er voldaan is aan de regels, toggle cel indien nodig
                        if (numBuren < minBlijfLevend || numBuren > maxBlijfLevend)
                            tempveld.toggleCel(i, j);

                    } else { //Als het veld niet levend is

                        //Checken of er voldaan is aan de regels, toggle cel indien nodig
                        if (numBuren >= minWordtLevend && numBuren <= maxWordtLevend)
                            tempveld.toggleCel(i, j);

                    }
                }
                System.out.println("");
            }
            //Tempveld opslaan naar het object veld
            veld = tempveld;
        }
    }
    
    /**
     * Stop simulatie zodra de huidige afgerond is
     */
    public void stop()
    {
        this.play = false;
    }
    
    @Override
    public void run() {       
        //Zolang play aanstaat, blijft de simulatie lopen aan een bepaalde snelheid
        while(this.play) {
            //Voer een simulatiestap uit
            stap();
            
            //Wachten zodat de speelsnelheid klopt
            try {
                Thread.sleep(wachttijd);
            } catch (InterruptedException ex) {
                Logger.getLogger(SimulatieThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Print het veld uit(enkel voor testfase)
            veld.printVeld();
        }
    }
}