package bullethell.game;

import bullethell.math.Vec2f;
import bullethell.util.Bullets;

import static org.lwjgl.glfw.GLFW.*;

public abstract class Character extends Solid{
    private boolean shooting;
    private long last, shootInterval;

    protected Character(float x, float y, float scale, float velocity, Vec2f direction,
                        int startingFrame, int numberOfFrames, long frameInterval, float hitRadius,
                        long shootInterval){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius);

        shooting = true;
        last = -1;
        this.shootInterval = shootInterval;
    }

    public void update(Bullets bullets){
        update();

        if(shooting){
            long now = System.currentTimeMillis();
            if(last == -1){
                shoot(bullets);
                last = now;
            } else if(now - last >= shootInterval){
                    shoot(bullets);
                    last += shootInterval;
            }
        }
    }

    @Override
    public void update(){
        move();
        updateAnimation();
    }

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

    protected abstract void shoot(Bullets bullets);

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
