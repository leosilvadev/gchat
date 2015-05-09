package br.leosilvadev.gchat.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import br.leosilvadev.gchat.config.conditions.SSLCondition;

@Conditional(SSLCondition.class)
@Configuration
public class HTTPSConfig {

	@Value("${gchat.keystore.pass}")
	private String keystorePass;
	
	@Value("${gchat.keystore.type}")
	private String keystoreType;
	
	@Value("${gchat.keystore.provider}")
	private String keystoreProvider;

	@Value("${gchat.keystore.alias}") 
	private String keystoreAlias;

	@Value("${gchat.keystore.location}") 
	private String keystoreLocation;
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		final String keystoreFile = getKeyPath();
	 
	    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
	    factory.addConnectorCustomizers((TomcatConnectorCustomizer) (Connector con) -> {
	        con.setScheme("https");
	        con.setSecure(true);
	        Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();
	        proto.setSSLEnabled(true);
	        proto.setKeystoreFile(keystoreFile);
	        proto.setKeystorePass(keystorePass);
	        proto.setKeystoreType(keystoreType);
	        proto.setProperty("keystoreProvider", keystoreProvider);
	        proto.setKeyAlias(keystoreAlias);
	    });
	    
	    return factory;
	}
	
	private String getKeyPath(){
		return  HTTPSConfig.class.getResource(keystoreLocation).getPath();
	}
	
}
