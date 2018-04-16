package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

abstract class Moving extends Animated{
    protected float velocity;
    protected Vec2f direction;
    final float hitRadius;

    float prevx, prevy; //for interpolation

    Moving(float x, float y, float scale, int startingFrame, int numberOfFrames, long frameInterval, float velocity, Vec2f direction, float hitRadius){
        super(x, y, scale, startingFrame, numberOfFrames, frameInterval);
        this.velocity = velocity;
        this.direction = direction;
        this.hitRadius = hitRadius;

        prevx = x;
        prevy = y;
    }

    public void update(float delta){
        prevx = x;
        prevy = y;

        move(delta);
        updateAnimation();
    }

    protected abstract void move(float delta);

    public void render(float alpha){
        super.render();
    }

    public abstract Timed explode();

    public boolean isOutOfCanvas(){
        return isOutside(x - scale, x + scale, y - scale, y + scale);
    }

    private static boolean isOutside(float top, float bottom, float right, float left){
        final float c = 1.5f; //canvas half-width
        return top > c || bottom < -c || right > c || left < -c;
    }

    @Override
    public void drawHitRadius(Renderer renderer){
        //renderer.drawTexture(x, y, hitRadius, 43);
    }
}
