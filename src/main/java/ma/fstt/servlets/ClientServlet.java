package ma.fstt.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ClientDAO;
import ma.fstt.entities.Client;

import java.io.IOException;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {

    @Inject
    private ClientDAO dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new": {
                req.getRequestDispatcher("/WEB-INF/views/client/form.jsp").forward(req, resp);
                break;
            }
            case "edit": {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("client", dao.findById(id));
                req.getRequestDispatcher("/WEB-INF/views/client/form.jsp").forward(req, resp);
                break;
            }
            case "delete": {
                dao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(req.getContextPath() + "/clients");
                break;
            }
            default: {
                req.setAttribute("clients", dao.findAll());
                req.getRequestDispatcher("/WEB-INF/views/client/list.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Client c = new Client();
        c.setNom(req.getParameter("nom"));
        c.setPrenom(req.getParameter("prenom"));
        c.setEmail(req.getParameter("email"));
        c.setTelephone(req.getParameter("telephone"));

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            dao.save(c);
        } else {
            c.setId(Integer.parseInt(id));
            dao.update(c);
        }
        resp.sendRedirect(req.getContextPath() + "/clients");
    }
}