package bullethell.game;

import bullethell.graphic.Renderer;

public class Ship{
    private float x, y;
    private final float hitRadius;
    private int currentAnimationFrame;
    private long lastFrame;

    private final static long FRAME_TIME = 500;

    public Ship(float x, float y, float radius){
        this.x = x;
        this.y = y;
        hitRadius = radius;
        currentAnimationFrame = 0;
        lastFrame = -1;
    }
    public void update(){
        if(lastFrame == -1) lastFrame = System.currentTimeMillis();
        else{
            long now = System.currentTimeMillis();
            long elapsed = now - lastFrame;
            if(elapsed > FRAME_TIME){
                lastFrame = now - elapsed + FRAME_TIME;
                currentAnimationFrame =
                        currentAnimationFrame == 0 ? 1 : 0;
            }
        }
    }
    public void render(Renderer renderer){
        renderer.drawTexture(x, y, 0.2f, currentAnimationFrame);
    }
}
