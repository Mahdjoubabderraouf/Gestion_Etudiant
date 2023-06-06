package javaapplication1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import com.sun.jdi.connect.spi.Connection;
import java.util.Vector;

public class Enseignant {
    private int matricule;
    private String nom;
    private String prenom;
    private DBConnection connectionEns;

    public Enseignant(int matricule, String nom, String prenom) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        connectionEns = new DBConnection("Enseignant", "TPEnseignant");
    }

    public Enseignant() {
        connectionEns = new DBConnection();
    }

    public Enseignant(int matricule) {
        this.matricule = matricule;
        connectionEns = new DBConnection();
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public DBConnection getConnectionEns() {
        return connectionEns;
    }

    public void setConnectionEns(DBConnection connectionEns) {
        this.connectionEns = connectionEns;
    }

    // Getters and setters

    public void insererEnseignant() {
        // Insère un nouvel enseignant dans la table
        String query = "INSERT INTO BDDAdmin.Enseignant VALUES (?, ?, ?)";
        connectionEns.connexion();
        try (PreparedStatement statement = connectionEns.getNewConnection().prepareStatement(query)) {
            statement.setInt(1, this.getMatricule());
            statement.setString(2, this.getNom());
            statement.setString(3, this.getPrenom());
            statement.executeUpdate();
            connectionEns.deconnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerEnseignant() {
        // Supprime un enseignant de la table en utilisant le matricule comme clé
        String query = "DELETE FROM BDDAdmin.Enseignant WHERE matricule_ens = ?";
        connectionEns.connexion();
        try (PreparedStatement statement = connectionEns.getNewConnection().prepareStatement(query)) {
            statement.setInt(1, this.matricule);
            statement.executeUpdate();
            connectionEns.deconnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Enseignant> consultationEnseignants() throws SQLException {
        // Récupère tous les enseignants de la table
        List<Enseignant> enseignants = new ArrayList<>();
        String query = "SELECT * FROM BDDAdmin.Enseignant";
        connectionEns.connexion();
        try (Statement statement = connectionEns.getNewConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int matricule = resultSet.getInt("matricule_ens");
                String nom = resultSet.getString("nom_ens");
                String prenom = resultSet.getString("prenom_ens");
                Enseignant enseignant = new Enseignant(matricule, nom, prenom);
                enseignants.add(enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionEns.deconnexion(); // Ferme la connexion à l'extérieur de la boucle while
        }
        return enseignants;
    }
    public Enseignant recherche() throws SQLException, NullPointerException {
    String query = "SELECT * FROM BDDAdmin.Enseignant WHERE matricule_ens = ?";
    Enseignant enseignant = null;
    connectionEns.connexion();

    try (PreparedStatement statement = connectionEns.getNewConnection().prepareStatement(query)) {
        statement.setInt(1, this.getMatricule());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            enseignant = new Enseignant(
                resultSet.getInt("matricule_ens"),
                resultSet.getString("nom_ens"),
                resultSet.getString("prenom_ens")
            );
        }
    } catch (SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            // Gérer l'exception de violation de contrainte d'intégrité
        } else if (e instanceof SQLDataException) {
            System.out.println("Enseignant n'existe pas");
        } else if (e instanceof SQLSyntaxErrorException) {
            // Gérer l'exception de syntaxe SQL
        } else {
            // Gérer toutes les autres exceptions SQL
        }
    } catch (NullPointerException e) {

    } finally {
        connectionEns.deconnexion();
        return enseignant;
    }
}


    public Vector<String> toVector() {
        Vector<String> vector = new Vector<>();
        vector.add(String.valueOf(matricule));
        vector.add(nom);
        vector.add(prenom);
        return vector;
    }

    Iterable<Enseignant> consultationEtudiants() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
