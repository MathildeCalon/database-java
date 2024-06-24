package Exercice01;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/postgres?currentSchema=exercice_jdbc01";
        String username = "postgres";
        String password = "root";

        Connection connection;

        try{
            connection = DriverManager.getConnection(url, username, password);
            if(connection != null){
                System.out.println("Connexion établie");
            } else {
                System.out.println("Connexion échouée");
            }

            startApp();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void newStudent() {
        String url ="jdbc:postgresql://localhost:5432/postgres?currentSchema=exercice_jdbc01";
        String username = "postgres";
        String password = "root";

        Connection connection;

        try{
            connection = DriverManager.getConnection(url, username, password);
            if(connection != null){
                System.out.println("Connexion établie");
            } else {
                System.out.println("Connexion échouée");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("Prénom ?");
            String firstname = scanner.next();
            System.out.println("Nom ?");
            String lastname = scanner.next();
            System.out.println("Classe ?");
            int classNumber = scanner.nextInt();
            System.out.println("Date ?");
            String diplomaDate = scanner.next();
            LocalDate date = LocalDate.parse(diplomaDate);

            Student student = Student.builder()
                    .firstname(firstname)
                    .lastname(lastname)
                    .classNumber(classNumber)
                    .diploma_date(date)
                    .build();

            String request = "INSERT INTO student (lastname, firstname, class_number, diploma_date) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, student.getLastname());
            preparedStatement.setString(2, student.getFirstname());
            preparedStatement.setInt(3, student.getClassNumber());
            preparedStatement.setDate(4, Date.valueOf(student.getDiploma_date()));

            int nbrRows = preparedStatement.executeUpdate();
            System.out.println(nbrRows);

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void menu(){
        System.out.println("1. Ajouter un étudiant");
        System.out.println("2. Afficher tous les étudiants");
        System.out.println("3. Afficher les étudiants d'une classe");
        System.out.println("4. Supprimer un étudiant");
        System.out.println("0. Quitter le programme");
    }

    public static void startApp(){
        menu();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

            switch (choice) {
                case (1):
                    newStudent();
                    break;
                case (2):
                    getAllStudents();
                    break;
                case (3): getStudentsFromClass();
                break;
                case (4): deleteStudent();
                break;
                case (0): break;
            }
    }

    public static void deleteStudent(){
        String url ="jdbc:postgresql://localhost:5432/postgres?currentSchema=exercice_jdbc01";
        String username = "postgres";
        String password = "root";

        Connection connection;

        Scanner scanner = new Scanner(System.in);

        try{
            connection = DriverManager.getConnection(url, username, password);
            if(connection != null){
                System.out.println("Connexion établie");
            } else {
                System.out.println("Connexion échouée");
            }

            System.out.println("Quel est l'id de l'étudiant ?");
            int studentId = scanner.nextInt();

            String request = "DELETE FROM student WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, studentId);

            int nbrRows = preparedStatement.executeUpdate();
            System.out.println(nbrRows);

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void getStudentsFromClass(){
        String url ="jdbc:postgresql://localhost:5432/postgres?currentSchema=exercice_jdbc01";
        String username = "postgres";
        String password = "root";

        Connection connection;

        try{
            connection = DriverManager.getConnection(url, username, password);
            if(connection != null){
                System.out.println("Connexion établie");
            } else {
                System.out.println("Connexion échouée");
            }

            System.out.println("Quelle classe ?");
            Scanner scanner = new Scanner(System.in);
            int chosedClass = scanner.nextInt();

            String request = "SELECT * FROM student WHERE class_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, chosedClass);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                System.out.println(resultSet.getString("lastname")+ "/" + resultSet.getString("firstname")+" / "+ resultSet.getInt("class_number")+" / " + resultSet.getDate("diploma_date"));;
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    public static void getAllStudents(){
        String url ="jdbc:postgresql://localhost:5432/postgres?currentSchema=exercice_jdbc01";
        String username = "postgres";
        String password = "root";

        Connection connection;

        try{
            connection = DriverManager.getConnection(url, username, password);
            if(connection != null){
                System.out.println("Connexion établie");
            } else {
                System.out.println("Connexion échouée");
            }

            Statement stmt = connection.createStatement();
            String request = "SELECT * FROM student;";
            ResultSet resultSet = stmt.executeQuery(request);

            while(resultSet.next()){
                System.out.println(resultSet.getString("lastname")+ "/" + resultSet.getString("firstname")+" / "+resultSet.getInt("class_number")+" / " + resultSet.getDate("diploma_date"));;
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}