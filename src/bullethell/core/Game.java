package bullethell.core;

import bullethell.game.Character;
import bullethell.game.characters.CharacterWithNoName;
import bullethell.game.enemies.EnemyWithNoName;
import bullethell.graphic.Renderer;
import bullethell.graphic.Window;
import bullethell.util.*;
import bullethell.util.lists.Cyclics;
import bullethell.util.lists.Timeds;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Game{
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    private Window window;

    private Renderer renderer;

    private Character player;
    private Cyclics enemies; //destroyables go here too
    //private Cyclics powerUps;
    private Timeds timeds;

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
                else if(key == GLFW_KEY_Z && action == GLFW_PRESS) player.shooting = true;
                else if(key == GLFW_KEY_Z && action == GLFW_RELEASE) player.shooting = false;
            }
        };
        window.setKeyCallback(keyCallback);

        renderer = new Renderer();
        renderer.init();

        player = new CharacterWithNoName(-0.5f, -0.5f);
        enemies = new Cyclics();
        timeds = new Timeds();

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

            while(accumulator >= interval){
                player.input(window.id);

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
        player.update(delta);
        enemies.update(delta);
        timeds.update();

        if(enemies.collided(player)) if(player.die()) running = false;
        enemies.handleCollisions(player, timeds);
    }

    private void render(float alpha){
        player.render(renderer, alpha);
        enemies.render(renderer, alpha);
        timeds.render(renderer);
    }

    private void wait(long start, long interval){
        while(System.currentTimeMillis() - start < interval)
            try{
                Thread.sleep(1);
            } catch(InterruptedException ignored){}
    }
}
