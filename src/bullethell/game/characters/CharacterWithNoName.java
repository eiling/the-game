package bullethell.game.characters;

import bullethell.game.Character;
import bullethell.math.Vec2f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;

public class CharacterWithNoName extends Character{
    public CharacterWithNoName(float x, float y){
        super(x,y,0.2f,0.05f, new Vec2f(0f,0f),
                0,2,500, 0.2f);
    }

    @Override
    public void update(){
        move();
        updateAnimation();
    }

    @Override
    protected void move(){
        x += velocity * direction.x;
        y += velocity * direction.y;

        if(x - hitRadius < -1f) x = -1f + hitRadius;
        else if(x + hitRadius > 1f) x = 1f - hitRadius;
        if(y - hitRadius < -1f) y = -1f + hitRadius;
        else if(y + hitRadius > 1f) y = 1f - hitRadius;
    }

    public void input(long windowID){
        direction.reset();
        if(glfwGetKey(windowID, GLFW_KEY_UP) == GLFW_PRESS) direction.y += 1f;
        if(glfwGetKey(windowID, GLFW_KEY_DOWN) == GLFW_PRESS) direction.y -= 1f;
        if(glfwGetKey(windowID, GLFW_KEY_RIGHT) == GLFW_PRESS) direction.x += 1f;
        if(glfwGetKey(windowID, GLFW_KEY_LEFT) == GLFW_PRESS) direction.x -= 1f;
        direction.normalize2();
    }
}
