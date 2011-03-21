/**
 *
 * The contents of this file are subject to the Mozilla Public License Version 1.1
 * (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the
 * specific language governing rights and limitations under the License.
 *
 * The Initial Developer of the Original Code is University Health Network. Copyright (C)
 * 2001.  All Rights Reserved.
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InterfaceBasedEventEditorForm.java
 *
 * Created on 17-Oct-2009, 1:40:22 PM
 */

package ca.uhn.hunit.swing.ui.event;

import java.beans.PropertyVetoException;

import ca.uhn.hunit.event.AbstractEvent;
import ca.uhn.hunit.swing.controller.ctx.EventEditorContextController;
import ca.uhn.hunit.swing.model.InterfaceComboBoxModel;

/**
 *
 * @author James
 */
public class BaseEventEditorForm extends AbstractEventEditorForm {
    private static final long serialVersionUID = 1L;
    private AbstractEvent myEvent;
    private EventEditorContextController myController;

    /** Creates new form InterfaceBasedEventEditorForm */
    public BaseEventEditorForm() {
        initComponents();
    }

    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        myInterfaceCombobox = new javax.swing.JComboBox();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ca/uhn/hunit/l10n/UiStrings"); // NOI18N
        jLabel1.setText(bundle.getString("eventeditor.interface")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(55, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(55, 14));
        jLabel1.setPreferredSize(new java.awt.Dimension(55, 14));

        myInterfaceCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myInterfaceComboboxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myInterfaceCombobox, 0, 312, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(myInterfaceCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void myInterfaceComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myInterfaceComboboxActionPerformed
        try {
            final String newInterfaceId = (String) myInterfaceCombobox.getModel().getSelectedItem();
            myEvent.setInterfaceId(newInterfaceId);
        } catch (PropertyVetoException ex) {
            myInterfaceCombobox.setSelectedItem(myEvent.getInterface().getId());
        }
    }//GEN-LAST:event_myInterfaceComboboxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox myInterfaceCombobox;
    // End of variables declaration//GEN-END:variables


    /**
     * {@inheritDoc }
     */
    @Override
    public void setController(EventEditorContextController theController) {
        myController = theController;
        myEvent = theController.getEvent();

        myInterfaceCombobox.setModel(new InterfaceComboBoxModel(myController.getTest().getBattery(), myEvent));
    }

    
    /**
     * {@inheritDoc }
     */
    @Override
    public void tearDown() {
        // nothing
    }


}
