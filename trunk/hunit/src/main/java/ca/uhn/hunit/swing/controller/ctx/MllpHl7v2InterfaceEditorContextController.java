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

package ca.uhn.hunit.swing.controller.ctx;

import ca.uhn.hunit.iface.MllpHl7V2InterfaceImpl;
import ca.uhn.hunit.swing.ui.MllpHl7v2InterfaceEditorForm;

/**
 *
 * @author James
 */
public class MllpHl7v2InterfaceEditorContextController extends AbstractInterfaceEditorContextController<MllpHl7V2InterfaceImpl, MllpHl7v2InterfaceEditorForm> {
    private final MllpHl7v2InterfaceEditorForm myView;

    /**
     * Constructor
     */
    public MllpHl7v2InterfaceEditorContextController(MllpHl7V2InterfaceImpl theModel) {
        super(theModel);

        myView = new MllpHl7v2InterfaceEditorForm(this);
    }

    @Override
    public MllpHl7v2InterfaceEditorForm getView() {
        return myView;
    }

}
