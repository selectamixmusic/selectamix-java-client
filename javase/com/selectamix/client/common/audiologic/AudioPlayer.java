package com.selectamix.client.common.audiologic;

/**
 * Insert the type's description here.
 * Creation date: (29/08/01 21:01:41)
 * @author: 
 */

import com.selectamix.client.common.model.data.Tune;
import com.selectamix.client.common.model.data.Mix;

import java.util.Vector;
 
public interface AudioPlayer {
/**
 * Insert the method's description here.
 * Creation date: (17/07/01 14:21:21)
 * @param mixList java.util.Vector
 */
public void playMix(Vector mixList);
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 19:24:26)
 * @param mix database.Mix
 */
public void playMixClip(Mix mix);
/**
 * Insert the method's description here.
 * Creation date: (25/07/01 19:17:54)
 * @param tune database.Tune
 */
public void playTuneClip(Tune tune);
}
