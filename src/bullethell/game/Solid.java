package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

public abstract class Solid extends Animated{
    protected float hitRadius;

    Solid(float x, float y, float scale, float velocity, Vec2f direction,
          int startingFrame, int numberOfFrames, long frameInterval, float hitRadius){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval);
        this.hitRadius = hitRadius;
    }

    public boolean collided(Bullet bullet){
        return checkCollision(x - bullet.x, y - bullet.y, hitRadius + bullet.hitRadius);
    }

    static boolean checkCollision(float dx, float dy, float minDist){
        final float dx2 = dx * dx;
        final float dy2 = dy * dy;
        final float d = (float) Math.sqrt(dx2 + dy2);

        return d < minDist;
    }

    @Override
    public void drawHitRadius(Renderer renderer){
        renderer.drawTexture(x, y, hitRadius, 34);
    }
}
