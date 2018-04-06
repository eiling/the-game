package bullethell.game;

import bullethell.graphic.Renderer;

public class Ship{
    private float x, y;
    private final float hitRadius = 0.2f;

    private final float[] color = {1f,0f,1f};

    public Ship(float x, float y){
        this.x = x; this.y = y;
    }

    public void render(Renderer renderer){
        renderer.drawPolygon(x, y, hitRadius, color, 36);
    }
}
