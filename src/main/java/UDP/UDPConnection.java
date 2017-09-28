import java.io.IOException;
import java.net.*;

public class UDPConnection implements Runnable, IMessage, ICommands {
    private byte[] receiveData = new byte[1024];
    private int recentPort;
    private InetAddress recentHost;
    private DatagramSocket serverSocket;
    private PLCSim simulator;

    /**
     * Create a UDP listener for a specific PLC instance.
     * @param port - The port to listen to
     * @param simulator - The PLC instance to send incoming commands to
     * @throws SocketException
     */

    public UDPConnection(int port, PLCSim simulator) throws SocketException {
        this.simulator = simulator; // This is the simulated PLC
        this.serverSocket = new DatagramSocket(port);
        // Start a thread receiving UDP packets indefinitely
        new Thread(this).start();
    }

    /**
     * Respond the last one who contacted us.
     *
     * @param command Should be the command of the original UDP message + 0x40 (64 bits)
     * @param serialNo Should be the serial of the UDP message we are responding to.
     * @param data of the respond.
     */

    public void respond(byte command, byte serialNo, byte data){
        byte[] msg = Message.packMessage(command, serialNo, data);

        try {
            DatagramPacket sendPacket = new DatagramPacket(msg, msg.length, this.recentHost, this.recentPort);
            this.serverSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Respond the last one who contacted us without data.
     *
     * @param command Should be the command of the original UDP message + 0x40 (64 bits)
     * @param serialNo Should be the serial of the UDP message we are responding to.
     */

    public void respond(byte command, byte serialNo){
        byte[] msg = Message.packMessage(command, serialNo);

        try {
            DatagramPacket sendPacket = new DatagramPacket(msg, msg.length, this.recentHost, this.recentPort);
            this.serverSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                this.serverSocket.receive(receivePacket);
                // Set the recent(port/host) to the last one who talked to us
                // so we can respond the the correct one.
                this.recentHost = receivePacket.getAddress();
                this.recentPort = receivePacket.getPort();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.simulator.decodeCommand(new CommandPacket(receivePacket));
        }
    }
}
