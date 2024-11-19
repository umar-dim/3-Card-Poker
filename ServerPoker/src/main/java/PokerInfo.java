import java.io.Serializable;
import java.util.ArrayList;

public class PokerInfo implements  Serializable {

    private String Status = ""; 
    private ArrayList<card> cards ; 
    private ArrayList<card> playerCards ; 
    private ArrayList<card> dealerCards ;
    private int playerPlayout = 0 ; 
    private int pairPlus = 0 ; 
    private int wager = 0 ; 
    private int anteWager = 0 ; 
    private int payout = 0 ; 
    private int winnings = 0 ;
    private int losing = 0 ; 
    private int pairPlusWinnings = 0 ; 
    private boolean isAnteWagePushedToNextRound = false ; 
    private boolean foldedByPlayer = false ; 
    private boolean isPairPlusBetMade = false ; 
    private boolean showDealerCards = false ; 
    private boolean isPlayerWon = false ;
    private int clientCount = 0 ;
    private boolean dealerHasQueenHigh = false ; 
    
    public int getClientCount() {
        return clientCount;
    }
    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }
    private void buildDeck() {
        String[] suits = {"hearts", "diamonds", "clubs", "spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (String suit : suits) {
            for (String value : values) {
                cards.add(new card(suit, value, value+"_of_"+suit));
            }
        }  
    } 
    PokerInfo(int clientCount) {
        cards = new ArrayList<card>();
        buildDeck() ; 
        playerCards = new ArrayList<card>();
        dealerCards = new ArrayList<card>();
        Status = "innitilaized";
        this.clientCount = clientCount ; 
    }
    
    
    public String getCardsString (ArrayList<card> cards){
        String AllCards = "" ; 
        for (int i = 0 ; i<cards.size(); i++){
            
            if ( i%4 == 0 )  AllCards+= "\n";
            AllCards= AllCards+cards.get(i).image+"\t"; 
        }
        return AllCards; 
    }
    public String getDeckofCardString(){
        return getCardsString(cards); 
    }

    public void setImages(){
        for(card i : cards){
            i.image = i.value+"_of_"+i.suit+".png";
        }
    }
    
    public String getPlayerCardsAString(){
        String PlayerCards = "" ; 
        PlayerCards = playerCards.get(0).value + " of " + playerCards.get(0).suit ; 
        PlayerCards += ", " + playerCards.get(1).value + " of " + playerCards.get(1).suit; 
        PlayerCards += ", " + playerCards.get(2).value + " of " + playerCards.get(2).suit+"."; 
        return PlayerCards ; 
    }
    public String getDealerCardsAString(){
        String DealerCards = "" ; 
        DealerCards = dealerCards.get(0).value + " of " + dealerCards.get(0).suit ; 
        DealerCards += ", " + dealerCards.get(1).value + " of " + dealerCards.get(1).suit; 
        DealerCards += ", " + dealerCards.get(2).value + " of " + dealerCards.get(2).suit+"."; 
        return DealerCards ; 
    }
    public boolean isPlayerWon() {
        return isPlayerWon;
    }
    public void setPlayerWon(boolean isPlayerWon) {
        this.isPlayerWon = isPlayerWon;
    }
    public boolean isShowDealerCards() {
        return showDealerCards;
    }
    public void setShowDealerCards(boolean showDealerCards) {
        this.showDealerCards = showDealerCards;
    }
    public int getWinnings() {
        return winnings;
    }
    public void setWinnings(int winnings) {
        this.winnings = winnings;
    }
    public ArrayList<card> getDealerCards() {
        return dealerCards;
    }
    public ArrayList<card> getPlayerCards(){
        return playerCards; 
    }
    public ArrayList<card> getCards() {
        return cards ; 
    }
    public void setCards(ArrayList<card> cards) {
        this.cards = cards;
    }
    public void setPlayerCards(ArrayList<card> playerCards) {
        this.playerCards = playerCards;
    }
    public void setDealerCards(ArrayList<card> dealerCards) {
        this.dealerCards = dealerCards;
    }
    public int getPlayerPlayout() {
        return playerPlayout;
    }
    public void setPlayerPlayout(int playerPlayout) {
        this.playerPlayout = playerPlayout;
    }
    public int getPairPlus() {
        return pairPlus;
    }
    public void setPairPlus(int pairPlus) {
        this.pairPlus = pairPlus;
    }
    public int getWager() {
        return wager;
    }
    public void setWager(int wager) {
        this.wager = wager;
    }
    public int getAnteWager() {
        return anteWager;
    }
    public void setAnteWager(int anteWager) {
        this.anteWager = anteWager;
    }
    public int getPayout() {
        return payout;
    }
    public void setPayout(int payout) {
        this.payout = payout;
    }
    public boolean isAnteWagePushedToNextRound() {
        return isAnteWagePushedToNextRound;
    }
    public void setAnteWagePushedToNextRound(boolean isAnteWagePushedToNextRound) {
        this.isAnteWagePushedToNextRound = isAnteWagePushedToNextRound;
    }
    public boolean isFoldedByPlayer() {
        return foldedByPlayer;
    }
    public void setFoldedByPlayer(boolean foldedByPlayer) {
        this.foldedByPlayer = foldedByPlayer;
    }
    public boolean isPairPlusBetMade() {
        return isPairPlusBetMade;
    }
    public void setPairPlusBetMade(boolean isPairPlusBetMade) {
        this.isPairPlusBetMade = isPairPlusBetMade;
    }
    public String getStatus() {
        return Status;
    }
    public void setStatus(String status) {
        Status = status;
    }
    public int getLosing() {
        return losing;
    }
    public void setLosing(int losing) {
        this.losing = losing;
    }
    public int getPairPlusWinnings() {
        return pairPlusWinnings;
    }
    public void setPairPlusWinnings(int pairPlusWinnings) {
        this.pairPlusWinnings = pairPlusWinnings;
    }
    public void setPairPlusWager(int i) {
        this.pairPlus = i ; 
    }
    public void setPairPlus(boolean b) {
    }
    public boolean isDealerHasQueenHigh() {
        return dealerHasQueenHigh;
    }
    public void setDealerHasQueenHigh(boolean dealerHasQueenHigh) {
        this.dealerHasQueenHigh = dealerHasQueenHigh;
    }


}
