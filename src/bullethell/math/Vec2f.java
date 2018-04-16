package bullethell.math;

import static bullethell.math.Trig.*;
import static bullethell.math.WeirdMath.*;

public class Vec2f{
    public float x, y;

    public Vec2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void normalize(){
        final float norma2 = x * x + y * y;
        final float invNorma = invSqrt(norma2);

        x *= invNorma;
        y *= invNorma;
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
