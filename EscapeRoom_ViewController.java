/*
 * Rebecca Sieber
 * CSC 240, Fall 2021
 * CONTROLLER FILE FOR SPLASH PAGE
 * This is the splash page where users are introduced
 * to the premise of the escape room. From this initial scene,
 * users can naviate to the main/home gameplay screen.
 */

package escaperoom;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
//import java.io.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.stage.Stage;
; 

/**
 * FXML Controller class
 *
 * @author rebec
 */

public class EscapeRoom_ViewController implements Initializable, 
        EventHandler<ActionEvent>{

    @FXML
    private Label lblSplash;
    @FXML
    private ImageView imgSplash;
    @FXML
    private Button btnSplash;
    @FXML
    private Label lblSplashStory;
    
    /**
     * Initializes the controller class.
 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*Formatting Title text, font, and color*/
        lblSplash.setText("Plane Wreck: Lost and Alone");
        lblSplash.setTextFill(Color.web("#FFFFFF", 0.8));
        lblSplash.setFont(Font.font("Chiller", FontWeight.BOLD, 34));
        
        /*Formatting Button text, font, and setting action event*/
        btnSplash.setText("PLAY");
        btnSplash.setFont(Font.font("Chiller", FontWeight.BOLD, 20));
        btnSplash.setOnAction(this);
        
        /*Formatting story text, font, color*/
        lblSplashStory.setText("You wake suddenly, lost, and disoriented.\nYou are the only survivor of a plane crash. \nYou must find a way out of the vast and \nunforgiving wilderness to safety. \nPlay if you dare.");
        lblSplashStory.setTextFill(Color.web("#FFFFFF", 0.8));
        lblSplashStory.setFont(Font.font("Bahnschrift Light SemiCondensed", FontWeight.BOLD, 12));
        
        /*Creating background image and setting it to jpeg file*/
        Image splashImage = new Image(getClass().getResourceAsStream("AbandondPlane.jpg"));
        imgSplash.setImage(splashImage);
       
    }    

    /*Handling event where button press enters user into the home gameplay screen*/
    @Override
    public void handle(ActionEvent event) {
        try{
            // Instantiate the FXMLLoader object and reference the view file
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("EscapeRoom_Home.fxml"));
            
            // Set the ViewController to the loader
            loader2.setController(new EscapeRoom_HomeController());
            
            // Using the loader, load the view into a Parent root container: SceneGraph
            Parent root = loader2.load();
            
            // Instantiate the Scene object and initialize it with the root container (SceneGraph)
            Scene scene = new Scene(root);
            
            // Set the scene on the stage
            EscapeRoom.mainStage.setScene(scene);
            //primaryStage.setScene(scene);

            // Set stage title
            //primaryStage.setTitle("Escape Room");
            
            // Show the stage
            EscapeRoom.mainStage.show();
            //primaryStage.show();
            
            } catch(Exception ex) {System.out.println("Error FXMLLoader2 - start()"); }
    
    }
    /*THESE VARIABLES keep track of when puzzles have been solved, and allow new
    puzzles to be unlocked if they are set to true*/
    public static boolean sudokuSolved;
    public static boolean pokerSolved;
       
}
