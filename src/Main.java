import logic.GameLogic;
import logic.OptionLogic;


public class Main {
    public static void main(String[] args) throws Exception {

        GameLogic gameLogic = new GameLogic();
        OptionLogic optionLogic = new OptionLogic();



        // Select options
        optionLogic.takeName();
        optionLogic.languageSelect();

        // Main game loop
        gameLogic.intro();
        gameLogic.printCurrentCarStats(gameLogic.pickCarFromGarage());

        // TODO: this particular method is old logic from my previous 'GT' project
        gameLogic.laps();

    }
}