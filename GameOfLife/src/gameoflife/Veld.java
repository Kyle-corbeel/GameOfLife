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
        if (tempHoogte ==0|| tempBreedte ==0)
        {
            throw new IllegalArgumentException("Dimensies moeten groter zijn dan 0");
        }
        hoogte = tempHoogte;
        breedte = tempBreedte;
        Boolean[][] tempVeldMatrix = new Boolean[hoogte][breedte];
        for (i=0; i<hoogte; i++)
        {
            for (j=0; j<breedte; j++)
            {
                tempVeldMatrix[i][j] = false;
                System.out.println(i +":" +j);
            }
        }
        veldMatrix = tempVeldMatrix;
        System.out.println(veldMatrix.length +" op " +veldMatrix[1].length);
    }
    
    public void printVeld ()
    {
        for (i=0; i<hoogte; i++)
        {
            for (j=0; j<breedte; j++)
            {
                if(veldMatrix[i][j]==false)
                {
                    System.out.print("X  ");
                }
                else 
                {
                    System.out.print("O  ");
                }
            }
            System.out.println();
        }
    }
    
    public int aantalBuren(int i,int j)
    {
        Integer aantal = 0;
        Integer k;
        Integer m;
        for (k=i-1;k<i+2;k++)
        {
            for(m=j-1;m<j+2;j++)
            {
                
            }
        }
            
        return(aantal);
    }
    
    public boolean getCelStatus(int i, int j)
    {
        return(veldMatrix[i][j]);
    }
    
    public void toggleCel(int i, int j)
    {
        veldMatrix[i][j] =  !veldMatrix[i][j];
    }
}


