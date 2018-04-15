package bullethell.game.bullets;

import bullethell.game.Bullet;
import bullethell.math.Vec2f;

public class Bullet2 extends Bullet{
    public Bullet2(float x, float y, Vec2f direction){
        super(x, y, 0.05f, 0.05f, 1.5f, direction, 23);
    }
}
