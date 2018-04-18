package bullethell.core;

import bullethell.game.Animated;
import bullethell.game.Character;
import bullethell.game.Enemy;
import bullethell.game.characters.CharacterWithNoName;
import bullethell.game.enemies.EnemyWithNoName;
import bullethell.graphic.Canvas;
import bullethell.graphic.Window;
import bullethell.util.*;
import bullethell.util.lists.Enemies;
import bullethell.util.lists.PowerUps;
import bullethell.util.lists.Solids;
import bullethell.util.lists.Timeds;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;

import static bullethell.graphic.Renderer.renderer;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Game{
    private GLFWErrorCallback errorCallback;
    private GLFWKeyCallback keyCallback;

    private Window window;

    private Character player;
    private Enemies enemies;
    private Solids solids;
    private PowerUps powerUps;
    private Timeds timeds;

    private Timer timer;

    private boolean running;

    private static final float TARGET_UPS = 60f;

    public static void main(String[] args){
        new Game().start();
    }

    private void start(){
        init();
        setup();
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
        window = new Window(720, 640, "Bullet Hell");

        keyCallback = new GLFWKeyCallback(){
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods){
                if(key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) glfwSetWindowShouldClose(window, true);
                else if(key == GLFW_KEY_Z && action == GLFW_PRESS) player.shooting = true;
                else if(key == GLFW_KEY_Z && action == GLFW_RELEASE) player.shooting = false;
            }
        };
        window.setKeyCallback(keyCallback);

        renderer.init();
    }

    private void setup(){
        player = new CharacterWithNoName(4.5f, 2f);
        enemies = new Enemies();
        solids = new Solids();
        powerUps = new PowerUps();
        timeds = new Timeds();

        player.canvas = new Canvas(-.5f);

        Animated.renderer = renderer;
        Enemy.solids = solids;

        Animated.character = player;

        //this will not exist
        enemies.add(new EnemyWithNoName(4.5f, 14f));
        //enemies.add(new SolidWithNoName(0f, 0f));

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
        solids.update(delta);
        powerUps.update(delta);
        timeds.update();

        if(enemies.collided(player) || solids.collided(player)) if(player.die()) running = false;
        enemies.handleCollisions(player, timeds);
        solids.handleCollisions(player, timeds);
    }

    private void render(float alpha){
        renderer.clearBuffers();

        //Draw without test and don't write to Stencil Buffer
        glStencilFunc(GL_ALWAYS, 1, 0xff);
        glStencilMask(0xff);

        player.canvas.drawBackground();
        renderer.draw();

        //Draw with Stencil test
        glStencilFunc(GL_EQUAL, 1, 0xff);
        glStencilMask(0x00);

        player.render(alpha);
        enemies.render(alpha);
        solids.render(alpha);
        powerUps.render(alpha);
        timeds.render();
        renderer.draw();

        //Draw without test and don't write to Stencil Buffer
        glStencilFunc(GL_ALWAYS, 1, 0xff);
        glStencilMask(0x00);

        player.canvas.drawBorder();
        player.drawLives();
        renderer.draw();
    }

    private void wait(long start, long interval){
        while(System.currentTimeMillis() - start < interval)
            try{
                Thread.sleep(1);
            } catch(InterruptedException ignored){}
    }
}
