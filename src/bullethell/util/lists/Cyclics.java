package bullethell.util.lists;

import bullethell.game.Enemy;
import bullethell.game.Character;
import bullethell.graphic.Renderer;

public class Cyclics{
    private Node first;
    private Node last;

    public void add(Enemy enemy){
        if(isEmpty()){
            first = new Node(enemy);
            last = first;
        }
        else{
            last.next = new Node(enemy);
            last = last.next;
        }
    }

    private void remove(Enemy enemy){
        if(isEmpty()) return;

        if(first.enemy == enemy){
            first = first.next;
            return;
        }

        Node temp = first;
        while(temp.next != null){
            if(temp.next.enemy == enemy){
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
            Enemy enemy = temp.enemy;
            enemy.update(delta);
            enemy.bullets.update(delta);
            if(enemy.isOutOfCanvas()) remove(enemy);
            temp = temp.next;
        }
    }

    public boolean collided(Character character){
        if(isEmpty()) return false;

        Node temp = first;
        while(temp != null) {
            if(character.collided(temp.enemy)) return true;
            if(temp.enemy.bullets.collided(character, false)) return true;
            temp = temp.next;
        }
        return false;
    }

    public void handleCollisions(Character character, Timeds timeds){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            Enemy enemy = temp.enemy;
            temp = temp.next;
            if(character.bullets.collided(enemy, true)){
                if(enemy.damage(character.damage)){
                    timeds.add(enemy.explode());
                    remove(enemy);
                }
            }
        }
    }

    public void render(Renderer renderer, float alpha){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            temp.enemy.render(renderer, alpha);
            temp.enemy.bullets.render(renderer, alpha);
            temp = temp.next;
        }
    }

    private boolean isEmpty(){
        return first == null;
    }

    private class Node{
        private final Enemy enemy;
        private Node next;

        private Node(Enemy enemy){
            this.enemy = enemy;
            next = null;
        }
    }
}
