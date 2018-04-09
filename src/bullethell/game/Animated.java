package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

abstract class Animated extends GameObject{
    private final int startingFrame;
    private int currentAnimationFrame;
    private final int numberOfFrames;
    private long lastFrameTime;
    private final long frameInterval;

    Animated(float x, float y, float scale, float velocity, Vec2f direction,
             int startingFrame, int numberOfFrames, long frameInterval){
        super(x,y,scale,velocity,direction);
        this.startingFrame = startingFrame;
        this.numberOfFrames = numberOfFrames;
        this.frameInterval = frameInterval;

        currentAnimationFrame = this.startingFrame;
        lastFrameTime = -1;
    }

    protected void updateAnimation(){
        if(lastFrameTime == -1) lastFrameTime = System.currentTimeMillis();
        else{
            long now = System.currentTimeMillis();
            long elapsed = now - lastFrameTime;
            if(elapsed > frameInterval){
                lastFrameTime = now - elapsed + frameInterval;
                if(++currentAnimationFrame == startingFrame + numberOfFrames)
                    currentAnimationFrame = startingFrame;
            }
        }
    }

    @Override
    public void render(Renderer renderer){
        renderer.drawTexture(x, y, scale, currentAnimationFrame);
    }
}
