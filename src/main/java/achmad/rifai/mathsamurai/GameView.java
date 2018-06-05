/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ashura
 */
public class GameView extends javax.swing.JPanel{
    private GameModel gm;

    public GameView(GameModel gm) {
        this.gm = gm;
        initView();
    }

    private void initView() {
        setPreferredSize(new java.awt.Dimension(Konstanta.SCREEN_WIDTH, Konstanta.SCREEN_HEIGHT));
        setDoubleBuffered(true);
        setFocusable(true);
        gm.setScreen(new java.awt.Rectangle(0, 0, Konstanta.SCREEN_WIDTH, Konstanta.SCREEN_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(gm.getBg(), 0, 0, Konstanta.SCREEN_WIDTH, Konstanta.SCREEN_HEIGHT, this);
        gm.getBl().forEach((b) -> {
            g.drawImage(b.getImg(), b.getX(), b.getY(), b.getWidth(), b.getHeight(), this);
        }); g.drawImage(gm.getPlayer().getImg(), gm.getPlayer().getX(), gm.getPlayer().getY(), gm.getPlayer().getWidth(), 
                gm.getPlayer().getHeight(), this);
        g.drawImage(gm.getMusuh().getImg(), gm.getMusuh().getX(), gm.getMusuh().getY(), gm.getMusuh().getWidth(), gm.getMusuh().getHeight(), this);
        if(gm.getDamage().isShowing())g.drawImage(gm.getDamage().getImg(), gm.getDamage().getX(), gm.getDamage().getY(), 
                gm.getDamage().getWidth(), gm.getDamage().getHeight(), this);
        if(gm.getUp().isShow())g.drawImage(gm.getUp().getImg(), gm.getUp().getX(), gm.getUp().getY(), gm.getUp().getWidth(), 
                gm.getUp().getHeight(), this);
        if(gm.getAttack().isShowing())g.drawImage(gm.getAttack().getImg(), gm.getAttack().getX(), gm.getAttack().getY(), 
                gm.getAttack().getWidth(), gm.getAttack().getHeight(), this);
        g.setColor(Color.red);
        g.drawRect(10, 10, 50, 10);
        g.fillRect(10, 10, 10*gm.getPlayer().getNyawa(), 10);
        g.setColor(Color.GREEN);
        g.drawRect(70, 10, 50*gm.getPlayer().getBatas_exp()/gm.getPlayer().getBatas_exp(), 10);
        g.fillRect(70, 10, 50*gm.getPlayer().getExp()/gm.getPlayer().getBatas_exp(), 10);
    }
}