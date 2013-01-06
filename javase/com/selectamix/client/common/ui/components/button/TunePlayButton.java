package com.selectamix.client.common.ui.components.button;

/**
 * Insert the type's description here.
 * Creation date: (18/08/01 14:25:25)
 * @author: 
 */
import java.awt.Color;
import java.awt.Graphics;

import java.util.Hashtable;
import uk.co.robertjdavis.library.graphics.vector.TransformablePolygon;
import uk.co.robertjdavis.library.ui.controller.VectorUserInterfaceType;
import uk.co.robertjdavis.library.ui.controller.PointerOverState;
 
public class TunePlayButton extends TuneButton implements VectorUserInterfaceType
{
  private TransformablePolygon playSymbol = null;
  
  private Color background = null;
  
  private Color pointerOffFill = null;
  private Color pointerOffOutline = null;
  private Color pointerOffSymbolFill = null;
  private Color pointerOffSymbolOutline = null;
  
  private Color pointerOnFill = null;
  private Color pointerOnOutline = null;
  private Color pointerOnSymbolFill = null;
  private Color pointerOnSymbolOutline = null;

  private Color pressedSymbolFill = null;
  private Color pressedSymbolOutline = null;
/**
 * Insert the method's description here.
 * Creation date: (21/08/01 20:43:47)
 */
public TunePlayButton( Object container, Hashtable colourScheme ) 
{
  super( container );
  int pointsX[] = { -radius / 2, -radius / 2, radius / 2 };
  int pointsY[] = { -radius / 2, radius / 2, 0 };
  playSymbol = new TransformablePolygon( pointsX, pointsY, 3 );
 // changeColourScheme( colourScheme );
}
/**
 * draw method comment.
 */
public void draw( Graphics g ) 
{
  if ( drawn || !visible) return;

  Color fill = null;
  Color outline = null;

  if ( pointerOverState == PointerOverState.OFF )
  {
	fill = pointerOffFill;
	outline = pointerOffOutline;
  }
  else if ( pointerOverState == PointerOverState.ON )
  {
	fill = pointerOnFill;
	outline = pointerOnOutline;
  }

  g.setColor( fill );
  g.fillOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  g.setColor( outline );
  g.drawOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  g.drawPolygon( playSymbol );
}
/**
 * getPointerMovedHandleMode method comment.
 */
public int getPointerMovedHandleMode() {
	return HANDLEMODE_SELF;
}
/**
 * getPointerPressedHandleMode method comment.
 */
public int getPointerPressedHandleMode() {
	return HANDLEMODE_SELF;
}
/**
 * getPointerReleasedHandleMode method comment.
 */
public int getPointerReleasedHandleMode() {
	return HANDLEMODE_SELF;
}
/**
 * hide method comment.
 */
public void hide(java.awt.Graphics g) 
{
  g.setColor( background );
  g.fillOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  g.drawOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  visible = false;
}
/**
 * pointerMovedOff method comment.
 */
public void pointerMovedOff(java.awt.Graphics g) 
{
  g.setColor( pointerOffFill );
  g.fillOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  g.setColor( pointerOffOutline );
  g.drawOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  g.setColor( pointerOffSymbolOutline );
  g.drawPolygon( playSymbol );
  
  pointerOverState = PointerOverState.OFF;
}
/**
 * pointerMovedOn method comment.
 */
public void pointerMovedOn(java.awt.Graphics g) 
{
  g.setColor( pointerOnFill );
  g.fillOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  g.setColor( pointerOnOutline );
  g.drawOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  g.drawPolygon( playSymbol );
  pointerOverState = PointerOverState.ON;
}
/**
 * pointerPressed method comment.
 */
public void pointerPressed(java.awt.Graphics g) 
{
  g.setColor( pressedSymbolFill );
  g.fillPolygon( playSymbol );
  g.setColor( pressedSymbolOutline );
  g.drawPolygon( playSymbol );
}
/**
 * pointerReleased method comment.
 */
public void pointerReleased(java.awt.Graphics g) 
{
  g.setColor( pointerOnFill );
  g.fillPolygon( playSymbol );
  g.setColor( pointerOnSymbolOutline );
  g.drawPolygon( playSymbol );
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/00 15:40:54)
 * @param x int
 */
public void setCentreX(int newX) 
{
  int newShiftX = newX - centreX;
  playSymbol.translate( newShiftX, 0 );
  super.setCentreX( newX );
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/00 15:43:47)
 * @param y int
 */
public void setCentreY(int newY) 
{
  int newShiftY = newY - centreY;
  playSymbol.translate( 0, newShiftY );
  super.setCentreY( newY );
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 15:00:05)
 * @param newRadius int
 */
public void setRadius(int newRadius) 
{
  double scaleFactor = ((double)newRadius) / ((double)radius);
  playSymbol.scale( scaleFactor );
  super.setRadius(newRadius);
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/01 19:10:42)
 * @param newVisualAttributes java.util.Hashtable
 */
public void setVisualAttributes(Hashtable newVisualAttributes) 
{
  background = (Color) newVisualAttributes.get( "bkgnd" );
  pointerOffFill = (Color) newVisualAttributes.get( "ptrOffFl" );
  pointerOffOutline = (Color) newVisualAttributes.get( "ptrOffOl" );
  pointerOffSymbolOutline = (Color) newVisualAttributes.get( "ptrOffSymOl" );
  pointerOffSymbolFill = (Color) newVisualAttributes.get( "ptrOffSymFl" );
  pointerOnFill = (Color) newVisualAttributes.get( "ptrOnFl" );    
  pointerOnOutline = (Color) newVisualAttributes.get( "ptrOnOl" ); 
  pointerOnSymbolOutline = (Color) newVisualAttributes.get( "ptrOnSymOl" );  
  pointerOnSymbolFill = (Color) newVisualAttributes.get( "ptrOffSymFl" );
  pressedSymbolOutline = (Color) newVisualAttributes.get( "pressedSymOl" );
  pressedSymbolFill = (Color) newVisualAttributes.get( "pressedSymOl" );
}
}
