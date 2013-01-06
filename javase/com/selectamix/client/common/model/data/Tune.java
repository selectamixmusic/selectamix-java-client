package com.selectamix.client.common.model.data;

/**
 * Insert the type's description here.
 * Creation date: (28/04/00 15:32:10)
 * @author: 
 */



 
// forward declaration of Mix required?

import java.awt.*;
import java.util.Vector; // for Vector class
import com.selectamix.client.common.ui.components.mixnetwork.TuneGraphic;
import uk.co.robertjdavis.library.definitions.SelectState;


// Responsibilities:
//   holds tune title, artist, version and length
//   holds mix possibilities with other tunes
//   knows whether it is selected in a mix
//   - and if so, knows from what tune precedes
//     it in the mix (incoming) and what follows
//     it (outgoing)
//     - from this it will also know whether it
//       is the first tune in a mix or the last
// The terms incoming and outgoing are always relative to the first Tune in the Mix between 2 Tunes.

// Knows nothing about it's graphical representation(s)
public class Tune {
	// what interfaces should this implement
	// e.g drawable object - for effective redraws when necessary?

	// Tune( String title, String artist, String version,
	//       int length )
	
	//data members:
	//(thought: is it good practise to have initial values? I think it is!)
	private String title;
	private String artist;
	private String version;
	private int length; // (in samples)
	private final int maximumNumberOfMixes = 10; // maximum is necessary so code can be deterministic
	private Vector mixes;
	private Mix selectedIncomingMix;
	private Mix selectedOutgoingMix;


	private TuneGraphic tuneGraphic;
	private java.lang.String clipId = null;
	private java.lang.Object object = null;
	private int selectState = SelectState.UNSELECTED;
	private boolean selectable = true;
/**
 * Tune constructor comment.
 */
public Tune( String anArtist, String aTitle, String aVersion, String aClipId ) {
	super();
	artist = anArtist;
	title = aTitle;
	version = aVersion;
	mixes = new Vector();

	// when constructed with this constructor, the Tune
	// is not selected in a mix (it doesn't have selected incoming
	// or outgoing mixes)
	Mix selectedIncomingMix = null;
	Mix selectedOutgoingMix = null;


	clipId = aClipId;
}
	public void addMix( Mix aMix )
	{
		// is there still room to add another mix
		if ( mixes.size() < maximumNumberOfMixes )
		{
		  // yes there is
		  mixes.addElement( aMix );
		}
		else // no there isn't
		{
			// throw an exception
		}
	}
	public String getArtist() { return artist; }
/**
 * Insert the method's description here.
 * Creation date: (27/05/01 19:46:26)
 * @return java.lang.String
 */
public java.lang.String getClipId() {
	return clipId;
}
	public int getMaximumNumberOfMixes() { return maximumNumberOfMixes; }
/**
 * Insert the method's description here.
 * Creation date: (21/06/00 22:23:13)
 * @return Mix
 */
public Mix getMix( int i ) {
	return (Mix) mixes.elementAt( i );
}
	public int getNumberOfMixes()
	{
		return mixes.size();
	}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 16:26:04)
 * @return java.lang.Object
 */
public java.lang.Object getObject() {
	return object;
}
	public Mix getSelectedIncomingMix() { return selectedIncomingMix; }
	public Mix getSelectedOutgoingMix() { return selectedOutgoingMix; }
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 21:56:46)
 * @return int
 */
public int getSelectState() {
	return selectState;
}
	//Dictionary mixes[] //initially empty (unordered indexable collection)
	// or an array of indexes?
	//float mixPositioning[indexed by Mix (a Dictionary collection - slow?)] initially empty
	
	//Mix selectedOutgoingMix;
	//Mix selectedIncomingMix;
	// const int maximumNumberOfMixesAllowed (is this necessary?)
	
	//methods:
	public String getTitle() { return title; }
/**
 * Insert the method's description here.
 * Creation date: (21/08/00 19:38:43)
 * @return vectorgraphics.ObjectGraphic
 */
public TuneGraphic getTuneGraphic() {
	return tuneGraphic;
}
	public String getVersion() { return version; }
	public boolean isFirstTune()
	{
	  if (    ( getSelectedIncomingMix() == null ) 
	       || ( getSelectedOutgoingMix() != null ) )
	  {
		return true;
	  }
	  else
	  {
		return false;
	  }
	}
	public boolean isLastTune()
	{
	  if (    ( getSelectedIncomingMix() != null ) 
	       || ( getSelectedOutgoingMix() == null ) )
	  {
		return true;
	  }
	  else
	  {
		return false;
	  }
	}
/**
 * Insert the method's description here.
 * Creation date: (26/10/01 13:42:37)
 * @return boolean
 */
public boolean isSelectable() {
	return selectable;
}
	public boolean isSelected()
	{
	  if ( selectState == SelectState.SELECTED ) return true;
		return false;
/*
	  // a Tune is selected for a mix if
	  // it has an incoming or outgoing mix selected or both
	  if (    ( getSelectedIncomingMix() != null )
	       || ( getSelectedOutgoingMix() != null ) )
	  {
		return true;
	  }
	  else
	  {
		return false;
	  }*/
	}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 16:26:04)
 * @param newObject java.lang.Object
 */
public void setObject(java.lang.Object newObject) {
	object = newObject;
}
/**
 * Insert the method's description here.
 * Creation date: (26/10/01 13:42:37)
 * @param newSeletable boolean
 */
public void setSelectable(boolean newSelectable) {
	selectable = newSelectable;
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 21:56:46)
 * @param newSelectState int
 */
public void setSelectState(int newSelectState) {
	selectState = newSelectState;
}
/**
 * Insert the method's description here.
 * Creation date: (21/08/00 19:38:43)
 * @param newTuneGraphic vectorgraphics.ObjectGraphic
 */
public void setTuneGraphic(TuneGraphic newTuneGraphic) {
	tuneGraphic = newTuneGraphic;
}
}
