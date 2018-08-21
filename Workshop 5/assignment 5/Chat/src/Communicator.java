import java.io.*;
import java.net.*;
 import java.util.Date;

public class Communicator extends Thread {
	Socket mySocket;
	String myName;
	Communicator other;
	private boolean first;

	private BufferedReader br;
	private DataOutputStream dout;
	private DataInputStream din;
	
	public String getMyName(){
		return myName;
	}
	
	public void setOther(Communicator c){
		other = c;
	}
	
	private void initializeStreams(Socket s) throws Exception {
		din=new DataInputStream(s.getInputStream());
		br=new BufferedReader(new InputStreamReader(System.in));
		dout=new DataOutputStream(s.getOutputStream());
	}
	
	
	
	private void communicate(String message) throws Exception{
		dout.writeUTF(message);
		dout.flush();
	}
	
	
	
	Communicator(Socket s, String name, boolean first){
		mySocket = s;
		myName = name;
		this.first = first;
	}
	
	public void sendMessage(String message) throws Exception{
		communicate(message);
	}
	
	public void run(){
		try{
			initializeStreams(mySocket);
			System.out.println(myName + " is running");
			while (true){
				//if (first){
					
					String messageIncoming=din.readUTF();
					System.out.println(other.getMyName() + ": " + messageIncoming);
					other.sendMessage(messageIncoming);
					
				//} else {  //second person waits for message first
				
					//i represent the second person, so i just wait 
				
				//	String messageIncoming=din.readUTF();
					//System.out.println(other.getMyName() + ": " + messageIncoming);
				//	other.sendMessage(messageIncoming);
					
				//}
				
			}
			
		}catch(Exception e){ System.out.println("I am first: " + first);e.printStackTrace();}
		
	}
}
