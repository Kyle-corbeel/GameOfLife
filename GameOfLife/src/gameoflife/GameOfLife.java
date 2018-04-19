/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.io.IOException;

/**
 *
 * @author kyle-
 */
public class GameOfLife {
    
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        //Start user interface op
        UserInterface UI = new UserInterface();
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UI.setVisible(true);
            }
        });
                
        
        
        try{
            Veldbeheer veldbeheer = new Veldbeheer();
            Bestandbeheer bestandH1 = new Bestandbeheer();
            Veld veld = veldbeheer.maakVeld(10, 10);
            //Glider aanmaken om werking te testen
            veld.toggleCel(0, 1);
            veld.toggleCel(1, 2);
            veld.toggleCel(2, 0);
            veld.toggleCel(2, 1);
            veld.toggleCel(2, 2);
            
            //Lijn van drie eenheden aanmaken om de werking te testen
            veld.toggleCel(5, 6);
            veld.toggleCel(5, 7);
            veld.toggleCel(5, 8);
            
            SimulatieBestuur simBestuur = new SimulatieBestuur(veld);
            
            /* Test of aantalBuren werkt 
            System.out.println("0,2 : " + veld1.aantalBuren(0, 2) + " - " + veld1.getCelStatus(0, 2));
            System.out.println("1,0 : " + veld1.aantalBuren(1, 0) + " - " + veld1.getCelStatus(1, 0));
            System.out.println("1,1 : " + veld1.aantalBuren(1, 1) + " - " + veld1.getCelStatus(1, 1));
            */            
            UI.refreshVeld(veld);
            //simBestuur.play(1);
            
            //bestandH1.saveVeld(veld1, "output.txt");
            //veld1 = bestandH1.laadVeld("output.txt");
            //veld1.printVeld();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
            
    }
    
}
