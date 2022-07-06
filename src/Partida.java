import java.util.LinkedList;

public class Partida {

    public static final int QUEDAT = 0;
    public static final int DEMANA = 1;
    public static final int DOBLA = 2;
    public static final int SPLIT = 3;
    public static final int BLACKJACK = 4;

    private Baralla baralles;
    private LinkedList<Integer> player;
    private LinkedList<Integer> croupier;

    private float money;
    private float limit;
    int decisio;
    private float aposta = 1;

    private int bancaGana = 0;
    private int jugadorGana = 0;

    public Partida(){
        player = new LinkedList<>();
        croupier = new LinkedList<>();
        baralles = new Baralla(1);
    }

    public Partida(int n_bar){

        player = new LinkedList<>();
        croupier = new LinkedList<>();

        baralles = new Baralla(n_bar);

    }

    public void start(int pasta, int objectiu){

        this.money = pasta;
        this.limit = objectiu;

        int numIntents = 100;
        for (int j = 0; j < numIntents; j++){
            this.money = pasta;
            this.limit = objectiu;
            aposta = 1;
            while(money > 0 && money < limit){
                //System.out.println("----------------------------");
                generatePartida(false, -1, -1);
                //System.out.println("Current Money: " + money + " €");
            }
            System.out.println("Partida nº " + j + " | Retirada: " + Math.max(money,0) + " €");
            if(money <= 0){
                bancaGana ++;
            }else{
                jugadorGana ++;
            }
        }

        System.out.println("Resultats | Success: " + jugadorGana + " Banca Guanya: " + bancaGana + " | Percetatge de victòria: " + (float)jugadorGana / (float)bancaGana * 100 + "%");
        
    }

    public void generatePartida(boolean split, int splitCroupier, int playerSplit) {

        boolean enableSplit = false;
        
        if(!split){
            player.add(baralles.getCarta());
        }else{
            player.add(playerSplit);
        }

        croupier.add(baralles.getCarta());

        player.add(baralles.getCarta());

        croupier.add(baralles.getCarta());

        LogicaDecisio ld = new LogicaDecisio(croupier, player);

        decisio = ld.decideix_player();

        if(decisio != BLACKJACK) {
            if(decisio != SPLIT && !split) {
                while (decisio != QUEDAT && decisio != DOBLA) {
                    player.add(baralles.getCarta());
                    decisio = ld.decideix_player();
                }
                if(decisio == DOBLA){
                    aposta = aposta * 2;
                    player.add(baralles.getCarta());
                }
            }else{
                enableSplit = true;
                player.pop();
            }
        }

        int totalCroupier = 0;

        if(!split){

            for(int i = 0; i < croupier.size(); i++) {
                totalCroupier += Math.min(croupier.get(i), 10);
            }

            while(totalCroupier < 17){
                croupier.add(baralles.getCarta());
                totalCroupier +=  Math.min(croupier.size() - 1, 10);
            }
        }else{
            totalCroupier = splitCroupier;
        }

        int totalPlayer = 0;

        for(int i = 0; i < player.size(); i++) {
            totalPlayer += Math.min(player.get(i), 10);
        }

        //printaCroupier();

        //printaJugador();

        //System.out.println();
        //System.out.println("Visible Croupier: " + croupier.get(0));
        //System.out.println("Suma Croupier: " + totalCroupier);
        //System.out.println("Suma Player: " + totalPlayer);

        if(enableSplit){
            generatePartida(split, totalCroupier, player.get(0));
        }
        
        winner(totalCroupier, totalPlayer);

        totalCroupier = 0;
        totalPlayer = 0;
        croupier.clear();
        player.clear();
    }

    public void printaJugador(){
        System.out.println();
        System.out.println("Cartes del jugador: ");
        System.out.println();

        for(int i = 0;  i < player.size(); i++) {
            System.out.println("Carta: " + player.get(i));
            i++;
        }

    }

    public void printaCroupier(){
        System.out.println();
        System.out.println("Cartes del croupier: ");
        System.out.println();

        for(int i = 0;  i < croupier.size(); i++) {
            System.out.println("Carta: " + croupier.get(i));
            i++;
        }
    }

    public int randomCarta(){
        int num;
            num = baralles.getCarta();
        return num;
    }

    public void winner(int totalCroupier, int totalPlayer){
    
        boolean playerWins = false;
        boolean draw = false;

        if (totalCroupier < 22){
            if(totalPlayer < 22){
                if(totalPlayer > totalCroupier){
                    playerWins = true;
                }
                else if(totalPlayer == totalCroupier){
                    draw = true;
                }
            }
        }else{
            if(totalPlayer < 22){
                playerWins = true;
            }else{
                draw = true;
            }
        }

        //System.out.println("Aposta: " + aposta + " €");
        if(draw){
            //System.out.println("DRAW!");
        }else{
            if(playerWins){
                //System.out.println("PLAYER WINS!");
                if(decisio == BLACKJACK){
                    money = (float) (money + aposta * 1.5);
                }else{
                    money += aposta;
                }
                aposta = 1;
            }else{
                //System.out.println("CROUPIER WINS!");
                money -= aposta;
                aposta = aposta * 2;
            }
        }
    }

}
