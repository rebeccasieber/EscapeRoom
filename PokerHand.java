package escaperoom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
This class contains a list of card objects (see card class for more details). It also
has a hand value which is computed based on the ranks/suits of the cards in the hand. 
Finally it contains a string for the hand type
 */
class PokerHand implements Comparable<PokerHand>{
    
    /*PRIVATE DATA MEMBERS*/
    private List<Card> hand;                    //Collection of 5 Card Objects
    private int handValue;                      //Value based on hand type plus highcard value, or value of pair(s)
    private String handType;                    //"Royal Flush", "Straight Flush", "Four of a kind", etc    
    
    /*CONSTRUCTORS*/
    public PokerHand() //Default values
    {
        hand = new ArrayList<>();
        handValue = -1;
        handType = "NULL";
    }
 
    public PokerHand(List<Card> listOfFiveCards)
    {
        hand = new ArrayList<>(listOfFiveCards);
        //checkHand(); //calculates handValue and handType private variables
    }
    
    /*GETTERS*/
    public int getHandValue() { return handValue; } // inline for 1 code instruction
    public String getHandType() { return handType; } // inline for 1 code instruction
  
    public void printCards()
    {
        System.out.print(hand.toString());
    }
    
    /*SETTERS*/
    public void setCards(List<Card> listOfFiveCards)
    {
        hand = new ArrayList<>(listOfFiveCards);
        checkHand(); //calculates handValue and handType private variables
    }
   
   
    /*PUBLIC MEMBER FUNCTIONS*/

    /*This function allows two poker hand objects to be compared to each
    other and determines which hand "wins" nased on the hand value descrined
    in the checkHand() function. THIS HAND COMPUTES THE handValue, and handType*/
   
   @Override 
    public int compareTo(PokerHand player2)
    {
        if(this.handValue == player2.handValue)
            return 0;
        else if (this.handValue > player2.handValue)
            return 1;
        return -1;
    }

    /*This function checks the hand to determine if it is a royal flush, a straight flush,
    four of a kind, full house, a flush, a straight, three of a kind, two pair, one pair,
    or simply high card. It then calculates the point value of the hand*/
    public void checkHand()
    {
        Collections.sort(hand);   //Cards arranged small to big by rank
        
        /*Checking for Royal Flush by calling straight, and flush() and highCard rank: Ace*/
        if(straight() && flush() && hand.get(4).getRank() == 14) 
        {
            handValue = 1000;
            handType = "Royal Flush";
        }
        else if(straight() && flush())                  //check for straight flush
        {
            handValue = 900 + hand.get(4).getRank();
            handType = "Straight Flush";
        }
        else if (fourOfKind())                          //checking for for of a kind
        {
            handValue = 800 + hand.get(4).getRank();
            handType = "Four of a Kind";
        }
        else if (fullHouse())                           //checking for full house
        {    
            handValue = 700 + hand.get(2).getRank();
            handType = "Full House";
        }
        else if (flush())                               //checking for flush house
        {
            handValue = 600 + hand.get(4).getRank();
            handType = "Flush";
        }
        else if (straight())                            //checking for straight 
        {
            handValue = 500 + hand.get(4).getRank();
            handType = "Straight";
        }
        else if (threeOfKind())                         //checking for three of a kind
        {
            handValue = 400 + hand.get(2).getRank();
            handType = "Three of a Kind";
        }
        else if (twoPair())                             //checking for two pair
        {
            handValue = 300 + hand.get(1).getRank() + hand.get(3).getRank();
            handType = "Two Pair";
        }
        else if (pair())                                //checking for pair
        {
            handValue = 200 + pairValue(); 
            handType = "Pair";
        }
        else                                            //default set to high card
        {
            handValue = 100 + hand.get(4).getRank();
            handType = "High Card";
        }
    }

    /*PRIVATE MEMBER FUNCTIONS*/
    
    /*Determines if hand is a straight- all 5 cards have consecutive rank*/
    private boolean straight()
    {
        boolean straightHand = true;
        Collections.sort(hand);
        for(int i = 0; i < hand.size()-1; i++)
        {
            if(hand.get(i).getRank()+ 1 != hand.get(i + 1).getRank())
            {
                straightHand = false;
                break;
            }
        }
        return straightHand;
    }
    
