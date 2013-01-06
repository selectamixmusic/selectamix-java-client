package com.selectamix.client.common.model.businesslogic;

/**
 * Insert the type's description here.
 * Creation date: (26/09/01 11:34:55)
 * @author: 
 */

import java.util.Vector;
import com.selectamix.client.common.model.data.Tune;
import com.selectamix.client.common.model.data.Mix;
import com.selectamix.client.common.model.data.MixNetworkDatabase;
import uk.co.robertjdavis.library.definitions.Direction;
import uk.co.robertjdavis.library.definitions.SelectState;
import com.selectamix.client.common.audiologic.PlayingTimeLimits;

public class MixNetworkSelectionController 
{
  private Vector mixRoute = new Vector();
  private MixNetworkSelectionView view = null;
  private MixNetworkDatabase database = null;
  private PlayingTimeLimits playingTimeLimits = null;
/**
 * SelectionController constructor comment.
 */
public MixNetworkSelectionController( MixNetworkDatabase db, PlayingTimeLimits ptl ) 
{
	super();
	database = db;
	playingTimeLimits = ptl;
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 12:48:25)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
public void deselectTune(Tune deselectedTune) 
{
  // user wants to deselect tune

  // only one tune selected	
  if ( mixRoute.size() == 1 )
  {
	// first tune selected only
	        
	// remove from mix route
	mixRoute.removeElementAt( mixRoute.size() - 1 );
	playingTimeLimits.removeTune();

	deselectedTune.setSelectState( SelectState.UNSELECTED );
	deselectedTune.setSelectable( true );
	
	// make tune visual state unselected
	view.deselectTune( deselectedTune );

	// reset its object connections to UNSELECTED
	int deselectedTuneNumberOfObjectConnections = deselectedTune.getNumberOfMixes();
	for ( int i = 0; i < deselectedTuneNumberOfObjectConnections; i++ )
	{
	  Mix aMix = deselectedTune.getMix( i );
	  aMix.setSelectState( SelectState.UNSELECTED );
	  view.deselectMix( aMix );
	}

	// re-enable all nodes in the network as unselected (selectable)
	for ( int i = 0; i < database.tunes.size(); i++ )
	{
	  Tune tune = (Tune)database.tunes.elementAt( i );
	  tune.setSelectable( true );
	  view.makeUnselectedButSelectable( tune );
	}

	return; // no further processing required
  }
  else
  {
	// else more than 1 object selected...

	// remove from mix route
	mixRoute.removeElementAt( mixRoute.size() - 1 );
	playingTimeLimits.removeTune();
		  
	// find previous object
	// ( because the last object has just been removed, then the previous object
	// will be the last object in the list)
	Tune previousTune = ((Tune)mixRoute.elementAt( mixRoute.size() - 1 ));
	deselectedTune.setSelectState( SelectState.UNSELECTED );
	deselectedTune.setSelectable( true );
	      
	// get its object connection that it was connected to the
	// deselected object with
	// set this to unselected state
	int deselectedTuneNumberOfObjectConnections = deselectedTune.getNumberOfMixes();
	for ( int i = 0; i <deselectedTuneNumberOfObjectConnections; i++ )
	{
	  Mix aMix = deselectedTune.getMix( i );
	  Tune otherTune = aMix.getOtherTune( deselectedTune );
	  if ( otherTune == previousTune )
	  {
	    aMix.setSelectState( SelectState.UNSELECTED );
	    view.deselectMix( aMix );
	  }
	}
	        
	// find the next object(s) to be selected and make unselectable

	view.deselectTune( deselectedTune );
	view.makeSelectedButDeselectable( previousTune );
	previousTune.setSelectable( true );

	updateSelectStateOfOtherTunes( previousTune );
  }	      
}
/**
 * Insert the method's description here.
 * Creation date: (03/10/01 12:22:32)
 * @return java.util.Vector
 */
public java.util.Vector getMixRoute() {
	return mixRoute;
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 16:47:07)
 * @return com.selectamix.client.common.model.businesslogic.MixNetworkSelectionView
 */
public MixNetworkSelectionView getView() {
	return view;
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 12:47:22)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
public void selectTune(Tune selectedTune) 
{
  // if not first tune selected, i.e. next tune in the mix
  if ( mixRoute.size() > 0 )
  {
	// get other tunes connected to last selected tune
	// and find out if the tune clicked on is one of them
	// if is then it can be selected, if it is not, then it can't
	Mix nodeConnectionRequired = null;
	boolean foundNodeConnection = false;
	Tune lastSelectedNode = (Tune) mixRoute.lastElement(); // last selected tune
	int numberOfNodeConnections = lastSelectedNode.getNumberOfMixes();
	int i = 0;
	while ( i<numberOfNodeConnections && foundNodeConnection == false)
	{     
	  Mix ocg = (Mix) lastSelectedNode.getMix( i );
	  if ( ocg.getOtherTune( lastSelectedNode ) == selectedTune )
	  {
	    nodeConnectionRequired = ocg;
		// now check that the connection between the last selected object and the object
		// clicked on is going towards the object clicked on
		if ( nodeConnectionRequired.getDirectionRelativeTo( selectedTune ) == Direction.IN )
	    {
		  foundNodeConnection = true; break;
		}
	  }
	  else
	  {
		i++;
	  }
	}
	
	// object clicked on is connected to the last selected object
	if ( foundNodeConnection )
	{
	  mixRoute.addElement( selectedTune );
	  playingTimeLimits.addTune();
	  lastSelectedNode.setSelectable( false );
	  view.makeSelectedButNotDeselectable( lastSelectedNode );
	  // add selected tune to mix
	  nodeConnectionRequired.setSelectState( SelectState.SELECTED );
	  selectedTune.setSelectState( SelectState.SELECTED );
	  //lastSelectedNode.selectOutgoingMix( nodeConnectionRequired );
	  selectedTune.setSelectable( true );
	  view.selectTune( selectedTune );
	  view.selectMix( nodeConnectionRequired );


	  updateSelectStateOfOtherTunes( selectedTune );
	} // end if found Object Connection
  }
  else // first tune in the mix
  {  
	if ( selectedTune.isSelected() == false )
	{
	  mixRoute.addElement( selectedTune );
	  playingTimeLimits.addTune();
	  selectedTune.setSelectState( SelectState.SELECTED );
	  selectedTune.setSelectable( true );
	  view.selectTune( selectedTune );

	  updateSelectStateOfOtherTunes( selectedTune );
	} // end if object graphic select state = unselected
  } // end check number of tunes in mixRoute
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 16:47:07)
 * @param newView com.selectamix.client.common.model.businesslogic.MixNetworkSelectionView
 */
public void setView(MixNetworkSelectionView newView) {
	view = newView;
}
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 12:49:11)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
private void updateSelectStateOfOtherTunes(Tune lastSelectedTune) 
{
  // iterate through entire (or visible in window) list of
  // object graphics
  // for each object graphic, see if it is selectable from
  // the og (direction from the og is  ? IN/OUT?)
  // if it is mark its tuneSelectButton the same as its parent
  // (consider loop backs?)
  // if it isn't, mark its tuneSelectButton as UNSELECTABLE

  int numberOfTunes = database.tunes.size();


  if ( playingTimeLimits.isValid() )
  {
	// max number of tunes reached - cannot select any more
	// set all tunes to unselectable except for the last one selected
	for ( int tunesIterator = 0; 
	      tunesIterator < numberOfTunes;
	      tunesIterator++ )
	{
	  Tune aTune = ((Tune) database.tunes.elementAt( tunesIterator ) );
	  if ( aTune != lastSelectedTune )
	  {
		if ( aTune.isSelected() == false)
		{
		  aTune.setSelectable( false );
		  view.makeUnselectedButNotSelectable( aTune );
		}
	  }
	  else
	  {
	    //
	  }
	}
	return;
  }


  int lastSelectedTuneNumberOfMixes = lastSelectedTune.getNumberOfMixes();
  for ( int tunesIterator = 0; 
	    tunesIterator < numberOfTunes;
	    tunesIterator++ )
  {
	// get next tune from database
	Tune aTune = ((Tune) database.tunes.elementAt( tunesIterator ) ); 

	// looking at tunes other than the lastSelectedTune
	if ( aTune != lastSelectedTune )
	{
	  boolean aTuneIsConnectedToLastSelectedTune = false;
	  for ( int tuneConnectionIterator = 0;
	        tuneConnectionIterator < lastSelectedTuneNumberOfMixes && aTuneIsConnectedToLastSelectedTune == false;
	        tuneConnectionIterator++ )
	  {
	    // only looking at each mix coming OUT of lastSelectedTune
	    Mix aMixOnLastSelectedTune = lastSelectedTune.getMix( tuneConnectionIterator );
	    Tune otherTune = aMixOnLastSelectedTune.getOtherTune( lastSelectedTune );

	    if ( aMixOnLastSelectedTune.getDirectionRelativeTo( lastSelectedTune ) == Direction.OUT )
	    {
		  if ( otherTune == aTune )
		  {
			// tune is connected to lastSelectedTune
			aTuneIsConnectedToLastSelectedTune = true;
			
			// make selectable
			if ( aTune.isSelected() )
			{
			  aTune.setSelectable( false );
			  view.makeSelectedButNotDeselectable( aTune );
			}
			else
			{
			  aTune.setSelectable( true );
			  view.makeUnselectedButSelectable( aTune );
			}
		  }
	    }
	    else // mix is incoming - can't select tune at other end
	    {
		  if ( otherTune.isSelected() )
	      {
		    otherTune.setSelectable( false );
		    view.makeSelectedButNotDeselectable( otherTune );
		  }
		  else
		  {
		    otherTune.setSelectable( false );
		    view.makeUnselectedButNotSelectable( otherTune );
		  }
	    }
	  }
		 
	  // check if tune is not connected to lastSelectedTune
	  if ( aTuneIsConnectedToLastSelectedTune == false )
	  {
		if ( aTune.isSelected() )
	    {
		  aTune.setSelectable( false );
		  view.makeSelectedButNotDeselectable( aTune );
	    }
		else
		{
		  aTune.setSelectable( false );
		  view.makeUnselectedButNotSelectable( aTune );
		}
	  } // end check through all mixes on lastSelectedTune
	} // end omit check lastSelectedTune in checking all the other tunes
  } 
}
}
