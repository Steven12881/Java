import java.net.*;
import java.io.*;

public class BMIClient {

	private BufferedReader br;
	private DataOutputStream dout;
	private DataInputStream din;
	
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
	
	
	BMIClient(){
		try{
			Socket s=new Socket("localhost",8888);
			initializeStreams(s);
			
			communicate("Write your height in meters (ex 1.80): ");
			communicate("Write your weight in kilograms (ex: 70.5): ");
			String bmi=din.readUTF();
			System.out.println("Your BMI is: " + bmi);
			
			
			
		} catch(Exception e){System.out.println(e);}
	}
	
	public static void main(String[] args){
		new BMIClient();
	}
	
}
