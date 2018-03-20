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
public class Personalisering {
    private int[] kleurAchtergrond;
    private int[] kleurDood;
    private int[] kleurLevend;
    
    public Personalisering()
    {
        int[] kleurAchtergrond = {125,125,125};
        int[] kleurDood = {255,255,255};
        int[] kleurLevend = {0,0,0};
        
    }
    
    /**
     * Verander de kleur van levende cellen
     * @param kleur gekozen kleur
     */
    
    public void kiesKleurLevend(int[] kleur)
    {
        kleurLevend = kleur;
    }
    
    /**
     * Verander de kleur van dode  cellen
     * @param kleur  gekozen kleur
     */
    
    public void kiesKleurDood(int[] kleur)
    {
        kleurDood = kleur;
    }
    
    /**
     * Verand de kleur van de achtergrodn
     * @param kleur de gekozen kleur
     */
    
    public void kiesKleurAchtergrond(int[] kleur)
    {
        kleurAchtergrond = kleur;
    }
    
}
