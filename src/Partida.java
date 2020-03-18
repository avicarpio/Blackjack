import java.sql.Time;
import java.util.LinkedList;
import java.util.Random;

public class Partida {

    public static final int QUEDAT = 0;
    public static final int DEMANA = 1;
    public static final int DOBLA = 2;
    public static final int SPLIT = 3;
    public static final int BLACKJACK = 4;

    private LinkedList<Baralla> baralles;
    private LinkedList<Integer> player;
    private LinkedList<Integer> croupier;
    private Random generator;

    public Partida(){
        generator = new Random();
        baralles = new LinkedList<>();
        player = new LinkedList<>();
        croupier = new LinkedList<>();
        baralles.add(new Baralla());

    }

    public Partida(int n_bar){

        generator = new Random();
        baralles = new LinkedList<>();
        player = new LinkedList<>();
        croupier = new LinkedList<>();

        for(int i = 0; i < n_bar; i++){

            baralles.add(new Baralla());

        }

    }

    public void start(int pasta, int objectiu){

        int n_baralla = randomBaralla();

        int n_carta = randomCarta(n_baralla);

        player.add(baralles.get(n_baralla).getCarta(n_carta));

        n_baralla = randomBaralla();

        n_carta = randomCarta(n_baralla);

        croupier.add(baralles.get(n_baralla).getCarta(n_carta));

        n_baralla = randomBaralla();

        n_carta = randomCarta(n_baralla);

        player.add(baralles.get(n_baralla).getCarta(n_carta));

        n_baralla = randomBaralla();

        n_carta = randomCarta(n_baralla);

        croupier.add(baralles.get(n_baralla).getCarta(n_carta));

        LogicaDecisio ld = new LogicaDecisio(croupier, player);

        int decisio = ld.decideix_player();

        if(decisio != BLACKJACK) {
            if(decisio != SPLIT) {
                while (decisio != QUEDAT) {
                    n_baralla = randomBaralla();

                    n_carta = randomCarta(n_baralla);

                    player.add(baralles.get(n_baralla).getCarta(n_carta));

                    decisio = ld.decideix_player();
                }
            }else{
                //SPLIT
            }
        }

        printaCroupier();
        printaJugador();



    }

    public void printaJugador(){
        System.out.println();
        System.out.println("Cartes del jugador: ");
        System.out.println();

        int i = 0;
        while(i < player.size()) {
            System.out.println("Carta: " + player.get(i));
            i++;
        }

    }

    public void printaCroupier(){
        System.out.println();
        System.out.println("Cartes del croupier: ");
        System.out.println();

        int i = 0;
        while(i < croupier.size()) {
            System.out.println("Carta: " + croupier.get(i));
            i++;
        }
    }

    public int randomBaralla(){
        int num;
            num = Math.round(generator.nextInt(baralles.size()));
        return num;
    }

    public int randomCarta(int bar){
        int num;
            num = Math.round (generator.nextInt(baralles.get(bar).getSize()));
        return num;
    }


}
