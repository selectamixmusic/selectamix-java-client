package com.selectamix.client.common.ui.components.mixlist;

/**
 * Insert the type's description here.
 * Creation date: (11/02/01 15:29:47)
 * @author: 
 */
import java.util.Vector;
 
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.FontMetrics;



import uk.co.robertjdavis.library.ui.controller.VectorUserInterfaceType;
import uk.co.robertjdavis.library.ui.controller.VectorUserInterfaceManager;
import com.selectamix.client.common.ui.components.button.TunePlayButton;
import com.selectamix.client.common.ui.components.MixListUIStatusBar;

import com.selectamix.client.common.model.data.Tune;

import com.selectamix.client.common.ui.components.mixnetwork.TuneGraphic;

import java.util.Hashtable;
import java.util.Observer;

import com.selectamix.client.common.audiologic.PlayingTimeLimits;

import uk.co.robertjdavis.library.ui.adapters.ScrollableUserInterface;

import uk.co.robertjdavis.library.ui.awt.graphicscontainers.ScrollableUIComponent;

import com.selectamix.client.common.audiologic.AudioPlayer;

public class MixListUserInterface implements ScrollableUserInterface, Observer 
{
  private int topLeftX = 0;
  private int topLeftY = 0;

  private int width = 0;
  private int depth = 0;
  private int marginX = 0;
  private int marginY = 0;

  private int currentOffsetX = 0;
  private int currentOffsetY = 0;
  
  private int gapBetweenItems = 5;
	
  private Vector list = null;
  
  private final int maxNumberOfTunes = 4;

  
  private PlayingTimeLimits playingTimeLimits = null;
	
  private VectorUserInterfaceManager vectorUserInterfaceManager = null;
  private ScrollableUIComponent parentContainer = null;
	public boolean preDrawn = false;
	private AudioPlayer audioPlayer = null;
	private java.awt.Dimension preferredSize = new Dimension( 330, 530 );
	private MixListUIStatusBar statusBar = null;
	private java.util.Hashtable buttonColourScheme = null;
	private java.awt.Color background = null;
	private boolean colourChanged = false;
	private java.util.Hashtable mixColourScheme = null;
	private java.util.Hashtable tuneFonts = null;
/**
 * MixListUserInterface constructor comment.
 */
public MixListUserInterface( int x, int y, int w, int d, int mx, int my, AudioPlayer ap, PlayingTimeLimits ptl, MixListUIStatusBar mixListUIStatusBar ) 
{
  // should comment this better using text graphics to illustrate
  super();
  
  list = new Vector();

  topLeftX = x; topLeftY = y;
  depth = d; width = w;
  marginX = mx; marginY = my;
	

  // set the depth and width of all mix list items
  MixListItem.setMaxWidth( 100 );
  MixListItem.setDepth( TuneGraphic.getTuneButtonRadius() * 2 );

  playingTimeLimits = ptl;

  vectorUserInterfaceManager = new VectorUserInterfaceManager();
  
  preDrawn = false;

  audioPlayer = ap;

  statusBar = mixListUIStatusBar;

  MixListItem.setWidth( 200 );
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:02:20)
 * @param mixListItem MixListItem
 */
public void addTune(Tune newTune ) 
{
  Graphics graphics = parentContainer.getGraphics();
	
  int y = topLeftY + marginY + list.size() * MixListItem.getDepth() + ( list.size() - 1 ) * gapBetweenItems - currentOffsetY;

  int r = TuneGraphic.getTuneButtonRadius();
  
  // add to list
  TunePlayButton tunePlayButton = new TunePlayButton( newTune, buttonColourScheme );
  vectorUserInterfaceManager.add( tunePlayButton );
  MixListItem listItem = new MixListItem( newTune, list.size() + 1, tunePlayButton, topLeftX + marginX - currentOffsetX, y );
  listItem.setVisualAttributes( mixColourScheme );
  list.addElement( listItem );
  
  // draw new last item with tune select button
  tunePlayButton.setVisible( true );
  listItem.draw( graphics );

  statusBar.updateTuneSelectionStatus();
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:02:58)
 */
public void deleteTune() 
{
  Graphics graphics = parentContainer.getGraphics();
	
  int endOfList = list.size() - 1;

  // don't run this code if there are no items in list
  // - nothing to delete
  if (endOfList >= 0 )
  {
	// blank out item on screen
	MixListItem item = (MixListItem) list.elementAt( endOfList );
	graphics.setColor( background );
	graphics.fillRect( item.getTopLeftX(), item.getTopLeftY(), item.getWidth() + 1, item.getDepth() + 1);

	// remove button from ui manager
	vectorUserInterfaceManager.remove( item.getTunePlayButton() );
		
	// remove last item from list
	list.removeElementAt( endOfList );
  }

  statusBar.updateTuneSelectionStatus();;

}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:46:33)
 */
