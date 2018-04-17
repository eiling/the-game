package bullethell.graphic;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static bullethell.graphic.Texture.load;
import static bullethell.graphic.Texture.st;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer{
    private static final Renderer renderer = new Renderer();

    private static final int BUFFER_SIZE = Float.BYTES * 100000;
    private int vertexArrayID;
    private int vertexBufferID;
    private int programID;
    private FloatBuffer vertices;
    private int numVertices;
    private Texture texture;

    public void init(){
        vertexArrayID = glGenVertexArrays();
        glBindVertexArray(vertexArrayID);

        programID = Shader.load("VertexShader.glsl", "FragmentShader.glsl");

        texture = load();

        vertices = MemoryUtil.memAllocFloat(BUFFER_SIZE);

        vertexBufferID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID);

        int textureSamplerUniform = glGetUniformLocation(programID, "textureSampler");
        glUniform1i(textureSamplerUniform, 0);

        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 4 * Float.BYTES, 0);

        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 4 * Float.BYTES, 2 * Float.BYTES);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void draw(boolean clear){
        vertices.flip();

        if(clear) glClear(GL_COLOR_BUFFER_BIT);

        glUseProgram(programID);

        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glDrawArrays(GL_TRIANGLES, 0, numVertices);

        vertices.clear();
        numVertices = 0;
    }

    public void drawTexture(float x, float y, float k, int texID){
        int i = texID * 4;

        float
                x1 = x - k, x2 = x + k,
                y1 = y - k, y2 = y + k,
                s1 = st[i], s2 = st[i + 2],
                t1 = st[i + 1], t2 = st[i + 3];

        vertices.put(x1).put(y1).put(s1).put(t1);
        vertices.put(x2).put(y1).put(s2).put(t1);
        vertices.put(x2).put(y2).put(s2).put(t2);
        vertices.put(x1).put(y1).put(s1).put(t1);
        vertices.put(x2).put(y2).put(s2).put(t2);
        vertices.put(x1).put(y2).put(s1).put(t2);

        numVertices += 6;
    }

    public void dispose(){
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        texture.delete();

        glDeleteBuffers(vertexBufferID);
        glDeleteProgram(programID);
        glDeleteVertexArrays(vertexArrayID);
    }
}
