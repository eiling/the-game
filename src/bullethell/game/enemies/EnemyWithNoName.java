package bullethell.game.enemies;

import bullethell.game.Enemy;
import bullethell.math.Vec2f;

public class EnemyWithNoName extends Enemy{
    public EnemyWithNoName(float x, float y){
        super(x, y, 0.2f, 0.05f, new Vec2f(0f, 0f),
                0, 2, 500, 0.2f);
    }

    @Override
    protected void move(){
    }
}
