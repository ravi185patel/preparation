package career.datastructure.graph.path;

public class PathExistsOrNot {
    public static void main(String[] args) {

        System.out.println(checkFor(1));

    }

    private static int checkFor(int index){
        int value=0;
        for(int i=1;i<10;i++){
            System.out.println(i);

            if(i == 5){
                value = i;
            }else{

            }
        }
        return value;
    }
}
