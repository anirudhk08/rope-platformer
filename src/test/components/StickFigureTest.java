package test.components;

import components.Obstacle;
import components.Platform;
import components.StickFigure;
import data.KeyBindings;
import data.Level;
import menus.Game;
import menus.GameLevelConstants;
import menus.PhysicsConstants;
import menus.Window;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by adamj on 5/29/2017.
 */

public class StickFigureTest {
    private StickFigure player;

    @Before
    public void init() {
        KeyBindings k = new KeyBindings();
        Window w = new Window(k);
        w.setVisible(false);
        w.startGame(new Level(0, 0, 0, 0));

        Game g = (Game) w.getContentPane().getComponent(0);
        player = g.getStickFigure();
    }

    @Test
    public void jump() throws Exception {
        player.jump();
        assertNotEquals(-4.5, player.getyVel());
        for (int i = 0; i < 1000; i++)
            player.updatePos();
        player.jump();
        assertEquals(-4.5, player.getyVel(), 0);
    }

    @Test
    public void moveRight() throws Exception {
        player.moveRight();
        assertNotEquals(1, player.getxVel());
        for (int i = 0; i < 1000; i++)
            player.updatePos();
        player.moveRight();
        assertEquals(1, player.getxVel(), 0);
    }

    @Test
    public void moveLeft() throws Exception {
        player.moveLeft();
        assertNotEquals(-1, player.getxVel());
        for (int i = 0; i < 1000; i++)
            player.updatePos();
        player.moveLeft();
        assertEquals(-1, player.getxVel(), 0);
    }

    @Test
    public void swing() throws Exception {
        player.swing(1000, 1000);
        assertNotNull(player.getRope());
        player.jump();
        assertNull(player.getRope());
        for (int i = 0; i < 1000; i++)
            player.updatePos();
        player.swing(1000, 1000);
        player.updatePos();
        assertNull(player.getRope());
    }

    @Test
    public void grapple() throws Exception {
        player.grapple(1000, 0);
        assertNotNull(player.getRope());
        player.jump();
        assertNull(player.getRope());
        for (int i = 0; i < 1000; i++)
            player.updatePos();
        double yPos = player.getyPos();
        player.grapple(1000, 0);
        player.updatePos();
        assertNotEquals(yPos, player.getyPos());
    }

    @Test
    public void stopMoving() throws Exception {
        for (int i = 0; i < 1000; i++)
            player.updatePos();
        player.moveRight();
        player.updatePos();
        player.stopMoving();
        assertEquals(0, player.getxVel(), 0);
    }


    @Test
    public void restart() throws Exception {
        player.restart();
        assertEquals(GameLevelConstants.LEVEL_STARAT_X_COORD, player.getxPos(), 0);
        assertEquals(GameLevelConstants.LEVEL_STARAT_Y_COORD, player.getyPos(), 0);
        assertEquals(PhysicsConstants.G, player.getyVel(), 0);
        assertEquals(0, player.getxVel(), 0);
        assertNull(player.getRope());
    }

    @Test
    public void updateMap() throws Exception {
        Level l = new Level(0, 0, 0, 0);
        player.updateMap(l);
        assertEquals(player.getMap(), l);
    }

}
