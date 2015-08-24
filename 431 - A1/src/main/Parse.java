package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/*
 * This class reads in a file from the console and parses each of the lines in the file looking for certain tokens that make up a valid
 * HTTP GET command.  If there are any unexpected/invalid tokens in the file commands an appropriate error message is output to the console.  
 */

public class Parse {

	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		String request;
		while(scanner.hasNextLine()){
			request=scanner.nextLine(); //gets the successive command lines
			System.out.println(request);
			httpCommandParse(request); //the method that figures out if the command is valid and what to do with it
			System.out.println("");
		}
		scanner.close();
	}

	public static void httpCommandParse(String input){
		String method = "Method = ", requestURL, httpVersion = "HTTP-Version = "; //Getting base of some of the output strings I will produce if necessary
		String[] noSpaceString = input.trim().split("\\s+"); //Removing all whitespace at the end of the string and then making an array of strings which were separated by 1 or more whitespace characters
		//order of if/else if/else statements checks all the parameters in order before executing anything
		if(noSpaceString.length == 0 || input.startsWith(" "))invalidMethod(); //checking to see if there was any message actually sent or if the original input started with a blank space
		else if(!(noSpaceString[0].equals("GET")))invalidMethod();
		else if(!(noSpaceString[1].startsWith("/")))invalidPath();
		else if(!(noSpaceString[2].matches("[H][T][T][P][/]\\d+[.]\\d+")))invalidHTTPVersion(); //regex formatting to check to make sure the HTTP version input is valid with 1 or more digits before and after the '.' and nothing unexpected elsewhere
		else if(noSpaceString.length == 4)spuriousText(); //check if anything random followed the HTTP version
		else{ //for passing all the checks and attempting to open the file and print the approved formatted input
			method += noSpaceString[0];
			requestURL = noSpaceString[1];
			httpVersion += noSpaceString[2];
			System.out.println(method);
			System.out.println("Request-URL = " + requestURL);
			System.out.println(httpVersion);

			//line 49-52 are basically just checking to see if the proper case-insensitive file endings were used
			String file = requestURL.substring(requestURL.lastIndexOf('/')+1);
			if(!(file.substring(file.indexOf('.')+1).equalsIgnoreCase("txt") | 
					file.substring(file.indexOf('.')+1).equalsIgnoreCase("htm") |
					file.substring(file.indexOf('.')+1).equalsIgnoreCase("html")))notImplemented(requestURL);
			else{ //if proper file type used this is entered
				try{ //now it attempts to find, open, and print out all the lines in the file
					FileReader fr = new FileReader(System.getProperty("user.dir")+ requestURL);
					BufferedReader br = new BufferedReader(fr);
					String line;
					while((line = br.readLine()) != null) //to make sure that the file has no more lines
						System.out.println(line);
					br.close();
				//next 4 lines are just to check for the last 2 errors possible with this attempted opening
				}catch(FileNotFoundException e){
					notFound(requestURL);
				}catch(Exception e){
					otherError(e.getMessage());
				}
			}
		}
	}

	//Lines below this point are just methods for error tokens to make code look cleaner and avoid numerous System.out.println calls
	public static void invalidMethod(){
		System.out.println("ERROR -- Invalid Method token.");
	}

	public static void invalidPath(){
		System.out.println("ERROR -- Invalid Absolute-Path token.");
	}

	public static void invalidHTTPVersion(){
		System.out.println("ERROR -- Invalid HTTP-Version token.");
	}

	public static void spuriousText(){
		System.out.println("ERROR -- Spurious token before CRLF.");
	}

	public static void notImplemented(String requestURL){
		System.out.println("501 Not Implemented: " + requestURL);
	}

	public static void notFound(String requestURL){
		System.out.println("404 Not Found: " + requestURL);
	}

	public static void otherError(String javaErrorMsg){
		System.out.println("ERROR: " + javaErrorMsg);
	}
}