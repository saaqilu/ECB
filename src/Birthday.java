public class Birthday {
	
	String birthday;
	int day;
	int mouth;
	int year;
	
	//Judge whether it conforms to the format of DD-MM-YY and whether the date is the real date
	String regex = "(((0[1-9]|[12][0-9]|3[01]|[1-9])-((0[13578]|[13578]|1[02]))|(([0-9]|0[1-9]|[12][0-9]|30)-(0[469]|[469]|11))|([0-9]|0[1-9]|[1][0-9]|2[0-8])-(02))-([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3}))|(29-02-(([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00)))";
	
	
	//The constructor of Birthday takes string as the parameter, and if the parameter does not conform to the regex format, String birthday is null; otherwise, String birthday is set as the parameter
	public Birthday(String birthday) {
		String[] bi;
		if(birthday.matches(regex)) {
			bi = birthday.split("-");
			if(bi[2].startsWith("0")|bi[2].startsWith("1")|bi[2].startsWith("2")) {
				this.birthday = birthday;
				day=Integer.parseInt(bi[0]);
				mouth=Integer.parseInt(bi[1]);
				year=Integer.parseInt(bi[2]);
			}
		}
	}
	
}
