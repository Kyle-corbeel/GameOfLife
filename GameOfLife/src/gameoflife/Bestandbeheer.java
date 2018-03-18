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
public class Bestandbeheer {
    private String standaardOpslagMap;
    private int i;
    private int j;
    
    public Bestandbeheer()
    {
        
    }
    
    public Boolean[][] laadVeld(String bestandsNaam)
    {
        Boolean[][] veldMatrix = new Boolean[][]{
                {false, false, false, false, false},
                {true, true, true, true, true},
                {false, false, false, false, false},
                {true, true, true, true, true},
                {false, false, false, false, false}
            };
        return(veldMatrix);
    }
    
    public void saveVeld(Veld veld)
    {
        int hoogte = veld.getHoogte();
        int breedte = veld.getBreedte();
        Boolean[][] veldMatrix = new Boolean[hoogte][breedte];
        for(i=0;i<hoogte;i++)
        {
            for(j=0;j<breedte;j++)
            {
                if(veld.getCelStatus(i, j))
                {
                    veldMatrix[i][j] = true;
                }
            }
        }
    }
    
}
