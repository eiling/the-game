package bullethell.game.destroyables;

import bullethell.game.Cyclic;
import bullethell.game.Timed;
import bullethell.math.Vec2f;

public class DestroyableWithNoName extends Cyclic {
    public DestroyableWithNoName(float x, float y){
        super(x, y, 0.2f, 0, new Vec2f(0f, 0f),
                2, 15, 100, 0.2f);
    }

    @Override
    protected void move(float delta){
    }

    @Override
    public Timed explode(){
        return null;
    }
}
