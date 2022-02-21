package blackjack;

public class Player {
    private String name;
    private int score=0;
    public Card playerCards[] = new Card[11];
    private boolean blackJack = false , lost = false;
    int index=0;
    public void initialize() {
        for(int i = 0; i <11 ;i++){
            playerCards[i] = null;
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Card getPlayerCards(int i) {
        return playerCards[i];
    }

    public void setPlayerCards( int position, Card card) {
        this.playerCards[position] = card;
        this.score+=playerCards[position].getValue();
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public void setBlackJack(boolean blackJack) {
        this.blackJack = blackJack;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }
}
