package org.mycompany.util;

import org.apache.camel.CamelContext;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.util.jsse.KeyManagersParameters;
import org.apache.camel.util.jsse.KeyStoreParameters;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.apache.camel.util.jsse.TrustManagersParameters;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class SSLRegistry {
	
	public SSLRegistry(CamelContext camelContext) {
		KeyStoreParameters keyStoreParameters = new KeyStoreParameters();
        // Change this path to point to your truststore/keystore as jks files
		
//		I3 Environment
//      keyStoreParameters.setResource("apps-ocp-itree-chain.jks");
		
//		DC Environment
        keyStoreParameters.setResource("client-truststore-DC.jks");
        
//      DEV Environment
//		keyStoreParameters.setResource("apps-ocp-dev-hanabank-co-id-chain.jks");
        keyStoreParameters.setPassword("wso2carbon");

        KeyManagersParameters keyManagersParameters = new KeyManagersParameters();
        keyManagersParameters.setKeyStore(keyStoreParameters);
        keyManagersParameters.setKeyPassword("wso2carbon");

        TrustManagersParameters trustManagersParameters = new TrustManagersParameters();
        trustManagersParameters.setKeyStore(keyStoreParameters);

        SSLContextParameters sslContextParameters = new SSLContextParameters();
        sslContextParameters.setKeyManagers(keyManagersParameters);
        sslContextParameters.setTrustManagers(trustManagersParameters);

        HttpComponent httpComponent = camelContext.getComponent("https4", HttpComponent.class);
        httpComponent.setSslContextParameters(sslContextParameters);
        //This is important to make your cert skip CN/Hostname checks
        httpComponent.setX509HostnameVerifier(new AllowAllHostnameVerifier());
	}
}
