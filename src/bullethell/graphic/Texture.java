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
            0.5f, 0.984375f, 0.515625f, 1.0f, //Enemy1 -- texID == 0
            0.5f, 0.96875f, 0.515625f, 0.984375f, //Enemy2 -- texID == 1
            0.515625f, 0.984375f, 0.53125f, 1.0f, //PowerUp01 -- texID == 2
            0.515625f, 0.96875f, 0.53125f, 0.984375f, //PowerUp02 -- texID == 3
            0.53125f, 0.984375f, 0.546875f, 1.0f, //PowerUp03 -- texID == 4
            0.53125f, 0.96875f, 0.546875f, 0.984375f, //PowerUp04 -- texID == 5
            0.546875f, 0.984375f, 0.5625f, 1.0f, //PowerUp05 -- texID == 6
            0.546875f, 0.96875f, 0.5625f, 0.984375f, //PowerUp06 -- texID == 7
            0.5625f, 0.984375f, 0.578125f, 1.0f, //PowerUp07 -- texID == 8
            0.5625f, 0.96875f, 0.578125f, 0.984375f, //PowerUp08 -- texID == 9
            0.578125f, 0.984375f, 0.59375f, 1.0f, //PowerUp09 -- texID == 10
            0.578125f, 0.96875f, 0.59375f, 0.984375f, //PowerUp10 -- texID == 11
            0.59375f, 0.984375f, 0.609375f, 1.0f, //PowerUp11 -- texID == 12
            0.59375f, 0.96875f, 0.609375f, 0.984375f, //PowerUp12 -- texID == 13
            0.609375f, 0.984375f, 0.625f, 1.0f, //PowerUp13 -- texID == 14
            0.609375f, 0.96875f, 0.625f, 0.984375f, //PowerUp14 -- texID == 15
            0.625f, 0.984375f, 0.640625f, 1.0f, //Ship1 -- texID == 16
            0.625f, 0.96875f, 0.640625f, 0.984375f, //Ship2 -- texID == 17
            0.0f, 0.96875f, 0.03125f, 1.0f, //explosion01 -- texID == 18
            0.03125f, 0.96875f, 0.0625f, 1.0f, //explosion02 -- texID == 19
            0.0625f, 0.96875f, 0.09375f, 1.0f, //explosion03 -- texID == 20
            0.09375f, 0.96875f, 0.125f, 1.0f, //explosion04 -- texID == 21
            0.125f, 0.96875f, 0.15625f, 1.0f, //explosion05 -- texID == 22
            0.15625f, 0.96875f, 0.1875f, 1.0f, //explosion06 -- texID == 23
            0.1875f, 0.96875f, 0.21875f, 1.0f, //explosion07 -- texID == 24
            0.21875f, 0.96875f, 0.25f, 1.0f, //explosion08 -- texID == 25
            0.25f, 0.96875f, 0.28125f, 1.0f, //explosion09 -- texID == 26
            0.28125f, 0.96875f, 0.3125f, 1.0f, //explosion10 -- texID == 27
            0.3125f, 0.96875f, 0.34375f, 1.0f, //explosion11 -- texID == 28
            0.34375f, 0.96875f, 0.375f, 1.0f, //explosion12 -- texID == 29
            0.375f, 0.96875f, 0.40625f, 1.0f, //explosion13 -- texID == 30
            0.40625f, 0.96875f, 0.4375f, 1.0f, //explosion14 -- texID == 31
            0.4375f, 0.96875f, 0.46875f, 1.0f, //explosion15 -- texID == 32
            0.46875f, 0.96875f, 0.5f, 1.0f //explosion16 -- texID == 33
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
