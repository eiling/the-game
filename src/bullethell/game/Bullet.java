package bullethell.game;

import bullethell.math.Vec2f;

public abstract class Bullet extends NonCyclic {
    public Bullet(float x, float y, float scale, int startingFrame, int finalFrame, long frameInterval, float velocity, Vec2f direction, float hitRadius){
        super(x, y, scale, startingFrame, finalFrame, frameInterval, velocity, direction, hitRadius);
    }

    @Override
    protected void move(float delta){
        x += delta * velocity * direction.x;
        y += delta * velocity * direction.y;
    }
}
