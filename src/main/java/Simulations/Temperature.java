public class Temperature implements Runnable {

    private float temperature = 17f;
    private float roomTemperature = 16f;
    private boolean heating = true;
    private float deltaOn = 1.006f;
    private float deltaOff = 0.906f;
    private float setPoint;
    private boolean on = false;

    public Temperature(){
        this.setPoint = 25f;
        new Thread(this).start();
    }
    public Temperature(float setPoint){
        this.setPoint = setPoint;
        new Thread(this).start();
    }


    @Override
    public void run() {
        while(true){

            if(this.temperature < this.setPoint && this.on){
                this.heating = true;
            } else {
                this.heating = false;
            }

            if(this.heating){
                this.temperature *= deltaOn;
            }

            if(!this.heating && !(this.temperature <= this.roomTemperature)){
                this.temperature *= deltaOff;
            }

            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean isHeating() {
        return heating;
    }

    public float getTemperature() {
        return temperature;
    }

    public boolean isOn() {

        return on;
    }

    public void setOn(boolean on) {
        System.out.println("temperature on");
        this.on = on;
    }

    public float getSetPoint() {
        return setPoint;
    }

    public void setSetPoint(float setPoint) {
        this.setPoint = setPoint;
    }

    public float getRoomTemperature() {
        return roomTemperature;
    }

    public void setRoomTemperature(float roomTemperature) {
        this.roomTemperature = roomTemperature;
    }
}
