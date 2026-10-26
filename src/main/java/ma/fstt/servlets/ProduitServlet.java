package ma.fstt.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Produit;

import java.io.IOException;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProduitDAO dao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new": {
                req.getRequestDispatcher("/WEB-INF/views/produit/form.jsp").forward(req, resp);
                break;
            }
            case "edit": {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("produit", dao.findById(id));
                req.getRequestDispatcher("/WEB-INF/views/produit/form.jsp").forward(req, resp);
                break;
            }
            case "delete": {
                try {
                    dao.delete(Integer.parseInt(req.getParameter("id")));
                } catch (RuntimeException e) {
                    // produit utilisé dans une commande : suppression impossible
                }
                resp.sendRedirect(req.getContextPath() + "/produits");
                break;
            }
            default: {
                req.setAttribute("produits", dao.findAll());
                req.getRequestDispatcher("/WEB-INF/views/produit/list.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Produit p = new Produit();
        p.setDesignation(req.getParameter("designation"));
        p.setPrix(Double.parseDouble(req.getParameter("prix")));
        p.setStock(Integer.parseInt(req.getParameter("stock")));

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            dao.save(p);
        } else {
            p.setId(Integer.parseInt(id));
            dao.update(p);
        }
        resp.sendRedirect(req.getContextPath() + "/produits");
    }
}