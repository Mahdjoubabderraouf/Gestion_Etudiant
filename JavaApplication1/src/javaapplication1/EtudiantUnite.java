/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import com.sun.jdi.connect.spi.Connection;
import java.util.Vector;
/**
 *
 * @author chems
 */
public class EtudiantUnite {
    private int matriculeEtu;
    private String codeUnite;
    private int noteCC;
    private int noteTP;

    public DBConnection getConnection() {
        return connection;
    }

    public void setConnection(DBConnection connection) {
        this.connection = connection;
    }
    private int noteEx;
    private DBConnection connection;

    // Constructeur
    public EtudiantUnite(int matriculeEtu, String codeUnite, int noteCC, int noteTP, int noteEx) {
        this.matriculeEtu = matriculeEtu;
        this.codeUnite = codeUnite;
        this.noteCC = noteCC;
        this.noteTP = noteTP;
        this.noteEx = noteEx;
        this.connection=new DBConnection();
    }

    // Getters et setters
    public int getMatriculeEtu() {
        return matriculeEtu;
    }

    public void setMatriculeEtu(int matriculeEtu) {
        this.matriculeEtu = matriculeEtu;
    }

    public String getCodeUnite() {
        return codeUnite;
    }

    public void setCodeUnite(String codeUnite) {
        this.codeUnite = codeUnite;
    }

    public int getNoteCC() {
        return noteCC;
    }

    public void setNoteCC(int noteCC) {
        this.noteCC = noteCC;
    }

    public int getNoteTP() {
        return noteTP;
    }

    public void setNoteTP(int noteTP) {
        this.noteTP = noteTP;
    }

    public int getNoteEx() {
        return noteEx;
    }

    public void setNoteEx(int noteEx) {
        this.noteEx = noteEx;
    }

    public void insererEtudiantUnite() {
        String query = "INSERT INTO EtudiantUnite VALUES (?, ?, ?, ?, ?)";
        
        connection.connexion();
        try (PreparedStatement statement = connection.getNewConnection().prepareStatement(query)) {
            statement.setInt(1, this.getMatriculeEtu());
            statement.setString(2, this.getCodeUnite());
            statement.setInt(3, this.getNoteCC());
            statement.setInt(4, this.getNoteTP());
            statement.setInt(5, this.getNoteEx());
            statement.executeUpdate();
            connection.deconnexion();
            System.out.println("Relation Etudiant-Unite ajoutée avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Méthode de suppression d'une relation Etudiant-Unite
    public void supprimerEtudiantUnite() {
        String query = "DELETE FROM EtudiantUnite WHERE matricule_etu = ? AND code_unite = ?";
        
        connection.connexion();
        try (PreparedStatement statement = connection.getNewConnection().prepareStatement(query)) {
            statement.setInt(1, this.getMatriculeEtu());
            statement.setString(2, this.getCodeUnite());
            statement.executeUpdate();
            connection.deconnexion();
            System.out.println("Relation Etudiant-Unite supprimée avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  public List<EtudiantUnite> etudiantUniteConsultation() throws SQLException {
    List<EtudiantUnite> etudiantunites = new ArrayList<>();
    String query = "SELECT * FROM BDDAdmin.EtudiantUnite";
    connection.connexion();

    try (Statement statement = connection.getNewConnection().createStatement()) {
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            EtudiantUnite etudiantunite = new EtudiantUnite(
                resultSet.getInt("matricule_etu"),
                resultSet.getString("code_unite"),
                resultSet.getInt("note_CC"),
                resultSet.getInt("note_TP"),
                resultSet.getInt("note_examen")
            );
            etudiantunites.add(etudiantunite);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        connection.deconnexion();
    }

    return etudiantunites;
}

    public Vector<String> toVector() {
        Vector<String> vector = new Vector<>();
        vector.add(String.valueOf(matriculeEtu));
        vector.add(codeUnite);
        vector.add(String.valueOf(noteCC));
        vector.add(String.valueOf(noteTP));
        vector.add(String.valueOf(noteEx));
        return vector;
    }
    
    
    
    // Méthode de mise à jour d'une relation Etudiant-Unite
    public void mettreAJourEtudiantUnite() {
        String query = "UPDATE EtudiantUnite SET note_CC = ?, note_TP = ?, note_ex = ? WHERE matricule_etu = ? AND code_unite = ?";
        
        connection.connexion();
        try (PreparedStatement statement = connection.getNewConnection().prepareStatement(query)) {
            statement.setInt(1, this.getNoteCC());
            statement.setInt(2, this.getNoteTP());
            statement.setInt(3, this.getNoteEx());
            statement.setInt(4, this.getMatriculeEtu());
            statement.setString(5, this.getCodeUnite());
            statement.executeUpdate();
            connection.deconnexion();
            System.out.println("Relation Etudiant-Unite mise à jour avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

