package bullethell.core;

import bullethell.game.Ship;
import bullethell.graphic.Renderer;
import bullethell.graphic.Window;
import bullethell.math.Trig;
import org.lwjgl.glfw.GLFWErrorCallback;

import java.awt.desktop.SystemEventListener;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;

public class Game{
    private GLFWErrorCallback errorCallback;

    private Window window;

    private Renderer renderer;

    private Ship ship;

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

        ship = new Ship(0f, 0f);
    }

    private void loop(){
        float[] color = {1f,0f,0f};
        float i = 0;
        while(true){
            long start = System.currentTimeMillis();

            if(window.isClosing()) break;

            //ship.render(renderer);

            i -= 0.02f;
            if(i > 2) i = 0f;
            renderer.drawStar(0f,0f,0.5f,0.5f,4, Trig.PI * i);
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
