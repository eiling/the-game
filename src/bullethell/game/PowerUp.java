package bullethell.game;

import bullethell.math.Vec2f;

public class PowerUp extends Entity{
    public PowerUp(float x, float y){
        super(0.2f,0.2f,4,14,80);

        this.x = x;
        this.y = y;

        velocity = 0;
        direction = new Vec2f(0f,0f);
    }

    @Override
    public void update(){
        updateAnimation();
    }
}
