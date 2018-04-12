package bullethell.game;

import bullethell.math.Vec2f;
import bullethell.util.Bullets;

public abstract class Entity extends Solid{
    long last, shootInterval;

    Entity(float x, float y, float scale, float velocity, Vec2f direction,
          int startingFrame, int numberOfFrames, long frameInterval, float hitRadius,
          long shootInterval, long last){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius);

        this.last = last;
        this.shootInterval = shootInterval;
    }

    public void update(Bullets bullets){
        update();

        long now = System.currentTimeMillis();
        if(last == -1){
            shoot(bullets);
            last = now;
        } else if(now - last >= shootInterval){
            shoot(bullets);
            last = now;
        }
    }

    protected abstract void shoot(Bullets bullets);
}
