package bullethell.graphic;

import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.stb.STBImage.*;

class Texture{
    static final float[] st = new float[]{
            0.54785156f, 0.984375f, 0.56347656f, 1.0f, //Enemy1 -- texID == 0
            0.5644531f, 0.984375f, 0.5800781f, 1.0f, //Enemy2 -- texID == 1
            0.5810547f, 0.984375f, 0.5966797f, 1.0f, //PowerUp01 -- texID == 2
            0.59765625f, 0.984375f, 0.61328125f, 1.0f, //PowerUp02 -- texID == 3
            0.6142578f, 0.984375f, 0.6298828f, 1.0f, //PowerUp03 -- texID == 4
            0.6308594f, 0.984375f, 0.6464844f, 1.0f, //PowerUp04 -- texID == 5
            0.64746094f, 0.984375f, 0.66308594f, 1.0f, //PowerUp05 -- texID == 6
            0.6640625f, 0.984375f, 0.6796875f, 1.0f, //PowerUp06 -- texID == 7
            0.68066406f, 0.984375f, 0.69628906f, 1.0f, //PowerUp07 -- texID == 8
            0.6972656f, 0.984375f, 0.7128906f, 1.0f, //PowerUp08 -- texID == 9
            0.7138672f, 0.984375f, 0.7294922f, 1.0f, //PowerUp09 -- texID == 10
            0.73046875f, 0.984375f, 0.74609375f, 1.0f, //PowerUp10 -- texID == 11
            0.7470703f, 0.984375f, 0.7626953f, 1.0f, //PowerUp11 -- texID == 12
            0.7636719f, 0.984375f, 0.7792969f, 1.0f, //PowerUp12 -- texID == 13
            0.78027344f, 0.984375f, 0.79589844f, 1.0f, //PowerUp13 -- texID == 14
            0.796875f, 0.984375f, 0.8125f, 1.0f, //PowerUp14 -- texID == 15
            0.81347656f, 0.984375f, 0.82910156f, 1.0f, //Ship1 -- texID == 16
            0.8300781f, 0.984375f, 0.8457031f, 1.0f, //Ship2 -- texID == 17
            0.8466797f, 0.984375f, 0.8623047f, 1.0f, //bullet_basic01 -- texID == 18
            0.86328125f, 0.984375f, 0.87890625f, 1.0f, //bullet_basic02 -- texID == 19
            0.8798828f, 0.984375f, 0.8955078f, 1.0f, //bullet_basic03 -- texID == 20
            0.8964844f, 0.984375f, 0.9121094f, 1.0f, //bullet_basic04 -- texID == 21
            0.91308594f, 0.984375f, 0.92871094f, 1.0f, //bullet_basic05 -- texID == 22
            0.9296875f, 0.984375f, 0.9453125f, 1.0f, //bullet_basic06 -- texID == 23
            0.94628906f, 0.984375f, 0.96191406f, 1.0f, //bullet_basic07 -- texID == 24
            0.9628906f, 0.984375f, 0.9785156f, 1.0f, //bullet_basic08 -- texID == 25
            0.9794922f, 0.984375f, 0.9951172f, 1.0f, //bullet_basic09 -- texID == 26
            0.0f, 0.952148438f, 0.015625f, 0.967773438f, //bullet_basic10 -- texID == 27
            0.0f, 0.935546875f, 0.015625f, 0.951171875f, //bullet_basic11 -- texID == 28
            0.0f, 0.91894531f, 0.015625f, 0.93457031f, //bullet_enemy -- texID == 29
            0.54785156f, 0.973632813f, 0.557617185f, 0.983398438f, //bullet_slash1 -- texID == 30
            0.5644531f, 0.973632813f, 0.574218725f, 0.983398438f, //bullet_slash2 -- texID == 31
            0.5810547f, 0.973632813f, 0.590820325f, 0.983398438f, //bullet_slash3 -- texID == 32
            0.59765625f, 0.973632813f, 0.607421875f, 0.983398438f, //bullet_slash4 -- texID == 33
            0.6142578f, 0.973632813f, 0.624023425f, 0.983398438f, //bullet_slash5 -- texID == 34
            0.0f, 0.96875f, 0.03125f, 1.0f, //explosion01 -- texID == 35
            0.032226562f, 0.96875f, 0.063476562f, 1.0f, //explosion02 -- texID == 36
            0.064453125f, 0.96875f, 0.095703125f, 1.0f, //explosion03 -- texID == 37
            0.09667969f, 0.96875f, 0.12792968999999998f, 1.0f, //explosion04 -- texID == 38
            0.12890625f, 0.96875f, 0.16015625f, 1.0f, //explosion05 -- texID == 39
            0.16113281f, 0.96875f, 0.19238281f, 1.0f, //explosion06 -- texID == 40
            0.19335938f, 0.96875f, 0.22460938f, 1.0f, //explosion07 -- texID == 41
            0.22558594f, 0.96875f, 0.25683594f, 1.0f, //explosion08 -- texID == 42
            0.2578125f, 0.96875f, 0.2890625f, 1.0f, //explosion09 -- texID == 43
            0.29003906f, 0.96875f, 0.32128906f, 1.0f, //explosion10 -- texID == 44
            0.32226562f, 0.96875f, 0.35351562f, 1.0f, //explosion11 -- texID == 45
            0.3544922f, 0.96875f, 0.3857422f, 1.0f, //explosion12 -- texID == 46
            0.38671875f, 0.96875f, 0.41796875f, 1.0f, //explosion13 -- texID == 47
            0.4189453f, 0.96875f, 0.4501953f, 1.0f, //explosion14 -- texID == 48
            0.45117188f, 0.96875f, 0.48242188f, 1.0f, //explosion15 -- texID == 49
            0.48339844f, 0.96875f, 0.51464844f, 1.0f, //explosion16 -- texID == 50
            0.515625f, 0.96875f, 0.546875f, 1.0f, //green-circle -- texID == 51
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

    private void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }

    void delete(){
        glDeleteTextures(id);
    }
}
