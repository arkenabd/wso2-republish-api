package org.mycompany.util;

import java.util.concurrent.TimeUnit;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spi.ShutdownStrategy;


public class GeneratePayloadToken implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
//		from("direct:initialAccessToken")
//		.log("SSO URL: {{sso.url}}")
//		.removeHeader("Camel*")
//		.setHeader(Exchange.HTTP_METHOD, constant("POST"))
//		.setHeader(Exchange.CONTENT_TYPE, constant("application/x-www-form-urlencoded"))
//		.setBody(simple("grant_type=password&username={{sso.username}}&password={{sso.password}}&client_id={{sso.clientId}}"))
//		.to("https4://{{sso.url}}/auth/realms/master/protocol/openid-connect/token")
//		
//		.unmarshal().json(JsonLibrary.Jackson, Token.class)
//		.log("This is the Access Token: ${body.getAccess_token} & This is the Refresh Token: ${body.getRefresh_token}")
//		.bean("token", "setAccess_token(${body.getAccess_token})")
//		.bean("token", "setRefresh_token(${body.getRefresh_token})")
//		.log("Token to access Single Sign-On is saved. Automatically refresh the token when expiry is due.")
//	;
//	}
	}
}
