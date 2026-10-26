-- Database schema for the "gestion-commandes" JEE workshop
CREATE DATABASE IF NOT EXISTS gestion_commandes CHARACTER SET utf8mb4;
USE gestion_commandes;

CREATE TABLE client (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  email VARCHAR(100),
  telephone VARCHAR(20)
);

CREATE TABLE produit (
  id INT AUTO_INCREMENT PRIMARY KEY,
  designation VARCHAR(100) NOT NULL,
  prix DOUBLE NOT NULL,
  stock INT DEFAULT 0
);

CREATE TABLE commande (
  id INT AUTO_INCREMENT PRIMARY KEY,
  date_commande DATE NOT NULL,
  client_id INT NOT NULL,
  FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
);

CREATE TABLE ligne_commande (
  id INT AUTO_INCREMENT PRIMARY KEY,
  commande_id INT NOT NULL,
  produit_id INT NOT NULL,
  quantite INT NOT NULL,
  prix_unitaire DOUBLE NOT NULL,
  FOREIGN KEY (commande_id) REFERENCES commande(id) ON DELETE CASCADE,
  FOREIGN KEY (produit_id) REFERENCES produit(id)
);

-- Database user used by the application (see ConnexionDB.java)
CREATE USER IF NOT EXISTS 'atelier'@'localhost' IDENTIFIED BY 'atelier123';
GRANT ALL PRIVILEGES ON gestion_commandes.* TO 'atelier'@'localhost';
FLUSH PRIVILEGES;
