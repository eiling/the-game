package bullethell.util.lists;

import bullethell.game.Entity;
import bullethell.game.Character;
import bullethell.graphic.Renderer;

public class Entities{
    private Node first;
    private Node last;

    public void add(Entity entity){
        if(isEmpty()){
            first = new Node(entity);
            last = first;
        }
        else{
            last.next = new Node(entity);
            last = last.next;
        }
    }

    private void remove(Entity entity){
        if(isEmpty()) return;

        if(first.entity == entity){
            first = first.next;
            return;
        }

        Node temp = first;
        while(temp.next != null){
            if(temp.next.entity == entity){
                temp.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    public void update(Bullets bullets){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            Entity entity = temp.entity;
            entity.update(bullets);
            if(entity.isOutOfScreen()) remove(entity);
            temp = temp.next;
        }
    }

    public boolean collided(Character character){
        if(isEmpty()) return false;

        Node temp = first;
        while(temp != null) {
            if(character.collided(temp.entity)) return true;
            temp = temp.next;
        }
        return false;
    }

    public void handleCollisions(Bullets bullets, Explosions explosions){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            Entity entity = temp.entity;
            temp = temp.next;
            if(bullets.collided(entity)) {
                explosions.add(entity.explode());
                remove(entity);
            }
        }
    }

    public void render(Renderer renderer){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            temp.entity.render(renderer);
            temp = temp.next;
        }
    }

    private boolean isEmpty(){
        return first == null;
    }

    private class Node{
        private Entity entity;
        private Node next;

        private Node(Entity entity){
            this.entity = entity;
            next = null;
        }
    }
}
