/*
 * Rebecca Sieber
 * CSC 240, Fall 2021
 * PUZZLE #3 - CALL FOR HELP
 * This is the final scene for the last puzzle- It declares
 * the user as a winner and prints rescue photo. It has a button
 * to exit the game and terminate the program.
 */

package escaperoom;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rebec
 */
public class CallForHelpController implements Initializable, EventHandler<ActionEvent>{

    @FXML
    private Label lblUnderConstruction;
    @FXML
    private Button btnGoHome;
    @FXML
    private ImageView imgPhone;
    @FXML
    private BorderPane scenePane;
    
    Stage stage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Formatting text for Title, button, and setting action for button click
        lblUnderConstruction.setText("Congratulations!\n You've been rescued! ");
        lblUnderConstruction.setFont(Font.font("Bahnschrift Light SemiCondensed", FontWeight.BOLD, 20));
        lblUnderConstruction.setTextAlignment(TextAlignment.CENTER);
        
        btnGoHome.setText("Exit");
        btnGoHome.setFont(Font.font("Chiller", FontWeight.BOLD, 25));
        btnGoHome.setOnAction(this);
        
        //Setting rescue image
        Image rescueImg = new Image(getClass().getResourceAsStream("rescue.jpg"));
        imgPhone.setImage(rescueImg);
    }   
    
    //This Action event for the button exits the game- terminates the program
    @Override
    public void handle(ActionEvent event) {
        try{
                stage = (Stage) scenePane.getScene().getWindow();
                System.out.println("You successfully logged out.");
                stage.close();
            
            } catch(Exception ex) {System.out.println("Error FXMLLoader5 - logout"); } 
    }     
}
