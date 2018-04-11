package bullethell.game;

import bullethell.graphic.Renderer;
import bullethell.math.Vec2f;

public abstract class Bullet extends GameObject{
    float hitRadius;
    private int texID;

    public Bullet(float x, float y, float scale, float hitRadius, float velocity, Vec2f direction, int texID){
        super(x, y, scale, velocity, direction);
        this.hitRadius = hitRadius;
        this.texID = texID;
    }

    @Override
    public void update(){
        move();
    }

    protected void move(){
        x += velocity * direction.x;
        y += velocity * direction.y;
    }

    public boolean isOutOfScreen(){
        return isOutside(x - hitRadius, x + hitRadius, y - hitRadius, y + hitRadius);
    }

    @Override
    public void render(Renderer renderer){
        renderer.drawTexture(x, y, scale, texID);
    }
}
