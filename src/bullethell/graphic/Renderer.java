package bullethell.graphic;

import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import static bullethell.graphic.Texture.*;

public class Renderer{
    private int vertexArrayID;
    private int vertexBufferID;
    private int programID;

    private FloatBuffer vertices;
    private int numVertices;

    private Texture texture;

    private static final int BUFFER_SIZE = Float.BYTES * 1024;

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
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 4*Float.BYTES, 0);

        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 4*Float.BYTES, 2*Float.BYTES);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void draw(){
        vertices.flip();

        glClear(GL_COLOR_BUFFER_BIT);

        glUseProgram(programID);

        texture.bind();

        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferID);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        glDrawArrays(GL_TRIANGLES, 0, numVertices);

        vertices.clear();
        numVertices = 0;
    }

    public void drawTexture(float x, float y, float k,  int texID){
        texID *= 4;

        vertices.put(x - k).put(y - k).put(st[texID]).put(st[texID + 1]);
        vertices.put(x + k).put(y - k).put(st[texID + 2]).put(st[texID + 1]);
        vertices.put(x + k).put(y + k).put(st[texID + 2]).put(st[texID + 3]);
        vertices.put(x - k).put(y - k).put(st[texID]).put(st[texID + 1]);
        vertices.put(x + k).put(y + k).put(st[texID + 2]).put(st[texID + 3]);
        vertices.put(x - k).put(y + k).put(st[texID]).put(st[texID + 3]);

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
