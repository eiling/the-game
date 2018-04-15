package bullethell.game;

import bullethell.math.Vec2f;

public abstract class PowerUp extends Cyclic {
    protected PowerUp(float x, float y, float scale, float velocity, Vec2f direction,
                      int startingFrame, int finalFrame, long frameInterval, float hitRadius){
        super(x, y, scale, velocity, direction, startingFrame, finalFrame, frameInterval, hitRadius);
    }

    @Override
    protected void move(float delta){} //does all powerups move the same way?
}
