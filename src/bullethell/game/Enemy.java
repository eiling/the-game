package bullethell.game;

import bullethell.math.Vec2f;
import bullethell.util.lists.Bullets;

public abstract class Enemy extends Entity{
    protected Enemy(float x, float y, float scale, float velocity, Vec2f direction,
                    int startingFrame, int finalFrame, long frameInterval, float hitRadius,
                    int life, long shootInterval){
        super(x, y, scale, velocity, direction, startingFrame, finalFrame, frameInterval, hitRadius,
                life, shootInterval, System.currentTimeMillis());

        bullets = new Bullets();
    }

    @Override
    public void update(float delta){
        super.update(delta);

        long now = System.currentTimeMillis();
        if(last == -1){
            shoot();
            last = now;
        } else if(now - last >= shootInterval){
            shoot();
            last = now;
        }
    }
}
