/* 
    Code Developed and Written by Paul P Joby 
    Name :  ChatServer 
    Version : 1.0.0
*/
import java.io.*;
import java.net.*;
import java.util.*;
public class ChatServer{
    public ServerSocket server;
    public Socket client = null;
    public DataOutputStream os;
    public DataInputStream is;
    public HashMap<String ,Socket> clientList = new HashMap<String,Socket>();
    public static void main(String[] args){
        ChatServer a = new ChatServer();
        a.doConnections();
    }
    public void doConnections(){
        try{
        server = new ServerSocket(9001);
        MyThreadServer messageRouterThread = new MyThreadServer();
        messageRouterThread.start();
        while(true)
        {
            
            client = server.accept();
            if(client != null)
            {
                os = new DataOutputStream(client.getOutputStream());
                is = new DataInputStream(client.getInputStream());    
                String requestedClientName = is.readUTF();
                clientList.put(requestedClientName,client);
                os.writeUTF("#accepted");
                messageRouterThread.clientList.put(requestedClientName,client);
            } 
        }
      }
      catch(Exception e){
        System.out.println("Error Occured Oops!" +  e.getMessage());
      }
    }
}
class MyThreadServer extends Thread{
    public HashMap<String,Socket> clientList = new HashMap<String,Socket>();
    public DataInputStream is= null;
    public DataOutputStream os=null;
    public void run(){
        String msg = "";
        int i = 0;
        System.out.println("Chat Server Running .....");
        String toClientName = "";
        while(true){
            try{
                if(clientList != null){
                    for(String key: clientList.keySet())
                    {
                        is= new DataInputStream(clientList.get(key).getInputStream());
                        if(is.available()>0)
                        {
                            msg=is.readUTF();
                            i= msg.indexOf("@");
                            toClientName = msg.substring(i);
                            // System.out.println(msg);
                            // obtained the client name 
                            // now write that message to that client
                            if(clientList.containsKey(toClientName)){
                                os= new DataOutputStream(clientList.get(toClientName).getOutputStream());
                                os.writeUTF( key + ": " + msg.substring(0,i));
				System.out.println("@SERVER : " + key + ": " + msg.substring(0, i) + " to " + toClientName);
                            }
                            else{
                                os= new DataOutputStream(clientList.get(key).getOutputStream());
                                os.writeUTF("Message From Server : No such Username found");
                            }
                        }
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("Server Recovering Initialization Failure ...");
                System.out.println("Server Up And Running ...");
            }
            //goes through all clientList and reads the UTF and Writes to that Client
        }
    }
}
/*
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	
	public ServerSocket        server;
	public Socket              client;
	
	public DataOutputStream    os;
	public DataInputStream     is;
	
	public Map<String, Socket> clientList;
	
	public ChatServer() {
		this.client = null;
		this.clientList = new HashMap<>();
	}
	
	public static void main(String[] args) {
		ChatServer a = new ChatServer();
		a.connections();
	}
	public void connections() {
		try {
			server = new ServerSocket(9001);
			ServerThread messageRouterThread = new ServerThread();
			messageRouterThread.start();
			while (true) {
				
				client = server.accept();
				if(client != null) {
					os = new DataOutputStream(client.getOutputStream());
					is = new DataInputStream(client.getInputStream());	
					String requestedClientName = is.readUTF();
					clientList.put(requestedClientName,client);
					os.writeUTF("#accepted");
					messageRouterThread.put(requestedClientName, client);
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("Error Occured Oops!" +  e.getMessage());
		}
		
	}
	
}

class ServerThread extends Thread {
	
	private Map<String, Socket> clientList;
	
	private DataInputStream     is;
	private DataOutputStream    os;
	
	public ServerThread() {
		this.clientList = new HashMap<>();
		this.is = null;
		this.os = null;
	}
	
	public void put(String str, Socket so) {
		this.clientList.put(str, so);
	}
	
	public void run() {
		String msg = "";
		int i = 0;
		System.out.println("Serveur chat en cours d'exécution .....");
		String toClientName = "";
		
		while (true) {
			try {
				if (this.clientList != null) {
					for (String key : this.clientList.keySet()) {
						this.is = new DataInputStream(this.clientList.get(key).getInputStream());
						if (this.is.available() > 0) {
							msg = this.is.readUTF();
							i = msg.indexOf("@");
							toClientName = msg.substring(i);
							
							if (this.clientList.containsKey(toClientName)) {
								this.os = new DataOutputStream(this.clientList.get(toClientName).getOutputStream());
								this.os.writeUTF(key + ": " + msg.substring(0,i));
							} else {
								this.os = new DataOutputStream(this.clientList.get(key).getOutputStream());
								this.os.writeUTF("Message Du Serveur : Nom d'utilisateur incorrect ou inexistant");
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println("Server Recovering Initialization Failure ...");
				//System.out.println("Server Up And Running ...");
			}
		}
	}
}
*/
