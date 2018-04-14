package bullethell.game;

import bullethell.math.Vec2f;
import bullethell.util.lists.Bullets;

public abstract class Entity extends Solid{
    long last, shootInterval;

    public Bullets bullets;

    Entity(float x, float y, float scale, float velocity, Vec2f direction,
          int startingFrame, int numberOfFrames, long frameInterval, float hitRadius,
          long shootInterval, long last){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius);

        this.last = last;
        this.shootInterval = shootInterval;

        bullets = new Bullets();
    }

    protected abstract void shoot();
}
