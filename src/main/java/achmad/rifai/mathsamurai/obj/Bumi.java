/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai.obj;

import achmad.rifai.mathsamurai.Konstanta;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ashura
 */
public class Bumi {
    private java.awt.image.BufferedImage img;
    private Rectangle2D hitbox,screen;
    private int speed,x,y,width,height;

    public Bumi(Rectangle2D hitbox, Rectangle2D screen) {
        this.hitbox = hitbox;
        this.screen = screen;
        genImg();
    }

    private void genImg() {
        try {
            img=ImageIO.read(getClass().getResource(Konstanta.BUMI_URL));
        } catch (IOException ex) {
            Logger.getLogger(Bumi.class.getName()).log(Level.SEVERE, null, ex);
        } speed=12;
        x=(int) hitbox.getX();
        y=(int) hitbox.getY();
        width=(int) hitbox.getWidth();
        height=(int) hitbox.getHeight();
    }

    public java.awt.image.BufferedImage getImg() {
        return img;
    }

    public void setImg(java.awt.image.BufferedImage img) {
        this.img = img;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void jalan(){
        setX(x-speed);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        hitbox.setRect(x, hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        hitbox.setRect(hitbox.getX(), y, hitbox.getWidth(), hitbox.getHeight());
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        hitbox.setRect(hitbox.getX()-speed, hitbox.getY(), width, hitbox.getHeight());
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        hitbox.setRect(hitbox.getX()-speed, hitbox.getY(), hitbox.getWidth(), height);
    }
}