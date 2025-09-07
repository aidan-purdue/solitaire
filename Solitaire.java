import java.util.*;

/**
 * Plays the solitaire game
 * 
 * @author Aidan Wang
 * @version November 3, 2023
 */
public class Solitaire
{
	/**
	 * Oversees the solitaire game
	 * 
	 * @param args Information from the command line
	 */
	public static void main(String[] args)
	{
		new Solitaire();
	}

	private Stack<Card> stock;
	private Stack<Card> waste;
	private Stack<Card>[] foundations;
	private Stack<Card>[] piles;
	private SolitaireDisplay display;

	private final boolean DEBUG = true;

	/**
	 * Creates Solitaire games
	 */
	public Solitaire()
	{
		foundations = new Stack[4];
		piles = new Stack[7];
		stock = new Stack();
		waste = new Stack();

		//INSERT CODE HERE
		for(int i=0; i<4; i++)
		{
			foundations[i] = new Stack();
		}
		for(int i=0; i<7; i++)
		{
			piles[i] = new Stack();
		}
		createStock();
		/* 
		if (DEBUG) 
			specialDeal_1();
		else
		*/
			deal();

		display = new SolitaireDisplay(this);
	}

	/**
	 * gets the card on top of the stock
	 * @return the card on top of the stock, or null if the stock is empty
	 */
	public Card getStockCard()
	{
		if(stock.isEmpty())
		{
			return null;
		}
		return stock.peek();
	}

	/**
	 * gets the card on top of the waste 
	 * @return gets the card on top of the waste, or null if the waste is empty
	 */
	public Card getWasteCard()
	{
		if(waste.isEmpty())
		{
			return null;
		}
		return waste.peek();
	}

	/**
	 * Gets the top card of a foundation pile
	 * @precondition:  0 <= index < 4
	 * @postcondition: returns the card on top of the given
	 *               foundation, or null if the foundation
	 *               is empty
	 * @param index the index of the foundationn to get the card at
	 * @return the top card of the foundation at index
	 */
	public Card getFoundationCard(int index)
	{
		if(index < 0 || index >= 4)
		{
			throw new IndexOutOfBoundsException("eRrOr, InDeX oUt Of BoUnDs");
		}
		if(foundations[index].isEmpty())
		{
			return null;
		}
		return foundations[index].peek();
	}

	/**
	 * gets the pile at index
	 * @precondition:  0 <= index < 7
	 * @postcondition: returns a reference to the given pile
	 * @param index the index to get the pile at
	 * @return the pile at index
	 */
	public Stack<Card> getPile(int index)
	{
		if(index < 0 || index >= 7)
		{
			throw new IndexOutOfBoundsException("eRrOr, InDeX oUt Of BoUnDs");
		}
		return piles[index];
	}

	/**
	 * Called when the stock is clicked. 
	 * Deals 3 cards into waste if the stock isn't empty
	 * or resets the stock if it is empty by taking
	 * back all the cards in the waste pile
	 */
	public void stockClicked()
	{
		if(display.isWasteSelected() || display.isPileSelected())
		{
			return;
		}
		if(!stock.isEmpty())
		{
			dealThreeCards();
			return;
		}
		resetStock();
	}

	/**
	 * called when the waste is clicked
	 * selects the waste
	 */
	public void wasteClicked()
	{
		if(!waste.empty() && !display.isWasteSelected() && !display.isPileSelected())
		{
			display.selectWaste();
			return;
		}
		if(display.isWasteSelected())
		{
			display.unselect();
		}
		//IMPLEMENT ME
		System.out.println("waste clicked");
	}

	/**
	 * called when given foundation is clicked
	 * if a pile is already selected, it puts the top card 
	 * of the pile into the foundation pile if it can be added
	 * if the waste is already selected, the top card of the waste
	 * is added to the foundation pile if it can be added
	 * @precondition:  0 <= index < 4
	 * @param index the index of the foundation that was clicked
	 */
	public void foundationClicked(int index)
	{
		if(display.isPileSelected())
		{
			int pi = display.selectedPile();
			if(!piles[pi].isEmpty() && canAddToFoundation(piles[pi].peek(), index))
			{
				foundations[index].add(piles[pi].pop());
			}
			display.unselect();
			
		}
		else if(display.isWasteSelected())
		{
			if(!waste.isEmpty() && canAddToFoundation(waste.peek(), index))
			{
				foundations[index].add(waste.pop());
			}
			display.unselect();
			
		}
		else if(display.isFoundationSelected())
		{
			display.unselect();
			
		}
		else
		{
			display.selectFoundation(index);
		}
		//IMPLEMENT ME
		System.out.println("foundation #" + index + " clicked");
	}

