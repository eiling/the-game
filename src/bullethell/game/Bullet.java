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
    public void update(float delta){
        prevx = x;
        prevy = y;

        move(delta);
    }

    protected void move(float delta){
        x += delta * velocity * direction.x;
        y += delta * velocity * direction.y;
    }

    public boolean isOutOfScreen(){
        return isOutside(x - hitRadius, x + hitRadius, y - hitRadius, y + hitRadius);
    }

    @Override
    public void render(Renderer renderer, float alpha){
        float interpolatedX = prevx * (1 - alpha) + x * alpha;
        float interpolatedY = prevy * (1 - alpha) + y * alpha;
        renderer.drawTexture(interpolatedX, interpolatedY, scale, texID);
    }
}
