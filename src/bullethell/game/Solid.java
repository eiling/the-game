package bullethell.game;

import bullethell.math.Vec2f;

abstract class Solid extends Animated{
    protected float hitRadius;

    Solid(float x, float y, float scale, float velocity, Vec2f direction,
          int startingFrame, int numberOfFrames, long frameInterval, float hitRadius){
        super(x,y,scale,velocity,direction,startingFrame,numberOfFrames,frameInterval);
        this.hitRadius = hitRadius;
    }
}
