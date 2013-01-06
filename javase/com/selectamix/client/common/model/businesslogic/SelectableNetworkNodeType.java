package com.selectamix.client.common.model.businesslogic;

/**
 * Insert the type's description here.
 * Creation date: (07/10/01 17:27:14)
 * @author: 
 */
public interface SelectableNetworkNodeType {
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 09:57:13)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void makeSelectedButDeselectable();
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 09:58:16)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void makeSelectedButNotDeselectable();
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 10:31:19)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void makeUnselectedButNotSelectable();
/**
 * Insert the method's description here.
 * Creation date: (27/09/01 09:56:10)
 * @param tune com.selectamix.client.common.model.data.Tune
 */
void makeUnselectedButSelectable();
}
