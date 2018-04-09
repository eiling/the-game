package bullethell.math;

import static bullethell.math.WeirdMath.invSqrt;

public class Vec2f{
    public float x, y;

    public Vec2f(float x, float y){
        this.x = x; this.y = y;
    }

    public void normalize(){
        final float invNorm = invSqrt(x*x + y*y);
        x *= invNorm; y *= invNorm;
    }

    public void normalize2(){
        if(x == 1f && y == 1f){
            x = 0.70710678118f;
            y = 0.70710678118f;
        }
    }

    public void reset(){
        x = 0; y = 0;
    }
}
