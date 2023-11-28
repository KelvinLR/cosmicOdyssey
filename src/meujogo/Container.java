package meujogo;
import javax.swing.JFrame;
import elements.Fase;

public class Container extends JFrame{

    public Container()
    {
        add(new Fase("res/pixel art _ Tumblr.gif"));
        
        setTitle("Cosmic Odyssey");
        setSize(1024,728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args){
        new Container();
    }
    
}
