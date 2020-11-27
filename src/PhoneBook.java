import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

	//Users arraylist to store User object
	ArrayList<User> users = new ArrayList<User>();
	//Judge if the user is exist
	Boolean exist;
	
	//Read book method, parameter is path of phonebookfile
	public void readBook(String path) {
		File file = new File(path);
		try{
			if(file.exists()) {
				InputStreamReader isr = new InputStreamReader(new FileInputStream(path));
				BufferedReader br = new BufferedReader(isr);
				String lineTxt = null;
				//Create a new User object, newUser
				User newUser = new User();
				//Read the PhoneBookFile line by line
				while ((lineTxt = br.readLine()) != null) {
					//If the content being read is not blank, divide the content into two parts with the first space. Store the content in a String[] lineList
					if(!lineTxt.equals("")) {
						String[] lineList = lineTxt.split(" ",2);
						//Judge the lineList[0]
						switch(lineList[0]) {
						case "name"://If lineList[0] is "name", set lineList[1] as newUser.name
							newUser.name = new Name(lineList[1]);
							break;
						case "phone"://If lineList[0] is "phone", set lineList[1] as newUser.phone
							newUser.phone = new Phone(lineList[1]);
							break;
						case "address"://If lineList[0] is "address", set lineList[1] as newUser.address
							newUser.address = lineList[1];
							break;
						case "birthday"://If lineList[0] is "birthday", set lineList[1] as newUser.birthday
							newUser.birthday = new Birthday(lineList[1]);
							break;
						case "email"://If lineList[0] is "email", set lineList[1] as newUser.email
							newUser.email = new Email(lineList[1]);
							break;
						}
					}else {//If the content being read is blank, add newUser to users
						newUser.setNull();//If set the blank attribute to null
						if(newUser.name!=null&newUser.birthday!=null) {//If name and birthday are not null, add newUser to users
							users.add(newUser);
							newUser = new User();
						}
					}
				}
				if(br.readLine()==null) {//If no content being read, add newUser to users
					newUser.setNull();//If set the blank attribute to null
					if(newUser.name!=null&newUser.birthday!=null) {//If name and birthday are not null, add newUser to users
						users.add(newUser);
						newUser = new User();
					}
				}
				br.close();
			}else {
				System.out.println("File not exist!");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	//Read book method, parameters are path of phonebookfile, outputfile, reportfile
	public void updateBook(String readPath, File outputfile, File reportfile) {
		
		File readFile = new File(readPath);
		//This map is for store the attribution to key and content of attribution to value
		Map<String, String> instr = new HashMap<String, String>();
	
		try{
			if(!outputfile.exists()) {
				outputfile.createNewFile();
			}if(!reportfile.exists()) {
				reportfile.createNewFile();
			}
			
			InputStreamReader isr = new InputStreamReader(new FileInputStream(readPath));
			BufferedReader br = new BufferedReader(isr);
			
			FileWriter fw = new FileWriter(outputfile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			FileWriter fw2 = new FileWriter(reportfile.getAbsoluteFile());
			BufferedWriter bw2 = new BufferedWriter(fw2);
			
			if(readFile.exists()) {
				String lineTxt = null;
				//Read the instructionFile line by line
				while ((lineTxt = br.readLine()) != null) {
					instr.clear();
					//If the content being read is not blank, split the content with the first blank, store it in String[] lineList
					if(!lineTxt.equals("")) {
						String[] lineList = lineTxt.split(" ",2);
						//Judge the lineList[0]
						switch(lineList[0]) {
						//If the lineList[0] is add, create a new object User , newUser
						case "add":	
							User newUser = new User();
							exist = false;
							String[] instrList = lineList[1].split(";\\s+");//Split the lineList[1] with ";" and space.
							
							for(String addWhat : instrList) {
								instr.put(addWhat.split(" ",2)[0], addWhat.split(" ",2)[1]);//Store the attribution to key and content of attribution to value
							}
							
							//Loop map instr, set the attribution of newUser, with the instr value
							for(int i = 0; i < users.size(); i++) {
								if(instr.keySet().contains("name")&instr.keySet().contains("birthday")) {
									Birthday bi = new Birthday(instr.get("birthday"));
									//If value of name and value of birthday are all same with an User in users. The "add" instruction update this user information
									if(users.get(i).name.name.equals(instr.get("name"))&isSameB(bi,users.get(i).birthday)) {
										for(String j : instr.keySet()) {
											switch(j) {
											case "name":
												break;
											case "phone":
												if(new Phone(instr.get("phone")).phone!=0) {
													users.get(i).phone = new Phone(instr.get("phone"));
												}
												break;
											case "brithday":
												break;
											case "email":
												if(new Email(instr.get("email")).email!=null) {
													users.get(i).email = new Email(instr.get("email"));
												}
												break;
											case "address":
												users.get(i).address = instr.get("address");
												break;
											}
											exist = true;
										}

										//If value of name and value of birthday are not all same with an User in users. The "add" instruction adds this user information
									}else {
										for(String j : instr.keySet()) {
											switch(j) {
											case "name":
												newUser.name = new Name(instr.get("name"));
												break;
											case "phone":
												newUser.phone =new Phone(instr.get("phone"));
												break;
											case "birthday":
												newUser.birthday = new Birthday(instr.get("birthday")); 
												break;
											case "email":
												newUser.email = new Email(instr.get("email"));
												break;
											case "address":
												newUser.address = instr.get("address");
												break;
											}
										}
									}
								}
								
							}
							if(exist==false) {
								newUser.setNull();
								if(newUser.name!=null&newUser.birthday!=null) {
									if(newUser.name.name!=null&newUser.birthday.birthday!=null) {
										users.add(newUser);
									}	
								}
							}
							break;
						//If the lineList[0] is delete, delete the object User
						case "delete":
							//Split lineList[1] with "; ", store it in del
							String[] del = lineList[1].split("; ",2);
							//If del[] length is 1, delete all users in arrayList users which name attribution is del[0] 
							if(del.length == 1) {
								for(int i = 0; i < users.size(); i++) {
									if(users.get(i).name.name.equals(del[0])) {
										users.remove(i);
									}
								}
							//If del[] length is 2, delete the user in arrayList users which name attribution and birthday attribution is del[0] 
							}else if(del.length == 2) {
								for(int i = 0; i < users.size(); i++) {
									Birthday bi = new Birthday(del[1]);
									if(users.get(i).name.name.equals(del[0])&isSameB(bi,users.get(i).birthday)) {
										users.remove(i);
									}
								}
							}
							break;
						//If the lineList[0] is query, query the object User
						case "query":
							//Split lineList[1] with " ", store it in que
							String[] que = lineList[1].split(" ",2);
							//Judge the que[0]
							switch(que[0]) {
							//If que[0] is name, write all user in arrayList users which name attribution is que[1]
							case "name":
								
								bw2.write("====== query name " + que[1] + " ======\r\n");
								for(User u : users) {
									if(u.name.name.equals(que[1])) {
										writerBook(u,bw2);
									}
								}
								bw2.write("====== end of query name " + que[1] + " ======\r\n\r\n");
								break;
							case "birthday":
								//If que[0] is birthday, write all user in arrayList users which birthday attribution is que[1]
								bw2.write("====== query birthday " + que[1] + " ======\r\n");
								
								for(User u : users) {
									if(u.birthday.birthday.equals(que[1])) {
										writerBook(u,bw2);
									}
								}
								bw2.write("====== end of query birthday " + que[1] + " ======\r\n\r\n");
								break;
							case "phone":
								//If que[0] is phone, write all user in arrayList users which phone attribution is que[1]
								bw2.write("====== query phone " + que[1] + " ======\r\n");
								
								for(User u : users) {
									if(u.phone!=null) {
										if(u.phone.phone == Integer.parseInt(que[1])) {
											writerBook(u,bw2);
										}
									}
								}
								bw2.write("====== end of query phone " + que[1] + " ======\r\n\r\n");
							}
							
							break;
						//If the lineList[0] is save, write the user in arrayList users
						case "save":
							for(User u : users) {
								writerBook(u, bw);
							}
							break;
						}
					}
				}
				br.close();
				bw.close();
				bw2.close();
			}else {
				System.out.println("File not exist!");
			}
		}catch(IOException e) {
			System.out.println("Error read");
		}
	}
	
	//Write method, the parameter is User object and BufferedWriter
	public void writerBook(User u, BufferedWriter bw) {
		try {
			if(u.address==null&u.email==null&u.phone==null) {
				bw.write("name: " + u.name.name + "\r\n" + "birthday: " + u.birthday.birthday + "\r\n\r\n");
			}else if(u.address==null&u.email==null) {
				bw.write("name: " + u.name.name + "\r\n" + "birthday: " + u.birthday.birthday + "\r\n" + "phone: " + u.phone.phone + "\r\n\r\n");
			}else if(u.address==null&u.phone==null) {
				bw.write("name: " + u.name.name + "\r\n" + "birthday: " + u.birthday.birthday + "\r\n" + "email: " + u.email.email + "\r\n\r\n");
			}else if(u.email==null&u.phone==null) {
				bw.write("name: " + u.name.name + "\r\n" + "birthday: " + u.birthday.birthday + "\r\n" + "address: " + u.address + "\r\n\r\n");
			}else if(u.address==null) {
				bw.write("name: " + u.name.name + "\r\n" + "birthday: " + u.birthday.birthday + "\r\n" + "email: " + u.email.email + "\r\n" + "phone: " + u.phone.phone + "\r\n\r\n");
			}else if(u.email==null) {
				bw.write("name: " + u.name.name + "\r\n" + "birthday: " + u.birthday.birthday + "\r\n" + "phone: " + u.phone.phone + "\r\n"+ "address: " + u.address + "\r\n\r\n");
			}else if(u.phone==null) {
				bw.write("name: " + u.name.name + "\r\n" + "birthday: " + u.birthday.birthday + "\r\n" + "email: " + u.email.email + "\r\n"+ "address: " + u.address + "\r\n\r\n");
			}else {
				bw.write("name: " + u.name.name + "\r\n" + "birthday: " + u.birthday.birthday + "\r\n" + "email: " + u.email.email + "\r\n" + "phone: " + u.phone.phone + "\r\n" + "address: " + u.address + "\r\n\r\n");
			}
		}catch(IOException e) {
			System.out.println("Error read");
		}
	}
	
	//Judge if the birthday is same, for example, 3-2-2020 is same with 03-02-2020
	public boolean isSameB(Birthday b1, Birthday b2) {
		if(b1.day==b2.day&b1.mouth==b2.mouth&b1.year==b2.year) {
			return true;
		}
		return false;
	}
	
}
