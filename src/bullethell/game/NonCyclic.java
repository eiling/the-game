package bullethell.game;

import bullethell.math.Vec2f;

abstract class NonCyclic extends Moving{
    NonCyclic(float x, float y, float scale, int startingFrame, int finalFrame, long frameInterval, float velocity, Vec2f direction, float hitRadius){
        super(x, y, scale, startingFrame, finalFrame, frameInterval, velocity, direction, hitRadius);
    }

    @Override
    void updateAnimation(){
        if(currentAnimationFrame == finalFrame) return;
        if(lastFrameTime == -1) lastFrameTime = System.currentTimeMillis();
        else{
            long now = System.currentTimeMillis();
            if(now - lastFrameTime > frameInterval){
                lastFrameTime = now;
                currentAnimationFrame++;
            }
        }
    }
}
