import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;


/**
 * This class is used create a new thread other than the 
 * javaFx thread in the client side of the application 
 * This object will be call in the controller class 
 */
public class ClientSideThread extends Thread {
    
    Socket socketClient;
	int port ; 
    String host ;
	ObjectOutputStream out;
	ObjectInputStream in;
    private PokerInfo pokerInfo; 
	
	public PokerInfo getPokerInfo() {
        return pokerInfo;
    }

    public void setPokerInfo(PokerInfo pokerInfo) {
        this.pokerInfo = pokerInfo;
    }

    private Consumer<Serializable> callback;
	
	ClientSideThread (Consumer<Serializable> call, String host, int port){
        this.port = port ; 
        this.host = host ;
        callback = call;
	}
    
    public void clientStart() throws IOException, ClassNotFoundException{
		
		socketClient= new Socket(host,port); // blocking class
	    System.out.println("Client: "+"Connection Established");
	    
	    System.out.println("The client is connected to: " +socketClient.getRemoteSocketAddress());
		
	    // out = new ObjectOutputStream(socketClient.getOutputStream());
	    // in = new ObjectInputStream(socketClient.getInputStream());
	    // socketClient.setTcpNoDelay(true);
	}
        
    // @Override
    public void run() {
        
        try {
   
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
        
        }
        catch(Exception e) {
            System.out.println("Error while trying to connect to the server");
            System.out.println(e);
        }
        // System.out.println("connection established with the server");
        /**
         * Infinite loop the thread to keep on running 
         * TODO: need to implement
         */
        while(true) {
            try {
                pokerInfo = (PokerInfo) in.readObject();
                // TODO make a call back accept 
                System.out.println("the Status is "+pokerInfo.getStatus());
                if(pokerInfo.getStatus() == "cards drawn"){
                System.out.println(pokerInfo.getPlayerCardsAString());
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
            // try {
        
            //     // String message = in.readObject().toString();
            //     // callback.accept(message);
            //     // recieving the pokerInfo object from the server
            //     PokerInfo currentHand = (PokerInfo)in.readObject();
            //     // System.out.println("recieved the pokerInfo object from the server");
            //     callback.accept(currentHand);

            //     PokerInfo userHand = currentHand;
            //     // change hand to whatever choices the client has made

            //     out.writeObject(userHand);


            // }
            // catch(Exception e) {
            //     System.out.println("Error while reading from the server");
			//     		break;
            // }
        }
    }

    public void send(PokerInfo pokerInfo) {
		
		try {
			out.writeObject(pokerInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
