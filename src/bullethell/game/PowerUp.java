package bullethell.game;

import bullethell.math.Vec2f;

public abstract class PowerUp extends Solid{
    public PowerUp(float x, float y, float scale, float velocity, Vec2f direction,
                   int startingFrame, int numberOfFrames, long frameInterval, float hitRadius){
        super(x,y,scale,velocity,direction,startingFrame,numberOfFrames,frameInterval,hitRadius);
    }
}
