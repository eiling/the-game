package bullethell.graphic;

public class Canvas{
    private final static float scalex = 1f / 9f;
    private final static float scaley = 1f / 8f;
    private final float translatex;
    private final float translatey = -1f;

    private final static float borderwidth = 0.1f;

    public Canvas(int mode){
        if(mode == 1){
            translatex = -1f;
        } else if(mode == 2){
            translatex = 0f;
        } else{
            translatex = -0.5f;
        }
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

    }
}
