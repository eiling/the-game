package bullethell.game.explosions;

import bullethell.game.Explosion;
import bullethell.graphic.Renderer;

import java.util.concurrent.ThreadLocalRandom;

public class ExplosionWithNoName extends Explosion{
    public ExplosionWithNoName(float x, float y){
        super(x, y, (float) ThreadLocalRandom.current().nextDouble(0.1,0.4), 18,
                16, 100);
    }

    @Override
    public void drawHitRadius(Renderer renderer){
    }
}
