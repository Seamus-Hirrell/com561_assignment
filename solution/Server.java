package solution;

/*
Consider the following sequence processing in the Client/Server application. There is a list of students’ names stored on the Client-side in a JAVA ArrayList<String>. The Client sets the communication with the Server and passes on each element of the ArrayList to the Server. The Server receives each element converts it to uppercase and appends a Hello message and sends it back to the Client. The Client then displays the received message from the Server. Once the list iteration has finished the Client sends the exit message to the Server and then the Server closes. Overall, the communication behaviour between the Client and Server is as follows:
Step 1: Server waits for connections from Clients.
Step 2: Client retrieves the ArrayList and passes each element of the list to the Server.
Step 3: Server receives the sent element and calls the appropriate functionality to change it to uppercase and then adds the Hello message to it (e.g., Hello SANDRA!). 
Step 4: The result is forwarded to the Client, which reads the message and displays it on the console.
Step 5: The Client closes the communication with the Server after receiving all the elements (from the ArrayList) back from the Server as uppercase and a message added.
Step 6: Terminate the Server if the Client sends an exit request.
 */

import java.io.*;
import java.net.*;

public class Server {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(1234);
    System.out.println("Waiting for client connection...");

    Socket socket = serverSocket.accept();
    System.out.println("Client connected.");

    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

    String input;
    while ((input = in.readLine()) != null) {

      if (input.equals("exit")) {
        System.out.println("Client disconnected.");
        break;
      }

      System.out.println("Received: " + input);
      out.println("Hello " + input.toUpperCase() + "!");
      System.out.println("Sent: " + "Hello " + input.toUpperCase() + "!");
    }

    socket.close();
    serverSocket.close();
  }
}
