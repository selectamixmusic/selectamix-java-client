package com.selectamix.client.pc;

/**
 * Insert the type's description here.
 * Creation date: (20/04/01 18:36:24)
 * @author: 
 */

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Hashtable;

import com.selectamix.client.common.ui.userpreferences.visualattributes.VisualAttributesSets;
import java.util.Observer;
import java.util.Enumeration;
import java.util.Properties;

import java.awt.event.ItemListener;

public class PreferencesUserInterface extends Panel implements Observer {
	private int topLeftX = 0;
	private int topLeftY = 0;
	private int width = 0;
	private int depth = 0;
	private java.awt.Choice colourSchemeChoice = null;
/**
 * PreferencesUserInterface constructor comment.
 */
public PreferencesUserInterface( int x, int y, int w, int d, ItemListener itemListener, VisualAttributesSets visualAttributesSets ) {
	super();
	topLeftX = x;
	topLeftY = y;
	width = w;
	depth = d;
	
	Dimension dim = getMaximumSize();
	setSize( dim.width, dim.height ); 

	colourSchemeChoice = new Choice();
	this.add( colourSchemeChoice );

	// build menu from colour schemes
	visualAttributesSets.addObserver( this );
	Object obj = null;
	for( Enumeration e = visualAttributesSets.getSets().keys(); e.hasMoreElements(); )
	{
	  colourSchemeChoice.addItem( (String)e.nextElement() );
	}
		
	colourSchemeChoice.addItemListener( itemListener );
}
/**
 * Insert the method's description here.
 * Creation date: (21/04/01 22:29:36)
 * @param g java.awt.Graphics
 */
public void paint(Graphics g) 
{
  setBackground( getBackground() );
  colourSchemeChoice.setBackground( getBackground() );
  colourSchemeChoice.setForeground( getForeground() );
 // g.fillRect( topLeftX, topLeftY, width, depth );
 /*
 	Dimension dim = getMaximumSize();
	setSize( dim.width, dim.height );
  g.setColor( ColourManager.getOutlineOnBackground() );
  g.drawRect( 0,0, dim.width, dim.height );
  //  g.drawRect( 0,0, 10, 10 );
  */
}
/**
 * update method comment.
 */
public void update(java.util.Observable o, java.lang.Object arg) 
{
  Hashtable newScheme = (Hashtable)arg;
  setBackground( (Color)newScheme.get( "bkgnd" ) );
  setForeground( (Color)newScheme.get( "textOnBkgnd" ) );
}
}
