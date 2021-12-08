/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcgrafik.model;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author svent
 * Serializable  -- wichtig, damit der Outputstream funktioniert!
 * in der Klasse werden die Punkte vond er jeweiligen Figure in einer ArrayList abgespeichert
 */
public class GrafikModelFigure implements Serializable
{
    private ArrayList<Point> points;
    private Point neuerPunkt;
    private Color color;
    private int strokeWidth;

    public GrafikModelFigure()
    {
      points = new ArrayList<>();
    }
    
    public List<Point> getPoints()
    {
      return Collections.unmodifiableList(points);
    }
    
    public void addPoint(Point p)
    {
      neuerPunkt = p;
      points.add(neuerPunkt);
    }
    
    public Point getNeuerPunkt()
    {
        return neuerPunkt;
    }
    
    public void speicherFarbe(Color color)
    {
        this.color = color;
    }
    
    public void speicherStrichStärke(int strokeWidth)
    {
        this.strokeWidth = strokeWidth;
    }

    public Color getColor()
    {
        return color;
    }
    
    public int getStrokeWidth()
    {
        return strokeWidth;
    }
}

