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

public class Etudiant {
    private int matricule;
    private String nom;
    private String prenom;
    private java.util.Date dateNaissance;
    private String adresse;
    private DBConnection connectionEtu ;
    
   
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    
    public Etudiant(int matricule, String nom, String prenom, java.util.Date dateNaissance, String adresse) {
        
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse=adresse;
        connectionEtu = new DBConnection ();
        
    }

    public int getMatricule() {
        return matricule;
    }

    public Etudiant(int matricule) {
        this.matricule = matricule;
        connectionEtu =  new DBConnection ();
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

    public java.util.Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(java.util.Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
       


    public void insererEtudiant( ) {
        // Insère un nouvel étudiant dans la table
        
        String query = "INSERT INTO BDDAdmin.Etudiant VALUES (?, ?, ?, ?, ?)";
        
        connectionEtu.connexion();
        try (PreparedStatement statement = connectionEtu.getNewConnection().prepareStatement(query)) {
            statement.setInt(1, this.getMatricule());
            statement.setString(2, this.getNom());
            statement.setString(3, this.getPrenom());
            statement.setDate(4, new java.sql.Date (this.getDateNaissance().getTime()));
            statement.setString(5, this.getAdresse());
            statement.executeUpdate();
            connectionEtu.deconnexion();
            System.out.println("ajoute succes ");
        } catch (SQLException  e) {
        // Gérer toutes les autres exceptions SQL
        e.printStackTrace();
    }
        }
    
    /*public void updateEtudiant(Etudiant etudiant) {
        // Met à jour les informations d'un étudiant dans la table
        String query = "UPDATE Etudiant SET nom_etu = ?, prenom_etu = ?, date_naissance = ? WHERE matricule_etu = ?";
        try (PreparedStatement statement = connectionEtu.getNewConnection().prepareStatement(query)) {
            statement.setString(1, etudiant.getNom());
            statement.setString(2, etudiant.getPrenom());
            statement.setString(3, etudiant.getDateNaissance());
            statement.setString(4, etudiant.getMatricule());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public void supprimerEtudiant( ) {
        // Supprime un étudiant de la table en utilisant le matricule comme clé
        String query = "DELETE FROM Etudiant WHERE matricule_etu = ?";
        connectionEtu.connexion();
        try (PreparedStatement statement = connectionEtu.getNewConnection().prepareStatement(query)) {
            statement.setInt(1, this.matricule);
            statement.executeUpdate();
             connectionEtu.deconnexion();
             System.out.println("supprime succes ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Etudiant() {connectionEtu = new DBConnection ();}
    public List<Etudiant> consultationEtudiants() throws SQLException {
    List<Etudiant> etudiants = new ArrayList<>();
    String query = "SELECT * FROM BDDAdmin.Etudiant";
    connectionEtu.connexion();
    try (Statement statement = connectionEtu.getNewConnection().createStatement()) {
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int matricule = resultSet.getInt("matricule_etu");
            String nom = resultSet.getString("nom_etu");
            String prenom = resultSet.getString("prenom_etu");
            Date dateNaissance = resultSet.getDate("date_naissance");
            String adresse = resultSet.getString("adresse");
            Etudiant etudiant = new Etudiant(matricule, nom, prenom, dateNaissance, adresse);
            etudiants.add(etudiant);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        connectionEtu.deconnexion(); // Close the connection outside the while loop
    }
    return etudiants;
}

       public Vector<String> toVector() {
        Vector<String> vector = new Vector<>();
        vector.add(String.valueOf(matricule));
        vector.add(nom);
        vector.add(prenom);
        vector.add(String.valueOf(dateNaissance));
        vector.add(adresse);
        return vector;
    }

 public Etudiant recherche() throws SQLException,NullPointerException {
    String query = "SELECT * FROM BDDAdmin.Etudiant WHERE matricule_etu = ?";
    Etudiant etudiant = null;
    connectionEtu.connexion();
    
    try (PreparedStatement statement = connectionEtu.getNewConnection().prepareStatement(query)) {
        statement.setInt(1, this.getMatricule());
        ResultSet resultSet = statement.executeQuery();
        
        
        if (resultSet.next()) 
            etudiant = new Etudiant(
                resultSet.getInt("matricule_etu"),
                resultSet.getString("nom_etu"),
                resultSet.getString("prenom_etu"),
                resultSet.getDate("date_naissance"),
                resultSet.getString("adresse")
            );
       
        
     
    } catch ( SQLException e) {
    if (e instanceof SQLIntegrityConstraintViolationException) {
        // Gérer l'exception de violation de contrainte d'intégrité
    } else if (e instanceof SQLDataException) {
        System.out.println("Etudiant n' ewiste pas");
    } else if (e instanceof SQLSyntaxErrorException) {
        // Gérer l'exception de syntaxe SQL
    } else {
        // Gérer toutes les autres exceptions SQL

    } 
    }catch (NullPointerException e){
    
    }
    finally {
        connectionEtu.deconnexion();
        return etudiant;  
    }    
} 

    /**
     *
     */
    public void afficherEtudiant() {
    System.out.print("Matricule : " + matricule+" ");
    System.out.print("Nom : " + nom+" ");
    System.out.print("Prénom : " + prenom+" ");
    System.out.print("Date de naissance : " + dateNaissance+" ");
    System.out.println("Adresse : " + adresse+" ");
}



    
 
}

        
        
    


