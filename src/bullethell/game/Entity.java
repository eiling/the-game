package bullethell.game;

import bullethell.math.Vec2f;
import bullethell.util.Bullets;

public abstract class Entity extends Solid{
    private boolean shooting;
    private long last, shootInterval;

    Entity(float x, float y, float scale, float velocity, Vec2f direction,
          int startingFrame, int numberOfFrames, long frameInterval, float hitRadius,
          long shootInterval){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius);

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

    protected abstract void shoot(Bullets bullets);
}
