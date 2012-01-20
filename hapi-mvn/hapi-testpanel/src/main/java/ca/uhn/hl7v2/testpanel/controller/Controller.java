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
package ca.uhn.hl7v2.testpanel.controller;

import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ca.uhn.hl7v2.conf.ProfileException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.parser.DefaultModelClassFactory;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.testpanel.model.ActivityIncomingMessage;
import ca.uhn.hl7v2.testpanel.model.ActivityMessage;
import ca.uhn.hl7v2.testpanel.model.InboundConnection;
import ca.uhn.hl7v2.testpanel.model.InboundConnectionList;
import ca.uhn.hl7v2.testpanel.model.MessagesList;
import ca.uhn.hl7v2.testpanel.model.OutboundConnection;
import ca.uhn.hl7v2.testpanel.model.OutboundConnectionList;
import ca.uhn.hl7v2.testpanel.model.msg.AbstractMessage;
import ca.uhn.hl7v2.testpanel.model.msg.Comment;
import ca.uhn.hl7v2.testpanel.model.msg.Hl7V2MessageBase;
import ca.uhn.hl7v2.testpanel.model.msg.Hl7V2MessageCollection;
import ca.uhn.hl7v2.testpanel.model.msg.Hl7V2MessageEr7;
import ca.uhn.hl7v2.testpanel.model.msg.Hl7V2MessageXml;
import ca.uhn.hl7v2.testpanel.ui.AddMessageDialog;
import ca.uhn.hl7v2.testpanel.ui.CreateOutboundConnectionDialog;
import ca.uhn.hl7v2.testpanel.ui.FileChooserSaveAccessory;
import ca.uhn.hl7v2.testpanel.ui.Hl7V2MessageEditorPanel;
import ca.uhn.hl7v2.testpanel.ui.InboundConnectionPanel;
import ca.uhn.hl7v2.testpanel.ui.NothingSelectedPanel;
import ca.uhn.hl7v2.testpanel.ui.OutboundConnectionPanel;
import ca.uhn.hl7v2.testpanel.ui.TestPanelWindow;
import ca.uhn.hl7v2.testpanel.util.AllFileFilter;
import ca.uhn.hl7v2.testpanel.util.ExtensionFilter;
import ca.uhn.hl7v2.testpanel.util.FileUtils;
import ca.uhn.hl7v2.testpanel.util.IOkCancelCallback;
import ca.uhn.hl7v2.testpanel.util.LineEndingsEnum;
import ca.uhn.hl7v2.testpanel.util.PortUtil;
import ca.uhn.hl7v2.testpanel.xsd.Hl7V2EncodingTypeEnum;

public class Controller {

	private static final String DIALOG_TITLE = "TestPanel";
	private static final Logger ourLog = LoggerFactory.getLogger(Controller.class);
	private JFileChooser myConformanceProfileFileChooser;
	private InboundConnectionList myInboundConnectionList;
	private Object myLeftSelectedItem;
	private boolean myMessageEditorInFollowMode = true;
	private MessagesList myMessagesList;
	private Object myNothingSelectedMarker = new Object();
	private JFileChooser myOpenMessagesFileChooser;
	private OutboundConnectionList myOutboundConnectionList;
	private JFileChooser mySaveMessagesFileChooser;
	private FileChooserSaveAccessory mySaveMessagesFileChooserAccessory;

	private TestPanelWindow myView;
	private String myAppVersionString;

