package controllers.animals;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Animals;
import models.validators.AnimalsValidator;
import utils.DBUtil;

/**
 * Servlet implementation class AnimalsUpdateServlet
 */
@WebServlet("/animals/update")
public class AnimalsUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalsUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Animals a = em.find(Animals.class, (Integer)(request.getSession().getAttribute("animals_id")));

            a.setAnimals_date(Date.valueOf(request.getParameter("animals_date")));
            a.setName(request.getParameter("name"));
            a.setSex(request.getParameter("sex"));
            a.setAge(Integer.parseInt(request.getParameter("age")));
            a.setContent(request.getParameter("content"));
            a.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = AnimalsValidator.validate(a);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("animals", a);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/animals/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("animals_id");

                response.sendRedirect(request.getContextPath() + "/animals/index");
            }
        }
    }

}
