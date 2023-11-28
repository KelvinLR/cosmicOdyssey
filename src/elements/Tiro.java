package elements;

import songs.EfeitosSonoros;

public class Tiro extends Element {
  private static final int LARGURA = 938; // Largura do objeto
  private static int VELOCIDADE = 6; // Velocidade do objeto

  // Construtor da classe Tiro
  public Tiro(int x, int y){
    super(x,y, "res/TiroSimples.png"); // Chamada do construtor da classe abstrata Element
    somTiroSimples();
  }

  // Implementação do método update() definido na classe Element
  public void update(){
    this.x += VELOCIDADE;
    if(this.x > LARGURA)
      isVisible = false;
  }

  // Método responsável pelo efeito sonoro de Tiro
  public void somTiroSimples() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarTiro();
	} 

}
