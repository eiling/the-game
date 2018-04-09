package bullethell.math;

public class WeirdMath{
    public static float invSqrt(float x) {
        float xHalf = 0.5f * x;
        int i = Float.floatToIntBits(x); // store floating-point bits in integer
        i = 0x5f3759df - (i >> 1);       // initial guess for Newton's method
        x = Float.intBitsToFloat(i);     // convert new bits into float
        x *= (1.5f - xHalf * x * x);     // One round of Newton's method
        return x;
    }
}
