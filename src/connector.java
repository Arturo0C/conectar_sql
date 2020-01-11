import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Scanner;

public class connector {
    private static String str;

    public static void main(String[] args) throws SQLException {
        partInsert();
    }

    // <--------------------------> Consult <-------------------------->
    static void consult() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an option: ");
        System.out.println("-----------------------");
        System.out.println("1. Participants");
        System.out.println("2. Teams");
        System.out.println("3. Sports listing");
        System.out.println("-----------------------");
        String input = sc.next();
        System.out.println("");
        if (!(isNumeric(input))) {
            System.out.println("This is not a choice, only numbers");
            input = "4";
            consult();
        }
        int select = Integer.parseInt(input);
        if (!(select > 4)) {
            switch (select) {
                case 1:
                    partConn();
                    break;
                case 2:
                    team();
                    break;
                case 3:
                    list();
                    break;
                case 4:
                    return;
            }
        } else {
            System.out.println("It only works with numbers from 1 to 3");
            consult();
        }

        System.out.println("Do you want to check something else?");
        System.out.println("Yes(y) or not(n)");
        if (sc.next().equals("y")) {

            consult();
        }
    }

    static void partConn() {
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
            System.out.println("The National Identification Document introduced does not exist, do you want to try another one?");
            System.out.println("Yes(y) or not(n)");
            String i = sc.next();
            System.out.println("");
            if (i.equals("y")) {
                partConn();
            }
        }
    }

    static void team() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Id team: ");
        String id = sc.next();
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
            System.out.println("The ID for the team does not exist, do you want to try another one?");
            System.out.println("Yes(y) or not(n)");
            String i = sc.next();
            System.out.println("");
            if (i.equals("y")) {
                team();
            }
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

    static boolean comp_teams(String id) {

        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");
            Statement st = conexion.createStatement();

            String query = "SELECT id_team FROM `team`";
            ResultSet rs = st.executeQuery(query);

            // Mostramos lo que queremos de la query hecha.
            while (rs.next()) {
                String id_tabla = rs.getString("id_team");

                if (id.equals(id_tabla)) {
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


    // <--------------------------> Insert <-------------------------->

    static void insert() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an option: ");
        System.out.println("-----------------------");
        System.out.println("1. Participant");
        System.out.println("2. Team");
        System.out.println("3. Sport");
        System.out.println("-----------------------");
        String input = sc.next();
        System.out.println("");
        if (!(isNumeric(input))) {
            System.out.println("This is not a choice, only numbers");
            input = "4";
            insert();
        }
        int select = Integer.parseInt(input);
        if (!(select > 4)) {
            switch (select) {
                case 1:
                    System.out.println("hello");
                    break;
                case 2:
                    System.out.println("hello");
                    break;
                case 3:
                    System.out.println("hello");
                    break;
                case 4:
                    return;
            }
        } else {
            System.out.println("It only works with numbers from 1 to 3");
            insert();
        }

        System.out.println("Do you want to check something else?");
        System.out.println("Yes(y) or not(n)");
        if (sc.next().equals("y")) {
            insert();
        }


    }

    static void partInsert() {

        Scanner sc = new Scanner(System.in);
            try {
                String url = "jdbc:mysql://localhost:3306/sports";
                Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");
                conexion.setAutoCommit(false);

                Statement st = conexion.createStatement();

                System.out.println("DNI: ");
                String dni = sc.next();
                System.out.println("Name: ");
                String name = sc.next();
                System.out.println("First last name: ");
                String last_name1 = sc.next();
                System.out.println("Second last name: ");
                String last_name2 = sc.next();
                System.out.println("Nationality: ");
                String nationality = sc.next();
                System.out.println("Age: ");
                String age = sc.next();
                System.out.println("Birth date: ");
                String birth_date = sc.next();
                System.out.println("Gender: ");
                String gender = sc.next();
                System.out.println("Height: ");
                String height = sc.next();
                System.out.println("Weight: ");
                String weight = sc.next();
                System.out.println("Active: ");
                String active = sc.next();
                System.out.println("Type: ");
                String type = sc.next();
                System.out.println("Back numner: ");
                String back_number = sc.next();

                st.executeUpdate("Insert into team (name,country) values ('"+name+"','"+nationality+"')");
//                st.executeUpdate("INSERT INTO participant (back_number,name,last_name1,last_name2,age,gender,dni,nationality,height,weight,type,is_active, birth_date) VALUES ("+back_number+",'"+name+"'"+",'"+last_name1+"'"+",'"+last_name2+"'"+","+age+","+",'"+gender+"'"+",'"+dni+"'"+",'"+nationality+"'"+","+height+","+weight+",'"+type+"'"+",'"+active+"'"+",'"+birth_date+"')");
                conexion.commit();
                conexion.close();


            } catch (Exception e) {
                System.err.println("Error");
            }


    }

    // <--------------------------> Modify <-------------------------->
    // <--------------------------> Delete <-------------------------->
    // <--------------------------> General <------------------------->
    private static boolean isNumeric(@NotNull String str) {
        connector.str = str;
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && !str.equals(""));
    }


}


