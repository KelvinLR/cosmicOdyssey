package meujogo;
import javax.swing.JFrame;
import elements.Fase;
import elements.FaseSingle;
import elements.Menu;

import java.awt.*;

public class Container extends JFrame {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 728;
    public static String gameState = "MENU";
    public static Container frameAtual;

    public Container(int op) {   
        if(op == 1) {
            add(new Menu(this));
        } else if (op == 2) {
            add(new FaseSingle("res/pixel art _ Tumblr.gif"));
        }
        else
        {
            add(new Fase("res/pixel art _ Tumblr.gif"));
        }

		setTitle("Cosmic Odyssey: Luzes do Infinito");
		setResizable(false);
		setSize(WIDTH, HEIGHT);	
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
        frameAtual = this;

        setIco();
    }

    public void fecharContainerAtual() {
        dispose();
    }

    public void setIco()
    {
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("res/icon.png")));
    }


    public static void main(String[] args){
        new Container(1);
    }
}
