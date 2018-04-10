package bullethell.game.characters;

import bullethell.game.Character;
import bullethell.math.Vec2f;

public class CharacterWithNoName extends Character{
    public CharacterWithNoName(float x, float y){
        super(x, y, 0.2f, 0.05f, new Vec2f(0f, 0f),
                16, 2, 500, 0.05f);
    }
}
