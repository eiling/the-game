package bullethell.util.lists;

import bullethell.game.Timed;
import bullethell.graphic.Renderer;

public class Timeds {
    private Node first;
    private Node last;

    public void add(Timed timed){
        if(isEmpty()){
            first = new Node(timed);
            last = first;
        } else{
            last.next = new Node(timed);
            last = last.next;
        }
    }

    private void remove(Timed timed){
        if(isEmpty()) return;

        if(first.timed == timed){
            first = first.next;
            return;
        }

        Node temp = first;
        while(temp.next != null){
            if(temp.next.timed == timed){
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
            Timed t = temp.timed;
            temp = temp.next;
            t.update();
            if(t.isOver()) remove(t);
        }
    }

    public void render(Renderer renderer){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            temp.timed.render(renderer);
            temp = temp.next;
        }
    }

    private boolean isEmpty(){
        return first == null;
    }

    private class Node{
        private final Timed timed;
        private Node next;

        private Node(Timed timed){
            this.timed = timed;
            next = null;
        }
    }
}
