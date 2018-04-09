package bullethell.game.explosions;

import bullethell.game.Explosion;

public class ExplosionWithNoName extends Explosion{
    protected ExplosionWithNoName(float x, float y){
        super(x, y, 0.2f, 0, 2, 100);
    }

    @Override
    public void update(){
    }

    @Override
    protected void move(){
    }
}
