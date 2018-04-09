package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

abstract class GameObject{
    protected float x, y;
    float scale;

    private boolean active;

    protected float velocity;
    protected Vec2f direction;

    GameObject(float x, float y, float scale, float velocity, Vec2f direction){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.velocity =velocity;
        this.direction = direction;

        active = true;
    }

    public abstract void update();

    protected abstract void move();

    public abstract void render(Renderer renderer);

    void delete(){
        active = false;
    }

    public boolean isActive(){
        return active;
    }
}
