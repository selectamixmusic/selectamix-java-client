package com.selectamix.client.common.ui.components.mixnetwork;

/**
 * Insert the type's description here.
 * Creation date: (12/08/01 19:42:03)
 * @author: 
 */
 import java.awt.Graphics;
 import java.awt.Color;
 import java.awt.Font;
 import java.util.Hashtable;

 import com.selectamix.client.common.model.businesslogic.SelectableNetworkNodeType;
 import com.selectamix.client.common.model.data.Tune;
 import com.selectamix.client.common.ui.components.button.TuneSelectButton;
 import com.selectamix.client.common.ui.components.button.TunePlayButton;

 import uk.co.robertjdavis.library.graphics.network.Node;
 import uk.co.robertjdavis.library.graphics.textutils.CircleBoundedText;
 import uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource;

 import uk.co.robertjdavis.library.definitions.SelectState;
 
public class TuneGraphic extends Node implements SelectableNetworkNodeType {
	// display data members
	protected int displayCentrePointX = 0;
	protected int displayCentrePointY = 0;

	// Tune appears as a circle & it is assumed that all Tunes have the same radius
	protected static int radius = 65;

	
	private final static int textBoundingMargin = 10;
	private final static int minimumMargin = 20;
	private TuneSelectButton tuneSelectButton = null;

	private static int textStartRelativeY = - (radius - textBoundingMargin); // relative to centre
	private static int textFinishRelativeY = radius - textBoundingMargin;
	private TunePlayButton tunePlayButton = null;
	private java.awt.Color backgroundColour = null;

	private static int buttonRatioToTune = 4; // button is 1/4 size of tune (?)
	private CircleBoundedText circleBoundedText = null;
	private static int textBoundingCircleRadius = radius - textBoundingMargin;
	private static int tuneButtonRadius = textBoundingCircleRadius / buttonRatioToTune;


	private Color fill = null;
	private Color outline = null;
	private Color textColour = null;

	private Hashtable visualAttributesForUnselected = null;
	private Hashtable visualAttributesForSelected = null;
	private Hashtable visualAttributesForUnselectable = null;

	private Hashtable visualAttributesForButton = null;
	
