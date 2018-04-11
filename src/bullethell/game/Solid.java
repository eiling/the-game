package bullethell.game;

import bullethell.game.explosions.ExplosionWithNoName;
import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;
import bullethell.util.Bullets;

public abstract class Solid extends Animated{
    protected float hitRadius;
    private boolean shooting;
    private long last, shootInterval;

    Solid(float x, float y, float scale, float velocity, Vec2f direction,
          int startingFrame, int numberOfFrames, long frameInterval, float hitRadius,
          long shootInterval){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval);
        this.hitRadius = hitRadius;

        shooting = true;
        last = -1;
        this.shootInterval = shootInterval;
    }

    public void update(Bullets bullets){
        update();

        if(shooting){
            long now = System.currentTimeMillis();
            if(last == -1){
                shoot(bullets);
                last = now;
            } else if(now - last >= shootInterval){
                shoot(bullets);
                last += shootInterval;
            }
        }
    }

    @Override
    public void update(){
        move();
        updateAnimation();
    }

    @Override
    protected void move(){
        direction.normalize();
        x += velocity * direction.x;
        y += velocity * direction.y;
        direction.reset();

        if(x - hitRadius < -1f) x = -1f + hitRadius;
        else if(x + hitRadius > 1f) x = 1f - hitRadius;
        if(y - hitRadius < -1f) y = -1f + hitRadius;
        else if(y + hitRadius > 1f) y = 1f - hitRadius;
    }

    protected abstract void shoot(Bullets bullets);

    public boolean collided(Bullet bullet){
        return checkCollision(x - bullet.x, y - bullet.y, hitRadius + bullet.hitRadius);
    }

    static boolean checkCollision(float dx, float dy, float minDist){
        final float dx2 = dx * dx;
        final float dy2 = dy * dy;
        final float d = (float) Math.sqrt(dx2 + dy2);

        return d < minDist;
    }

    public boolean isOutOfScreen(){
        return isOutside(x - hitRadius, x + hitRadius, y - hitRadius, y + hitRadius);
    }

    public Explosion explode(){ return new ExplosionWithNoName(0,0);}

    @Override
    public void drawHitRadius(Renderer renderer){
        renderer.drawTexture(x, y, hitRadius, 43);
    }
}
