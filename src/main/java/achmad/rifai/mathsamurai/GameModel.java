/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai;

import achmad.rifai.mathsamurai.obj.*;
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
public class GameModel {
    private java.awt.image.BufferedImage bg;
    private java.util.List<Bumi>bl;
    private Rectangle2D screen;
    private Samurai player;
    private Kenjutsu damage,attack;
    private Dan up;
    private Ronin musuh;
    private State state;

    public GameModel() {
        initBG();
        state=State.RUN;
    }

    private void initBG() {
        try {
            bg=ImageIO.read(getClass().getResource(Konstanta.BG_URL));
        } catch (IOException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setScreen(Rectangle2D screen) {
        this.screen = screen;
        bl=new java.util.LinkedList<>();
        Bumi b1=new Bumi(new java.awt.Rectangle(0, (int) (screen.getHeight()/2)-130, (int) screen.getWidth(), (int) (screen.getHeight()/2)+100),screen),
                    b2=new Bumi(new java.awt.Rectangle((int) b1.getHitbox().getWidth(), (int) (screen.getHeight()/2)-130, (int) screen.getWidth(), 
                            (int) (screen.getHeight()/2)+100),screen);
        player=new Samurai(new java.awt.Rectangle(50, (int) (screen.getHeight()/3)+100, 200, (int) (screen.getWidth()/10)), screen);
        bl.add(b1);
        bl.add(b2);
        damage=new Kenjutsu(player.getHitbox(), screen);
        up=new Dan(player.getHitbox(), screen);
        musuh=new Ronin(new java.awt.Rectangle((int) screen.getWidth()-200, (int) (screen.getHeight()/3)+100, 200, (int) (screen.getWidth()/10)), screen);
        attack=new Kenjutsu(musuh.getHitbox(), screen);
    }

    public BufferedImage getBg() {
        return bg;
    }

    public void setBg(BufferedImage bg) {
        this.bg = bg;
    }

    public Rectangle2D getScreen() {
        return screen;
    }

    public java.util.List<Bumi> getBl() {
        return bl;
    }

    public void setBl(java.util.List<Bumi> bl) {
        this.bl = bl;
    }

    public void jalan() {
        bl.forEach((b)->{
            b.jalan();
            if(b.getX()+b.getWidth()==0&&b==bl.get(0))b.setX(0);
        });bl.get(1).setX(bl.get(0).getX()+bl.get(0).getWidth());
        player.jalan();
        musuh.jalan();
        if(player.getWidth()+player.getX()>=musuh.getX()&&
                musuh.getState()!=Ronin.State.DIED)state=State.ONQUIS;
        if(musuh.getX()+musuh.getWidth()<0)musuh=new Ronin(new java.awt.Rectangle((int) screen.getWidth()-200, 
                (int) (screen.getHeight()/3)+100, 200, (int) (screen.getWidth()/10)), screen);
        aturMode();
    }

    public Samurai getPlayer() {
        return player;
    }

    public void setPlayer(Samurai player) {
        this.player = player;
    }

    public Kenjutsu getDamage() {
        return damage;
    }

    public void setDamage(Kenjutsu damage) {
        this.damage = damage;
    }

    public Kenjutsu getAttack() {
        return attack;
    }

    public void setAttack(Kenjutsu attack) {
        this.attack = attack;
    }

    public Dan getUp() {
        return up;
    }

    public void setUp(Dan up) {
        this.up = up;
    }

    public Ronin getMusuh() {
        return musuh;
    }

    public void setMusuh(Ronin musuh) {
        this.musuh = musuh;
    }

    private void aturMode() {
        if(null!=state)switch (state) {
            case RUN:
                player.setState(Samurai.State.RUN);
                if(musuh.getState()!=Ronin.State.DIED)musuh.setState(Ronin.State.RUN);
                break;
            case PAUSE:
                player.setState(Samurai.State.IDLE);
                if(musuh.getState()!=Ronin.State.DIED)musuh.setState(Ronin.State.IDLE);
                break;
            case LOSE:
                if(player.getState()!=Samurai.State.IDLE)player.setState(Samurai.State.DIED);
                if(musuh.getState()!=Ronin.State.DIED)musuh.setState(Ronin.State.WIN);
                break;
            case WIN:
                player.setState(Samurai.State.WIN);
                musuh.setState(Ronin.State.DIED);
                break;
            case ONQUIS:
                player.setState(Samurai.State.ONQUIS);
                if(musuh.getState()!=Ronin.State.DIED)musuh.setState(Ronin.State.WIN);
                else{
                    state=State.RUN;
                    aturMode();
                }   break;
            default:
                break;
        }
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        aturMode();
    }

    public enum State{
        PAUSE,RUN,LOSE,ONQUIS,WIN
    }
}