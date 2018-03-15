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
public class SimulatieThread implements Runnable{
    
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
    public void SimulatieThread(Veld veld, int minBlijfL, int maxBlijfL, int minWordtL, int maxWordtL)
    {
        //Initialiseer het veld en stel huidige regels in
        this.veld = veld;
        
        this.minBlijfLevend = minBlijfL;
        this.maxBlijfLevend = maxBlijfL;
        this.minWordtLevend = minWordtL;
        this.maxWordtLevend = maxWordtL;
    }

    @Override
    public void run() {
        int numBuren;
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                numBuren = veld.aantalBuren(i, j);
                //Als het veld levend is
                if (veld.getVeldStatus(i,j)) {
                    if (numBuren < minBlijfLevend || numBuren > maxBlijfLevend)
                        veld.toggleCel(i, j);
                } else { //Als het veld niet levend is
                    if (numBuren >= minWordtLevend && numBuren <= maxWordtLevend)
                        veld.toggleCel(i, j);
                }
            }
        }
    }
}
