
public class Email {
	
	String email;
	//Judge whether it conforms to the format of email, xxxxx@xxxxx (x can be letter or . )
	String regex = "^[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\.]+$";
	
	//The constructor of Email takes string as the parameter, and if the parameter does not conform to the regex format, String email is null; otherwise, String email is set as the parameter
	public Email(String email) {
		if (email.matches(regex)) {
			this.email = email;
		}
	}
}
