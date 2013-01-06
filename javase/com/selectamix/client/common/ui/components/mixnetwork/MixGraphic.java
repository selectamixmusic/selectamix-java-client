package com.selectamix.client.common.ui.components.mixnetwork;

/**
 * Insert the type's description here.
 * Creation date: (12/08/01 17:25:56)
 * @author: 
 */
 
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;

import java.util.Hashtable;

import uk.co.robertjdavis.library.graphics.network.NodeConnection;
import uk.co.robertjdavis.library.graphics.network.Node;
import uk.co.robertjdavis.library.ui.controller.VectorUserInterfaceType;
import uk.co.robertjdavis.library.graphics.vector.TransformablePolygon;
import uk.co.robertjdavis.library.maths.TanTable;
import uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource;

import com.selectamix.client.common.model.data.Mix;

import com.selectamix.client.common.model.businesslogic.SelectableNodeConnectionType;

public class MixGraphic extends NodeConnection implements VectorUserInterfaceType, SelectableNodeConnectionType {
	private MixPlayButton mixPlayButton1 = null;
	private MixPlayButton mixPlayButton2 = null;
	private TransformablePolygon shape = null;
	private TransformablePolygon line = null;
	private Color fill = null;
	private Color outline = null;

	private Hashtable visualAttributesForUnselected = null;
	private Hashtable visualAttributesForSelected = null;
	private uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource graphicsSource = null;
/**
 * MixGraphic constructor comment.
 * @param aNode1 networkgraphics.Node
 * @param aNode2 networkgraphics.Node
 * @param object java.lang.Object
 * @param theSelectState int
 * @param theDirection int
 */
public MixGraphic(TuneGraphic tuneGraphic1, TuneGraphic tuneGraphic2, Mix mix, int theSelectState, int theDirection) {
	super(tuneGraphic1, tuneGraphic2, mix, theDirection);

	mixPlayButton1 = new MixPlayButton( tuneGraphic1, nodeConnectionEdgeDirection1 );
	nodeConnectionEdge1 = mixPlayButton1;
	mixPlayButton2 = new MixPlayButton( tuneGraphic2, nodeConnectionEdgeDirection2 );
	nodeConnectionEdge2 = mixPlayButton2;

}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 21:07:37)
 * @param g java.awt.Graphics
 */
public void draw(Graphics g) 
{
  g.setColor( fill );
  g.fillPolygon( line );
  g.setColor( fill );
  g.drawPolygon( line );
 
  mixPlayButton1.draw( g );
  mixPlayButton2.draw( g );
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 20:50:25)
 * @return int
 */
static public int getDistanceBetweenTunes() {
	return distanceBetweenNodes;
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 21:32:49)
 * @return database.Mix
 */
public Mix getMix() {
	return (Mix)object;
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 21:06:14)
 * @return uicomponents.TuneGraphic
 * @param tuneGraphic uicomponents.TuneGraphic
 */
