package com.selectamix.client.common.ui.components.mixnetwork;

/**
 * Insert the type's description here.
 * Creation date: (21/06/00 22:56:40)
 * @author: 
 */

 
 import java.util.Vector;
 import java.util.Observer;
 import java.util.Hashtable; 
  
 import java.awt.Graphics;
 import java.awt.Color;
 import java.awt.BorderLayout;
 import java.awt.Dimension;

 import com.selectamix.client.common.model.data.*;
 import uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource;

 import uk.co.robertjdavis.library.definitions.Direction;
 import uk.co.robertjdavis.library.ui.controller.VectorUserInterfaceType;
 import uk.co.robertjdavis.library.ui.controller.VectorUserInterfaceManager;
 import uk.co.robertjdavis.library.definitions.SelectState;
 import uk.co.robertjdavis.library.ui.adapters.ScrollableUserInterface;

 import com.selectamix.client.common.ui.components.button.TuneSelectButton;
 import com.selectamix.client.common.ui.components.button.TunePlayButton;
 import com.selectamix.client.common.ui.components.mixlist.MixListUserInterface;

 import com.selectamix.client.common.audiologic.PlayingTimeLimits;
 import com.selectamix.client.common.audiologic.AudioPlayer;

import com.selectamix.client.common.model.businesslogic.MixNetworkSelectionController;
import com.selectamix.client.common.model.businesslogic.MixNetworkSelectionView;

 

public class MixNetworkUserInterface implements ScrollableUserInterface, Observer, MixNetworkSelectionView
{
  private Vector tuneGraphics;
  private Vector mixGraphics;
  
  private MixNetworkDatabase database;


  private final static int gridWidth = 2;
  private int width = 0;
  private int depth = 0;
  private int networkMarginX = 0;
  private int networkMarginY = 0;
  private int oldOffsetX = 0;
  private int oldOffsetY = 0;
  

  private MixListUserInterface mixListUserInterface = null;
  
  private VectorUserInterfaceManager vectorUserInterfaceManager = null;

  private AudioPlayer audioPlayer = null;
	private java.awt.Dimension preferredSize = new Dimension( 330, 530 );

	private Hashtable visualAttributesForSelectedMix = null;
	private Hashtable visualAttributesForUnselectedMix = null;
	private Hashtable visualAttributesForTune = null;
	private Hashtable visualAttributesForMix = null;

	Color backgroundColour = null;
	private boolean colourChanged = false;

