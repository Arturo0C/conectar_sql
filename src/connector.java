import java.sql.*;
import java.util.Scanner;

public class connector {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);


        String i = "y";
        while (i.equals("y")) {

            System.out.println("Select an option: ");
            System.out.println("-----------------------");
            System.out.println("1. Participants");
            System.out.println("2. Teams");
            System.out.println("3. Sports listing");
            System.out.println("-----------------------");
            String input = sc.next();
            if (!(isNumeric(input))) {
                System.out.println("This is not a choice, select a numeric choise: ");
                input = sc.next();
            }
            int select = Integer.parseInt(input);
            if (!(select > 3)) {
                switch (select) {
                    case 1:
                        participant();
                        break;
                    case 2:
                        teams();
                        break;
                    case 3:
                        list();
                        break;
                }
            } else {
                System.out.println("It only works with numbers from 1 to 3");
                System.out.println("");
            }

            System.out.println("Do you want to check something else?");
            System.out.println("Yes(y) or not(n)");
            i = sc.next();
            while (i == "y" || i == "n") {
                System.out.println("Yes(y) or not(n)");
                i = sc.next();
            }
        }


    }

    static void participant() {
        Scanner sc = new Scanner(System.in);
        System.out.println("National Document Number: ");
        String dni = sc.next();
        System.out.println("-----------------------");
        if (comp_dni(dni)) {
            try {
                String url = "jdbc:mysql://localhost:3306/sports";
                Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");

                String query = "SELECT * FROM `participant` WHERE `dni`='" + dni + "'";


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
                System.err.println("Error");
            }
        } else {
            System.out.println("The National Identification Document introduced does not exist");
        }
    }

    static void teams() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Id team: ");
        int id = sc.nextInt();
        System.out.println("-----------------------");
        if (comp_teams(id)) {
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
        } else {
            System.out.println("The ID for the team does not exist");
        }
    }

    static void list() {
        Scanner sc = new Scanner(System.in);
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

                System.out.println("Id: " + rs.getInt("id_sport"));
                System.out.println("Name: " + rs.getString("name") + ", category name: " + rs.getString("category_name"));
            }
            System.out.println("-----------------------");
            st.close();
            rs.close();

        } catch (Exception e) {
            System.err.println("Fail to connect.");
        }
    }

    static boolean comp_dni(String dni) {

        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");
            Statement st = conexion.createStatement();

            String query = "SELECT dni FROM `participant`";
            ResultSet rs = st.executeQuery(query);

            // Mostramos lo que queremos de la query hecha.
            while (rs.next()) {
                String dni_tabla = rs.getString("dni");

                if (!dni.equals(dni_tabla)) {
                    continue;
                } else {
                    return true;
                }
            }

            st.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Fail");
        }

        return false;
    }

    static boolean comp_teams(int id) {

        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");
            Statement st = conexion.createStatement();

            String query = "SELECT id_team FROM `team`";
            ResultSet rs = st.executeQuery(query);

            // Mostramos lo que queremos de la query hecha.
            while (rs.next()) {
                int id_tabla = rs.getInt("id_team");

                if (id != id_tabla) {
                    continue;
                } else {
                    return true;
                }
            }

            st.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Fail");
        }

        return false;
    }

    public static boolean isNumeric(String str) {
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && str.equals("") == false);
    }

}


