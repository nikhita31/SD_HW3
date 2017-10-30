import java.util.*;
import java.lang.*;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String []args) {

        //Main Main1 = new Main();   //constructor for Main class
        if (args.length == 0) {System.out.print("Error!!! Missing arguments!!!: "); return;}
        Options options = new Options();

        Option type = Option.builder().longOpt("type").hasArg().desc("Enter the type of the arguments:s for Strings and i for integers").build();
        // add type option
        options.addOption(type);


        Option key = Option.builder().longOpt("key").hasArg().numberOfArgs(1).desc("Enter the key").build();
        // add key option
        options.addOption(key);

        Option list = Option.builder().longOpt("list").hasArgs().desc("Enter the list of arguments of the specified type").build();
        // add list option
        options.addOption(list);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        String key1;



        int result;

        try {
            cmd = parser.parse(options, args);
            key1=cmd.getOptionValue("key");
            String[] cmdList= cmd.getOptionValues("list");

            if(cmd.getOptionValue("type").equals("i")) // search for integer key
            {
                Integer []aList= new Integer[cmdList.length];
                for(int p=0; p<cmdList.length; p++) {
                    aList[p]=Integer.parseInt(cmdList[p]);
                }
                Integer intkey= Integer.parseInt(key1);

                result= binSearch(aList, intkey);
                System.out.print("Result= "+result);

            }
            else if(cmd.getOptionValue("type").equals("s")){  // search for string key
                result= binSearch(cmdList, key1);
                System.out.print("Result= "+result);
            }
            else System.out.println("Entered argument invalid");
        }
        catch(ParseException e)
        {
            System.err.println("Parsing failed. Reason:Failed to parse comand line properties due to invalid input " + e.getMessage());
        }

    }

    private static <T extends Comparable<T>> int binSearch(T[] aList, T key){ {// binary search method to search an element

        int left= 0, mid;
        int right= (aList.length) - 1;        // obtain the position of the last argument in the user's argument list

        while (left <= right) {
            mid= left + (right-left)/2;          //middle element position

            if (aList[mid].compareTo(key)== 0)                 //check if key is found at the middle
            {   System.out.print("Key found\n");
                return 1;   //return 1 if the key is found
            }
            if (aList[mid].compareTo(key) < 0)
                left = mid + 1;              //ignore left half if the key is greater than th middle value
            else
                right = mid - 1;             //ignore right half if the key is greater than th middle value
        }
        System.out.print("Key not found\n");
        return 0;                              //return false if the key is not found
    }

    }
}