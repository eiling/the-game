package bullethell.util;

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
                break;
            }
            temp = temp.next;
        }
    }

    public void update(){
        if(isEmpty()) return;

        Node temp = first;
        while(temp.next != null){
            temp.bullet.update();
            temp = temp.next;
        }
    }

    public boolean collided(Solid solid){
        Node temp = first;
        while(temp.next != null) {
            if(solid.collided(temp.bullet)) return true;
            temp = temp.next;
        }
        return false;
    }

    public void render(Renderer renderer){
        if(isEmpty()) return;

        Node temp = first;
        while(temp.next != null){
            temp.bullet.render(renderer);
            temp = temp.next;
        }
    }

    private boolean isEmpty(){
        return first == null;
    }

    private class Node{
        private Bullet bullet;
        private Node next;

        private Node(Bullet bullet){
            this.bullet = bullet;
            next = null;
        }
    }
}
