/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai;

import achmad.rifai.mathsamurai.util.Work;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author ashura
 */
public class GameController extends Thread{
    private GameView gv;
    private GameModel gm;
    private String nama;
    private boolean running;
    private QuisOn qo;
    private javazoom.jl.player.Player p;

    public GameController(GameView gv, GameModel gm, QuisOn qo) {
        this.gv = gv;
        this.gm = gm;
        this.qo=qo;
        running=true;
    }

    @Override
    public void run() {
        while(running){
            if(gm.getState()==GameModel.State.RUN)mlaku();
            if(!running)break;
            if(gm.getState()==GameModel.State.LOSE)kalah();
            if(!running)break;
            if(gm.getState()==GameModel.State.PAUSE)pause();
            if(!running)break;
            if(gm.getState()==GameModel.State.WIN)menang();
            if(!running)break;
            if(gm.getState()==GameModel.State.ONQUIS)quis();
            if(!running)break;
        } Work.simpan(nama,gm.getPlayer());
    }

    private void mlaku() {
        try {
            p=new javazoom.jl.player.Player(getClass().getResourceAsStream(Konstanta.WALK_SOUND));
        } catch (JavaLayerException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }if(p!=null)new Thread(()->{
            try {
                p.play();
            } catch (JavaLayerException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
        gm.jalan();
        gv.repaint();
        while(!p.isComplete()){}
    }

    private void kalah() {
        gv.repaint();
        running=false;
    }

    private void pause() {
        gv.repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void menang() {
        return;
    }

    private void quis() {
        try {
            p=new javazoom.jl.player.Player(getClass().getResourceAsStream(Konstanta.BATTLE_SOUND));
            p.play();
        } catch (JavaLayerException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        } qo.quisJalan();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public interface QuisOn{
        void quisJalan();
        void back();
    }
}