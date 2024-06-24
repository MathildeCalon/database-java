package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url ="jdbc:postgresql://localhost:5432/postgres?currentSchema=exercice02";
        String username = "postgres";
        String password = "root";

        Connection connection;

        try{
            connection = DriverManager.getConnection(url, username, password);
            if(connection != null){
                System.out.println("Connexion établie");
            } else {
                System.out.println("connexion échouée");
            }

            Statement stmt = connection.createStatement();
            String request = "SELECT * FROM livre;";
            ResultSet resultSet = stmt.executeQuery(request);

            while(resultSet.next()){
                System.out.println(resultSet.getString("titre")+ " / "+resultSet.getInt("table_id"));
            }

            stmt.close();
            resultSet.close();

            String request2 = "SELECT * FROM livre WHERE table_id = 1;";
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(request2);

            if(resultSet.next()){
                System.out.println(resultSet.getString("titre")+ " / " +resultSet.getInt("table_id"));
            }
            stmt.close();
            resultSet.close();

            stmt = connection.createStatement();
            String request3 = "SELECT * FROM livre";
            resultSet = stmt.executeQuery(request3);
            List<Book> books = new ArrayList<>();
            while(resultSet.next()){
                books.add(Book.builder()
                        .id(resultSet.getInt("table_id"))
                        .title(resultSet.getString("titre"))
                        .author(resultSet.getString("auteur"))
                        .build());
            }

            System.out.println(books);

            Book book = Book.builder().title("mon livre java")
                    .author("leo")
                    .publisher("m2i")
                    .isbn10("1234567895")
                    .publicationDate(LocalDate.now())
                    .build();

            String request4 = "INSERT INTO livre (titre,auteur,editeur,isbn_10,date_publication) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(request4);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getPublisher());
            preparedStatement.setString(4, book.getIsbn10());
            preparedStatement.setDate(5, Date.valueOf(book.getPublicationDate()));

            int nbrRows = preparedStatement.executeUpdate();
            System.out.println(nbrRows);



        } catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }
}