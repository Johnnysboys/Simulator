package Simulations;

public class Temperature implements Runnable {

    private double temperature = 17f;
    private double roomTemperature = 16f;
    private int temperatureFallDelay = 1000; // in ms
    private boolean heating = true;
    private double deltaOn = 1.006f; // ~ 0.4%
    private double deltaOff = 0.995f; // ~ 0.5%
    private double setPoint;

    /**
     * Simulate the temperature.
     */
    public Temperature(){
        this.setPoint = this.temperature;
        new Thread(this).start();
    }

    public Temperature(double setPoint){
        this.setPoint = setPoint;
        new Thread(this).start();
    }


    @Override
    public void run() {
        while(true){
            // Only heat the green house if its below the temperature
            if(this.temperature <= this.setPoint){
                this.heating = true;
            } else {
                this.heating = false;
            }

            // the temperature rises with ~0.4%
            if(this.heating){
                this.temperature *= deltaOn;
            }

            // The temperature falls with ~0.5%
            if(!this.heating && !(this.temperature <= this.roomTemperature)){
                this.temperature *= deltaOff;
            }

            if(this.temperature <= this.roomTemperature){
                this.temperature = this.roomTemperature;
            }
            try {
                Thread.sleep(this.temperatureFallDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Set the temperature set point
     * @param setPoint
     */
    public void setSetPoint(double setPoint) {
        this.setPoint = setPoint;
    }

    /**
     * Returns the "room temperature" around the green house
     * @return double
     */
    public double getRoomTemperature() {
        return roomTemperature;
    }

    /**
     * Returns the current "temperature" inside the green house
     * @return double
     */
    public double getTemperature() {
        return temperature;
    }
}
