/**
The contents of this file are subject to the Mozilla Public License Version 1.1 
(the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.mozilla.org/MPL/ 
Software distributed under the License is distributed on an "AS IS" basis, 
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the 
specific language governing rights and limitations under the License. 

The Original Code is "RuleTypeBuilder.java".  Description: 
"RuleBuilder that determines which kind of rule shall be built" 

The Initial Developer of the Original Code is University Health Network. Copyright (C) 
2004.  All Rights Reserved. 

Contributor(s): ______________________________________. 

Alternatively, the contents of this file may be used under the terms of the 
GNU General Public License (the "GPL"), in which case the provisions of the GPL are 
applicable instead of those above.  If you wish to allow use of your version of this 
file only under the terms of the GPL and not to allow others to use your version 
of this file under the MPL, indicate your decision by deleting  the provisions above 
and replace  them with the notice and other provisions required by the GPL License.  
If you do not delete the provisions above, a recipient may use your version of 
this file under either the MPL or the GPL. 
 */
package ca.uhn.hl7v2.validation.builder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.uhn.hl7v2.Version;
import ca.uhn.hl7v2.validation.PrimitiveTypeRule;
import ca.uhn.hl7v2.validation.Rule;
import ca.uhn.hl7v2.validation.impl.RuleBinding;

/**
 * Defines the type of rule to be built.
 * 
 * @author Christian Ohr
 */
@SuppressWarnings("serial")
public class RuleTypeBuilder<T extends Rule<?>> extends ValidationRuleBuilder {

	protected Set<Version> versions;
	protected String description;
	protected String sectionReference;
	protected boolean active = true;

	protected RuleTypeBuilder(List<RuleBinding<? extends Rule<?>>> rules, Set<Version> versions) {
		super(rules);
		if (versions.size() == 0)
			throw new IllegalArgumentException("Must specify a version");
		this.versions = versions;
	}

	protected RuleTypeBuilder(List<RuleBinding<? extends Rule<?>>> rules, Version... versions) {
		super(rules);
		if (versions.length == 0)
			throw new IllegalArgumentException("Must specify a version");
		this.versions = new HashSet<Version>(Arrays.asList(versions));
	}

	/**
	 * Builds {@link PrimitiveTypeRule}s for the specified types
	 * 
	 * @param type an array of types
	 * @return this instance to continue building rules
	 */
	public PrimitiveRuleBuilder primitive(String... type) {
		if (type.length == 0) {
			throw new IllegalArgumentException("Must specify a type");
		}
		return new PrimitiveRuleBuilder(rules, versions, new HashSet<String>(Arrays.asList(type)));
	}

	/**
	 * Builds {@link MessageRule}s for the specified event types and triggers
	 * 
	 * @param eventType Event type, e.g. "ADT", or "*" for all types
	 * @param triggerEvent, e.g. "A01" or "*" for all trigger events
	 * @return this instance to continue building rules
	 */
	public MessageRuleBuilder message(String eventType, String triggerEvent) {
		return new MessageRuleBuilder(rules, versions, eventType, triggerEvent);
	}
	
	public MessageExpressionBuilder message() {
		return new MessageExpressionBuilder();
	}	

	/**
	 * Builds {@link MessageRule}s for the specified encoding
	 * 
	 * @param encoding "XML" or "VB"
	 * @return this instance to continue building rules
	 */
	public EncodingRuleBuilder encoding(String encoding) {
		return new EncodingRuleBuilder(rules, versions, encoding);
	}

	/**
	 * Add {@link RuleBinding}s for the rule that have been built
	 * 
	 * @param rule
	 */
	protected void addRuleBindings(T rule) {
		if (Version.allVersions(versions)) {
			// Save some bindings when all HL7 versions are affected
			rules.addAll(getRuleBindings(rule, "*"));
		} else {
			for (Version version : versions) {
				rules.addAll(getRuleBindings(rule, version.getVersion()));
			}
		}
	}

	/**
	 * Builder implementation must overwrite this method to return all {@link RuleBinding}s for
	 * rules that have been built.
	 * 
	 * @param rule
	 * @param version
	 * @return a collection of {@link RuleBinding}s
	 */
	@SuppressWarnings("unchecked")
	protected Collection<RuleBinding<T>> getRuleBindings(T rule, String version) {
		return (Collection<RuleBinding<T>>) Collections.EMPTY_LIST;
	}
	
	/**
	 * Helper builder when the versions are not given explicitly but in form of
	 * an expression.
	 */
	public class MessageExpressionBuilder {

		public MessageRuleBuilder all() {
			return new MessageRuleBuilder(rules, versions, "*", "*");
		}
		
		public MessageRuleBuilder allOfEventType(String eventType) {
			return new MessageRuleBuilder(rules, versions, eventType, "*");
		}		

	}	

}
