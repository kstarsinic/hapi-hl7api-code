/**
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Original Code is ""  Description:
 * ""
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
 *
 * Contributor(s): ______________________________________.
 *
 * Alternatively, the contents of this file may be used under the terms of the
 * GNU General Public License (the  "GPL"), in which case the provisions of the GPL are
 * applicable instead of those above.  If you wish to allow use of your version of this
 * file only under the terms of the GPL and not to allow others to use your version
 * of this file under the MPL, indicate your decision by deleting  the provisions above
 * and replace  them with the notice and other provisions required by the GPL License.
 * If you do not delete the provisions above, a recipient may use your version of
 * this file under either the MPL or the GPL.
 */
package ca.uhn.hl7v2.testpanel.ui.v2tree;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;

import ca.uhn.hl7v2.testpanel.ui.v2tree.Hl7V2MessageTree.TreeNodeSegment;
import ca.uhn.hl7v2.testpanel.ui.v2tree.Hl7V2MessageTree.TreeNodeType;

public class ValueCellEditor extends DefaultCellEditor {

	public ValueCellEditor(Font theFont) {
		super(createTextField(theFont));
		setClickCountToStart(1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.DefaultCellEditor#getTreeCellEditorComponent(javax.swing.
	 * JTree, java.lang.Object, boolean, boolean, boolean, int)
	 */
	@Override
	public Component getTreeCellEditorComponent(JTree theTree, Object theValue, boolean theIsSelected, boolean theExpanded, boolean theLeaf, int theRow) {
		theValue = convertValue(theValue);
		return super.getTreeCellEditorComponent(theTree, theValue, theIsSelected, theExpanded, theLeaf, theRow);
	}

	private Object convertValue(Object theValue) {

		if (theValue instanceof TreeNodeSegment) {
			theValue = ((TreeNodeSegment) theValue).getPipeEncodedValue();
		} else if (theValue instanceof TreeNodeType) {
			theValue = ((TreeNodeType) theValue).getPipeEncodedValue();
		}

		return theValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.DefaultCellEditor#getTableCellEditorComponent(javax.swing
	 * .JTable, java.lang.Object, boolean, int, int)
	 */
	@Override
	public Component getTableCellEditorComponent(JTable theTable, Object theValue, boolean theIsSelected, int theRow, int theColumn) {
		theValue = convertValue(theValue);
		return super.getTableCellEditorComponent(theTable, theValue, theIsSelected, theRow, theColumn);
	}

	private static JTextField createTextField(Font theFont) {
		JTextField field = new JTextField();
		field.setFont(theFont);
		return field;
	}

}
