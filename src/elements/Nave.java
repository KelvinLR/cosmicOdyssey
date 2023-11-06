package elements;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public abstract class Nave {

	public abstract void load();

	public abstract void update();

	public abstract Rectangle getBounds();

}