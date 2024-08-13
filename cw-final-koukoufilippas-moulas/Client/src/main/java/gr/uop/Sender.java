<<<<<<< HEAD
package gr.uop;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Sender {
   private Socket socket;
   private DataOutputStream out;
   private ObjectOutputStream object_out;

    public void establishConnection() {
        try {
            // Create a socket and establish a connection to the server
            socket = new Socket("localhost", 7777);
            out = new DataOutputStream(socket.getOutputStream());
            object_out = new ObjectOutputStream(socket.getOutputStream());

            DataOutputStream object_out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Connection was established");
        } catch (IOException e) {
            System.err.println("Error establishing connection to server: " + e);
        }
    }

    public void sendData(String licencePlate,String cost,String vehiclekind,ArrayList<String> services) {

        try(Socket socket = new Socket("localhost", 7777);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            String servicesString = String.join(",", services);
                
            out.writeUTF(licencePlate);
            out.writeUTF(cost);
            out.writeUTF(vehiclekind);
            out.writeUTF(servicesString);

            out.close();
            socket.close();
        }

        catch (IOException e) {
            System.err.println("Error establishing connection to server: " + e);
        }
       
    }

    public void closeConnection() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e);
        }
    }
}
=======
package gr.uop;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Sender {
   private Socket socket;
   private DataOutputStream out;
   private ObjectOutputStream object_out;

    public void establishConnection() {
        try {
            // Create a socket and establish a connection to the server
            socket = new Socket("localhost", 7777);
            out = new DataOutputStream(socket.getOutputStream());
            object_out = new ObjectOutputStream(socket.getOutputStream());

            DataOutputStream object_out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Connection was established");
        } catch (IOException e) {
            System.err.println("Error establishing connection to server: " + e);
        }
    }

    public void sendData(String licencePlate,String cost,String vehiclekind,ArrayList<String> services) {

        try(Socket socket = new Socket("localhost", 7777);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

            String servicesString = String.join(",", services);
                
            out.writeUTF(licencePlate);
            out.writeUTF(cost);
            out.writeUTF(vehiclekind);
            out.writeUTF(servicesString);

            out.close();
            socket.close();
        }

        catch (IOException e) {
            System.err.println("Error establishing connection to server: " + e);
        }
       
    }

    public void closeConnection() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e);
        }
    }
}
>>>>>>> 922c45c22f807447bb23997b5a0b839e3ea69cbd
