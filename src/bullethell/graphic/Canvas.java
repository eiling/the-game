package bullethell.graphic;

import static bullethell.graphic.Renderer.renderer;

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

    private final float BACKGROUND_X1;
    private final float BACKGROUND_X2;
    private static final float BACKGROUND_Y1 = (MIN_Y * scaley + translatey) * (1 - borderwidth);
    private static final float BACKGROUND_Y2 = (MAX_Y * scaley + translatey) * (1 - borderwidth);

    public Canvas(float x){
        translatex = x;
        BACKGROUND_X1 = (MIN_X * scalex + translatex) * (1 - borderwidth);
        BACKGROUND_X2 = (MAX_X * scalex + translatex) * (1 - borderwidth);
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

    public void drawBorder(){
        for(float x = -0.5f; x <= 0.501f; x += borderwidth * 0.1f)
            renderer.drawTextureSquare(x, -0.95f, borderwidth * 0.5f, 51);
        for(float y = -0.95f; y <= 0.951f; y += borderwidth * 0.1f)
            renderer.drawTextureSquare(0.5f, y, borderwidth * 0.5f, 51);
        for(float x = -0.5f; x <= 0.501f; x += borderwidth * 0.1f)
            renderer.drawTextureSquare(x, 0.95f, borderwidth * 0.5f, 51);
        for(float y = -0.95f; y <= 0.951f; y += borderwidth * 0.1f)
            renderer.drawTextureSquare(-0.5f, y, borderwidth * 0.5f, 51);
    }

    public void drawBackground(){
        renderer.drawVertex(BACKGROUND_X1, BACKGROUND_Y1, 0f, 0f);
        renderer.drawVertex(BACKGROUND_X2, BACKGROUND_Y1, 0f, 0f);
        renderer.drawVertex(BACKGROUND_X2, BACKGROUND_Y2, 0f, 0f);
        renderer.drawVertex(BACKGROUND_X1, BACKGROUND_Y1, 0f, 0f);
        renderer.drawVertex(BACKGROUND_X2, BACKGROUND_Y2, 0f, 0f);
        renderer.drawVertex(BACKGROUND_X1, BACKGROUND_Y2, 0f, 0f);
    }
}
