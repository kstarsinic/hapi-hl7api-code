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
package ca.uhn.hunit.msg;

import ca.uhn.hunit.iface.TestMessage;
import ca.uhn.hunit.xsd.MessageDefinition;
import java.beans.PropertyVetoException;

public abstract class AbstractMessage<T> {

	protected String myId;

	public AbstractMessage(MessageDefinition theConfig) {
		myId = theConfig.getId();
	}

	public AbstractMessage(String theId) {
		myId = theId;
	}

    public abstract TestMessage<T> getTestMessage();

    public abstract Class<? extends T> getMessageClass();

    public abstract String getSourceMessage();

    public abstract void setSourceMessage(String theSourceMessage) throws PropertyVetoException;

	public String getId() {
		return myId;
	}

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractMessage<T> other = (AbstractMessage<T>) obj;
        if ((this.myId == null) ? (other.myId != null) : !this.myId.equals(other.myId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.myId != null ? this.myId.hashCode() : 0);
        return hash;
    }

    
}