package at.nsc.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.*;

/**Car Database - Model
 * @author Niklas Schachl
 * @version 1.0, 7.1.2021
 */
public class CarDatabase
{
    protected HashMap<String, Vehicle> db = new HashMap();


    public CarDatabase()
    {
        generate();
    }

    //public Vehicle search(String licensePlate, boolean exact)
    public List<Vehicle> search(String licensePlate, boolean exact)
    {
        List<Vehicle> list = new LinkedList<Vehicle>();
        if(exact)
        {
            try
            {
                list.add(db.get(licensePlate));
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
                    list.add(db.get(currentLicense));
                }
            }
        }
        return list;
    }

    public ObservableList<Vehicle> getObservableList(List<Vehicle> vehicleList) {
        return FXCollections.observableList(vehicleList);
    }
    
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
