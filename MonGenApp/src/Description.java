enum typeOfDesc{REAL, FAKE117, FAKE345}

public class Description
{	
    private typeOfDesc type;
    private String descriptionText;
    Description(typeOfDesc realOrFake, String desc)
    {
        this.type = realOrFake;
        this.descriptionText = desc;
    }
    
    public String getText()
    {
    	return this.descriptionText;
    }
    
    public typeOfDesc getDescType()
    {
    	return type;
    }

}

