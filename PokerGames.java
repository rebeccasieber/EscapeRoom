package escaperoom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 This class reads a file of 1000 sample poker games. Each game has two hands =
a user hand, a computer hand. It has a function to pull a random game from the 1000
games in the .txt file. It instaitates 2 PokerHand objects each consisting of 5 unique
Card objects.  When pulling from the .txt file it translates the 
.txt file card code of using 2 chars to denote each card (2H for 2 of hearts, TD for 
ten of diamonds, for example) into card objects that take 2 integers to denote the card.
 */

public class PokerGames {
    
    /*PRIVATE DATA MEMBERS*/
    public List<Card> userHand;
    public List<Card> computerHand;
    private List<String[]> allGames;
    public String[] randomGame;//= new String[] {"8C", "TS", "KC", "9H", "4S", "7D", "2S", "5D", "3S", "AC"};
    
/*Constructor- PokerGames is a dynamic list of possible games to play. It calls
    a function to read from a file*/ 
    public PokerGames() 
    {
        userHand = new ArrayList<Card>();
        computerHand = new ArrayList<Card>();
        allGames = new ArrayList<String[]>();
        randomGame = new String[10];
        readFile(); //this initializes allGames data member
        
    }

    /*This function reads a txt file of games and uploades them into the poker games
    object*/
   private void readFile()
    {
        File myFile = new File("poker.txt");
        FileReader fileReader;
        BufferedReader reader;
    
        try
        {
            fileReader = new FileReader(myFile);
            reader = new BufferedReader(fileReader);
            String line = "";
            
            line = reader.readLine();  //Reading first line
            String[] lineAsArray = new String[10];
            int index;
            
            while (line != null ) 
            {
                index = 0;
                for (String singleCard: line.split(" ")) 
                {
                   lineAsArray[index] = singleCard;
                   ++index;
                }
               //System.out.println(Arrays.toString(lineAsArray)); 
               //System.out.println();
               allGames.add(lineAsArray.clone()); 
               line = reader.readLine();
            }
                //for(String[] array : allGames) {
                //    System.out.println(Arrays.toString(array));
                //}
            reader.close();   
        } catch(IOException e) {System.out.println(e.getMessage() + "\tRead Error poker.txt\n");} 
    }
    //THIS IS USED TO LOAD ONE OF THE GAMES INTO
   
   public void pickRandomGame()
    {

        Random rand = new Random();
        Integer randInt = rand.nextInt(allGames.size());                     //random number between 0- # games in the arrayList
        //System.out.println("Random Integer: " + randInt);
        //System.out.println(Arrays.toString(allGames.get(randInt))); 
        randomGame = allGames.get(randInt).clone();                         // copying array from allGames at random index into random game list
        System.out.println(Arrays.toString(randomGame)); 
        //allGames.remove(randInt);                                          //removing this random game from gameplay so that it cannot be repeated 
    }
    
    /*This function creates the computer hand from the first 5 elements
    of the random game. NOTE that the txt file that random game gets uses
    string codes consisting of some characters T = 10, J = 11, Q = 12, K = 13
    and A = 15. However the card class uses the numberical digits only, so this
    functions convert the string/char into ints.*/
    public List<Card> getComputerHand()
    {
        //Card card0 = new Card(2,1);
        /*Pulling chars from the strings in the random game array, converting the chars to the
        corresponding integer required by the card object*/
        Card card1 = new Card(rankToInt(randomGame[0].charAt(0)), suitToInt(randomGame[0].charAt(1))); 
        Card card2 = new Card(rankToInt(randomGame[1].charAt(0)), suitToInt(randomGame[1].charAt(1)));
        Card card3 = new Card(rankToInt(randomGame[2].charAt(0)), suitToInt(randomGame[2].charAt(1)));
        Card card4 = new Card(rankToInt(randomGame[3].charAt(0)), suitToInt(randomGame[3].charAt(1)));
        Card card5 = new Card(rankToInt(randomGame[4].charAt(0)), suitToInt(randomGame[4].charAt(1)));

        /*Adding card objects from random game entries 0-4 into the computer's PokerHand object*/
        if(computerHand.isEmpty() == false)
            computerHand.clear();
            
        computerHand.add(0, card1);
        computerHand.add(1, card2);
        computerHand.add(2, card3);
        computerHand.add(3, card4);
        computerHand.add(4, card5);
        
        return computerHand;
    } 

    /*This function creates the user hand from the last 5 elements
    of the random game. NOTE that the txt file that random game gets uses
    string codes consisting of some characters T = 10, J = 11, Q = 12, K = 13
    and A = 15. However the card class uses the numberical digits only, so this
    functions convert the string/char into ints.*/    
    public List<Card> getUserHand()
    {
        /*Pulling chars from the strings in the random game array, converting the chars to the
        corresponding integer required by the card object*/
        Card card6 = new Card(rankToInt(randomGame[5].charAt(0)), suitToInt(randomGame[5].charAt(1)));
        Card card7 = new Card(rankToInt(randomGame[6].charAt(0)), suitToInt(randomGame[6].charAt(1)));
        Card card8 = new Card(rankToInt(randomGame[7].charAt(0)), suitToInt(randomGame[7].charAt(1))); 
        Card card9 = new Card(rankToInt(randomGame[8].charAt(0)), suitToInt(randomGame[8].charAt(1)));
        Card card10 = new Card(rankToInt(randomGame[9].charAt(0)), suitToInt(randomGame[9].charAt(1)));
        
        /*Adding card objects from random game entries 0-4 into the computer's PokerHand object*/
        
        if(userHand.isEmpty() == false)
            userHand.clear();
       
        userHand.add(0, card6);
        userHand.add(1, card7);        
        userHand.add(2, card8);
        userHand.add(3, card9);
        userHand.add(4, card10);
       
       List<Card> playerHand = new ArrayList<>(userHand); 
        return playerHand;
    }  
    
   /* public List<Card> resetUserHand()
    {
        userHand.remove(0);
        userHand.remove(1);
        userHand.remove(2);
        userHand.remove(3);
        userHand.remove(4);
        
        return userHand;
    }  */
    /*In the txt file, Clubs are 'C' but for a card they must be integer 1, 
    Diamonds ('D' = 2)*/
    private int suitToInt(char charSuit)
    {
        if (charSuit == 'C' || charSuit == 'c')
            return 1;
        else if (charSuit == 'D' || charSuit == 'd')
            return 2;
        else if (charSuit == 'H' || charSuit == 'h')
            return 3;
        else if (charSuit == 'S' || charSuit == 's')
            return 4;
        else 
        {
            System.out.println("Error- suitToInt functio");
            return -1;
        }  //signifies error
    }
    
    private int rankToInt(char charRank)
    {
        if (charRank == '2')
             return 2;
        else if(charRank == '3')
            return 3;
        else if(charRank == '4')
            return 4;
        else if(charRank == '5')
            return 5;
        else if(charRank == '6')
            return 6;
        else if(charRank == '7')
            return 7;
        else if(charRank == '8')
            return 8;
        else if(charRank == '9')
            return 9;
        else if(charRank == 'T')
            return 10;
        else if (charRank == 'J' || charRank == 'j')
            return 11;
        else if (charRank == 'Q' || charRank == 'q')
            return 12;
        else if (charRank == 'K' || charRank == 'k')
            return 13;
        else if (charRank == 'A' || charRank == 'a')
            return 14;
        else {
            System.out.println("Error- rankToInt functio");
            return -1; //signifies error
        }
    }
}