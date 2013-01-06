package com.selectamix.client.common.model.businesslogic;

/**
 * Insert the type's description here.
 * Creation date: (26/09/01 13:35:06)
 * @author: 
 */
import com.selectamix.client.common.model.data.Tune;
import com.selectamix.client.common.model.data.Mix;

public interface MixNetworkSelectionView {
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 13:38:35)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void addTune(Tune tune);
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 14:23:51)
 * @param mix com.selectamix.client.common.model.data.Mix
 */
void deselectMix(Mix mix);
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 22:48:37)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void deselectTune(Tune tune);
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 09:57:13)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void makeSelectedButDeselectable(Tune tune);
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 09:58:16)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void makeSelectedButNotDeselectable(Tune tune);
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 10:31:19)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void makeUnselectedButNotSelectable(Tune tune);
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 09:56:10)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void makeUnselectedButSelectable(Tune tune);
/**
 * Insert the method's description here.
 * Creation date: (26/09/01 14:23:37)
 * @param mix com.selectamix.client.common.model.data.Mix
 */
void selectMix(Mix mix);
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 22:48:22)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void selectTune(Tune tune);
}
