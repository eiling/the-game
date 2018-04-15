package bullethell.game;

public abstract class Timed extends Animated{
    private boolean finished;

    public Timed(float x, float y, float scale, int startingFrame, int finalFrame, long frameInterval){
        super(x, y, scale, startingFrame, finalFrame, frameInterval);

        finished = false;
    }

    @Override
    void updateAnimation(){
        if(lastFrameTime == -1) lastFrameTime = System.currentTimeMillis();
        else{
            long now = System.currentTimeMillis();
            long elapsed = now - lastFrameTime;
            if(elapsed > frameInterval){
                lastFrameTime = now - elapsed + frameInterval;
                if(currentAnimationFrame++ == finalFrame) finished = true;
            }
        }
    }

    public boolean isOver(){
        return finished;
    }
}
