package career.interview.google;

//[{timeStamp: 1, message: "Hello"}, {timeStamp: 2, message: "Hello"}, {timeStamp: 8, message: "Hey"}, {timeStamp: 12, message: "Hello"}]

import java.util.*;

class Message{
    int timeStamp;
    String message;

    public Message(int timeStamp, String message) {
        this.timeStamp = timeStamp;
        this.message = message;
    }
}
public class ClassifyDuplicates {
    public static void main(String[] args) {
        Message[] messages=new Message[4];
        messages[0] = new Message(1,"Hello");
        messages[1] = new Message(2,"Hello");
        messages[2] = new Message(8,"Hey");
        messages[3] = new Message(12,"Hello");
        String res = getMessage(messages);
        System.out.println(res);
    }

    private static String getMessage(Message[] messages){
//        Arrays.sort(messages,(m1,m2) -> m1.timeStamp-m2.timeStamp);
        Map<String,Integer> messageMap = new HashMap<>();
        List<String> res = new ArrayList<>();
        for(Message message:messages){
            if(messageMap.containsKey(message.message)){
//                if(messageMap.get())
                res.remove(message.message);
            }
            messageMap.put(message.message,message.timeStamp);
            res.add(message.message);
        }
        StringBuilder sb= new StringBuilder();
        for(String message:res){
            sb.append(message).append(" ");
        }
        return sb.toString().trim();
    }
}
