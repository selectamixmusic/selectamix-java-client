package com.selectamix.client.common.audiologic;

/**
 * Insert the type's description here.
 * Creation date: (25/07/01 12:38:50)
 * @author: 
 */
import com.selectamix.client.common.model.data.Tune;
import java.util.Vector;
 
public final class PlayingTimeLimits {
	private final int MAXLIMITMODE_NONE = 0;
	private final int MAXLIMITMODE_NUMBEROFTUNES = 1;
	private final int MAXLIMITMODE_MINUTES = 2;

	private final int MINLIMITMODE_NONE = 0;
	private final int MINLIMITMODE_NUMBEROFTUNES = 1;
	private final int MINLIMITMODE_MINUTES = 2;

	private int maxLimitMode = MAXLIMITMODE_NONE;
	private int minLimitMode = MINLIMITMODE_NONE;
	private int minTunes = 0;
	private int maxTunes = 0;
	private int numTunes = 0;
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 13:14:13)
 */
public PlayingTimeLimits() 
{
	super();
	reset();
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 13:02:14)
 */
public void addTune() 
{
  numTunes++;
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 12:54:13)
 * @return int
 */
public int getMaxTunes() {
	return maxTunes;
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 12:53:52)
 * @return int
 */
public int getMinTunes() {
	return minTunes;
}
/**
 * Insert the method's description here.
 * Creation date: (04/09/01 16:57:32)
 * @return int
 */
public int getNumTunes() {
	return numTunes;
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 13:01:46)
 * @return boolean
 */
public boolean isValid() {
	boolean minIsValid = false;
	boolean maxIsValid = false;
	
	switch ( minLimitMode )
	{
	  case MINLIMITMODE_MINUTES:
	  {
	    // not implemented yet
	  }
	  break;

	  case MINLIMITMODE_NUMBEROFTUNES:
	  {
	    if ( numTunes >= minTunes ) minIsValid = true;
	  }
	  break;

	  default: { minIsValid = true; }
	}

	switch ( maxLimitMode )
	{
	  case MAXLIMITMODE_MINUTES:
	  {
	    // not implemented yet
	  }
	  break;

	  case MAXLIMITMODE_NUMBEROFTUNES:
	  {
	    if ( numTunes <= maxTunes ) maxIsValid = true;
	  }
	  break;

	  default: { maxIsValid = true; };
	}

	if ( minIsValid && maxIsValid )
	{
	  return true;
	}
	
	return false;
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 13:02:27)
 */
public void removeTune() 
{
  if ( numTunes > 0 ) 
  {
	  numTunes--;
  }
  else
  {
	  // raise exception or ignore
  }
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 13:14:02)
 */
public void reset() 
{
  numTunes = 0;
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 12:54:13)
 * @param newMaxTunes int
 */
public void setMaxTunes(int newMaxTunes) {
	maxTunes = newMaxTunes;
	maxLimitMode = MAXLIMITMODE_NUMBEROFTUNES;
}
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 12:53:52)
 * @param newMinTunes int
 */
public void setMinTunes(int newMinTunes) {
	minTunes = newMinTunes;
	minLimitMode = MINLIMITMODE_NUMBEROFTUNES;
}
}
