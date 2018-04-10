package bullethell.math;

public class Vec2f{
    public float x, y;

    public Vec2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void normalize(){
        if(x == 1f && y == 1f){
            x = 0.70710678118f;
            y = 0.70710678118f;
        }
    }

    public void reset(){
        x = 0;
        y = 0;
    }
}
