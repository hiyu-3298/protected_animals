package controllers.animals;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Animals;
import models.Manager;
import utils.DBUtil;

/**
 * Servlet implementation class AnimalsEditServlet
 */
@WebServlet("/animals/edit")
public class AnimalsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalsEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Animals a = em.find(Animals.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Manager login_manager = (Manager)request.getSession().getAttribute("login_manager");
        if(a != null && login_manager.getId() == a.getManager().getId()) {
            request.setAttribute("animals", a);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("animals_id", a.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/animals/edit.jsp");
        rd.forward(request, response);
    }

}
