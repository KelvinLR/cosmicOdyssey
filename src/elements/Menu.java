package elements;
import meujogo.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu extends JPanel implements ActionListener {
	
	private int currentOption = 0;
	private int maxOption = 2;
	
	private boolean up, down, enter;
	private Image player, background;

	private Container container;

	public Menu(Container container) {	
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon ref = new ImageIcon(getClass().getClassLoader().getResource("res/pixel art _ Tumblr.gif"));
        background = ref.getImage();
		ref = new ImageIcon(getClass().getClassLoader().getResource("res/spaceship2_small.png"));
		player = ref.getImage();

		addKeyListener(new TecladoAdapter());

		Timer timer = new Timer(1000 / 60, this);
        timer.start();

		this.container = container;
	}
	
	public void tick() {
		if(up) {
			up = false;
			currentOption--;

			if(currentOption < 0)
				currentOption = maxOption;
		} 
		
		if(down) {
			down = false;
			currentOption++;

			if(currentOption > maxOption)
				currentOption = 0;
		} 
		
		if(enter) {
			enter = false;
			
			if(currentOption == 0) {
				Container.gameState = "JOGO";
				new Container(2);
				container.dispose();
			} else if(currentOption == 1) {
				//Modo Versus
			} else if(currentOption == 2) {
				System.exit(0);
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Minecraft", Font.BOLD, 35));

		//Font minecraftFont = carregarFonte();
        //g.setFont(minecraftFont);

		g.drawString("COSMIC", (Container.WIDTH/4) - 74, 100);
		g.drawString("ODYSSEY", (Container.WIDTH/4) - 87, 145);

		g.setFont(new Font("Minecraft", 3, 18));

		g.drawString("COOPERATIVO", (Container.WIDTH/4) - 75, 240);
		g.drawString("VERSUS", (Container.WIDTH/4) - 46, 270);
		g.drawString("SAIR", (Container.WIDTH/4) - 28, 300);
		
		g.setColor(Color.YELLOW);

		if(currentOption == 0) {
			g.drawImage(player, (Container.WIDTH/4) - 112, 225, null);
			g.drawString("COOPERATIVO", (Container.WIDTH/4) - 75, 240);
		}

		if(currentOption == 1) {
			g.drawImage(player, (Container.WIDTH/4) - 83, 255, null);
			g.drawString("VERSUS", (Container.WIDTH/4) - 46, 270);
		}

		if(currentOption == 2) {
			g.drawImage(player, (Container.WIDTH/4) - 65, 285, null);
			g.drawString("SAIR", (Container.WIDTH/4) - 28, 300);
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(background, 0, 0, null);
		g2.scale(2, 2);

		if(Container.gameState.equals("MENU")) {
			render(g2);
		}

		g.dispose();
	}

	@Override
    public void actionPerformed(ActionEvent e) {
		if(Container.gameState == "MENU") {
			tick();
		}
		
		repaint();
    }

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				if(Container.gameState == "MENU") {
					up = true;
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				if(Container.gameState == "MENU") {
					down = true;
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(Container.gameState == "MENU") {
					enter = true;
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
				if(Container.gameState == "MENU") {
					up = false;
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
				if(Container.gameState == "MENU") {
					down = false;
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				if(Container.gameState == "MENU") {
					enter = false;
				}
			}
		}
	}
}
