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
public class SimulatieBestuur implements Runnable{
    
    //Simulatieveld, wordt doorgegeven vanuit een 'bovenliggend' object
    Veld veld;
    
    //De spelregels
    //Hoeveel levende buren er min/max nodig zijn om levend te blijven
    private int minBlijfLevend;
    private int maxBlijfLevend;
    //Het bereik van aantal levende buren dat nodig is om een dood veld levend te maken
    private int minWordtLevend;
    private int maxWordtLevend;
    
    //Variabele die aangeeft of er gesimuleerd wordt aan +/- vaste snelheid
    private boolean play;
    
    /**
     * Constructor van SimulatieBestuur
     * @param veld Het huidige veld dat gesimuleerd zal worden
     */
    SimulatieBestuur(Veld veld)
    {
        //Veld doorgeven
        this.veld = veld;
        
        //Standaardinstellingen initialiseren (Zoals in Conway's Game Of Life)
        this.minBlijfLevend = 2;
        this.maxBlijfLevend = 3;
        this.minWordtLevend = 3;
        this.maxWordtLevend = 3;
        
        //Standaard speelt de simulatie niet
        this.play = false;
    }
    
    /**
     * Doe een bepaald aantal stappen
     * @param aantal stappen dat overlopen zal worden
     * @throws java.lang.Exception
     */
    public void stap(int aantal) throws Exception
    {
        for (int i = 0; i < aantal; i++)
        {
            //Maak nieuwe simulatie en start
            Thread simulatie = new Thread(new SimulatieThread(veld, minBlijfLevend, maxBlijfLevend, minWordtLevend, maxWordtLevend));
            simulatie.start();
            
            //Wacht tot simulatie klaar is
            try {
                simulatie.join();
            } catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    
    /**
     * Blijf lopen tot er op stop gedrukt wordt
     * @param snelheid (stappen per seconde)
     * @throws java.lang.InterruptedException
     */
    public void play(int snelheid) throws InterruptedException
    {
        //Play wordt aangezet
        this.play = true;
        
        //Negatieve argumenten vermijden
        if (snelheid <= 0)
            snelheid = 1;
        
        //Wachttijd voordat een thread start berekenen
        long wachttijd = 1000 / snelheid; //in ms
        
        //Simulatiethread aanmaken
        Thread simulatie = new Thread(new SimulatieThread(veld, minBlijfLevend, maxBlijfLevend, minWordtLevend, maxWordtLevend));
        simulatie.start();
        
        //Zolang play aanstaat, blijft de simulatie lopen aan een bepaalde snelheid
        while(this.play) {
            
            //Wacht tot de vorige simulatie klaar is
            try {
                simulatie.join();
            } catch(Exception e)
            {
                System.out.println(e);
            }
            
            //Nieuwe simulatie starten als play aanstaat
            if (this.play) {
                //Simulatiethread aanmaken
                simulatie = new Thread(new SimulatieThread(veld, minBlijfLevend, maxBlijfLevend, minWordtLevend, maxWordtLevend));

                //Thread starten en even wachten om de gewenste snelheid te benaderen
                try {
                    veld.printVeld();
                    simulatie.start();
                    simulatie.sleep(wachttijd);
                } catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        }
    }
    
    /**
     * Methode om de simulatie te stoppen
     */
    public void stop() {
        this.play = false;
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

    @Override
    public void run() {
        //Veld doorgeven
        this.veld = veld;
        
        //Standaardinstellingen initialiseren (Zoals in Conway's Game Of Life)
        this.minBlijfLevend = 2;
        this.maxBlijfLevend = 3;
        this.minWordtLevend = 3;
        this.maxWordtLevend = 3;
        
        //Standaard speelt de simulatie niet
        this.play = false;
        
    }
}