    /*A full house when sorted can present in one of 2 ways: AABBB or AAABB. Checking for both
    ways below.*/
    private boolean fullHouse()
    {
       boolean isFullHouse = false;
       
       //AABBB
       if(hand.get(0).getRank() == hand.get(1).getRank() &&
          hand.get(2).getRank() == hand.get(3).getRank() && 
          hand.get(3).getRank() == hand.get(4).getRank())
           isFullHouse = true;
       
       //AAABB
       else if(hand.get(0).getRank() == hand.get(1).getRank() &&
          hand.get(1).getRank() == hand.get(2).getRank() && 
          hand.get(3).getRank() == hand.get(4).getRank())
          isFullHouse = true;
       
       return isFullHouse;
    }
    
    //Checking flush = all cards have the same suit
    private boolean flush()
    {
        boolean sameSuit = true;
        int suitToCheck = hand.get(0).getSuit();
        for(int i = 1; i < hand.size(); i++)
        {
            if(hand.get(i).getSuit() != suitToCheck)
            {
                sameSuit = false;
                break;
            }
        }
    return sameSuit;
    }
    
    //Checking to see if 4 of the cards all have the same rank
    private boolean fourOfKind()
    {
        boolean fourSameRank = false;
        int matchCounter = 0;
        
        for(int i = 0; i < hand.size(); i++)
        {
            matchCounter = 0; //resetting match counter for next check
            for(int j = 0; j < hand.size(); j++)
            {
                if (hand.get(i).getRank() == hand.get(j).getRank())
                    ++matchCounter;
            }
                                                
            if (matchCounter == 4)
            {
                fourSameRank = true;
                break; // four of a kind found
            }
        }      
        return fourSameRank; 
    }
    
    /* Checking for three of a kind.
    Since the hand is sorted, a three of a kind can only present in 3 ways
    AAABC, BAAAC, or BCAAA. Becuse 5 of a kind and full house are checked before
    three of a kind, we dont need to worry about distinguishing them.*/
    private boolean threeOfKind()
    {
       boolean threeSameRank = false;
       
         //AAABC
        if(hand.get(0).getRank() == hand.get(1).getRank() &&
           hand.get(1).getRank() == hand.get(2).getRank())
          
            threeSameRank = true;
        
        //BAAAC
        else if(hand.get(1).getRank() == hand.get(2).getRank() &&
           hand.get(2).getRank() ==hand.get(3).getRank())
          
            threeSameRank = true;
        
        //BCAAA
        else if(hand.get(2).getRank() == hand.get(3).getRank() &&
           hand.get(3).getRank() ==hand.get(4).getRank())
          
            threeSameRank = true;
        
        return threeSameRank;
    
    }
   
    /*Since cards are sorted, a two pair can present in 3 and only three ways:
     AABCC, BAACC, or AABBC. Checking each of the three ways. Since three of a kind
     and four of a kind are higher point values than 2 pair, and since they gets 
     checked first, we do not need to worry about eliminating those as options*/
     
    private boolean twoPair()
    {
        boolean twoSetPair = false;
        
        //AABCC 
        if(hand.get(0).getRank() == hand.get(1).getRank() && 
           hand.get(3).getRank() == hand.get(4).getRank())
            twoSetPair = true;

        //BAACC
        else if(hand.get(1).getRank() == hand.get(2).getRank() && 
                hand.get(3).getRank() == hand.get(4).getRank())
            twoSetPair = true;
        //AACCB
        else if(hand.get(0).getRank() == hand.get(1).getRank() && 
                hand.get(2).getRank() == hand.get(3).getRank())
            twoSetPair = true;
                    
        return twoSetPair;
    }
    
    /*Since nearly all other possible hands are checked first, we do not 
    need to worry about eliminating a more valuable hand, and only need
    to confirm that a pair exists in the hand*/
    private boolean pair()
    {
        boolean twoSameRank = true;
        for(int i = 0; i < hand.size(); i++)
        {
            for(int j = i+1; j < hand.size(); j++)
            {
                if (hand.get(i).getRank() == hand.get(j).getRank())
                    return twoSameRank;
            }
        }    
        twoSameRank = false;
        return twoSameRank;
    }
    
    /*If a poker hand is determined to be a pair, then this function
    gets called to find the value of the pair so that it can be used to compute
    the value of the hand.*/
    private int pairValue()
    {
        for(int i = 0; i < hand.size(); i++)
        {
            for(int j = i+1; j < hand.size(); j++)
            {
                if (hand.get(i).getRank() == hand.get(j).getRank())
                    return hand.get(i).getRank();
            }
        } 
        return 0;
    }
}