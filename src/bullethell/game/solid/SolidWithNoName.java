package bullethell.game.solid;

import bullethell.game.Solid;
import bullethell.game.Timed;
import bullethell.game.explosions.ExplosionWithNoName;
import bullethell.math.Vec2f;

public class SolidWithNoName extends Solid{
    public SolidWithNoName(float x, float y){
        super(x, y, 2f, 0.5f, new Vec2f(1f, 0f),
                31, 31, 0, 2f, 1);
    }

    @Override
    protected void move(float delta){
        x += delta * velocity * direction.x;
        y += delta * velocity * direction.y;

        direction.x = character.x - x;
        direction.y = character.y - y;
        direction.normalize();
    }

    @Override
    public Timed explode(){
        return new ExplosionWithNoName(x, y);
    }
}
