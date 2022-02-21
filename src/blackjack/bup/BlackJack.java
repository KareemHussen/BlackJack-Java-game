package blackjack;

import java.util.Scanner;

public class BlackJack {

    static Game game = new Game();
    static GUI gui = new GUI();
    static boolean push = false;

    public static void main(String[] args) {

        game.generate(game.cards); //Generate card deck and initialize all players
        game.setInfo(); //Get player names and give each one 2 random cards
        game.calcMaxScore();
        gui.runGUI(game.cards, game.player[0].playerCards, game.player[1].playerCards, game.player[2].playerCards,
                game.player[3].playerCards); // call GUI

        //Gameplay
        System.out.println("Game started : ");
        for (int i = 0; i < 3; i++) {
            System.out.println("Player " + (i + 1) + " ," + game.player[i].getName() + "'s turn :");
            game.calcMaxScore();
            playGame(game.player[i], i);
        }
        //Dealer's turn
        System.out.println("Dealer's turn :");
        DealerGame();
        int maxCheck = checkPush();
        if (!push && (maxCheck < game.calcMaxScore())) {
            int win = getWinner();
            System.out.println("Winner is player " + (win + 1) + " : " + game.player[win].getName());
            game.calcMaxScore();
        } else {
            System.out.println("Push , some players got the same score");
        }
        System.out.println("Game ended");

    }

    public static void playGame(Player p, int index) {
        int score = p.getScore();
        System.out.println("Player " + p.getName() + " score is : " + score);
        System.out.println("Enter 1 to hit or 2 to stand : ");
        Scanner move = new Scanner(System.in);
        char action = '1';
        int i = 2;
        while (i < 11) {
            action = move.next().charAt(0);
            if (action == '1') {
                Card tmp = game.drawCard();
                p.setPlayerCards(i, tmp);
                gui.updatePlayerHand(tmp, index);
                i++;
                System.out.println("New card drawn , card value : " + tmp.getValue());
                System.out.println("Current player score : " + p.getScore());
                game.calcMaxScore();
                if (p.getScore() == 21) {
                    System.out.println("Player " +(index+1)+" got Blackjack");
                    p.setBlackJack(true);
                    break;
                }
                if (p.getScore() > 21) {
                    System.out.println("Player "+ (index+1)+ " got Busted");
                    game.calcMaxScore();
                    p.setLost(true);
                    break;
                }
                System.out.println("Enter 1 to hit or 2 to stand : ");
            } else if (action == '2') {
                System.out.println("Player " + p.getName() + " stand");
                break;
            } else {
                System.out.println("please enter a valid character");
            }
        }
    }

    public static void DealerGame() {
        Player p = game.player[3];
        int score = p.getScore();
        if (score > game.calcMaxScore()) {
            System.out.println("Dealer's Score already greater than other players' maximum score");
            System.out.println( game.player[3].getName() + " wins");
        } else {
            System.out.println("Dealer " + p.getName() + " score is : " + score);
            System.out.println("Enter 1 to hit or 2 to stand : ");
            Scanner move = new Scanner(System.in);
            char action = '1';
            int i = 2;
            while (i < 11) {
                action = move.next().charAt(0);
                if (action == '1') {
                    Card tmp = game.drawCard();
                    p.setPlayerCards(i, tmp);
                    gui.updateDealerHand(tmp, game.cards);
                    i++;
                    System.out.println("New card drawn , card value : " + tmp.getValue());
                    System.out.println("Current player score : " + p.getScore());
                    game.calcMaxScore();
                    if (p.getScore() == 21) {
                        System.out.println("Dealer got Blackjack");
                        p.setBlackJack(true);
                        break;
                    }
                    if (p.getScore() > 21) {
                        System.out.println("Dealer got Busted");
                        game.calcMaxScore();
                        p.setLost(true);
                        break;
                    }
                    System.out.println("Enter 1 to hit or 2 to stand : ");
                } else if (action == '2') {
                    System.out.println("Player " + p.getName() + " stand");
                    break;
                } else {
                    System.out.println("please enter a valid character");
                }
            }
        }

    }

    public static int getWinner() {
        int maxScore = 0;
        int index = 0;
        for (int i = 0; i < 4; i++) {
            if (game.player[i].getScore() > maxScore && !game.player[i].isLost()) {
                maxScore = game.player[i].getScore();
                index = i;
            }
        }
        return index;
    }

    public static int checkPush() {
        int pscore = -1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (game.player[i].getScore() == game.player[j].getScore() && i != j && !game.player[i].isLost()) {
                    push = true;
                    pscore = game.player[i].getScore();
                }
            }
        }
    return pscore;
    }
}
