/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author Kyle Corbeel en Dieter Nuytemans
 */
public class SimulatieBestuur {
    
    //Simulatieveld, wordt doorgegeven vanuit een 'bovenliggend' object
    Veld veld;
    
    //De spelregels
    //Hoeveel levende buren er min/max nodig zijn om levend te blijven
    private int minBlijfLevend;
    private int maxBlijfLevend;
    //Het bereik van aantal levende buren dat nodig is om een dood veld levend te maken
    private int minWordtLevend;
    private int maxWordtLevend;
    
    //
    private boolean play = false;
    
    /**
     * Constructor van SimulatieBestuur
     * @param veld Het huidige veld dat gesimuleerd zal worden
     */
    public void SimulatieBestuur(Veld veld)
    {
        //Veld doorgeven
        this.veld = veld;
        
        //Standaardinstellingen initialiseren (Zoals in Conway's Game Of Life)
        minBlijfLevend = 2;
        maxBlijfLevend = 3;
        minWordtLevend = 3;
        maxWordtLevend = 3;
    }
    
    /**
     * Doe een bepaald aantal stappen
     * @param aantal stappen dat overlopen zal worden
     */
    public void stap(int aantal)
    {
        
    }
    
    /**
     * Blijf lopen tot er op stop gedrukt wordt
     * @param snelheid (stappen per seconde)
     */
    public void play(int snelheid)
    {
        //Thread aanmaken
        Thread simulatie = new Thread(new SimulatieThread(veld, minBlijfLevend, maxBlijfLevend, minWordtLevend, maxWordtLevend));
        //Thread starten
        simulatie.start();
        //Zolang play aanstaat, blijft de simulatie lopen aan een bepaalde snelheid
        while(play) {
            
        }
    }
    
    /**
     * Stel de grenzen in van Game Of Life, hiermee veranderen de spelregels
     * @param minBlijfL minumum # buren dat een cel nodig heeft om levend te blijven
     * @param maxBlijfL maximum # buren dat een cel mag hebben om levend te blijven
     * @param minWordtL minumum # buren dat een dode cel nodig heeft om levend te worden
     * @param maxWordtL maximum #buren dat een dode cel mag hebben om levend te worden
     */
    public boolean setGrenzen(int minBlijfL, int maxBlijfL, int minWordtL, int maxWordtL)
    {
        if (minBlijfL <= maxBlijfL && minBlijfL >= 0 && maxBlijfL <= 8)
        {            
            if (minWordtL <= maxWordtL && minWordtL >= 0 && maxWordtL <= 8)
            {
                this.minBlijfLevend = minBlijfL;
                this.maxBlijfLevend = maxBlijfL;
                
                this.minWordtLevend = minWordtL;
                this.maxWordtLevend = maxWordtL;
                
                return true;
            }
        }
        return false;
    }
}
