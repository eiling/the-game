package bullethell.game;

import bullethell.math.Vec2f;

abstract class TimedAnimated extends Animated{
    private int repeat;

    TimedAnimated(float x, float y, float scale, float velocity, Vec2f direction,
             int startingFrame, int numberOfFrames, long frameInterval, int repeat){
        super(x,y,scale,velocity,direction,startingFrame,numberOfFrames,frameInterval);

        this.repeat = repeat;
    }

    @Override
    void updateAnimation(){
        if(lastFrameTime == -1) lastFrameTime = System.currentTimeMillis();
        else{
            long now = System.currentTimeMillis();
            long elapsed = now - lastFrameTime;
            if(elapsed > frameInterval){
                lastFrameTime = now - elapsed + frameInterval;
                if(++currentAnimationFrame == startingFrame + numberOfFrames)
                    if(--repeat == 0) delete();
            }
        }
    }
}
