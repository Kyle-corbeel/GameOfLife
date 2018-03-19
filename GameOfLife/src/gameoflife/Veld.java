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
public class Veld {
    private Integer hoogte;
    private Integer breedte;
    private Boolean[][] veldMatrix;
    private Integer i;
    private Integer j;
    
    /**
     * De constructor van klasse veld, maakt een veld aan met gegeven dimensies
     * en vult deze met dode cellen
     * @param tempHoogte de hoogte die het veld zal aannemen
     * @param tempBreedte de breedte die het veld zal aannemen
     */
    
    public Veld (int tempHoogte, int tempBreedte) throws IllegalArgumentException
    {
        if (tempHoogte ==0 || tempBreedte ==0)
            throw new IllegalArgumentException("Dimensies moeten groter zijn dan 0");
        hoogte = tempHoogte;
        breedte = tempBreedte;
        Boolean[][] tempVeldMatrix = new Boolean[hoogte][breedte];
        for (i=0; i<hoogte; i++)
        {
            for (j=0; j<breedte; j++)
            {
                tempVeldMatrix[i][j] = false;
                //System.out.println(i +":" +j);
            }
        }
        veldMatrix = tempVeldMatrix;
        //System.out.println(veldMatrix.length +" op " +veldMatrix[1].length);
    }
    
    /**
     * Functie om de werking van deze classe te testen
     */
    
    public void printVeld ()
    {
        //Veld printen
        for (i=0; i<hoogte; i++)
        {
            for (j=0; j<breedte; j++)
            {
                if(veldMatrix[i][j]==false)
                    System.out.print("X  ");
                else 
                    System.out.print("O  ");
            }
            System.out.println();
        }
        
        //Lijntje trekken onder geprint veld
        for (int h = 0; h < breedte*3; h++)
        {
            System.out.print("-");
        }
        System.out.println("");
    }
    
    /**
     * Telt het aantal buren van de cel
     * @param i de rij van de cel
     * @param j de kolom van de cel
     * @return returned het aantal levende buren
     */
    
    public synchronized int aantalBuren(int i,int j)
    {
        Integer aantal = 0;
        
        for (int k = i - 1; k < i + 2; k++)
        {
            for(int m = j - 1; m < j + 2 ; m++)
            {
                if(k >= 0 && k < hoogte && m >= 0 && m < breedte)
                {
                    if(veldMatrix[k][m])
                        aantal++;
                }
            }
        }
            
        return(aantal);
    }
    
    /**
     * Controleert de status van de cel
     * @param i de rij van de cel
     * @param j de kolom van de cel
     * @return returned de staat van de cel: True-Levend, False-dood
     */
    
    public synchronized boolean getCelStatus(int i, int j)
    {
        return(veldMatrix[i][j]);
    }
    
    /**
     * Toggled de cel van levend naar dood en vice versa
     * @param i de rij van de cel
     * @param j de kolom van de cel
     */
    
    public synchronized void toggleCel(int i, int j)
    {
        veldMatrix[i][j] =  !veldMatrix[i][j];
    }
    
    /**
     * Returned de hoogte van de matrix
     * @return 
     */
    
    public int getHoogte ()
    {
        return(hoogte);
    }
    
    /**
     * Returned de breedte van de matrix
     * @return 
     */
    
    public int getBreedte ()
    {
        return(breedte);
    }
}


