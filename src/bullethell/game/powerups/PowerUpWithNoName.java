package bullethell.game.powerups;

import bullethell.game.PowerUp;
import bullethell.math.Vec2f;

public class PowerUpWithNoName extends PowerUp{
    public PowerUpWithNoName(float x, float y){
        super(x,y,0.2f,0.01f, new Vec2f(0f,0f),
                2,14,100, 0.2f);
    }

    @Override
    public void update(){
        updateAnimation();
    }

    @Override
    protected void move(){

    }
}
