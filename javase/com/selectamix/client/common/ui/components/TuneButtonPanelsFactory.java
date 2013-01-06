package com.selectamix.client.common.ui.components;

/**
 * Insert the type's description here.
 * Creation date: (10/02/01 21:50:19)
 * @author: 
 */
import com.selectamix.client.common.model.data.MixNetworkDatabase;
import com.selectamix.client.common.model.data.Tune;
import com.selectamix.client.common.ui.components.mixnetwork.MixNetworkUserInterface;

import java.util.Vector;

public class TuneButtonPanelsFactory {
	private java.util.Vector tuneButtonPanels = null;
/**
 * TuneButtonPanelsFactory constructor comment.
 */
public TuneButtonPanelsFactory( MixNetworkUserInterface theMixNetworkUI, MixNetworkDatabase theDatabase ) 
{
  super();
  
  tuneButtonPanels = new Vector();
	
  // iterate through the database
  
  int tuneIndex = 0;
  for( tuneIndex = 0; tuneIndex < theDatabase.tunes.size(); tuneIndex++ )
  {
	// get tune
	Tune aTune = (Tune)(theDatabase.tunes.elementAt( tuneIndex ));
	 
	// add tune to mix network
	theMixNetworkUI.addTune( aTune );
  }
}
}
