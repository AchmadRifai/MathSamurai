/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai.ui;

import achmad.rifai.mathsamurai.Konstanta;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ashura
 */
public class DashView extends javax.swing.JPanel{

    public DashView() {
        initView();
    }

    private void initView() {
        setPreferredSize(new java.awt.Dimension(Konstanta.SCREEN_WIDTH, Konstanta.SCREEN_HEIGHT));
        setDoubleBuffered(true);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(getClass().getResource(Konstanta.DASH_URL)), 0, 0, Konstanta.SCREEN_WIDTH, Konstanta.SCREEN_HEIGHT, this);
        } catch (IOException ex) {
            Logger.getLogger(DashView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}