	/**
	 * @precondition:  0 <= index < 7
	 * called when a pile is clicked
	 * if a pile is already selected, it puts the face up cards
	 * of that pile into this pile if they can be added
	 * if the waste is already selected, the top card of the waste
	 * is added to this pile if it can be added
	 * @param index the index of the clicked pile
	 */
	public void pileClicked(int index)
	{
		System.out.println("pile #" + index + " clicked");
		if(display.isPileSelected())
		{
			int pi = display.selectedPile();
			Stack <Card> stk = removeFaceUpCards(pi);
			if(stk.isEmpty())
			{
				display.unselect();
				return;
			}
			if(canAddToPile(stk.peek(), index) && pi!=index)
			{
				addToPile(stk, index);
			}
			else
			{
				addToPile(stk, pi);
			}
			display.unselect();
		}
		else if(display.isWasteSelected())
		{
			if(!waste.isEmpty() && canAddToPile(waste.peek(), index))
			{
				piles[index].push(waste.pop());
			}
			display.unselect();
		}
		else if(display.isFoundationSelected())
		{
			int foun = display.selectedFoundation();
			if(foundations[foun].isEmpty())
			{
				display.unselect();
				return;
			}
			Card crd = foundations[foun].peek();
			Stack<Card> stk = new Stack<Card>();
			stk.push(crd);
			if(canAddToPile(crd, index))
			{
				addToPile(stk, index);
				foundations[foun].pop();
			}
			display.unselect();
		}
		else
		{
			if(piles[index].isEmpty())
			{
				return;
			}
			if(!piles[index].peek().isFaceUp())
			{
				piles[index].peek().turnUp();
			}
			else{
				display.selectPile(index);
			}
		}
	}

	/**
	 * checks if a card can be added to a foundation pile
	 * @precondition:  0 <= index < 4
	 * @postcondition: Returns true if the given card can be
	 *                 legally moved to the top of the given
	 *                 foundation
	 * @param card the card to add
	 * @param index the foundation pile to check
	 * @return	true	 if the given card can be added to the 
	 * 			foundation pile at index; otherwise,
	 * 			false
	 */
	private boolean canAddToFoundation(Card card, int index)
	{
		if(foundations[index].isEmpty())
		{
			return card.getRank()==1;
		}
		Card c = foundations[index].peek();
		if(c.getRank() == card.getRank()-1)
		{
			return c.getSuit().equals(card.getSuit());
		}
		return false;
	}


	/**
	 * creates a stock with 52 cards with 4 different
	 * suits and 13 different ranks. the order
	 * of the cards is random
	 */
	public void createStock()
	{
		ArrayList<Card> arr = new ArrayList();
		for(int i=0; i<4; i++)
		{
			String suit = "";
			switch(i) {
				case 0:
					suit = "c";
					break;
				case 1:
					suit = "d";
					break;
				case 2:
					suit = "h";
					break;
				case 3:
					suit = "s";
					break;
			}
			for(int j=1; j<=13; j++)
			{
				arr.add(new Card(j, suit));
			}
		}
		while(!arr.isEmpty())
		{
			int num =(int)(Math.random()*arr.size());
			stock.push(arr.remove(num));
		}
	}

	/**
	 * deals cards from the stock into 
	 * the piles
	 */
	public void deal()
	{
		for(int i=0; i<7; i++)
		{
			for(int j=0; j<=i; j++)
			{
				piles[i].push(stock.pop());
			}
			piles[i].peek().turnUp();
		}
	}

	/**
	 * deals three cards from the stock into the waste
	 */
	public void dealThreeCards()
	{
		for(int i=0; i<3; i++)
		{
			if(stock.isEmpty())
			{
				return;
			}
			waste.push(stock.pop());
			waste.peek().turnUp();
		}
	}

	/**
	 * resets the stock by clearing the 
	 * waste pile
	 */
	public void resetStock()
	{
		while(!waste.isEmpty())
		{
			stock.push(waste.pop());
			stock.peek().turnDown();
		}
	}

	/**
	 * checks if a card can be added to a pile
	 * @precondition:  0 <= index < 7
	 * @postcondition: Returns true if the given card can be
	 *                 legally moved to the top of the given
	 *                 pile
	 * @param card the card to check
	 * @param index the index of the pile to check
	 * @return	true if the card can be added to
	 * 			the pile at the given index; otherwise,
	 * 			false
	 */
	private boolean canAddToPile(Card card, int index)
	{
		if(piles[index].isEmpty())
		{
			return card.getRank()==13;
		}
		Card c = piles[index].peek();
		if(c.getRank() == card.getRank()+1)
		{
			return c.isRed()!=card.isRed();
		}
		return false;
	}

	
	/**
	 * removes all the face up cards from the pile at a given index
	 * @precondition:  0 <= index < 7
	 * @postcondition: Removes all face-up cards on the top of
	 *                 the given pile; returns a stack
	 *                 containing these cards
	 * @param index	the index of the pile to remove the face up cards of
	 * @return	the removed face up cards in the form of a stack
	 */
	private Stack<Card> removeFaceUpCards(int index)
	{
		Stack<Card> stk = new Stack<Card>();
		while(!piles[index].isEmpty()&&piles[index].peek().isFaceUp())
		{
			stk.push(piles[index].pop());
		}
		return stk;
	}

	/**
	 * adds a stack of cards to a pile at a 
	 * given index
	 * @precondition:  0 <= index < 7
	 * @postcondition: Removes elements from cards, and adds
	 *                 them to the given pile.
	 * @param cards	the cards to add
	 * @param index	the index of the pile to add the cards to
	 */
	private void addToPile(Stack<Card> cards, int index)
	{
		while(!cards.isEmpty())
		{
			piles[index].push(cards.pop());
		}
	}
}