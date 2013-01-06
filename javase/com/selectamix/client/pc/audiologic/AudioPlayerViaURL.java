package com.selectamix.client.pc.audiologic;

/**
 * Insert the type's description here.
 * Creation date: (17/07/01 14:16:56)
 * @author: 
 */

import com.selectamix.client.common.audiologic.AudioPlayer;
import java.util.Vector; // to hold list of tunes selected
import java.net.URL;
import java.net.MalformedURLException;
import com.selectamix.client.common.model.data.*;
import java.applet.*; // to be able to open a URL for the external content handles to play audio

// Plays tune and mix clips and also mixes,
// via external content handlers - MP3 for tune and mix
// clips and RealAudio for mixes 
public class AudioPlayerViaURL implements AudioPlayer {
	private Vector mixCombinations = null;
	private java.applet.Applet applet = null;
/**
 * MixPlayer constructor comment.
 */
public AudioPlayerViaURL( MixNetworkDatabase mixNetworkDatabase, Applet a ) {
	super();

	this.applet = a;

	mixCombinations = new Vector();

	mixCombinations.addElement( new Vector() );
	mixCombinations.addElement( new Vector() );
	mixCombinations.addElement( new Vector() );
	mixCombinations.addElement( new Vector() );
	mixCombinations.addElement( new Vector() );
	mixCombinations.addElement( new Vector() );

	Vector tunes = null;
	
	tunes = (Vector) mixCombinations.elementAt( 0 );
	tunes.addElement( mixNetworkDatabase.findTune( "Pete Heller", "Big Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Big Time Charlie", "On The Run", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Masters At Work", "To Be In Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "M J Cole", "Crazy Love", "" ) );

	tunes = (Vector) mixCombinations.elementAt( 1 );
	tunes.addElement( mixNetworkDatabase.findTune( "Big Time Charlie", "On The Run", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Masters At Work", "To Be In Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "M J Cole", "Crazy Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Shanks And Bigfoot", "Sweet Like Chocolate", "" ) );

	tunes = (Vector) mixCombinations.elementAt( 2 );
	tunes.addElement( mixNetworkDatabase.findTune( "M J Cole", "Crazy Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Shanks And Bigfoot", "Sweet Like Chocolate", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Masters At Work", "To Be In Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Pete Heller", "Big Love", "" ) );

	tunes = (Vector) mixCombinations.elementAt( 3 );
	tunes.addElement( mixNetworkDatabase.findTune( "Masters At Work", "To Be In Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "M J Cole", "Crazy Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Shanks And Bigfoot", "Sweet Like Chocolate", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Doolally", "Straight From The Heart", "" ) );

	tunes = (Vector) mixCombinations.elementAt( 4 );
	tunes.addElement( mixNetworkDatabase.findTune( "Shanks And Bigfoot", "Sweet Like Chocolate", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Masters At Work", "To Be In Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Pete Heller", "Big Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Big Time Charlie", "On The Run", "" ) );

	tunes = (Vector) mixCombinations.elementAt( 5 );
	tunes.addElement( mixNetworkDatabase.findTune( "Doolally", "Straight From The Heart", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "M J Cole", "Crazy Love", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Shanks And Bigfoot", "Sweet Like Chocolate", "" ) );
	tunes.addElement( mixNetworkDatabase.findTune( "Masters At Work", "To Be In Love", "" ) );
}
/**
 * Insert the method's description here.
 * Creation date: (17/07/01 14:21:21)
 * @param mixList java.util.Vector
 */
public void playMix(Vector mixList) 
{
  for ( int i = 0; i < mixCombinations.size(); i++ )
  {
	Vector tunes = (Vector) mixCombinations.elementAt( i );
	boolean isEqual = true;
	for ( int j = 0; j < tunes.size() && isEqual; j++ )
	{
	  if ( ((Tune) tunes.elementAt( j )) != ((Tune)mixList.elementAt( j )) )
	  {
	    isEqual = false; 
	  }
	}
	if ( isEqual )
	{
	  int n = i + 1;
	  System.out.println( "Mix file: " + n );
	
	  try
	  {
	    String urlString = "realaudio/mix" + n + ".rm";
	    System.out.println( urlString );
	    applet.getAppletContext().showDocument( new URL( applet.getCodeBase(), urlString ), "self" );
	    System.out.println( (new URL( applet.getCodeBase(), urlString )).toString() );
	  }
	  catch( MalformedURLException me )
	  {
		System.out.println( "error in url" );
	  }
	}
  }
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 19:24:26)
 * @param mix database.Mix
 */
public void playMixClip(Mix mix) 
{
  try
  {
	String urlString = "mix fragments/" + mix.getClipId() + ".wav";
	applet.getAppletContext().showDocument( new URL( applet.getCodeBase(), urlString ), "self" );
  }
  catch( MalformedURLException me )
  {
	System.out.println( "error in url" );
  }
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 19:17:54)
 * @param tune database.Tune
 */
public void playTuneClip(Tune tune) 
{
  try
  {
	String urlString = "tuneclips/" + tune.getClipId();
	applet.getAppletContext().showDocument( new URL( applet.getCodeBase(), urlString ), "self" );
  }
  catch( MalformedURLException me )
  {
	System.out.println( "error in url" );
  }
}
/**
 * Insert the method's description here.
 * Creation date: (17/07/01 14:58:17)
 * @param maxNumberOfTunes int
 */
private void workOutMixes(MixNetworkDatabase db, int maxNumberOfTunes, Vector mixes ) 
{
  /*
  // for each tune as a starting tune, see if a mix which
  // has number of tunes maxNumberOfTunes can be made
  for ( int i = 0; i < db.tunes.size(); i++ )
  {
	Vector mixList = new Vector();
	Tune tune = (Tune) db.tunes.elementAt( i );
	mixList.addElement( tune );
	int numTuneMixes = tune.getNumberOfMixes();
	for( int tuneMixIndex = 0; tuneMixIndex < numTuneMixes; tuneMixIndex++ )
	{
	  Mix mix = (Mix) tune.getMix( tuneMixIndex );
	  if ( mix.direction( tune ) == Mix.OUTGOING )
	  {
	    tune = mix.getIncomingTune();
	    mixList.addElement( tune );
	  }

	  // .... work tune back into outer loop -arrghh!
	}
  }

  // recursive(?): stopping case is when max tunes in mix reached or when tune has no outgoing mixes or when tune has outgoing mixes but mix into tune in use earlier by the mix
  // could re-use mix network ui updateotherobjectgraphics algorithm somehow
  */
}
}