	private GraphicsSource graphicsSource = null;
	private boolean completeRedrawRequired = true;
/**
 * TuneGraphic constructor comment.
 */

// Tune Graphic and Mix Graphic could be generic
// and not dependent on Tune or Mix at all
// Tune Graphic = GraphicObject
// Mix Graphic = GraphicConnector
  
public TuneGraphic( String aTitle, String anArtist, String aMix, int x, int y, Tune tune, TuneSelectButton aTuneSelectButton, TunePlayButton aTunePlayButton ) {
	super();

	displayCentrePointX = x;
	displayCentrePointY = y;

	String stringArray[] = {aTitle,anArtist,aMix};

	circleBoundedText = new CircleBoundedText( stringArray );

	tuneSelectButton = aTuneSelectButton;
	tunePlayButton = aTunePlayButton;
		  
	// initialise tune select button
	tunePlayButton.setVisible( true );
	tuneSelectButton.setRadius( tuneButtonRadius );                     

	// initialise tune play button
	tunePlayButton.setRadius( tuneButtonRadius ); 

	setCoords( x, y );

	object = tune;
}
public void draw(Graphics g) 
{
   int textBoundingCircleRadius = radius - textBoundingMargin;

  circleBoundedText.
  preDrawText( g,    // the display device
		       textBoundingCircleRadius, // the radius of the circle bounding the text
		       /*displayCentrePointY + */ textStartRelativeY, // the y pos of where the text will start
		       /*displayCentrePointY + */ textFinishRelativeY, // the y pos of where the text will finish
		       displayCentrePointX,
		       displayCentrePointY); 


  // (do arcs later)

	g.setColor( fill );
	g.fillOval( displayCentrePointX - radius, displayCentrePointY - radius, radius * 2, radius * 2 );

	g.setColor( outline );
	g.drawOval( displayCentrePointX - radius, displayCentrePointY - radius, radius * 2, radius * 2 );
	
	tuneSelectButton.reDrawRequired();
	tuneSelectButton.draw( g );

	tunePlayButton.reDrawRequired();
	tunePlayButton.draw( g );

	g.setColor( textColour );
	circleBoundedText.drawText2( g, radius - textBoundingMargin, displayCentrePointX, displayCentrePointY, textStartRelativeY );
}
	public int getDisplayCentrePointX() { return displayCentrePointX; }
	public int getDisplayCentrePointY() { return displayCentrePointY; }
/**
 * Insert the method's description here.
 * Creation date: (25/08/00 11:03:00)
 * @return int
 */
public final static int getMinimumMargin() {
	return minimumMargin;
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 21:04:20)
 * @return uicomponents.MixGraphic
 * @param i int
 */
public MixGraphic getMixGraphic(int i) {
	return (MixGraphic) super.getNodeConnection( i );
}
/**
 * Insert the method's description here.
 * Creation date: (12/08/01 20:57:41)
 * @return int
 */
public int getNumberOfMixGraphics() {
	return super.getNumberOfNodeConnections();
}
/**
 * Insert the method's description here.
 * Creation date: (20/04/01 16:23:24)
 * @return int
 */
static public int getRadius() {
	return radius;
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 21:50:12)
 * @return database.Tune
 */
public Tune getTune() {
	return (Tune)object;
}
/**
 * Insert the method's description here.
 * Creation date: (25/02/01 20:57:05)
 * @return int
 */
public static int getTuneButtonRadius() {

	return tuneButtonRadius;
}
/**
 * Insert the method's description here.
 * Creation date: (06/01/01 18:18:31)
 * @return userinterface.TunePlayButton
 */
public TunePlayButton getTunePlayButton() {
	return tunePlayButton;
}
/**
 * Insert the method's description here.
 * Creation date: (18/11/00 16:35:00)
 * @return userinterface.TuneSelectButton
 */
public TuneSelectButton getTuneSelectButton() {
	return tuneSelectButton;
}
/**
 * Insert the method's description here.
 * Creation date: (26/10/01 14:47:36)
 * @return boolean
 */
public boolean isCompleteRedrawRequired() {
	return completeRedrawRequired;
}
/**
 * makeSelectedButDeselectable method comment.
 */
public void makeSelectedButDeselectable() 
{
  Graphics g = graphicsSource.getGraphics();
  setCurrentAppearance( visualAttributesForSelected );
  tuneSelectButton.setVisualAttributes( visualAttributesForButton );
  tuneSelectButton.setSelectState( SelectState.SELECTED );
  tuneSelectButton.setVisible( true );
  tunePlayButton.setVisualAttributes( visualAttributesForButton );
  draw( g );
  redrawMixGraphics( g );
}
/**
 * makeSelectedButNotDeselectable method comment.
 */
public void makeSelectedButNotDeselectable() 
{
  Graphics g = graphicsSource.getGraphics();
  // tune graphic already drawn as selected
  setCurrentAppearance( visualAttributesForSelected );
  // so just need to make unselectable by hiding select button
  tuneSelectButton.setVisualAttributes( visualAttributesForButton );
  tuneSelectButton.setSelectState( SelectState.UNSELECTABLE );
  tuneSelectButton.setVisible( false );
  if ( completeRedrawRequired ) 
  {
	draw( g );
	redrawMixGraphics( g );
  }
  else
  {
	tuneSelectButton.hide( g );
  }
}
/**
 * makeUnselectedButNotSelectable method comment.
 */
public void makeUnselectedButNotSelectable() 
{
  // tune graphic already drawn as unselected
  Graphics g = graphicsSource.getGraphics();
  setCurrentAppearance( visualAttributesForUnselected );
  // so just need to make unselectable by hiding select button
  tuneSelectButton.setVisualAttributes( visualAttributesForButton );
  tuneSelectButton.setSelectState( SelectState.UNSELECTABLE );
  tuneSelectButton.setVisible( false );
  if ( completeRedrawRequired )
  {
	draw( g );
	redrawMixGraphics( g );
  }
  else
  {
	tuneSelectButton.hide( g );
  }
}
/**
 * makeUnselectedButSelectable method comment.
 */
public void makeUnselectedButSelectable() 
{
  Graphics g = graphicsSource.getGraphics();
  setCurrentAppearance( visualAttributesForUnselected );
  tuneSelectButton.setSelectState( SelectState.UNSELECTED );
  tuneSelectButton.setVisible( true );
  tuneSelectButton.setVisualAttributes( visualAttributesForButton );
  tunePlayButton.setVisualAttributes( visualAttributesForButton );
  draw( g );
  redrawMixGraphics( g );
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 16:08:50)
 * @param tuneGraphic com.selectamix.client.common.ui.components.mixnetwork.TuneGraphic
 */
private void redrawMixGraphics( Graphics g ) 
{
  // re-draw object connections connected to object graphic
  int numberOfMixGraphics = getNumberOfMixGraphics();
  for ( int i = 0; i < numberOfMixGraphics; i++ )
  {
	(getMixGraphic( i )).draw( g );
  }
}
/**
 * Insert the method's description here.
 * Creation date: (26/10/01 14:47:36)
 * @param newCompleteRedrawRequired boolean
 */
public void setCompleteRedrawRequired(boolean newCompleteRedrawRequired) {
	completeRedrawRequired = newCompleteRedrawRequired;
}
/**
 * Insert the method's description here.
 * Creation date: (16/06/01 23:42:53)
 * @param x int
 * @param y int
 */
public void setCoords(int x, int y) 
{
  displayCentrePointX = x;
  displayCentrePointY = y;
	
  // for use with initialising tune select and play buttons
  int offset = ( new Double( Math.sqrt( (new Double( ((textBoundingCircleRadius - tuneButtonRadius)*(textBoundingCircleRadius - tuneButtonRadius)) / 2
	                                                 )
	                                      ).doubleValue()
	                                    )
	                         )
	             ).intValue() - 1;
					
  tuneSelectButton.setCentreX( displayCentrePointX - offset );
  tuneSelectButton.setCentreY( displayCentrePointY + offset );

  tunePlayButton.setCentreX( displayCentrePointX + offset );
  tunePlayButton.setCentreY( displayCentrePointY + offset );
}
/**
 * Insert the method's description here.
 * Creation date: (26/10/01 11:31:47)
 */
private void setCurrentAppearance( Hashtable currentAppearance ) 
{
  textColour = (Color) currentAppearance.get( "text" );
  outline = (Color) currentAppearance.get( "ol" );
  fill = (Color) currentAppearance.get( "fl" );
  visualAttributesForButton = (Hashtable) currentAppearance.get( "button" );
  Hashtable fontsList = (Hashtable)currentAppearance.get( "tuneFonts" );
  Font[] fonts = { (Font)fontsList.get( "title" ), (Font)fontsList.get( "artist" ), (Font)fontsList.get( "mix" ) };
  circleBoundedText.setFonts( fonts );
}
/**
 * Insert the method's description here.
 * Creation date: (26/10/01 11:22:03)
 * @param newGraphicsSource uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource
 */
public void setGraphicsSource(uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource newGraphicsSource) {
	graphicsSource = newGraphicsSource;
}
/**
 * Insert the method's description here.
 * Creation date: (20/04/01 16:23:24)
 * @param newRadius int
 */
public static void setRadius(int newRadius) {
	radius = newRadius;
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/01 21:15:37)
 * @param newScheme java.util.Hashtable
 */
public void setVisualAttributes(Hashtable visualAttributes ) 
{
  visualAttributesForSelected = (Hashtable) visualAttributes.get( "selected" );
  visualAttributesForUnselected = (Hashtable) visualAttributes.get( "unselected" );
  //visualAttributesForUnselectable = (Hashtable) tuneVisualAttributes.get( "unselectable" );
}
}
