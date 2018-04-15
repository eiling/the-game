package bullethell.util.lists;

import bullethell.game.Bullet;
import bullethell.game.Solid;
import bullethell.graphic.Renderer;

public class Bullets{
    private Node first;
    private Node last;

    public void add(Bullet bullet){
        if(isEmpty()){
            first = new Node(bullet);
            last = first;
        } else{
            last.next = new Node(bullet);
            last = last.next;
        }
    }

    private void remove(Bullet bullet){
        if(isEmpty()) return;

        if(first.bullet == bullet){
            first = first.next;
            return;
        }

        Node temp = first;
        while(temp.next != null){
            if(temp.next.bullet == bullet){
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    public void update(float delta){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            Bullet bullet = temp.bullet;
            bullet.update(delta);
            if(bullet.isOutOfCanvas()) remove(bullet);
            temp = temp.next;
        }
    }

    boolean collided(Solid solid, boolean isFromPlayer){
        if(isEmpty()) return false;

        Node temp = first;
        while(temp != null) {
            if(solid.collided(temp.bullet)){
                if(isFromPlayer) remove(temp.bullet);
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void render(Renderer renderer, float alpha){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            temp.bullet.render(renderer, alpha);
            temp = temp.next;
        }
    }

    private boolean isEmpty(){
        return first == null;
    }

    private class Node{
        private final Bullet bullet;
        private Node next;

        private Node(Bullet bullet){
            this.bullet = bullet;
            next = null;
        }
    }
}
