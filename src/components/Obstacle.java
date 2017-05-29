package components;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static menus.GameLevelConstants.*;
import static menus.GameWindowConstants.WINDOW_HEIGHT;
import static menus.GameWindowConstants.WINDOW_WIDTH;

/**
 * Created by Anirudh on 5/27/2017.
 */
public class Obstacle extends GameComponent {

    private Color color;
    private boolean move;


    public Obstacle(double startX, double startY, Color color, int width, int height, boolean move) {
        super(startX, startY, width, height);

        this.color = color;
        this.move = move;
        this.harmful = true;
    }


    public static ArrayList<Obstacle> createObstacles(int gameLevel)
    {
        ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();

        switch (gameLevel)
        {
            case LEVEL_INTRO:
                createIntroObstacles(obstacles);
                break;
            case LEVEL_GOOD:
                createGoodObstacles(obstacles);
                break;
            case LEVEL_COOL:
                createCoolObstacles(obstacles);
                break;
            case LEVEL_SCARY:
                createScaryObstacles(obstacles);
                break;
            case LEVEL_UNBEARABLE:
                createUnbearableObstacles(obstacles);
                break;
            case LEVEL_FINAL:
                createFinalObstacles(obstacles);
                break;
            default:
                break;
        }

        return obstacles;
    }


    private static void createIntroObstacles(ArrayList<Obstacle> obstacles) {
        obstacles.add(new Obstacle(500, 50, Color.BLUE, 150, 75, true));
    }

    private static void createGoodObstacles(ArrayList<Obstacle> obstacles) {

        obstacles.add(new Obstacle(100, 620, Color.BLUE, 150, 75, true));
        obstacles.add(new Obstacle(500, 100, Color.RED, 150, 100, true));
        obstacles.add(new Obstacle(750, 620, Color.YELLOW, 100, 100, true));

    }

    private static void createCoolObstacles(ArrayList<Obstacle> obstacles)
    {
        obstacles.add(new Obstacle(100, 620, Color.BLUE, 75, 75, true));
        obstacles.add(new Obstacle(500, 100, Color.RED, 75, 75, true));
        obstacles.add(new Obstacle(600, 620, Color.YELLOW, 75, 75, true));
        obstacles.add(new Obstacle(900, 250, Color.GREEN, 75, 75, true));

    }

    private static void createScaryObstacles(ArrayList<Obstacle> obstacles)
    {
        obstacles.add(new Obstacle(100, 620, Color.BLUE, 150, 75, true));
        obstacles.add(new Obstacle(500, 100, Color.RED, 150, 100, true));
        obstacles.add(new Obstacle(600, 620, Color.YELLOW, 100, 100, true));
        obstacles.add(new Obstacle(900, 250, Color.GREEN, 75, 150, true));
    }


    private static void createUnbearableObstacles(ArrayList<Obstacle> obstacles)
    {
        obstacles.add(new Obstacle(100, 100, Color.YELLOW, 70, 60, true));
        obstacles.add(new Obstacle(120, 600, Color.RED, 60, 80, true));
        obstacles.add(new Obstacle(500, 540, Color.BLUE, 70, 70, true));
        obstacles.add(new Obstacle(640, 200, Color.ORANGE, 50, 50, true));
        obstacles.add(new Obstacle(800, 540, Color.CYAN, 90, 90, true));
    }
    private static void createFinalObstacles(ArrayList<Obstacle> obstacles)
    {
        obstacles.add(new Obstacle(200, 100, Color.CYAN, 50, 50, true));
        obstacles.add(new Obstacle(310, 100, Color.RED, 50, 50, true));
        obstacles.add(new Obstacle(410, 100, Color.YELLOW, 50, 50, true));
        obstacles.add(new Obstacle(100, 600, Color.BLUE, 50, 50, true));
        obstacles.add(new Obstacle(200, 400, Color.ORANGE, 50, 50, true));
        obstacles.add(new Obstacle(700, 100, Color.MAGENTA, 50, 50, true));
        obstacles.add(new Obstacle(700, 500, Color.GREEN, 50, 50, true));
    }


    public boolean canMove() {
        return move;
    }

    private int getRandomDirection()
    {
        int rand =  new Random().nextInt(7) - 3;
        return rand;
    }

    public void updatePosition()
    {
        if (move)
        {
            double xOrig = xPos;
            double yOrig = yPos;


            xPos += getRandomDirection();
            yPos += getRandomDirection();

            if (getLeftEdge() < 0 || getRightEdge() > WINDOW_WIDTH
                    || getTopEdge() < 0 || getBottomEdge() > WINDOW_HEIGHT)
            {
                xPos = xOrig;
                yPos = yOrig;
            }

        }

    }

    public void updatePosition(double xPos, double yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void draw(Graphics2D g)
    {
        g.setColor(color);
        g.fillRect(getLeftEdge(), getTopEdge(), width, height);
    }
}