package com.selectamix.client.common.ui.components;

/**
 * Insert the type's description here.
 * Creation date: (10/02/01 14:39:58)
 * @author: 
 */
import com.selectamix.client.common.model.data.Tune;
import java.awt.Graphics;
import com.selectamix.client.common.ui.components.button.TuneSelectButton;
import com.selectamix.client.common.ui.components.button.TunePlayButton;
 
public class TuneButtonPanel {
	private TuneSelectButton tuneSelectButton = null;
	private TunePlayButton tunePlayButton = null;
	private Tune tune = null;
/**
 * TuneButtonPanel constructor comment.
 */
public TuneButtonPanel( Tune aTune ) {
	super();
	tune = aTune;
	tuneSelectButton = new TuneSelectButton( null );
	tunePlayButton = new TunePlayButton( null, null );
}
/**
 * Insert the method's description here.
 * Creation date: (10/02/01 14:57:09)
 * @return database.Tune
 */
public Tune getTune() {
	return tune;
}
/**
 * Insert the method's description here.
 * Creation date: (10/02/01 14:43:36)
 * @return userinterface.TunePlayButton
 */
public TunePlayButton getTunePlayButton() {
	return tunePlayButton;
}
/**
 * Insert the method's description here.
 * Creation date: (10/02/01 14:42:52)
 * @return userinterface.TuneSelectButton
 */
public TuneSelectButton getTuneSelectButton() {
	return tuneSelectButton;
}
}
