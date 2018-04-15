package bullethell.math;

import static bullethell.math.Trig.*;

public class Vec2f{
    public float x, y;

    public Vec2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void normalize(){
        if((x == 1f || x == -1f) && (y == 1f || y == -1f)){
            x *= 0.70710678118f;
            y *= 0.70710678118f;
        }
    }

    public void reset(){
        x = 0;
        y = 0;
    }

    public void rotate(float theta){
        final float tempx = x;
        final float tempy = y;
        final float cos = cos(theta);
        final float sin = sin(theta);
        x = tempx * cos - tempy * sin;
        y = tempx * sin + tempy * cos;
    }
}
