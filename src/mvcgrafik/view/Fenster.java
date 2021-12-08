/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcgrafik.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author svent
 */
public class Fenster extends JFrame {

    private final JButton btnSpeichern;
    private final JButton btnOeffnen;
    private final JButton btnDrucken;
    private final JButton btnFarbe;
    private final JButton btnStrokeWidth;
    private final JTextField txtFStrokeWidth;
    private final JTextArea taEdit;
    private final GrafikView tfEingabe;
    
   /**
   * Im Konstruktor von Fenster werden alle Container festgelegt die es gibt und mit Inhalt gefüllt
   * Fenster ist für das Overlay des gesamten Programmes zuständig
   * Nur das eingefügte tfEingabe wird extern in GrafikView verwaltet
   * @param tfEingabe ist das Zeichenfenster und wird extern verwaltet
   */
    public Fenster(GrafikView tfEingabe)
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        btnSpeichern = new JButton("Speichern");
        btnOeffnen = new JButton("Oeffnen");
        btnDrucken = new JButton("Drucken");
        btnFarbe = new JButton("Change Color!");
        btnStrokeWidth = new JButton("Change Stroke Width!");
        txtFStrokeWidth = new JTextField();
        this.tfEingabe = tfEingabe;
        taEdit = new JTextArea();
        txtFStrokeWidth.setColumns(2);
        
        Container cMain = this.getContentPane();
        cMain.setLayout(new BorderLayout());
        
        Container cSouth = new Container();
        cSouth.setLayout(new FlowLayout());
        
        Container cEast = new Container();
        cEast.setLayout(new GridLayout(0,1, 200, 200));
        
        cEast.add(btnFarbe);
        cEast.add(btnStrokeWidth);
        cEast.add(txtFStrokeWidth);
        
        cSouth.add(btnSpeichern);
        cSouth.add(btnOeffnen);
        cSouth.add(btnDrucken);
        
        cMain.add(cEast, BorderLayout.EAST);
        cMain.add(cSouth, BorderLayout.SOUTH);
        cMain.add(tfEingabe, BorderLayout.CENTER);
        
        this.setSize(800, 600);
    }
    
    public JButton getBtnFarbe()
    {
        return btnFarbe;
    }
    
    public JButton getBtnStrokeWidth()
    {
        return btnStrokeWidth;
    }
    
    public JButton getBtnSpeichern()
    {
        return btnSpeichern;
    }
    
    public JButton getBtnOeffnen()
    {
        return btnOeffnen;
    }
    
    public JButton getBtnDrucken()
    {
        return btnDrucken;
    }
    
    public JTextField getTxtFStrokeWidth()
    {
        return txtFStrokeWidth;
    }
}
