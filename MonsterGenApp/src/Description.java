public class Description
{
    final private String type;
    final private String descriptionText;
    Description(String realOrFake, String desc)
    {
        this.type = realOrFake;
        if(type != "real" && type != "fake")
        {
        	System.out.println("Error: Typeof description is invalid. Description can only be 'real' or 'fake'.");
        }
        this.descriptionText = desc;
    }
    
    public String getText()
    {
    	return this.descriptionText;
    }
    
    public boolean IsGenerated()
    {
    	if (type == "fake")
    		return true;
    	else
    		return false;
    }
    
}