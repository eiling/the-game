package bullethell.graphic;

import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.stb.STBImage.stbi_failure_reason;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

public class Texture{
    private int id;

    public static float[] st = new float[]{
            0.53125f, 0.96875f, 0.546875f, 0.984375f, //Ship1
            0.546875f, 0.96875f, 0.5625f, 0.984375f //Ship2
    };

    public static Texture load(){
        IntBuffer w = MemoryUtil.memAllocInt(1);
        IntBuffer h = MemoryUtil.memAllocInt(1);
        IntBuffer comp = MemoryUtil.memAllocInt(1);

        stbi_set_flip_vertically_on_load(true);

        ByteBuffer data = stbi_load("./textures/atlas1.png", w, h, comp, 0);
        if (data == null) {
            throw new RuntimeException("Failed to load a texture file!"
                    + System.lineSeparator() + stbi_failure_reason());
        }

        int width = w.get();
        int height = h.get();

        Texture texture = new Texture();
        texture.id = glGenTextures();

        texture.bind();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        return texture;
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void delete(){
        glDeleteTextures(id);
    }
}
