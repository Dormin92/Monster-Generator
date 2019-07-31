
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GenServlet")
public class GenServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private MySQLAccess sqlAccess;
	private Description[] monsterDescriptions;

    public GenServlet() 
    {
    	sqlAccess = new MySQLAccess();
    	monsterDescriptions = new Description[2];
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

		request.setAttribute("Description1", monsterDescriptions[0].getText());
		request.setAttribute("Description2", monsterDescriptions[1].getText());
	    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/templates/Template.jsp");      
	    view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
		int button = Integer.parseInt(request.getParameter("button"));
		typeOfDesc TOD = typeOfDesc.FAKE117;
		
		for (int i = 0; i < 2; i++)
		{
			if(monsterDescriptions[i].getDescType() == typeOfDesc.FAKE345)
				TOD = typeOfDesc.FAKE345;
		}
		
		if(monsterDescriptions[button].getDescType() == typeOfDesc.REAL)
		{
			try 
			{
				sqlAccess.IncrementResults(TOD, true);
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			try 
			{
				sqlAccess.IncrementResults(TOD, false);
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        request.getRequestDispatcher("WEB-INF/templates/Template.jsp").forward(request, response);

	}

}