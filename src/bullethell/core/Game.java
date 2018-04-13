package bullethell.core;

import bullethell.game.Character;
import bullethell.game.characters.CharacterWithNoName;
import bullethell.game.enemies.EnemyWithNoName;
import bullethell.graphic.Renderer;
import bullethell.graphic.Window;
import bullethell.util.*;
import bullethell.util.lists.Bullets;
import bullethell.util.lists.Entities;
import bullethell.util.lists.Explosions;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Game{
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

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

    private boolean running;

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

        keyCallback = new GLFWKeyCallback(){
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods){
                if(key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) glfwSetWindowShouldClose(window, true);
                else if(key == GLFW_KEY_Z && action == GLFW_PRESS) player.setShooting(true);
                else if(key == GLFW_KEY_Z && action == GLFW_RELEASE) player.setShooting(false);
            }
        };
        window.setKeyCallback(keyCallback);

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

        running = true;

        timer = new Timer();
        timer.init();
    }

    private void loop(){
        float delta;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;
        float alpha;

        while(running){
            if(window.isClosing()) running = false;

            delta = timer.getDelta();
            accumulator += delta;

//            player.input(window.id);

            while(accumulator >= interval){
                player.input(window.id); //should not be here?

                update(interval);

                timer.updateUPS();
                accumulator -= interval;
            }

            alpha = accumulator / interval;

            render(alpha);
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
        player.update(playerBullets, delta);
        playerBullets.update(delta);
        enemies.update(bullets, delta);
        bullets.update(delta);
        explosions.update();

        if(enemies.collided(player) || bullets.collided(player, false));
        enemies.handleCollisions(playerBullets, explosions);
    }

    private void render(float alpha){
        player.render(renderer, alpha);
        playerBullets.render(renderer, alpha);
        enemies.render(renderer, alpha);
        bullets.render(renderer, alpha);
        explosions.render(renderer);
    }

    private void wait(long start, long interval){
        while(System.currentTimeMillis() - start < interval)
            try{
                Thread.sleep(1);
            } catch(InterruptedException ignored){}
    }
}
