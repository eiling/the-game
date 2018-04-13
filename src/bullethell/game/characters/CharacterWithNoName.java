package bullethell.game.characters;

import bullethell.game.Character;
import bullethell.game.Explosion;
import bullethell.game.bullets.BulletWithNoName;
import bullethell.math.Vec2f;
import bullethell.util.lists.Bullets;

public class CharacterWithNoName extends Character{
    public CharacterWithNoName(float x, float y){
        super(x, y, 0.2f, 0.05f, new Vec2f(0f, 0f),
                16, 2, 500, 0.05f,
                100);
    }

    @Override
    protected void shoot(Bullets bullets){
        bullets.add(new BulletWithNoName(x, y + 0.16f, new Vec2f(0f,1f)));
    }

    @Override
    public Explosion explode(){ return null;}
}
