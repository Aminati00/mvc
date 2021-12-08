/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcgrafik;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import mvcgrafik.controller.GrafikController;
import mvcgrafik.model.GrafikModelData;
import mvcgrafik.view.Fenster;
import mvcgrafik.view.GrafikView;

/**
 *
 * @author svent
 */
public class Start 
{
    
  /**
   * Funktion Start() erstellt alle Klasen
   */
  public Start()
  {
    GrafikView Zeichenfenster = new GrafikView();
    GrafikModelData model = new GrafikModelData();
    Zeichenfenster.setModel(model);
    Fenster view = new Fenster(Zeichenfenster);
    GrafikController controller = new GrafikController(Zeichenfenster, model, view);
    controller.registerEvents();
    
    view.setVisible(true);
    
  }

  public static void main(String[] args) 
  {
    try    
    {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }    
    catch (Exception ex)    
    {
      JOptionPane.showMessageDialog(null, ex.toString());
    }
    
    new Start();
  }

}
