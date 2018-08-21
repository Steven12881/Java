import java.io.*;
import java.net.*;
import java.util.Date;

public class BMIServer {
	
	private BufferedReader br;
	private DataOutputStream dout;
	private DataInputStream din;
	
	
	private void initializeStreams(Socket s) throws Exception {
		din=new DataInputStream(s.getInputStream());
		br=new BufferedReader(new InputStreamReader(System.in));
		dout=new DataOutputStream(s.getOutputStream());
	}
	
	
	
	private String calculateBMI(String heightStr,String weightStr) throws Exception{
		float height = Float.valueOf(heightStr);
		float weight = Float.valueOf(weightStr);
		
		float bmi = ((weight/height/height));
			
		String ret;
		if(bmi < 18.5)
		{
			ret = "Underweight";
		}
		else if (18.5 <= bmi  && bmi  < 25.0 )
		{
			ret = "Normal";
		}
		else if (25.0 <= bmi && bmi < 30.0 ){
			ret = "Overweight";
		}
		else {
			ret = "Obese";
		}

		return ret;
	}
	
	private void communicate(String message) throws Exception{
		//System.out.print(message);
		//String height=br.readLine();
		dout.writeUTF(message);
		dout.flush();
	}
	
	BMIServer(){
		//start the server
		try {
			ServerSocket serverSocket=new ServerSocket(8888);
		
			System.out.println("BMICalculatorServer started at" + (new Date()));
			
			
			//Socket s=ss.accept();
			System.out.println("Listening for connection");
			Socket s = serverSocket.accept();  //this finishes when someone connects
			System.out.println("Connected to a client at started at " + (new Date()));
			
			initializeStreams(s);
			System.out.println("Waiting for height and weight from client:");
			
			String heightStr=din.readUTF();
			System.out.println("Height is: " + heightStr);
			
			String weightStr=din.readUTF();
			System.out.println("Weight is: " + weightStr);
			
			
			communicate(calculateBMI(heightStr,weightStr));
			
			
			
		} catch(Exception e){System.out.println(e);}
	}
	
	
	public static void main(String [] args){
		new BMIServer();
		System.out.println("Server shut down");
	}
	
	
}
