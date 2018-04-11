package bullethell.game.bosses;

import bullethell.game.Boss;
import bullethell.math.Vec2f;
import bullethell.util.Bullets;

public class BossWithNoName extends Boss{
    public BossWithNoName(float x, float y){
        super(x, y, 0.2f, 0.01f, new Vec2f(0f, 0f),
                0, 2, 100, 0.2f,
                500);
    }

    @Override
    protected void move(){
    }

    @Override
    protected void shoot(Bullets bullets){
    }
}
