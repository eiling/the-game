package bullethell.game;

import bullethell.math.Vec2f;
import bullethell.util.lists.Bullets;

import static org.lwjgl.glfw.GLFW.*;

public abstract class Character extends Entity{
    private boolean shooting;

    protected Character(float x, float y, float scale, float velocity, Vec2f direction,
                        int startingFrame, int numberOfFrames, long frameInterval, float hitRadius,
                        long shootInterval){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius,
                shootInterval, -1);

        shooting = false;
    }

    @Override
    public void update(Bullets bullets, float delta){
        update(delta);

        if(shooting){
            long now = System.currentTimeMillis();
            if(last == -1){
                shoot(bullets);
                last = now;
            } else if(now - last >= shootInterval){
                shoot(bullets);
                last = now;
            }

            shooting = false;
        }
    }

    @Override
    protected void move(float delta){
        direction.normalize();
        x += delta * velocity * direction.x;
        y += delta * velocity * direction.y;
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

        if(glfwGetKey(windowID, GLFW_KEY_Z) == GLFW_PRESS) shooting = true;
    }

    public boolean collided(Solid solid){
        return checkCollision(x - solid.x, y - solid.y, hitRadius + solid.hitRadius);
    }
}
