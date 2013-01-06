package com.selectamix.client.common.ui.components;

/**
 * Insert the type's description here.
 * Creation date: (03/09/01 15:45:26)
 * @author: 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

import java.util.Observer;
import java.util.Hashtable;

import uk.co.robertjdavis.library.ui.controller.VectorUserInterfaceManager;
import uk.co.robertjdavis.library.ui.controller.VectorUserInterfaceType;

import com.selectamix.client.common.ui.components.button.TunePlayButton;

import com.selectamix.client.common.audiologic.PlayingTimeLimits;
import uk.co.robertjdavis.library.graphics.heading.Heading;

import uk.co.robertjdavis.library.graphics.basicadapters.Drawable;
import uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource;
import uk.co.robertjdavis.library.ui.adapters.GraphicsBasedUI;

import com.selectamix.client.common.audiologic.AudioPlayer;

import com.selectamix.client.common.model.businesslogic.MixNetworkSelectionController;

public class MixListUIStatusBar implements GraphicsBasedUI, Observer
{
	private java.awt.Dimension preferredSize = new Dimension( 330, 40 );
	private PlayingTimeLimits playingTimeLimits = null;
	private GraphicsSource graphicsSource = null;
	private int bodyDrawingStartY = 0;
	private int bracketHeight = 0;
	private int marginLeft = 3;
	private boolean preDrawn = false;

	private String textMixReady = "Mix Ready!";
	private String textKeepSelectingLine1 = "Keep";
	private String textKeepSelectingLine2 = "Selecting!";

	private Heading heading = null;
	private int bodyHeight = 0;
	private int bracketGap = 0;
	private int bracketEdgeWidth = 4;
	private int marginBetweenTextAndBracket = 3;
	private int marginSelectionStats = 0;
	private int marginBetweenHeadingAndBody = 3;
	private int fontHeight = 0;
	private TunePlayButton playMixButton = null;
	private VectorUserInterfaceManager vectorUserInterfaceManager = null;
	private java.awt.Color background = null;
	private java.awt.Color textOnBackground = null;
	private java.awt.Color outlineOnBackground = null;
	private AudioPlayer audioPlayer = null;
	private MixNetworkSelectionController selectionController = null;
	private java.awt.Font generalFont = null;
/**
 * TitleMixListUI constructor comment.
 */
public MixListUIStatusBar( PlayingTimeLimits ptl, MixNetworkSelectionController mixNetworkSelectionController, AudioPlayer anAudioPlayer, int width, int headingHeight, int heightOfBody ) 
{
  super();
  playingTimeLimits = ptl;
  heading = new Heading( "Tune Selection Status", Heading.CENTRE, width, headingHeight );
  bodyHeight = heightOfBody;
  preferredSize.width = width;
  preferredSize.height = headingHeight + bodyHeight;

  
  playMixButton = new TunePlayButton( null, null );
  vectorUserInterfaceManager = new VectorUserInterfaceManager();
  vectorUserInterfaceManager.add( playMixButton );

  selectionController = mixNetworkSelectionController;

  audioPlayer = anAudioPlayer;
}
/**
 * Insert the method's description here.
 * Creation date: (03/09/01 15:52:55)
 * @param g java.awt.Graphics
 */
public void draw(Graphics g) 
{
  if (!preDrawn) preDraw( g );
 
  g.setColor( background );
  g.fillRect( 0, bodyDrawingStartY, preferredSize.width, bodyHeight );
   
  heading.draw( g );
 
  updateTuneSelectionStatus();
}
/**
 * getPreferredSize method comment.
 */
public java.awt.Dimension getSize() {
	return preferredSize;
}
/**
 * pointerEntered method comment.
 */
public void pointerEntered(int x, int y, java.awt.Graphics g) 
{
  vectorUserInterfaceManager.pointerEntered( x, y, g );
}
/**
 * pointerExited method comment.
 */
public void pointerExited(int x, int y, java.awt.Graphics g) 
{
  vectorUserInterfaceManager.pointerExited( x, y, g );
}
/**
 * Insert the method's description here.
 * Creation date: (14/08/01 18:16:14)
 * @param e userinterface.PointerEvent
 */
public void pointerMoved(int x, int y, Graphics g ) 
{
  VectorUserInterfaceType vuit = vectorUserInterfaceManager.pointerMoved( x, y, g );
}
/**
 * Insert the method's description here.
 * Creation date: (14/08/01 18:16:14)
 * @param e userinterface.PointerEvent
 */
public void pointerPressed(int x, int y, Graphics g ) 
{
  VectorUserInterfaceType vuit = vectorUserInterfaceManager.pointerPressed( x, y, g );
  if ( vuit instanceof TunePlayButton )
  {
	audioPlayer.playMix( selectionController.getMixRoute() );
  }
}
/**
 * Insert the method's description here.
 * Creation date: (14/08/01 18:16:14)
 * @param e userinterface.PointerEvent
 */
public void pointerReleased(int x, int y, Graphics g ) 
{
  VectorUserInterfaceType vuit = vectorUserInterfaceManager.pointerReleased( x, y, g );
}
/**
 * Insert the method's description here.
 * Creation date: (11/09/01 14:51:39)
 * @param g java.awt.Graphics
 */
