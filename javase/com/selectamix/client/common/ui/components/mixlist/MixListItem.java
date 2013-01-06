package com.selectamix.client.common.ui.components.mixlist;

/**
 * Insert the type's description here.
 * Creation date: (11/02/01 16:00:01)
 * @author: 
 */

import java.awt.Font;
import java.awt.FontMetrics;

import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Color;


import uk.co.robertjdavis.library.ui.controller.PointerOverState;

import com.selectamix.client.common.ui.components.button.TunePlayButton;

import java.util.Hashtable;
import java.util.Observable;

import com.selectamix.client.common.model.data.Tune;
 
class MixListItem 
{
  private int topLeftX = 0;
  private int topLeftY = 0;

  private Tune tune = null;

  private int index = 0;
  
  private TunePlayButton tunePlayButton = null;
  
  static private int width = 0;
  static private int depth = 15;

  private static int textYOffset = 0;
  private static int textXOffset = 0;
  
  private static int gapBetweenButtons = 5;
  private static int gapBetweenButtonAndText = 5;
	
  private static int maxWidth = 0;
  private static int minWidth = 0;

	private static int textTopMargin = 3;
	private static int textBottomMargin = 3;
	private static int gapBetweenTextLines = 3;

	Color fill = null;
	Color outline = null;
	Color text = null;
	private java.util.Hashtable tuneFonts = null;
/**
 * MixListItem constructor comment.
 */
public MixListItem( Tune aTune, int i, TunePlayButton aTunePlayButton, int x, int y ) 
{
  super();
	
  tune = aTune;

  // set absolute co-ords for top left corner of item
  topLeftX = x; topLeftY = y;

  //int height = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();

  
  tunePlayButton = aTunePlayButton;

  int radius = 10;
  
  tunePlayButton.setRadius( radius );
  tunePlayButton.setCentreX( topLeftX + depth / 2 );
  tunePlayButton.setCentreY( topLeftY + depth / 2 );
  
  index = i;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:30:05)
 */
public void draw( Graphics g ) 
{
  // fill and outline rounded edges

  int w = width;
  
  int rectTopLeftX = topLeftX + depth/2;
  int rectTopLeftY = topLeftY;
  int rectBotRightX = width;
  int rectBotRightY = depth;

  
  g.setColor( fill );
  g.fillOval( topLeftX, topLeftY, depth, depth );
  g.fillOval( topLeftX + width - depth, topLeftY, depth, depth );
  g.setColor( outline );
  g.drawOval( topLeftX, topLeftY, depth, depth );
  g.drawOval( topLeftX + width - depth, topLeftY, depth, depth );


   
  // fill and outline long rectange part

  g.setColor( fill );
  g.fillRect( rectTopLeftX, topLeftY, width - depth, depth );
  g.setColor( outline );
  g.drawLine( rectTopLeftX, topLeftY, rectTopLeftX + width - depth, topLeftY );
  g.drawLine( rectTopLeftX, topLeftY + depth, rectTopLeftX + width - depth, topLeftY + depth );

  // display tune play button
  tunePlayButton.draw( g );
  
  // display tune text information 
  g.setColor( text );
  g.setFont( (Font)tuneFonts.get("title") );
  FontMetrics fontMetrics = g.getFontMetrics();
  int textYPos = topLeftY + fontMetrics.getLeading() + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
  g.drawString( tune.getTitle(), topLeftX + depth + gapBetweenButtonAndText, textYPos );

  g.setFont( (Font)tuneFonts.get("artist") );
  fontMetrics = g.getFontMetrics();
  textYPos += fontMetrics.getLeading() + fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
  g.drawString( tune.getArtist(), topLeftX + depth + gapBetweenButtonAndText, textYPos );
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:36:10)
 * @return int
 */
static public int getDepth() {
	return depth;
}
/**
 * Insert the method's description here.
 * Creation date: (18/03/01 13:11:25)
 * @return int
 */
public static int getGapBetweenButtonAndText() {
	return gapBetweenButtonAndText;
}
/**
 * Insert the method's description here.
 * Creation date: (18/03/01 13:11:37)
 * @return int
 */
public static int getGapBetweenButtons() {
	return gapBetweenButtons;
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 19:20:03)
 * @return int
 */
public static int getGapBetweenTextLines() {
	return gapBetweenTextLines;
}
/**
 * Insert the method's description here.
 * Creation date: (17/04/01 16:51:13)
 * @return int
 */
public static int getMaxWidth() {
	return maxWidth;
}
/**
 * Insert the method's description here.
 * Creation date: (17/04/01 16:52:47)
 * @return int
 */
public static int getMinWidth() {
	return minWidth;
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 19:18:13)
 * @return int
 */
public static int getTextBottomMargin() {
	return textBottomMargin;
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 19:17:55)
 * @return int
 */
public static int getTextTopMargin() {
	return textTopMargin;
}
/**
 * Insert the method's description here.
 * Creation date: (18/03/01 13:09:23)
 * @return int
 */
public static int getTextXOffset() {
	return textXOffset;
}
/**
 * Insert the method's description here.
 * Creation date: (25/02/01 20:39:55)
 * @return int
 */
public static int getTextYOffset() {
	return textYOffset;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:35:29)
 * @return int
 */
public int getTopLeftX() {
	return topLeftX;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:35:55)
 * @return int
 */
public int getTopLeftY() {
	return topLeftY;
}
/**
 * Insert the method's description here.
 * Creation date: (20/07/01 14:48:58)
 * @return database.Tune
 */
public Tune getTune() {
	return tune;
}
/**
 * Insert the method's description here.
 * Creation date: (25/02/01 19:45:41)
 * @return userinterface.TunePlayButton
 */
public TunePlayButton getTunePlayButton() {
	return tunePlayButton;
}
/**
 * Insert the method's description here.
 * Creation date: (01/10/01 10:07:20)
 * @return int
 */
public static int getWidth() {
	return width;
}
/**
 * Insert the method's description here.
 * Creation date: (24/08/01 15:40:39)
 * @param g java.awt.Graphics
 */
public void preDraw(Graphics g) 
{
/*
  int r = TuneGraphic.getTuneButtonRadius();
  
  // work out text formatting for mix list item   
  FontMetrics fontMetrics = g.getFontMetrics();
  //int leading = fontMetrics.getLeading();
  int height = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
  setTextYOffset( height - ((r * 2) - height) / 2 );
  setTextXOffset( (r * 4) + MixListItem.getGapBetweenButtons() + MixListItem.getGapBetweenButtonAndText() );

  int tuneButtonRadius = TuneGraphic.getTuneButtonRadius();
  tunePlayButton.setRadius( tuneButtonRadius ); 
  tunePlayButton.setCentreX( topLeftX + (tuneButtonRadius * 3) + gapBetweenButtons);
  tunePlayButton.setCentreY( topLeftY + tuneButtonRadius );

  // set text 
  text = Integer.toString(index) + ". " + tune.getTitle() + ", " +
				tune.getArtist() + ", (" +
				tune.getVersion() + ")";
  // set width of item
  width = 
	    gapBetweenButtons
	  + (tunePlayButton.getRadius() * 2)
	  + gapBetweenButtonAndText
	  + (g.getFontMetrics()).stringWidth( text );	
	  */	
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:36:10)
 * @param newDepth int
 */
static public void setDepth(int newDepth) {
	depth = newDepth;
}
/**
 * Insert the method's description here.
 * Creation date: (18/03/01 13:11:25)
 * @param newGapBetweenButtonAndText int
 */
public static void setGapBetweenButtonAndText(int newGapBetweenButtonAndText) {
	gapBetweenButtonAndText = newGapBetweenButtonAndText;
}
/**
 * Insert the method's description here.
 * Creation date: (18/03/01 13:11:37)
 * @param newGapBetweenButtons int
 */
public static void setGapBetweenButtons(int newGapBetweenButtons) {
	gapBetweenButtons = newGapBetweenButtons;
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 19:20:03)
 * @param newGapBetweenTextLines int
 */
public static void setGapBetweenTextLines(int newGapBetweenTextLines) {
	gapBetweenTextLines = newGapBetweenTextLines;
}
/**
 * Insert the method's description here.
 * Creation date: (17/04/01 16:51:13)
 * @param newMaxWidth int
 */
public static void setMaxWidth(int newMaxWidth) {
	maxWidth = newMaxWidth;
}
/**
 * Insert the method's description here.
 * Creation date: (17/04/01 16:52:47)
 * @param newMinWidth int
 */
public static void setMinWidth(int newMinWidth) {
	minWidth = newMinWidth;
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 19:18:13)
 * @param newTextBottomMargin int
 */
public static void setTextBottomMargin(int newTextBottomMargin) {
	textBottomMargin = newTextBottomMargin;
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 19:17:55)
 * @param newTextTopMargin int
 */
public static void setTextTopMargin(int newTextTopMargin) {
	textTopMargin = newTextTopMargin;
}
/**
 * Insert the method's description here.
 * Creation date: (18/03/01 13:09:23)
 * @param newTextXOffset int
 */
public static void setTextXOffset(int newTextXOffset) {
	textXOffset = newTextXOffset;
}
/**
 * Insert the method's description here.
 * Creation date: (25/02/01 20:39:55)
 * @param newTextYOffset int
 */
public static void setTextYOffset(int newTextYOffset) {
	textYOffset = newTextYOffset;
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:35:29)
 * @param newTopLeftX int
 */
public void setTopLeftX(int newTopLeftX) {
	topLeftX = newTopLeftX;


  tunePlayButton.setCentreX( newTopLeftX + depth / 2 );
}
/**
 * Insert the method's description here.
 * Creation date: (11/02/01 16:35:56)
 * @param newTopLeftY int
 */
public void setTopLeftY(int newTopLeftY) {
	topLeftY = newTopLeftY;

	  tunePlayButton.setCentreY( newTopLeftY + depth / 2 );
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/01 20:41:26)
 * @param scheme java.util.Hashtable
 */
public void setVisualAttributes(Hashtable newMixListScheme ) 
{
  fill = (Color) newMixListScheme.get( "fl" );
  text = (Color) newMixListScheme.get( "text" );
  outline = (Color) newMixListScheme.get( "ol" );
  
  tunePlayButton.setVisualAttributes( (Hashtable)newMixListScheme.get( "mixListItemPlayButton" ) );
  tuneFonts = (Hashtable) newMixListScheme.get( "tuneFonts" );
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 19:09:41)
 * @param newWidth int
 */
static public void setWidth(int newWidth) { width = newWidth; }
}
