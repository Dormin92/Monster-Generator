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
    private String[] monsterDescriptions = new String[2];

    public String[] GetMonsterDescriptions() throws Exception {
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
            	monsterDescriptions[0] = resultSet.getString("description");
            }
            
            /*
            preparedStatement = connect.prepareStatement("SELECT description FROM saldb.descReal WHERE id= ?;");
            preparedStatement.setInt(1, index);
            resultSet = preparedStatement.executeQuery();
            monsterDescriptions[0] = resultSet.getString("description");
            */
            
            int index = rand.nextInt(2);
            switch(index)
            {
            case 0: 
            	statement = connect.createStatement();
                resultSet = statement.executeQuery("select description from saldb.desc117 order by rand() limit 1;");
                while(resultSet.next())
                {
                	monsterDescriptions[1] = resultSet.getString("description");
                }
	            break;
            case 1:
            	statement = connect.createStatement();
                resultSet = statement.executeQuery("select description from saldb.desc345 order by rand() limit 1;");
                while(resultSet.next())
                {
                	monsterDescriptions[1] = resultSet.getString("description");
                }
	            break;
            }

        } catch (Exception e) {
        	monsterDescriptions[0] = ("It didn't woooorrrkkkk.");
            throw e;
        } finally {
            close();
        }
        return monsterDescriptions;

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
