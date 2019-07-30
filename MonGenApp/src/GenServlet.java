

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
	private Description[] monsters;
	private MonGen m;

    public GenServlet() 
    {
    	m = new MonGen();
    	monsters = m.MonsterDescriptions();
        // TODO Auto-generated constructor stub
    }
    
    protected Description ServletMethod()
    {
    	Description d = new Description(typeOfDesc.REAL, "This is a sample description");
    	return d;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		ServletOutputStream out = response.getOutputStream();
    	out.println("<html>");
    	out.println("<head><title>Hello Servlet</title></head>");
    	out.println("<body>");
    	out.println(1 + ". " + monsters[0].getText());
    	out.println(2 + ". " + monsters[1].getText());
        out.println("</body>");
    	out.println("<html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
