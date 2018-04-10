package bullethell.game.destroyables;

import bullethell.game.Destroyable;
import bullethell.math.Vec2f;

public class DestroyableWithNoName extends Destroyable{
    public DestroyableWithNoName(float x, float y){
        super(x, y, 0.2f, 0, new Vec2f(0f, 0f),
                2, 14, 100, 0.2f);
    }
}
