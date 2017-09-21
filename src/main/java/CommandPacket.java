import java.net.DatagramPacket;
import java.util.Arrays;
import java.util.Calendar;

public class CommandPacket implements IMessage{
    private byte command;
    private byte serialNo;
    private byte direction;
    private byte size; //length of data
    private Calendar timestamp;
    private byte [] data;
    protected byte [] answer = new byte[125];


    public CommandPacket(DatagramPacket packet){
        byte[] temp = packet.getData();
        this.size = temp[SIZE];
        this.command = temp[COMMAND];
        this.serialNo = temp[SERIAL_NO];
        this.direction = temp[DIRECTION];
        this.data = new byte[this.size];
        System.arraycopy(packet.getData(), DATA_START, this.data, 0, temp[SIZE]);
    }

    public byte getCommand() {
        return command;
    }

    public byte getData() {
        return data[0];
    }
}
