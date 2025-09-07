import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * Cards in the Solitaire game
 * 
 * @author Aidan Wang
 * @version November 3, 2023
 */
public class Card
{
    int rank;
    String suit;
    boolean isFaceUp;

    /**
     * Constructs objects of class card
     * @param r the rank of this card
     * @param s the suit of this card
     */
    public Card(int r, String s)
    {
        rank = r;
        suit = s;
        isFaceUp = false;
    }
    
    /**
     * gets the rank of this card
     * @return the rank of this card
     */
    int getRank()
    {
        return rank;
    }

    /**
     * gets the suit of this card
     * @return the suit of this card
     */
    String getSuit()
    {
        return suit;
    }

    /**
     * checks if this card is red
     * @return True     if the suit is diamond or heart; otherwise,
     *         False
     */
    boolean isRed()	
    {
        return suit == "d" || suit == "h";
    }

    /**
     * checks if this card is face up
     * @return True     if the card is face up; otherwise,
     *         False
     */
    boolean isFaceUp()
    {
        return isFaceUp;
    }

    /**
     * turns the card up
     */
    void turnUp()
    {
        isFaceUp = true;
    }

    /**
     * turns the card down
     */
    void turnDown()
    {
        isFaceUp = false;
    }

    /**
     * gets the filename to use for this card
     * @return the filename for this card in string format
     */
    String getFileName()
    {
        if(!isFaceUp)
        {
            return "cards//back.gif";
        }
        String rnk = "";
        switch(rank){
            case 1:
                rnk = "a";
                break;
            case 2:
                rnk = "2";
                break;
            case 3:
                rnk = "3";
                break;
            case 4:
                rnk = "4";
                break;
            case 5:
                rnk = "5";
                break;
            case 6:
                rnk = "6";
                break;
            case 7:
                rnk = "7";
                break;
            case 8:
                rnk = "8";
                break;
            case 9:
                rnk = "9";
                break;
            case 10:
                rnk = "t";
                break;
            case 11:
                rnk = "j";
                break;
            case 12:
                rnk = "q";
                break;
            case 13:
                rnk = "k";
                break;
        }
        
        return "cards//" + rnk + suit + ".gif";
    }
}