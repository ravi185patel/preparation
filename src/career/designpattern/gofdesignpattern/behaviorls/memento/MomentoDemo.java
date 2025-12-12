package career.designpattern.gofdesignpattern.behaviorls.memento;

import java.util.Deque;
import java.util.LinkedList;

/*
9️⃣ Memento Pattern – “Snapshot/undo”
Capture object’s state for restoring later.

 */
class Momento{
    Deque<String> queue;

    public Momento() {
        queue= new LinkedList<>();
    }

    public void saveState(String sb){
        queue.add(sb);
    }

    public String reset(){
        if(queue.isEmpty()){
            return null;
        }
        return queue.poll();
    }
}

class Notepad{
    StringBuffer sb;
    Momento momento;

    public Notepad(Momento momento) {
        this.sb = new StringBuffer();
        this.momento = momento;
    }

    public void addText(String word){
        sb.append(word);
//        String newString = sb.toString();
        momento.saveState(sb.toString());
    }

    public void undo(){
        String res = momento.reset();
        if(res == null){
            System.out.println("no state found");
            return;
        }
        sb = new StringBuffer(res);
    }

    public void read(){
        System.out.println(sb.toString());
    }
}

public class MomentoDemo {
    public static void main(String[] args) {
        Momento momento = new Momento();
        Notepad notepad = new Notepad(momento);

        notepad.addText("Ravi");
        notepad.addText("patel");

        notepad.read();

        notepad.undo();

        notepad.read();

        notepad.undo();
        notepad.undo();

    }
}
