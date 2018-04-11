package bullethell.game.explosions;

import bullethell.game.Explosion;
import bullethell.graphic.Renderer;

import java.util.concurrent.ThreadLocalRandom;

public class ExplosionWithNoName extends Explosion{
    public ExplosionWithNoName(float x, float y){
        super(x, y, 0.2f, 18, 16, 100);
    }

    @Override
    public void drawHitRadius(Renderer renderer){
    }
}
