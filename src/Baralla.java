import java.util.LinkedList;

public class Baralla {

    private LinkedList<Integer> carta;

    public Baralla(){
        //Crea Baralla

        carta = new LinkedList<>();

        for(int j = 0; j < 4; j++){
            for(int i = 1; i <= 13; i++){
                carta.add(i);
            }
        }

    }

    public void printaBaralla(){

        int i = 0;

        try{
            while(i < carta.size()) {
                System.out.println("Carta: " + carta.get(i));
                i++;
            }
        }catch (Exception e){
            System.out.println("Tot OK");
        }


    }

    public Integer getCarta(int numero){
        int quina = carta.get(numero);

        carta.remove(numero);

        return quina;
    }

    public Integer getSize(){
        return carta.size();
    }

}
