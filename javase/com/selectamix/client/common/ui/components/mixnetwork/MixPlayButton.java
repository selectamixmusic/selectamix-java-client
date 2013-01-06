package com.selectamix.client.common.ui.components.mixnetwork;

/**
 * Insert the type's description here.
 * Creation date: (12/08/01 17:01:51)
 * @author: 
 */
import uk.co.robertjdavis.library.graphics.vector.TransformablePolygon;

import java.awt.Graphics;

import uk.co.robertjdavis.library.graphics.network.NodeConnectionEdge;
import uk.co.robertjdavis.library.graphics.network.Node;
import uk.co.robertjdavis.library.definitions.Direction;

import java.util.Hashtable;
import java.awt.Color;
 
public class MixPlayButton extends NodeConnectionEdge {
	private TransformablePolygon pointerOverShape = null;
	private TransformablePolygon shape = null;

	  private Color background = null;
  
  private Color pointerOffFill = null;
  private Color pointerOffOutline = null;
  private Color pointerOffSymbolFill = null;
  private Color pointerOffSymbolOutline = null;
  
  private Color pointerOnFill = null;
  private Color pointerOnOutline = null;
  private Color pointerOnSymbolFill = null;
	private Color pressedSymbolFill = null;
  private Color pointerOnSymbolOutline = null;
	private Color pressedSymbolOutline = null;
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 17:02:39)
 */
public MixPlayButton( TuneGraphic tuneGraphic, int aDirection ) 
{
  super( tuneGraphic, aDirection );
  int xpoints[] = { -10, 10, 0 };
  int ypoints[] = {  -10, -10, 10 };
  shape = new TransformablePolygon( xpoints, ypoints, xpoints.length ); 
		
  int pointerOverShapeXPoints[] = { -6, 6, 0 };
  int pointerOverShapeYPoints[] = { -6, -6, 6 };
  pointerOverShape = new TransformablePolygon( pointerOverShapeXPoints, pointerOverShapeYPoints, 3 );
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 21:57:20)
 * @param g java.awt.Graphics
 */
public void draw(Graphics g) 
{
  g.setColor( pointerOffFill );
  g.fillPolygon( shape );
  g.setColor( pointerOffOutline );
  g.drawPolygon( shape );
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 20:41:23)
 * @return uicomponents.TuneGraphic
 */
public TuneGraphic getTuneGraphic() {
	return (TuneGraphic)node;
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 20:27:45)
 * @return boolean
 * @param x int
 * @param y int
 */
public boolean isInside(int x, int y) {
	return shape.contains( x, y);
}
/**
 * pointerMovedOff method comment.
 */
public void pointerMovedOff(java.awt.Graphics g) 
{
  g.setColor( pointerOffSymbolFill );
  g.fillPolygon( pointerOverShape );
  g.setColor( pointerOffSymbolOutline );
  g.drawPolygon( pointerOverShape );
}
/**
 * pointerMovedOn method comment.
 */
public void pointerMovedOn(java.awt.Graphics g) 
{
  g.setColor( pointerOnSymbolFill );
  g.fillPolygon( pointerOverShape );
  g.setColor( pointerOnSymbolOutline );
  g.drawPolygon( pointerOverShape );
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 16:53:04)
 * @param g java.awt.Graphics
 */
public void pointerPressed(Graphics g) 
{
  g.setColor( pressedSymbolFill );
  g.fillPolygon( pointerOverShape );
  g.setColor( pressedSymbolOutline );
  g.drawPolygon( pointerOverShape );
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 16:53:04)
 * @param g java.awt.Graphics
 */
public void pointerReleased(Graphics g) 
{
  g.setColor( pointerOnFill );
  g.fillPolygon( pointerOverShape );
  g.setColor( pointerOnSymbolOutline );
  g.drawPolygon( pointerOverShape );
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 17:05:11)
 */
public void preDraw( double angle ) 
{
  // PRE DRAW - work out co-ords of ObjectConnectionGraphic first before drawing
  // assume Polygon appearance indicates it facing inwards

  // set up pointer over shape
	
  // work out positioning
	
  // flip vertically the polygon depending on incoming or outgoing direction
  // (will need to also consider bi-directional ObjectConnectionGraphic objects in future)
  // polygon is facing inwards, so only need to flip if outgoing
  if ( direction == Direction.OUT )
  {
	pointerOverShape.flipVertically();
  }
	
  // shift north by ObjectGraphic radius
  pointerOverShape.translate( 0, -(getTuneGraphic().getRadius()) );
	
  // rotate about (0,0) by angle
  pointerOverShape.rotate( 0, 0, angle );
	
  // shift based on object graphic centrex, centrey
  pointerOverShape.translate( getTuneGraphic().getDisplayCentrePointX(), getTuneGraphic().getDisplayCentrePointY() );


  // PRE DRAW - work out co-ords of ObjectConnectionGraphic first before drawing

	// assume ObjectConnectionGraphic Polygon appearance indicates it facing inwards
	
	// work out positioning
	
	
	// flip vertically the polygon depending on incoming or outgoing direction
	// (will need to also consider bi-directional ObjectConnectionGraphic objects in future)
	// polygon is facing inwards, so only need to flip if outgoing
	if ( direction == Direction.OUT )
	{
		shape.flipVertically();
	}
	
	// shift north by ObjectGraphic radius
	shape.translate( 0, -(getTuneGraphic().getRadius()) );
	
	// rotate about (0,0) by angle
	shape.rotate( 0, 0, angle );
	
	// shift based on object graphic centrex, centrey
	shape.translate( getTuneGraphic().getDisplayCentrePointX(), getTuneGraphic().getDisplayCentrePointY() );

	// do we need to do this:
	centreX = shape.getCentreX(); centreY = shape.getCentreY();
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 17:37:37)
 * @param newCentreX int
 */
public void setCentreX(int newCentreX) 
{
  centreX = newCentreX;
  pointerOverShape.absoluteShift( newCentreX, centreY );
  shape.absoluteShift( newCentreX, centreY );
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 17:37:37)
 * @param newCentreY int
 */
public void setCentreY(int newCentreY) 
{
  centreY = newCentreY;
  pointerOverShape.absoluteShift( centreX, newCentreY );
  shape.absoluteShift( centreX, newCentreY );
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/01 19:10:42)
 * @param visualAttributes java.util.Hashtable
 */
public void setVisualAttributes(Hashtable visualAttributes) 
{
  background = (Color) visualAttributes.get( "bkgnd" );
  pointerOffFill = (Color) visualAttributes.get( "ptrOffFl" );
  pointerOffOutline = (Color) visualAttributes.get( "ptrOffOl" );
  pointerOffSymbolOutline = (Color) visualAttributes.get( "ptrOffSymOl" );
  pointerOffSymbolFill = (Color) visualAttributes.get( "ptrOffSymFl" );
  pointerOnFill = (Color) visualAttributes.get( "ptrOnSymFl" );    
  pointerOnOutline = (Color) visualAttributes.get( "ptrOnSymOl" ); 
  pointerOnSymbolOutline = (Color) visualAttributes.get( "ptrOnSymOl" );  
  pointerOnSymbolFill = (Color) visualAttributes.get( "ptrOnSymFl" );
  pressedSymbolOutline = (Color) visualAttributes.get( "pressedSymOl" );
  pressedSymbolFill = (Color) visualAttributes.get( "pressedSymFl" );
}
}
