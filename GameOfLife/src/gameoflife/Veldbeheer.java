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
public class Veldbeheer {
    
    public Veldbeheer()
    {
        
    }
    
    public Veld maakVeld(int hoogte, int breedte)
    {
        try{
            Veld veld1 = new Veld(hoogte,breedte);
            return(veld1);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return(veld1);
        }
        
    }
    
}
