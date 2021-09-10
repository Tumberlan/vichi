import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] arags){
        LInear lInear = new LInear();
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        String[] sub_str = command.split(" ");
        lInear.allOperations(Integer.parseInt(sub_str[0]),Integer.parseInt(sub_str[1]),
                Integer.parseInt(sub_str[2]));
    }
}
