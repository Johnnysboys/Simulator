import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Main implements ICommands {
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(5000);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        Temperature t = new Temperature();

        System.out.println("Simulator started on port " + serverSocket.getLocalPort());
        while(true)
        {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            CommandPacket cmd = new CommandPacket(receivePacket);

            switch (cmd.getCommand()){
                case TEMP_SETPOINT:
                    t.setOn(true);
                    try {
                        t.setSetPoint((float) cmd.getData());
                    } catch (SetPointException e) {
                        e.printStackTrace();
                    }
                    break;
                case READ_GREENHOUSE_TEMP:

            }

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