public TuneGraphic getOtherTuneGraphic(TuneGraphic tuneGraphic) {
	return (TuneGraphic) super.getOtherNode( (Node)tuneGraphic );
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
 * isInside method comment.
 */
public boolean isInside(int x, int y) 
{
	return (    mixPlayButton1.isInside( x, y )
		     || mixPlayButton2.isInside( x, y )
	       );
}
/**
 * makeSelected method comment.
 */
public void makeSelected() 
{
  setCurrentAppearance( visualAttributesForSelected );
  draw( graphicsSource.getGraphics() );
}
/**
 * makeUnselectable method comment.
 */
public void makeUnselectable() {}
/**
 * makeUnselected method comment.
 */
public void makeUnselected() 
{
  setCurrentAppearance( visualAttributesForUnselected );
  draw( graphicsSource.getGraphics() );
}
/**
 * drawStatePointerMovedOff method comment.
 */
public void pointerMovedOff(Graphics g) 
{
  mixPlayButton1.pointerMovedOff( g );
  mixPlayButton2.pointerMovedOff( g );
}
/**
 * drawStatePointerMovedOn method comment.
 */
public void pointerMovedOn(java.awt.Graphics g) 
{
  mixPlayButton1.pointerMovedOn( g );
  mixPlayButton2.pointerMovedOn( g );
}
/**
 * pointerPressed method comment.
 */
public void pointerPressed(java.awt.Graphics g) 
{
  mixPlayButton1.pointerPressed( g );
  mixPlayButton2.pointerPressed( g );
}
/**
 * pointerReleased method comment.
 */
public void pointerReleased(java.awt.Graphics g) 
{
  mixPlayButton1.pointerReleased( g );
  mixPlayButton2.pointerReleased( g );
}
/**
 * Insert the method's description here.
 * Creation date: (23/07/00 11:49:36)
 */
public void preDraw() 
{
	// get each object graphic at either end of this object connection graphic
	TuneGraphic tuneGraphic1 = mixPlayButton1.getTuneGraphic();
	TuneGraphic tuneGraphic2 = mixPlayButton2.getTuneGraphic();

	// work out angles of object connection graphic objects based on
	// co-ords of object graphic at either end

	double angle1 = TanTable.getAngle( tuneGraphic1.getDisplayCentrePointX(), 
		                               tuneGraphic1.getDisplayCentrePointY(),
		                               tuneGraphic2.getDisplayCentrePointX(), 
		                               tuneGraphic2.getDisplayCentrePointY() );
	
	double angle2 = TanTable.getAngle( tuneGraphic2.getDisplayCentrePointX(), 
		                               tuneGraphic2.getDisplayCentrePointY(),
		                               tuneGraphic1.getDisplayCentrePointX(), 
		                               tuneGraphic1.getDisplayCentrePointY() );

	mixPlayButton1.preDraw( angle1 );
	mixPlayButton2.preDraw( angle2 );

	// now work out connecting line
	// use the polygon object to hold line information
	// not ideal, especially if come to use multi-point line
	// but will do for now

	// first work out the distance between the 2 object graphics,
	// using pythagoras
	int adj = tuneGraphic2.getDisplayCentrePointX() - tuneGraphic1.getDisplayCentrePointX();
	int opp = tuneGraphic2.getDisplayCentrePointY() - tuneGraphic1.getDisplayCentrePointY();
	int hyp = ( new Double(Math.sqrt(   (new Integer(adj*adj)).doubleValue() 
		                              + (new Integer(opp*opp)).doubleValue()
	                                ) 
	                      )
	          ).intValue();
	
	/*
	int xpoints[] = { 0, 0 };
	int ypoints[] = { -( TuneGraphic.getRadius() + 10 ), -( hyp - TuneGraphic.getRadius() - 10) };
	*/

	int connectorWidth = 4;
	int offsetFromConnector = 6;
	
	int xpoints[] = { -connectorWidth/2,
		              -connectorWidth/2, 
		               connectorWidth/2,
		               connectorWidth/2
					};
	int ypoints[] = { -( TuneGraphic.getRadius() + offsetFromConnector ), 
		              -( hyp - TuneGraphic.getRadius() - offsetFromConnector),
		              -( hyp - TuneGraphic.getRadius() - offsetFromConnector),
		              -( TuneGraphic.getRadius() + offsetFromConnector )
					};
	
	line = new TransformablePolygon( new Polygon( xpoints, ypoints, 4 ) );
	line.rotate( 0, 0, angle1 );
	line.translate( tuneGraphic1.getDisplayCentrePointX(), tuneGraphic1.getDisplayCentrePointY() );
}
/**
 * Insert the method's description here.
 * Creation date: (26/10/01 20:23:28)
 * @param visualAttributes java.util.Hashtable
 */
private void setCurrentAppearance(Hashtable newVisualAttributes) 
{
  fill = (Color) newVisualAttributes.get( "fl" );
  outline = (Color) newVisualAttributes.get( "ol" );

  Hashtable mixPlayButtonVisualAttributes = (Hashtable) newVisualAttributes.get( "mixPlayButton" );
  
  mixPlayButton1.setVisualAttributes( mixPlayButtonVisualAttributes );
  mixPlayButton2.setVisualAttributes( mixPlayButtonVisualAttributes );
}
/**
 * Insert the method's description here.
 * Creation date: (26/10/01 20:29:09)
 * @param newGraphicsSource uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource
 */
public void setGraphicsSource(uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource newGraphicsSource) {
	graphicsSource = newGraphicsSource;
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/01 20:55:00)
 * @param newScheme java.util.Hashtable
 */
public void setVisualAttributes(Hashtable newVisualAttributes) 
{	
  visualAttributesForUnselected = (Hashtable)newVisualAttributes.get( "unselected" );
  visualAttributesForSelected = (Hashtable)newVisualAttributes.get( "selected" );

  /*
  fill = (Color) newVisualAttributes.get( "fl" );
  outline = (Color) newVisualAttributes.get( "ol" );

  Hashtable mixPlayButtonVisualAttributes = (Hashtable) newVisualAttributes.get( "mixPlayButton" );
  
  mixPlayButton1.setVisualAttributes( mixPlayButtonVisualAttributes );
  mixPlayButton2.setVisualAttributes( mixPlayButtonVisualAttributes );
  */
}
/**
 * Insert the method's description here.
 * Creation date: (09/07/01 21:26:15)
 * @param x int
 * @param y int
 */
public void shift(int offsetx, int offsety) 
{
  mixPlayButton1.setCentreX( mixPlayButton1.getCentreX() + offsetx );
  mixPlayButton1.setCentreY( mixPlayButton1.getCentreY() + offsety );

  mixPlayButton2.setCentreX( mixPlayButton2.getCentreX() + offsetx );
  mixPlayButton2.setCentreY( mixPlayButton2.getCentreY() + offsety );

  line.translate( offsetx, offsety );
}
}
