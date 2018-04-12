package bullethell.game;

import bullethell.math.Vec2f;

public abstract class Enemy extends Entity{
    protected Enemy(float x, float y, float scale, float velocity, Vec2f direction,
                    int startingFrame, int numberOfFrames, long frameInterval, float hitRadius,
                    long shootInterval){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius,
                shootInterval);
    }
}
