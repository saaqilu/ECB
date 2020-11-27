import java.io.File;

public class Comp {

	//main method of project
	public static void main(String[] args) {
		
		PhoneBook pb = new PhoneBook();
		
		//set the path of phonebookfile instructionfile outputfile reportfile.
		String phonebookfile = "src\\contacts_5.txt";
		String instructionfile = "src\\instructions_5.txt";
		File outputfile = new File("src\\result.txt");
		File reportfile = new File("src\\report.txt");
		pb.readBook(phonebookfile);
		pb.updateBook(instructionfile,outputfile,reportfile);
		
	}
	
}
