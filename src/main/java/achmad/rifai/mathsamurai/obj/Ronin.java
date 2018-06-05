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
public class Ronin {
    private java.awt.image.BufferedImage died,injured,idle,left,right,kiri1,kiri2,kiri3;
    private Rectangle2D hitbox,screen;
    private boolean kanan;
    private int x,y,width,height,mode;
    private State state;

    public Ronin(Rectangle2D hitbox, Rectangle2D screen) {
        this.hitbox = hitbox;
        this.screen = screen;
        loadImg();
    }

    private void loadImg() {
        try {
            died=ImageIO.read(getClass().getResource(Konstanta.RAMPOK_DIED));
            injured=ImageIO.read(getClass().getResource(Konstanta.RAMPOK_INJURED));
            idle=ImageIO.read(getClass().getResource(Konstanta.RAMPOK_STAY));
            left=ImageIO.read(getClass().getResource(Konstanta.RAMPOK_LEFT));
            right=ImageIO.read(getClass().getResource(Konstanta.RAMPOK_RIGHT));
            kiri1=ImageIO.read(getClass().getResource(Konstanta.RAMPOK_WIN1));
            kiri2=ImageIO.read(getClass().getResource(Konstanta.RAMPOK_WIN2));
            kiri3=ImageIO.read(getClass().getResource(Konstanta.RAMPOK_WIN3));
        } catch (IOException ex) {
            Logger.getLogger(Ronin.class.getName()).log(Level.SEVERE, null, ex);
        } x=(int) hitbox.getX();
        y=(int) hitbox.getY();
        width=(int) hitbox.getWidth();
        height=(int) hitbox.getHeight();
        kanan=false;
        state=State.IDLE;
        mode=1;
    }

    public void jalan(){
        setX(x-20);
        kanan=!kanan;
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

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State{
        DIED,RUN,WIN,LOSE,IDLE
    }

    public java.awt.Image getImg(){
        if(null!=state)switch (state) {
            case DIED:
                return died;
            case IDLE:
                return idle;
            case LOSE:
                return injured;
            case WIN:
                switch (mode) {
                    case 1:
                        return kiri1;
                    case 2:
                        return kiri2;
                    case 3:
                        return kiri3;
                    default:
                        break;
                }   break;
            case RUN:
                if(kanan)return right;
                else return left;
            default:
                break;
        } return null;
    }
}