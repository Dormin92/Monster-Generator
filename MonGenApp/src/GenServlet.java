
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GenServlet")
public class GenServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private MySQLAccess sqlAccess;
	private String[] monsterDescriptions;

    public GenServlet() 
    {
    	sqlAccess = new MySQLAccess();
    	monsterDescriptions = new String[2];
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			monsterDescriptions = sqlAccess.GetMonsterDescriptions();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ServletOutputStream out = response.getOutputStream();
    	out.println("<html>");
    	out.println("<head><title>Hello Servlet</title></head>");
    	out.println("<body>");
    	out.println("1: " + monsterDescriptions[0]);
    	out.println("2: " + monsterDescriptions[1]);
        out.println("</body>");
    	out.println("<html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
