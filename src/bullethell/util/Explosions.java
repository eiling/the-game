package bullethell.util;

import bullethell.game.Explosion;
import bullethell.graphic.Renderer;

public class Explosions{
    private Node first;
    private Node last;

    public void add(Explosion explosion){
        if(isEmpty()){
            first = new Node(explosion);
            last = first;
        } else{
            last.next = new Node(explosion);
            last = last.next;
        }
    }

    private void remove(Explosion explosion){
        if(isEmpty()) return;

        if(first.explosion == explosion){
            first = first.next;
            return;
        }

        Node temp = first;
        while(temp.next != null){
            if(temp.next.explosion == explosion){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    public void update(){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            Explosion e = temp.explosion;
            temp = temp.next;
            e.update();
            if(e.isOver()) remove(e);
        }
    }

    public void render(Renderer renderer){
        if(isEmpty()) return;

        Node temp = first;
        while(temp.next != null){
            temp.explosion.render(renderer);
            temp = temp.next;
        }
    }

    private boolean isEmpty(){
        return first == null;
    }

    private class Node{
        private Explosion explosion;
        private Node next;

        private Node(Explosion explosion){
            this.explosion = explosion;
            next = null;
        }
    }
}
