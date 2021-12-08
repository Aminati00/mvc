/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcgrafik.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import mvcgrafik.model.GrafikModelData;
import mvcgrafik.view.Fenster;
import mvcgrafik.view.GrafikView;

/**
 *
 * @author svent
 */
public class GrafikController extends MouseAdapter implements MouseMotionListener, ActionListener
{
  private GrafikView Zeichenfenster;
  private GrafikModelData model;
  private Fenster view;
  private JFileChooser fc;
  
  public GrafikController(GrafikView Zeichenfenster, GrafikModelData model, Fenster view)
  {
    this.Zeichenfenster = Zeichenfenster;
    this.model = model;
    this.view = view;
    
    fc = new JFileChooser();
  }
  
  /**
   * Funktion registerEvents() werden alle Events die es gibt registriert
   */
  public void registerEvents()
  {
    Zeichenfenster.addMouseMotionListener(this);
    Zeichenfenster.addMouseListener(this);
    view.getBtnDrucken().addActionListener(this);
    view.getBtnOeffnen().addActionListener(this);
    view.getBtnSpeichern().addActionListener(this);
    view.getBtnFarbe().addActionListener(this);
    view.getBtnStrokeWidth().addActionListener(this);
  }
  
  @Override
  public void mouseReleased(MouseEvent evt)
  {
    if (evt.getButton() == MouseEvent.BUTTON3)
    {
      Zeichenfenster.doPrint();
    }
  }

  @Override
  public void mousePressed(MouseEvent evt)
  {
     model.newFigur();
  }
  
  
  /**
   * Funktion mouseDreagged() erstellt für jeden Taktzyklus einen Punkt, ruft die Funkion drawPoint in Zeichenfenster auf und addPoint() in der aktuellen Figur
   * @param evt Referenz auf Eventquelle
   */
  @Override
  public void mouseDragged(MouseEvent evt)
  {
    Point p = evt.getPoint();
    Zeichenfenster.drawPoint(p);
    model.getNeueFigur().addPoint(p);
  }

  @Override
  public void mouseMoved(MouseEvent evt)
  {
  }

  /**
   * Funktion actionPerformed() wird ausgeführt sobald ein Event registriert wird
   * je nach Event, wird ein anderer Befehl aufgerufen
   * Es gibt folgende Events:
   * -BtnDrucken
   * -BtnSpeichern
   * -BtnOeffnen
   * -BtnText
   * -BtnFarbe
   * @param evt Referenz auf Eventquelle
   */
    @Override
    public void actionPerformed(ActionEvent evt) {
        
        Object quelle = evt.getSource();
        if (quelle == view.getBtnDrucken())
        {
            Zeichenfenster.doPrint();
        }
        else if (quelle == view.getBtnOeffnen())
        {
           fc.setCurrentDirectory(new File(model.getLastFolder()));
           System.out.println("Öffnen!");
           int choice = fc.showOpenDialog(view);
           if (choice == JFileChooser.APPROVE_OPTION)
           {
               String filename = fc.getSelectedFile().getAbsolutePath();
               try
               {
                   model.loadFigures(filename);
                   Zeichenfenster.setStrokeWidth( model.returnfirstFigure().getStrokeWidth());
                   Zeichenfenster.SetColor(model.returnfirstFigure().getColor());
                   view.repaint();
                   
               }
               catch(Exception exp)
               {
                   System.out.println(exp);
               }
           }
        }
        else if (quelle == view.getBtnSpeichern())
        {
            fc.setCurrentDirectory(new File(model.getLastFolder()));
            System.out.println("Speichern!");
            int choice = fc.showSaveDialog(view);
            if (choice == JFileChooser.APPROVE_OPTION)
            {
                String filename = fc.getSelectedFile().getAbsolutePath();
                model.setLastFolder(fc.getSelectedFile().getParent());
                
                try
                {
                    model.saveFigures(filename, Zeichenfenster.getStrokeWidth(), Zeichenfenster.getStrokeColor());
                }
                catch(Exception exp)
                {
                    System.out.println(exp);
                }
            }
        }
        else if (quelle == view.getBtnFarbe())
        {
            JColorChooser cc = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "Choose color!", Color.BLACK);
            Zeichenfenster.SetColor(color);
            view.repaint();
        }
        else if (quelle == view.getBtnStrokeWidth())
        {
            if (view.getTxtFStrokeWidth().getText().equals(""))
            {
                        
            }
            else
            {
              Zeichenfenster.setStrokeWidth(Integer.parseInt(view.getTxtFStrokeWidth().getText()));
              view.repaint(); 
            }
        }
    }
}
