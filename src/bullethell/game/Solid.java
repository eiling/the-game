package bullethell.game;

import bullethell.math.Vec2f;

abstract class Solid extends Animated{
    protected float hitRadius;

    Solid(float x, float y, float scale, float velocity, Vec2f direction,
          int startingFrame, int numberOfFrames, long frameInterval, float hitRadius){
        super(x,y,scale,velocity,direction,startingFrame,numberOfFrames,frameInterval);
        this.hitRadius = hitRadius;
    }

    public boolean collided(Solid solid){
        final float dx = x - solid.x;
        final float dy = y - solid.y;
        final float dx2 = dx * dx;
        final float dy2 = dy * dy;
        final float d = (float) Math.sqrt(dx2 + dy2);

        return d < hitRadius + solid.hitRadius;
    }
}
