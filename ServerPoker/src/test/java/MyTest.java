import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

 class MyTest {

	@Test
	void test() {
		// innitilaze 
		PokerInfo p = new PokerInfo(1); 
		gameLogic g = new gameLogic(); 
		// send server to client 
		System.out.println("game innitilaized");

		// set the ante and pair plus and pair plus boolean 
		p.setAnteWager(7);
		p.setPairPlusWager(17);
		p.setPairPlus(true);
		// send client to server 
		System.out.println("bets are played");
		System.out.println("the ante and pair plus are : "+ p.getAnteWager()+" "+ p.getPairPlus());

		// deal cards 
		p = g.drawCards(p);
		// send server to client
		System.out.println("cards are drawn");
		// System.out.println("the deck of cards are as follows :");
		// System.out.println(p.getDeckofCardString());
		System.out.println("the player cards are "+p.getPlayerCardsAString());
		System.out.println("the dealer cards are "+p.getDealerCardsAString());

		// set is folded or play 
		p.setFoldedByPlayer(false);
		p.setWager(p.getAnteWager());

		// calculate winnings
		p = g.getWinnings(p); 
		if (p.isFoldedByPlayer()){
			System.out.println("The losing are : "+p.getLosing());
		} else {
			if (p.isAnteWagePushedToNextRound()){
				System.out.println("ante wager returned");
			}else if (p.isPlayerWon()){
				System.out.println("the player winnings are : "+ p.getWinnings());
			} else {
				System.out.println("the player lost : "+ p.getLosing());
			}
			System.out.println("the pair plus prize are : "+p.getPairPlusWinnings());
		}

		

	

	}

}
