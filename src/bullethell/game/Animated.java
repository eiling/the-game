package bullethell.game;

import bullethell.graphic.Renderer;

abstract class Animated{
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

    public void render(Renderer renderer){
        renderer.drawTexture(x, y, scale, currentAnimationFrame);
        drawHitRadius(renderer);
    }

    void drawHitRadius(Renderer renderer){}
}
