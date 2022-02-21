package blackjack;
import java.util.Random;
import java.util.Scanner;


public class Game {
    public Player player[] = new Player[4];

    public Card cards[] = new Card[52];
    private static int maxScore ;
    Random rand = new Random();
    private int calcValue(int x){
        if (x <= 9){
            return ++x;
        }
        else return 10;
    }

    public Card[] getCards() {
        return cards;
    }

    public void generate(Card cards[]) {
        for ( int i =0 ; i < 13 ; i++){
            cards[i] = new Card(0 , i , calcValue(i) );
        }
        for ( int i =13 ; i < 26 ; i++){
            cards[i] = new Card(1 , i%13 , calcValue((i)%13) );
        }
        for ( int i =26 ; i < 39 ; i++){
            cards[i] = new Card(2 , i%13 , calcValue((i)%13) );
        }
        for ( int i =39 ; i < 52 ; i++){
            cards[i] = new Card(3 , i%13 , calcValue((i)%13) );
        }
        for ( int i =0 ; i<4 ;i++){
            player[i] = new Player();
            player[i].initialize();
        }
    }
    public Card drawCard(){
        int z = rand.nextInt(52);
        if (cards[z] != null){
            Card temp = cards[z];
            cards[z] = null;
            return temp;
        }
        else return drawCard();//recursive call
    }
    Scanner input = new Scanner(System.in);
    public void setInfo (){
        int d = 1;
        for ( int i =0 ; i<4 ; i++){
            System.out.println("Enter name of player "+ d);
            String name = input.next();
            d++;
            player[i].setName(name);
            player[i].setPlayerCards(0 , drawCard());
            player[i].setPlayerCards(1 , drawCard());
        }
        String dealerName = "Dealer : "+player[3].getName();
        player[3].setName(dealerName);
    }
    public int calcMaxScore(){
        int tmp = 0;
        for (int i = 0 ; i<4 ; i++){
            if (player[i].getScore() > tmp && !player[i].isLost() && player[i].getScore() <= 21){
                tmp = player[i].getScore();
            }
        }
        maxScore = tmp;
        System.out.println("Current max score is : " + maxScore);
        return tmp;
    }
}
