import java.io.IOException;
import java.net.*;

public class Rfc865UdpServer {
    public static void main(String[] argv) {
        final int PORT = 17; // RFC 865 UDP port
        DatagramSocket socket = null;

        try {
            // 1. Open UDP socket at well-known port
            socket = new DatagramSocket(PORT);
            System.out.println("RFC 865 UDP Server running on port " + PORT);

            while (true) {
                try {
                    // 2. Listen for UDP request from client
                    byte[] buffer = new byte[512];
                    DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                    socket.receive(request);

                    InetAddress clientAddress = request.getAddress();
                    int clientPort = request.getPort();

                    // 3. Send UDP reply (quote of the day)
                    String quote = "What is time? Time is the stream I go a-fishing in. - Henry David Thoreau";
                    byte[] responseData = quote.getBytes();
                    DatagramPacket reply = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);

                    socket.send(reply);
                    System.out.println("Sent quote to " + clientAddress + ":" + clientPort);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
        }
    }
}