	private MixNetworkSelectionController selectionController = null;
	private uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource graphicsSource = null;
/**
 * MixMatrixGraphic constructor comment.
 */
public MixNetworkUserInterface( MixNetworkDatabase aDatabase, MixListUserInterface mixListUI, AudioPlayer ap, MixNetworkSelectionController mixNetworkSelectionController, int x, int y, int w, int d, int nmx, int nmy ) 
{
  super();

  mixListUserInterface = mixListUI;
  
  mixGraphics = new Vector();
  tuneGraphics = new Vector();
	
  database = aDatabase;
	
  width = w;
  depth = d;
  networkMarginX = nmx;
  networkMarginY = nmy;
  
  TuneGraphic.setRadius( 65 );

  //setBackground(ColourManager.getBackground());


  vectorUserInterfaceManager = new VectorUserInterfaceManager();

  audioPlayer = ap;

  selectionController = mixNetworkSelectionController;
}
/**
 * addTune method comment.
 */
public void addTune(com.selectamix.client.common.model.data.Tune tune) 
{
// work out position in grid

TunePlayButton tunePlayButton = new TunePlayButton( null, null );
TuneSelectButton tuneSelectButton = new TuneSelectButton( null );

	int tuneIndex = tuneGraphics.size();
	int gridy = tuneIndex/gridWidth;
	int gridx = tuneIndex - gridy * gridWidth;

	TuneGraphic newTuneGraphic =
	  new TuneGraphic(  tune.getTitle(),
						  tune.getArtist(),
						  tune.getVersion(),
		                  networkMarginX + TuneGraphic.getRadius() + ( (   TuneGraphic.getRadius() * 2 )
		                                                            + MixGraphic.getDistanceBetweenTunes()
		                                                        ) * gridx,
		    		      networkMarginY + TuneGraphic.getRadius() + ( (   TuneGraphic.getRadius() * 2 )
		                                                            + MixGraphic.getDistanceBetweenTunes()
		                                                        ) * gridy,
		    		      tune,
						         tuneSelectButton,
						         tunePlayButton
					   );

	
	tune.setTuneGraphic( newTuneGraphic );
	tune.setObject( newTuneGraphic );

	// ...and when tuneGraphic is selected need to find associated tune
	//newTuneGraphic.setTune( tune );
	
	//tuneGraphics.addElement( newTuneGraphic );
	vectorUserInterfaceManager.add( tuneSelectButton );
	vectorUserInterfaceManager.add( tunePlayButton );

	tuneSelectButton.setParentContainer( (Object)newTuneGraphic );
	tunePlayButton.setParentContainer( (Object)newTuneGraphic );

	tuneGraphics.addElement( newTuneGraphic );
}
/**
 * deselectMix method comment.
 */
public void deselectMix(com.selectamix.client.common.model.data.Mix mix) 
{
  MixGraphic mixGraphic = (MixGraphic)mix.getObject();
  //mixGraphic.setVisualAttributes( visualAttributesForUnselectedMix );
  //mixGraphic.draw( graphicsSource.getGraphics() );
  mixGraphic.makeUnselected();
}
/**
 * deselectTune method comment.
 */
public void deselectTune(com.selectamix.client.common.model.data.Tune tune) 
{
  makeUnselectedButSelectable( tune );
  notifyRemoveLastTuneOccured();
}
/**
 * Insert the method's description here.
 * Creation date: (21/06/00 23:03:08)
 */
public void draw( int x, int y, Graphics g ) 
{
  g.setColor( backgroundColour );
  g.fillRect( 0, 0, width, depth );

  // tunes
  for (int tuneGraphicIndex = 0; tuneGraphicIndex < tuneGraphics.size(); tuneGraphicIndex++) 
  {
	TuneGraphic og = ((TuneGraphic) tuneGraphics.elementAt(tuneGraphicIndex));
	og.setGraphicsSource( graphicsSource );
	og.setCoords( og.getDisplayCentrePointX() + x, og.getDisplayCentrePointY() + y );
	og.setCompleteRedrawRequired( true );
	
	if (colourChanged)
	{
	  og.setVisualAttributes( visualAttributesForTune );
	  Tune tune = og.getTune();
	  if ( tune.isSelected() == false )
	  {
		if ( tune.isSelectable() )
		{
		  og.makeUnselectedButSelectable();
		}
		else
		{
		  og.makeUnselectedButNotSelectable();
		}
	  }
	  else // selected
	  {
		if ( tune.isSelectable() )
		{
		  og.makeSelectedButDeselectable();
		}
		else
		{
		  og.makeSelectedButNotDeselectable();
		}
	  }
	}
	else
	{
	  og.draw( g );
	}
	og.setCompleteRedrawRequired( false );
  }
  
  for (int mixGraphicIndex = 0; mixGraphicIndex < mixGraphics.size(); mixGraphicIndex++)
  {
	MixGraphic ocg = (MixGraphic) mixGraphics.elementAt(mixGraphicIndex);
	ocg.setGraphicsSource( graphicsSource );
	ocg.shift( x, y );

	if (colourChanged)
	{
	  ocg.setVisualAttributes( visualAttributesForMix );
	  if ( ocg.getMix().getSelectState() == SelectState.UNSELECTED )
	  {
	    ocg.makeUnselected();
	  }
	  else if ( ocg.getMix().getSelectState() == SelectState.SELECTED ) 
	  {
		ocg.makeSelected();
	  }
	}
	
	ocg.draw(g);
  }

  colourChanged = false;
}
/**
 * Insert the method's description here.
 * Creation date: (21/06/00 23:03:08)
 */
public void draw( Graphics g ) 
{
  draw( 0, 0, g );
}
/**
 * getPreferredSize method comment.
 */
public java.awt.Dimension getSize() 
{
  int gridDepth = tuneGraphics.size() / gridWidth;
  if ( gridDepth * gridWidth < tuneGraphics.size() ) gridDepth += 1;
  
  preferredSize.width = networkMarginX * 2 + ( TuneGraphic.getRadius() * 2 * gridWidth ) + ( MixGraphic.getDistanceBetweenTunes() * ( gridWidth - 1 ) );
  preferredSize.height = networkMarginY * 2 + ( TuneGraphic.getRadius() * 2 * gridDepth ) + ( MixGraphic.getDistanceBetweenTunes() * ( gridDepth - 1 ) );

  return preferredSize;
}
/**
 * makeSelectedButDeselectable method comment.
 */
public void makeSelectedButDeselectable(com.selectamix.client.common.model.data.Tune tune) 
{
  TuneGraphic tuneGraphic = (TuneGraphic)tune.getObject();
  tuneGraphic.makeSelectedButDeselectable();
}
/**
 * makeSelectedEarlierButNotDeselectableNow method comment.
 */
public void makeSelectedButNotDeselectable(com.selectamix.client.common.model.data.Tune tune) 
{
  TuneGraphic tuneGraphic = (TuneGraphic)tune.getObject();
  tuneGraphic.makeSelectedButNotDeselectable();
}
/**
 * makeUnselectedButNotSelectable method comment.
 */
public void makeUnselectedButNotSelectable(com.selectamix.client.common.model.data.Tune tune) 
{
  TuneGraphic tuneGraphic = (TuneGraphic)tune.getObject();
  tuneGraphic.makeUnselectedButNotSelectable();
}
/**
 * makeUnselectedButSelectable method comment.
 */
public void makeUnselectedButSelectable(com.selectamix.client.common.model.data.Tune tune) 
{
  TuneGraphic tuneGraphic = (TuneGraphic)tune.getObject();
  tuneGraphic.makeUnselectedButSelectable();
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 15:32:12)
 * @param tune database.Tune
 */
private void notifyAddTuneOccured(Tune tune) 
{
  mixListUserInterface.addTune( tune );	
}
/**
 * Insert the method's description here.
 * Creation date: (29/08/01 15:32:12)
 * @param tune database.Tune
 */
private void notifyRemoveLastTuneOccured() 
{
  mixListUserInterface.deleteTune();
}
/**
 * pointerEntered method comment.
 */
public void pointerEntered(int x, int y, java.awt.Graphics g) 
{
  vectorUserInterfaceManager.pointerEntered( x, y, g );
}
/**
 * pointerEntered method comment.
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

  //TuneGraphic tg = findTuneGraphic( x, y );
  //TuneSelectButton tuneSelectButton = tg.getTuneSelectButton();
  //TunePlayButton tunePlayButton = tg.getTunePlayButton();
  if ( vuit instanceof TunePlayButton ) 
  {
	audioPlayer.playTuneClip( ((TuneGraphic)((TunePlayButton)vuit).getParentContainer()).getTune() );
  }
  else if ( vuit instanceof MixGraphic )
  {
	audioPlayer.playMixClip( ((MixGraphic)vuit).getMix() );
  }
  else if ( vuit instanceof TuneSelectButton ) 
  {
	TuneSelectButton tuneSelectButton = (TuneSelectButton)vuit;
	TuneGraphic tg = (TuneGraphic)tuneSelectButton.getParentContainer();
	// if the button has previously been selected then clicking
	// on it will de-selected it
	if ( tuneSelectButton.getSelectState() == SelectState.SELECTED )
	{
	  tuneSelectButton.pointerPressed( g );	
	  selectionController.deselectTune( tg.getTune() );
	}
	else // otherwise the user may be selecting the next tune
	{
	  tuneSelectButton.pointerPressed( g );
	  selectionController.selectTune( tg.getTune() );
	}
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
 * Creation date: (24/08/00 17:58:52)
 * @param g java.awt.Graphics
 */
public void preDraw() 
{
  // now link the tunes up with the database.mixes
  for ( int mixIndex = 0; mixIndex < database.mixes.size(); mixIndex++ )
  {
   Mix mix = (Mix) database.mixes.elementAt(mixIndex);
   mixGraphics.addElement( 
	         new MixGraphic( (mix.getOutgoingTune()).getTuneGraphic(),
							          (mix.getIncomingTune()).getTuneGraphic(),
							          mix,
									  SelectState.UNSELECTED, 
									  Direction.OUT )
						 );
	MixGraphic newObjectConnectionGraphic = ( (MixGraphic) mixGraphics.elementAt( mixIndex ) );

	mix.setObject( newObjectConnectionGraphic );
	//newObjectConnectionGraphic.setObject( database.mixes.elementAt(mixIndex) );
	newObjectConnectionGraphic.preDraw(); // could make this private and part of constructor?
	vectorUserInterfaceManager.add( newObjectConnectionGraphic );
  }
}
/**
 * scrollX method comment.
 */
public void scrollX(int change, Graphics g ) 
{
  draw( oldOffsetX - change, 0, g );
  oldOffsetX = change;
}
/**
 * scrollY method comment.
 */
public void scrollY(int change, Graphics g ) 
{
  draw( 0, -(change - oldOffsetY), g );
  oldOffsetY = change;
}
/**
 * selectMix method comment.
 */
public void selectMix(com.selectamix.client.common.model.data.Mix mix) 
{
  MixGraphic mixGraphic = (MixGraphic)mix.getObject();
  //mixGraphic.setVisualAttributes( visualAttributesForSelectedMix );
  //mixGraphic.draw( graphicsSource.getGraphics() );
  mixGraphic.makeSelected();
}
/**
 * selectTune method comment.
 */
public void selectTune(com.selectamix.client.common.model.data.Tune tune) 
{
  makeSelectedButDeselectable( tune );
  notifyAddTuneOccured( tune );
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 16:13:52)
 * @param newGraphicsSource uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource
 */
public void setGraphicsSource(uk.co.robertjdavis.library.graphics.basicadapters.GraphicsSource newGraphicsSource) {
	graphicsSource = newGraphicsSource;
}
/**
 * resize method comment.
 */
public void setViewableArea(int x, int y, int w, int d, Graphics g ) {  width = w; depth = d; }
/**
 * update method comment.
 */
public void update(java.util.Observable o, java.lang.Object arg) 
{
  Hashtable newScheme = (Hashtable)arg;
  

  visualAttributesForSelectedMix = (Hashtable) newScheme.get( "selectedMix" );
  visualAttributesForUnselectedMix = (Hashtable) newScheme.get( "unselectedMix" );
  visualAttributesForTune = (Hashtable) newScheme.get( "tune" );
  visualAttributesForMix = (Hashtable) newScheme.get( "mix" );
  backgroundColour = (Color) newScheme.get( "bkgnd" );

  colourChanged = true;
}
}
