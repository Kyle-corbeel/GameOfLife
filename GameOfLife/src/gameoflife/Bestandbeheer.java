/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;

/**
 *
 * @author kyle-
 */
public class Bestandbeheer {
    private String standaardOpslagMap;
    private File[] listOfFiles;
    
    public Bestandbeheer() throws IOException
    {
        File folder = new File("./velden");
        File[] listOfFiles = folder.listFiles();

        System.out.println("Scannen van opgeslagen files: ");
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) 
            {
                System.out.println("File " + listOfFiles[i].getName());
            }
        }
    }
    
    public Veld laadVeld(String bestandsNaam) throws FileNotFoundException, IOException
    {
        byte[] data = new byte[1000];
        byte[] test = new byte[2];
        FileInputStream fis = new FileInputStream(bestandsNaam);
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
                veldMatrix[i][j] = false;
            }
        }
        int i =0;
        int j =0;
        for(int by=2;by<data.length;by++)
        {
            for(int bi=0;bi<8;bi++)
            {
                test[0] = (byte) (data[by]>>(7-bi));
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
    
    public void saveVeld(Veld veld) throws FileNotFoundException, IOException
    {
        int hoogte = veld.getHoogte();
        int breedte = veld.getBreedte();
        int benodigdeRuimte = hoogte*breedte + 16;
        int gebruikteRuimte = (benodigdeRuimte+(benodigdeRuimte % 8))/8;
        byte[] data = new byte[gebruikteRuimte+16];
        data[0] = (byte) hoogte;
        data[1] = (byte) breedte;
        System.out.println("lengte is: " +data.length);
        FileOutputStream fos = new FileOutputStream(new File("./velden/output.txt"));
        for(int i=0;i<hoogte;i++)
        {
            for(int j=0;j<breedte;j++)
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
