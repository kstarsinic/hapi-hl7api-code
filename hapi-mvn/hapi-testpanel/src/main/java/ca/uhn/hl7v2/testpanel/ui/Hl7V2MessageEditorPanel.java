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
package ca.uhn.hl7v2.testpanel.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import jsyntaxpane.DefaultSyntaxKit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uhn.hl7v2.conf.spec.RuntimeProfile;
import ca.uhn.hl7v2.testpanel.controller.Controller;
import ca.uhn.hl7v2.testpanel.controller.Prefs;
import ca.uhn.hl7v2.testpanel.model.OutboundConnection;
import ca.uhn.hl7v2.testpanel.model.OutboundConnectionList;
import ca.uhn.hl7v2.testpanel.model.msg.AbstractMessage;
import ca.uhn.hl7v2.testpanel.model.msg.Hl7V2MessageCollection;
import ca.uhn.hl7v2.testpanel.ui.v2tree.Hl7V2MessageTree;
import ca.uhn.hl7v2.testpanel.util.IOkCancelCallback;
import ca.uhn.hl7v2.testpanel.util.Range;
import ca.uhn.hl7v2.testpanel.xsd.Hl7V2EncodingTypeEnum;
import ca.uhn.hl7v2.validation.impl.DefaultValidation;
import java.awt.Insets;

public class Hl7V2MessageEditorPanel extends BaseMainPanel implements IDestroyable {
	private static final String CREATE_NEW_CONNECTION = "Create New Connection...";
	private static final String NO_CONNECTIONS = "No Connections";
	private static final Logger ourLog = LoggerFactory.getLogger(Hl7V2MessageEditorPanel.class);

