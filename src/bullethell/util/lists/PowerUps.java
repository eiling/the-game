package bullethell.util.lists;

import bullethell.game.Character;
import bullethell.game.PowerUp;
import bullethell.graphic.Renderer;

public class PowerUps{
    private Node first;
    private Node last;

    public void add(PowerUp powerUp){
        if(isEmpty()){
            first = new Node(powerUp);
            last = first;
        }
        else{
            last.next = new Node(powerUp);
            last = last.next;
        }
    }

    private void remove(PowerUp powerUp){
        if(isEmpty()) return;

        if(first.powerUp == powerUp){
            first = first.next;
            return;
        }

        Node temp = first;
        while(temp.next != null){
            if(temp.next.powerUp == powerUp){
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
            PowerUp powerUp = temp.powerUp;
            powerUp.update(delta);
            if(powerUp.isOutOfCanvas()) remove(powerUp);
            temp = temp.next;
        }
    }

    public boolean collided(Character character){
        if(isEmpty()) return false;

        Node temp = first;
        while(temp != null) {
            if(character.collided(temp.powerUp)) return true;
            temp = temp.next;
        }
        return false;
    }

    public void render(float alpha){
        if(isEmpty()) return;

        Node temp = first;
        while(temp != null){
            temp.powerUp.render(alpha);
            temp = temp.next;
        }
    }

    private boolean isEmpty(){
        return first == null;
    }

    private class Node{
        private final PowerUp powerUp;
        private Node next;

        private Node(PowerUp powerUp){
            this.powerUp = powerUp;
            next = null;
        }
    }
}
