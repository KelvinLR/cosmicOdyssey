package Objects;

import java.awt.image.BufferedImage;

public class Objects {
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected BufferedImage sprite;

    public Objects(double x, double y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        
    }
}
