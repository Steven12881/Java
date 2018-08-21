import java.io.*;
import java.net.*;
import java.util.Date;

public class ChatServer {

	ChatServer(){
		try{
				ServerSocket serverSocket=new ServerSocket(9999);
				System.out.println("MultiThreadServer started at " + (new Date()));
		
	
		
			
				//wait for connection
				Socket s1 = serverSocket.accept();  //this finishes when someone connects
				System.out.println("Connection from " + s1 + " at " + (new Date()));
				
				
				
				Socket s2 = serverSocket.accept();  //this finishes when someone connects
				System.out.println("Connection from " + s1 + " at " + (new Date()));
				
				
				
				DataOutputStream dout1=new DataOutputStream(s1.getOutputStream());
				DataOutputStream dout2=new DataOutputStream(s2.getOutputStream());
				
				dout1.writeUTF("1");
				dout1.flush();
				
				dout2.writeUTF("2");
				dout2.flush();
				
				Communicator c1 = new Communicator(s1,"ali",true);
				Communicator c2 = new Communicator(s2,"mahboob",false);
				
				
				
				c1.setOther(c2);
				c2.setOther(c1);
				c1.start();
				c2.start();
				
			} catch(Exception e){System.out.println(e);}
	}
	
	
	
	public static void main(String [] args){
		new ChatServer();
		//System.out.println("Server shut down");
	}
}
