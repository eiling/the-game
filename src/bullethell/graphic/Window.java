package bullethell.graphic;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window{
    public final long id;

    public Window(int width, int height, CharSequence title){
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_SAMPLES, 4);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        id = glfwCreateWindow(width, height, title, NULL, NULL);
        if(id == NULL){
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window!");
        }

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(id,
                (vidMode.width() - width) / 2,
                (vidMode.height() - height) / 2
        );

        glfwMakeContextCurrent(id);
        GL.createCapabilities();

        glClearColor(0f, 0f, 0.4f, 1f);

        glfwSetKeyCallback(id, new GLFWKeyCallback(){
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods){
                if(key == GLFW_KEY_ESCAPE && action == GLFW_PRESS){
                    glfwSetWindowShouldClose(window, true);
                }
            }
        });
    }

    public void update(){
        glfwSwapBuffers(id);
        glfwPollEvents();
    }

    public boolean isClosing(){
        return glfwWindowShouldClose(id);
    }

    public void dispose(){
        glfwDestroyWindow(id);
        glfwTerminate();
    }
}
