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
import models.Manager;
import models.validators.AnimalsValidator;
import utils.DBUtil;

/**
 * Servlet implementation class AnimalsCreateServlet
 */
@WebServlet("/animals/create")
public class AnimalsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnimalsCreateServlet() {
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

            Animals a = new Animals();

            a.setManager((Manager)request.getSession().getAttribute("login_manager"));

            Date animals_date = new Date(System.currentTimeMillis());
            String ad_str = request.getParameter("animals_date");
            if(ad_str != null && !ad_str.equals("")) {
                animals_date = Date.valueOf(request.getParameter("animals_date"));
            }
            a.setAnimals_date(animals_date);

            a.setName(request.getParameter("name"));
            a.setSex(request.getParameter("sex"));
            a.setAge(Integer.parseInt(request.getParameter("age")));
            a.setContent(request.getParameter("content"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            a.setCreated_at(currentTime);
            a.setUpdated_at(currentTime);

            List<String> errors = AnimalsValidator.validate(a);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("animals", a);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/animals/new.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.persist(a);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/animals/index");
            }
        }
    }

}
