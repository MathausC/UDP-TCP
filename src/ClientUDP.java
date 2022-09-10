import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClientUDP {
    private DatagramSocket socket;
    private InetAddress address;
    private int serverAdress;

    private byte[] buffer;

    public ClientUDP(String localAdress, int serverAdress) {
        try {
            this.serverAdress = serverAdress;
            this.socket = new DatagramSocket();
            this.address = InetAddress.getByName(localAdress);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String send(String msg) {
        try {
            buffer = msg.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, serverAdress);
            this.socket.send(packet);
            packet = new DatagramPacket(buffer, buffer.length);
            this.socket.receive(packet);
            String received = new String(packet.getData(), 0, packet.getLength());
            return received;
        } catch (IOException e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public void close() {
        socket.close();
    }
}
