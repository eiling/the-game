package bullethell.game.bullets;

import bullethell.game.Bullet;
import bullethell.game.Timed;
import bullethell.game.explosions.ExplosionWithNoName;
import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

public class Bullet2 extends Bullet{
    public Bullet2(float x, float y, Vec2f direction){
        super(x, y, 1f, 20, 23, 50, 16f, direction, 1f);
    }

    @Override
    public Timed explode() {
        return new ExplosionWithNoName(x, y);
    }
}
