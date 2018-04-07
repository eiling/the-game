package bullethell.core;

import bullethell.game.Ship;
import bullethell.graphic.Renderer;
import bullethell.graphic.Window;
import org.lwjgl.glfw.GLFWErrorCallback;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;

public class Game{
    private GLFWErrorCallback errorCallback;

    private Window window;

    private Renderer renderer;

    private Ship ship;
    private Ship ship2;

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

        ship = new Ship(0f, 0f, 0.2f);
        ship2 = new Ship(0.5f, 0f, 0.2f);
    }

    private void loop(){
        while(true){
            long start = System.currentTimeMillis();

            if(window.isClosing()) break;

            ship.update();
            ship2.update();

            ship.render(renderer);
            ship2.render(renderer);

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
