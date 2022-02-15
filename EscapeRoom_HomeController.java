/*
 * Rebecca Sieber
 * CSC 240, Fall 2021
 * CONTROLLER FILE FOR HOME/MAIN SCREEN
 * In this home/main scene the user can navagate through all
 * of the puzzles to play and finish the game
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.TextField;
import javafx.scene.image.Image;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.layout.Background;
//import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author rebec
 */
public class EscapeRoom_HomeController implements Initializable {

    @FXML
    private Label lblInstructions;
    @FXML
    private ImageView imgPickTheLock;
    @FXML
    private ImageView imgRestorePower;
    @FXML
    private ImageView imgCallForHelp;
    @FXML
    private Button btnPickTheLock;
    @FXML
    private Button btnRestorePower;
    @FXML
    private Button btnCallForHelp;
   // @FXML
   // private TextField restorePowerTxt; 
   // @FXML
    //private TextField callForHelpTxt;
     
    
   // public static boolean sudokuSolved;// = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    lblInstructions.setText(" To escape, you must call for help. The emergency phone is locked, and the plane’s  "
            + "\n battery is dead. You must first pick the lock to the phone, then restore power to the "
            + "\n plane, and then call for help.");
    lblInstructions.setFont(Font.font("Chiller", FontWeight.BOLD, 23));
    lblInstructions.setTextAlignment(TextAlignment.CENTER);
    
    btnPickTheLock.setText("PICK THE LOCK");
    btnPickTheLock.setFont(Font.font("Chiller", FontWeight.BOLD, 15));
    btnPickTheLock.setOnAction(event1);
    
    btnRestorePower.setText("RESTORE POWER");
    btnRestorePower.setFont(Font.font("Chiller", FontWeight.BOLD, 15));
    btnRestorePower.setOnAction(event2);
    btnRestorePower.setDisable(true);
    
    //Checking to see if sudoku puzzle has been solved yet, and if so, enabling button for poker puzzle
    if (EscapeRoom_ViewController.sudokuSolved == true)
        btnRestorePower.setDisable(false);
        
    btnCallForHelp.setText("CALL FOR HELP");     
    btnCallForHelp.setFont(Font.font("Chiller", FontWeight.BOLD, 15));
    btnCallForHelp.setOnAction(event3); 
    btnCallForHelp.setDisable(true);
    
    //Checking to see if poker puzzle has been solved yet, and if so, enabling finish button
    if (EscapeRoom_ViewController.pokerSolved == true)
        btnCallForHelp.setDisable(false);

    
    /*Text fields for the Restore Power room and Call for help room require
    passwords to enter- user obtains password by correctly solving previous puzzles
    setting these text fields to enable the corresponding button once the correct 
    password is entered. SudokuWinner21 */
    //restorePowerTxt.setOnKeyReleased(new passwordChecker1());
    //callForHelpTxt.setOnKeyReleased(new passwordChecker2());
    
    //SETTING IMAGES
    Image lockImage = new Image(getClass().getResourceAsStream("Lock.jpg"));
    imgPickTheLock.setImage(lockImage);
    
    Image lightBulbImage = new Image(getClass().getResourceAsStream("LightBulb.jpg"));
    imgRestorePower.setImage(lightBulbImage);
    
    Image phoneImage = new Image(getClass().getResourceAsStream("Phone.jpg"));
    imgCallForHelp.setImage(phoneImage);
    
    }    

    // Action Event- Handling for clicking the "PICK THE LOCK" button to get to the first puzzle.
    // The button takes you to a new scene
    EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            try{
                
                // Instantiate the FXMLLoader object and reference the view file
                FXMLLoader loader2 = new FXMLLoader(getClass().getResource("PickTheLock.fxml"));

                // Set the ViewController to the loader
                loader2.setController(new PickTheLockController());

                // Using the loader, load the view into a Parent root container: SceneGraph
                Parent root = loader2.load();

                // Instantiate the Scene object and initialize it with the root container (SceneGraph)
                Scene scene = new Scene(root);
                //stage.setTitle("Puzzle: ...");
                // Set the scene on the stage
                EscapeRoom.mainStage.setScene(scene);
                //primaryStage.setScene(scene);

                // Set stage title
                //primaryStage.setTitle("Escape Room");

                // Show the stage
                EscapeRoom.mainStage.show();
                //primaryStage.show();
            
            } catch(Exception ex) {System.out.println("Error FXMLLoader3 - pick the lock button"); }
            
            System.out.print("Pick the lock Button pushed");
            }
        };   

    // Action Event- Handling for clicking the "RESTORE POWER" button to get to the second puzzle.
    // The button takes you to a new scene
    EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            try{
                // Instantiate the FXMLLoader object and reference the view file
                FXMLLoader loader3 = new FXMLLoader(getClass().getResource("RestorePower.fxml"));

                // Set the ViewController to the loader
                loader3.setController(new RestorePowerController());

                // Using the loader, load the view into a Parent root container: SceneGraph
                Parent root = loader3.load();

                // Instantiate the Scene object and initialize it with the root container (SceneGraph)
                Scene scene = new Scene(root);

                // Set the scene on the stage
                EscapeRoom.mainStage.setScene(scene);
                //primaryStage.setScene(scene);

                // Set stage title
                //primaryStage.setTitle("Poker Puzzle");

                // Show the stage
                EscapeRoom.mainStage.show();
                //primaryStage.show();
            
            } catch(Exception ex) {System.out.println("Error FXMLLoader4& - start()"); }
                        
            System.out.print("Pick the power Button pushed");
            }
        };  
    
    // Action Event- Handling for clicking the "CALL FOR HELP" button to get to the Third puzzle.
    // The button takes you to a new scene
    EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            try{
                // Instantiate the FXMLLoader object and reference the view file
                FXMLLoader loader4 = new FXMLLoader(getClass().getResource("CallForHelp.fxml"));

                // Set the ViewController to the loader
                loader4.setController(new CallForHelpController());

                // Using the loader, load the view into a Parent root container: SceneGraph
                Parent root = loader4.load();

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
            
            } catch(Exception ex) {System.out.println("Error FXMLLoader4 - start()"); }
                        
            System.out.print("Call For Help Button pushed");
            }
        };  

/*
    private class passwordChecker1 implements EventHandler<KeyEvent> 

    {
        @Override
        public void handle(KeyEvent event) 
        {
            if (restorePowerTxt.getText().equals("SudokuMaster"))
                btnRestorePower.setDisable(false);   
        }
    }
    private class passwordChecker2 implements EventHandler<KeyEvent> 
    {
        @Override
        public void handle(KeyEvent event) 
        {
            if (callForHelpTxt.getText().equals("PokerChamp"))
                btnCallForHelp.setDisable(false); 
        }
    }
    
    public class enableButton
    {
        public void enableCallForHelp()
        {
            btnCallForHelp.setDisable(true); 
        }

    
    }*/
}