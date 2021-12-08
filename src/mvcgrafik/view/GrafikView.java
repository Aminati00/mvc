/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvcgrafik.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import mvcgrafik.model.GrafikModelData;

/**
 *
 * @author svent
 * GrafikView erstellt die graphische Oberfläche für das Center von View
 */
public class GrafikView extends JComponent implements Printable
{
  private final static Dimension DIM_ONE = new Dimension(1, 1);
  private Rectangle2D.Float pixel;
  private GrafikModelData model;
  private Point lastPoint;
  int hilf = 0;
  private Color color = Color.BLACK;
  private int strokewidth = 5;
  
  public GrafikView()
  {
    pixel = new Rectangle2D.Float();
  }
  
  public void setModel(GrafikModelData model)
  {
    this.model = model;
  }
  
  public Color getStrokeColor()
  {
      return color;
  }
  
  public int getStrokeWidth()
  {
      return strokewidth;
  }
  
  /**
   * Funktion drawPoint() malt einen neuen Pixel im Punkt p und verbindet diesen mit dem voherigen, wenn dieser nicht der erste in der ArrayList ist
   *@param p Punkt als Koordinate mit y und x
   */
  public void drawPoint(Point p)
  {
    Graphics2D g2 = (Graphics2D)this.getGraphics();
    pixel.setFrame(p, DIM_ONE);
    g2.draw(pixel);
    g2.setStroke(new BasicStroke(strokewidth));
    g2.setColor(color);
    if (model.getNeueFigur().getPoints().isEmpty())
    {
        
    }
    else
    {
      g2.drawLine(model.getNeueFigur().getNeuerPunkt().x, model.getNeueFigur().getNeuerPunkt().y, p.x, p.y); 
    }
    
    
    g2.dispose(); // GAAAANNNNNZZZZZZ WICHTIG!!!!!!
  }
  
  public void SetColor(Color c)
  {
    this.color = c;
  }
  
  public void setStrokeWidth(int sw)
  {
      this.strokewidth = sw;
  }
  
  /**
   * Funktion paintComponent wird immer dann aufgerufen, wenn das Fenster neu gemalt werden muss(Verschiebung, verändern der Größe,...)
   * Es wird die ArrayList mit allen Figures durchgegangen 
   * Für jedes Figure wird die ArrayList mit aööen Points durchgegangen
   * Jeder Point wird gezeichnet, und wenn es nicht der erste ist (hilf = 0) wird er auch mit einer Linie mit dem vorherigen verbunden
   * @param g 
   */
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    hilf = 0;
    g2.setStroke(new BasicStroke(strokewidth));
    g2.setColor(color);
    model.getFigures().forEach(f ->
     { 
        f.getPoints().forEach(p -> 
        {
          pixel.setFrame(p, DIM_ONE);
          g2.draw(pixel);
          
          if (hilf == 0)
          {
             hilf = 1;
          }
          else
          {
            g2.drawLine(lastPoint.x, lastPoint.y, p.x, p.y);
          }
          lastPoint = p;
          });
        hilf = 0;
     });
    
  }
  
  /**
   * Funktion doPrint() kann die GrafikView ausdrucken
   */
  public void doPrint()
  {
    HashPrintRequestAttributeSet printSet =
            new HashPrintRequestAttributeSet();
    printSet.add(DialogTypeSelection.NATIVE);
    PrinterJob pj = PrinterJob.getPrinterJob();
    pj.setPrintable(this);
    if (pj.printDialog(printSet))
    {
      try
      {
        pj.print(printSet);
      }
      catch (PrinterException ex)
      {
        JOptionPane.showMessageDialog(this, ex.toString());
      }
    }
  }

  @Override
  public int print(Graphics gp, PageFormat pf, int pageIndex) throws PrinterException
  {
    Graphics2D g2p = (Graphics2D)gp;
    if (pageIndex == 0)
    {
      g2p.translate(pf.getImageableX(), pf.getImageableY());
      g2p.scale(pf.getImageableWidth()/this.getWidth(), 
                pf.getImageableHeight()/this.getHeight());
      super.print(g2p);
      return Printable.PAGE_EXISTS;
    }
    else
    {
      return Printable.NO_SUCH_PAGE;
    }
  }
  
}
