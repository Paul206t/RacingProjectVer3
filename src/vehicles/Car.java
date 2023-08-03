package vehicles;

import java.io.FileReader;
import java.util.Properties;

import static java.lang.Integer.parseInt;


public class Car {

    public int carID;
    public String manufacturer;
    public String carModel;
    public int year;
    public int power;
    public String drivetrain;
    public int topSpeed;
    public int weight;
    public String countryOfOrigin;




    // Car constructor
    public Car(int carID, String manufacturer, String carModel, int year, int power, String drivetrain, int topSpeed, int weight, String countryOfOrigin) {
        this.carID = carID;
        this.manufacturer = manufacturer;
        this.carModel = carModel;
        this.year = year;
        this.power = power;
        this.topSpeed = topSpeed;
        this.weight = weight;
        this.drivetrain = drivetrain;
        this.countryOfOrigin = countryOfOrigin;
    }

    // Generic dummy no args Car()
    public Car() {

    }

    // Inject a car ID and return the selected car based on cross-reference from car.properties
    public Car injectCar(int carID) throws Exception {

        Properties carProp;
        FileReader reader;

        // Pull proper car.properties file based on carID
        // TODO: this logic will need to change/look a little nicer as the game grows
        if(carID == 1){
            reader = new FileReader("resources/cars/car1.properties");
        }
        else if(carID == 2){
            reader = new FileReader("resources/cars/car2.properties");
        }
        else if(carID == 3){
            reader = new FileReader("resources/cars/car3.properties");
        }
        else{
            return null;
        }

        // Read the car file that was pulled
        carProp = new Properties();
        carProp.load(reader);

        // Parse needed String numbers to integers before passing arguments to constructor
        int caridInt = parseInt(carProp.getProperty("car.carID"));
        int yearInt = parseInt(carProp.getProperty("car.year"));
        int powerInt = parseInt(carProp.getProperty("car.power"));
        int topSpeedInt = parseInt(carProp.getProperty("car.topSpeed"));
        int weightInt = parseInt(carProp.getProperty("car.weight"));

        // Feed car from selected car.properties file into car constructor
        return new Car(caridInt, carProp.getProperty("car.manufacturer"), carProp.getProperty("car.carModel"), yearInt,
                powerInt, carProp.getProperty("car.drivetrain"), topSpeedInt,
                weightInt, carProp.getProperty("car.countryofOrigin"));
    }


}
