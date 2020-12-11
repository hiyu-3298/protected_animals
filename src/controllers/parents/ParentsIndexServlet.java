package controllers.parents;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Parents;
import utils.DBUtil;

/**
 * Servlet implementation class ParentsIndexServlet
 */
@WebServlet("/parents/index")
public class ParentsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParentsIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) {

        }

        List<Parents> parents = em.createNamedQuery("getAllParents",Parents.class).setFirstResult(15 * (page - 1))
                .setMaxResults(15).getResultList();

        long parents_count = (long)em.createNamedQuery("getParentsCount",Long.class).getSingleResult();

        em.close();

        request.setAttribute("parents", parents);
        request.setAttribute("parents_count", parents_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/parents/index.jsp");
        rd.forward(request, response);
    }

}
