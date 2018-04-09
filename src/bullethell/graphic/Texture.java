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

    static final float[] st = new float[]{
            0.53125f, 0.96875f, 0.546875f, 0.984375f, //Ship1 - texID == 0
            0.546875f, 0.96875f, 0.5625f, 0.984375f, //Ship2 - texID == 1
            0.5f, 1f - 0.015625f - 0.015625f, 0.515625f, 1f - 0.015625f, //Enemy1 - texID == 2
            0.515625f, 1f - 0.015625f - 0.015625f, 0.53125f, 1f - 0.015625f, //Enemy2 - texID == 3
            0.5f, 1f - 0.015625f, 0.515625f, 1f, //PowerUp1 - texID == 4
            0.515625f, 1f - 0.015625f, 0.53125f, 1f, //PowerUp2 - texID == 5
            0.53125f, 1f - 0.015625f, 0.546875f, 1f, //PowerUp3 - texID == 6
            0.546875f, 1f - 0.015625f, 0.5625f, 1f, //PowerUp4 - texID == 7
            0.5625f, 1f - 0.015625f, 0.578125f, 1f, //PowerUp5 - texID == 8
            0.578125f, 1f - 0.015625f, 0.59375f, 1f, //PowerUp6 - texID == 9
            0.59375f, 1f - 0.015625f, 0.609375f, 1f, //PowerUp7 - texID == 10
            0.609375f, 1f - 0.015625f, 0.625f, 1f, //PowerUp8 - texID == 11
            0.625f, 1f - 0.015625f, 0.640625f, 1f, //PowerUp9 - texID == 12
            0.640625f, 1f - 0.015625f, 0.65625f, 1f, //PowerUp10 - texID == 13
            0.65625f, 1f - 0.015625f, 0.671875f, 1f, //PowerUp11 - texID == 14
            0.671875f, 1f - 0.015625f, 0.6875f, 1f, //PowerUp12 - texID == 15
            0.6875f, 1f - 0.015625f, 0.703125f, 1f, //PowerUp13 - texID == 16
            0.703125f, 1f - 0.015625f, 0.71875f, 1f //PowerUp14 - texID == 17
    };

    static Texture load(){
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
