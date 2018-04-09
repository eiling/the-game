package bullethell.game;

import bullethell.math.Vec2f;

import static org.lwjgl.glfw.GLFW.*;

public class Ship extends Entity{
    public Ship(float x, float y){
        super(0.2f,0.05f,0,2,500);

        this.x = x;
        this.y = y;

        velocity = 0.05f;
        direction = new Vec2f(0f,0f);
    }

    @Override
    public void update(){
        move();
        updateAnimation();
    }

    private void move(){
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
