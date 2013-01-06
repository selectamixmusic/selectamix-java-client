package com.selectamix.client.common.ui.components.button;

/**
 * Insert the type's description here.
 * Creation date: (21/08/01 21:06:51)
 * @author: 
 */
import java.awt.Color;
import java.util.Hashtable;
import uk.co.robertjdavis.library.ui.controller.VectorUserInterfaceType;
import uk.co.robertjdavis.library.ui.controller.PointerOverState;
import uk.co.robertjdavis.library.definitions.SelectState;
import java.awt.Graphics;
 
public class TuneSelectButton extends TuneButton implements VectorUserInterfaceType
{
  protected int crossX1 = 0;
  protected int crossX2 = 0;
  protected int crossY1 = 0;
  protected int crossY2 = 0;

	private int selectState = SelectState.UNSELECTED;
	private int number = 0;

  private Color pointerOffFill = null;
	private Color pressedSymbolFill = null;
	private Color pressedSymbolOutline = null;
  private Color pointerOffOutline = null;
  private Color pointerOffSymbolFill = null;
  private Color pointerOffSymbolOutline = null;
  
  private Color pointerOnFill = null;
  private Color pointerOnOutline = null;
  private Color pointerOnSymbolFill = null;
  private Color pointerOnSymbolOutline = null;
/**
 * SelectButtonColourAppearance constructor comment.
 */
public TuneSelectButton( Object container ) 
{
  super( container );
  visible = true;
}
/**
 * Insert the method's description here.
 * Creation date: (29/10/00 12:42:46)
 */
protected void calculateCircleParams() 
{
  super.calculateCircleParams();

  crossX1 = 0; crossY1 = -radius / 2;
  crossX2 = radius / 2; crossY2 = 0;
}
/**
 * draw method comment.
 */
public void draw( Graphics g ) 
{
  if ( !visible ) return;

  Color fill = background;
  Color outline = background;
  Color symbol = background;

  if ( pointerOverState == PointerOverState.OFF )
  {
	symbol = pointerOffSymbolOutline;
	fill = pointerOffFill;
	outline = pointerOffOutline;
  }
  else if ( pointerOverState == PointerOverState.ON )
  {
	symbol = pointerOnSymbolOutline;
	fill = pointerOnFill;
	outline = pointerOnOutline;
  }

  g.setColor( fill );
  g.fillOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  g.setColor( outline );
  g.drawOval( circleTopLeftX, circleTopLeftY, radiusTimes2, radiusTimes2 );
  g.setColor( symbol );
  if ( selectState == SelectState.UNSELECTED ) 
  {
	drawPlus( g );
  }
  else if ( selectState == SelectState.SELECTED )
  {
	g.drawLine( centreX - radius / 2, centreY, centreX + radius / 2, centreY );
  }
}
/**
 * Insert the method's description here.
 * Creation date: (22/08/01 10:37:02)
 * @param g java.awt.Graphics
 */
private void drawPlus(Graphics g) 
{
  g.drawLine( centreX, centreY - radius / 2, centreX, centreY + radius / 2 );
  g.drawLine( centreX - radius / 2, centreY, centreX + radius / 2, centreY );
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
	return HANDLEMODE_PARENT;
}
/**
 * getPointerReleasedHandleMode method comment.
 */
public int getPointerReleasedHandleMode() {
	return HANDLEMODE_SELF;
}
/**
 * Insert the method's description here.
 * Creation date: (22/08/01 09:55:59)
 * @return int
 */
public int getSelectState() {
	return selectState;
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
  if ( selectState == SelectState.UNSELECTED )
  {
	drawPlus( g );
  } 
  else if ( selectState == SelectState.SELECTED )
  {
	g.drawLine( centreX - radius / 2, centreY, centreX + radius / 2, centreY );
  }

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
  g.setColor( pointerOnSymbolOutline );
  if ( selectState == SelectState.UNSELECTED )
  {
	drawPlus( g );
  } 
  else if ( selectState == SelectState.SELECTED )
  {
	g.drawLine( centreX - radius / 2, centreY, centreX + radius / 2, centreY );
  }

  pointerOverState = PointerOverState.ON;
}
/**
 * pointerPressed method comment.
 */
public void pointerPressed(java.awt.Graphics g) 
{
  if ( selectState == SelectState.SELECTED )
  {
	selectState = SelectState.UNSELECTED;
  }
  else if ( selectState == SelectState.UNSELECTED )
  {
	selectState = SelectState.SELECTED;
  }
}
/**
 * pointerReleased method comment.
 */
public void pointerReleased(java.awt.Graphics g) {}
/**
 * Insert the method's description here.
 * Creation date: (22/08/01 10:39:54)
 * @param newNumber int
 */
public void setNumber(int newNumber) {
	number = newNumber;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 15:00:05)
 * @param newRadius int
 */
public void setRadius(int newRadius) 
{
  super.setRadius( newRadius );
}
/**
 * Insert the method's description here.
 * Creation date: (22/08/01 09:55:59)
 * @param newSelectState int
 */
public void setSelectState(int newSelectState) {
	selectState = newSelectState;
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
