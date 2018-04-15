package bullethell.game;

import bullethell.math.Vec2f;
import bullethell.util.lists.Bullets;

public abstract class Enemy extends Entity{
    private int life;

    protected Enemy(float x, float y, float scale, float velocity, Vec2f direction,
                    int startingFrame, int finalFrame, long frameInterval, float hitRadius,
                    long shootInterval, int life){
        super(x, y, scale, velocity, direction, startingFrame, finalFrame, frameInterval, hitRadius,
                shootInterval, System.currentTimeMillis());

        this.life = life;

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

    public boolean damage(int damage){
        life -= damage;
        return life == 0;
    }
}
