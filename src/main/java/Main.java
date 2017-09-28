import Interfaces.ICommands;
import Simulations.PLCSim;

public class Main implements ICommands {
    public static void main(String args[]) throws Exception {

        new PLCSim("Name", 5000);
        new PLCSim("Name", 5010);
    }
}
