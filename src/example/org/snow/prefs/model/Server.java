package example.org.snow.prefs.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;


public class Server {

	@Element
	private final String name;
	
	@Element
	private final String protocol;

	@Element
	private final String host;

	@Element
	private final int port;

	@Element
	private final String username;

	@Element
	private final String password;


	public Server( @Attribute(name="name") final String name, @Element(name="protocol") final String protocol, @Element(name="host") final String host, @Element(name="port") final int port, @Element(name="username") final String username, @Element(name="password") final String password ) {
		this.name = name;
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	@Attribute(name="name")
	public String getName() {
		return name;
	}
	
	@Element(name="protocol")
	public String getProtocol() {
		return protocol;
	}
	
	@Element(name="host")
	public String getHost() {
		return host;
	}
	
	@Element(name="port")
	public int getPort() {
		return port;
	}
	
	@Element(name="username")
	public String getUsername() {
		return username;
	}
	
	@Element(name="password")
	public String getPassword() {
		return password;
	}
	
}
