package com.selectamix.client.common.ui.components.button;

/**
 * Insert the type's description here.
 * Creation date: (22/08/01 09:41:00)
 * @author: 
 */

import uk.co.robertjdavis.library.ui.controller.PointerOverState;
import java.awt.Color;
import java.awt.Graphics;
 
public class TuneButton 
{
  protected int radius = 20;

  protected int centreX = 0;
  protected int centreY = 0;

  protected Color background = null;

  protected int radiusTimes2 = 0;

  protected int circleTopLeftX = 0;
  protected int circleTopLeftY = 0;

	protected boolean visible = false;

  protected boolean drawn = false;

  int pointerOverState = PointerOverState.OFF;
	private java.lang.Object parentContainer = null;
/**
 * CircularButton constructor comment.
 */
protected TuneButton( Object obj ) 
{
  super();
  background = Color.black;
  parentContainer = obj;
}
/**
 * Insert the method's description here.
 * Creation date: (29/10/00 12:42:46)
 */
protected void calculateCircleParams() 
{
  radiusTimes2 = radius*2;
  circleTopLeftX = centreX - radius;
  circleTopLeftY = centreY - radius;
}
/**
 * Insert the method's description here.
 * Creation date: (28/10/00 19:58:54)
 * @param g java.awt.Graphics
 */
public void draw(Graphics g) 
{
}
/**
 * Insert the method's description here.
 * Creation date: (18/08/01 14:55:03)
 * @return java.awt.Color
 */
public java.awt.Color getBackground() {
	return background;
}
/**
 * Insert the method's description here.
 * Creation date: (20/08/01 13:29:13)
 * @return int
 */
public int getCentreX() {
	return centreX;
}
/**
 * Insert the method's description here.
 * Creation date: (20/08/01 13:29:13)
 * @return int
 */
public int getCentreY() {
	return centreY;
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 21:41:33)
 * @return java.lang.Object
 */
public java.lang.Object getParentContainer() {
	return parentContainer;
}
/**
 * Insert the method's description here.
 * Creation date: (20/08/01 13:29:13)
 * @return int
 */
public int getRadius() {
	return radius;
}
/**
 * Insert the method's description here.
 * Creation date: (28/10/00 18:27:03)
 * @return boolean
 */
public boolean inside( int qx, int qy) 
{
  int radiusToUse = this.getRadius();
	
  boolean inside = false;
  int relativeX = centreX - qx;
  int relativeY = centreY - qy;
  // by pythagoras
  double distanceFromCentreDouble = Math.sqrt( ( new Integer( relativeX*relativeX + relativeY*relativeY ) ).doubleValue() );
  int distanceFromCentre = ( new Double ( distanceFromCentreDouble ) ).intValue();
  if ( distanceFromCentre < radiusToUse )
  {
	inside = true;
  }
  
  return inside;
}
/**
 * isInside method comment.
 */
public boolean isInside(int x, int y) {
	return inside( x, y );
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 15:05:26)
 * @return boolean
 */
public boolean isVisible() {
	return visible;
}
/**
 * Insert the method's description here.
 * Creation date: (22/08/01 10:13:00)
 * @return boolean
 */
public void reDrawRequired() {
	drawn = false;
}
/**
 * Insert the method's description here.
 * Creation date: (18/08/01 14:55:03)
 * @param newBackground java.awt.Color
 */
public void setBackground(java.awt.Color newBackground) {
	background = newBackground;
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/00 15:40:54)
 * @param x int
 */
public void setCentreX(int newX) 
{
  centreX = newX;
  calculateCircleParams();
}
/**
 * Insert the method's description here.
 * Creation date: (05/11/00 15:43:47)
 * @param y int
 */
public void setCentreY(int newY) 
{
  centreY = newY;
  calculateCircleParams();
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 21:41:33)
 * @param newParentContainer java.lang.Object
 */
public void setParentContainer(java.lang.Object newParentContainer) {
	parentContainer = newParentContainer;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 15:00:05)
 * @param newRadius int
 */
public void setRadius(int newRadius) 
{
  radius = newRadius;
  calculateCircleParams();
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 15:05:26)
 * @param newVisible boolean
 */
public void setVisible(boolean newVisible) {
	visible = newVisible;
}
}
