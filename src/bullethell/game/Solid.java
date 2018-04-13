package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

public abstract class Solid extends Animated{
    float hitRadius;

    Solid(float x, float y, float scale, float velocity, Vec2f direction,
          int startingFrame, int numberOfFrames, long frameInterval, float hitRadius){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval);

        this.hitRadius = hitRadius;
    }

    @Override
    public void update(float delta){
        prevx = x;
        prevy = y;

        move(delta);
        updateAnimation();
    }

    public boolean collided(Bullet bullet){
        return checkCollision(x - bullet.x, y - bullet.y, hitRadius + bullet.hitRadius);
    }

    static boolean checkCollision(float dx, float dy, float minDist){
        final float dx2 = dx * dx;
        final float dy2 = dy * dy;

        return dx2 + dy2 < minDist * minDist;
    }

    public boolean isOutOfScreen(){
        return isOutside(x - hitRadius, x + hitRadius, y - hitRadius, y + hitRadius);
    }

    public abstract Explosion explode();

    @Override
    public void drawHitRadius(Renderer renderer){
        renderer.drawTexture(x, y, hitRadius, 43);
    }
}
