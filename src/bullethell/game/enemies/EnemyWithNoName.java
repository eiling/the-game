package bullethell.game.enemies;

import bullethell.game.Enemy;
import bullethell.game.bullets.BulletWithNoName;
import bullethell.math.Vec2f;
import bullethell.util.Bullets;

public class EnemyWithNoName extends Enemy{
    public EnemyWithNoName(float x, float y){
        super(x, y, 0.2f, 0.05f, new Vec2f(0f, 0f),
                0, 2, 500, 0.2f,
                500);
    }

    @Override
    protected void move(){
    }

    @Override
    protected void shoot(Bullets bullets){
        bullets.add(new BulletWithNoName(x + 0.2f, y, new Vec2f(0f, -1f)));
        bullets.add(new BulletWithNoName(x - 0.2f, y, new Vec2f(0f, -1f)));
    }
}
