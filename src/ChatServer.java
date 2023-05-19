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
				//System.out.println("Server Recovering Initialization Failure ...");
				//System.out.println("Server Up And Running ...");
			}
		}
	}
}
