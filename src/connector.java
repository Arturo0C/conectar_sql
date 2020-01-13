import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.Scanner;

public class connector {
    private static String str;

    public static void main(String[] args) throws SQLException {
    partModify();
    }

    // <--------------------------> Menu <----------------------------->

    static void menu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an option: ");
        System.out.println("-----------------------");
        System.out.println("1. Consult");
        System.out.println("2. Insert");
        System.out.println("3. Modify");
        System.out.println("4. Delete");
        System.out.println("-----------------------");
        String input = sc.next();
        System.out.println("");
        if (!(isNumeric(input))) {
            System.out.println("This is not a choice, only numbers");
            input = "5";
            menu();
        }
        int select = Integer.parseInt(input);
        if (!(select > 5)) {
            switch (select) {
                case 1:
                    consult();
                    break;
                case 2:
                    insert();
                    break;
                case 3:
                    System.out.println("hello!");
                    break;
                case 4:
                    System.out.println("hello!");
                case 5:
                    return;
            }
        } else {
            System.out.println("It only works with numbers from 1 to 3");
            menu();
        }
    }

    // <--------------------------> Consult <-------------------------->
    static void consult() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an option: ");
        System.out.println("-----------------------");
        System.out.println("1. Participant");
        System.out.println("2. Team");
        System.out.println("3. Sport listing");
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
                    partConDni();
                    break;
                case 2:
                    teamCon();
                    break;
                case 3:
                    listCon();
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
            System.out.println("");
            menu();
        }
    }

    static void partConDni() {
        Scanner sc = new Scanner(System.in);
        System.out.println("National Document Number: ");
        String dni = sc.next();
        System.out.println("-----------------------");
        partCon(dni);
    }

    static void partCon(String dni) {
        Scanner sc = new Scanner(System.in);
        if (compDni(dni)) {
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
                System.out.println("");
                partConDni();

            }
        }
    }

    static void teamCon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Id team: ");
        String id = sc.next();
        System.out.println("-----------------------");
        if (compTeams(id)) {
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
                System.out.println("");
                teamCon();
            }
        }
    }

    static void listCon() {
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
            System.err.println("Fail to charge the team.");
        }
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
                    partInsert();
                    break;
                case 2:
                    teamInsert();
                    break;
                case 3:
                    sportInsert();
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
            System.out.println("");
            menu();
        }


    }

    static void partInsert() {
        Scanner sc = new Scanner(System.in);

            try {
                String url = "jdbc:mysql://localhost:3306/sports";
                Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");
                conexion.setAutoCommit(false);

                Statement st = conexion.createStatement();

                System.out.println("Enter the participant's information: ");
                System.out.println("");

                System.out.println("DNI(required): ");
                String dni = sc.next();
                System.out.println("Name(required): ");
                String name = sc.next();
                System.out.println("First last name(required): ");
                String last_name1 = sc.next();
                System.out.println("Second last name: ");
                String last_name2 = sc.next();
                System.out.println("Nationality(required): ");
                String nationality = sc.next();
                System.out.println("Age(required): ");
                String age = sc.next();
                System.out.println("Birth date(required): ");
                String birth_date = sc.next();
                System.out.println("Gender(required: Male or Female): ");
                String gender = sc.next();
                System.out.println("Height: ");
                String height = sc.next();
                System.out.println("Weight: ");
                String weight = sc.next();
                System.out.println("Active(required: Yer or No): ");
                String active = sc.next();
                System.out.println("Type(required: Coach,Player or Referee: ):  ");
                String type = sc.next();
                System.out.println("Back numner: ");
                String back_number = sc.next();

                st.executeUpdate("INSERT INTO participant (back_number,name,last_name1,last_name2,age,gender,dni,nationality,height,weight,type,is_active, birth_date) VALUES ("+back_number+",'"+name+"','"+last_name1+"','"+last_name2+"',"+age+",'"+gender+"','"+dni+"','"+nationality+"',"+height+","+weight+",'"+type+"','"+active+"','"+birth_date+"')");

                conexion.commit();
                conexion.close();

            } catch (Exception e) {
                System.out.println("The data entered is not correct, do you want to try another one?");
                System.out.println("Yes(y) or not(n)");
                String i = sc.next();
                System.out.println("");
                if (i.equals("y")) {
                    System.out.println("");
                    partInsert();
                }
            }


    }

    static void teamInsert() {

        Scanner sc = new Scanner(System.in);
        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");
            conexion.setAutoCommit(false);

            Statement st = conexion.createStatement();

            System.out.println("Enter team information: ");
            System.out.println("");

            System.out.println("Name(required): ");
            String name = sc.next();
            System.out.println("country(required): ");
            String country = sc.next();

            st.executeUpdate("INSERT INTO team(name,country) VALUES("+"'"+name+"','"+country+"')");

            conexion.commit();
            conexion.close();

        } catch (Exception e) {
            System.out.println("The data entered is not correct, do you want to try another one?");
            System.out.println("Yes(y) or not(n)");
            String i = sc.next();
            System.out.println("");
            if (i.equals("y")) {
                System.out.println("");
                teamInsert();
            }
        }


    }

    static void sportInsert() {

        Scanner sc = new Scanner(System.in);
        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");
            conexion.setAutoCommit(false);

            Statement st = conexion.createStatement();

            System.out.println("Enter sport information: ");
            System.out.println("");

            System.out.println("Name(required): ");
            String name = sc.next();
            System.out.println("Category(required): ");
            String category = sc.next();

            st.executeUpdate("INSERT INTO sport(name, category_name) VALUES("+"'"+name+"','"+category+"')");

            conexion.commit();
            conexion.close();

        } catch (Exception e) {
            System.out.println("The data entered is not correct, do you want to try another one?");
            System.out.println("Yes(y) or not(n)");
            String i = sc.next();
            System.out.println("");
            if (i.equals("y")) {
                System.out.println("");
                sportInsert();
            }
        }


    }

    // <--------------------------> Modify <-------------------------->

    static void modify() {
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
            consult();
        }
        int select = Integer.parseInt(input);
        if (!(select > 4)) {
            switch (select) {
                case 1:
                    System.out.println("hello!");
                    break;
                case 2:
                    System.out.println("hello!");
                    break;
                case 3:
                    System.out.println("hello!");
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
            System.out.println("");
            menu();
        }
    }

    static void partModify() {
        Scanner sc = new Scanner(System.in);
        System.out.println("National Document Number: ");
        String dni = sc.next();
        System.out.println("-----------------------");
        System.out.println("");
        partCon(dni);

        System.out.println("What data do you want to change?");
        String dato = sc.next();
        dato = dato.toLowerCase();
        if (compColumn(dato)) {
            try {
                String url = "jdbc:mysql://localhost:3306/sports";
                Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");
                conexion.setAutoCommit(false);
                Statement st = conexion.createStatement();

                System.out.println("");
                System.out.println("New data: ");
                String nuevoDato = sc.next();
                System.out.println("update participant set "+dato+"='"+nuevoDato+"' where dni='"+dni+"'");

                st.executeUpdate("update participant set "+dato+"='"+nuevoDato+"' where dni='"+dni+"'");


            } catch (Exception e) {
                System.out.println("The data entered is not correct, do you want to try another one?");
                System.out.println("Yes(y) or not(n)");
                String i = sc.next();
                System.out.println("");
                if (i.equals("y")) {
                    System.out.println("");
                    partModify();
                }
            }
        } else {
            System.out.println("The column does not exist");
            System.out.println("Yes(y) or not(n)");
            String i = sc.next();
            System.out.println("");
            if (i.equals("y")) {
                System.out.println("");
                partModify();
            }
        }

    }





    // <--------------------------> Delete <-------------------------->
    // <--------------------------> General <------------------------->
    private static boolean isNumeric(@NotNull String str) {
        connector.str = str;
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && !str.equals(""));
    }

    static boolean compDni(String dni) {

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
            System.err.println("Error on verification of dni");
        }

        return false;
    }

    static boolean compTeams(String id) {

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
            System.err.println("Error on verification id team");
        }

        return false;
    }

    static boolean compColumn(String date) {

        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "root", "tuenti1997");
            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM `participant`");
            ResultSetMetaData rsmd = rs.getMetaData();

            for (int i = 1; i <= 13; i++) {
                if ((date.equals(rsmd.getColumnName(i)))) {
                    return true;
                }
            }

            st.close();
            rs.close();
        } catch (Exception e) {
            System.err.println("Error, this column does exist");
        }
        return false;
    }


}