	static {

		System.setProperty("DefaultFont", "ARIAL-PLAIN-13");

		try {
			DefaultSyntaxKit.initKit();
			DefaultSyntaxKit.registerContentType("text/er7", Er7SyntaxKit.class.getName());
			DefaultSyntaxKit.setUseEditorFont(true);
			ourLog.info("Registered syntaxKit");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private JPanel bottomPanel;
	private JPanel messageEditorContainerPanel;
	private JComboBox myShowCombo;
	private Controller myController;
	private boolean myDisableCaretUpdateHandling;
	private DocumentListener myDocumentListener;
	private JToggleButton myFollowToggle;
	private JLabel mylabel;
	private JLabel mylabel_1;
	private JLabel mylabel_2;
	private JLabel mylabel_3;
	private Hl7V2MessageCollection myMessage;
	private JEditorPane myMessageEditor;
	private PropertyChangeListener myMessageListeneer;
	private JScrollPane myMessageScrollPane;
	private PropertyChangeListener myOutboundConnectionsListener;
	private JComboBox myOutboundInterfaceCombo;
	private DefaultComboBoxModel myOutboundInterfaceComboModel;
	private JComboBox myProfileCombobox;
	private ProfileComboModel myProfileComboboxModel;
	private PropertyChangeListener myRangeListener;
	private JRadioButton myRdbtnEr7;
	private JRadioButton myRdbtnXml;
	private PropertyChangeListener mySelectedPathListener;
	private JButton mySendButton;
	private JSplitPane mysplitPane;
	private JLabel myTerserPathTextField;
	private JToolBar mytoolBar;
	private Hl7V2MessageTree myTreePanel;
	private JScrollPane myTreeScrollPane;
	private JPanel treeContainerPanel;
	private JTabbedPane myTopTabBar;
	// private JPanel mySendingPanel;
	private ActivityTable mySendingActivityTable;
	private PropertyChangeListener myWindowTitleListener;
	private Component myhorizontalStrut;
	private Component myhorizontalStrut_1;
	private Component myhorizontalStrut_2;
	private boolean myOutboundInterfaceComboModelIsUpdating;
	private ArrayList<OutboundConnection> myOutboundInterfaceComboModelShadow;
	private final JPopupMenu myTerserPathPopupMenu = new JPopupMenu();
	private Component myhorizontalStrut_3;
	private boolean myHandlingProfileComboboxChange;
	private JLabel mylabel_4;

	/**
	 * Create the panel.
	 */
	public Hl7V2MessageEditorPanel(final Controller theController) {
		setBorder(null);
		myController = theController;

		ButtonGroup encGrp = new ButtonGroup();
		setLayout(new BorderLayout(0, 0));

		mysplitPane = new JSplitPane();
		mysplitPane.setResizeWeight(0.5);
		mysplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(mysplitPane);

		mysplitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent theEvt) {
				double ratio = (double)mysplitPane.getDividerLocation()/ mysplitPane.getHeight();
				ourLog.info("Resizing split to ratio: {}", ratio);
				Prefs.setHl7EditorSplit(ratio);
			}
		});
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				mysplitPane.setDividerLocation(Prefs.getHl7EditorSplit());
			}});
		
		messageEditorContainerPanel = new JPanel();
		messageEditorContainerPanel.setBorder(null);
		mysplitPane.setRightComponent(messageEditorContainerPanel);
		messageEditorContainerPanel.setLayout(new BorderLayout(0, 0));

		myMessageEditor = new JEditorPane();
		myMessageEditor.setFont(Prefs.getHl7EditorFont());

		myMessageScrollPane = new JScrollPane(myMessageEditor);
		messageEditorContainerPanel.add(myMessageScrollPane);

		JToolBar toolBar = new JToolBar();
		messageEditorContainerPanel.add(toolBar, BorderLayout.NORTH);
		toolBar.setFloatable(false);
		toolBar.setRollover(true);

		myFollowToggle = new JToggleButton("Follow");
		myFollowToggle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theController.setMessageEditorInFollowMode(myFollowToggle.isSelected());
			}
		});
		myFollowToggle.setIcon(new ImageIcon(Hl7V2MessageEditorPanel.class.getResource("/ca/uhn/hl7v2/testpanel/images/updown.png")));
		myFollowToggle.setSelected(theController.isMessageEditorInFollowMode());
		toolBar.add(myFollowToggle);

		myhorizontalStrut = Box.createHorizontalStrut(20);
		toolBar.add(myhorizontalStrut);
		
		mylabel_4 = new JLabel("Encoding");
		toolBar.add(mylabel_4);

		myRdbtnEr7 = new JRadioButton("ER7");
		myRdbtnEr7.setMargin(new Insets(1, 2, 0, 1));
		toolBar.add(myRdbtnEr7);

		myRdbtnXml = new JRadioButton("XML");
		myRdbtnXml.setMargin(new Insets(1, 5, 0, 1));
		toolBar.add(myRdbtnXml);
		encGrp.add(myRdbtnEr7);
		encGrp.add(myRdbtnXml);

		treeContainerPanel = new JPanel();
		mysplitPane.setLeftComponent(treeContainerPanel);
		treeContainerPanel.setLayout(new BorderLayout(0, 0));

		myTreePanel = new Hl7V2MessageTree(theController);
		myTreeScrollPane = new JScrollPane(myTreePanel);

		myTopTabBar = new JTabbedPane();
		treeContainerPanel.add(myTopTabBar);
		myTopTabBar.setBorder(null);
		myTopTabBar.add("Message Tree", myTreeScrollPane);

		mytoolBar = new JToolBar();
		mytoolBar.setFloatable(false);
		mytoolBar.setRollover(true);
		treeContainerPanel.add(mytoolBar, BorderLayout.NORTH);

		myOutboundInterfaceCombo = new JComboBox();
		myOutboundInterfaceComboModel = new DefaultComboBoxModel();

		mylabel_1 = new JLabel("Send");
		mytoolBar.add(mylabel_1);
		myOutboundInterfaceCombo.setModel(myOutboundInterfaceComboModel);
		myOutboundInterfaceCombo.setMaximumSize(new Dimension(200, 32767));
		mytoolBar.add(myOutboundInterfaceCombo);

		mySendButton = new JButton("Send");
		mySendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// int selectedIndex =
				// myOutboundInterfaceComboModel.getIndexOf(myOutboundInterfaceComboModel.getSelectedItem());
				int selectedIndex = myOutboundInterfaceCombo.getSelectedIndex();
				OutboundConnection connection = myController.getOutboundConnectionList().getConnections().get(selectedIndex);
				activateSendingActivityTabForConnection(connection);
				List<AbstractMessage<?>> messages = myMessage.getMessages();
				myController.sendMessages(connection, messages);
			}
		});

		myhorizontalStrut_3 = Box.createHorizontalStrut(20);
		myhorizontalStrut_3.setPreferredSize(new Dimension(2, 0));
		myhorizontalStrut_3.setMinimumSize(new Dimension(2, 0));
		myhorizontalStrut_3.setMaximumSize(new Dimension(2, 32767));
		mytoolBar.add(myhorizontalStrut_3);
		mySendButton.setIcon(new ImageIcon(Hl7V2MessageEditorPanel.class.getResource("/ca/uhn/hl7v2/testpanel/images/button_execute.png")));
		mytoolBar.add(mySendButton);

		myhorizontalStrut_1 = Box.createHorizontalStrut(20);
		mytoolBar.add(myhorizontalStrut_1);

		myProfileCombobox = new JComboBox();
		myProfileCombobox.setPreferredSize(new Dimension(200, 27));
		myProfileCombobox.setMinimumSize(new Dimension(200, 27));
		myProfileCombobox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (myHandlingProfileComboboxChange) {
					return;
				}

				myHandlingProfileComboboxChange = true;
				try {
					if (myProfileCombobox.getSelectedIndex() == 0) {
						myMessage.setValidationContext(null);
					} else if (myProfileCombobox.getSelectedIndex() == 1) {
						myMessage.setValidationContext(new DefaultValidation());
					} else if (myProfileCombobox.getSelectedItem() == ProfileComboModel.APPLY_CONFORMANCE_PROFILE) {
						IOkCancelCallback<Void> callback = new IOkCancelCallback<Void>() {
							public void ok(Void theArg) {
								myProfileComboboxModel.update();
							}

							public void cancel(Void theArg) {
								myProfileCombobox.setSelectedIndex(0);
							}
						};
						myController.chooseAndLoadConformanceProfileForMessage(myMessage, callback);
					}
				} finally {
					myHandlingProfileComboboxChange = false;
				}
			}
		});
		myProfileCombobox.setMaximumSize(new Dimension(300, 32767));
		myProfileComboboxModel = new ProfileComboModel();

		mylabel_2 = new JLabel("Validate");
		mytoolBar.add(mylabel_2);
		myProfileCombobox.setModel(myProfileComboboxModel);

		mytoolBar.add(myProfileCombobox);

		myhorizontalStrut_2 = Box.createHorizontalStrut(20);
		mytoolBar.add(myhorizontalStrut_2);

		mylabel_3 = new JLabel("Show");
		mytoolBar.add(mylabel_3);

		myShowCombo = new JComboBox();
		myShowCombo.setPreferredSize(new Dimension(110, 27));
		myShowCombo.setMinimumSize(new Dimension(110, 27));
		myShowCombo.setMaximumSize(new Dimension(130, 32767));
		myShowCombo.setModel(new ShowComboModel());
		mytoolBar.add(myShowCombo);

		// mySendingPanel = new JPanel();
		// mySendingPanel.setBorder(null);
		// myTopTabBar.addTab("Sending", null, mySendingPanel, null);
		// mySendingPanel.setLayout(new BorderLayout(0, 0));

		mySendingActivityTable = new ActivityTable();
		mySendingActivityTable.setController(myController);
		myTopTabBar.addTab("Sending", null, mySendingActivityTable, null);

		// mySendingPanelScrollPanel = new JScrollPane();
		// mySendingPanelScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		// mySendingPanelScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		// mySendingPanelScrollPanel.setColumnHeaderView(mySendingActivityTable);
		//
		// mySendingPanel.add(mySendingPanelScrollPanel, BorderLayout.CENTER);

		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(10, 20));
		bottomPanel.setMinimumSize(new Dimension(10, 20));
		add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[] { 98, 74, 0 };
		gbl_bottomPanel.rowHeights = new int[] { 16, 0 };
		gbl_bottomPanel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_bottomPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		bottomPanel.setLayout(gbl_bottomPanel);

		mylabel = new JLabel("Terser Path:");
		mylabel.setHorizontalTextPosition(SwingConstants.LEFT);
		mylabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.weighty = 1.0;
		gbc_label.anchor = GridBagConstraints.NORTHWEST;
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		bottomPanel.add(mylabel, gbc_label);

		myTerserPathTextField = new JLabel();
		myTerserPathTextField.setForeground(Color.BLUE);
		myTerserPathTextField.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		myTerserPathTextField.setBorder(null);
		myTerserPathTextField.setBackground(SystemColor.control);
		myTerserPathTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (StringUtils.isNotEmpty(myTerserPathTextField.getText())) {
					myTerserPathPopupMenu.show(myTerserPathTextField, 0, 0);
				}
			}
		});

		GridBagConstraints gbc_TerserPathTextField = new GridBagConstraints();
		gbc_TerserPathTextField.weightx = 1.0;
		gbc_TerserPathTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_TerserPathTextField.gridx = 1;
		gbc_TerserPathTextField.gridy = 0;
		bottomPanel.add(myTerserPathTextField, gbc_TerserPathTextField);

		initLocal();

	}

	public void destroy() {
		myMessage.removePropertyChangeListener(Hl7V2MessageCollection.SOURCE_MESSAGE_PROPERTY, myMessageListeneer);
		myMessage.removePropertyChangeListener(Hl7V2MessageCollection.PROP_HIGHLITED_RANGE, myRangeListener);
		myMessage.removePropertyChangeListener(Hl7V2MessageCollection.SAVED_PROPERTY, myWindowTitleListener);
		myMessage.removePropertyChangeListener(Hl7V2MessageCollection.PROP_SAVE_FILENAME, myWindowTitleListener);

		myTreePanel.destroy();
		myController.getOutboundConnectionList().addPropertyChangeListener(OutboundConnectionList.PROP_LIST, myOutboundConnectionsListener);

	}

	private void initLocal() {

		myDocumentListener = new DocumentListener() {

			public void changedUpdate(DocumentEvent theE) {
				ourLog.info("Document change: " + theE);
				handleChange(theE);
			}

			private void handleChange(DocumentEvent theE) {
				String newSource = myMessageEditor.getText();
				int changeStart = theE.getOffset();
				int changeEnd = changeStart + theE.getLength();
				myMessage.updateSourceMessage(newSource, changeStart, changeEnd);
			}

			public void insertUpdate(DocumentEvent theE) {
				ourLog.info("Document insert: " + theE);
				handleChange(theE);
			}

			public void removeUpdate(DocumentEvent theE) {
				ourLog.info("Document removed: " + theE);
				handleChange(theE);
			}
		};
		myMessageEditor.getDocument().addDocumentListener(myDocumentListener);

		myMessageEditor.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent theE) {
				if (!myDisableCaretUpdateHandling) {
					myMessage.setHighlitedPathBasedOnRange(new Range(theE.getDot(), theE.getMark()));
				}
			}
		});

		updateOutboundConnectionsBox();
		myOutboundConnectionsListener = new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent theEvt) {
				updateOutboundConnectionsBox();
			}
		};
		myController.getOutboundConnectionList().addPropertyChangeListener(OutboundConnectionList.PROP_LIST, myOutboundConnectionsListener);

		myOutboundInterfaceCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theE) {
				if (!myOutboundInterfaceComboModelIsUpdating) {
					updateSendButton();
				}
			}

		});

		JMenuItem copyMenuItem = new JMenuItem("Copy to Clipboard");
		copyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theE) {
				String selection = myTerserPathTextField.getText();
				StringSelection data = new StringSelection(selection);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(data, data);
			}
		});
		myTerserPathPopupMenu.add(copyMenuItem);

	}

	private void updateSendButton() {
		String selected = (String) myOutboundInterfaceCombo.getSelectedItem();
		if (selected == null || selected == NO_CONNECTIONS) {
			mySendButton.setEnabled(false);
			return;
		}

		if (selected == CREATE_NEW_CONNECTION) {
			IOkCancelCallback<OutboundConnection> handler = new IOkCancelCallback<OutboundConnection>() {
				public void ok(OutboundConnection theArg) {
					myOutboundInterfaceCombo.setSelectedIndex(myOutboundInterfaceComboModel.getSize() - 2);
				}

				public void cancel(OutboundConnection theArg) {
					myOutboundInterfaceCombo.setSelectedIndex(0);
				}
			};
			myController.addOutboundConnectionToSendTo(handler);
			return;
		}

		mySendButton.setEnabled(true);
	}

	/**
	 * @param theMessage
	 *            the message to set
	 */
	public void setMessage(Hl7V2MessageCollection theMessage) {
		Validate.isTrue(myMessage == null);

		myMessage = theMessage;

		updateEncodingButtons();
		myRdbtnEr7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theE) {
				myMessage.setEncoding(Hl7V2EncodingTypeEnum.ER_7);
			}
		});
		myRdbtnXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent theE) {
				myMessage.setEncoding(Hl7V2EncodingTypeEnum.XML);
			}
		});

		try {
			myDisableCaretUpdateHandling = true;
			updateMessageEditor();
		} finally {
			myDisableCaretUpdateHandling = false;
		}

		myMessageListeneer = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent theEvt) {
				try {
					myDisableCaretUpdateHandling = true;
					updateMessageEditor();
				} finally {
					myDisableCaretUpdateHandling = false;
				}
			}
		};
		myMessage.addPropertyChangeListener(Hl7V2MessageCollection.SOURCE_MESSAGE_PROPERTY, myMessageListeneer);

		mySelectedPathListener = new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent theEvt) {
				String path = myMessage.getHighlitedPath();
				if (path == null) {
					path = "";
				}

				int index = path.indexOf('/');
				if (index > -1) {
					path = path.substring(index);
				}

				myTerserPathTextField.setText(path);
			}
		};
		myMessage.addPropertyChangeListener(Hl7V2MessageCollection.PROP_HIGHLITED_PATH, mySelectedPathListener);

		myTreePanel.setMessage(myMessage);

		myRangeListener = new PropertyChangeListener() {

			public void propertyChange(PropertyChangeEvent theEvt) {
				if (theEvt.getNewValue() == null || !myFollowToggle.isSelected()) {
					return;
				}
				Range range = (Range) theEvt.getNewValue();

				myMessageEditor.select(range.getStart(), range.getEnd());
				myMessageEditor.grabFocus();

				String substring = myMessage.getSourceMessage().substring(range.getStart(), range.getEnd());
				ourLog.info("Selected range set to " + range + " which is " + substring);
			}
		};
		myMessage.addPropertyChangeListener(Hl7V2MessageCollection.PROP_HIGHLITED_RANGE, myRangeListener);

		myProfileComboboxModel.update();

		// Window Title
		myWindowTitleListener = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent theEvt) {
				updateWindowTitle();
			}
		};
		myMessage.addPropertyChangeListener(Hl7V2MessageCollection.SAVED_PROPERTY, myWindowTitleListener);
		myMessage.addPropertyChangeListener(Hl7V2MessageCollection.PROP_SAVE_FILENAME, myWindowTitleListener);
		updateWindowTitle();

	}

	private void updateWindowTitle() {
		StringBuilder b = new StringBuilder();

		if (myMessage.isSaved() == false) {
			b.append("Unsaved");
		}

		if (b.length() > 0) {
			b.append(" - ");
		}
		if (StringUtils.isNotBlank(myMessage.getSaveFileName())) {
			b.append(myMessage.getSaveFileName());
		} else {
			b.append("New File");
		}

		setWindowTitle(b.toString());
	}

	private void updateEncodingButtons() {
		switch (myMessage.getEncoding()) {
		case XML:
			myRdbtnXml.setSelected(true);
			myRdbtnEr7.setSelected(false);
			break;
		case ER_7:
			myRdbtnXml.setSelected(false);
			myRdbtnEr7.setSelected(true);
		}
	}

	private void updateMessageEditor() {

		final JScrollBar vsb = myMessageScrollPane.getVerticalScrollBar();
		int initialVerticalValue = vsb.getValue();

		myMessageEditor.getDocument().removeDocumentListener(myDocumentListener);

		String sourceMessage = myMessage.getSourceMessage();

		if (myMessage.getEncoding() == Hl7V2EncodingTypeEnum.XML) {
			myMessageEditor.setContentType("text/xml");
		} else {
			myMessageEditor.setContentType("text/er7");
			sourceMessage = sourceMessage.replace('\r', '\n');
		}

		myMessageEditor.setText(sourceMessage);

		myMessageEditor.getDocument().addDocumentListener(myDocumentListener);

		final int verticalValue = Math.min(initialVerticalValue, vsb.getMaximum());

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
//				myMessageEditor.setFont(Prefs.getHl7EditorFont());
				vsb.setValue(verticalValue);
			}
		});

	}

	private void updateOutboundConnectionsBox() {
		int currentSelection = myOutboundInterfaceCombo.getSelectedIndex();

		List<OutboundConnection> conn = myController.getOutboundConnectionList().getConnections();

		myOutboundInterfaceComboModelIsUpdating = true;

		myOutboundInterfaceComboModel.removeAllElements();
		myOutboundInterfaceComboModelShadow = new ArrayList<OutboundConnection>();
		if (conn.isEmpty()) {
			myOutboundInterfaceComboModel.addElement(NO_CONNECTIONS);
			myOutboundInterfaceComboModelShadow.add(null);
		} else {
			for (OutboundConnection next : conn) {
				myOutboundInterfaceComboModel.addElement(next.getName());
				myOutboundInterfaceComboModelShadow.add(next);
			}
		}

		myOutboundInterfaceComboModel.addElement(CREATE_NEW_CONNECTION);
		myOutboundInterfaceComboModelShadow.add(null);

		if (currentSelection != -1 && currentSelection < myOutboundInterfaceComboModel.getSize()) {
			myOutboundInterfaceCombo.setSelectedIndex(currentSelection);
		} else {
			myOutboundInterfaceCombo.setSelectedIndex(0);
		}

		myOutboundInterfaceComboModelIsUpdating = false;

		updateSendButton();
	}

	private class ProfileComboModel extends DefaultComboBoxModel {

		private static final String APPLY_CONFORMANCE_PROFILE = "Apply Conformance Profile...";

		public void update() {
			myHandlingProfileComboboxChange = true;
			try {
				super.removeAllElements();

				addElement("No Profile/Validation");
				addElement("Default Datatype Validation (HAPI)");

				RuntimeProfile profile = myMessage.getRuntimeProfile();
				if (profile != null) {
					String name = "Conf Profile (" + profile.getMessage().getMsgType() + "^" + profile.getMessage().getEventType() + ")";
					String profName = myMessage.getRuntimeProfile().getName();
					if (StringUtils.isNotBlank(profName)) {
						name = name + ":" + profName;
					}

					addElement(name);
				}

				addElement(APPLY_CONFORMANCE_PROFILE);

				if (profile != null) {
					setSelectedItem(getElementAt(2));
				} else if (myMessage.getValidationContext() instanceof DefaultValidation) {
					setSelectedItem(getElementAt(1));
				} else {
					setSelectedItem(getElementAt(0));
				}
			} finally {
				myHandlingProfileComboboxChange = false;
			}
		}
	}

	public class ShowComboModel extends DefaultComboBoxModel implements ActionListener {
		private static final String ALL = "All";
		private static final String ERRORS = "Errors";
		private static final String POPULATED = "Populated";

		public ShowComboModel() {
			addElement(POPULATED);
			addElement(ALL);
			addElement(ERRORS);

			switch (myTreePanel.getShow()) {
			case ALL:
				setSelectedItem(ALL);
				break;
			case ERROR:
				setSelectedItem(ERRORS);
				break;
			case POPULATED:
				setSelectedItem(POPULATED);
				break;
			}

			myShowCombo.addActionListener(this);
		}

		public void actionPerformed(ActionEvent theE) {
			String value = (String) myShowCombo.getSelectedItem();

			if (value == ALL) {
				myTreePanel.setShow(ShowEnum.ALL);
			} else if (value == ERRORS) {
				myTreePanel.setShow(ShowEnum.ERROR);
			} else {
				myTreePanel.setShow(ShowEnum.POPULATED);
			}

		}
	}

	private void activateSendingActivityTabForConnection(OutboundConnection theConnection) {
		mySendingActivityTable.setConnection(theConnection, false);
		myTopTabBar.setSelectedComponent(mySendingActivityTable);
	}

}
