import java.net.DatagramPacket;

public class CommandPacket implements IMessage{
    private byte command;
    private byte serialNo;
    private byte size; // Length of data
    private byte[] data;


    /**
     * Used to easy decode the Datagram packet
     * @param packet
     */
    public CommandPacket(DatagramPacket packet){
        byte[] temp = packet.getData();
        this.size = temp[SIZE];
        this.command = temp[COMMAND];
        this.serialNo = temp[SERIAL_NO];
        if(this.size >= 1){
            // If there is any data, put it in an array
            this.data = new byte[this.size];
            System.arraycopy(packet.getData(), DATA_START, this.data, 0, this.size);
        }
    }

    /**
     * Get the command from the UDP packet
     * @return byte
     */
    public byte getCommand() {
        return command;
    }

    /**
     * Get the data from the UDP packet
     * @return byte
     */
    public byte getData() {
        return data[0];
    }

    /**
     * Returns the serial of the UDP packet
     * @return byte
     */
    public byte getSerialNo() {
        return serialNo;
    }
}
