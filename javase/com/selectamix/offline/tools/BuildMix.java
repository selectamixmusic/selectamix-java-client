package com.selectamix.offline.tools;

import java.applet.*;
import java.awt.*;
/**
 * Insert the type's description here.
 * Creation date: (06/05/01 15:51:57)
 * @author: 
 */
public class BuildMix extends Applet {
/**
 * Returns information about this applet.
 * @return a string of information about this applet
 */
public String getAppletInfo() {
	return "BuildMix\n" + 
		"\n" + 
		"Insert the type's description here.\n" + 
		"Creation date: (06/05/01 15:51:57)\n" + 
		"@author: \n" + 
		"";
}
/**
 * Initializes the applet.
 * 
 * @see #start
 * @see #stop
 * @see #destroy
 */
public void init() {
	super.init();

	// insert code to initialize the applet here
}
/**
 * Paints the applet.
 * If the applet does not need to be painted (e.g. if it is only a container for other
 * awt components) then this method can be safely removed.
 * 
 * @param g  the specified Graphics window
 * @see #update
 */
public void paint(Graphics g) {
	super.paint(g);

	// insert code to paint the applet here
}
/**
 * Insert the method's description here.
 * Creation date: (06/05/01 15:54:09)
 */
public void start() 
{/*
  final static int riffChunkLength = 12;
  final static int formatChunkLength = 24;
  final static int riffChunkStart = 0;
  final static int formatChunkStart = riffChunkLength;
  final static int dataChunkStart = riffChunkLength + formatChunkLength;
  final static int dataChunkHeaderLength = 8;
  final static int sampleStart = dataChunkStart + dataChunkHeaderLength;

  // absolute positions
  final static int dataChunk_sampleLengthInBytes = dataChunkStart + 4;

  RandomAccessFile tune1( tune1file, "r" );
  RandomAccessFile tune2( tune2file, "r" );
  RandomAccessFile mix( mixFile, "r" );
  */
   
	/*
  // open result
  // work out length of result and write



  tune1.seek( posSampleLengthInBytes );
  int tune1SampleLengthInBytes = tune1.readInt();

  tune2.seek( posSampleLengthInBytes );
  int tune2SampleLengthInBytes = tune2.readInt();

  mix.seek( posSampleLengthInBytes );
  int mixSampleLengthInBytes = mix.readInt();
  
  
  FileInputStream 
  // open tune 1
  // read tune 1 up to where mix starts
  // write tune 1 up to where mix starts to result
  skip
  // close tune 1
  // open mix
  // read mix to end
  // write mix to end to result file
  // close mix
  // open tune 2
  // read tune 2 up to where mix starts
  // write tune 2 up to where mix starts to result
  // close tune 2

  // close result
  */
}
}
