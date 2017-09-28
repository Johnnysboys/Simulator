package UDP;

import Interfaces.IMessage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Message implements IMessage {


    /**
     * Create a UDP message to the SCADA system with data as one byte
     * @param command - byte - The command of the message
     * @param serialNo - byte - The serialNo of the UDP message
     * @param data - byte - The data of the UDP message
     * @return a byte array ready for a DatagramPacket
     */
    public static byte[] packMessage(byte command, byte serialNo, byte data){
        GregorianCalendar timestamp = new GregorianCalendar();

        byte[] packet = new byte[10 + 1];
        packet[COMMAND] = command;
        packet[DIRECTION] = 1;
        packet[SIZE] = 1;
        packet[SERIAL_NO] = serialNo;

        packet[YEAR] = (byte) (timestamp.get(Calendar.YEAR) - 2000);
        packet[MONTH] = (byte) timestamp.get(Calendar.MONTH);
        packet[DAY] = (byte) timestamp.get(Calendar.DAY_OF_MONTH);
        packet[HOUR] = (byte) timestamp.get(Calendar.HOUR_OF_DAY);
        packet[MINUTE] = (byte) timestamp.get(Calendar.MINUTE);
        packet[SECOND] = (byte) timestamp.get(Calendar.SECOND);

        packet[DATA_START] = data;

        return packet;
    }


    /**
     * Create a UDP message to the SCADA system with data as byte array
     * @param command - byte - The command of the message
     * @param serialNo - byte - The serialNo of the UDP message
     * @param data - byte[] - The data of the UDP message
     * @return byte array ready for a DatagramPacket
     */

    public static byte[] packMessage(byte command, byte serialNo, byte[] data){
        throw new NotImplementedException();
//        GregorianCalendar timestamp = new GregorianCalendar();
//
//        byte[] packet = new byte[10 + data.length];
//        packet[COMMAND] = command;
//        packet[DIRECTION] = 1;
//        packet[SIZE] = (byte) data.length;
//        packet[SERIAL_NO] = serialNo;
//
//        packet[YEAR] = (byte) (timestamp.get(Calendar.YEAR) - 2000);
//        packet[MONTH] = (byte) timestamp.get(Calendar.MONTH);
//        packet[DAY] = (byte) timestamp.get(Calendar.DAY_OF_MONTH);
//        packet[HOUR] = (byte) timestamp.get(Calendar.HOUR_OF_DAY);
//        packet[MINUTE] = (byte) timestamp.get(Calendar.MINUTE);
//        packet[SECOND] = (byte) timestamp.get(Calendar.SECOND);
//
//        // TODO: Concatnate data array with packet at the end
////        packet[DATA_START] = data;
//
//        return packet;
    }


    /**
     * Create a UDP message to the SCADA system with no data.
     * @param command - byte - The command of the message
     * @param serialNo - byte - The serialNo of the UDP message
     * @return a byte array ready for a DatagramPacket
     */

    public static byte[] packMessage(byte command, byte serialNo) {
        GregorianCalendar timestamp = new GregorianCalendar();

        byte[] packet = new byte[10];
        packet[COMMAND] = command;
        packet[DIRECTION] = 1;
        packet[SIZE] = 0;
        packet[SERIAL_NO] = serialNo;

        packet[YEAR] = (byte) (timestamp.get(Calendar.YEAR) - 2000);
        packet[MONTH] = (byte) timestamp.get(Calendar.MONTH);
        packet[DAY] = (byte) timestamp.get(Calendar.DAY_OF_MONTH);
        packet[HOUR] = (byte) timestamp.get(Calendar.HOUR_OF_DAY);
        packet[MINUTE] = (byte) timestamp.get(Calendar.MINUTE);
        packet[SECOND] = (byte) timestamp.get(Calendar.SECOND);

        return packet;
    }
}
