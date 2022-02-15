package escaperoom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rebec
 */
class Card implements Comparable<Card> 
{
  private int rank; // 2-10, 11 Jack, 12 Queen, 13 King, 14 Ace
  private int suit; // 1 Clubs, 2 Diamonds, 3 Hearts, 4 Spades
  
    public Card()
    {
        this(2,1); // 2 of Clubs set as default Card  
    }
  
  public Card(int initRank, int initSuit){
    rank = initRank;
    suit = initSuit;
  }
 
  /*This constructor takes a single string such as 4C and establishes rank/suit
  from the single string code. 4 would be the rank, and C would mean Clubs = 1
  The string must be 2 characters ONLY. The first char is 1-9 or T, J, Q, K, A and the
  second character must be C, D, H, or S.*/
  public Card(String rankSuitString){
      
    if (rankSuitString.charAt(0) == '1' || rankSuitString.charAt(0) == '2' ||
            rankSuitString.charAt(0) == '3' || rankSuitString.charAt(0) == '4' || 
            rankSuitString.charAt(0) == '5' || rankSuitString.charAt(0) == '6' ||
            rankSuitString.charAt(0) == '7' || rankSuitString.charAt(0) == '8' ||
            rankSuitString.charAt(0) == '9')
        rank = Character.getNumericValue(rankSuitString.charAt(0)); //direct conversion 1-9
    else if (rankSuitString.charAt(0) == 'T'|| rankSuitString.charAt(0) == 't')
        rank = 10;
    else if (rankSuitString.charAt(0) == 'J'|| rankSuitString.charAt(0) == 'j')
        rank = 11;
    else if (rankSuitString.charAt(0) == 'Q'|| rankSuitString.charAt(0) == 'q')
        rank = 12;
    else if (rankSuitString.charAt(0) == 'K'|| rankSuitString.charAt(0) == 'k')
        rank = 13;
    else if (rankSuitString.charAt(0) == 'A'|| rankSuitString.charAt(0) == 'a')
        rank = 14;
    else
        System.out.print("Error creating object. Input string did not comply with required structure.");
        //throw exception because the second char must be a 1, 2, 3, or 4. NOTHING ELSE
             
    /*ESTABLISING SUIT FROM STRING*/
    if (rankSuitString.charAt(1) == 'C' || rankSuitString.charAt(1) == 'c')
           suit =  1;
    else if (rankSuitString.charAt(1) == 'D' || rankSuitString.charAt(1) == 'd')
           suit =  2;
    else if (rankSuitString.charAt(1) == 'H' || rankSuitString.charAt(1) == 'h')
           suit =  3;
    else if (rankSuitString.charAt(1) == 'S' || rankSuitString.charAt(1) == 's')
           suit =  4;
    else 
        System.out.print("Error creating object. Input string did not comply with required structure.");
        //throw exception because the second char must be a 1, 2, 3, or 4. NOTHING ELSE
    //rank = initRank;
    //suit = initSuit;
  }
  
  // Getters
  public int getRank() { return rank; } // inline for 1 code instruction
  public int getSuit() { return suit; } // inline for 1 code instruction
  
  // Setters
  public void setRank(int initRank) { 
    if(initRank >= 2 && initRank <= 14)
      rank = initRank;
  }
  public void setSuit(int initSuit) {
    if(initSuit >= 1 && initSuit <= 4)
      suit = initSuit; 
  }
  public void setCard(int initRank, int initSuit){
    setRank(initRank);
    setSuit(initSuit);
  }
  
  public String showCard(){
    return rankToString() + " of " + suitToString();
  }
  public String rankToString(){
    switch(rank){
      case 1: case 14:
        return "Ace";
      case 13: 
        return "King";
      case 12:
        return "Queen";
      case 11:
        return "Jack";
      default: // 2-10
        return Integer.toString(rank);
    }
  }
  public String suitToString(){
    switch(suit){
      case 1:
        return "Clubs";
      case 2:
        return "Diamonds";
      case 3:
        return "Hearts";
      default: // 4
        return "Spades";
    }
  }

  @Override public int compareTo(Card card2) {
    if(this.rank == card2.rank)
      return 0;
    else if(this.rank > card2.rank)
      return 1;
    else
      return -1;
  }
  @Override public String toString() {
    return showCard(); 
  }

}
    
