import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class MonGenMain
{
	static int fooled = 0;
	static int notFooled = 0;
    public static void main(String argsp[])
    {
    	Description[] d = new Description[2];
    	boolean play = true;
    	List<Description> originals = readDescriptionFromCSV("real");
        List<Description> generated = readDescriptionFromCSV("fake");
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
    	while(play)
    	{
	        int order = rand.nextInt(2);
	        
	        if (order == 0)
	        {
	        	d[0] = originals.get(rand.nextInt(originals.size()));
	        	d[1] = generated.get(rand.nextInt(generated.size()));
	        }
	        else
	        {
	        	d[1] = originals.get(rand.nextInt(originals.size()));
	        	d[0] = generated.get(rand.nextInt(generated.size()));
	        }
	        
	        for(int i = 0; i < 2; i++)
	        {
	        	System.out.println(i+1 + ". " + d[i].getText());
	        }
	        
	        int input;
	        do
	        {
	        	System.out.println("Which description is Fake? (1/2)");
	        	input = scan.nextInt();
	        }
	        while(input != 1 && input != 2);
	        input--;
	        
	        if (d[input].IsGenerated())
	        {
	        	System.out.println("You got it! That was a computer generated description!");
	        	notFooled++;
	        	System.out.println("Would you like to go again? (y/n)");
	        	String s = scan.next();
	        	if(s != "y")
	        		play = false;
	        }
    	}
        scan.close();
        
    }
    
    private static List<Description> readDescriptionFromCSV(String type) {
    	
        List<Description> descriptions = new ArrayList<>();
        String fileName = "";
        if (type == "real")
        	fileName = "original creatures.txt";
        else if(type == "fake")
        	fileName = "generated creatures.txt";
        else
        	System.out.println("Incorrect type!!");
        
        try (BufferedReader br = new BufferedReader(new FileReader("src/" + fileName));) {

            // read the first line from the text file
            String line = br.readLine();
            
            // loop until all lines are read
            while (line != null) {

                // creates a new Description object with a description text and type
                Description d = new Description(type, line);

                // adds description to arraylist
                descriptions.add(d);

                // reads next line for loop
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return descriptions;
    }
    
    
}
