/*
 * Rebecca Sieber
 * CSC 240, Fall 2021
 * Due 10/31/2021
 * PUZZLE #1 - SUDOKU 
 * This is the scene for the very first puzzle- sudoku. After
 * solving the puzzle, users will be given a code to use to be able
 * to enter the scene for the second puzzle.
 * 
 */

package escaperoom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.control.Separator;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;


/**
 * FXML Controller class
 *
 * @author rebec
 */
public class PickTheLockController implements Initializable{

    @FXML
    private Label lblUnderConstruction1;
    @FXML
    private Button btnGoHome;
    @FXML
    private ImageView imgPickTheLock;
    @FXML
    private GridPane sudokuGrid;
    @FXML
    private Button btnCheckSudoku;
    @FXML
    private Label lblwinOrLose;
    @FXML
    private Separator seperator1;
    @FXML
    private Separator seperator2;
    @FXML
    private Separator seperator3;
    @FXML
    private Separator seperator4;
    
    private Label[] hardCodedNumber;            //ARRAY OF NUMBERS 0-9 These are the numbers already given to the player
    private TextField[] numberToBeEntered;      //Array of TextFields. These are where the player inputs their own numbers

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*Setting up gridpane with thick black boarder*/
        sudokuGrid.setStyle("-fx-border-style: solid inside;" + 
                      "-fx-border-color: black;" +
                      "-fx-border-width: 5px");
        
        /*Setting up separators that create boundaries for the 9 inner squares*/
        seperator1.setStyle("-fx-background-color: black;" + 
                      "-fx-fill-color: black;" +
                      "-fx-border-style: solid");
        seperator2.setStyle("-fx-background-color: black;" + 
                      "-fx-fill-color: black;" +
                      "-fx-border-style: solid");
        seperator3.setStyle("-fx-background-color: black;" + 
                      "-fx-fill-color: black;" +
                      "-fx-border-style: solid");
        seperator4.setStyle("-fx-background-color: black;" + 
                      "-fx-fill-color: black;" +
                      "-fx-border-style: solid");
        
        /*Setting puzzle title and changing to a "spooky" font*/
        lblUnderConstruction1.setText("     Pick The Lock: SuDoku Puzzle!");
        lblUnderConstruction1.setFont(Font.font("Chiller", FontWeight.BOLD, 35));
        
        
        /*Formatting GO HOME button text and setting up action to take user back a level*/
        btnGoHome.setText("GO BACK");
        btnGoHome.setFont(Font.font("Chiller", FontWeight.BOLD, 15));
        btnGoHome.setOnAction(event1);
        
        /*Formatting CHECK SOLUTION button text and setting up action*/
        btnCheckSudoku.setText("CHECK SOLUTION");
        btnCheckSudoku.setFont(Font.font("Chiller", FontWeight.BOLD, 15));
        btnCheckSudoku.setWrapText(true);
        btnCheckSudoku.setTextAlignment(TextAlignment.CENTER);
        btnCheckSudoku.setOnAction(event2);
        
        /*This is the lable to state if user has solved the puzzle correctly
        or not. Starting with blank lable until user hits the "check solution"
        button, then it will change to either "You win" or "You lose" based on their sudoku board*/
        lblwinOrLose.setText("");
        lblwinOrLose.setWrapText(true);
        lblwinOrLose.setFont(Font.font("Chiller", FontWeight.BOLD, 20));
        lblwinOrLose.setTextAlignment(TextAlignment.CENTER);
        
        /*Setting up and instantiating lock image*/
        Image lockImage = new Image(getClass().getResourceAsStream("Lock.jpg"));
        imgPickTheLock.setImage(lockImage);
    
        /*Creating arrays to hold numbers provided to user, and text fields for player
        to enter numbers into. They both have 81 slots, but which slots actually get filled
        is up the random sudoku board that gets pulled from the txt file. Some slots in both
        arrays will be empty depending on the board*/
        hardCodedNumber = new Label[81];
        numberToBeEntered = new TextField[81];
        
        /*CREATING SUDOKU BOARD*/
        Integer[] sudokuArray = readRandomSudokuGrid(); //Opening external file, and picking random board from the file
        
