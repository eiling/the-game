package bullethell.game;

import bullethell.math.Vec2f;

public abstract class Bullet extends GameObject{
    float hitRadius;

    public Bullet(float x, float y, float scale, float hitRadius, float velocity, Vec2f direction){
        super(x,y,scale,velocity,direction);
        this.hitRadius = hitRadius;
    }
}
