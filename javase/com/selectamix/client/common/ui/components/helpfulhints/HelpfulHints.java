package com.selectamix.client.common.ui.components.helpfulhints;

/**
 * Insert the type's description here.
 * Creation date: (13/09/01 12:53:30)
 * @author: 
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import uk.co.robertjdavis.library.graphics.heading.Heading;
import uk.co.robertjdavis.library.ui.adapters.ResizeableViewGrapher;
import java.awt.Dimension;
import java.util.Hashtable;
import java.util.Observer;
import uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource;
 
public class HelpfulHints implements ResizeableViewGrapher, Observer
{
  private Heading heading = null;
  private java.awt.Dimension size = new Dimension();
  private int bodyHeight = 0;
	private int bodyDrawingStartY = 0;
	private GraphicsSource graphicsSource = null;
	private java.awt.Color background = null;
/**
 * HelpfulHints constructor comment.
 */
public HelpfulHints( int width, int headingHeight, int heightOfBody ) 
{
  super();
  bodyHeight = heightOfBody;
  heading = new Heading( "Helpful Hints", Heading.LEFT, width, headingHeight );

  size.width = width;
  size.height = headingHeight + bodyHeight;
  bodyDrawingStartY = headingHeight;
}
/**
 * draw method comment.
 */
public void draw( Graphics g ) 
{
 // if (!preDrawn) preDraw( g );
   
 heading.draw( g );

  g.setColor( background );
  g.fillRect( 0, bodyDrawingStartY, size.width, bodyHeight );
}
/**
 * getSize method comment.
 */
public java.awt.Dimension getSize() {
	return size;
}
/**
 * Insert the method's description here.
 * Creation date: (13/09/01 21:30:01)
 * @param newGraphicsSource basicgraphics.GraphicsSource
 */
public void setGraphicsSource(GraphicsSource newGraphicsSource) {
	graphicsSource = newGraphicsSource;
}
/**
 * setViewableArea method comment.
 */
public void setViewableArea(int x, int y, int w, int d, java.awt.Graphics g) 
{
  size.width = w;
  heading.setViewableArea( x, y, w, d, g );
}
/**
 * update method comment.
 */
public void update(java.util.Observable o, java.lang.Object arg) 
{
  Hashtable visualAttributes = (Hashtable)arg;
  background = (Color)visualAttributes.get( "bkgnd" );
  Hashtable headingVisualAttributes = (Hashtable)visualAttributes.get( "heading" );
  heading.setAppearance( (Color)headingVisualAttributes.get( "bkgnd" ),
	                     (Color)headingVisualAttributes.get( "fgnd" ),
	                     (Font)headingVisualAttributes.get( "font" ));
}
}
