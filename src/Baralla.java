import java.util.Collections;
import java.util.LinkedList;

public class Baralla {

    private LinkedList<Integer> cartes;
    private int howMany;
    private int initCards;

    public Baralla(int howMany){
        //Crea Baralla

        this.howMany = howMany;
        cartes = new LinkedList<>();

        generateCards();
        
        initCards = cartes.size();

    }

    private void generateCards() {
        for(int b = 0; b < howMany; b++){
            for(int j = 0; j < 4; j++){
                for(int i = 1; i <= 13; i++){
                    cartes.add(i);
                }
            }
        }
        Collections.shuffle(cartes);
    }

    public void printaBaralla(){

        int i = 0;

        try{
            while(i < cartes.size()) {
                System.out.println("Carta: " + cartes.get(i));
                i++;
            }
        }catch (Exception e){
            System.out.println("Tot OK");
        }


    }

    public Integer getCarta(){
        if(cartes.size() < initCards / 2){
            cartes.clear();
            generateCards();
        }
        return cartes.pop();
    }

    public Integer getSize(){
        return cartes.size();
    }

}
