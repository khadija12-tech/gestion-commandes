# Gestion des commandes – JEE Atelier

Web application for managing customers, products and orders, built with
**Servlets, JSP/JSTL, CDI and JDBC** following the MVC pattern.

## Structure

| Package / folder | Role |
|---|---|
| `ma.fstt.entities` | Entities: Client, Produit, Commande, LigneCommande |
| `ma.fstt.service` | `IDao` interface |
| `ma.fstt.dao` | DAO classes (JDBC) and `ConnexionDB` |
| `ma.fstt.servlets` | Controllers: `/clients`, `/produits`, `/commandes` |
| `src/main/webapp/WEB-INF/views` | JSP views (JSTL) |
| `schema.sql` | Database schema and user |

## Requirements

Java 17, Maven, Apache Tomcat 10+, MySQL or MariaDB.

## Run

1. Create the database: `sudo mysql < schema.sql`
2. Build: `mvn clean package`
3. Deploy `target/gestion-commandes.war` on Tomcat (or run it from Eclipse).
4. Open <http://localhost:8080/gestion-commandes/clients>

Database settings are in `src/main/java/ma/fstt/dao/ConnexionDB.java`.
