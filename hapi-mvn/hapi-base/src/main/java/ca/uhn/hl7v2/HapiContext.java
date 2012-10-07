/**
The contents of this file are subject to the Mozilla Public License Version 1.1 
(the "License"); you may not use this file except in compliance with the License. 
You may obtain a copy of the License at http://www.mozilla.org/MPL/ 
Software distributed under the License is distributed on an "AS IS" basis, 
WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for the 
specific language governing rights and limitations under the License. 

The Original Code is "HapiContext.java".  Description: 
"HAPI configuration and factory" 

The Initial Developer of the Original Code is University Health Network. Copyright (C) 
2001.  All Rights Reserved. 

Contributor(s): ______________________________________. 

Alternatively, the contents of this file may be used under the terms of the 
GNU General Public License (the  "GPL"), in which case the provisions of the GPL are 
applicable instead of those above.  If you wish to allow use of your version of this 
file only under the terms of the GPL and not to allow others to use your version 
of this file under the MPL, indicate your decision by deleting  the provisions above 
and replace  them with the notice and other provisions required by the GPL License.  
If you do not delete the provisions above, a recipient may use your version of 
this file under either the MPL or the GPL. 
 */
package ca.uhn.hl7v2;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;

import ca.uhn.hl7v2.app.ConnectionHub;
import ca.uhn.hl7v2.app.SimpleServer;
import ca.uhn.hl7v2.app.TwoPortService;
import ca.uhn.hl7v2.llp.LowerLayerProtocol;
import ca.uhn.hl7v2.parser.GenericParser;
import ca.uhn.hl7v2.parser.ModelClassFactory;
import ca.uhn.hl7v2.parser.ParserConfiguration;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import ca.uhn.hl7v2.util.SocketFactory;
import ca.uhn.hl7v2.validation.ValidationContext;
import ca.uhn.hl7v2.validation.ValidationExceptionHandler;
import ca.uhn.hl7v2.validation.ValidationExceptionHandlerFactory;
import ca.uhn.hl7v2.validation.Validator;
import ca.uhn.hl7v2.validation.builder.ValidationRuleBuilder;

/**
 * Interface that provides a starting point for
 * <ul>
 * <li>Configuring HAPI core services (e.g. parsing)
 * <li>Obtaining correspondingly configured instances of HAPI core services
 * </ul>
 * 
 * HapiContext instances are not supposed to be singletons, i.e. if necessary, it is possible to
 * have several HapiContexts within one application.
 * <p>
 * HapiContext objects maintains the following configuration information
 * <ul>
 * <li>{@link ExecutorService}: thread executors used for the HAPI networking features in
 * ca.uhn.hl7v2.app
 * <li>{@link LowerLayerProtocol}: MLLP protocol used for the HAPI networking features in
 * ca.uhn.hl7v2.app
 * <li>{@link SocketFactory}: Socket factory used for the HAPI networking features in
 * ca.uhn.hl7v2.app
 * <li>{@link ParserConfiguration}: detail configuration for all HL7 parsers
 * <li>{@link ModelClassFactory}: lookup for message model classes during parsing or message
 * creation
 * <li>{@link ValidationContext}: validation rules used during parsing or during a dedcated
 * validation step
 * <li>{@link ValidationRuleBuilder}: alternative way of providing a ValidationContext
 * <li>{@link ValidationExceptionHandlerFactory}: factory for exception handler used during message validation
 * </ul>
 * <p>
 * HapiContext serves as factory for HAPI objects that refer to this configuration. Changing the
 * configuration automatically influence all HAPI objects that were created and will be created
 * using the given factory instance:
 * <ul>
 * <li>{@link PipeParser}
 * <li>{@link XMLParser}
 * <li>{@link GenericParser}
 * <li>{@link Validator}
 * <li>{@link ConnectionHub}
 * <li>{@link SimpleServer}
 * <li>{@link TwoPortService}
 * </ul>
 * 
 */
public interface HapiContext extends Serializable {

	/**
	 * @return the {@link ExecutorService} to be used by all services that spawn threads
	 */
	ExecutorService getExecutorService();

	/**
	 * @param executorService the {@link ExecutorService} to be used by all services that spawn
	 *            threads
	 */
	void setExecutorService(ExecutorService executorService);

	/**
	 * @return a new ConnectionHub instance
	 */
	ConnectionHub getConnectionHub();

	/**
	 * @return the {@link ParserConfiguration} to be used by all parsers obtained from this class.
	 */
	ParserConfiguration getParserConfiguration();

	/**
	 * @param configuration {@link ParserConfiguration} to be used by all parsers obtained from this
	 *            class.
	 */
	void setParserConfiguration(ParserConfiguration configuration);

	/**
	 * @return the {@link ValidationContext} to be used by all parsers obtained from this class.
	 */
	ValidationContext getValidationContext();

