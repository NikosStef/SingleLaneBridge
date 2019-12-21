public class BlueCar implements Runnable {

    private Bridge bridge;
    private int ID;
    private int BlueColor;
    private String Scenario;

    BlueCar(Bridge bridge, int CarId, String Scenario) {
        this.bridge = bridge;
        this.ID = CarId;
        this.Scenario = Scenario;
        this.BlueColor = 1;
    }

    //Sxedon se ola ta senaria xrhsimopioithike kapoio thread.sleep() sxetika mikro, apla kai mono gia na iparxei mia omali roi kai na prolavainoun na kanoun arrive kai alla aftokinita otan tha pernaei kapoio.

    public void run() {

        //O xronos pou ksekinaei na trexei to thread kai ftanei to amaksi stin mia akri tis gefiras.
        long arrivalTime = System.currentTimeMillis();
        bridge.BlueArrived();
        System.out.println("                               BLUE Car " + ID + " arrived at time: " + arrivalTime + "\n");

        //Elexgos tou senariou me switch-case
        switch (Scenario) {
            //Scenario 1 (Anasfalis dielefsi).
            case ("1"): {

                long startTime = System.currentTimeMillis();
                System.out.println("                               BLUE Car " + ID + " Crossing at time: " + startTime + "\n");

                bridge.UnsafeCrossed(BlueColor);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            //Scenario 2 (Asfalhs alla adikh dielefsh, opoios prolavei pernaei).
            case ("2"): {

                bridge.SafeCrossed(BlueColor);

                long startTime = System.currentTimeMillis();
                System.out.println("                               BLUE Car " + ID + " Crossing at time: " + startTime + "\n");

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            //Scenario 3 (Asfalhs kai Dikaih dielefsh, Ginete elexgos tou xromatos tou aftokinitou pou perase proigoumenws kai antistixa to sigkekrimeno thread trexei i menei sto loop kai perimenei).
            case ("3"): {

                while (true) {
                    if (!(bridge.getPreviousCar() == BlueColor)) {
                        bridge.SafeFairCrossed(BlueColor);

                        long startTime = System.currentTimeMillis();
                        System.out.println("                               BLUE Car " + ID + " Crossing at time: " + startTime + "\n");

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
            //Scenario 4 (Asfalhs dielefsi analoga posa aftokinita perimenoun se kathe plevra. Elexgos tou plithous twn aftokiniton pou perimenoun se kathe plevra kai analoga me to apotelesma pernaei to sigkekrimeno aftokinito h perimenei sto loop).
            case ("4"): {

                while (true) {

                    if (bridge.getBlueCounter() >= bridge.getRedCounter()) {

                        bridge.SafeCrossed(BlueColor);

                        long startTime = System.currentTimeMillis();
                        System.out.println("                               BLUE Car " + ID + " Crossing at time: " + startTime + "\n");

                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        break;
                    }
                }
                break;
            }
            //Scenario 5 (Ylopioish fanariwn me xrono 15 millisec prasinou fanariou. Kathe aftokinito kanei 5 millisec na perasei (mesw tou thread.sleep()).
            case ("5"): {

                while (true) {

                    try {
                        //Elexgos an prepei to fanari na allaksei xrwma. An nai, ginete allagi kai pernaei to aftokinito.
                        bridge.myTurn(BlueColor, System.currentTimeMillis());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //Elexgos an einai prasino to fanari kai an prolavainei na perasei (afou kanei 5 millisecond gia na perasei).
                    if (bridge.inTime() && bridge.canPass(BlueColor)) {

                        bridge.TrafficLightCrossed();

                        long startTime = System.currentTimeMillis();
                        System.out.println("                               BLUE Car " + ID + " Crossing at time: " + startTime + "\n");

                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;

            }
        }


        bridge.Exit();
    }
}
