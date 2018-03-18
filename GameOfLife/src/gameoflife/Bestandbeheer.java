/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 *
 * @author kyle-
 */
public class Bestandbeheer {
    private String standaardOpslagMap;
    private int i;
    private int j;
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
    
    public void saveVeld(Veld veld) throws FileNotFoundException, IOException
    {
        int hoogte = veld.getHoogte();
        int breedte = veld.getBreedte();
        Boolean[][] veldMatrix = new Boolean[hoogte][breedte];
        int benodigdeRuimte = hoogte*breedte + 16;
        int gebruikteRuimte = (benodigdeRuimte+(benodigdeRuimte % 8))/8;
        byte[] data = new byte[gebruikteRuimte];
        data[0] = (byte) hoogte;
        data[1] = (byte) breedte;
        System.out.println("lengte is: " +data.length);
        FileOutputStream fos = new FileOutputStream(new File("./velden/output.txt"));
        for(i=0;i<hoogte;i++)
        {
            for(j=0;j<breedte;j++)
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
