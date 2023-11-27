package elements;

import songs.EfeitosSonoros;


public class Tiro extends Element {

    private static final int LARGURA = 938;
    private static int VELOCIDADE = 6;

    
    public Tiro(int x, int y){
        super(x,y, "res/TiroSimples.png");
        somTiroSimples();
    }

    // Implementação do método update() definido na classe Element
    public void update()
    {
        this.x += VELOCIDADE;
          if(this.x > LARGURA)
            isVisible = false;
    }

    public void somTiroSimples() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarTiro();
	} 

}
