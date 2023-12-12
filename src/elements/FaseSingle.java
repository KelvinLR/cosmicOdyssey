package elements;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FaseSingle extends JPanel implements ActionListener {

    private Image fundo;
    private Player player1;
    private Timer timer;
    private ArrayList<InimigoComum> inimigosComuns;
    private ArrayList<InimigoAtirador> inimigosAtiradores;
    private Chefao chefao;
    private List<Vida> vidas;
    private ArrayList <Explosao> explosoes;
    private boolean emJogo, emExplosao, youWin;
    private int powerUpVida = 0;
    private Font minecraftFont;
    private static int numeroInimigos = 40;

    // Construtor da classe Fase com a referência da tela de fundo.
    public FaseSingle(String ref) {
        setFocusable(true);
        setDoubleBuffered(true);
        emJogo = true;
        youWin = false;

        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource(ref));
        fundo = referencia.getImage();

        player1 = new Player(1);

        powerUpVida = 0;
        vidas = new ArrayList<Vida>();

        addKeyListener(new TecladoAdapter());

        waitForSeconds();
        Timer timer = new Timer(5,this);
        timer.start();

        inicializarElementos();
        inicializarInimigos1();
        //INICIALIZAR OS INIMIGOS ATIRADORES COM ARRAY VAZIO
        inimigosAtiradores = new ArrayList<InimigoAtirador>();
        inicializarChefao();
    }

    public Font carregarFonte() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/res/Fonts/Minecraft.ttf");

            if (fontStream != null) {
                return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(1, 18);
            } else {
                System.err.println("Arquivo de fonte não encontrado.");
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Lida com exceções, por exemplo, carregando uma fonte padrão
        return new Font("Arial", Font.PLAIN, 18);
    }

    public void inicializarInimigos1()
    {
        inimigosComuns = new ArrayList<InimigoComum>();
        for (int i = 0; i < numeroInimigos; i++) {
            int x = (int) ((Math.random() * 8000) + 1024);
            int y = (int) ((Math.random() * 580) + 40);
            inimigosComuns.add(new InimigoComum(x, y));
        }
    }

    public void inicializarInimigos2()
    {
        for (int i = 0; i < numeroInimigos; i++) {
            int x = (int) ((Math.random() * 8000) + 1024);
            int y = (int) ((Math.random() * 580) + 40);
            inimigosAtiradores.add(new InimigoAtirador(x, y));
        }
    }

    public void inicializarChefao()
    {
        chefao = new Chefao(1024, 250);
        chefao.setVisivel(false);
    }

    public void inicializarElementos()
    {
        this.explosoes = new ArrayList<Explosao>();
        minecraftFont = carregarFonte();
    }

    public void arrayShot(Graphics g){
        ArrayList<Tiro> tiros = player1.getTiros();
        Graphics2D graficos = (Graphics2D) g;
        for (int i = 0; i < tiros.size(); i++) {
            Tiro t = tiros.get(i);
            graficos.drawImage(t.getImagem(), t.getX(), t.getY(), this);
        }
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        if(youWin) {
            ImageIcon gamerOver = new ImageIcon(getClass().getClassLoader().getResource("res/youwin.png"));
            graficos.drawImage(gamerOver.getImage(), 0, 0, null);

            graficos.setColor(Color.white);
            minecraftFont = minecraftFont.deriveFont(Font.PLAIN, 25);
            graficos.setFont(minecraftFont);

            int scoreTotal = player1.getScore();

            String scoreText = "SCORE: " + scoreTotal;
            int textWidth = graficos.getFontMetrics().stringWidth(scoreText);

            int screenWidth = getWidth();
            int screenHeight = getHeight();

            int x = (screenWidth - textWidth) / 2; // Centraliza horizontalmente
            int y = 510; // Define a posição vertical (ajuste conforme necessário)

            graficos.drawString(scoreText, x, y);
        }
        else{
            if (emJogo) {
                graficos.drawImage(fundo, 0, 0, null);

                if (player1.isVisible())
                    graficos.drawImage(player1.getImagem(), player1.getX(), player1.getY(), this);

                arrayShot(g);

                //Inimigos 1
                for (int i = 0; i < inimigosComuns.size(); i++) {
                    InimigoComum in = inimigosComuns.get(i);
                    if (in.getBounds().intersects(0, 0, 1024, 728)) {
                        graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                    }
                }
                //////////////////////////////////////////////////////////////////////////////

                //Inimigos 2 e seus Tiros
                for (int i = 0; i < inimigosAtiradores.size(); i++) {
                    InimigoAtirador in = inimigosAtiradores.get(i);
                    if (in.getBounds().intersects(0, 0, 1024, 728)) {
                        graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                    }
                }

                for (int i = 0; i < inimigosAtiradores.size(); i++) {
                    List<TiroInimigo> tirosInimigo = inimigosAtiradores.get(i).getTirosInimigo();
                    for (int o = 0; o < tirosInimigo.size(); o++) {
                        TiroInimigo m = (TiroInimigo) tirosInimigo.get(o);
                        if (m.getBounds().intersects(0, 0, 1024, 728)) {
                            graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                        }
                    }
                }
                //////////////////////////////////////////////////////////////////////////////

                //Explosões
                for (int i = 0; i < explosoes.size(); i++) {
                    Explosao ex = explosoes.get(i);
                    graficos.drawImage(ex.getImagem(), ex.getX(), ex.getY(), this);
                }
                /////////////////////////////////////////////////////////////////////////////

                //PowerUp Vida
                for (int k = 0; k < vidas.size(); k++) {
                    Vida on = vidas.get(k);
                    graficos.drawImage(on.getImagem(), on.getX(), on.getY(), null);
                }
                /////////////////////////////////////////////////////////////////////////////////

                //Status da vida

                graficos.setColor(Color.white);
                minecraftFont = minecraftFont.deriveFont(Font.PLAIN, 18);
                graficos.setFont(minecraftFont);

                String p1 = "P1:";

                graficos.drawString(p1, 11, 30);

                int x1 = 40;
                for (int j = 0; j < player1.getVida(); j++) {
                    ImageIcon vida = new ImageIcon(getClass().getClassLoader().getResource("res/Vida.png"));
                    graficos.drawImage(vida.getImage(), x1, 10, null);
                    x1 += 30;
                }

                /////////////////////////////////////////////////////////////////////////////////////

                //Chefao e seus tiros
                if (chefao.isVisivel()) {
                    chefao.load();
                    graficos.drawImage(chefao.getImagem(), chefao.getX(), chefao.getY(), this);

                    if (chefao.getVida() > 0 && chefao.getVida() < 30) {
                        ImageIcon vidaBoss = new ImageIcon(getClass().getClassLoader().getResource("res/VidaBoss.png"));
                        graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
                    }
                    if (chefao.getVida() > 29 && chefao.getVida() < 50) {
                        ImageIcon vidaBoss = new ImageIcon(getClass().getClassLoader().getResource("res/VidaBoss1Hit.png"));
                        graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
                    }
                    if (chefao.getVida() > 49 && chefao.getVida() < 80) {
                        ImageIcon vidaBoss = new ImageIcon(getClass().getClassLoader().getResource("res/VidaBoss2Hit.png"));
                        graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
                    }
                    if (chefao.getVida() > 79 && chefao.getVida() < 100) {
                        ImageIcon vidaBoss = new ImageIcon(getClass().getClassLoader().getResource("res/VidaBoss3Hit.png"));
                        graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
                    }

                    if (chefao.getVida() > 99 && chefao.getVida() < 120) {
                        ImageIcon vidaBoss = new ImageIcon(getClass().getClassLoader().getResource("res/VidaBoss4Hit.png"));
                        graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
                    }

                    if (chefao.getVida() > 119) {
                        ImageIcon vidaBoss = new ImageIcon(getClass().getClassLoader().getResource("res/VidaBoss5Hit.png"));
                        graficos.drawImage(vidaBoss.getImage(), 250, 650, null);
                    }
                } else if (!chefao.getExplodido()) {
                    ImageIcon explosaoChefe = new ImageIcon(getClass().getClassLoader().getResource("res/explosion.gif"));
                    graficos.drawImage(explosaoChefe.getImage(), chefao.getX(), chefao.getY(), null);
                    chefao.setExplodido(true);
                }

                List<TiroChefao> tiroBoss = chefao.getTiroBoss();
                for (int o = 0; o < tiroBoss.size(); o++) {
                    TiroChefao m = tiroBoss.get(o);
                    if (chefao.isVisivel()) {
                        graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                    }
                }
                ////////////////////////////////////////////////////////////////////////////////////////////

                //Score
                graficos.setColor(Color.white);
                minecraftFont = minecraftFont.deriveFont(Font.BOLD, 18);
                graficos.setFont(minecraftFont);

                int scoreTotal = player1.getScore();

                String scoreText = "SCORE: " + scoreTotal;
                int textWidth = graficos.getFontMetrics().stringWidth(scoreText);

                int screenWidth = getWidth();
                int screenHeight = getHeight();

                int x = (screenWidth - textWidth) / 2; // Centraliza horizontalmente
                int y = 30; // Define a posição vertical (ajuste conforme necessário)

                graficos.drawString(scoreText, x, y);
                ///////////////////////////////////////////////////////////////////////////////////////
            } else {
                ImageIcon gamerOver = new ImageIcon(getClass().getClassLoader().getResource("res/fimdejogo.png"));
                graficos.drawImage(gamerOver.getImage(), 0, 0, null);

                graficos.setColor(Color.white);
                minecraftFont = minecraftFont.deriveFont(Font.PLAIN, 25);
                graficos.setFont(minecraftFont);

                int scoreTotal = player1.getScore();

                String scoreText = "SCORE: " + scoreTotal;
                int textWidth = graficos.getFontMetrics().stringWidth(scoreText);

                int screenWidth = getWidth();
                int screenHeight = getHeight();

                int x = (screenWidth - textWidth) / 2; // Centraliza horizontalmente
                int y = 430; // Define a posição vertical (ajuste conforme necessário)

                graficos.drawString(scoreText, x, y);
            }
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!player1.isVisible())
            emJogo = false;

        if(emJogo) {

            ArrayList<Tiro> tiros = player1.getTiros();

            if (player1.isVisible())
                player1.update();
            else if(!player1.getExplodido())
            {
                player1.setExplodido(true);
                explosoes.add(new Explosao(player1.getX(), player1.getY()));
            }

            for (int i = 0; i < tiros.size(); i++) {
                if (tiros.get(i).isVisible()) {
                    tiros.get(i).update();
                } else {
                    tiros.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < inimigosComuns.size(); i++) {
                InimigoComum in = inimigosComuns.get(i);

                if (in.isVisible()) {
                    in.update();
                } else {
                    inimigosComuns.remove(i);
                    i--;
                    explosoes.add(new Explosao(in.getX(), in.getY()));
                    powerUpVida++;
                    if (powerUpVida == 5) {
                        vidas.add(new Vida(in.getX(), in.getY()));
                        powerUpVida = 0;
                    }
                }
            }

            for (int i = 0; i < inimigosAtiradores.size(); i++) {
                InimigoAtirador in = inimigosAtiradores.get(i);

                if (in.isVisible()) {
                    in.update();
                } else {
                    inimigosAtiradores.remove(i);
                    i--;
                    explosoes.add(new Explosao(in.getX(), in.getY()));
                    powerUpVida++;
                    if (powerUpVida == 5) {
                        vidas.add(new Vida(in.getX(), in.getY()));
                        powerUpVida = 0;
                    }
                }
            }


            if (inimigosAtiradores != null && !inimigosAtiradores.isEmpty()) {
                for (int q = 0; q < inimigosAtiradores.size(); q++) {
                    List<TiroInimigo> tiroInimigos = inimigosAtiradores.get(q).getTirosInimigo();
                    for (int o = 0; o < tiroInimigos.size(); o++) {
                        TiroInimigo m = (TiroInimigo) tiroInimigos.get(o);
                        if (m.isVisible()) {
                            m.update();
                        } else {
                            tiroInimigos.remove(o);
                            o--;
                        }

                    }
                }
            }

            if (explosoes != null && !explosoes.isEmpty()) {   // "!explosoes.isEmpty()" equivale a "explosoes.size() != 0"
                for (int q = 0; q < explosoes.size(); q++) {
                    Explosao y = explosoes.get(q);
                    if (y.isVisible()) {
                        y.update();
                    } else {
                        explosoes.remove(q);
                        //q--;
                    }
                }
            }

            for (int p = 0; p < vidas.size(); p++) {
                Vida on = vidas.get(p);

                if (on.isVisible())
                    on.update();
                else {
                    vidas.remove(p);
                    p--;
                }
            }

            if (chefao.isVisivel()) {
                chefao.update(player1.getX(), player1.getY());
                if (chefao.getTiroBoss().isEmpty()) {
                    chefao.tiroBoss();
                }
            }

            List<TiroChefao> tiroBoss = chefao.getTiroBoss();

            if(!tiroBoss.isEmpty()) {
                TiroChefao tempTiro = tiroBoss.get(0);

                if (tempTiro != null) {
                    if (tempTiro.isVisivel()) {
                        tempTiro.update(player1.getX(), player1.getY());
                    } else
                        tiroBoss.remove(0);
                }
            }

            collisions();
        }

        repaint();
    }

    public void collisions()
    {
        Rectangle formaNave = player1.getBounds();
        Rectangle formaInimigo1;
        Rectangle formaInimigo2;
        Rectangle formaTiroInimigo;
        Rectangle formaTiro;
        Rectangle formaTiro2;
        Rectangle formaChefao;
        Rectangle formaTiroChefao;

        //Inimigo1, player1 e player2
        if(player1.isVisible()) {
            for (int i = 0; i < inimigosComuns.size(); i++) {
                formaInimigo1 = inimigosComuns.get(i).getBounds();

                if (formaNave.intersects(formaInimigo1)) {
                    player1.somarScore();

                    int a = player1.getVida();

                    if (a == 0) {
                        player1.setVisible(false);
                        break;
                    } else {
                        player1.setVida(a - 1);
                        inimigosComuns.get(i).setVisible(false);
                        break;
                    }
                }
            }
        }

        //Inimigo2 & SeusTiros, player1 e player2
        if(player1.isVisible()) {
            for (int i = 0; i < inimigosAtiradores.size(); i++) {
                formaInimigo2 = inimigosAtiradores.get(i).getBounds();
                InimigoAtirador tempInimigo2 = inimigosAtiradores.get(i);
                int a = player1.getVida();

                if (formaNave.intersects(formaInimigo2)) {
                    player1.somarScore();

                    if (a == 0) {
                        player1.setVisible(false);
                        break;
                    } else {
                        player1.setVida(a - 1);
                        tempInimigo2.setVisible(false);
                    }
                }

                ArrayList<TiroInimigo> tirosInimigo = tempInimigo2.getTirosInimigo();

                for (int j = 0; j < tirosInimigo.size(); j++) {
                    TiroInimigo tempTiroInimigo = tirosInimigo.get(j);
                    formaTiroInimigo = tempTiroInimigo.getBounds();

                    if (formaNave.intersects(formaTiroInimigo)) {
                        if (a == 0) {
                            player1.setVisible(false);
                            break;
                        } else {
                            player1.setVida(a - 1);
                            tempTiroInimigo.setVisible(false);
                        }
                    }
                }
            }
        }

        //Chefao & Seus Tiros, player1 e player2
        Chefao tempchefao = chefao;
        formaChefao = tempchefao.getBounds();

        if(player1.isVisible()) {
            if (formaNave.intersects(formaChefao)) {
                player1.setVida(player1.getVida() - 1);
                if (player1.getVida() < 0) {
                    player1.setVisible(false);
                }
            }
        }

        List<TiroChefao> tirosChefao = chefao.getTiroBoss();

        for(int i=0 ; i<tirosChefao.size() ; i++)
        {
            TiroChefao tempTiroChefao = tirosChefao.get(i);
            formaTiroChefao = tempTiroChefao.getBounds();

            if(player1.isVisible()) {
                if (formaNave.intersects(formaTiroChefao)) {
                    if (player1.getVida() == 0) {
                        player1.setVisible(false);
                        break;
                    } else if (player1.getVida() < 2) {
                        player1.setVida(0);
                        tempTiroChefao.setVisivel(false);
                    } else {
                        player1.setVida(player1.getVida() - 2);
                        tempTiroChefao.setVisivel(false);
                    }
                }
            }

        }

        // Tiro player e Inimigos
        List<Tiro> tiros = player1.getTiros();

        for (int i = 0; i < tiros.size(); i++) {
            Tiro temptiro = tiros.get(i);
            formaTiro = temptiro.getBounds();

            for (int j = 0; j < inimigosComuns.size(); j++) {
                InimigoComum tempRedUfo = inimigosComuns.get(j);
                formaInimigo1 = tempRedUfo.getBounds();

                if (formaTiro.intersects(formaInimigo1)) {
                    player1.somarScore();
                    tempRedUfo.setVisible(false);
                    temptiro.setVisible(false);
                    break;
                }

            }

            for (int j = 0; j < inimigosAtiradores.size(); j++) {
                InimigoAtirador tempGreenFire = inimigosAtiradores.get(j);
                formaInimigo2 = tempGreenFire.getBounds();

                if (formaTiro.intersects(formaInimigo2)) {
                    player1.somarScore();
                    tempGreenFire.setVisible(false);
                    temptiro.setVisible(false);
                    break;
                }
            }

            if (formaTiro.intersects(formaChefao)) {
                chefao.setHit(true);
                int a = tempchefao.getVida();
                if (a < 120) {
                    temptiro.setVisible(false);
                    tempchefao.setVida(a + 1);
                }
                if (a > 119) {
                    tempchefao.setVisivel(false);
                    temptiro.setVisible(false);
                    youWin = true;
                }
            }

            for(int j=0 ; j<tirosChefao.size() ; j++) {
                TiroChefao tempTiroChefao = tirosChefao.get(j);
                formaTiroChefao = tempTiroChefao.getBounds();

                if (formaTiro.intersects(formaTiroChefao)) {
                    tempTiroChefao.setVisivel(false);
                    temptiro.setVisible(false);
                }
            }
        }

        // Vidas spawnadas

        Rectangle formaPowerUpVida;

        for (int i = 0; i < vidas.size(); i++) {
            Vida tempPowerUpVida = vidas.get(i);
            formaPowerUpVida = tempPowerUpVida.getBounds();

            if(player1.isVisible()) {
                if (formaNave.intersects(formaPowerUpVida)) {
                    if (player1.getVida() < 5) {
                        player1.setVida(player1.getVida() + 1);
                        vidas.remove(i);
                    }
                    break;
                }
            }

        }
    }

    // Temporizador
    public void waitForSeconds() {
        Timer timer;
        Timer timer2;
        Timer timerChefao;
        Timer timerInimigos2;

        timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < explosoes.size(); i++) {
                    explosoes.get(i).setVisible(false);
                }

            }

        });
        timer.start();

        timer2 = new Timer(200, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (chefao.isHit() == true) {
                    chefao.setHit(false);

                }
            }

        });
        timer2.start();

        timerChefao = new Timer(100000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chefao.setVisivel(true);
            }
        });
        timerChefao.start();

        timerInimigos2  = new Timer(45000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inicializarInimigos2();
            }

        });
        timerInimigos2.start();
    }

    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if(player1.isVisible())
                player1.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
        }
    }

}
