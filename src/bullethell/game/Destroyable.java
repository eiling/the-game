package bullethell.game;

import bullethell.math.Vec2f;

public abstract class Destroyable extends Solid{
    public Destroyable(float x, float y, float scale, float velocity, Vec2f direction,
                       int startingFrame, int numberOfFrames, long frameInterval, float hitRadius){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius);
    }

    @Override
    public void update(float delta){
        prevx = x;
        prevy = y;

        move(delta);
        updateAnimation();
    }
}
