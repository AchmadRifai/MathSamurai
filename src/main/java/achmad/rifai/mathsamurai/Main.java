/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package achmad.rifai.mathsamurai;

import achmad.rifai.mathsamurai.util.Work;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ashura
 */
public class Main extends JFrame implements GameController.QuisOn{
    private GameView gv;
    private GameModel gm;
    private GameController gc;

    public Main() {
        super(Konstanta.NAME_GAME);
        gm=new GameModel();
        gv=new GameView(gm);
        gc=new GameController(gv, gm, this);
        buildGame();
    }

    public static void main(String[]args){
        System.out.println("Hallo");
        for(javax.swing.UIManager.LookAndFeelInfo i:javax.swing.UIManager.getInstalledLookAndFeels())if("swing".equals(i.getName()))
            try {
                javax.swing.UIManager.setLookAndFeel(i.getClassName());
                break;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(()->{
            new achmad.rifai.mathsamurai.ui.Dash().setVisible(true);
        });
    }

    private void buildGame() {
        setPreferredSize(new java.awt.Dimension(Konstanta.SCREEN_WIDTH, Konstanta.SCREEN_HEIGHT));
        add(gv, BorderLayout.CENTER);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                String nama=javax.swing.JOptionPane.showInputDialog("Masukan nama anda : ");
                while(!Work.oleh(nama))nama=javax.swing.JOptionPane.showInputDialog("Masukan nama anda : ");
                gc.setNama(nama);
                gc.start();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                gc.setRunning(false);
                back();
            }
        }); pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @Override
    public void quisJalan() {
        SoalKata sk=new achmad.rifai.mathsamurai.SoalKata(this, true, gv, gm);
        sk.setVisible(true);
        while(sk.isVisible()){}
    }

    @Override
    public void back() {
        new achmad.rifai.mathsamurai.ui.Dash().setVisible(true);
        this.setVisible(false);
    }
}