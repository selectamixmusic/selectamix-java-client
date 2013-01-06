package com.selectamix.client.common.ui.components.heading;

/**
 * Insert the type's description here.
 * Creation date: (30/09/01 15:33:44)
 * @author: 
 */
import java.util.Observer;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Font;
import uk.co.robertjdavis.library.graphics.heading.Heading;

public class VAHeading extends Heading implements Observer {
/**
 * VAHeader constructor comment.
 * @param hdgText java.lang.String
 * @param justifyMode int
 * @param wd int
 * @param ht int
 */
public VAHeading(String hdgText, int justifyMode, int wd, int ht) {
	super(hdgText, justifyMode, wd, ht);
}
/**
 * update method comment.
 */
public void update(java.util.Observable o, java.lang.Object arg) 
{
  Hashtable visualAttributes = (Hashtable)((Hashtable)arg).get("heading");
  setAppearance( (Color)visualAttributes.get("bkgnd"), (Color)visualAttributes.get("fgnd"), (Font)visualAttributes.get("font"));
}
}
