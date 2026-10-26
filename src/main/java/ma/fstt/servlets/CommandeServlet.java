package ma.fstt.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.dao.ClientDAO;
import ma.fstt.dao.CommandeDAO;
import ma.fstt.dao.LigneCommandeDAO;
import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Commande;
import ma.fstt.entities.LigneCommande;
import ma.fstt.entities.Produit;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/commandes")
public class CommandeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject private CommandeDAO commandeDao;
    @Inject private ClientDAO clientDao;
    @Inject private ProduitDAO produitDao;
    @Inject private LigneCommandeDAO ligneDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";
        String ctx = req.getContextPath();

        switch (action) {
            case "new": {
                req.setAttribute("clients", clientDao.findAll());
                req.getRequestDispatcher("/WEB-INF/views/commande/form.jsp").forward(req, resp);
                break;
            }
            case "detail": {
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("commande", commandeDao.findById(id));
                req.setAttribute("produits", produitDao.findAll());
                req.getRequestDispatcher("/WEB-INF/views/commande/detail.jsp").forward(req, resp);
                break;
            }
            case "delete": {
                commandeDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(ctx + "/commandes");
                break;
            }
            case "deleteLigne": {
                ligneDao.delete(Integer.parseInt(req.getParameter("id")));
                resp.sendRedirect(ctx + "/commandes?action=detail&id=" + req.getParameter("commandeId"));
                break;
            }
            default: {
                req.setAttribute("commandes", commandeDao.findAll());
                req.getRequestDispatcher("/WEB-INF/views/commande/list.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String ctx = req.getContextPath();

        if ("addLigne".equals(action)) {
            int commandeId = Integer.parseInt(req.getParameter("commandeId"));
            Produit p = produitDao.findById(Integer.parseInt(req.getParameter("produitId")));
            LigneCommande l = new LigneCommande();
            l.setProduit(p);
            l.setQuantite(Integer.parseInt(req.getParameter("quantite")));
            l.setPrixUnitaire(p.getPrix());
            ligneDao.save(l, commandeId);
            resp.sendRedirect(ctx + "/commandes?action=detail&id=" + commandeId);
            return;
        }

        // action "save" : création d'une commande
        Commande c = new Commande();
        c.setDateCommande(Date.valueOf(req.getParameter("date")));
        ma.fstt.entities.Client cl = new ma.fstt.entities.Client();
        cl.setId(Integer.parseInt(req.getParameter("clientId")));
        c.setClient(cl);
        commandeDao.save(c);
        resp.sendRedirect(ctx + "/commandes");
    }
}