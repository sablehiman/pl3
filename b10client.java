import java.io.*;
import java.net.*;

public class b10client {
    public static void main(String[] args) {
	
	String hostname = "localhost";
	int port = 6789;

        Socket clientSocket = null;  
        DataOutputStream os = null;
        BufferedReader is = null;		
        try {
            clientSocket = new Socket(hostname, port);
            os = new DataOutputStream(clientSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + hostname);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + hostname);
        }
	
	if (clientSocket == null || os == null || is == null) {
	    System.err.println( "Something is wrong. One variable is null." );
	    return;
	}

	try {
	    while ( true ) {
		System.out.print( "Enter  (sc to stop connection, ss to stop server): " );
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String keyboardInput = br.readLine();
		os.writeBytes( keyboardInput + "\n" );

		//int n = Integer.parseInt( keyboardInput );
		if ( keyboardInput.equals("sc")|| keyboardInput.equals("ss")) {
		    break;
		}
		String responseLine = is.readLine();
		System.out.println("Server returns:- " + responseLine);
	    }
	    os.close();
	    is.close();
	    clientSocket.close();   
	} catch (UnknownHostException e) {
	    System.err.println("Trying to connect to unknown host: " + e);
	} catch (IOException e) {
	    System.err.println("IOException:  " + e);
	}
    }           
}
