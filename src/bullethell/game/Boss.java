package bullethell.game;

import bullethell.math.Vec2f;

public abstract class Boss extends Solid{
    protected Boss(float x, float y, float scale, float velocity, Vec2f direction,
                    int startingFrame, int numberOfFrames, long frameInterval, float hitRadius){
        super(x,y,scale,velocity,direction,startingFrame,numberOfFrames,frameInterval,hitRadius);
    }

    @Override
    public void update(){
        move();
        updateAnimation();
    }

    @Override
    protected abstract void move();
}
