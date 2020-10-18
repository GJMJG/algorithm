package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟Java Socket通信，Server是服务端套接字
 */
public class Server {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            ServerSocket server = new ServerSocket(2000);
            // Blocking until accept the request from client.
            Socket socket = server.accept();
            // Get the input stream.
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Get the output stream.
            pw = new PrintWriter(socket.getOutputStream(), true);
            String s = br.readLine(); //Get the accepted string.
            pw.println(s); // Send the same contents to the client.
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                pw.close();
            } catch (Exception e) {
            }
        }
    }
}
