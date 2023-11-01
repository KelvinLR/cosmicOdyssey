package Container;
import Fases.Fase;

import javax.swing.JFrame;

public class Container extends JFrame {
    public Container(Fase fase) {
        add(fase);
        setTitle("Cosmic Odyssey: Luzes do Infinito");
        setSize(900, 675);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
}
