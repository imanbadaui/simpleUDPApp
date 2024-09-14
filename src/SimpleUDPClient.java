import java.net.*;
import java.io.*;

public class SimpleUDPClient {

	public static void main(String[] args) {
		DatagramSocket dgSocket = null;
		if (args.length < 3) {
			System.out.println("Pass the arguments for SimpleUDPClient in the order: "
					+ "Test message, Server Host name and Port number.");
			System.exit(1);
		}
		try {
			dgSocket = new DatagramSocket();
			byte[] bytes = args[0].getBytes();
			InetAddress serverHost = InetAddress.getByName(args[1]);
			int serverPortNumber = Integer.valueOf(args[2]).intValue();
			// send packet to server.
			DatagramPacket sentPacket = new DatagramPacket(bytes, bytes.length, serverHost, serverPortNumber);
			dgSocket.send(sentPacket);

			// receive packet from server.
			byte[] byteBuffer = new byte[1000];
			DatagramPacket receivedPacket = new DatagramPacket(byteBuffer, byteBuffer.length);
			dgSocket.receive(receivedPacket);
			System.out.println("Datagram Recieved: " + new String(receivedPacket.getData()));
		} catch (SocketException e) {
			System.out.println("Socket Exception:" + e.getMessage());

		} catch (IOException e) {
			System.out.println("IO Exception:" + e.getMessage());
		} finally {
			//checks for when if condition will terminate.
			if (dgSocket != null) {
				dgSocket.close();
			}

		}

	}
}
