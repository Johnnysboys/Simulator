import java.io.*;
import java.net.*;

class UDPServer
{
    private int port;
    private DatagramSocket serverSocket;
    byte[] receiveData = new byte[1024];
    byte[] sendData = new byte[1024];

    public UDPServer(int port) throws SocketException{
        this.port = port;
        serverSocket = new DatagramSocket(this.port);

    }
}