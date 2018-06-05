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
public class Samurai {
    private java.awt.image.BufferedImage idle,slash1,slash2,slash3,walk_right,walk_left,died;
    private Rectangle2D hitbox,screen;
    private int x,y,width,height,kiri=1;
    private boolean kanan;
    private State state;
    private int level=1,nyawa=5,exp=0,batas_exp=100,gold=0;

    public Samurai(Rectangle2D hitbox, Rectangle2D screen) {
        this.hitbox = hitbox;
        this.screen = screen;
        loadImage();
        kanan=false;
        state=State.IDLE;
    }

    public java.awt.image.BufferedImage getImg(){
        if(null!=state)switch (state) {
            case IDLE:
                return idle;
            case RUN:
                if(kanan){
                    return walk_right;
                }else{
                    return walk_left;
                }
            case WIN:
        switch (kiri) {
            case 1:
                return slash1;
            case 2:
                return slash2;
            case 3:
                return slash3;
            default:
                break;
        }
            case ONQUIS:
                return slash1;
            case DIED:
                return died;
            default:
                break;
        } return null;
    }

    private void loadImage() {
        try {
            idle=ImageIO.read(getClass().getResource(Konstanta.SAMURAI_STAND));
            slash1=ImageIO.read(getClass().getResource(Konstanta.SAMURAI_FIRST_KIRI));
            slash2=ImageIO.read(getClass().getResource(Konstanta.SAMURAI_SECOND_KIRI));
            slash3=ImageIO.read(getClass().getResource(Konstanta.SAMURAI_LAST_KIRI));
            walk_right=ImageIO.read(getClass().getResource(Konstanta.SAMURAI_RIGHT));
            walk_left=ImageIO.read(getClass().getResource(Konstanta.SAMURAI_LEFT));
            died=ImageIO.read(getClass().getResource(Konstanta.SAMURAI_DIED));
        } catch (IOException ex) {
            Logger.getLogger(Samurai.class.getName()).log(Level.SEVERE, null, ex);
        } x=(int) hitbox.getX();
        y=(int) hitbox.getY();
        width=(int) hitbox.getWidth();
        height=(int) hitbox.getHeight();
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

    public int getKiri() {
        return kiri;
    }

    public void setKiri(int kiri) {
        this.kiri = kiri;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        RUN,DIED,ONQUIS,IDLE,WIN
    }

    public void jalan(){
        kanan = !kanan;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getNyawa() {
        return nyawa;
    }

    public void setNyawa(int nyawa) {
        this.nyawa = nyawa;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        if(exp>=batas_exp){
            int b=batas_exp;
            setLevel(level+1);
            setBatas_exp(batas_exp*2);
            setExp(exp-b);
        }
    }

    public int getBatas_exp() {
        return batas_exp;
    }

    public void setBatas_exp(int batas_exp) {
        this.batas_exp = batas_exp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}