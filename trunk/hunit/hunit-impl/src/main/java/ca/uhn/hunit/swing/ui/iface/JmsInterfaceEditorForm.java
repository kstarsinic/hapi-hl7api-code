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
 * JmsInterfaceEditorForm.java
 *
 * Created on 9-Oct-2009, 11:24:46 PM
 */

package ca.uhn.hunit.swing.ui.iface;

import ca.uhn.hunit.swing.controller.ctx.JmsInterfaceContextController;
import ca.uhn.hunit.swing.ui.AbstractContextForm;

/**
 *
 * @author James
 */
public class JmsInterfaceEditorForm extends AbstractContextForm<JmsInterfaceContextController> {

    private static final long serialVersionUID = 1;

    /** Creates new form JmsInterfaceEditorForm */
    public JmsInterfaceEditorForm() {
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

        myInterfacePropertiesBorder = new javax.swing.JPanel();
        myInterfaceForm = new ca.uhn.hunit.swing.ui.iface.InterfaceForm();
        myJmsInterfaceForm = new ca.uhn.hunit.swing.ui.iface.JmsInterfaceForm();

        myInterfacePropertiesBorder.setBorder(javax.swing.BorderFactory.createTitledBorder("Interface Properties"));

        javax.swing.GroupLayout myInterfacePropertiesBorderLayout = new javax.swing.GroupLayout(myInterfacePropertiesBorder);
        myInterfacePropertiesBorder.setLayout(myInterfacePropertiesBorderLayout);
        myInterfacePropertiesBorderLayout.setHorizontalGroup(
            myInterfacePropertiesBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myInterfaceForm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
        );
        myInterfacePropertiesBorderLayout.setVerticalGroup(
            myInterfacePropertiesBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myInterfacePropertiesBorderLayout.createSequentialGroup()
                .addComponent(myInterfaceForm, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myInterfacePropertiesBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(myJmsInterfaceForm, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(myInterfacePropertiesBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myJmsInterfaceForm, javax.swing.GroupLayout.PREFERRED_SIZE, 277, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ca.uhn.hunit.swing.ui.iface.InterfaceForm myInterfaceForm;
    private javax.swing.JPanel myInterfacePropertiesBorder;
    private ca.uhn.hunit.swing.ui.iface.JmsInterfaceForm myJmsInterfaceForm;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setController(JmsInterfaceContextController theController) {
        myInterfaceForm.setController(theController);
        myJmsInterfaceForm.setController(theController);
    }

    @Override
    public void tearDown() {
        // nothing
    }

}
