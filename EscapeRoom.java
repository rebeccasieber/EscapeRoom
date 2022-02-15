/*
 * Rebecca Sieber
 * CSC 240, Fall 2021
 * MAIN JAVA FILE FOR PROJECT
 * This is the semester-long escape room project.
 * in this GUI program the user/player will solve puzzles to 
 * "escape" a plan wreck by navagating different FXML scenes
 */
package escaperoom;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.scene.Parent;

/**
 *
 * @author rebec
 */
public class EscapeRoom extends Application {
    public static Stage mainStage;
    
    @Override
    public void start(Stage primaryStage) {
        EscapeRoom.mainStage = primaryStage;
        try{
            // Instantiate the FXMLLoader object and reference the view file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EscapeRoom_View.fxml"));
            
            // Set the ViewController to the loader
            loader.setController(new EscapeRoom_ViewController());
            
            //FOR TESTING PURPOSES
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("EscapeRoom_Home.fxml"));
            //loader.setController(new EscapeRoom_HomeController());
            
            //FOR TESTING PURPOSES
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("PickTheLock.fxml"));
            //loader.setController(new PickTheLockController());
            
            //FOR TESTING PURPOSES
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("RestorePower.fxml"));
            //loader.setController(new RestorePowerController());
            
            //FOR TESTING PURPOSES
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("CallForHelp.fxml"));
            //loader.setController(new CallForHelpController());
            
            // Using the loader, load the view into a Parent root container: SceneGraph
            Parent root = loader.load();
            
            // Instantiate the Scene object and initialize it with the root container (SceneGraph)
            Scene scene = new Scene(root);
            
            // Set the scene on the stage
            primaryStage.setScene(scene);
            
            // Set stage title
            primaryStage.setTitle("Escape Room");
            
            // Show the stage
            primaryStage.show();
            } catch(Exception ex) {System.out.println("Error FXMLLoader - start()"); }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