public void draw( int x, int y, Graphics g ) 
{
  if ( !preDrawn ) preDraw( g ); 
	
  g.setColor( background );
  g.fillRect( 0, 0, width, depth );
  
  // only draw if there are items to draw
  if ( list.size() > 0 )
  {
	int lastItemPosition = list.size();  
	for (int listItemIndex = 0; listItemIndex < lastItemPosition; listItemIndex++ )
	{
	  MixListItem item = (MixListItem)list.elementAt( listItemIndex );
	  item.setTopLeftX( item.getTopLeftX() + x); item.setTopLeftY( item.getTopLeftY() + y );
	  if (colourChanged) item.setVisualAttributes( mixColourScheme );
	  item.draw( g );
	}
  }

  colourChanged = false;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:46:33)
 */
public void draw( Graphics g ) 
{	
  draw( 0, 0, g );
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:18:02)
 * @return int
 */
public int getGapBetweenItems() {
	return gapBetweenItems;
}
/**
 * Insert the method's description here.
 * Creation date: (24/08/01 17:53:18)
 * @return client.pc.uiadapters.ScrollableUIComponent
 */
public ScrollableUIComponent getParentContainer() {
	return parentContainer;
}
/**
 * getPreferredSize method comment.
 */
public java.awt.Dimension getSize() 
{
  preferredSize.width = MixListItem.getWidth() + marginX * 2;
  int maxTunes = playingTimeLimits.getMaxTunes();
  preferredSize.height = maxTunes * MixListItem.getDepth() + gapBetweenItems * ( maxTunes - 1 ) + marginY * 2;
	
	return preferredSize;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:14:26)
 * @return int
 */
public int getTopLeftX() {
	return topLeftX;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:14:47)
 * @return int
 */
public int getTopLeftY() {
	return topLeftY;
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
	audioPlayer.playTuneClip( ((Tune)((TunePlayButton)vuit).getParentContainer()) );
  }
}
/**
 * pointerReleased method comment.
 */
public void pointerReleased(int x, int y, java.awt.Graphics g) 
{
  VectorUserInterfaceType vuit = vectorUserInterfaceManager.pointerReleased( x, y, g );
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 18:56:31)
 * @param g java.awt.Graphics
 */
public void preDraw(Graphics g) 
{
  int mixListDepth = MixListItem.getTextTopMargin();
  
  g.setFont( (Font)tuneFonts.get( "title" ) );
  FontMetrics fontMetrics = g.getFontMetrics();
  mixListDepth = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
  
  mixListDepth += MixListItem.getGapBetweenTextLines();
  
  g.setFont( (Font)tuneFonts.get( "artist" ) );
  fontMetrics = g.getFontMetrics();
  mixListDepth += fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
  
  mixListDepth += MixListItem.getGapBetweenTextLines();
   
  g.setFont( (Font)tuneFonts.get( "mix" ) );
  fontMetrics = g.getFontMetrics();
  mixListDepth += fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();

  mixListDepth += MixListItem.getTextBottomMargin();
  
  MixListItem.setDepth( mixListDepth );

  preDrawn = true;
}
/**
 * scrollX method comment.
 */
public void scrollX(int change, java.awt.Graphics g) 
{
  draw( currentOffsetX - change, 0, g );
  currentOffsetX = change;
}
/**
 * scrollY method comment.
 */
public void scrollY(int change, java.awt.Graphics g) 
{
  draw( 0, currentOffsetY - change, g );
  currentOffsetY = change;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:18:02)
 * @param newGapBetweenItems int
 */
public void setGapBetweenItems(int newGapBetweenItems) {
	gapBetweenItems = newGapBetweenItems;
}
/**
 * Insert the method's description here.
 * Creation date: (24/08/01 17:53:18)
 * @param newParentContainer client.pc.uiadapters.ScrollableUIComponent
 */
public void setParentContainer( ScrollableUIComponent newParentContainer) {
	parentContainer = newParentContainer;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:14:26)
 * @param newTopLeftX int
 */
public void setTopLeftX(int newTopLeftX) {
	topLeftX = newTopLeftX;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:14:47)
 * @param newTopLeftY int
 */
public void setTopLeftY(int newTopLeftY) {
	topLeftY = newTopLeftY;
}
/**
 * setBounds method comment.
 */
public void setViewableArea(int x, int y, int w, int d, java.awt.Graphics g) { width = w; depth = d; }
/**
 * update method comment.
 */
public void update(java.util.Observable o, java.lang.Object arg) 
{
  Hashtable newScheme = (Hashtable)arg;
  background = (Color) newScheme.get( "bkgnd" );
  mixColourScheme = (Hashtable) newScheme.get( "mixListItem" );
  colourChanged = true;
  tuneFonts = (Hashtable)((Hashtable)newScheme.get( "mixListItem" )).get( "tuneFonts" );
}
}
