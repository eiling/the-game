package bullethell.game;

public abstract class Explosion extends TimedAnimated{
    protected Explosion(float x, float y, float scale, int startingFrame, int numberOfFrames, long frameInterval){
        super(x,y,scale,0f,null,startingFrame,numberOfFrames,frameInterval, 1);
    }

    @Override
    public void update(){
        updateAnimation();
    }

    @Override
    protected void move(){} //it doesn't move!
}
