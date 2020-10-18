package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 模拟客户端Socket通信
 */
public class Client {
    public static void main(String[] args) {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            Socket socket = new Socket("localhost", 2000);
            // Get the input stream and the output stream.
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            //Send the message to the server.
            pw.println("Hello");
            String s = null;
            while (true) {
                s = br.readLine();
                if (s != null) break;
            }
            System.out.println(s);
            //socket.shutdownInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                br.close();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
