package at.nsc.controller;

import at.nsc.model.CarDatabase;
import at.nsc.model.Vehicle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**Car Database - Controller
 * @author Niklas Schachl
 * @version 1.0, 7.1.2021
 */
public class MainController implements Initializable
{
    private Stage stage;
    private CarDatabase database = new CarDatabase();

    @FXML
    private ListView<Vehicle> listView_signs;

    @FXML
    private TextField textField_input;

    public static void show(Stage stage)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/at/nsc/view/mainView.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            MainController ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.getIcons().add(new Image("/at/nsc/images/icon.png"));
            stage.setTitle("Car Database");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Internal Error");
            alert.setContentText(String.format("An internal Error occurred. Please restart the program%nor contact the developer on GitHub%n%nError message: %s", exception.getMessage()));
            alert.showAndWait();
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    @FXML
    private void action_search()
    {
       displayResult(false);
    }

    @FXML
    private void action_exact()
    {
        displayResult(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //listView_signs.setItems(null);
    }

    private void displayResult(boolean exact)
    {
        listView_signs.setItems(database.getObservableList(database.search(textField_input.getText(), exact)));
    }
}
