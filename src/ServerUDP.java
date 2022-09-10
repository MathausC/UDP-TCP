import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDP extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buffer;

    public ServerUDP(int address, int bufferSize) {
        try {
            this.buffer = new byte[bufferSize];
            this.socket = new DatagramSocket(address);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            running = true;

            while (running) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer, buffer.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());

                if (received.equals("end")) {
                    running = false;
                }
                socket.send(packet);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}