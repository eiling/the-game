package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

abstract class Animated extends GameObject{
    final int startingFrame;
    final int numberOfFrames;
    final long frameInterval;
    int currentAnimationFrame;
    long lastFrameTime;

    Animated(float x, float y, float scale, float velocity, Vec2f direction,
             int startingFrame, int numberOfFrames, long frameInterval){
        super(x, y, scale, velocity, direction);

        this.startingFrame = startingFrame;
        this.numberOfFrames = numberOfFrames;
        this.frameInterval = frameInterval;

        currentAnimationFrame = this.startingFrame;
        lastFrameTime = -1;
    }

    void updateAnimation(){
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
        drawHitRadius(renderer);
    }

    public abstract void drawHitRadius(Renderer renderer);
}
