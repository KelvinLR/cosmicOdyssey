import Container.Container;
import Fases.Fase;

public class Main {
    public static void main(String[] args){
        System.out.println("Rodando o jogo");
        Fase f1 = new Fase("assets\\scenario\\galaxy-animated.gif");
        new Container(f1);
    }
}
