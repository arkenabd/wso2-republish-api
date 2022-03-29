package org.mycompany.util;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.camel.CamelContext;
import org.apache.camel.component.http4.HttpComponent;
import org.apache.camel.util.jsse.KeyManagersParameters;
import org.apache.camel.util.jsse.KeyStoreParameters;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.apache.camel.util.jsse.TrustManagersParameters;
import org.apache.commons.io.FileUtils;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")

public class SSLRegistry {
	@Autowired
	public SSLRegistry(CamelContext camelContext, @Value("${keystore.base64}") final String base64,
			@Value("${keystore.pass}") final String pass) {
		KeyStoreParameters keyStoreParameters = new KeyStoreParameters();
		// Change this path to point to your truststore/keystore as jks files
		System.out.println("keystore.base64 " + base64);
		System.out.println("keystore.pass " + pass);
		String finalBase64 = base64.replaceAll("\n", "");

		finalBase64 = base64.replaceAll("\\s", "");
		byte[] decodedBytes = Base64.getDecoder().decode(finalBase64);
		try {
			FileUtils.writeByteArrayToFile(new File("src/main/resources/client-truststore.jks"), decodedBytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		keyStoreParameters.setResource("src/main/resources/client-truststore.jks");

		keyStoreParameters.setPassword(pass);

		KeyManagersParameters keyManagersParameters = new KeyManagersParameters();
		keyManagersParameters.setKeyStore(keyStoreParameters);
		keyManagersParameters.setKeyPassword(pass);

		TrustManagersParameters trustManagersParameters = new TrustManagersParameters();
		trustManagersParameters.setKeyStore(keyStoreParameters);

		SSLContextParameters sslContextParameters = new SSLContextParameters();
		sslContextParameters.setKeyManagers(keyManagersParameters);
		sslContextParameters.setTrustManagers(trustManagersParameters);

		HttpComponent httpComponent = camelContext.getComponent("https4", HttpComponent.class);
		httpComponent.setSslContextParameters(sslContextParameters);
		// This is important to make your cert skip CN/Hostname checks
		httpComponent.setX509HostnameVerifier(new AllowAllHostnameVerifier());
	}
}
