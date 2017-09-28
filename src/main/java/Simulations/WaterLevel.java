public class WaterLevel implements Runnable {
    private double waterLevel = 650d;
    private double evaporation = 0.02d;
    private double plantMultiplier = 2.0d;
    private double pumpFlow = 2d;
    private boolean plant = false;

    /**
     * Starts a new thread simulating the Waterlevel of a greenhouse
     */
    public WaterLevel (){
        new Thread(this).start();
    }

    /**
     * Simulate the PLC's add_water command
     * 'Adds'
     * @param seconds
     */
    public void addWater(int seconds){
        this.waterLevel += (seconds * pumpFlow);
    }

    public void setPlant(boolean plant){
        this.plant = plant;
    }

    @Override
    public void run() {
        while (true) {
            waterLevel -= this.plant ? (evaporation * plantMultiplier * waterLevel) : (evaporation * waterLevel);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double readWaterLevel(){
        return this.waterLevel;
    }
}
