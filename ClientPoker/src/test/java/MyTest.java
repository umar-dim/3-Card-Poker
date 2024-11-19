import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {

	@Test
	void test() {
		PokerInfo p =  new PokerInfo();
		// gameLogic g = new gameLogic() ;
		
		
		System.out.println("the "+p.getCards().size()+" cards are :\n"+ p.getCardsString(p.getCards()));
		System.out.println();
		System.out.println("the player cards are :\n"+ p.getCardsString(p.getPlayerCards()));
		System.out.println();
		System.out.println("the dealer cards are :\n"+p.getCardsString(p.getDealerCards())); 
		// g.drawCards(p);

		System.out.println();
		System.out.println("the "+p.getCards().size()+" cards are :\n"+ p.getCardsString(p.getCards()));
		System.out.println();
		System.out.println("the player cards are :\n"+ p.getCardsString(p.getPlayerCards()));
		System.out.println();
		System.out.println("the dealer cards are :\n"+p.getCardsString(p.getDealerCards())); 
	}

	@Test
	void test2(){
		PokerInfo p =  new PokerInfo();
		// gameLogic g = new gameLogic() ;
		// g.drawCards(p);
		// System.out.println(g.suits);
		// System.out.println(g.values);
	}
	
}
