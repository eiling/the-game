package bullethell.game.enemies;

import bullethell.game.Enemy;
import bullethell.game.Timed;
import bullethell.game.bullets.BulletWithNoName;
import bullethell.game.explosions.ExplosionWithNoName;
import bullethell.game.solid.SolidWithNoName;
import bullethell.math.Vec2f;

import static bullethell.math.Trig.*;

public class EnemyWithNoName extends Enemy{
    private static final float N = TWO_PI/12;
    private float phi = 0;

    public EnemyWithNoName(float x, float y){
        super(x, y, 1f, 2f, new Vec2f(1f, 0f),
                0, 1, 500, 1f,
                10, 1000);
        solids.add(new SolidWithNoName(x, y));
    }

    @Override
    protected void move(float delta){
        //x += delta * direction.x * velocity;

        if(x > 8f) direction.x = -1f;
        else if(x < 1f) direction.x = 1f;
    }

    @Override
    protected void shoot(){
        for(int i = 0; i < 12; i ++){
            float theta = i*N;
            bullets.add(new BulletWithNoName(x, y, new Vec2f(cos(theta + phi), sin(theta + phi))));
        }
        phi += 0.2f;
    }

    @Override
    public Timed explode(){
        return new ExplosionWithNoName(x, y);
    }
}
