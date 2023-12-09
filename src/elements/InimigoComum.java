package elements;

import java.awt.event.ActionEvent;

public class InimigoComum extends Nave {
    private static int VELOCIDADE = 2; // Velocidade do inimigo

    // Construtor da classe InimigoComum
    public InimigoComum(int x, int y){
        super(x, y, "res/enemy1.png", 1000); // Chamada do construtor da classe abstrata Nave
    }

    // Implementação do método abstrato actionPerformed()
    @Override
	public void actionPerformed(ActionEvent e) {}

    // Implementação do método abstrato update()
    public void update(){
        if (this.x < -largura) {
			this.x = (int) (Math.random() * 8000 + 1024);
			this.y = (int) (Math.random() * 580 + 40);
		}
        else {
            this.x -= VELOCIDADE;
        }
    }

    // Get e Set para a velocidade de InimigoComum
    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }
}
