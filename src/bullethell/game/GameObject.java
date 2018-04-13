package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

abstract class GameObject{
    protected float x, y;
    protected float velocity;
    protected Vec2f direction;
    float scale;

    float prevx, prevy;

    GameObject(float x, float y, float scale, float velocity, Vec2f direction){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.velocity = velocity;
        this.direction = direction;

        prevx = x;
        prevy = y;
    }

    public abstract void update(float delta);

    protected abstract void move(float delta);

    public abstract void render(Renderer renderer, float alpha);

    static boolean isOutside(float top, float bottom, float right, float left){
        return top > 1f || bottom < -1f || right > 1f || left < -1f;
    }
}
