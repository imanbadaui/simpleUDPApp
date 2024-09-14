import java.net.*;
import java.io.*;

public class SimpleUDPServer {

	public static void main(String[] args) {
	DatagramSocket dgSocket = null;
	if(args.length <= 0) {
		System.out.println("please pass the port number for UDP Server");
		//exit the program if no port number passed.
		System.exit(1);
	}
	
	try{
		int portNumber = Integer.parseInt(args[0]);
		//opens connection on portNumber.
		dgSocket = new DatagramSocket(portNumber);
		byte[] byteBuffer = new byte[1000];
		while(true) {
			//receive packet from client.
			//ReceivedPacket contains sender's address and port.
			DatagramPacket ReceivedPacket = new DatagramPacket(byteBuffer, byteBuffer.length);
			dgSocket.receive(ReceivedPacket);
			//send packet back to client.
			DatagramPacket SentPacket = new DatagramPacket(ReceivedPacket.getData(), 
					ReceivedPacket.getLength(), ReceivedPacket.getAddress(), ReceivedPacket.getPort());
			dgSocket.send(SentPacket);
			}
		
		}catch(SocketException e) {
		System.out.println("Socket Exception:" + e.getMessage());
		
	}catch(IOException e) {
		System.out.println("IO Exception:" + e.getMessage());
	}finally{
		if(dgSocket != null) {
			dgSocket.close();
		}
	}
}
}
