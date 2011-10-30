/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InterfaceEditorTabsForm.java
 *
 * Created on 19-Dec-2009, 3:08:33 PM
 */

package ca.uhn.hunit.swing.ui.iface;

import ca.uhn.hunit.iface.AbstractInterface;
import ca.uhn.hunit.swing.controller.ctx.AbstractInterfaceEditorContextController;
import ca.uhn.hunit.swing.ui.AbstractContextForm;
import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author James
 */
public class InterfaceEditorTabsForm extends AbstractContextForm<AbstractInterfaceEditorContextController<?, ?>> implements PropertyChangeListener {

    /** Creates new form InterfaceEditorTabsForm */
    public InterfaceEditorTabsForm() {
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

        myTabbedPane = new javax.swing.JTabbedPane();
        myEditorPanel = new javax.swing.JPanel();
        myRuntimePanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        myStopButton = new javax.swing.JButton();
        myStatusLabel = new javax.swing.JLabel();
        myStartButton = new javax.swing.JButton();

        myEditorPanel.setLayout(new java.awt.BorderLayout());
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ca/uhn/hunit/l10n/UiStrings"); // NOI18N
        myTabbedPane.addTab(bundle.getString("interface.editor.editor"), myEditorPanel); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));

        myStopButton.setText(bundle.getString("interface.editor.runtime.status.stop")); // NOI18N

        myStatusLabel.setText("jLabel1");

        myStartButton.setText(bundle.getString("interface.editor.runtime.status.start")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(myStopButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myStartButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(myStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(myStopButton)
                .addComponent(myStatusLabel)
                .addComponent(myStartButton))
        );

        javax.swing.GroupLayout myRuntimePanelLayout = new javax.swing.GroupLayout(myRuntimePanel);
        myRuntimePanel.setLayout(myRuntimePanelLayout);
        myRuntimePanelLayout.setHorizontalGroup(
            myRuntimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myRuntimePanelLayout.setVerticalGroup(
            myRuntimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myRuntimePanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        myTabbedPane.addTab(bundle.getString("interface.editor.runtime"), myRuntimePanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        myTabbedPane.getAccessibleContext().setAccessibleName(bundle.getString("interface.editor.editor")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel myEditorPanel;
    private javax.swing.JPanel myRuntimePanel;
    private javax.swing.JButton myStartButton;
    private javax.swing.JLabel myStatusLabel;
    private javax.swing.JButton myStopButton;
    private javax.swing.JTabbedPane myTabbedPane;
    // End of variables declaration//GEN-END:variables

    private AbstractContextForm<?> myEditorView;
    private AbstractInterfaceEditorContextController<?, ?> myController;
    
    /**
     * Provide the view for the editor tab
     */
    public void setEditorView(AbstractContextForm<?> theEditorView) {
        myEditorView = theEditorView;
        myEditorPanel.removeAll();
        myEditorPanel.add(myEditorView, BorderLayout.CENTER);

    }


    @Override
    public void setController(AbstractInterfaceEditorContextController<?, ?> theController) {
        myController = theController;
        myController.getModel().addPropertyChangeListener(AbstractInterface.INTERFACE_STARTED_PROPERTY, this);
        updateInterfaceStatus();
    }

    @Override
    public void tearDown() {
        myController.getModel().removePropertyChangeListener(AbstractInterface.INTERFACE_STARTED_PROPERTY, this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (AbstractInterface.INTERFACE_STARTED_PROPERTY.equals(evt.getPropertyName())) {
            updateInterfaceStatus();
        }
    }

    private void updateInterfaceStatus() {
        boolean started = myController.getModel().isStarted();
        myStopButton.setEnabled(started);
        myStartButton.setEnabled(!started);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("ca/uhn/hunit/l10n/UiStrings"); // NOI18N
        if (started) {
            myStatusLabel.setText(bundle.getString("interface.editor.runtime.status.running")); // NOI18N
        } else {
            myStatusLabel.setText(bundle.getString("interface.editor.runtime.status.not_running")); // NOI18N
        }
    }

}