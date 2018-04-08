package bullethell.math;

public class Vec2f{
    public float x, y;

    public Vec2f(float x, float y){
        this.x = x; this.y = y;
    }

    public void add(Vec2f v){
        x += v.x; y += v.y;
    }
}
