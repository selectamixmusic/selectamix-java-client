package com.selectamix.client.common.model.data;

/**
 * Insert the type's description here.
 * Creation date: (15/09/00 19:17:55)
 * @author: 
 */

import java.util.Vector;
 
public class MixNetworkDatabase {
	public java.util.Vector mixes;
	public java.util.Vector tunes;
/**
 * MixNetworkDatabase constructor comment.
 */
public MixNetworkDatabase() {
	super();
	mixes = new Vector();
	tunes = new Vector();
	build();
}
/**
 * Insert the method's description here.
 * Creation date: (21/06/00 22:57:20)
 */
public void build() 
{ 
	System.out.println("buildMixMatrixDatabase");	
	// read in Tune and Mix info from xml
	// create Tunes
	// create Mixes and associate with Tunes

	// test data
	// create the tunes
	tunes.addElement( new Tune( "Pete Heller", "Big Love", "", "biglove.wav" ) );
	tunes.addElement( new Tune( "Big Time Charlie", "On The Run", "", "ontherun.wav" ) );
	tunes.addElement( new Tune( "Masters At Work", "To Be In Love", "", "tobeinlove.wav" ) );
	tunes.addElement( new Tune( "M J Cole", "Crazy Love", "", "crazylove.wav" ) );
	tunes.addElement( new Tune( "Shanks And Bigfoot", "Sweet Like Chocolate", "", "sweetlikechocolate.wav" ) );
	tunes.addElement( new Tune( "Doolally", "Straight From The Heart", "", "straightfromtheheart.wav" ) );
   
	// now link them up with the mixes
	mixes.addElement( new Mix( findTune( "Pete Heller", "Big Love", "" ), 0,
		                       findTune( "Big Time Charlie", "On The Run", "" ), 0, "1" ) );
	
	mixes.addElement( new Mix( findTune( "Big Time Charlie", "On The Run", "" ), 0,
		                       findTune( "Masters At Work", "To Be In Love", "" ), 0, "7" ) );
	
	mixes.addElement( new Mix( findTune( "Masters At Work", "To Be In Love", "" ), 0,
		                       findTune( "M J Cole", "Crazy Love", "" ), 0, "4" ) );
	
	mixes.addElement( new Mix( findTune( "M J Cole", "Crazy Love", "" ), 0,
		                       findTune( "Shanks And Bigfoot", "Sweet Like Chocolate", ""), 0, "3" ) );
	
	mixes.addElement( new Mix( findTune( "Shanks And Bigfoot", "Sweet Like Chocolate", "" ), 0,
		                       findTune( "Doolally", "Straight From The Heart", "" ), 0, "6" ) );
	
	mixes.addElement( new Mix( findTune( "Doolally", "Straight From The Heart", "" ), 0,
		                       findTune( "M J Cole", "Crazy Love", "" ), 0, "5" ) );
	
	mixes.addElement( new Mix( findTune( "Shanks And Bigfoot", "Sweet Like Chocolate", "" ), 0,
		                       findTune( "Masters At Work", "To Be In Love", "" ), 0, "2" ) );
	
	mixes.addElement( new Mix( findTune( "Masters At Work", "To Be In Love", "" ), 0,
		                       findTune( "Pete Heller", "Big Love", "" ), 0, "8" ) );
}
/**
 * Insert the method's description here.
 * Creation date: (08/07/00 15:38:00)
 * @return Tune
 * @param artist java.lang.String
 * @param title java.lang.String
 * @param version java.lang.String
 */
public Tune findTune(String artist, String title, String version) {
	int i = 0;
	while( i < tunes.size() )
	{
		if (    ((Tune) tunes.elementAt(i)).getArtist() == artist
		     && ((Tune) tunes.elementAt(i)).getTitle() == title
		     && ((Tune) tunes.elementAt(i)).getVersion() == version
		   )
		{
		  return ((Tune) tunes.elementAt(i));
		}
		else
		{
			i++;
		}
	}

	// not found, throw exception?
	return null;
}
}