	public Controller() {
		myMessagesList = new MessagesList(this);
		try {
			File workfilesDir = Prefs.getTempWorkfilesDirectory();
			if (workfilesDir.exists() && workfilesDir.listFiles().length > 0) {
				ourLog.info("Restoring work files from directory: {}", workfilesDir.getAbsolutePath());
				myMessagesList.restoreFromWorkDirectory(workfilesDir);
			}
		} catch (IOException e1) {
			ourLog.error("Failed to restore from work direrctory", e1);
		}

		String savedOutboundList = Prefs.getOutboundConnectionList();
		if (StringUtils.isNotBlank(savedOutboundList)) {
			try {
				myOutboundConnectionList = OutboundConnectionList.fromXml(savedOutboundList);
			} catch (Exception e) {
				ourLog.error("Failed to load outbound connections from storage, going to create default value", e);
				createDefaultOutboundConnectionList();
			}
		}

		if (myOutboundConnectionList == null || myOutboundConnectionList.getConnections().isEmpty()) {
			ourLog.info("No saved outbound connection list found");
			createDefaultOutboundConnectionList();
		}

		String savedInboundList = Prefs.getInboundConnectionList();
		if (StringUtils.isNotBlank(savedInboundList)) {
			try {
				myInboundConnectionList = InboundConnectionList.fromXml(savedInboundList);
			} catch (Exception e) {
				ourLog.error("Failed to load inbound connections from storage, going to create default value", e);
				createDefaultInboundConnectionList();
			}
		}

		if (myInboundConnectionList == null || myInboundConnectionList.getConnections().isEmpty()) {
			ourLog.info("No saved inbound connection list found");
			createDefaultInboundConnectionList();
		}

	}

	public void addInboundConnection() {
		InboundConnection con = myInboundConnectionList.createDefaultConnection(provideRandomPort());

		setLeftSelectedItem(con);
		myInboundConnectionList.addConnection(con);
	}