	/**
	 * @param context {@link ValidationContext} to be used by all parsers obtained from this class.
	 */
	void setValidationContext(ValidationContext context);

	/**
	 * Sets a default {@link ValidationContext}. Note that a default {@link ValidationRuleBuilder}
	 * has precedence of this ValidationContext.
	 * 
	 * @param contextClassName class name of the {@link ValidationContext} to be used by all parsers
	 *            obtained from this class.
	 */
	void setValidationContext(String contextClassName);

	/**
	 * @return the {@link ValidationRuleBuilder} to be used by all parsers obtained from this class.
	 */
	ValidationRuleBuilder getValidationRuleBuilder();

	/**
	 * Sets a default {@link ValidationRuleBuilder}. Note that this {@link ValidationRuleBuilder}
	 * has precedence over a default {@link ValidationContext} set with
	 * {@link #setValidationContext(ValidationContext)} or {@link #setValidationContext(String)}
	 * 
	 * @param context {@link ValidationRuleBuilder} to be used by all parsers obtained from this
	 *            class.
	 */
	void setValidationRuleBuilder(ValidationRuleBuilder ruleBuilder);

	/**
	 * Sets a new instance of {@link ValidationRuleBuilder} as default. Note that this
	 * {@link ValidationRuleBuilder} has precedence over a default {@link ValidationContext} set
	 * with {@link #setValidationContext(ValidationContext)} or
	 * {@link #setValidationContext(String)}
	 * 
	 * @param builderClassName class name of the {@link ValidationRuleBuilder} to be used by all
	 *            parsers obtained from this class.
	 */
	void setValidationRuleBuilder(String builderClassName);

	/**
	 * @return the {@link ModelClassFactory} to be used by all parsers obtained from this class.
	 */
	ModelClassFactory getModelClassFactory();

	/**
	 * @param modelClassFactory the {@link ModelClassFactory} to be used by all parsers obtained
	 *            from this class.
	 */
	void setModelClassFactory(ModelClassFactory modelClassFactory);

	// Default instances of business objects

	/**
	 * @return a new PipeParser instance initialized as set with
	 *         {@link #setModelClassFactory(ModelClassFactory)},
	 *         {@link #setValidationContext(String)} and
	 *         {@link #setParserConfiguration(ParserConfiguration)}.
	 */
	PipeParser getPipeParser();

	/**
	 * @return a new XMLParser instance initialized as set with
	 *         {@link #setModelClassFactory(ModelClassFactory)},
	 *         {@link #setValidationContext(String)} and
	 *         {@link #setParserConfiguration(ParserConfiguration)}.
	 */
	XMLParser getXMLParser();

	/**
	 * @return a new GenericParser instance initialized as set with
	 *         {@link #setModelClassFactory(ModelClassFactory)},
	 *         {@link #setValidationContext(String)} and
	 *         {@link #setParserConfiguration(ParserConfiguration)}.
	 */
	GenericParser getGenericParser();

	/**
	 * @return a MessageValidator instance initialized with the {@link ValidationContext} as set
	 *         using {@link #setValidationContext(ValidationContext)}. For each validation it will
	 *         use a new instance of {@link ValidationExceptionHandler} as obtained by
	 *         {@link #getValidationExceptionHandler()}.
	 */
	<R> Validator<R> getMessageValidator();
	
	<R> ValidationExceptionHandlerFactory<R> getValidationExceptionHandlerFactory();
	
	/**
	 * @param factory a {@link ValidationExceptionHandlerFactory} that is used to create
	 * a {@link ValidationExceptionHandler} during message validation.
	 */
	<R> void setValidationExceptionHandlerFactory(ValidationExceptionHandlerFactory<R> factory);

	/**
	 * @return the {@link LowerLayerProtocol} instance used by all HL7 MLLP operations
	 */
	LowerLayerProtocol getLowerLayerProtocol();

	/**
	 * @param llp the {@link LowerLayerProtocol} instance used by all HL7 MLLP operations
	 */
	void setLowerLayerProtocol(LowerLayerProtocol llp);

	/**
	 * @return the {@link SocketFactory} instance used by HL7 networking operations
	 */
	SocketFactory getSocketFactory();

	/**
	 * @param socketFactory the {@link SocketFactory} instance used by HL7 networking operations
	 */
	void setSocketFactory(SocketFactory socketFactory);

	/**
	 * @param port
	 * @param tls
	 * @return HL7 service running on the configured port using the default parser and executor
	 *         service instances provided by this interface.
	 */
	SimpleServer getSimpleService(int port, boolean tls);

	/**
	 * @param port
	 * @param tls
	 * @return HL7 service running on the configured ports using the default parser and executor
	 *         service instances provided by this interface.
	 */
	TwoPortService getTwoPortService(int inbound, int outbound, boolean tls);
}
