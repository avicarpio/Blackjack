import java.util.LinkedList;

public class LogicaDecisio {

    public static final int QUEDAT = 0;
    public static final int DEMANA = 1;
    public static final int DOBLA = 2;
    public static final int SPLIT = 3;
    public static final int BLACKJACK = 4;
    private LinkedList<Integer> croupier;
    private LinkedList<Integer> player;
    private int enemy;

    public LogicaDecisio(LinkedList<Integer> cartes_croupier, LinkedList<Integer> cartes_player){

        croupier = new LinkedList<>();
        croupier = cartes_croupier;
        enemy = croupier.get(0);

        if(enemy > 10){
            enemy = 10;
        }

        player = new LinkedList<>();
        player = cartes_player;

        if(player.get(0) > 10){
            player.set(0,10);
        }
        if(player.get(1) > 10){
            player.set(1,10);
        }

    }

    public int decideix_player(){
        int decisio = QUEDAT;

        if(player.size() == 2){
            if(player.get(0) == 1 || player.get(1) == 1 && player.get(0) == 10 || player.get(1) == 10){
                //BLACKJACK!

                decisio = BLACKJACK;
            }else {
                if (player.get(0) == player.get(1)) {
                    //Son parella

                    decisio = parella();
                } else {
                    if (player.get(0) == 1 || player.get(1) == 1) {
                        //Te un as

                        decisio = ace();
                    } else {
                        //Son diferents

                        decisio = diferents();
                    }
                }
            }
        }else{
            decisio = diferents();
        }

        return decisio;
    }

    private int diferents() {

        int suma = 0;
        int fes = QUEDAT;

        for(int i = 0; i < player.size(); i++){
            suma = suma + player.get(i);
        }

        if(suma <= 8){
            fes = DEMANA;
        }

        if(suma == 9){
            if(enemy != 3 && enemy != 4 && enemy != 5 && enemy != 6){
                fes = DEMANA;
            }else{
                fes = DOBLA;
            }
        }

        if(suma == 10){
            if(enemy != 3 && enemy != 4 && enemy != 5 && enemy != 6 && enemy != 7 && enemy != 8 && enemy != 9){
                fes = DEMANA;
            }else{
                fes = DOBLA;
            }
        }

        if(suma == 11){
            if(enemy != 3 && enemy != 4 && enemy != 5 && enemy != 6 && enemy != 7 && enemy != 8 && enemy != 9 && enemy != 10){
                fes = DEMANA;
            }else{
                fes = DOBLA;
            }
        }

        if(suma == 12){
            if(enemy != 4 && enemy != 5 && enemy != 6){
                fes = DEMANA;
            }else{
                fes = QUEDAT;
            }
        }

        if(suma >= 13 && suma <= 16){
            if(enemy != 2 && enemy != 3 && enemy != 4 && enemy != 5 && enemy != 6){
                fes = DEMANA;
            }else{
                fes = QUEDAT;
            }
        }

        //Si es 17 o + fes = QUEDAT

        return fes;
    }

    private int ace() {
        int carta;
        int fes = QUEDAT;

        if(player.get(0) == 1){
            carta = player.get(1);
        }else{
            carta = player.get(0);
        }

        if(carta == 2 || carta == 3){
            if(enemy != 5 && enemy !=6){
                fes = DEMANA;
            }else{
                fes = DOBLA;
            }
        }

        if(carta == 4 || carta == 5){
            if(enemy != 4 && enemy != 5 && enemy !=6){
                fes = DEMANA;
            }else{
                fes = DOBLA;
            }
        }

        if(carta == 6){
            if(enemy != 3 && enemy != 4 && enemy != 5 && enemy !=6){
                fes = DEMANA;
            }else{
                fes = DOBLA;
            }
        }

        if(carta == 7){
            if(enemy != 3 && enemy != 4 && enemy != 5 && enemy !=6){
                if(enemy != 2 && enemy != 7 && enemy != 8){
                    fes = DEMANA;
                }else{
                    fes = QUEDAT;
                }
            }else{
                fes = DOBLA;
            }
        }

        //Si es un 8 o un 9 fes = QUEDAT


        return fes;
    }

    private int parella() {
        int carta = player.get(0);
        int fes = QUEDAT;

        if(carta == 1){
            if(enemy != 10 && enemy != 1){
                fes = SPLIT;
            }else{
                fes = DEMANA;
            }
        }

        if(carta == 2 || carta == 3 || carta == 7){
            if(enemy != 8 && enemy != 9 && enemy != 10 && enemy != 1){
                fes = SPLIT;
            }else{
                fes = DEMANA;
            }
        }

        if(carta == 4){
            if(enemy != 5 && enemy != 6){
                fes = DEMANA;
            }else{
                fes = SPLIT;
            }
        }

        if(carta == 5){
            if(enemy != 1){
                fes = DOBLA;
            }else{
                fes = DEMANA;
            }
        }

        if(carta == 6){
            if(enemy != 7 && enemy != 8 && enemy != 9 && enemy != 10 && enemy != 1){
                fes = SPLIT;
            }else{
                fes = DEMANA;
            }
        }

        if(carta == 8){
            if(enemy != 1){
                fes = SPLIT;
            }else{
                fes = DEMANA;
            }
        }

        if(carta == 9){
            if(enemy != 7 && enemy != 10 && enemy != 1){
                fes = QUEDAT;
            }else{
                fes = SPLIT;
            }
        }

        //Si es una parella de 10 fes = QUEDAT

        return fes;
    }
}