	public void addMessage() {
		AddMessageDialog dialog = new AddMessageDialog(this);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	/**
	 * Create a new message collection with a single instantiated message and
	 * add it to the list of available messages, then select it for editing.
	 */
	public void addMessage(String theVersion, String theType, String theTrigger, String theStructure, Hl7V2EncodingTypeEnum theEncoding) {
		DefaultModelClassFactory mcf = new DefaultModelClassFactory();
		try {
			Hl7V2MessageCollection col = new Hl7V2MessageCollection();

			Class<? extends Message> messageClass = mcf.getMessageClass(theStructure, theVersion, true);
			ca.uhn.hl7v2.model.AbstractMessage message = (ca.uhn.hl7v2.model.AbstractMessage) messageClass.newInstance();
			message.initQuickstart(theType, theTrigger, "T");

			GenericParser p = new GenericParser();
			Hl7V2MessageBase msg;
			if (theEncoding == Hl7V2EncodingTypeEnum.ER_7) {
				p.setPipeParserAsPrimary();
				col.setEncoding(Hl7V2EncodingTypeEnum.ER_7);
				msg = new Hl7V2MessageEr7();
				msg.setSourceMessage(p.encode(message));
			} else {
				p.setXMLParserAsPrimary();
				col.setEncoding(Hl7V2EncodingTypeEnum.XML);
				msg = new Hl7V2MessageXml();
				msg.setSourceMessage(p.encode(message));
			}

			col.addMessage(new Comment(""));

			msg.setIndexWithinCollection(1);
			col.addMessage(msg);

			setLeftSelectedItem(col);
			myMessagesList.addMessage(col);

		} catch (Exception e) {
			handleUnexpectedError(e);
		}
	}

	public void addOutboundConnection() {
		OutboundConnection con = myOutboundConnectionList.createDefaultConnection(provideRandomPort());

		setLeftSelectedItem(con);
		myOutboundConnectionList.addConnection(con);
	}

	public void addOutboundConnectionToSendTo(final IOkCancelCallback<OutboundConnection> theHandler) {
		OutboundConnection connection = myOutboundConnectionList.createDefaultConnection(provideRandomPort());

		final IOkCancelCallback<OutboundConnection> handler = new IOkCancelCallback<OutboundConnection>() {
			public void cancel(OutboundConnection theArg) {
				theHandler.cancel(theArg);
			}

			public void ok(OutboundConnection theArg) {
				myOutboundConnectionList.addConnection(theArg);
				theHandler.ok(theArg);
			}
		};

		CreateOutboundConnectionDialog dialog = new CreateOutboundConnectionDialog(connection, handler);
		dialog.setVisible(true);
	}

	public void chooseAndLoadConformanceProfileForMessage(Hl7V2MessageCollection theMessage, IOkCancelCallback<Void> theCallback) {
		if (myConformanceProfileFileChooser == null) {
			myConformanceProfileFileChooser = new JFileChooser(Prefs.getOpenPathConformanceProfile());
			myConformanceProfileFileChooser.setDialogTitle("Choose an HL7 Conformance Profile");

			ExtensionFilter type = new ExtensionFilter("XML Files", new String[] { ".xml" });
			myConformanceProfileFileChooser.addChoosableFileFilter(type);
		}

		int value = myConformanceProfileFileChooser.showOpenDialog(myView.getMyframe());
		if (value == JFileChooser.APPROVE_OPTION) {

			File file = myConformanceProfileFileChooser.getSelectedFile();
			Prefs.setOpenPathConformanceProfile(file.getPath());

			try {
				String profileString = FileUtils.readFile(file);
				theMessage.setRuntimeProfile(profileString);

				theCallback.ok(null);

			} catch (IOException e) {
				ourLog.error("Failed to load profile", e);
				theCallback.cancel(null);
			} catch (ProfileException e) {
				ourLog.error("Failed to load profile", e);
				theCallback.cancel(null);
			}
		} else {
			theCallback.cancel(null);
		}

	}

	public void close() {

		if (!saveAllMessagesAndReturnFalseIfCancelIsPressed()) {
			return;
		}

		myOutboundConnectionList.removeNonPersistantConnections();
		ourLog.info("Saving {} outbound connection descriptors", myOutboundConnectionList.getConnections().size());
		Prefs.setOutboundConnectionList(myOutboundConnectionList.exportConfigToXml());

		myInboundConnectionList.removeNonPersistantConnections();
		ourLog.info("Saving {} inbound connection descriptors", myInboundConnectionList.getConnections().size());
		Prefs.setInboundConnectionList(myInboundConnectionList.exportConfigToXml());

		File workfilesDir;
		try {
			workfilesDir = Prefs.getTempWorkfilesDirectory();
			myMessagesList.dumpToWorkDirectory(workfilesDir);
		} catch (IOException e) {
			ourLog.error("Failed to flush work directory!", e);
		}

		myView.destroy();

		ourLog.info("TestPanel is exiting with status 0");
		System.exit(0);
	}

	public boolean saveAllMessagesAndReturnFalseIfCancelIsPressed() {

		for (Hl7V2MessageCollection next : myMessagesList.getMessages()) {
			if (next.isSaved() == false) {
				int save = showPromptToSaveMessageBeforeClosingIt(next, true);
				switch (save) {
				case JOptionPane.YES_OPTION:
					if (!saveMessages(next)) {
						return false;
					}
					break;
				case JOptionPane.NO_OPTION:
					break;
				case JOptionPane.CANCEL_OPTION:
					return false;
				default:
					// shouldn't happen
					throw new Error("invalid option:" + save);
				}
			}
		}

		return true;
	}

	public void closeMessage(Hl7V2MessageCollection theMsg) {
		if (theMsg.isSaved() == false) {
			int save = showPromptToSaveMessageBeforeClosingIt(theMsg, true);
			switch (save) {
			case JOptionPane.YES_OPTION:
				if (!saveMessages(theMsg)) {
					return;
				}
				break;
			case JOptionPane.NO_OPTION:
				break;
			case JOptionPane.CANCEL_OPTION:
				return;
			default:
				// shouldn't happen
				throw new Error("invalid option:" + save);
			}
		}

		myMessagesList.removeMessage(theMsg);
		if (myMessagesList.getMessages().size() > 0) {
			setLeftSelectedItem(myMessagesList.getMessages().get(0));
		} else {
			tryToSelectSomething();
		}
	}

	private void createDefaultInboundConnectionList() {
		myInboundConnectionList = new InboundConnectionList();
		// myInboundConnectionList.addConnection(myInboundConnectionList.createDefaultConnection(9999));
	}

	private void createDefaultOutboundConnectionList() {
		myOutboundConnectionList = new OutboundConnectionList();
		// myOutboundConnectionList.addConnection(myOutboundConnectionList.createDefaultConnection(9999));
	}

	/**
	 * @return Returns true if the file is saved
	 */
	private boolean doSave(Hl7V2MessageCollection theSelectedValue) {
		Validate.notNull(theSelectedValue);

		try {
			// BufferedWriter w = new BufferedWriter(new
			// FileWriterWithEncoding(theSelectedValue.getSaveFileName(),
			// theSelectedValue.getSaveCharset()));

			File saveFile = new File(theSelectedValue.getSaveFileName());
			FileOutputStream fos = new FileOutputStream(saveFile);
			BufferedWriter w = new BufferedWriter(new OutputStreamWriter(fos, theSelectedValue.getSaveCharset()));

			boolean saveStripComments = theSelectedValue.isSaveStripComments();
			LineEndingsEnum lineEndings = theSelectedValue.getSaveLineEndings();

			theSelectedValue.writeToFile(w, saveStripComments, lineEndings);

			w.close();
			fos.close();

			theSelectedValue.setSaveFileTimestamp(saveFile.lastModified());

			ourLog.info("Saved " + theSelectedValue.getMessages().size() + " messages to " + theSelectedValue.getSaveFileName());
			theSelectedValue.setSaved(true);
			return true;

		} catch (IOException e) {
			ourLog.error("Failed to save file", e);
			showDialogError("Failed to save file: " + e.getMessage());
			return false;
		}

	}

	public void editMessages(List<ActivityMessage> theList) {
		Validate.notEmpty(theList);

		Hl7V2MessageCollection messageCollection = new Hl7V2MessageCollection();

		int index = 0;
		for (ActivityMessage next : theList) {
			Hl7V2MessageBase nextModel;
			if (next.getEncoding() == Hl7V2EncodingTypeEnum.ER_7) {
				nextModel = new Hl7V2MessageEr7();
			} else {
				nextModel = new Hl7V2MessageXml();
			}

			nextModel.setEncoding(next.getEncoding());
			try {
				nextModel.setSourceMessage(next.getRawMessage());
			} catch (PropertyVetoException e) {
				ourLog.error("Failed to create message object", e);
				continue;
			}

			nextModel.setIndexWithinCollection(index++);

			StringBuilder b = new StringBuilder();
			if (index > 1) {
				b.append("\n");
			}

			if (next instanceof ActivityIncomingMessage) {
				b.append("Received ");
			} else {
				b.append("Sent ");
			}

			String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS").format(next.getTimestamp());
			b.append(timestamp);

			messageCollection.addComment(b.toString());

			messageCollection.addMessage(nextModel);

		}

		setLeftSelectedItem(messageCollection);
		myMessagesList.addMessage(messageCollection);

	}

	/**
	 * @return the inboundConnectionList
	 */
	public InboundConnectionList getInboundConnectionList() {
		return myInboundConnectionList;
	}

	public Object getLeftSelectedItem() {
		return myLeftSelectedItem;
	}

	public MessagesList getMessagesList() {
		return myMessagesList;
	}

	public OutboundConnectionList getOutboundConnectionList() {
		return myOutboundConnectionList;
	}

	private void handleUnexpectedError(Exception theE) {
		ourLog.error(theE.getMessage(), theE);
		showDialogError(theE.getMessage());
	}

	public boolean isMessageEditorInFollowMode() {
		return myMessageEditorInFollowMode;
	}

	public void openMessages() {
		if (myOpenMessagesFileChooser == null) {
			myOpenMessagesFileChooser = new JFileChooser(Prefs.getOpenPathMessages());
			myOpenMessagesFileChooser.setDialogTitle("Choose a file containing HL7 messages");

			FileFilter type = new ExtensionFilter("HL7 Files", new String[] { ".hl7" });
			myOpenMessagesFileChooser.addChoosableFileFilter(type);

			type = new ExtensionFilter("XML Files", new String[] { ".xml" });
			myOpenMessagesFileChooser.addChoosableFileFilter(type);

			type = new AllFileFilter();
			myOpenMessagesFileChooser.addChoosableFileFilter(type);
		}

		int value = myOpenMessagesFileChooser.showOpenDialog(myView.getMyframe());
		if (value == JFileChooser.APPROVE_OPTION) {

			File file = myOpenMessagesFileChooser.getSelectedFile();
			Prefs.setOpenPathMessages(file.getPath());

			try {
				String profileString = FileUtils.readFile(file);
				Hl7V2MessageCollection col = new Hl7V2MessageCollection();
				col.setSourceMessage(profileString);

				if (col.getMessages().isEmpty()) {
					showDialogError("No messages were found in the file");
					return;
				}

				setLeftSelectedItem(col);
				myMessagesList.addMessage(col);

			} catch (IOException e) {
				ourLog.error("Failed to load profile", e);
			}
		}

	}

	public void populateWithSampleMessageAndConnections() {
		Hl7V2MessageCollection col = new Hl7V2MessageCollection();

		String message = "MSH|^~\\&|NES|NINTENDO|TESTSYSTEM|TESTFACILITY|20010101000000||ADT^A04|Q123456789T123456789X123456|P|2.3\r"
				+ "EVN|A04|20010101000000|||^KOOPA^BOWSER^^^^^^^CURRENT\r"
				+ "PID|1||123456789|0123456789^AA^^JP|BROS^MARIO^^^^||19850101000000|M|||123 FAKE STREET^MARIO \\T\\ LUIGI BROS PLACE^TOADSTOOL KINGDOM^NES^A1B2C3^JP^HOME^^1234|1234|(555)555-0123^HOME^JP:1234567|||S|MSH|12345678|||||||0|||||N\r"
				+ "NK1|1|PEACH^PRINCESS^^^^|SO|ANOTHER CASTLE^^TOADSTOOL KINGDOM^NES^^JP|(123)555-1234|(123)555-2345|NOK|||||||||||||\r"
				+ "NK1|2|TOADSTOOL^PRINCESS^^^^|SO|YET ANOTHER CASTLE^^TOADSTOOL KINGDOM^NES^^JP|(123)555-3456|(123)555-4567|EMC|||||||||||||\r"
				+ "PV1|1|O|ABCD^EFGH^|||^^|123456^DINO^YOSHI^^^^^^MSRM^CURRENT^^^NEIGHBOURHOOD DR NBR^|^DOG^DUCKHUNT^^^^^^^CURRENT||CRD|||||||123456^DINO^YOSHI^^^^^^MSRM^CURRENT^^^NEIGHBOURHOOD DR NBR^|AO|0123456789|1|||||||||||||||||||MSH||A|||20010101000000\r"
				+ "IN1|1|PAR^PARENT||||LUIGI\r" + "IN1|2|FRI^FRIEND||||PRINCESS";
		col.setEncoding(Hl7V2EncodingTypeEnum.ER_7);
		col.setSourceMessage(message);
		myMessagesList.addMessage(col);

		int port = PortUtil.findFreePort();

		InboundConnection iCon = myInboundConnectionList.createDefaultConnection(port);
		iCon.setPersistent(true);
		myInboundConnectionList.addConnection(iCon);

		OutboundConnection oCon = myOutboundConnectionList.createDefaultConnection(port);
		oCon.setPersistent(true);
		myOutboundConnectionList.addConnection(oCon);

		setLeftSelectedItem(col);
	}

	/**
	 * Provide a random, currently unused port
	 */
	private int provideRandomPort() {
		ServerSocket server;
		try {
			server = new ServerSocket(0);
			int port = server.getLocalPort();
			server.close();
			return port;
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	public void removeInboundConnection(InboundConnection theConnection) {
		myInboundConnectionList.removeConnecion(theConnection);
		if (myInboundConnectionList.getConnections().size() > 0) {
			setLeftSelectedItem(myInboundConnectionList.getConnections().get(0));
		} else {
			tryToSelectSomething();
		}
	}

	public void removeOutboundConnection(OutboundConnection theConnection) {
		myOutboundConnectionList.removeConnecion(theConnection);
		if (myOutboundConnectionList.getConnections().size() > 0) {
			setLeftSelectedItem(myOutboundConnectionList.getConnections().get(0));
		} else {
			tryToSelectSomething();
		}
	}

	public boolean saveMessages(Hl7V2MessageCollection theSelectedValue) {
		Validate.notNull(theSelectedValue);

		if (theSelectedValue.getSaveFileName() == null) {
			return saveMessagesAs(theSelectedValue);
		} else {
			return doSave(theSelectedValue);
		}
	}

	/**
	 * Prompt for a filename and save the currently selected messages
	 * 
	 * @return Returns true if the file is saved
	 */
	public boolean saveMessagesAs(Hl7V2MessageCollection theSelectedValue) {
		Validate.notNull(theSelectedValue);

		if (mySaveMessagesFileChooser == null) {
			mySaveMessagesFileChooser = new JFileChooser(Prefs.getSavePathMessages());
			mySaveMessagesFileChooser.setDialogTitle("Choose a file to save the current message(s) to");
			mySaveMessagesFileChooserAccessory = new FileChooserSaveAccessory();
			mySaveMessagesFileChooser.setAccessory(mySaveMessagesFileChooserAccessory);

			FileFilter type = new ExtensionFilter("HL7 Files", new String[] { ".hl7" });
			mySaveMessagesFileChooser.addChoosableFileFilter(type);

			type = new ExtensionFilter("XML Files", new String[] { ".xml" });
			mySaveMessagesFileChooser.addChoosableFileFilter(type);

			type = new AllFileFilter();
			mySaveMessagesFileChooser.addChoosableFileFilter(type);

			mySaveMessagesFileChooser.setPreferredSize(new Dimension(700, 500));
		}

		int value = mySaveMessagesFileChooser.showSaveDialog(myView.getMyframe());
		if (value == JFileChooser.APPROVE_OPTION) {

			File file = mySaveMessagesFileChooser.getSelectedFile();
			Prefs.setSavePathMessages(file.getPath());

			if (!file.getName().contains(".")) {
				switch (theSelectedValue.getEncoding()) {
				case ER_7:
					file = new File(file.getAbsolutePath() + ".hl7");
					break;
				case XML:
					file = new File(file.getAbsolutePath() + ".xml");
					break;
				}
			}

			if (file.exists()) {
				String message = "The file \"" + file.getName() + "\" already exists. Do you wish to overwrite it?";
				int confirmed = showDialogYesNo(message);
				if (confirmed == JOptionPane.NO_OPTION) {
					return false;
				}

				ourLog.info("Deleting file: {}", file.getAbsolutePath());
				file.delete();
			}

			theSelectedValue.setSaveCharset(mySaveMessagesFileChooserAccessory.getSelectedCharset());
			theSelectedValue.setSaveFileName(file.getAbsolutePath());

			theSelectedValue.setSaveStripComments(mySaveMessagesFileChooserAccessory.isSelectedSaveStripComments());
			theSelectedValue.setSaveLineEndings(mySaveMessagesFileChooserAccessory.getSelectedLineEndings());

			doSave(theSelectedValue);
			return true;

		} else {

			return false;

		}
	}

	/**
	 * Send one or more messages out over an interface
	 */
	public void sendMessages(OutboundConnection theConnection, List<AbstractMessage<?>> theMessages) {
		theConnection.sendMessages(theMessages);
	}

	public void setLeftSelectedItem(Object theSelectedValue) {
		if (myLeftSelectedItem == theSelectedValue) {
			return;
		}
		myLeftSelectedItem = theSelectedValue;
		if (myLeftSelectedItem instanceof Hl7V2MessageCollection) {
			Hl7V2MessageEditorPanel hl7v2MessageEditorPanel = new Hl7V2MessageEditorPanel(this);
			hl7v2MessageEditorPanel.setMessage((Hl7V2MessageCollection) myLeftSelectedItem);
			myView.setMainPanel(hl7v2MessageEditorPanel);
		} else if (myLeftSelectedItem instanceof OutboundConnection) {
			OutboundConnectionPanel panel = new OutboundConnectionPanel();
			panel.setController(this);
			panel.setConnection((OutboundConnection) myLeftSelectedItem);
			myView.setMainPanel(panel);
		} else if (myLeftSelectedItem instanceof InboundConnection) {
			InboundConnectionPanel panel = new InboundConnectionPanel();
			panel.setController(this);
			panel.setConnection((InboundConnection) myLeftSelectedItem);
			myView.setMainPanel(panel);
		} else if (myLeftSelectedItem == myNothingSelectedMarker) {
			myView.setMainPanel(new NothingSelectedPanel(this));
		}
	}

	/**
	 * @param theMessageEditorInFollowMode
	 *            the messageEditorInFollowMode to set
	 */
	public void setMessageEditorInFollowMode(boolean theMessageEditorInFollowMode) {
		myMessageEditorInFollowMode = theMessageEditorInFollowMode;
	}

	public void showDialogError(String message) {
		JOptionPane.showMessageDialog(myView.getMyframe(), message, DIALOG_TITLE, JOptionPane.ERROR_MESSAGE);
	}

	public int showDialogYesNo(String message) {
		return JOptionPane.showConfirmDialog(myView.getMyframe(), message, DIALOG_TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}

	private int showPromptToSaveMessageBeforeClosingIt(Hl7V2MessageCollection theMsg, boolean theShowCancelButton) {
		Component parentComponent = myView.getMyframe();
		Object message = "<html>The following file is unsaved, do you want to save before closing?<br>" + theMsg.getBestDescription() + "</html>";
		String title = DIALOG_TITLE;
		int optionType = theShowCancelButton ? JOptionPane.YES_NO_CANCEL_OPTION : JOptionPane.YES_NO_OPTION;
		int messageType = JOptionPane.QUESTION_MESSAGE;
		return JOptionPane.showConfirmDialog(parentComponent, message, title, optionType, messageType);
	}

	public void start() {
		myView = new TestPanelWindow(this);
		myView.getFrame().setVisible(true);

		if (myMessagesList.getMessages().size() > 0) {
			setLeftSelectedItem(myMessagesList.getMessages().get(0));
		} else {
			setLeftSelectedItem(myNothingSelectedMarker);
		}
	}

	public void startAllInboundConnections() {
		ourLog.info("Starting all inbound connections");
		for (InboundConnection next : myInboundConnectionList.getConnections()) {
			next.start();
		}
	}

	public void stopAllInboundConnections() {
		ourLog.info("Stopping all inbound connections");
		for (InboundConnection next : myInboundConnectionList.getConnections()) {
			next.stop();
		}
	}

	private void tryToSelectSomething() {
		if (myMessagesList.getMessages().size() > 0) {
			setLeftSelectedItem(myMessagesList.getMessages().get(0));
		} else if (myOutboundConnectionList.getConnections().size() > 0) {
			setLeftSelectedItem(myOutboundConnectionList.getConnections().get(0));
		} else if (myInboundConnectionList.getConnections().size() > 0) {
			setLeftSelectedItem(myInboundConnectionList.getConnections().get(0));
		} else {
			setLeftSelectedItem(myNothingSelectedMarker);
		}
	}

	public boolean validateNewValue(String theTerserPath, String theNewValue) {
		String errorMsg = null;
		if (theTerserPath.endsWith("MSH-1")) {
			if (theNewValue.length() != 1) {
				errorMsg = "MSH-1 must be exactly 1 character";
			}
		}

		if (theTerserPath.endsWith("MSH-2")) {
			if (theNewValue.length() != 4) {
				errorMsg = "MSH-2 must be exactly 4 characters";
			}
		}

		if (errorMsg != null) {
			showDialogError(errorMsg);
			return false;
		}

		return true;
	}

	public void showAboutDialog() {
		myView.showAboutDialog();
	}

	public void startInboundConnection(InboundConnection theLeftSelectedItem) {
		theLeftSelectedItem.start();
	}

	public void startOutboundConnection(OutboundConnection theLeftSelectedItem) {
		theLeftSelectedItem.start();
	}

	public void startAllOutboundConnections() {
		ourLog.info("Starting all outbound connections");
		for (OutboundConnection next : myOutboundConnectionList.getConnections()) {
			next.start();
		}
	}

	public void stopAllOutboundConnections() {
		ourLog.info("Stopping all outbound connections");
		for (OutboundConnection next : myOutboundConnectionList.getConnections()) {
			next.stop();
		}
	}

	public String getAppVersionString() {
		if (myAppVersionString == null) {
			//FileUtils.loadResourceFromClasspath(thePath)
			Properties prop = new Properties();
			try {
				prop.load(Controller.class.getClassLoader().getResourceAsStream("testpanelversion.properties"));
				myAppVersionString = prop.getProperty("app.version");
			} catch (IOException e) {
				ourLog.error("Couldn't load version property", e);
				myAppVersionString = "v.UNK";
			}
		}
		return myAppVersionString;
	}

}
