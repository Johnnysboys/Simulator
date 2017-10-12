import Helpers.Logger;
import Helpers.Types;
import Interfaces.ICommands;
import Simulations.PLCSim;

import java.util.ArrayList;

public class Main implements ICommands {
    public static void main(String args[]) throws Exception {
        int amount = 1;
        int port = 5000;
        ArrayList<PLCSim> sims = new ArrayList<>();

        if(args.length > 0){
            try{
                for(int i = 0; i < args.length;  i++){
                    switch (args[i]){
                        case "-n":
                            amount = Integer.parseInt(args[i+1]);
                            break;
                        case "-p":
                            port = Integer.parseInt(args[i+1]);
                            break;
                    }
                }
            } catch (NumberFormatException ex){
                System.out.println("It isent numbers");
                System.out.println("thank you, come again!");
                System.exit(1);
            }
        }

        for(int i = 0; i < amount; i++){
            PLCSim sim = new PLCSim("Sim " + i, port + (i * 10));
            sims.add(sim);
            Logger.Log(Types.INFO,"Started " + sim.getName() + " on port " + sim.getPort());

        }

    }
}
