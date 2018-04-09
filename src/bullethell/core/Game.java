package bullethell.core;

import bullethell.game.Enemy;
import bullethell.game.PowerUp;
import bullethell.game.characters.CharacterWithNoName;
import bullethell.game.enemies.EnemyWithNoName;
import bullethell.game.powerups.PowerUpWithNoName;
import bullethell.graphic.Renderer;
import bullethell.graphic.Window;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;

public class Game{
    private GLFWErrorCallback errorCallback;

    private Window window;

    private Renderer renderer;

    private CharacterWithNoName player;
    private EnemyWithNoName enemy;
    private PowerUpWithNoName powerUp;

    private void start(){
        init();
        loop();
        dispose();
    }

    private void init(){
        errorCallback = GLFWErrorCallback.createPrint();
        glfwSetErrorCallback(errorCallback);

        /* Initialize GLFW */
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW!");
        }

        /* Create GLFW window */
        window = new Window(500, 500, "Bullet Hell");

        renderer = new Renderer();
        renderer.init();

        player = new CharacterWithNoName(-0.5f, -0.5f);
        enemy = new EnemyWithNoName(0.5f, -0.5f);
        powerUp = new PowerUpWithNoName(-0.5f, 0.5f);
    }

    private void loop(){
        while(true){
            long start = System.currentTimeMillis();

            if(window.isClosing()) break;

            player.input(window.id);

            player.update();
            enemy.update();
            powerUp.update();

            player.render(renderer);
            enemy.render(renderer);
            powerUp.render(renderer);

            renderer.draw();

            window.update();

            long now = System.currentTimeMillis();
            wait(now, (long)(1000f/30f) - (now - start));
        }
    }

    private void dispose(){
        renderer.dispose();
        window.dispose();
    }

    private void wait(long start, long interval){
        while(System.currentTimeMillis() - start < interval)
            try{ Thread.sleep(1);} catch(InterruptedException ignored){}
    }

    public static void main(String[] args){
        new Game().start();
    }
}
