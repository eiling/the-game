package bullethell.game;

import bullethell.graphic.Canvas;
import bullethell.graphic.Renderer;

import static bullethell.graphic.Canvas.MAX_X;
import static bullethell.graphic.Canvas.SCALED_MAX_Y;
import static bullethell.graphic.Canvas.SCALED_MIN_Y;

public abstract class Animated{
    public static Renderer renderer;
    public static Canvas canvas;
    public static Character character;

    public float x, y;
    float scale;

    final int startingFrame;
    final int finalFrame;
    final long frameInterval;
    int currentAnimationFrame;
    long lastFrameTime;

    Animated(float x, float y, float scale, int startingFrame, int finalFrame, long frameInterval){
        if(finalFrame < startingFrame){
            System.err.println("finalFrame < startingFrame");
            System.exit(1);
        }

        this.x = x;
        this.y = y;
        this.scale = scale;

        this.startingFrame = startingFrame;
        this.finalFrame = finalFrame;
        this.frameInterval = frameInterval;

        currentAnimationFrame = this.startingFrame;
        lastFrameTime = -1;
    }

    public void update(){
        updateAnimation();
    }

    void updateAnimation(){
        if(lastFrameTime == -1) lastFrameTime = System.currentTimeMillis();
        else{
            long now = System.currentTimeMillis();
            if(now - lastFrameTime > frameInterval){
                lastFrameTime = now;
                if(currentAnimationFrame++ == finalFrame)
                    currentAnimationFrame = startingFrame;
            }
        }
    }

    public void render(){
        renderer.drawTexture(canvas.scaledx(x), canvas.scaledy(y), canvas.scale(scale), currentAnimationFrame, canvas.SCALED_MIN_X, canvas.SCALED_MAX_X, SCALED_MIN_Y, SCALED_MAX_Y);
        drawHitRadius();
    }

    void drawHitRadius(){}
}
