/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai.obj;

import achmad.rifai.mathsamurai.Konstanta;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ashura
 */
public class Dan {
    private BufferedImage img;
    private Rectangle2D hitbox,screen;
    private int x,y,width,height;
    private boolean show;

    public Dan(Rectangle2D hitbox, Rectangle2D screen) {
        this.hitbox = hitbox;
        this.screen = screen;
        loadImg();
    }

    private void loadImg() {
        try {
            img=ImageIO.read(getClass().getResource(Konstanta.LEVEL_UP));
        } catch (IOException ex) {
            Logger.getLogger(Dan.class.getName()).log(Level.SEVERE, null, ex);
        } x=(int) hitbox.getX();
        y=(int) hitbox.getY();
        width=(int) hitbox.getWidth();
        height=(int) hitbox.getHeight();
        show=false;
    }

    public BufferedImage getImg() {
        return img;
    }

    public Rectangle2D getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle2D hitbox) {
        this.hitbox = hitbox;
        x=(int) hitbox.getX();
        y=(int) hitbox.getY();
        width=(int) hitbox.getWidth();
        height=(int) hitbox.getHeight();
    }

    public Rectangle2D getScreen() {
        return screen;
    }

    public void setScreen(Rectangle2D screen) {
        this.screen = screen;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        hitbox.setRect(x, y, width, height);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        hitbox.setRect(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        hitbox.setRect(x, y, width, height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        hitbox.setRect(x, y, width, height);
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}