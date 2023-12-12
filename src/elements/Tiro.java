//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

import songs.EfeitosSonoros;

public class Tiro extends Element {
    private static final int LARGURA = 938;
    private static int VELOCIDADE = 6;

    public Tiro(int x, int y) {
        super(x, y, "res/TiroSimples.png");
        this.somTiroSimples();
    }

    public void update() {
        this.x += VELOCIDADE;
        if (this.x > 938) {
            this.isVisible = false;
        }

    }

    public void somTiroSimples() {
        EfeitosSonoros a = new EfeitosSonoros();
        a.tocarTiro();
    }
}
