package logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

public class OptionLogic {

    // Init Scanner
    static Scanner scan = new Scanner(System.in);


    public void takeName() throws Exception {

        // Establishing optionsPropSet, inputStream, and outputStream
        Properties optionsProp = new Properties();
        FileInputStream inputStream;
        FileOutputStream outputStream;

        // Taking name
        System.out.print("What's your name? ");
        String myName = scan.next();

        // Loading options.properties to pull everything
        inputStream = new FileInputStream("resources/options.properties");
        optionsProp.load(inputStream);

        // Writing name to options.properties file
        optionsProp.setProperty("options.name", myName);

        // Storing the name and everything else that was loaded from options.properties
        outputStream = new FileOutputStream("resources/options.properties");
        optionsProp.store(outputStream, null);

    }

    public void languageSelect() throws Exception {

        // Establishing optionsPropSet, inputStream, and outputStream
        Properties optionsProp = new Properties();
        FileInputStream inputStream;
        FileOutputStream outputStream;

        // Take language selection
        System.out.print("Press 1 for English, or anything else for Spanish: ");
        int chosenLanguage = scan.nextInt();

        // Loading options.properties to pull everything
        inputStream = new FileInputStream("resources/options.properties");
        optionsProp.load(inputStream);

        // Writes language based on earlier input
        if (chosenLanguage == 1) {
            optionsProp.setProperty("options.language", "English");
        }
        else {
            optionsProp.setProperty("options.language", "Espanol");
        }

        // Storing the language and everything else that was loaded from options.properties
        outputStream = new FileOutputStream("resources/options.properties");
        optionsProp.store(outputStream, null);

    }


    public static Properties loadTranslation() throws Exception{
        // Init textReader and testProp to prepare text translation file
        FileReader textReader;
        Properties textProp;

        // Pull options.properties
        FileReader optionsReader = new FileReader("resources/options.properties");
        Properties optionsProp = new Properties();
        optionsProp.load(optionsReader);

        // Assign chosen language String from language that is saved in options.language file
        String chosenLang = optionsProp.getProperty("options.language");

        // Conditional to choose translation file
        if(chosenLang.equalsIgnoreCase("Espanol")){
            textReader = new FileReader("resources/translations/text_spa.properties");
        }
        else {
            textReader = new FileReader("resources/translations/text_eng.properties");
        }

        textProp = new Properties();
        textProp.load(textReader);
        Properties result = textProp;
        return result;
    }
}

