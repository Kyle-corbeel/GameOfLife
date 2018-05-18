/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Color;

/**
 *
 * @author kyle-
 */
public class Personalisering {
    
    private Color kleurAchtergrond;
    private Color kleurDood;
    private Color kleurLevend;
    private int schaalfactor;
    
    public Personalisering()
    {
        //Standaardkleuren initialiseren
        kleurAchtergrond = Color.GRAY;
        kleurDood = Color.BLACK;
        kleurLevend = Color.WHITE;
        //Standaardinstelling initialiseren
        schaalfactor = 700;
    }
    
    /**
     * Verander de kleur van levende cellen
     * @param kleur gekozen kleur
     */
    public void setKleurLevend(Color kleur)
    {
        kleurLevend = kleur;
    }
    
    /**
     * Verander de kleur van dode  cellen
     * @param kleur  gekozen kleur
     */
    public void setKleurDood(Color kleur)
    {
        kleurDood = kleur;
    }
    
    /**
     * Verand de kleur van de achtergrodn
     * @param kleur de gekozen kleur
     */
    public void setKleurAchtergrond(Color kleur)
    {
        kleurAchtergrond = kleur;
    }
    
    /**
     * Vraag de kleur achtergrond op
     * @return int[] kleur van achtergrond
     */
    public Color getKleurAchtergrond()
    {
        return kleurAchtergrond;
    }
    
    /**
     * Vraag de kleur van de levende cellen op
     * @return int[] kleur van levende cellen
     */
    public Color getKleurLevend()
    {
        return kleurLevend;
    }
    
    /**
     * Vraag de kleur van de dode cellen op
     * @return int[] kleur van dode cellen
     */
    public Color getKleurDood()
    {
        return kleurDood;
    }
    
    /**
     * Verander de schaalfactor van het veld
     * @param newSchaalfactor Schaalfactor van het veld (hoogte)
     */
    public void setSchaalfactor(int newSchaalfactor)
    {
        schaalfactor = newSchaalfactor;
    }
    
    /**
     * Vraag de huidige schaalfactor op
     * @return int schaalfactor
     */
    public int getSchaalfactor()
    {
        return schaalfactor;
    }
    
}
