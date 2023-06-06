package javaapplication1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Unite {
    private String codeUnite;
    private String libelle;
    private String nbrHeures;
    private int matriculeEns;
    private DBConnection connection;

    public DBConnection getConnection() {
        return connection;
    }

    public void setConnection(DBConnection connection) {
        this.connection = connection;
    }

    // Constructeur
    public Unite(String codeUnite, String libelle, String nbrHeures, int matriculeEns) {
        this.codeUnite = codeUnite;
        this.libelle = libelle;
        this.nbrHeures = nbrHeures;
        this.matriculeEns = matriculeEns;
        this.connection = new DBConnection();
    }

    public Unite() {
        this.connection = new DBConnection();
    }
    public Unite(String codeUnite) {
         this.codeUnite = codeUnite;
        this.connection = new DBConnection();
    }

    // Getters et setters
    public String getCodeUnite() {
        return codeUnite;
    }

    public void setCodeUnite(String codeUnite) {
        this.codeUnite = codeUnite;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNbrHeures() {
        return nbrHeures;
    }

    public void setNbrHeures(String nbrHeures) {
        this.nbrHeures = nbrHeures;
    }

    public int getMatriculeEns() {
        return matriculeEns;
    }

    public void setMatriculeEns(int matriculeEns) {
        this.matriculeEns = matriculeEns;
    }

    // Méthode d'insertion d'une nouvelle unité
    public void insererUnite() {
        String query = "INSERT INTO Unite VALUES (?, ?, ?, ?)";

        connection.connexion();
        try (PreparedStatement statement = connection.getNewConnection().prepareStatement(query)) {
            statement.setString(1, this.codeUnite);
            statement.setString(2, this.getLibelle());
            statement.setString(3, this.getNbrHeures());
            statement.setInt(4, this.getMatriculeEns());
            statement.executeUpdate();
            connection.deconnexion();
            System.out.println("Unité ajoutée avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode de suppression d'une unité
    public void supprimerUnite() {
        String query = "DELETE FROM BDDADMIN.Unite WHERE code_unite = ?";

        connection.connexion();
        try (PreparedStatement statement = connection.getNewConnection().prepareStatement(query)) {
            statement.setString(1, this.getCodeUnite());
            statement.executeUpdate();
            connection.deconnexion();
            System.out.println("Unité supprimée avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*String query = "DELETE FROM Etudiant WHERE matricule_etu = ?";
        connectionEtu.connexion();
        try (PreparedStatement statement = connectionEtu.getNewConnection().prepareStatement(query)) {
            statement.setInt(1, this.matricule);
            statement.executeUpdate();
             connectionEtu.deconnexion();
             System.out.println("supprime succes ");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

  public List<Unite> uniteConsultation() throws SQLException {
    List<Unite> unites = new ArrayList<>();
    String query = "SELECT * FROM BDDAdmin.Unite";
    connection.connexion();

    try (Statement statement = connection.getNewConnection().createStatement()) {
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Unite unite = new Unite(
                resultSet.getString("code_unite"),
                resultSet.getString("libelle"),
                resultSet.getString("nbr_heures"),
                resultSet.getInt("matricule_ens")
            );
            unites.add(unite);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        connection.deconnexion();
    }

    return unites;
}

    public Vector<String> toVector() {
        Vector<String> vector = new Vector<>();
        vector.add(codeUnite);
        vector.add(libelle);
        vector.add(nbrHeures);
        vector.add(String.valueOf(matriculeEns));
        return vector;
    }
}
