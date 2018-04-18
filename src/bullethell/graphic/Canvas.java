package bullethell.graphic;

public class Canvas{
    private static final float scalex = 1f / 9f;
    private static final float scaley = 1f / 8f;
    private final float translatex; //different for each canvas
    private static final float translatey = -1f;

    private final static float borderwidth = 0.1f;

    public static final float MAX_X = 9f;
    public static final float MIN_X = 0f;
    public static final float MAX_Y = 16f;
    public static final float MIN_Y = 0f;

    public final float SCISSOR_X;
    public final float SCISSOR_WIDTH;
    public static final float SCISSOR_Y = (MIN_Y * scaley + translatey) * (1 - borderwidth);
    public static final float SCISSOR_HEIGHT = (MAX_Y * scaley + translatey) * (1 - borderwidth) - SCISSOR_Y;

    public Canvas(float x){
        translatex = x;
        SCISSOR_X = (MIN_X * scalex + translatex) * (1 - borderwidth);
        SCISSOR_WIDTH = (MAX_X * scalex + translatex) * (1 - borderwidth) - SCISSOR_X;
    }

    public float scaledx(float x){
        return (x * scalex + translatex) * (1 - borderwidth);
    }

    public float scaledy(float y){
        return (y * scaley + translatey) * (1 - borderwidth);
    }

    public float scale(float k){
        return k * scalex * (1 - borderwidth);
    }

    public void drawBorder(Renderer renderer){
        for(float x = -0.5f; x <= 0.501f; x += borderwidth * 0.1f)
            renderer.drawTexture(x, -0.95f, borderwidth * 0.5f, 51);
        for(float y = -0.95f; y <= 0.951f; y += borderwidth * 0.1f)
            renderer.drawTexture(0.5f, y, borderwidth * 0.5f, 51);
        for(float x = -0.5f; x <= 0.501f; x += borderwidth * 0.1f)
            renderer.drawTexture(x, 0.95f, borderwidth * 0.5f, 51);
        for(float y = -0.95f; y <= 0.951f; y += borderwidth * 0.1f)
            renderer.drawTexture(-0.5f, y, borderwidth * 0.5f, 51);
    }
}
