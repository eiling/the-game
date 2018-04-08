package bullethell.math;

public interface Trig{
    float PI = 3.14159265359f;
    float HALF_PI = PI * 0.5f;
    float TWO_PI = PI * 2f;
    float TWO_PI_INV = 1 / (PI * 2f);

    float a2 = 2f / PI - 12f / (PI * PI);
    float a3 = 16f / (PI * PI * PI) - 4f / (PI * PI);

    //from: http://allenchou.net/2014/02/game-math-faster-sine-cosine-with-polynomial-curves/
    private static float Hill(float x){
        /*final float a2 = 2f / PI - 12f / (PI * PI);
        final float a3 = 16f / (PI * PI * PI) - 4f / (PI * PI);*/
        final float xx = x * x;
        final float xxx = xx * x;

        return 1f + a2 * xx + a3 * xxx;
    }

    static float sin(float x){
        // wrap x within [0, TWO_PI)
        final float a = x * TWO_PI_INV;
        x -= (int)a * TWO_PI;
        if (x < 0.0f)
            x += TWO_PI;

        // 4 pieces of hills
        if (x < HALF_PI)
            return Hill(HALF_PI - x);
        else if (x < PI)
            return Hill(x - HALF_PI);
        else if (x < 3.0f * HALF_PI)
            return -Hill(3.0f * HALF_PI - x);
        else
            return -Hill(x - 3.0f * HALF_PI);
    }

    static float cos(float x){
        return sin(x + HALF_PI);
    }
}
