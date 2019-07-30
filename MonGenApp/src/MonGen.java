import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class MonGen
{
	
	List<Description> originals;
    List<Description> generated117;
    List<Description> generated345;
    
    /*
    public static void main(String [] args)
    {
    	Description[] d = new Description[2];
    	boolean play = true;
    	List<Description> originals = readDescriptionFromCSV("original creatures.txt", typeOfDesc.REAL);
        List<Description> generated117 = readDescriptionFromCSV("generated creatures_117.txt", typeOfDesc.FAKE117);
        List<Description> generated345 = readDescriptionFromCSV("generated creatures_345.txt", typeOfDesc.FAKE345);
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
    	while(play)
    	{
	        int order = rand.nextInt(4);
	        
	        switch(order)
	        {
	        case 0:
	        	d[0] = originals.get(rand.nextInt(originals.size()));
	        	d[1] = generated117.get(rand.nextInt(generated117.size()));
	        	break;
	        case 1:
	        	d[0] = generated117.get(rand.nextInt(generated117.size()));
	        	d[1] = originals.get(rand.nextInt(originals.size()));
	        	break;
	        case 2:
	        	d[0] = originals.get(rand.nextInt(originals.size()));
	        	d[1] = generated345.get(rand.nextInt(generated345.size()));
	        	break;
	        case 3:
	        	d[0] = generated345.get(rand.nextInt(generated345.size()));
	        	d[1] = originals.get(rand.nextInt(originals.size()));
	        	break;
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
	        
	        String fileName = "results117.txt";
	        
	        for (int i = 0; i < d.length; i++)
	        {
	        	if(d[i].DescType() == typeOfDesc.FAKE345)
	        		fileName = "results345.txt";
	        }
	        
	        if (d[input].DescType() == typeOfDesc.REAL)
	        {
	        	System.out.println("Nope! A human wrote that description!");
	        	DataManager(d.clone()[input].DescType(), fileName);
	        	System.out.println("Would you like to go again? (y/n)");
	        	String s = scan.next();
	        	if(s != "y")
	        		play = false;
	        }
	        else
	        {
	        	System.out.println("You got it! That was a computer generated description!");
	        	DataManager(d[input].DescType(), fileName);
	        	System.out.println("Would you like to go again? (y/n)");
	        	String s = scan.next();
	        	if(s != "y")
	        		play = false;
	        }
    	}
        scan.close();
        
    }
    */
    
    
    //constructor
    public MonGen()
    {
    	originals = readDescriptionFromCSV("original creatures.txt", typeOfDesc.REAL);
        generated117 = readDescriptionFromCSV("generated creatures_117.txt", typeOfDesc.FAKE117);
        generated345 = readDescriptionFromCSV("generated creatures_345.txt", typeOfDesc.FAKE345);
    }
    
    public Description[] MonsterDescriptions()
    {
    	Description[] d = new Description[2];
    	
        Random rand = new Random();
        
        int order = rand.nextInt(4);
        
        switch(order)
        {
        case 0:
        	d[0] = originals.get(rand.nextInt(originals.size()));
        	d[1] = generated117.get(rand.nextInt(generated117.size()));
        	break;
        case 1:
        	d[0] = generated117.get(rand.nextInt(generated117.size()));
        	d[1] = originals.get(rand.nextInt(originals.size()));
        	break;
        case 2:
        	d[0] = originals.get(rand.nextInt(originals.size()));
        	d[1] = generated345.get(rand.nextInt(generated345.size()));
        	break;
        case 3:
        	d[0] = generated345.get(rand.nextInt(generated345.size()));
        	d[1] = originals.get(rand.nextInt(originals.size()));
        	break;
        }
        
        return d;
    }
    
    private static List<Description> readDescriptionFromCSV(String fileName, typeOfDesc type) {
    	
        List<Description> descriptions = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("src/" + fileName));) 
        {

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
    
    private static void DataManager(typeOfDesc descType, String fileName)
    {
    	BufferedWriter bw = null;
    	try (BufferedReader br = new BufferedReader(new FileReader("src/" + fileName));) 
        {

            String[] lines = new String[2];
            lines[0] = br.readLine();
            lines[1] = br.readLine();
            
            if(descType == typeOfDesc.REAL)
            {
            	String cGuesses[] = lines[0].split("=");
            	int noOfCGuesses = Integer.parseInt(cGuesses[1]);
            	noOfCGuesses++;
            	lines[0] = cGuesses[0] + "=" + noOfCGuesses;
            }
            else
            {
            	String wGuesses[] = lines[1].split("=");
            	int noOfWGuesses = Integer.parseInt(wGuesses[1]);
            	noOfWGuesses++;
            	lines[1] = wGuesses[0] + "=" + noOfWGuesses;
            }
            
			File file = new File("src/" + fileName);
			
			FileWriter fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(lines[0] + "\n" + lines[1]);
			bw.close();
        } 
    	catch (IOException ioe) 
    	{
            ioe.printStackTrace();
        }
    }
    
}
