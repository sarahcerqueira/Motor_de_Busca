package Meta2.action;

public class Teste {
	private String username, password;
	
	private String getUsername(){
		return this.username;
	}
	
	private String getPassword(){
		return this.password;
	}
	
	private void setUsername(String username) {
		this.username = username;
	}
	
	private void setPassword(String password) {
		this.password = password;
	}
}


//<c:forEach items="${primesBean.primes}" var="value">