package bullethell.game.bosses;

import bullethell.game.Boss;
import bullethell.game.Explosion;
import bullethell.game.explosions.ExplosionWithNoName;
import bullethell.math.Vec2f;
import bullethell.util.lists.Bullets;

public class BossWithNoName extends Boss{
    public BossWithNoName(float x, float y){
        super(x, y, 0.2f, 0.01f, new Vec2f(0f, 0f),
                0, 2, 100, 0.2f,
                500);
    }

    @Override
    protected void move(float delta){
    }

    @Override
    protected void shoot(Bullets bullets){
    }

    @Override
    public Explosion explode(){
        return new ExplosionWithNoName(x, y);
    }
}
