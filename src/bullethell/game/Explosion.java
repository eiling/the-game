package bullethell.game;

public abstract class Explosion extends Animated{
    protected Explosion(float x, float y, float scale, int startingFrame, int numberOfFrames, long frameInterval){
        super(x,y,scale,0f,null,startingFrame,numberOfFrames,frameInterval);
    }
}
