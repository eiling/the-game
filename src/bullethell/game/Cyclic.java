package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

public abstract class Cyclic extends Moving {
    public Cyclic(float x, float y, float scale, float velocity, Vec2f direction,
                  int startingFrame, int numberOfFrames, long frameInterval, float hitRadius){
        super(x, y, scale, startingFrame, numberOfFrames, frameInterval, velocity, direction, hitRadius);
    }

    @Override
    public void update(float delta){
        prevx = x;
        prevy = y;

        move(delta);
        updateAnimation();
    }

    public boolean collided(Moving moving){
        final float dx = x - moving.x;
        final float dy = y - moving.y;
        final float minDist = hitRadius + moving.hitRadius;

        final float dx2 = dx * dx;
        final float dy2 = dy * dy;

        return dx2 + dy2 < minDist * minDist;
    }
}
