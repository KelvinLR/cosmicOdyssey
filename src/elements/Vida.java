//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

public class Vida extends Element {
    private static int VELOCIDADE = 2;

    public Vida(int x, int y) {
        super(x, y, "res/Vida.png");
    }

    public void update() {
        if (this.x < 0) {
            this.isVisible = false;
        } else {
            this.x -= VELOCIDADE;
        }

    }
}
