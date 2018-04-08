package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

public abstract class Entity{
    float x, y;
    private final float scale;
    private final float hitRadius;

    Vec2f velocity;

    private final int startingFrame;
    private int currentAnimationFrame;
    private final int numberOfFrames;
    private long lastFrameTime;
    private final long frameInterval;

    Entity(float scale, float hitRadius,int startingFrame, int numberOfFrames, long frameInterval){
        this.scale = scale;
        this.hitRadius = hitRadius;
        this.numberOfFrames = numberOfFrames;
        this.frameInterval = frameInterval;

        this.startingFrame = startingFrame;
        currentAnimationFrame = this.startingFrame;
        lastFrameTime = -1;
    }

    public abstract void update();

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

    public void render(Renderer renderer){
        renderer.drawTexture(x, y, scale, currentAnimationFrame);
    }
}
