/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.Random;

/**
 *
 * @author kyle-
 */
public class Veldbeheer {
    
    private int i;
    private int j;
    
    public Veldbeheer()
    {
        
    }
    
    /**
     * Deze functie zal een nieuw veld aanmaken volgens gewenste afmetingen
     * @param hoogte de gewenste hoogte van het veld
     * @param breedte de gewenste breedte van het veld
     * @return het gecreeërde veld
     */
    
    public Veld maakVeld(int hoogte, int breedte)
    {
        try{
            Veld veld1 = new Veld(hoogte,breedte);
            return(veld1);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return(null);
        }
        
    }
    
    /**
     * Deze method zal alle cellen van het veld terug op dood zetten
     * @param veld het te resetten veld
     * @return het veld waarbij alle cellen nu dood zijn
     */
    
    public Veld resetVeld(Veld veld)
    {
        for(i=0;i<veld.getHoogte();i++)
        {
            for (j=0;j<veld.getBreedte();j++)
            {
                if(veld.getCelStatus(i, j))
                {
                    veld.toggleCel(i, j);
                }
            }
        }
        return(veld);  
    }
    
    public Veld vulVeldRandom (Veld veld, int kans)
    {
        Random rand = new Random();
        veld = this.resetVeld(veld);
        if (kans > 0)
        {
            for(i=0;i<veld.getHoogte();i++)
            {
                for (j=0;j<veld.getBreedte();j++)
                {
                    if(rand.nextInt(100)<=kans)
                    {
                        veld.toggleCel(i, j);
                    }
                }
            }
        }
        return(veld);
    }
    
    /**
     * Deze methode maakt een nieuw veld aan volgens een opgegeven matrix
     * @param veldMatrix Deze matrix bepaalt welke cellen er levend zullen zijn
     * @return Er zal een nieuw veld gecreeërd zijn
     */
    
    public Veld maakVeldFile(Boolean[][] veldMatrix)
    {
        int hoogte = veldMatrix.length;
        int breedte = veldMatrix[0].length;
        Veld veld = this.maakVeld(hoogte, breedte);
        for(i=0;i<hoogte;i++)
        {
            for(j=0;j<breedte;j++)
            {
                if(veldMatrix[i][j])
                {
                    veld.toggleCel(i, j);
                }
            }
        }
        return(veld);
    }
    
}
