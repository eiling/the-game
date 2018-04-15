package bullethell.game.bullets;

import bullethell.game.Bullet;
import bullethell.game.Timed;
import bullethell.game.explosions.ExplosionWithNoName;
import bullethell.math.Vec2f;

public class BulletWithNoName extends Bullet{
    public BulletWithNoName(float x, float y, Vec2f direction){
        super(x, y, 0.05f, 18, 21, 0, 1.5f, direction, 0.05f);
    }

    @Override
    public Timed explode() {
        return new ExplosionWithNoName(x, y);
    }
}