        int row = 0;        //Used to loop through a series of numbers that have their own unique row/column combination
        int column = 0;     //Used to loop through a series of numbers that have their own unique row/column combination
        
        /*Looping through the randomly chosed sudoku board and determining which elements should be hard coded numbers
        and which should be text fields, and estabilishing those values in the corresponding arrays*/
        for (int i = 0; i < sudokuArray.length; i++)
        {
            if (sudokuArray[i] == 0)                                                //In the txt file "0" signifies that it should be a text field for the player to enter a number in
            {
                /*creating txt field, and add textfield to gridpane*/
                numberToBeEntered[i]  = new TextField();
                numberToBeEntered[i].setAlignment(Pos.CENTER);
                numberToBeEntered[i].setMaxWidth(25);
                numberToBeEntered[i].setOnKeyReleased(new UserInputValidation()); //Checking user input to see if it is digit 1-9, if not, turning text field red
                
                /*Adding and alligning textfield to gridpane*/
                sudokuGrid.add(numberToBeEntered[i], row, column);                
                sudokuGrid.setHalignment(numberToBeEntered[i], HPos.CENTER); 
            }
            else 
            {
                /*Number given by .txt should be hardcoded into the gridpane and NOT be a text*/
                hardCodedNumber[i]  = new Label(Integer.toString(sudokuArray[i]));
                sudokuGrid.add(hardCodedNumber[i], row, column);
                sudokuGrid.setHalignment(hardCodedNumber[i], HPos.CENTER); //Centering Label in the middle of the gridpane slot
            } 
           
            /*INCRIMENT ROw/COLUM COUNTERS*/    
            if (row <8)
                row++;
            else 
            {
                row = 0;
                column++;
            }
        }
    }    
    
    /*This Action event takes the user back to the home/main screen when they Press the "GO BACK" button*/
     EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
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

                } catch(Exception ex) {System.out.println("Error Pick Lock Home Button"); } 
        }
    };   
     
    /*THIS ACTION EVENT IS FOR THE "CHECK SOLUTION BUTTON". It checks the sudoku board by making
     sure that no row, no column, and no square has a duplicate number*/
    EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            int sudokuSolution[] = new int[81];         //Holds potebtial solution created by player to sudoku board
            
            /*Uploading values from 2 arrays (hard coded number and text field numbers) into one array. 
            Changing inputted strings into integers for easier comparison*/
            try{   
                for(int i = 0; i <81; i++)
                {
                    if (numberToBeEntered[i] == null)
                        sudokuSolution[i] = Integer.parseInt(hardCodedNumber[i].getText());
                    else
                        sudokuSolution[i] = Integer.parseInt(numberToBeEntered[i].getText());
                }
                
            /*Using function defined later/below to check for duplicates in all rows, columns, and squares*/
            if (checkSudokuBoard(sudokuSolution) == false)
            {
                lblwinOrLose.setTextFill(Color.web("#FF0000", 0.8));                //changing text of lose message to red
                lblwinOrLose.setText("Not quite. \nKeep Trying.");
                //EscapeRoom_ViewController.sudokuSolved = true; FOR TESTING PURPOSES
            }
            else
            {
                lblwinOrLose.setTextFill(Color.web("#028A0f", 0.8));                //Changing text of win message to green
                lblwinOrLose.setText("YOU WIN! \n Go Back and restore the power.");
                
                /*SETTING THIS TRUE ALLOWS NEXT EXSCAPE ROOM TO BE UNLOCKED WHEN USER RETURNS HOME*/
                EscapeRoom_ViewController.sudokuSolved = true;
            }
            /*If user does not fill in a square, or fills in which somthing incorrect, exception will be caught and game wii
            tell user that they made an error*/
            } catch(Exception ex) {System.out.println("Error Check Sudoku Button"); lblwinOrLose.setText("Error- Check board. Make sure each square has a single number 1-9.");} 
        }
    };  

/*This function reads from a provided .txt file containing 50 sudoku grids. In the text file "0" denotes a text field
    where the user/player is supposed to enter 1-9 to solve the sudoku board. Out of the 50 boards in the .txt file, this function
    choses one at random to play.*/
    
