import java.io.*;
import java.net.*;

public class ChatClient {
	
	public Socket           client;
	
	public DataOutputStream os;
	public DataInputStream  is;
	
	public String           clientName;
	
	public ChatClient() {
		this.clientName = "@Client0";
		this.client     = null;
	}
	
	public static void main(String[] args) throws IOException {
		ChatClient a = new ChatClient();
		a.doConnection();
	
	}
	public void connection() throws IOException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Entrez votre pseudo <sans espace> : ");
			this.clientName = "@" + br.readLine();
			
			this.client = new Socket("127.0.0.1", 9001);
			
			this.os = new DataOutputStream(this.client.getOutputStream());
			this.is = new DataInputStream(this.client.getInputStream());
			
			String request = this.clientName;
			this.os.writeUTF(request);
			
			String reponse = this.is.readUTF(); 
			
			ReadThread  read  = new ReadThread(this.is);
			WriteThread write = new WriteThread(this.os);
			
			if (reponse.equals("#accepted")) {
				System.out.println("# Connection en tant que " + this.clientName + " réussie !");
				System.out.println("Format de message");
				System.out.println("-----------------");
				System.out.println("CORPS_DU_MESSAGE @nom_d_utilisateur");
				
				read.start();
				write.start();
				
				read.join();
				write.join();
			} else {
				System.out.println("# La connexion au serveur a échoué !");
			}
			
		} catch (Exception e) {
			System.out.println("Une erreur est survenue !");
		}
		
	}
	
}

class ReadThread extends Thread {
	
	private DataInputStream is;
	
	public MyThreadRead(DataInputStream i) {
		this.is = i;  
	}
	
	@Override
	public void run() {
		try{
			String msg = null;
			while (true) {
				msg = this.is.readUTF();
				if (msg != null)
					System.out.println(msg);
				msg = null;
			}
			
		} catch (Exception e) {
		}
		
	}
	
}

class WriteThread extends Thread {
	
	private DataOutputStream os;
	private BufferedReader   br;
	
	public MyThreadWrite(DataOutputStream o) {
		this.os = o;
		
		try {
			this.br = new BufferedReader(new InputStreamReader(System.in));
		} catch (Exception e) {
		}
		
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String msg = this.br.readLine();
				this.os.writeUTF(msg);
			}
		} catch (Exception e) {
		}
		
	}
	
}
