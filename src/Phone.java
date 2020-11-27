
public class Phone {
	
	long phone;
	//Judge whether it conforms to the format of phone, phone just contains number
	String regex = "^[0-9]+$";
	
	//The constructor of Phone takes string as the parameter, and if the parameter does not conform to the regex format, long phone is null; otherwise, long phone is set as the parameter
	public Phone(String phone) {
		if (phone.matches(regex)) {
			this.phone = Long.parseLong(phone);
		}
	}
}
