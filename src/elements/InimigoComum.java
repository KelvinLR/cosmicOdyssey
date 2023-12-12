//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

import java.awt.event.ActionEvent;

public class InimigoComum extends Nave {
    private static int VELOCIDADE = 3;

    public InimigoComum(int x, int y) {
        super(x, y, "res/enemy1.png", 1000);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void update() {
        if (this.x < -this.largura) {
            this.x = (int)(Math.random() * 8000.0 + 1024.0);
            this.y = (int)(Math.random() * 580.0 + 40.0);
        } else {
            this.x -= VELOCIDADE;
        }

    }

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }
}
