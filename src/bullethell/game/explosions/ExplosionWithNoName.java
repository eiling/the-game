package bullethell.game.explosions;

import bullethell.game.Explosion;
import bullethell.graphic.Renderer;

public class ExplosionWithNoName extends Explosion{
    public ExplosionWithNoName(float x, float y){
        super(x, y, 0.2f, 27, 16, 100);
    }

    @Override
    public void drawHitRadius(Renderer renderer){
    }
}
