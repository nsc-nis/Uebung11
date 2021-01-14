package at.nsc.main;

import at.nsc.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

/**Car Database - Main
 * @author Niklas Schachl
 * @version 1.0, 7.1.2021
 */
public class Main extends Application
{
    public static void main (String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        MainController.show(stage);
    }
}
