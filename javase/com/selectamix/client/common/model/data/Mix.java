package com.selectamix.client.common.model.data;

/**
 * Insert the type's description here.
 * Creation date: (28/04/00 15:54:10)
 * @author: 
 */

 // Outgoing and Incoming are relative to Tune 1 in a Mix between Tune1 and Tune 2

import java.awt.Graphics;
import uk.co.robertjdavis.library.definitions.SelectState;
import uk.co.robertjdavis.library.definitions.Direction;

public class Mix {
	//data members (attributes)
	
	private Tune outgoingTuneRef; // reference to (shallow copy) a Tune
	private Tune incomingTuneRef; // reference to (shallow copy) a Tune
	private int unmixedOutgoingTuneFinishTime; // point (exact sample) within the unmixed outgoing tune at which it stops playing (and the mix starts playing)
	private int unmixedIncomingTuneStartTime; // point (exact sample) within the unmixed incoming tune at which it should start playing after the mix is done


	public final static int INCOMING = Direction.IN;
	public final static int OUTGOING = Direction.OUT;
	private java.lang.String clipId = null;
	private int selectState = SelectState.UNSELECTED;
	private java.lang.Object object = null;
	//methods
	public Mix( Tune anOutgoingTuneRef, int anOutgoingTuneFinishTime,
	     Tune anIncomingTuneRef, int anIncomingTuneStartTime, String aClipId )
	{
	  outgoingTuneRef = anOutgoingTuneRef; // (should be a shallow copy - check)
	  incomingTuneRef = anIncomingTuneRef; // (should be a shallow copy - check)
	  
	  unmixedOutgoingTuneFinishTime = anOutgoingTuneFinishTime;
	  unmixedIncomingTuneStartTime = anIncomingTuneStartTime;

	  // tell tunes
	  outgoingTuneRef.addMix( this );
	  incomingTuneRef.addMix( this );

	  clipId = aClipId;
	}
/**
 * Insert the method's description here.
 * Creation date: (09/07/00 15:54:16)
 * @return int
 * @param tune Tune
 */
// get direction of mix
// relative to tune it is in
// INCOMING means the mix is going into the tune
// OUTGOING means the mix is going away from the tune
// may have to extend to rename the fn as selectedDirection
// as mix may be bidirectional
public int direction(Tune tune) {
  // == - refering to the same object
  if ( tune == incomingTuneRef ) return INCOMING;
  if ( tune == outgoingTuneRef ) return OUTGOING;

  // otherwise (throw an exception?)
  return 0;
}
/**
 * Insert the method's description here.
 * Creation date: (27/05/01 19:04:11)
 * @return java.lang.String
 */
public java.lang.String getClipId() {
	return clipId;
}
/**
 * Insert the method's description here.
 * Creation date: (17/09/00 13:51:43)
 * @return int
 * @param og networkgraphics.ObjectGraphic
 */
public int getDirectionRelativeTo(Tune tune) 
{
	if ( tune == incomingTuneRef ) return Direction.IN;
	if ( tune == outgoingTuneRef ) return Direction.OUT;
	// else	
	return 0;
}
	public Tune getIncomingTune() { return incomingTuneRef; }
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 16:16:27)
 * @return java.lang.Object
 */
public java.lang.Object getObject() {
	return object;
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 14:04:42)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
public Tune getOtherTune( Tune thisNode ) 
{
  Tune otherTune = null;
  if ( thisNode == incomingTuneRef ) 
  {
	otherTune = outgoingTuneRef;
  }

  if ( thisNode == outgoingTuneRef ) 
  {
	otherTune = incomingTuneRef;
  }
  
  return otherTune;
}
	public Tune getOutgoingTune() { return outgoingTuneRef; }
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 13:47:17)
 * @return int
 */
public int getSelectState() {
	return selectState;
}
public int getUnmixedIncomingTuneStartTime() { return unmixedIncomingTuneStartTime; }
public int getUnmixedOutgoingTuneFinishTime() { return unmixedOutgoingTuneFinishTime; }
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 16:16:27)
 * @param newObject java.lang.Object
 */
public void setObject(java.lang.Object newObject) {
	object = newObject;
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 13:47:17)
 * @param newSelectState int
 */
public void setSelectState(int newSelectState) {
	selectState = newSelectState;
}
}
