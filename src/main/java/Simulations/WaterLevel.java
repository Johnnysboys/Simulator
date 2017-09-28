package Simulations;

public class WaterLevel implements Runnable {
    private double waterLevel = 70d;
    private double evaporation = 0.02d;
    private double threashold = 70d;
    private double pumpFlow = 2d;
    private int evaporationDelay = 10000; // Delay in ms

    /**
     * Starts a new thread simulating the Waterlevel of a greenhouse
     */
    public WaterLevel (){
        new Thread(this).start();
    }

    /**
     * Simulate the PLC's add_water command
     * 'Adds' water for a specific amout of seconds
     * @param seconds
     */
    public void addWater(int seconds){
        this.waterLevel += (seconds / pumpFlow);
        if(this.waterLevel > 120d) this.waterLevel = 120d;
    }

    @Override
    public void run() {
        while (true) {
            double delta = evaporation * waterLevel;

            if(!(this.waterLevel - delta >= this.threashold))
                this.waterLevel = this.threashold;
            else
                this.waterLevel -= delta;

            try {
                Thread.sleep(this.evaporationDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read the "water level" of the green house
     * @return the waterLevel
     */
    public double readWaterLevel(){
        return this.waterLevel;
    }
}
