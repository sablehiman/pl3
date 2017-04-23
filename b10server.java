import java.io.*;
import java.net.*;

public class b10server {
    public static void main(String args[]) {
	int port = 6789;
	b10server server = new b10server( port );
	server.startServer();
    }

    ServerSocket echoServer = null;
    Socket clientSocket = null;
    int numConnections = 0;
    int port;
	
    public b10server( int port ) {
	this.port = port;
    }

    public void stopServer() {
	System.out.println( "Server cleaning up." );
	System.exit(0);
    }

    public void startServer() {
	// Note that we can't choose a port less than 1024 if we are not root
		
        try {
	    echoServer = new ServerSocket(port);
        }
        catch (IOException e) {
	    System.out.println(e);
        }   
	
	System.out.println( "Server is started and is waiting for connections." );
	System.out.println( "With multi-threading, multiple connections are allowed." );
	System.out.println( "Any client can send ss to stop the server." );

	// Whenever a connection is received, start a new thread to process the connectionand wait for the next connection. 
	while ( true ) {
	    try {
		clientSocket = echoServer.accept();
		numConnections ++;
		b10serverConnection oneconnection = new b10serverConnection(clientSocket, numConnections, this);
		new Thread(oneconnection).start();
	    }   
	    catch (IOException e) {
		System.out.println(e);
	    }
	}
    }
}

class b10serverConnection implements Runnable {
    BufferedReader is;
    PrintStream os;
    Socket clientSocket;
    int id;
    b10server server;

    public b10serverConnection(Socket clientSocket, int id, b10server server) {
	this.clientSocket = clientSocket;
	this.id = id;
	this.server = server;
	System.out.println( "Connection " + id + " established with: " + clientSocket );
	try {
	    is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    os = new PrintStream(clientSocket.getOutputStream());
	} catch (IOException e) {
	    System.out.println(e);
	}
    }

    public void run() {
        String line;
	try {
	    boolean serverStop = false;

            while (true) {
                line = is.readLine();
		System.out.println( "Received " + line + " from Connection " + id + "." );
                //int n = Integer.parseInt(line);
		if ( line.equals("ss") ) {
		    serverStop = true;
		    break;
		}
		if ( line.equals("sc") ) break;
                os.println("" + line ); 
            }

	    System.out.println( "Connection " + id + " closed." );
            is.close();
            os.close();
            clientSocket.close();

	    if ( serverStop ) server.stopServer();
	} catch (IOException e) {
	    System.out.println(e);
	}
    }
}
