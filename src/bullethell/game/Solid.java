package bullethell.game;

import bullethell.math.Vec2f;

public abstract class Solid extends Cyclic{
    int life;

    public Solid(float x, float y, float scale, float velocity, Vec2f direction, int startingFrame, int numberOfFrames,
                 long frameInterval, float hitRadius, int life){
        super(x, y, scale, velocity, direction, startingFrame, numberOfFrames, frameInterval, hitRadius);

        this.life = life;
    }

    public boolean damage(int damage){
        life -= damage;
        return life <= 0;
    }
}
