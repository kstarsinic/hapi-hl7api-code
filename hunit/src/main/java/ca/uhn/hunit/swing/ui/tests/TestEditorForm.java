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
 * TestEditorForm.java
 *
 * Created on 13-Oct-2009, 8:30:53 PM
 */
package ca.uhn.hunit.swing.ui.tests;

import ca.uhn.hunit.event.AbstractEvent;
import ca.uhn.hunit.event.expect.Hl7V2ExpectSpecificMessageImpl;
import ca.uhn.hunit.event.send.Hl7V2SendMessageImpl;
import ca.uhn.hunit.l10n.Colours;
import ca.uhn.hunit.swing.controller.ctx.TestEditorController;
import ca.uhn.hunit.swing.ui.AbstractContextForm;
import ca.uhn.hunit.swing.ui.event.AbstractEventEditorForm;
import ca.uhn.hunit.swing.ui.event.EventEditorDefaultPane;
import ca.uhn.hunit.swing.ui.event.expect.Hl7V2ExpectSpecificMessageEditorForm;
import ca.uhn.hunit.swing.ui.event.send.Hl7V2SendMessageEditorForm;
import java.beans.PropertyVetoException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author James
 */
public class TestEditorForm extends AbstractContextForm<TestEditorController> implements ListSelectionListener {

    private static final long serialVersionUID = 1L;
    private TestEditorController myController;
    private int myCurrentlySelectedRow = -2;

    /** Creates new form TestEditorForm */
    public TestEditorForm() {
        initComponents();

        myEventsTable.setDefaultRenderer(Object.class, new EventListTableCellRenderer());
        ((BasicSplitPaneUI) mySplitPane.getUI()).getDivider().setBorder(null);

        myNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                nameValueChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                nameValueChanged();
            }

            public void changedUpdate(DocumentEvent e) {
                nameValueChanged();
            }
        });
    }

    private void nameValueChanged() {
        try {
            myController.getTest().setName(myNameTextField.getText());
            myNameTextField.setBackground(Colours.getTextFieldOk());
        } catch (PropertyVetoException ex) {
            myNameTextField.setBackground(Colours.getTextFieldError());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        myNameTextField = new javax.swing.JTextField();
        mySplitPane = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        myAddEventButton = new javax.swing.JButton();
        myUpButton = new javax.swing.JButton();
        myDownButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        myEventsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        myEventEditorScrollPane = new javax.swing.JScrollPane();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Test ID"));

        jLabel1.setText("Name");

        myNameTextField.setText("jTextField1");
        myNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myNameTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(myNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(myNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mySplitPane.setBorder(null);
        mySplitPane.setDividerLocation(250);
        mySplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ca/uhn/hunit/l10n/UiStrings"); // NOI18N
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("eventlist.border"))); // NOI18N

        jToolBar2.setBorder(null);
        jToolBar2.setRollover(true);

        myAddEventButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ca/uhn/hunit/ui/resources/images/add.png"))); // NOI18N
        myAddEventButton.setText(bundle.getString("listeditor.add")); // NOI18N
        myAddEventButton.setFocusable(false);
        myAddEventButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        myAddEventButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(myAddEventButton);

        myUpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ca/uhn/hunit/ui/resources/images/move_task_up.png"))); // NOI18N
        myUpButton.setText(bundle.getString("listeditor.up")); // NOI18N
        myUpButton.setFocusable(false);
        myUpButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        myUpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        myUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myUpButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(myUpButton);

        myDownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ca/uhn/hunit/ui/resources/images/move_task_down.png"))); // NOI18N
        myDownButton.setText(bundle.getString("listeditor.down")); // NOI18N
        myDownButton.setFocusable(false);
        myDownButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        myDownButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        myDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myDownButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(myDownButton);

        myEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        myEventsTable.setShowVerticalLines(false);
        jScrollPane1.setViewportView(myEventsTable);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(bundle.getString("eventlist.instructions")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mySplitPane.setTopComponent(jPanel2);

        myEventEditorScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("eventeditor.bordertitle"))); // NOI18N
        mySplitPane.setRightComponent(myEventEditorScrollPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mySplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mySplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void myNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myNameTextFieldActionPerformed
    }//GEN-LAST:event_myNameTextFieldActionPerformed

    private void myUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myUpButtonActionPerformed
        myController.getTest().getEventsModel().moveUp(getSelectedEvent());
    }//GEN-LAST:event_myUpButtonActionPerformed

    private void myDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myDownButtonActionPerformed
        myController.getTest().getEventsModel().moveDown(getSelectedEvent());
    }//GEN-LAST:event_myDownButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton myAddEventButton;
    private javax.swing.JButton myDownButton;
    private javax.swing.JScrollPane myEventEditorScrollPane;
    private javax.swing.JTable myEventsTable;
    private javax.swing.JTextField myNameTextField;
    private javax.swing.JSplitPane mySplitPane;
    private javax.swing.JButton myUpButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setController(TestEditorController theController) {
        myController = theController;

        myEventsTable.setModel(myController.getTest().getEventsModel());
        myEventsTable.getSelectionModel().addListSelectionListener(this);

        myNameTextField.setText(myController.getTest().getName());

        updateEditorPane();
    }

    @Override
    public void tearDown() {
        myEventsTable.getSelectionModel().removeListSelectionListener(this);
    }

    public void valueChanged(ListSelectionEvent e) {
        updateEditorPane();

    }

    public AbstractEvent getSelectedEvent() {
        int selectedRow = myEventsTable.getSelectedRow();
        if (selectedRow != -1) {
            return myController.getTest().getEventsModel().getEvent(selectedRow);
        } else {
            return null;
        }

    }

    private void updateEditorPane() {
        int selectedRow = myEventsTable.getSelectedRow();

        if (selectedRow == myCurrentlySelectedRow) {
            return;
        }
        myCurrentlySelectedRow = selectedRow;

        AbstractEventEditorForm editorForm = null;
        if (selectedRow != -1) {

            AbstractEvent event = myController.getTest().getEventsModel().getEvent(selectedRow);
            if (event instanceof Hl7V2ExpectSpecificMessageImpl) {
                editorForm = new Hl7V2ExpectSpecificMessageEditorForm();
                editorForm.setController(myController, myController.getTest().getBattery(), (Hl7V2ExpectSpecificMessageImpl) event);
            } else if (event instanceof Hl7V2SendMessageImpl) {
                editorForm = new Hl7V2SendMessageEditorForm();
                editorForm.setController(myController, myController.getTest().getBattery(), (Hl7V2SendMessageImpl) event);
            }

            myUpButton.setEnabled(true);
            myDownButton.setEnabled(true);

        } else {

            myUpButton.setEnabled(false);
            myDownButton.setEnabled(false);

        }


        if (editorForm == null) {
            editorForm = new EventEditorDefaultPane();
        }
        myEventEditorScrollPane.setViewportView(editorForm);
        myEventEditorScrollPane.validate();
    }
}
