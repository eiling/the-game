package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

abstract class GameObject{
    protected float x, y;
    protected float velocity;
    protected Vec2f direction;
    float scale;

    GameObject(float x, float y, float scale, float velocity, Vec2f direction){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.velocity = velocity;
        this.direction = direction;
    }

    public abstract void update();

    protected abstract void move();

    public abstract void render(Renderer renderer);
}
