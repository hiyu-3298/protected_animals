package controllers.manager;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Manager;
import utils.DBUtil;

/**
 * Servlet implementation class ParentsShowServlet
 */
@WebServlet("/manager/show")
public class ManagerShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Manager e = em.find(Manager.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("manager", e);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/manager/show.jsp");
        rd.forward(request, response);
    }

}
