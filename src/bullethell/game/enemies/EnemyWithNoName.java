package bullethell.game.enemies;

import bullethell.game.Enemy;
import bullethell.game.Explosion;
import bullethell.game.bullets.BulletWithNoName;
import bullethell.game.explosions.ExplosionWithNoName;
import bullethell.math.Vec2f;
import bullethell.util.Bullets;

import static bullethell.math.Trig.*;

public class EnemyWithNoName extends Enemy{
    static final float N = TWO_PI/24;

    public EnemyWithNoName(float x, float y){
        super(x, y, 0.2f, 0.05f, new Vec2f(0f, 0f),
                0, 2, 500, 0.2f,
                1000);
    }

    @Override
    protected void move(){
    }

    @Override
    protected void shoot(Bullets bullets){
        for(int i = 16; i <= 20; i ++){
            float theta = i*N;
            bullets.add(new BulletWithNoName(x, y, new Vec2f(cos(theta), sin(theta))));
        }
    }

    @Override
    public Explosion explode(){
        return new ExplosionWithNoName(x, y);
    }
}
