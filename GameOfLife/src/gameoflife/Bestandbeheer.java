/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author kyle-
 */
public class Bestandbeheer {
    private String standaardOpslagMap = "./velden";
    private File[] listOfFiles;
    
    /**
     * Bij het initialiseren word de standaarmap gecontroleerd op files
     * @throws IOException 
     */
    
    public Bestandbeheer() throws IOException
    {
        File folder = new File(standaardOpslagMap);
        File[] listOfFiles = folder.listFiles();

        System.out.println("Scannen van opgeslagen files: ");
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) 
            {
                System.out.println("File " + listOfFiles[i].getName());
            }
        }
    }
    
    /**
     * Er wordt een nieuw veld gecreeërd op basis van het gekozen bestand
     * @param bestandsNaam
     * @return het gecreeërde veld
     * @throws FileNotFoundException
     * @throws IOException 
     */
    
    public Veld laadVeld(String bestandsNaam) throws FileNotFoundException, IOException
    {
        byte[] data = new byte[1000];
        byte[] test = new byte[2];
        FileInputStream fis = new FileInputStream(standaardOpslagMap +"/" +bestandsNaam);
        Veldbeheer veldbeh = new Veldbeheer();
        fis.read(data);
        int hoogte = data[0];
        int breedte = data[1];
        System.out.println("H: " +hoogte +" B: " +breedte);
        Boolean[][] veldMatrix = new Boolean[hoogte][breedte];
        for(int i=0;i<hoogte;i++)
        {
            for(int j=0;j<breedte;j++)
            {
                veldMatrix[i][j] = false; //zet de volledige matrix standaard op false
            }
        }
        int i =0;
        int j =0;
        for(int by=2;by<data.length;by++)   //Gaat bit per bit door de data
        {                                   //wanneer de bit een 1 is zal
            for(int bi=0;bi<8;bi++)         //in veldmatrix het overeenkomstige
            {                               //veld op true gezet worden
                if((data[by]>>(7-bi) & 0b00000001) == 0b00000001)
                {
                    veldMatrix[i][j] = true;
                }
                if(j==breedte-1)
                    {
                        j=0;
                        i++;
                    }
                else
                    {
                        j++;
                    }
            }
        }
        
        
        
        Veld veld = veldbeh.maakVeldFile(veldMatrix);             
        return(veld);
    }
    
    /**
     * Slaat het doorgegeven veld op in de standaardmap
     * @param veld het op te slagen veld
     * @param bestandsNaam de gekozen bestandsnaam
     * @throws FileNotFoundException
     * @throws IOException 
     */
    
    public void saveVeld(Veld veld, String bestandsNaam) throws FileNotFoundException, IOException
    {
        int hoogte = veld.getHoogte();
        int breedte = veld.getBreedte();
        int benodigdeRuimte = hoogte*breedte + 16;
        int gebruikteRuimte = (benodigdeRuimte+(benodigdeRuimte % 8))/8;
        byte[] data = new byte[gebruikteRuimte+16];
        data[0] = (byte) hoogte;
        data[1] = (byte) breedte;
        System.out.println("lengte is: " +data.length);
        FileOutputStream fos = new FileOutputStream(new File(standaardOpslagMap +"/" +bestandsNaam));
        for(int i=0;i<hoogte;i++)       //Converteert het veld naar een array
        {                               //van bytes, om deze zo compact mogelijk
            for(int j=0;j<breedte;j++)  //op te slaan.
            {
                int elementPlace = i*breedte+j+16;
                int bitnr = elementPlace % 8;
                int bytenr = (elementPlace - bitnr)/8;
                if(veld.getCelStatus(i, j))
                {
                    data[bytenr] |= (1<<7-bitnr);
                }
            }
        }
        fos.write(data);
        fos.flush();
        fos.close();
        
    }
    
}
