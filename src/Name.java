
public class Name {
	
	String name;
	//Judge whether it conforms to the format of name, name can contain space and letter
	String regex = "^[a-zA-Z\\s]+$";
	
	//The constructor of Name takes string as the parameter, and if the parameter does not conform to the regex format, String name is null; otherwise, String name is set as the parameter
	public Name(String name) {
		if (name.matches(regex)) {
			this.name = name;
		}
	}
}
