
package gui;

import components.StickFigure;
import data.KeyBindings;
import data.Map;
import master.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by axu047 on 5/5/2017.
 */
public class Game extends JPanel {
    private Map map;
    private StickFigure player;
    private final double FRAMES_PER_SECOND = 500;
    private Timer timer;
    private Player p1;

    public Game(Window parent, Map m, KeyBindings k) {
        setSize(1000, 1000);

        map = m;
        player = new StickFigure(map.getStartX(), map.getStartY(), map);
        p1 = new Player(player, k);
        start();
        repaint();
    }

    public void start() {
        addMouseListener(p1);
        addKeyListener(p1);
        ActionListener al = ae -> repaint();
        timer = new Timer((int) (1000.0 / FRAMES_PER_SECOND), al);
        timer.start();
    }

    public void stop() {
        if (timer != null) timer.stop();
        removeMouseListener(p1);
        removeKeyListener(p1);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g);
        map.draw(g2, FRAMES_PER_SECOND);
        player.draw(g2, FRAMES_PER_SECOND);

    }
}