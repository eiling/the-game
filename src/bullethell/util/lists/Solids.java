package bullethell.util.lists;

import bullethell.game.Solid;
import bullethell.game.Character;
import bullethell.graphic.Renderer;

public class Solids{
    private Node first;
    private Node last;

    public void add(Solid solid){
        if(isEmpty()){
            first = new Node(solid);
            last = first;
        }
        else{
            last.next = new Node(solid);
            last = last.next;
        }
    }

    private void remove(Solid solid){
        if(isEmpty()) return;

        if(first.solid == solid){
            first = first.next;
            return;
        }

        Node temp = first;
        while(temp.next != null){
            if(temp.next.solid == solid){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    public void update(float delta){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            Solid solid = temp.solid;
            solid.update(delta);
            if(solid.isOutOfCanvas()) remove(solid);
            temp = temp.next;
        }
    }

    public boolean collided(Character character){
        if(isEmpty()) return false;

        Node temp = first;
        while(temp != null) {
            if(character.collided(temp.solid)) return true;
            temp = temp.next;
        }
        return false;
    }

    public void handleCollisions(Bullets bullets, Explosions explosions){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            Solid solid = temp.solid;
            temp = temp.next;
            if(bullets.collided(solid, true)) {
                explosions.add(solid.explode());
                remove(solid);
            }
        }
    }

    public void render(Renderer renderer, float alpha){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            temp.solid.render(renderer, alpha);
            temp = temp.next;
        }
    }

    private boolean isEmpty(){
        return first == null;
    }

    private class Node{
        private final Solid solid;
        private Node next;

        private Node(Solid solid){
            this.solid = solid;
            next = null;
        }
    }
}
