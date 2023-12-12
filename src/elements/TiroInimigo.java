//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

public class TiroInimigo extends Element {
    private static final int LARGURA = 1024;
    private static int VELOCIDADE = 5;

    public TiroInimigo(int x, int y) {
        super(x, y, "res/TiroInimigo.png");
    }

    public void update() {
        this.x -= VELOCIDADE;
        if (this.x > 1024) {
            this.isVisible = false;
        }

        if (this.x < 0) {
            this.isVisible = false;
        }

    }
}