public static Integer[] readRandomSudokuGrid()
    {
        ArrayList<Integer[]> sudokuGrids = new ArrayList<Integer[]>(); //This holds holds 50 arrays of 81 integers each
            
        File myFile = new File("p096_sudoku.txt");
        FileReader fileReader;
        BufferedReader reader;
    
        try
        {
            fileReader = new FileReader(myFile);
            reader = new BufferedReader(fileReader);
            String line = "";
            
            line = reader.readLine();  
            
            while (line != null ) 
            {
                
                Integer[] sudokuGridArray = new Integer[81];                    //creating new array to hold one sudoku grid from .txt file
                int counter = 0;                                                //controls index of arrays containted in the arrayList. Goes up to 81
                                                 //Gettting first line AND IGNORING IT
                                    
                for(int i = 0; i < 9; i++)                                      //there are 9 lines (with 9 number each) per sudoku board. 
                {
                    line = reader.readLine();                                   //getting next line

                    for (String oneChar: line.split(""))                        //line is 9 numbers with no delimiter, splitting into 9 separate strings
                    {
                        sudokuGridArray[counter] = Integer.parseInt(oneChar);   //adding each separated string (converted to an integer) to board array
                        counter++;                                              //increasing counter it goes from 0-81
                    }
                }
                //System.out.println(Arrays.toString(sudokuGridArray));         //print sudoku board array FOR TESTING PURPOSES ONLY
                
                sudokuGrids.add(sudokuGridArray);                               //adding integer array to arrayList.
                line = reader.readLine();  
            }
            
            reader.close();      
        
        } catch(IOException e) {System.out.println(e.getMessage() + "\tRead Error\n");} 
        //catch(NullPointerException e) {System.out.print("NullPointerException caught");}
        
        Random rand = new Random();
        Integer randInt = rand.nextInt(49);                     //random number between 0-49 since there are 50 boards in the arrayList

        return sudokuGrids.get(randInt);
    }

