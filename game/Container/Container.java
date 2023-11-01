package Container;

import javax.swing.JFrame;

public class Container extends JFrame {
    public Container(){
        setTitle("Cosmic Odyssey: Luzes do Infinito");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
}
