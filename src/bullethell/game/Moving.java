package bullethell.game;

import bullethell.math.Vec2f;

import static bullethell.graphic.Canvas.*;

public abstract class Moving extends Animated{
    public static Character character;

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
        if(x + hitRadius < MIN_X) return true;
        if(x - hitRadius > MAX_X) return true;
        if(y + hitRadius < MIN_Y) return true;
        if(y - hitRadius > MAX_Y) return true;
        return false;
    }

    @Override
    public void drawHitRadius(){
        //renderer.drawTexture(canvas.scaledx(x), canvas.scaledy(y), canvas.scale(hitRadius), 51);
    }
}