/*This function validates user input by highlighting a text field red if anything BUT a single digit 1-9 is entered.
if incorrect input is recieved the text field will highlight red until the incorrect input is fully deleted, or the
correct input is provided. It does NOT check to see if the digit 1-9 is correct for solving the board*/
private class UserInputValidation implements EventHandler<KeyEvent> 
    {
        @Override
        public void handle(KeyEvent event) 
        {
            /*FIGURING OUT WHICH TEXT FIELD THE KEY EVENT OCCURED*/
            int index = -1;
            for (int i = 0; i < 81; i++)
            {
                if (event.getSource() == numberToBeEntered[i])
                {
                    index = i;
                    break;
                }
            }

            /* Checking to see if multiple characters have been entered, and highlighting text field red if so*/
            if (numberToBeEntered[index].getText().length() > 1) 
            {
                numberToBeEntered[index].setStyle("-fx-background-color: #ff0000");
            }
 
            /*If user hits delete key, chekcing text field to see if the input is correct (digit 1-9)
            and text field back to white. The user will use a delete key to fix any errors taht they make. 
            This code insures that the text field doesnt stay red forever after an incorrect input*/
            else if (event.getCode() == KeyCode.BACK_SPACE)
            {
                if (numberToBeEntered[index].getText().equals("1") == true || numberToBeEntered[index].getText().equals("2") == true|| 
                    numberToBeEntered[index].getText().equals("3") == true || numberToBeEntered[index].getText().equals("4") == true|| 
                    numberToBeEntered[index].getText().equals("5") == true || numberToBeEntered[index].getText().equals("6") == true|| 
                    numberToBeEntered[index].getText().equals("7") == true || numberToBeEntered[index].getText().equals("8") == true||
                    numberToBeEntered[index].getText().equals("8") == true)
                        numberToBeEntered[index].setStyle("-fx-background-color: #ffffff");
                
                /*Making sure that an empty text field is ALWAYS WHITE*/
                else if (numberToBeEntered[index].getText().isEmpty() == true) 
                {
                    numberToBeEntered[index].setStyle("-fx-background-color: #ffffff");
                }
            }

            /**Turning background color white if correct input is provided. The correct input
            is a SINGLE digit 1-9. If input was originally incorrect, turning
            the text field red, this will cause the text field to go back to white.*/
            else if(event.getCode() == KeyCode.DIGIT1 || event.getCode() == KeyCode.DIGIT2 || event.getCode() == KeyCode.DIGIT3 ||
                    event.getCode() == KeyCode.DIGIT4 || event.getCode() == KeyCode.DIGIT5 || event.getCode() == KeyCode.DIGIT6 ||
                    event.getCode() == KeyCode.DIGIT7 || event.getCode() == KeyCode.DIGIT8 || event.getCode() == KeyCode.DIGIT9 )
                    //||event.getCode() == KeyCode.BACK_SPACE)
                {
                    //this.setStyle("-fx-background-color: #ffffff");
                    //numberToBeEntered[index].setStyle("-fx-background-color: #ffffff"); //Changing back to white backround incase it was red at some point
                }
            
            else
                numberToBeEntered[index].setStyle("-fx-background-color: #ff0000");  //Changing background to red to signify error
        }
    }

    /*This function checks each sudoku board row, each sudoku board column, and each sudoku board square
    to see if there are any duplicats in them. It will return false if a dulplicate is found, and true if
    a duplicate is NOT found*/
   static boolean checkSudokuBoard(final int[] someSudokuGridArray)
    {
        /*Checking to make sure all numbers in the board are 1-9. If user enters something other
        than a number, and error will be triggered before this function gets called, however if they enter
        0 or a number greater than 9 this function can still be called. Technically if the user enters 0 or
        10+ the input validation function will cause the text field to turn red, but the text field turning
        red does not prevent them from hitting "check board". This prevents a user from cheating and putting
        numbers other than 1-9 and still winning*/
        for (int i = 0; i < 81; i++)
        {
            if (someSudokuGridArray[i] == 0 || someSudokuGridArray[i] > 9)
                return false;
        }
        
        /*Now that it is confirmed that all the player's entries were numbers 1-9. Checking board*/
        int row[] = new int[9];             //holds a single row in the sudoku board to check for duplicates
        int column[] = new int[9];          //holds a single column in the sudoku board to check for duplicates
        int square[] = new int[9];          //holds a single square in the sudoku board to check for duplicates

        /*Checking each row for duplicates. There are 9 rows of 9 numbers each*/
        for (int i = 0; i < 9; i++ )
        {
            for (int j = 0 ; j < 9; j++)
            {
                row[j] = someSudokuGridArray[9*i+j];
            }
            
            if (checkForDuplicates(row) == true) //duplicate found
                return false;
        }
          
        /*Checking each column for duplicates*/
        for (int i = 0; i < 9; i++ )
        {
            for (int j = 0 ; j < 9; j++)
            {
                column[j] = someSudokuGridArray[j*9+i];
            }

            if (checkForDuplicates(column) == true) //duplicate found
                return false;
        }
        
        /*Checking squares for duplicates*/
        int[] incrimentIntervals = {0,3,6,27,30,33,54,57,60};
        for (int i = 0; i < 9; i++ )
        {

            for (int j = 0 ; j < 3; j++)
            {
                square[j] = someSudokuGridArray[incrimentIntervals[i] + j];
            }
            
            for (int j = 0 ; j < 3; j++)
            {
                square[j+3] = someSudokuGridArray[incrimentIntervals[i] + j + 9];
            }
            for (int j = 0 ; j < 3; j++)
            {
                square[j+6] = someSudokuGridArray[incrimentIntervals[i] + j + 18];
            }
            
            if (checkForDuplicates(square) == true) //duplicate found
                return false;
        }      
        return true; //No duplicats found in any row, nor any column, nor any square
    }
    
    /*This function checks an integer array to see if any duplicates exist, returning true
    if there are duplicates and false if there are no duplicates*/
    static boolean checkForDuplicates(final int[] someArray)
    {
        // for every array element, check if it is found afterward in the array
        for (int i = 0; i < someArray.length; i++)
        {
            for (int j = i + 1; j < someArray.length; j++)
            {
                if (someArray[i] == (someArray[j])) 
                    return true;        //duplicate found
            }
        }
        return false;                   // no duplicate is found
    }

}