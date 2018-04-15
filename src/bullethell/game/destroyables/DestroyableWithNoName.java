package bullethell.game.destroyables;

import bullethell.game.Solid;
import bullethell.game.Timed;
import bullethell.math.Vec2f;

public class DestroyableWithNoName extends Solid{
    public DestroyableWithNoName(float x, float y){
        super(x, y, 0.2f, 1f, new Vec2f(1f, 0f),
                29, 29, 0, 0.2f, 5);
    }

    @Override
    protected void move(float delta){
        x += delta * velocity * direction.x;
        y += delta * velocity * direction.y;

        direction.rotate(-0.1f);
    }

    @Override
    public Timed explode(){
        return null;
    }
}
