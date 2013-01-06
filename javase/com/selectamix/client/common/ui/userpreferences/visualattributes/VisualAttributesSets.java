package com.selectamix.client.common.ui.userpreferences.visualattributes;

/**
 * Insert the type's description here.
 * Creation date: (21/09/01 22:09:54)
 * @author: 
 */
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Color;
import java.lang.String;
import java.util.Observable;
import java.awt.Font;

 
public class VisualAttributesSets extends Observable {
	private java.util.Hashtable sets = null;
/**
 * ColourScheme constructor comment.
 */
public VisualAttributesSets() 
{	
	super();

// ptr = pointer
// bkgnd = background
// sym = symbol
// Fl = Fill
// Ol = Outline

	// could define colours and have this reference them for efficiency

	// herbal
  sets = new Hashtable();

  Hashtable herbal = new Hashtable();

  Hashtable headingTitle = new Hashtable();
  headingTitle.put("bkgnd", new Color( 102, 153, 102 ) ); // light green
  headingTitle.put("fgnd", new Color( 51, 102, 51 ) ); // white
  headingTitle.put("font", new Font( "Helvetica", Font.BOLD, 14 ) );
	  
  Hashtable buttonInUnselectedTune = new Hashtable();
  buttonInUnselectedTune.put( "bkgnd",       new Color( 153, 102, 51 ) ); // coffee
  buttonInUnselectedTune.put( "ptrOffFl",    new Color( 204, 153, 102 ) ); // coffee
  buttonInUnselectedTune.put( "ptrOffOl",    new Color( 102, 51 ,0 ) ); // white
  buttonInUnselectedTune.put( "ptrOffSymOl", new Color( 102, 51 ,0 ) ); // white
  buttonInUnselectedTune.put( "ptrOffSymFl", new Color( 153, 102, 51 ) ); // coffee
  buttonInUnselectedTune.put( "ptrOnFl",     new Color( 204, 153, 102 ) ); // cream
  buttonInUnselectedTune.put( "ptrOnOl",     new Color( 255, 255, 255 ) ); // white
  buttonInUnselectedTune.put( "ptrOnSymOl",  new Color( 255, 255, 255 ) ); // white
  buttonInUnselectedTune.put( "ptrOnSymFl", new Color( 255, 102, 51 ) ); // coffee
  buttonInUnselectedTune.put( "pressedSymOl", new Color( 255, 255, 255 ) ); // white
  buttonInUnselectedTune.put( "pressedSymFl", new Color( 255, 255, 255 ) ); // white
  
  Hashtable tuneFonts = new Hashtable();
  tuneFonts.put( "artist", new Font( "Helvetica", Font.PLAIN, 10 ) );
  tuneFonts.put( "title", new Font( "Helvetica", Font.BOLD, 12 ) );
  tuneFonts.put( "mix", new Font( "Helvetica", Font.ITALIC, 8 ) );

  Hashtable unselectedTune = new Hashtable();
  unselectedTune.put( "ol", new Color( 153, 102, 51 ) ); // coffee
  unselectedTune.put( "fl", new Color( 153, 102, 51 ) ); // coffee
  unselectedTune.put( "text", new Color( 255, 255, 255 ) ); // white
  unselectedTune.put( "button", buttonInUnselectedTune );
  unselectedTune.put( "tuneFonts", tuneFonts );

  
  Hashtable unselectedMix = new Hashtable();
  unselectedMix.put( "ol", new Color( 153, 102, 51 ) ); // coffee
  unselectedMix.put( "fl", new Color( 153, 102, 51 ) ); // coffee
  Hashtable mixPlayButtonOnUnselectedMix = new Hashtable();
  mixPlayButtonOnUnselectedMix.put( "ptrOffFl", new Color( 204, 153, 102 ) ); // coffee
  mixPlayButtonOnUnselectedMix.put( "ptrOffOl", new Color( 102, 51 ,0 ) ); // dark brown
  mixPlayButtonOnUnselectedMix.put( "ptrOffSymOl", new Color( 204, 153, 102 ) ); // coffee
  mixPlayButtonOnUnselectedMix.put( "ptrOffSymFl", new Color( 204, 153, 102 ) ); // coffee
  mixPlayButtonOnUnselectedMix.put( "ptrOnSymOl", new Color( 255, 255, 255 ) ); // white
  mixPlayButtonOnUnselectedMix.put( "ptrOnSymFl", new Color( 204, 153, 102 ) ); // coffee
  mixPlayButtonOnUnselectedMix.put( "pressedSymOl", new Color( 255, 255, 255 ) ); // white
  mixPlayButtonOnUnselectedMix.put( "pressedSymFl", new Color( 255, 255, 255 ) ); // white
  unselectedMix.put( "mixPlayButton", mixPlayButtonOnUnselectedMix );
  
	
  Hashtable buttonInSelectedTune = new Hashtable();
  buttonInSelectedTune.put( "bkgnd",       new Color( 102, 153, 102 ) ); // soft light green
  buttonInSelectedTune.put( "ptrOffFl",    new Color( 102, 153, 102 ) ); // soft light green
  buttonInSelectedTune.put( "ptrOffOl",    new Color( 255, 255, 255 ) ); // white
  buttonInSelectedTune.put( "ptrOffSymOl", new Color( 255, 255, 255 ) ); // white
  buttonInSelectedTune.put( "ptrOffSymFl", new Color( 0, 0, 0 ) ); // black
  buttonInSelectedTune.put( "ptrOnFl",     new Color( 153, 204, 153 ) ); // soft light green
  buttonInSelectedTune.put( "ptrOnOl",     new Color( 255, 255, 255 ) ); // white
  buttonInSelectedTune.put( "ptrOnSymOl",  new Color( 255, 255, 255 ) ); // white
  buttonInSelectedTune.put( "ptrOnSymFl", new Color( 0, 0, 0 ) ); // black
  buttonInSelectedTune.put( "pressedSymOl", new Color( 255, 255, 255 ) ); // white
  buttonInSelectedTune.put( "pressedSymFl", new Color( 255, 255, 255 ) ); // white
  

  Hashtable selectedTune = new Hashtable();
  selectedTune.put( "ol", new Color( 102, 153, 102 ) ); // soft light green
  selectedTune.put( "fl", new Color( 102, 153, 102 ) ); // soft light green
  selectedTune.put( "text", new Color( 255, 255, 255 ) ); // white
  selectedTune.put( "button", buttonInSelectedTune );
  selectedTune.put( "tuneFonts", tuneFonts );

  Hashtable tune = new Hashtable();
  tune.put( "unselected", unselectedTune );
  tune.put( "selected", selectedTune );
   
  Hashtable selectedMix = new Hashtable();
  selectedMix.put( "ol", new Color( 102, 153, 102 ) ); // soft light green
  selectedMix.put( "fl", new Color( 102, 153, 102 ) ); // soft light green
  Hashtable mixPlayButtonOnSelectedMix = new Hashtable();
  mixPlayButtonOnSelectedMix.put( "ptrOffFl", new Color( 153, 204, 153 ) ); // soft light green
  mixPlayButtonOnSelectedMix.put( "ptrOffOl", new Color( 51, 102, 51 ) ); // black
  mixPlayButtonOnSelectedMix.put( "ptrOffSymOl", new Color( 153, 204, 153 ) ); // soft light green
  mixPlayButtonOnSelectedMix.put( "ptrOffSymFl", new Color( 153, 204, 153 ) ); // soft light green
  mixPlayButtonOnSelectedMix.put( "ptrOnSymOl", new Color( 255, 255, 255 ) ); // white
  mixPlayButtonOnSelectedMix.put( "ptrOnSymFl", new Color( 153, 204, 153 ) ); // soft light green
  mixPlayButtonOnSelectedMix.put( "pressedSymOl", new Color( 255, 255, 255 ) ); // white
  mixPlayButtonOnSelectedMix.put( "pressedSymFl", new Color( 255, 255, 255 ) ); // white
  selectedMix.put( "mixPlayButton", mixPlayButtonOnSelectedMix );

  Hashtable mix = new Hashtable();
  mix.put( "selected", selectedMix );
  mix.put( "unselected", unselectedMix );
  
  Hashtable mixListItem = new Hashtable();
  mixListItem.put( "ol", new Color( 102, 153, 102 ) ); // soft light green
  mixListItem.put( "fl", new Color( 102, 153, 102 ) ); // soft light green
  mixListItem.put( "text", new Color( 255, 255, 255 ) ); // white
  mixListItem.put( "mixListItemPlayButton", buttonInSelectedTune );
  mixListItem.put( "tuneFonts", tuneFonts );
  
  herbal.put( "bkgnd", new Color( 51, 102, 51 ) ); // dark green
  herbal.put( "textOnBkgnd", new Color( 255, 255, 255 ) ); // white
  herbal.put( "outlineOnBkgnd", new Color( 255, 255, 255 ) );
  herbal.put( "olOnBkgnd", new Color( 255, 255, 255 ) ); // white
  herbal.put( "heading", headingTitle );
  herbal.put( "mixListItem", mixListItem );
  herbal.put( "mixListPlayButton", buttonInSelectedTune ); // for the entire mix
  herbal.put( "generalFont", new Font( "Helvetica", Font.PLAIN, 12 ) );
  herbal.put( "tune", tune );
  herbal.put( "mix", mix );
  

  sets.put( "herbal", herbal );

}
/**
 * Insert the method's description here.
 * Creation date: (23/09/01 17:05:20)
 * @param newScheme java.lang.String
 */
public void changeSet(String newSet) 
{
  setChanged();
  notifyObservers( sets.get( newSet ) );
}
/**
 * Insert the method's description here.
 * Creation date: (23/09/01 17:37:27)
 * @return java.util.Hashtable
 */
public java.util.Hashtable getSets() {
	return sets;
}
}
