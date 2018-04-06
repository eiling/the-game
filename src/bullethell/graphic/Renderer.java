package bullethell.graphic;

import bullethell.math.Trig;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Renderer{
    private int vertexArrayID;
    private int vertexBufferID;
    private int programID;

    private FloatBuffer vertices;
    private int numVertices;

    private static final int BUFFER_SIZE = Float.BYTES * 1024;

    public void init(){
        vertexArrayID = glGenVertexArrays();
        glBindVertexArray(vertexArrayID);

        programID = Shader.load("VertexShader.glsl", "FragmentShader.glsl");

        vertices = MemoryUtil.memAllocFloat(BUFFER_SIZE);

        vertexBufferID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 5*Float.BYTES, 0);

        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 5*Float.BYTES, 2*Float.BYTES);
    }

    public void draw(){
        vertices.flip();

        glClear(GL_COLOR_BUFFER_BIT);

        glUseProgram(programID);

        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glDrawArrays(GL_TRIANGLES, 0, numVertices);

        vertices.clear();
        numVertices = 0;
    }

    public void drawPolygon(float x, float y, float radius, float[] color, int t){
        final int TRIANGLES = t;
        for(int i = 0; i < TRIANGLES; i++){
            vertices.put(x)
                    .put(y)
                    .put(color);
            vertices.put(x + radius * Trig.cos(Trig.TWO_PI * i / TRIANGLES))
                    .put(y + radius * Trig.sin(Trig.TWO_PI * i / TRIANGLES))
                    .put(color);
            vertices.put(x + radius * Trig.cos(Trig.TWO_PI * (i + 1) / TRIANGLES))
                    .put(y + radius * Trig.sin(Trig.TWO_PI * (i + 1) / TRIANGLES))
                    .put(color);

            numVertices += 3;
        }
    }

    public void drawStar(float x, float y, float radius, float k, int t, float phi){
        final int TRIANGLES = t;
        for(int i = 0; i < TRIANGLES; i++){
            vertices.put(x)
                    .put(y)
                    .put(1f).put(0f).put(0f);
            vertices.put(x + radius * Trig.cos(Trig.TWO_PI * i / TRIANGLES + phi))
                    .put(y + radius * Trig.sin(Trig.TWO_PI * i / TRIANGLES + phi))
                    .put(1f).put(0f).put(0f);
            vertices.put(x + k * radius * Trig.cos(Trig.TWO_PI * (i + 1) / TRIANGLES + phi))
                    .put(y + k * radius * Trig.sin(Trig.TWO_PI * (i + 1) / TRIANGLES + phi))
                    .put(0.2f).put(0f).put(0f);

            numVertices += 3;
        }
    }

    public void dispose(){
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glDeleteBuffers(vertexBufferID);
        glDeleteProgram(programID);
        glDeleteVertexArrays(vertexArrayID);
    }
}
