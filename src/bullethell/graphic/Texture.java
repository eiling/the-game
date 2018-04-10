package bullethell.graphic;

import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.stb.STBImage.*;

public class Texture{
    static final float[] st = new float[]{
            0.515625f, 0.984375f, 0.53125f, 1.0f, //Enemy1 -- texID == 0
            0.53222656f, 0.984375f, 0.54785156f, 1.0f, //Enemy2 -- texID == 1
            0.5488281f, 0.984375f, 0.5644531f, 1.0f, //PowerUp01 -- texID == 2
            0.5654297f, 0.984375f, 0.5810547f, 1.0f, //PowerUp02 -- texID == 3
            0.58203125f, 0.984375f, 0.59765625f, 1.0f, //PowerUp03 -- texID == 4
            0.5986328f, 0.984375f, 0.6142578f, 1.0f, //PowerUp04 -- texID == 5
            0.6152344f, 0.984375f, 0.6308594f, 1.0f, //PowerUp05 -- texID == 6
            0.63183594f, 0.984375f, 0.64746094f, 1.0f, //PowerUp06 -- texID == 7
            0.6484375f, 0.984375f, 0.6640625f, 1.0f, //PowerUp07 -- texID == 8
            0.66503906f, 0.984375f, 0.68066406f, 1.0f, //PowerUp08 -- texID == 9
            0.6816406f, 0.984375f, 0.6972656f, 1.0f, //PowerUp09 -- texID == 10
            0.6982422f, 0.984375f, 0.7138672f, 1.0f, //PowerUp10 -- texID == 11
            0.71484375f, 0.984375f, 0.73046875f, 1.0f, //PowerUp11 -- texID == 12
            0.7314453f, 0.984375f, 0.7470703f, 1.0f, //PowerUp12 -- texID == 13
            0.7480469f, 0.984375f, 0.7636719f, 1.0f, //PowerUp13 -- texID == 14
            0.76464844f, 0.984375f, 0.78027344f, 1.0f, //PowerUp14 -- texID == 15
            0.78125f, 0.984375f, 0.796875f, 1.0f, //Ship1 -- texID == 16
            0.79785156f, 0.984375f, 0.81347656f, 1.0f, //Ship2 -- texID == 17
            0.0f, 0.96875f, 0.03125f, 1.0f, //explosion01 -- texID == 18
            0.032226562f, 0.96875f, 0.063476562f, 1.0f, //explosion02 -- texID == 19
            0.064453125f, 0.96875f, 0.095703125f, 1.0f, //explosion03 -- texID == 20
            0.09667969f, 0.96875f, 0.12792968999999998f, 1.0f, //explosion04 -- texID == 21
            0.12890625f, 0.96875f, 0.16015625f, 1.0f, //explosion05 -- texID == 22
            0.16113281f, 0.96875f, 0.19238281f, 1.0f, //explosion06 -- texID == 23
            0.19335938f, 0.96875f, 0.22460938f, 1.0f, //explosion07 -- texID == 24
            0.22558594f, 0.96875f, 0.25683594f, 1.0f, //explosion08 -- texID == 25
            0.2578125f, 0.96875f, 0.2890625f, 1.0f, //explosion09 -- texID == 26
            0.29003906f, 0.96875f, 0.32128906f, 1.0f, //explosion10 -- texID == 27
            0.32226562f, 0.96875f, 0.35351562f, 1.0f, //explosion11 -- texID == 28
            0.3544922f, 0.96875f, 0.3857422f, 1.0f, //explosion12 -- texID == 29
            0.38671875f, 0.96875f, 0.41796875f, 1.0f, //explosion13 -- texID == 30
            0.4189453f, 0.96875f, 0.4501953f, 1.0f, //explosion14 -- texID == 31
            0.45117188f, 0.96875f, 0.48242188f, 1.0f, //explosion15 -- texID == 32
            0.48339844f, 0.96875f, 0.51464844f, 1.0f, //explosion16 -- texID == 33
    };
    private int id;

    static Texture load(){
        IntBuffer w = MemoryUtil.memAllocInt(1);
        IntBuffer h = MemoryUtil.memAllocInt(1);
        IntBuffer comp = MemoryUtil.memAllocInt(1);

        stbi_set_flip_vertically_on_load(true);

        ByteBuffer data = stbi_load("./textures/atlas1.png", w, h, comp, 0);
        if(data == null){
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
