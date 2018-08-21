import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {

	private BufferedReader br;
	private DataOutputStream dout;
	private DataInputStream din;
	private String otherName;
	
	private void initializeStreams(Socket s) throws Exception {
		din=new DataInputStream(s.getInputStream());
		br=new BufferedReader(new InputStreamReader(System.in));
		dout=new DataOutputStream(s.getOutputStream());
	}
	
	
	
	private void communicate(String message) throws Exception{
		System.out.print(message);
		String height=br.readLine();
		dout.writeUTF(height);
		dout.flush();
	}
	
	
	ChatClient(){
		try{
			Socket s=new Socket("localhost",9999);
			initializeStreams(s);
			
			
			String myNumber=din.readUTF();
			boolean first=false;
			if (myNumber.equals("1")){
				first=true;
				this.otherName = "mahboob";
			//	System.out.println("I get to talk first");
			} else {
			//	System.out.println("I get to listen first");
				this.otherName = "ali";
			}
			
			
			while(true){
				if (first){
					String message=br.readLine();	//get input from the user and send it
					dout.writeUTF(message);
					dout.flush();
				} else {
					String message=din.readUTF();
					System.out.println(this.otherName + ":" + message);
				}
				first = !first;
			}
			
			
		//	communicate("Write your height in meters (ex 1.80): ");
			//communicate("Write your weight in kilograms (ex: 70.5): ");
	//		String bmi=din.readUTF();
		//	System.out.println("Your BMI is: " + bmi);
			
			
			
		} catch(Exception e){System.out.println(e);}
	}
	
	public static void main(String[] args){
		
		new ChatClient();
		System.out.println("Finished");
	}
	
	
}
