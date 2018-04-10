package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

public abstract class Bullet extends GameObject{
    float hitRadius;

    public Bullet(float x, float y, float scale, float hitRadius, float velocity, Vec2f direction){
        super(x, y, scale, velocity, direction);
        this.hitRadius = hitRadius;
    }

    @Override
    public void update(){
    }

    protected void move(){
    }

    @Override
    public void render(Renderer renderer){
    }
}
