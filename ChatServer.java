import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	
	private static final int PORT = 9001;
	
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
			server = new ServerSocket(ChatServer.PORT);
			MyThreadServer messageRouterThread = new MyThreadServer();
			messageRouterThread.start();
			
			while (true) {
				client = server.accept();
				
				if (client != null) {
					os = new DataOutputStream(client.getOutputStream());
					is = new DataInputStream(client.getInputStream());
					
					String requestedClientName = is.readUTF();
					clientList.put(requestedClientName, client);
					os.writeUTF("#accepted");
					messageRouterThread.clientList.put(requestedClientName, client);
				} 
			}
		} catch(Exception e) {
			System.out.println("Une erreur est arrivée !");
			e.printStackTrace(System.out);
		}
	}
}

class MyThreadServer extends Thread {
	
	public Map<String, Socket> clientList;
	
    public DataInputStream     is;
    public DataOutputStream    os;
	
	public MyThreadServer() {
		this.clientList = new HashMap<>();
		this.is = null;
		this.os = null;
	}
	
    public void run() {
        String msg = "";
        int i = 0;
		
        System.out.println("Serveur de conversation en cours d'exécution sur l'adresse '" + InetAddress.getLocalHost() + "' et sur le port '" + ChatServer.PORT + "'  .....");
        String toClientName = "";
		
		while (true) {
			try {
				if (clientList != null) {
					for (String key: clientList.keySet()) {
						is = new DataInputStream(clientList.get(key).getInputStream());
						if (is.available() > 0) {
							msg = is.readUTF();
							i = msg.indexOf("@");
							toClientName = msg.substring(i);
							if (clientList.containsKey(toClientName)) {
								os = new DataOutputStream(clientList.get(toClientName).getOutputStream());
								os.writeUTF(key + ": " + msg.substring(0,i));
								System.out.println("@SERVER : " + key + ": " + msg.substring(0, i) + " to " + toClientName);
							} else {
								os = new DataOutputStream(clientList.get(key).getOutputStream());
								os.writeUTF("Message du serveur : aucun nom d'utilisateur trouvé");
							}
						}
					}
				}
			} catch (Exception e) {
				//System.out.println("Server Recovering Initialization Failure ...");
				System.out.println("Serveur opérationnel ...");
			}
		}
	}
}
