package bullethell.core;

import bullethell.game.Character;
import bullethell.game.Explosion;
import bullethell.game.characters.CharacterWithNoName;
import bullethell.game.enemies.EnemyWithNoName;
import bullethell.graphic.Renderer;
import bullethell.graphic.Window;
import bullethell.util.*;
import bullethell.util.lists.Bullets;
import bullethell.util.lists.Entities;
import bullethell.util.lists.Explosions;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;

public class Game{
    private GLFWErrorCallback errorCallback;

    private Window window;

    private Renderer renderer;

    private Character player;
    private Bullets playerBullets;
    private Entities enemies;
    private Bullets bullets;
    //private Solids powerUps;
    //private Solids destroyables;
    private Explosions explosions;

    private Timer timer;

    private static final float TARGET_UPS = 60f;

    public static void main(String[] args){
        new Game().start();
    }

    private void start(){
        init();
        loop();
        dispose();
    }

    private void init(){
        errorCallback = GLFWErrorCallback.createPrint();
        glfwSetErrorCallback(errorCallback);

        /* Initialize GLFW */
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW!");
        }

        /* Create GLFW window */
        window = new Window(600, 600, "Bullet Hell");

        renderer = new Renderer();
        renderer.init();

        player = new CharacterWithNoName(-0.5f, -0.5f);
        playerBullets = new Bullets();
        enemies = new Entities();
        bullets = new Bullets();
        explosions = new Explosions();

        //this will not exist
        enemies.add(new EnemyWithNoName(0.5f, 0.5f));
        enemies.add(new EnemyWithNoName(-0.5f, 0.5f));
        enemies.add(new EnemyWithNoName(0f, 0.5f));

        timer = new Timer();
        timer.init();
    }

    private void loop(){
        float delta;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;
        float alpha;

        while(!window.isClosing()){

            delta = timer.getDelta();
            accumulator += delta;

            player.input(window.id);

            while(accumulator >= interval){
                update(delta);

                timer.updateUPS();
                accumulator -= interval;
            }

            alpha = accumulator / interval; //for interpolation

            player.render(renderer);
            playerBullets.render(renderer);
            enemies.render(renderer);
            bullets.render(renderer);
            explosions.render(renderer);

            renderer.draw();

            timer.updateFPS();

            timer.update();

            window.update();

            System.out.println("FPS: " + timer.getFPS() + "  --  UPS: " + timer.getUPS());

            //maybe put a fps limiter here... just maybe...
        }
    }

    private void dispose(){
        renderer.dispose();
        window.dispose();
    }

    private void update(float delta){
        player.update(playerBullets);
        playerBullets.update();
        enemies.update(bullets);
        bullets.update();
        explosions.update();

        if(enemies.collided(player) || bullets.collided(player))
            explosions.add(new Explosion(0f, 0f, 1f, 27, 16, 100){
                @Override
                public void drawHitRadius(Renderer renderer){
                }
            });
        enemies.handleCollisions(playerBullets, explosions);
    }

    private void wait(long start, long interval){
        while(System.currentTimeMillis() - start < interval)
            try{
                Thread.sleep(1);
            } catch(InterruptedException ignored){}
    }
}
