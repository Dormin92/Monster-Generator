import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    //private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Description[] monsterDescriptions = new Description[2];

    public Description[] GetMonsterDescriptions() throws Exception 
    {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/saldb?" + "user=sal&password=KyqLV1zkHqcjxi4!");
          
            Random rand = new Random();
            
            statement = connect.createStatement();
            resultSet = statement.executeQuery("select description from saldb.descReal order by rand() limit 1;");
            while(resultSet.next())
            {
            	monsterDescriptions[0] = new Description(typeOfDesc.REAL, resultSet.getString("description"));
            }
            
            int index = rand.nextInt(2);
            switch(index)
            {
            case 0: 
            	statement = connect.createStatement();
                resultSet = statement.executeQuery("select description from saldb.desc117 order by rand() limit 1;");
                while(resultSet.next())
                {
                	monsterDescriptions[1] = new Description(typeOfDesc.FAKE117, resultSet.getString("description"));
                }
	            break;
            case 1:
            	statement = connect.createStatement();
                resultSet = statement.executeQuery("select description from saldb.desc345 order by rand() limit 1;");
                while(resultSet.next())
                {
                	monsterDescriptions[1] = new Description(typeOfDesc.FAKE345, resultSet.getString("description"));
                }
	            break;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
        return monsterDescriptions;

    }
    
    public void IncrementResults(typeOfDesc TOD, boolean guessedCorrectly) throws Exception
    {
    	String modelToIncrement = null;
    	String columnToIncrement = null;
    	if(TOD == typeOfDesc.FAKE117)
    		modelToIncrement = "117";
    	else if(TOD == typeOfDesc.FAKE345)
    		modelToIncrement = "345";
    	
    	if(guessedCorrectly)
    		columnToIncrement = "timesCaught";
    	else
    		columnToIncrement = "timesFooled";
    	
    	try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager.getConnection("jdbc:mysql://localhost/saldb?" + "user=sal&password=KyqLV1zkHqcjxi4!");
            
            statement = connect.createStatement();
            statement.executeUpdate("UPDATE saldb.results SET " + columnToIncrement + " = " + columnToIncrement + " + 1 WHERE model=" + modelToIncrement + ";");

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
