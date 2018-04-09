package bullethell.game.bosses;

import bullethell.game.Boss;
import bullethell.math.Vec2f;

public class BossWithNoName extends Boss{
    public BossWithNoName(float x, float y){
        super(x,y,0.2f,0.01f,new Vec2f(0f,0f),
                0,2,100, 0.2f);
    }
    @Override
    public void update(){
    }

    @Override
    protected void move(){
    }
}
