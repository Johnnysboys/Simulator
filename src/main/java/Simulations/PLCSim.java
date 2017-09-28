import java.net.SocketException;

public class PLCSim implements ICommands {
    private Temperature temperature;
    private WaterLevel waterLevel;
    private Moisture moisture;
    private String name;

    private UDPConnection server;



    public PLCSim(String name, int port) throws SocketException {
        this.name = name;
        this.server = new UDPConnection(port, this);
        this.temperature = new Temperature();
        this.waterLevel = new WaterLevel();
        this.moisture = new Moisture();
    }

    /**
     * Decode what the SCADA wants to do.
     * @param commandPacket
     */
    public void decodeCommand(CommandPacket commandPacket) {
        switch (commandPacket.getCommand()){
            case TEMP_SETPOINT:
                this.temperature.setSetPoint(commandPacket.getData());
                this.server.respond(TEMP_SETPOINT_ACK, commandPacket.getSerialNo(), commandPacket.getData());
                break;
            case ADDWATER:
                this.waterLevel.addWater(commandPacket.getData());
                break;
            case READ_GREENHOUSE_TEMP:
                byte temp = (byte) this.temperature.getSetPoint();
                this.server.respond(READ_GREENHOUSE_TEMP_ACK, commandPacket.getSerialNo(), temp);
                break;
            case READ_MOISTURE:
                byte moisture = (byte) this.moisture.getMoisture();
                this.server.respond(READ_MOISTURE_ACK, commandPacket.getSerialNo(), moisture);
                break;
            case READ_OUTDOOR_TEMP:
                byte roomTemp = (byte) this.temperature.getRoomTemperature();
                this.server.respond(READ_GREENHOUSE_TEMP_ACK, commandPacket.getSerialNo(), roomTemp);
                break;
            case READ_WATER_LEVEL:
                byte waterLevel = (byte) this.waterLevel.readWaterLevel();
                this.server.respond(READ_WATER_LEVEL_ACK, commandPacket.getSerialNo(), waterLevel);
                break;
        }
    }
}
