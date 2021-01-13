package at.nsc.model;

import at.nsc.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**Car Database - Model
 * @author Niklas Schachl
 * @version 1.0, 7.1.2021
 */
public class CarDatabase
{
    protected HashMap db = new HashMap();
    private ArrayList<String> list = new ArrayList<String>();


    public CarDatabase()
    {
        generate();
    }

    //public Vehicle search(String licensePlate, boolean exact)
    public void search(String licensePlate, boolean exact)
    {
        boolean exactSearchSuccess = false;
        if(exact)
        {
            try
            {
                list.add(db.get(licensePlate).toString());
                exactSearchSuccess = true;
            }
            catch (RuntimeException exception)
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Not a valid License");
                alert.setContentText(String.format("Not a valid license plate!"));
                alert.showAndWait();
            }
        }
        else
        {
            Iterator<String> iterator = db.keySet().iterator();
            while(iterator.hasNext())
            {
                // current element
                String currentLicense = iterator.next();

                if (currentLicense.contains(licensePlate))
                {
                    list.add(db.get(currentLicense).toString());
                }
            }
        }

        if (!exactSearchSuccess)
        {
            for (int i = 0; i < list.size(); i++)
            {
                try
                {
                    if (list.get(i) == null)
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("No car found");
                        alert.setContentText(String.format("No car was found for your search query "));
                        alert.showAndWait();
                    }
                }
                catch (Exception exception)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("No car found");
                    alert.setContentText(String.format("No car was found for your search query"));
                    alert.showAndWait();
                }
            }
        }
    }

    public ObservableList<String> getObservableList() {
        return FXCollections.observableList(list);
    }

    public void clear(){list.clear();}

    private void generate()
    {
        MagicGenerator generator = new MagicGenerator();
        int counter = 0;
        while (counter < 20)
        {
            String licensePlate = generator.getRandomLicencePlate();
            String manufacturer = generator.getRandomManufacturer();
            db.put(licensePlate, new Vehicle(generator.getRandomColor(), generator.getRandomName(), manufacturer, generator.getRandomModel(manufacturer), licensePlate));
            System.out.printf("Generated: Vehicle with license %s%n", licensePlate);

            counter++;
        }
    }
}
