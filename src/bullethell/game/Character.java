package bullethell.game;

import bullethell.math.Vec2f;
import bullethell.util.Bullets;

import static org.lwjgl.glfw.GLFW.*;

public abstract class Character extends Entity{
    protected Character(float x, float y, float scale, float velocity, Vec2f direction,
                        int startingFrame, int numberOfFrames, long frameInterval, float hitRadius,
                        long shootInterval){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius,
                shootInterval);
    }

    protected abstract void shoot(Bullets bullets);

    @Override
    protected void move(){
        direction.normalize();
        x += velocity * direction.x;
        y += velocity * direction.y;
        direction.reset();

        if(x - hitRadius < -1f) x = -1f + hitRadius;
        else if(x + hitRadius > 1f) x = 1f - hitRadius;
        if(y - hitRadius < -1f) y = -1f + hitRadius;
        else if(y + hitRadius > 1f) y = 1f - hitRadius;
    }

    public void input(long windowID){
        if(glfwGetKey(windowID, GLFW_KEY_UP) == GLFW_PRESS) direction.y += 1f;
        if(glfwGetKey(windowID, GLFW_KEY_DOWN) == GLFW_PRESS) direction.y -= 1f;
        if(glfwGetKey(windowID, GLFW_KEY_RIGHT) == GLFW_PRESS) direction.x += 1f;
        if(glfwGetKey(windowID, GLFW_KEY_LEFT) == GLFW_PRESS) direction.x -= 1f;
    }

    public boolean collided(Solid solid){
        return checkCollision(x - solid.x, y - solid.y, hitRadius + solid.hitRadius);
    }
}