private void preDraw(Graphics g) 
{
  bodyDrawingStartY = (heading.getSize().height) + marginBetweenHeadingAndBody;
  
  FontMetrics fontMetrics = g.getFontMetrics();

  fontHeight = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
  
  bracketHeight = fontHeight * 2;

  // work out gap between brackets so it is wide enough to fit the text in.
  int w1 = fontMetrics.stringWidth( textKeepSelectingLine1 );
  int w2 = fontMetrics.stringWidth( textKeepSelectingLine2 );
  int w3 = fontMetrics.stringWidth( textMixReady );
  
  bracketGap = w1;
  if ( w2 > bracketGap ) bracketGap = w2;
  if ( w3 < bracketGap ) bracketGap = w3;
  
  marginSelectionStats = marginLeft + bracketEdgeWidth * 2 + bracketGap + marginLeft;  

  playMixButton.setCentreX( marginLeft + bracketGap / 2 );
  playMixButton.setCentreY( bodyDrawingStartY + bracketHeight / 2);
  playMixButton.setRadius( 10 );
  
  preDrawn = true;
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/01 17:06:30)
 * @param newGraphicsSource client.common.ui.adapters.GraphicsSource
 */
public void setGraphicsSource(GraphicsSource newGraphicsSource) {
	graphicsSource = newGraphicsSource;
}
/**
 * setSize method comment.
 */
public void setViewableArea(int x, int y, int w, int d, Graphics g ) 
{
  preferredSize.width = w;
  heading.setViewableArea( x, y, w, d, g );
}
/**
 * update method comment.
 */
public void update(java.util.Observable o, java.lang.Object arg) 
{
  Hashtable newScheme = (Hashtable)arg;
  
  background = (Color) newScheme.get( "bkgnd" );
  textOnBackground = (Color) newScheme.get( "textOnBkgnd" );
  outlineOnBackground = (Color) newScheme.get( "outlineOnBkgnd" );
  playMixButton.setVisualAttributes( (Hashtable)newScheme.get( "mixListPlayButton" ) );
  Hashtable headingVisualAttributes = (Hashtable) newScheme.get( "heading" );
  heading.setAppearance( (Color)headingVisualAttributes.get( "bkgnd" ),
	                     (Color)headingVisualAttributes.get( "fgnd" ),
	                     (Font)headingVisualAttributes.get( "font" ));
  generalFont = (Font) newScheme.get( "generalFont" );
}
/**
 * Insert the method's description here.
 * Creation date: (03/09/01 16:02:57)
 */
public void updateTuneSelectionStatus() 
{
  int x = marginSelectionStats; 
	
  // draw tunes left information text
  if ( graphicsSource == null ) return;
 
  Graphics g = graphicsSource.getGraphics();

  // need to blank out information, so work out area to blank out
  g.setColor( textOnBackground );
  g.setFont( generalFont );
  FontMetrics fontMetrics = g.getFontMetrics();

  int y = bodyDrawingStartY + fontMetrics.getMaxAscent();
  
  int textDepth = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();

  g.setColor( background );
  
  String tunesSelected = "Number selected: " + playingTimeLimits.getNumTunes();
  int stringWidth = fontMetrics.stringWidth( tunesSelected );

  g.fillRect( x, y - fontMetrics.getMaxAscent(), stringWidth, textDepth );

  g.setColor( background );
  
  String maxTunes = "Number required for mix: " + playingTimeLimits.getMaxTunes();
  stringWidth = fontMetrics.stringWidth( maxTunes );

  g.fillRect( x, y - fontMetrics.getMaxAscent() + textDepth, stringWidth, textDepth );

  g.setColor( textOnBackground );
  g.drawString( tunesSelected, x, y );
  g.drawString( maxTunes, x, y + textDepth );

  g.setColor( background );
  g.fillRect( marginLeft, bodyDrawingStartY, bracketEdgeWidth * 2 + bracketGap + 1, bracketHeight + 1);

  g.setColor( outlineOnBackground );
  
  // [
  g.drawLine( marginLeft, bodyDrawingStartY, marginLeft + bracketEdgeWidth, bodyDrawingStartY );
  g.drawLine( marginLeft, bodyDrawingStartY, marginLeft, bodyDrawingStartY + bracketHeight );
  g.drawLine( marginLeft, bodyDrawingStartY + bracketHeight, marginLeft + bracketEdgeWidth, bodyDrawingStartY + bracketHeight );

  // ]
  g.drawLine( marginLeft + bracketEdgeWidth + bracketGap, bodyDrawingStartY, marginLeft + bracketEdgeWidth * 2 + bracketGap, bodyDrawingStartY );
  g.drawLine( marginLeft + bracketEdgeWidth * 2 + bracketGap, bodyDrawingStartY, marginLeft + bracketEdgeWidth * 2 + bracketGap, bodyDrawingStartY + bracketHeight );
  g.drawLine( marginLeft + bracketEdgeWidth + bracketGap, bodyDrawingStartY + bracketHeight, marginLeft + bracketEdgeWidth * 2 + bracketGap, bodyDrawingStartY + bracketHeight ); 
 


  // is mix playable?
  if ( playingTimeLimits.isValid() )
  {
	// yes
	g.setColor( background );
	g.fillRect( marginLeft + bracketEdgeWidth, bodyDrawingStartY, bracketGap, bracketHeight + 1);
	
	playMixButton.setVisible( true );
	playMixButton.reDrawRequired();
	playMixButton.draw( g );
  }
  else
  {
	// no
	playMixButton.hide( g );
	
	g.setColor( background );
	g.fillRect( marginLeft + bracketEdgeWidth, bodyDrawingStartY, bracketGap, bracketHeight + 1);

	g.setColor( textOnBackground );
	g.drawString( textKeepSelectingLine1, marginLeft + bracketEdgeWidth + (bracketGap - fontMetrics.stringWidth( textKeepSelectingLine1 )) / 2, bodyDrawingStartY + fontMetrics.getMaxAscent() );
	g.drawString( textKeepSelectingLine2, marginLeft + bracketEdgeWidth + (bracketGap - fontMetrics.stringWidth( textKeepSelectingLine2 )) / 2, bodyDrawingStartY + fontHeight + fontMetrics.getMaxAscent() );

  }
}
}
