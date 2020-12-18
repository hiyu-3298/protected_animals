package controllers.animals;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Animals;

/**
 * Servlet implementation class AnimalsNewServlet
 */
@WebServlet("/animals/new")
public class AnimalsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Animals a = new Animals();
        a.setAnimals_date(new Date(System.currentTimeMillis()));
        request.setAttribute("animals", a);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/animals/new.jsp");
        rd.forward(request, response);
    }

}
