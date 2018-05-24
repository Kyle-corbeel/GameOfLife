/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;


/**
 *
 * @author Dieter Nuytemans en Kyle Corbeel
 */
public class SimulatieBestuur{
    
    //Simulatieveld, wordt doorgegeven vanuit een 'bovenliggend' object
    Veld veld;
    
    //De spelregels
    //Hoeveel levende buren er min/max nodig zijn om levend te blijven
    private int minBlijfLevend;
    private int maxBlijfLevend;
    //Het bereik van aantal levende buren dat nodig is om een dood veld levend te maken
    private int minWordtLevend;
    private int maxWordtLevend;
    
    //De simulatiethread
    Thread simThread;
    SimulatieThread simulatie;
    
    /**
     * Constructor van SimulatieBestuur
     * @param veld Het huidige veld dat gesimuleerd zal worden
     */
    SimulatieBestuur(Veld veld)
    {
        //Veld doorgeven
        this.veld = veld;
        
        //Standaardinstellingen initialiseren (Zoals in Conway's Game Of Life)
        //Bij twee of drie levende buren, blijft een cel levend
        //Anders sterft de cel
        this.minBlijfLevend = 2;
        this.maxBlijfLevend = 3;
        //Door omgeven te worden door precies drie levende buurcellen wordt een dode cel levend
        this.minWordtLevend = 3;
        this.maxWordtLevend = 3;
    }
    
    /**
     * Doe een bepaald aantal stappen
     * @param aantal stappen dat overlopen zal worden
     * @throws java.lang.Exception
     */
    public void stap(int aantal) throws Exception
    {
        
        if (simulatie != null)
        {
            simulatie.stop();
            //Wachten op de vorige simulatie, mocht er nog één bezig zijn
            /*
            try {
                simThread.join();
            } catch (Exception e)
            {
                System.out.println(e);
            }*/
        } else {
        
            for (int i = 0; i < aantal; i++)
            {
                //Maak nieuwe simulatie en start
                simulatie = new SimulatieThread(veld, minBlijfLevend, maxBlijfLevend, minWordtLevend, maxWordtLevend, -1, 1);
                simThread = new Thread(simulatie);
                simThread.start();
                //Stop de simulatie zodat er slechts één stap doorlopen wordt
                //stop();

                //Wacht tot simulatie klaar is
                /*
                try {
                    simThread.join();
                } catch(Exception e)
                {
                    System.out.println(e);
                }*/
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
        //Wachten op de vorige simulatie, mocht er nog één bezig zijn
        try {
            simThread.join();
        } catch (Exception e)
        {
            System.out.println("Play() in orde, er runt geen andere simulatie (exception: " + e + ")");
        }
        
        //Simulatiethread aanmaken
        simThread = new Thread(simulatie = new SimulatieThread(veld, minBlijfLevend, maxBlijfLevend, minWordtLevend, maxWordtLevend, snelheid));
        simThread.start();
    }
    
    /**
     * Stop de simulatie
     */
    public void stop()
    {
        if (simulatie != null)
            simulatie.stop();
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
    
    /**
     * Plaats een nieuw veld in simulatieBestuur
     * @param veld veld dat nu gesimuleerd zal worden
     */
    public void setVeld(Veld veld)
    {
        this.veld = veld;
    }
}
