package logic;

import vehicles.Car;

import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

public class GameLogic {

    // Init Scanner
    static Scanner scan = new Scanner(System.in);

    public void intro() throws Exception {

        // Pull options file
        Properties optionsProp = pullOptions();

        // Pull text/translation file
        Properties textProp = OptionLogic.loadTranslation();

        // Printing introduction using selected translation/text file
        System.out.println("\n" + textProp.getProperty("text.intro.line1") + " " + optionsProp.getProperty("options.name") + ".");
        System.out.println(textProp.getProperty("text.intro.line2"));
        System.out.println(textProp.getProperty("text.intro.line3") + " " + optionsProp.getProperty("options.language") + ".");
    }

    public Properties pullOptions() throws Exception {

        // Pull options.properties file
        FileReader optionsReader = new FileReader("resources/options.properties");
        Properties optionsProp = new Properties();
        optionsProp.load(optionsReader);

        Properties result = optionsProp;
        return result;
    }


    // TODO: Simple function to pick a car. It's pretty rudimentary and doesn't really check much. This needs to be improved
    public int pickCarFromGarage() throws Exception {

        Properties textProp = OptionLogic.loadTranslation();

        System.out.println();
        System.out.print(textProp.getProperty("text.pickCar"));
        int result = scan.nextInt();

        while ((result > 3) || (result < 0)) {
            System.out.print(textProp.getProperty("text.pickCarError"));
            result = scan.nextInt();
        }
        return result;
    }

    public void printCurrentCarStats(int pickedCar) throws Exception {

        Car car = new Car();

        // Pull selected car
        Car selectedCar = car.injectCar(pickedCar);

        // Load text file
        Properties textProp = OptionLogic.loadTranslation();

        System.out.println();
        System.out.println(textProp.getProperty("text.selectedCarMsg"));
        System.out.println(textProp.getProperty("text.carStats.year") + ": " + selectedCar.year);
        System.out.println(textProp.getProperty("text.carStats.manufacturer") + ": " + selectedCar.manufacturer);
        System.out.println(textProp.getProperty("text.carStats.carModel") + ": " + selectedCar.carModel);
        System.out.println(textProp.getProperty("text.carStats.countryOfOrigin") + ": " + selectedCar.countryOfOrigin);
        System.out.println(textProp.getProperty("text.carStats.drivetrain") + ": " + selectedCar.drivetrain);
        System.out.println(textProp.getProperty("text.carStats.power") + ": " + selectedCar.power);
        System.out.println(textProp.getProperty("text.carStats.topSpeed") + ": " + selectedCar.topSpeed);
        System.out.println(textProp.getProperty("text.carStats.weight") + ": " + selectedCar.weight);
    }



    // TODO: this particular method is old logic from my previous 'GT' project
    public void laps() throws Exception {

        Properties textProp = OptionLogic.loadTranslation();

        // Asking for number of laps
        System.out.print("\n" + textProp.getProperty("text.laps.numLaps"));
        int numLaps = scan.nextInt();

        if (numLaps > 10) {
            System.out.println(textProp.getProperty("text.laps.numLapsMoreThanAllowed"));
            laps();
        } else if (numLaps < 1) {
            System.out.println(textProp.getProperty("text.laps.numLapsLessThanAllowed"));
            laps();
        } else {
            // Asking for each lap time
            System.out.println("\n" + textProp.getProperty("text.laps.lapSeconds") + "\n");
            // Created double type array to store size of it based on what was entered for numLaps
            double[] lapTimes = new double[numLaps];

            // For loop to ask for each Lap time only up to the number of laps that was inputted (lapTimes.length)
            // and stores each lap time that's been input in each next iterated element of the lapTimes Array
            for (int i = 0; i < lapTimes.length; i++) {
                System.out.print(textProp.getProperty("text.laps.lapTime"));
                lapTimes[i] = scan.nextDouble();
            }

            // Sets fastest lap initially to first element of lapTimes array
            double fastestTime = lapTimes[0];

            // For loop to compare each element of the array to the next until it gets to the end and sets
            // fastestTime from that element of the lapTimes array, int i starts at index 1 because fastestTime
            // was already set to index 0 for comparison
            for (int i = 1; i < lapTimes.length; i++) {
                if (lapTimes[i] < fastestTime) {
                    fastestTime = lapTimes[i];
                }
            }

            System.out.format("\n" + textProp.getProperty("text.laps.fastestLapTime") + " %.2f seconds \n", fastestTime);
            // Get sum of all lap times and calculate + print the average lap time
            double sumLaps = 0;
            double lengthLapTimes = lapTimes.length;
            for (int i = 0; i < lapTimes.length; i++) {
                sumLaps += lapTimes[i];
            }
            double avgLapTimes = sumLaps / lengthLapTimes;
            System.out.format(textProp.getProperty("text.laps.avgLapTime") + " %.2f seconds \n", avgLapTimes);

        }
    }
}
