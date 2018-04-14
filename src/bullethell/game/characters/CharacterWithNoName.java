package bullethell.game.characters;

import bullethell.game.Character;
import bullethell.game.Explosion;
import bullethell.game.bullets.Bullet2;
import bullethell.math.Vec2f;

public class CharacterWithNoName extends Character{
    public CharacterWithNoName(float x, float y){
        super(x, y, 0.2f, 1f, new Vec2f(0f, 0f),
                16, 2, 500, 0.05f,
                200, 1);
    }

    @Override
    protected void shoot(){
        bullets.add(new Bullet2(x, y + 0.16f, new Vec2f(0f,1f)));
    }

    @Override
    public Explosion explode(){ return null;}
}
