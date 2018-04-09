package bullethell.game.bullets;

import bullethell.game.Bullet;
import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

public class BulletWithNoName extends Bullet{
    public BulletWithNoName(float x, float y, Vec2f direction){
        super(x,y,0.2f,0.2f, 0.2f,direction);
    }
}
