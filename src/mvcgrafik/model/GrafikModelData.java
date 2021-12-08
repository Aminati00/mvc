/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcgrafik.model;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.prefs.Preferences;

/**
 *
 * @author svent
 */
public class GrafikModelData
{
  private ArrayList<GrafikModelFigure> Figures;
  private GrafikModelFigure neueFigur;
  private Preferences p;
  
  public GrafikModelData()
  {
    Figures = new ArrayList<>();
    p = Preferences.userRoot().node(getClass().getName());
  }
  
  /**
   * newFigur() fügt der ArrayList Figures eine neue Figur hinzu
   */
  public void newFigur()
  {
    neueFigur = new GrafikModelFigure();
    Figures.add(neueFigur);
  }

  /**
   * gibt die ArrayList Figures zurück. Jedoch nur als Read!
   * @return List<GrafikModelFigure> 
   */
  public List<GrafikModelFigure> getFigures()
  {
    return Collections.unmodifiableList(Figures);
  }
  
  public GrafikModelFigure getNeueFigur()
  {
      return neueFigur;
  }
  
  /**
   * In der Funktion saveFigures() wird die Array List Figures auf den ObjectOutputStream geschrieben
 Davor wird noch die strokeColor und die StrokeWidth in den Figure gespeichert
   * @param filename
   * @param strokeWidth
   * @param strokeColor
   * @throws FileNotFoundException
   * @throws IOException 
   */
  public void saveFigures(String filename, int strokeWidth, Color strokeColor) throws FileNotFoundException, IOException
  {
    FileOutputStream fos = new FileOutputStream(filename);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    saveColor(strokeColor);
    saveStrokeWidth(strokeWidth);
    oos.writeObject(Figures);
    oos.flush(); // wichtig! leert und schreibt damit den Puffer
    oos.close();
  }
  
  /**
   * Funktion loadFigures() wird der ObjectInputStream auf die Figures als ArrayList geschrieben
   * @param filename
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ClassNotFoundException 
   */
  public void loadFigures(String filename) throws FileNotFoundException, IOException, ClassNotFoundException
  {
    FileInputStream fis = new FileInputStream(filename);
    BufferedInputStream bis = new BufferedInputStream(fis);
    ObjectInputStream ois = new ObjectInputStream(bis);
    Figures = (ArrayList<GrafikModelFigure>)ois.readObject();
    System.out.println("Punkte geladen!");

    ois.close();
  }
  
  /**
   * 
  */
  public String getLastFolder()
  {
      String lastFolder = p.get("lastFolder", System.getProperty("user.home"));
      return lastFolder;
  }
  
  /**
   * 
   * @param lastFolder 
   */
  public void setLastFolder(String lastFolder)
  {
      p.put("lastFolder", lastFolder);
  }
  
  /**
   * 
   * @param color 
   */
  
  public void saveColor(Color color)
  {
      Figures.forEach(f ->
      {
          f.speicherFarbe(color);
      });
  }
  
  /**
   * 
   * @param StrokeWidth 
   */
  public void saveStrokeWidth(int StrokeWidth)
  {
      Figures.forEach(f ->
      {
          f.speicherStrichStärke(StrokeWidth);
      });
  }
  
  /**
   * 
   * @return 
   */
  public GrafikModelFigure returnfirstFigure()
  {
      return Figures.get(0);
  }
}