/*
Name: YourName
Group: YourLabGroup
IP Address: YourClientIPAddress
*/

import java.io.IOException;
import java.net.*;

public class Rfc865UdpClient {
    public static void main(String[] argv) {
        final int SERVER_PORT = 17; // RFC 865 port
        final String SERVER_HOST = "127.0.0.1"; // localhost for same machine

        try {
            // 1. Open UDP socket
            DatagramSocket socket = new DatagramSocket();

            // 2. Send UDP request to server
            String requestMessage = "YourName, YourLabGroup, " + InetAddress.getLocalHost().getHostAddress();
            byte[] requestData = requestMessage.getBytes();
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);
            DatagramPacket request = new DatagramPacket(requestData, requestData.length, serverAddress, SERVER_PORT);
            socket.send(request);
            System.out.println("Request sent to server: " + requestMessage);

            // 3. Receive UDP reply from server
            byte[] buffer = new byte[512];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.receive(reply);

            String quote = new String(reply.getData(), 0, reply.getLength());
            System.out.println("Quote of the Day: " + quote);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}