//Define the user's data structure
public class User {
	
	Name name;
	Birthday birthday; 
	Phone phone;
	String address;
	Email email;
	
	public User() {
		
	}
	
	public User(Name name, Birthday birthday) {
		this.name =  name;
		this.birthday = birthday;
	}
	
	public User(Name name, Birthday birthday, Phone phone) {
		this.name =  name;
		this.birthday = birthday;
		this.phone = phone;
	}
	
	public User(Name name, Birthday birthday,String address) {
		this.name =  name;
		this.birthday = birthday;
		this.address = address;
	}
	
	public User(Name name, Birthday birthday, Email email) {
		this.name =  name;
		this.birthday = birthday;
		this.email = email;
		
	}
	
	public User(Name name, Birthday birthday, Phone phone, String address) {
		this.name =  name;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
	}
	
	public User(Name name, Birthday birthday, Phone phone, Email email) {
		this.name =  name;
		this.birthday = birthday;
		this.phone = phone;
		this.email = email;
	}
	
	public User(Name name, Birthday birthday, String address, Email email) {
		this.name =  name;
		this.birthday = birthday;
		this.address = address;
		this.email = email;
	}
	
	public User(Name name, Birthday birthday, Phone phone, String address, Email email) {
		this.name =  name;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}
	
	public String getName() {
		return name.name;
	}
	
	public String getbirthday() {
		return birthday.birthday;
	}
	
	public Long getphone() {
		return phone.phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getEmail() {
		return email.email;
	}
	
	public void setName(String name) {
		this.name = new Name(name);
	}
	
	public void setPhone(String phone) {
		this.phone = new Phone(phone);
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setBrithday(String brithday) {
		this.birthday = new Birthday(brithday);
	}
	
	public void setEmail(String email) {
		this.email = new Email(email);
	}
	
	//Set null attribution
	public void setNull() {
		//If the name.name is null, set name to null
		if(name!=null) {
			if(name.name==null){
				name=null;
			}
		}
		//If the phone.phone is 0, set phone to null
		if(phone!=null) {
			if(phone.phone==0){
				phone=null;
			}
		}
		//If the email.email is null, set email to null
		if(email!=null) {
			if(email.email==null){
				email=null;
			}
		}
		//If the birthday.birthday is null, set birthday to null
		if(birthday!=null) {
			if(birthday.birthday==null){
				birthday=null;
			}
		}
	}
	
}
