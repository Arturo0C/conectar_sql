
import java.sql.*;
import java.util.Scanner;

public class connection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an option: ");
        System.out.println("-----------------------");
        System.out.println("1. Participants");
        System.out.println("2. Teams");
        System.out.println("3. Sports listing");
        System.out.println("");
        int select = sc.nextInt();
        System.out.println("");

        switch (select) {
            case 1: //Participants
                System.out.println("Numero de DNI: ");
                String dni = sc.next();
                System.out.println("-----------------------");

                try {
                    String url = "jdbc:mysql://localhost:3306/sports";
                    Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");

                    String query = "SELECT * FROM `participant` WHERE `dni`='" + dni + "'";

                    // Creamos java attement
                    Statement st = conexion.createStatement();

                    //Execute the query and get a java resulset
                    ResultSet rs = st.executeQuery(query);

                    // Mostramos lo que queremos de la query hecha.
                    while (rs.next()) {

                        String name = rs.getString("name");
                        String last_name1 = rs.getString("last_name1");
                        String last_name2 = rs.getString("last_name2");
                        String nation = rs.getString("nationality");
                        int age = rs.getInt("age");
                        String birth_date = rs.getString("birth_date");
                        String gender = rs.getString("gender");
                        int height = rs.getInt("height");
                        double weight = rs.getDouble("weight");
                        String type = rs.getString("type");
                        String active = rs.getString("is_active");
                        int back_number = rs.getInt("back_number");

                        System.out.println("Name: " + name + " " + last_name1 + " " + last_name2 + ".");
                        System.out.println("Nationality: " + nation);
                        System.out.println("Age: " + age);
                        System.out.println("Birth date: " + birth_date);
                        System.out.println("Gender: " + gender);
                        System.out.println("Height: " + height);
                        System.out.println("Weight: " + weight);
                        System.out.println("Active: " + active);
                        System.out.println("Type: " + type);
                        System.out.println("Back numner: " + back_number);

                    }
                    System.out.println("-----------------------");
                    st.close();
                    rs.close();

                } catch (Exception e) {
                    System.err.println("Fail to connect.");
                }
                break;
            case 2: //Teams
                System.out.println("Id team: ");
                int id = sc.nextInt();
                System.out.println("-----------------------");

                try {
                    String url = "jdbc:mysql://localhost:3306/sports";
                    Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");

                    String query = "SELECT * FROM `team` WHERE `id_team`='" + id + "'";

                    // Creamos java attement
                    Statement st = conexion.createStatement();

                    //Execute the query and get a java resulset
                    ResultSet rs = st.executeQuery(query);

                    // Mostramos lo que queremos de la query hecha.
                    while (rs.next()) {

                        String name = rs.getString("name");
                        String country = rs.getString("country");

                        System.out.println("Name: " + name);
                        System.out.println("Country: " + country);

                    }
                    System.out.println("-----------------------");
                    st.close();
                    rs.close();

                } catch (Exception e) {
                    System.err.println("Fail to connect.");
                }
                break;
            case 3: //List
                try {
                    String url = "jdbc:mysql://localhost:3306/sports";
                    Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");

                    String query = "SELECT * FROM `sport`";

                    // Creamos java attement
                    Statement st = conexion.createStatement();

                    //Execute the query and get a java resulset
                    ResultSet rs = st.executeQuery(query);

                    // Mostramos lo que queremos de la query hecha.
                    System.out.println("Sports:");
                    while (rs.next()) {

                        System.out.println("Id: "+rs.getInt("id_sport"));
                        System.out.println("Name: "+rs.getString("name")+", category name: "+rs.getString("category_name"));
                    }
                    System.out.println("-----------------------");
                    st.close();
                    rs.close();

                } catch (Exception e) {
                    System.err.println("Fail to connect.");
                }
                break;
        }
    }
}

