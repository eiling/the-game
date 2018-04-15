package bullethell.game;

import bullethell.math.Vec2f;

public abstract class Boss extends Enemy{
    protected Boss(float x, float y, float scale, float velocity, Vec2f direction,
                   int startingFrame, int finalFrame, long frameInterval, float hitRadius,
                   long shootInterval, int life){
        super(x, y, scale, velocity, direction, startingFrame, finalFrame, frameInterval, hitRadius,
                shootInterval,life);
    }
}
