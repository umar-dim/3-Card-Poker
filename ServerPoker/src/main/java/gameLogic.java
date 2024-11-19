import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class gameLogic {
    
    // private String[] suits = {};
    // private String[] values = {};

    // private ArrayList<String> suits = new ArrayList<>(Arrays.asList("hearts", "diamonds", "clubs", "spades")); 
    //  ArrayList<String> suits = new ArrayList<>(Arrays.asList("hearts", "diamonds", "clubs", "spades")); 
    private ArrayList<String> values = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")); 
    //  ArrayList<String> values = new ArrayList<>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")); 
    private ArrayList<String> orderOfHands = new ArrayList<>(Arrays.asList("High Card", "Pair", "Flush", "Straight", "Three of a Kind", "Straight Flush")); 

    private class Hand {
        public String hand = "";
        // public String handSpecial = "" ; 
        public String highCardValue = "";  
        // public String highCardSuit = "";  
    }
    
    public PokerInfo drawCards(PokerInfo pokerInfo){
        Random rand = new Random(); 
        ArrayList<card> playerCards = pokerInfo.getPlayerCards(); 
        ArrayList<card> cards = pokerInfo.getCards(); 
        ArrayList<card> dealerCards = pokerInfo.getDealerCards(); 
        for (int i = 0 ; i < 3 ; i++ ){
            int index = rand.nextInt(cards.size()); 
            playerCards.add(cards.get(index));
            cards.remove(index);

            index = rand.nextInt(cards.size()); 
            dealerCards.add(cards.get(index));
            cards.remove(index);
            
        }

        pokerInfo.setCards(cards);
        pokerInfo.setPlayerCards(playerCards);
        pokerInfo.setDealerCards(dealerCards);
        return pokerInfo; 
    }

    public PokerInfo getWinnings (PokerInfo p){
        ArrayList<card> deaCards = p.getDealerCards(); 
        ArrayList<card> playerCards = p.getPlayerCards();

        Hand playerHand = getHand(playerCards);
        Hand dealerHand = getHand(deaCards);

        if (p.isFoldedByPlayer()){
            // the player folded and winngs is set to 0 the player losing is set 
            p.setWinnings(0);
            p.setLosing(p.getAnteWager()+p.getWager());
        } else {
            // the player did not fold 
            
            // calculate the pair plus regard less the player wins or loses 
            if (p.getPairPlus() != 0 ){  // Calculate pair plus 
                p.setPairPlusWinnings( getPairPlusWinnings(p, playerHand)); 
            }

            // calculate the winnings
            if (values.indexOf(dealerHand.highCardValue)>9 ){
                p.setDealerHasQueenHigh(true);
                // if the dealer has a queen 
                String outcome = betterHand(playerHand, dealerHand); 
                if(outcome == "player") {
                    // player has a better hand 
                    p.setWinnings(2*p.getWager() + 2*p.getAnteWager());
                    p.setPlayerWon(true);
                } else if (outcome == "dealer"){
                    // dealer has a better hand 
                    p.setPlayerWon(false);
                    p.setLosing(p.getAnteWager()+p.getWager());
                }
            } else {
                // the dealer dosent hava a queen high cards 
                p.setDealerHasQueenHigh(false);
                p.setAnteWagePushedToNextRound(true);
            }
        }
        return p ; 
    }

    private int getPairPlusWinnings(PokerInfo pokerInfo, gameLogic.Hand playerHand) {
        if (playerHand.hand == "Pair"){
            return 1*pokerInfo.getPairPlus() ;
        } else if (playerHand.hand == "Flush"){
            return 3*pokerInfo.getPairPlus() ;
        } else if (playerHand.hand == "Straight"){
            return 6*pokerInfo.getPairPlus() ;
        } else if (playerHand.hand == "Three of a Kind"){
            return 30*pokerInfo.getPairPlus() ;
        } else if (playerHand.hand == "Straight Flush"){
            return 40*pokerInfo.getPairPlus() ;
        }
        return 0;
    }

    private String betterHand (Hand player, Hand dealer) {
        // int O;
        if (orderOfHands.indexOf(player.hand) > orderOfHands.indexOf(dealer.hand)) {
            return "player"; 
        } else if (orderOfHands.indexOf(player.hand) == orderOfHands.indexOf(dealer.hand)){
            if (values.indexOf(player.highCardValue) > values.indexOf(dealer.highCardValue)){
                return "player";
            } else {
                return "dealer";
            } 
        }else {
            return "dealer";
        }
    }

    private gameLogic.Hand getHand(ArrayList<card> cards) {
        gameLogic.Hand hand ; 

        if (isStraightFlush(cards)){
            hand = getStraightFlush(cards) ; 
        } else if (isThreeOfaKind(cards)){
            hand = getThreeOfaKind(cards) ; 
        } else if (isStraight(cards)){
            hand = getStraight(cards) ;
        } else if (isFlush(cards)){
            hand = getFlush(cards) ; 
        } else if (isPair(cards)){
            hand = getPair(cards) ; 
        } else {
            hand = getHighCard(cards) ; 
        }
        
        return hand;
    }
    
    private gameLogic.Hand getHighCard(ArrayList<card> cards) {
        gameLogic.Hand hand = new gameLogic.Hand();
        hand.hand = "High Card";
        if (values.indexOf(cards.get(0).value) > values.indexOf(cards.get(1).value)){
            // true then 0 > 1 
            if (values.indexOf(cards.get(0).value) > values.indexOf(cards.get(2).value)){
                // true then 0 > 2 (0 > 2 > 1)
                // hand.handSpecial = cards.get(0).value ; 
                hand.highCardValue = cards.get(0).value ; 
                // hand.highCardSuit = cards.get(0).suit ; 
            } else {
                // false then 2 > 0 (2>0>1)
                // return cards.get(2).value ; 
                // hand.handSpecial = cards.get(2).value ; 
                hand.highCardValue = cards.get(2).value ; 
                // hand.highCardSuit = cards.get(2).suit ; 
            }
        }else {
            // false then 1 > 0 
            if (values.indexOf(cards.get(1).value) > values.indexOf(cards.get(2).value)){
                // true then 1 > 2  (1>2>0)
                // return cards.get(1).value ; 
                // hand.handSpecial = cards.get(1).value ; 
                hand.highCardValue = cards.get(1).value ; 
                // hand.highCardSuit = cards.get(1).suit ; 
            } else {
                // false then 2 > 1 (2>1>0)
                // return cards.get(2).value ; 
                // hand.handSpecial = cards.get(2).value ; 
                hand.highCardValue = cards.get(2).value ; 
                // hand.highCardSuit = cards.get(2).suit ; 
            }
        }
        return hand;
    }
    
    private String getHighCardValue(ArrayList<card> cards) {
        if (values.indexOf(cards.get(0).value) > values.indexOf(cards.get(1).value)){
            // true then 0 > 1 
            if (values.indexOf(cards.get(0).value) > values.indexOf(cards.get(2).value)){
                // true then 0 > 2 (0 > 2 > 1)
                return cards.get(0).value ; 
            } else {
                // false then 2 > 0 (2>0>1)
                return cards.get(2).value ; 
            }
        }else {
            // false then 1 > 0 
            if (values.indexOf(cards.get(1).value) > values.indexOf(cards.get(2).value)){
                // true then 1 > 2  (1>2>0)
                return cards.get(1).value ; 
            } else {
                // false then 2 > 1 (2>1>0)
                return cards.get(2).value ; 
            }
        }
        // return null;
    }
   
    // private String getHighCardSuit(ArrayList<card> cards){
    //     if (suits.indexOf(cards.get(0).suit) > suits.indexOf(cards.get(1).suit)){
    //         // true then 0 > 1 
    //         if (suits.indexOf(cards.get(0).suit) > suits.indexOf(cards.get(2).suit)){
    //             // true then 0 > 2 (0 > 2 > 1)
    //             return cards.get(0).suit ; 
    //         } else {
    //             // false then 2 > 0 (2>0>1)
    //             return cards.get(2).suit ; 
    //         }
    //     }else {
    //         // false then 1 > 0 
    //         if (suits.indexOf(cards.get(1).suit) > suits.indexOf(cards.get(2).suit)){
    //             // true then 1 > 2  (1>2>0)
    //             return cards.get(1).suit ; 
    //         } else {
    //             // false then 2 > 1 (2>1>0)
    //             return cards.get(2).suit ; 
    //         }
    //     }
    // }

    private gameLogic.Hand getPair(ArrayList<card> cards) {
        gameLogic.Hand hand = new Hand();
        hand.hand = "Pair";

        if (cards.get(0).value == cards.get(1).value){
            // hand.handSpecial = cards.get(0).value ;
            hand.highCardValue = cards.get(2).value;
            // hand.highCardSuit = cards.get(2).suit;
        }else if (cards.get(0).value == cards.get(2).value){
            // hand.handSpecial = cards.get(0).value ;
            hand.highCardValue = cards.get(1).value;
            // hand.highCardSuit = cards.get(1).suit;
        } else if (cards.get(1).value == cards.get(2).value){
            // hand.handSpecial = cards.get(0).value ;
            hand.highCardValue = cards.get(1).value;
            // hand.highCardSuit = cards.get(1).suit;
        }

        return hand;
    }

    private boolean isPair(ArrayList<card> cards) {
        if (cards.get(0).value == cards.get(1).value ||
            cards.get(0).value == cards.get(2).value ||
            cards.get(1).value == cards.get(2).value){
                return true ;
            }
        return false;
    }

    private gameLogic.Hand getFlush(ArrayList<card> cards) {
        gameLogic.Hand hand = new Hand();
        hand.hand = "Flush";
        // hand.handSpecial = cards.get(0).suit; 
        hand.highCardValue = getHighCardValue(cards); 
        // hand.highCardSuit = cards.get(0).suit; 
        return hand;
    }

    private boolean isFlush(ArrayList<card> cards) {
        if (cards.get(0).suit == cards.get(1).suit){
            if (cards.get(1).suit == cards.get(2).suit){
                return true ;
            }
        }
        return false;
    }

    private gameLogic.Hand getStraight(ArrayList<card> cards) {
        gameLogic.Hand hand = new Hand();
        hand.hand = "Straight";
        // hand.handSpecial = getHighCardValue(cards);
        hand.highCardValue = getHighCardValue(cards);
        // hand.highCardSuit = getHighCardSuit(cards) ; 
        
        return hand;
    }

    private boolean isStraight(ArrayList<card> cards) {
        ArrayList<Integer> val = new ArrayList<>(); 
        for (card c : cards){
            val.add(values.indexOf(c.value));
        }
        Collections.sort(val);   
        if (val.get(0) + 1 == val.get(1) && val.get(1) + 1 == val.get(2)){
            return true ; 
        }
        return false;
    }

    private gameLogic.Hand getThreeOfaKind(ArrayList<card> cards) {
        gameLogic.Hand hand = new Hand(); 
        hand.hand = "Three of a Kind";
        // hand.handSpecial = cards.get(0).value;
        hand.highCardValue = cards.get(0).value; 
        // hand.highCardSuit = getHighCardSuit(cards) ; 
        return hand;
    }

    private boolean isThreeOfaKind(ArrayList<card> cards) {
        if (cards.get(0).value == cards.get(1).value){
            if (cards.get(1).value == cards.get(2).value){
                return true ;
            }
        }
        return false;
    }

    private gameLogic.Hand getStraightFlush(ArrayList<card> cards) {
        gameLogic.Hand hand = new Hand();
        hand.hand = "Straight Flush";
        // hand.handSpecial = getHighCardValue(cards);
        hand.highCardValue = getHighCardValue(cards);
        // hand.highCardSuit = cards.get(0).suit;
        return hand;
    }

    private boolean isStraightFlush(ArrayList<card> cards) {
        
        if (isStraight(cards))
            if (isFlush(cards))
                return true ;
        
        return false;
    }

}
