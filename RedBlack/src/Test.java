//Some code below was provided by the Professor (Dr. Hadong Wang)
//Marked code is my implementation

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        //My Code
        //Creation of the RebBlack Object
        RBTreeMap RedBlackTest = new RBTreeMap();

        //Block takes the File and breaks it into Keys and Values and pushes them to RB Obj
        String lineNeeded, Key, Value;
        File roster = new File("roster.txt");
        Scanner file = new Scanner(roster);
        while (file.hasNextLine()) {
            Key = file.next();
            Value = file.nextLine();
            RedBlackTest.put(Key, Value);
        }


        System.out.println(RedBlackTest.toString());
        //End of my Code
    }
}