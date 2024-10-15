package telran.view;
import java.io.*;
public class StandardInputOutput implements InputOutput{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintStream writer = System.out;
    public String readString(String prompt){
        writer.println(prompt);
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
	public void writeString(String str){
        writer.println(str);
    }
}