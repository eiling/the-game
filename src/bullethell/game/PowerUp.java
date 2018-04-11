package bullethell.game;

import bullethell.math.Vec2f;
import bullethell.util.Bullets;

public abstract class PowerUp extends Solid{
    public PowerUp(float x, float y, float scale, float velocity, Vec2f direction,
                   int startingFrame, int numberOfFrames, long frameInterval, float hitRadius){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius,
                -1);
    }

    @Override
    public void update(){
        updateAnimation();
    }

    @Override
    protected void move(){
    }

    @Override
    protected void shoot(Bullets bullets){}
}
