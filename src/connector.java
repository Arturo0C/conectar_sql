import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Scanner;

public class connector {
    private static String str;

    public static void main(String[] args) {
        menu();
    }

    // <--------------------------> Menu <----------------------------->

    static void menu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "Select an option");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "1. Consult");
        System.out.println("");
        System.out.printf("| %-19s |", "2. Insert");
        System.out.println("");
        System.out.printf("| %-19s |", "3. Modify");
        System.out.println("");
        System.out.printf("| %-19s |", "3. Delete");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("");


        String input = sc.next();
        System.out.println("");
        if (!(isNumeric(input))) {
            System.out.println("This is not a choice, only numbers");
            menu();
        }
        int select = Integer.parseInt(input);
        if (select < 5) {
            switch (select) {
                case 1:
                    consult();
                    break;
                case 2:
                    insert();
                    break;
                case 3:
                    modify();
                    break;
                case 4:
                    delete();
            }
        } else {
            System.out.println("It only works with numbers from 1 to 4");
            menu();
        }
    }

    // <--------------------------> Consult <-------------------------->
    static void consult() {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "Select an option");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "1. Participant");
        System.out.println("");
        System.out.printf("| %-19s |", "2. Team");
        System.out.println("");
        System.out.printf("| %-19s |", "3. Sport listing");
        System.out.println("");
        System.out.printf("| %-19s |", "4. Menu");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("");

        String input = sc.next();
        System.out.println("");

        if (!(isNumeric(input))) {
            System.out.println("This is not a choice, only numbers");
            consult();
        }
        int select = Integer.parseInt(input);
        if (select < 5) {
            switch (select) {
                case 1:
                    partCon();
                    break;
                case 2:
                    teamCon();
                    break;
                case 3:
                    listCon();
                    break;
                case 4:
                    menu();
            }
        } else {
            System.out.println("It only works with numbers from 1 to 4");
            consult();
        }

        System.out.println("Do you want to check something else?");
        System.out.println("Yes(y) or not(n)");
        if (sc.next().equals("y")) {
            System.out.println("");
            menu();
        }
    }


    static void partCon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------");
        System.out.printf("| %-19s |", "National document Number");
        System.out.println("");
        System.out.println("----------------------------");
        String dni = sc.next();
        if (compDNI(dni)) {
            try {
                Statement st = conexion().createStatement();
                ResultSet rs = st.executeQuery("select *,p.name as nombrepersona, t.name as team,pt.dni as dni2 from participant p inner join participant_have_team pt on p.dni=pt.dni inner join team t on pt.id_team = t.id_team where p.dni like '" + dni + "'");

                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-11s | %-27s | %-11s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-20s | ", "dni", "Name", "Nationality", "Age", "Birth date", "Gender", "Height", "Weight", "Type", "Is active", "back number", "Team");
                System.out.println("");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while (rs.next()) {
                    System.out.printf("| %-11s | %-27s | %-11s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-11s | %-20s | ", rs.getString("dni"), rs.getString("nombrepersona") + " " + rs.getString("last_name1") + " " + rs.getString("last_name2"), rs.getString("nationality"), rs.getString("age"), rs.getString("birth_date"), rs.getString("gender"), rs.getString("height"), rs.getString("weight"), rs.getString("type"), rs.getString("is_active"), rs.getString("back_number"), rs.getString("team"));
                    System.out.println("");
                }
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            } catch (Exception e) {
                System.err.println("Error");
            }
        } else {
            System.out.println("This dni does not exist.");
            System.out.println("");
            partCon();
        }
    }

    static void teamCon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Id team: ");
        System.out.println("");
        String id = sc.next();

        try {
            Statement st = conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `team` WHERE `id_team` like '" + id + "'");

            System.out.println("------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s |", "Id", "Name", "Country");
            System.out.println("");
            System.out.println("------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-10s | %-20s | %-20s |", rs.getString("id_team"), rs.getString("name"), rs.getString("country"));
                System.out.println("");
            }
            System.out.println("------------------------------------------------------------");
            st.close();
            rs.close();

        } catch (Exception e) {
            System.err.println("Fail to connect.");
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
        try {
            Statement st = conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `sport`");


            System.out.println("------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s |", "Id", "Name", "Category");
            System.out.println("");
            System.out.println("------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-10s | %-20s | %-20s |", rs.getString("id_sport"), rs.getString("name"), rs.getString("category_name"));
                System.out.println("");
            }
            System.out.println("------------------------------------------------------------");
            st.close();
            rs.close();

        } catch (Exception e) {
            System.err.println("Fail to charge the Sport list.");
        }
    }

    // <--------------------------> Insert <-------------------------->

    static void insert() {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "Select an option");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "1. Participant");
        System.out.println("");
        System.out.printf("| %-19s |", "2. Team");
        System.out.println("");
        System.out.printf("| %-19s |", "3. Sport");
        System.out.println("");
        System.out.printf("| %-19s |", "4. Menu");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("");

        String input = sc.next();
        System.out.println("");
        if (!(isNumeric(input))) {
            System.out.println("This is not a choice, only numbers");
            insert();
        }
        int select = Integer.parseInt(input);
        if (select < 5) {
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
                    menu();
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

        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | %-5s | ", "dni", "Name", "Nationality", "Age", "Birth date", "Gender", "Height", "Weight", "Type", "Is active", "back number", "Team");
        System.out.println("");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("");

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
        System.out.println("Active(required: Yes or No): ");
        String active = sc.next();
        System.out.println("Type(required: Coach,Player or Referee: ):  ");
        String type = sc.next();
        System.out.println("Back numner: ");
        String back_number = sc.next();
        System.out.println("Team id: ");
        String id_team = sc.next();
        System.out.println("");

        try {

            Statement st = conexion().createStatement();
            st.executeUpdate("INSERT INTO participant (back_number,name,last_name1,last_name2,age,gender,dni,nationality,height,weight,type,is_active, birth_date) VALUES (" + back_number + ",'" + name + "','" + last_name1 + "','" + last_name2 + "'," + age + ",'" + gender + "','" + dni + "','" + nationality + "'," + height + "," + weight + ",'" + type + "','" + active + "','" + birth_date + "')");
            st.executeUpdate("Insert into participant_have_team (dni, id_team) values ('" + dni + "'," + id_team + ")");

            conexion().setAutoCommit(true);
            conexion().close();
            st.close();

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

        System.out.println("Data:");
        try {
            Statement st = conexion().createStatement();
            ResultSet rs = st.executeQuery("select *,p.name as nombrepersona, t.name as team,pt.dni as dni2 from participant p inner join participant_have_team pt on p.dni=pt.dni inner join team t on pt.id_team = t.id_team where p.dni like '" + dni + "'");


            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-11s | %-27s | %-11s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-20s | ", "dni", "Name", "Nationality", "Age", "Birth date", "Gender", "Height", "Weight", "Type", "Is active", "back number", "Team");
            System.out.println("");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-11s | %-27s | %-11s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-11s | %-20s | ", rs.getString("dni"), rs.getString("nombrepersona") + " " + rs.getString("last_name1") + " " + rs.getString("last_name2"), rs.getString("nationality"), rs.getString("age"), rs.getString("birth_date"), rs.getString("gender"), rs.getString("height"), rs.getString("weight"), rs.getString("type"), rs.getString("is_active"), rs.getString("back_number"), rs.getString("team"));
                System.out.println("");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void teamInsert() {

        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.printf("| %-5s | %-5s | %-5s |", "Id", "Name", "Country");
        System.out.println("");
        System.out.println("---------------------------");

        System.out.println("Enter team information: ");
        System.out.println("");

        System.out.println("Name(required): ");
        String name = sc.next();
        System.out.println("country(required): ");
        String country = sc.next();
        try {

            Statement st = conexion().createStatement();
            st.executeUpdate("INSERT INTO team(name,country) VALUES(" + "'" + name + "','" + country + "')");
            System.out.println("");

            conexion().setAutoCommit(true);
            st.close();
            conexion().close();

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

        System.out.println("Data:");
        try {
            Statement st = conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `team` WHERE `name` like '" + name + "'");

            System.out.println("------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s |", "Id", "Name", "Country");
            System.out.println("");
            System.out.println("------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-10s | %-20s | %-20s |", rs.getString("id_team"), rs.getString("name"), rs.getString("country"));
                System.out.println("");
            }
            System.out.println("------------------------------------------------------------");
            st.close();
            rs.close();

        } catch (Exception e) {
            System.err.println("Fail to connect.");
            System.out.println("Yes(y) or not(n)");
            String i = sc.next();
            System.out.println("");
            if (i.equals("y")) {
                System.out.println("");
                teamCon();
            }

        }


    }

    static void sportInsert() {

        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------------------------");
        System.out.printf("| %-5s | %-5s | %-5s |", "Id", "Name", "Category");
        System.out.println("");
        System.out.println("-------------------------------------------");
        System.out.println("Enter sport information: ");
        System.out.println("");

        System.out.println("Name(required): ");
        String name = sc.next();
        System.out.println("Category(required): ");
        String category = sc.next();
        System.out.println("");

        try {
            Statement st = conexion().createStatement();
            st.executeUpdate("INSERT INTO sport(name, category_name) VALUES(" + "'" + name + "','" + category + "')");
            conexion().setAutoCommit(true);
            conexion().close();
            st.close();

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

        System.out.println("Data: ");
        try {
            Statement st = conexion().createStatement();
            ResultSet rs = st.executeQuery("select * from sport where name='" + name + "'");

            System.out.println("------------------------------");
            System.out.printf("| %-5s | %-5s | %-10s |", "Id", "Name", "Category");
            System.out.println("");
            while (rs.next()) {

                System.out.println("------------------------------");
                System.out.printf("| %-5s | %-5s | %-10s |", rs.getString("id_sport"), rs.getString("name"), rs.getString("category_name"));
                System.out.println("");
                System.out.println("------------------------------");
            }
            System.out.println("");

        } catch (Exception e) {
            System.out.println("Hello");
        }

    }


    // <--------------------------> Modify <-------------------------->

    static void modify() {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "Select an option");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "1. Participant");
        System.out.println("");
        System.out.printf("| %-19s |", "2. Team");
        System.out.println("");
        System.out.printf("| %-19s |", "3. Sport");
        System.out.println("");
        System.out.printf("| %-19s |", "4. Menu");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("");

        String input = sc.next();
        System.out.println("");
        if (!(isNumeric(input))) {
            System.out.println("This is not a choice, only numbers");
            modify();
        }
        int select = Integer.parseInt(input);
        if (select < 5) {
            switch (select) {
                case 1:
                    partModify();
                    break;
                case 2:
                    teamModify();
                    break;
                case 3:
                    sportModify();
                    break;
                case 4:
                    menu();
            }
        } else {
            System.out.println("It only works with numbers from 1 to 3");
            modify();
        }

        System.out.println("Do you want to check something else?");
        System.out.println("Yes(y) or not(n)");
        if (sc.next().equals("y")) {
            System.out.println("");
            menu();
        }
    }


    static void partModify() {
        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "admin", "Nochelarga123-");
            String query = "select *,p.name as nombrepersona, t.name as team,pt.dni as dni2 from participant p inner join participant_have_team pt on p.dni=pt.dni inner join team t on pt.id_team = t.id_team where p.dni like '%'";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);


            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("| %-11s | %-27s | %-11s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-20s | ", "dni", "name", "nationality", "age", "birth_date", "gender", "height", "weight", "type", "is_active", "back_number", "team");
            System.out.println("");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-11s | %-27s | %-11s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-11s | %-20s | ", rs.getString("dni"), rs.getString("nombrepersona") + " " + rs.getString("last_name1") + " " + rs.getString("last_name2"), rs.getString("nationality"), rs.getString("age"), rs.getString("birth_date"), rs.getString("gender"), rs.getString("height"), rs.getString("weight"), rs.getString("type"), rs.getString("is_active"), rs.getString("back_number"), rs.getString("team"));
                System.out.println("");
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("National Document Number: ");
        String dni = sc.next();
        System.out.println("");
        System.out.println("What data do you want to change?");
        String dato = sc.next();
        dato = dato.toLowerCase();

        if (compColumn(dato)) {
            try {
                System.out.println("");
                System.out.println("New " + dato + ": ");
                String nuevoDato = sc.next();
                System.out.println("");

                String sql = "update participant set " + dato + "='" + nuevoDato + "' where dni='" + dni + "'";
                Statement st = conexion().createStatement();

                st.executeUpdate(sql);
                conexion().setAutoCommit(true);
                conexion().close();
                st.close();

            } catch (Exception e) {
                System.out.println("");
                System.out.println("The data entered is not correct, do you want to try another one?");
                System.out.println("Yes(y) or not(n)");
                String i = sc.next();
                System.out.println("");
                if (i.equals("y")) {
                    System.out.println("");
                    partModify();
                }
            }
        } else if (dato.equals("team")) {
            try {

                System.out.println("");
                System.out.println("New " + dato + ": ");
                String nuevoDato = sc.next();
                System.out.println("");

                String sql = "update participant_have_team set id_team=(select id_team from team where name like '" + nuevoDato + "') where dni='" + dni + "'";
                Statement st = conexion().createStatement();

                st.executeUpdate(sql);
                conexion().setAutoCommit(true);
                conexion().close();
                st.close();

            } catch (Exception e) {
                System.out.println("");
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
            System.out.println("Do you want to try again?2");
            System.out.println("Yes(y) or not(n)");
            String i = sc.next();
            System.out.println("");
            if (i.equals("y")) {
                System.out.println("");
                partModify();
            }
        }

    }

    static void teamModify() {
        Scanner sc = new Scanner(System.in);
        try {
            Statement st = conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `team` WHERE `id_team` like '%'");

            System.out.println("-------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-20s |", "Id", "name", "country");
            System.out.println("");
            System.out.println("-------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-5s | %-20s | %-20s |", rs.getString("id_team"), rs.getString("name"), rs.getString("country"));
                System.out.println("");
            }
            System.out.println("-------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Id of the team: ");
        String id = sc.next();
        System.out.println("");

        System.out.println("What data do you want to change?");
        String dato = sc.next();
        dato = dato.toLowerCase();
        try {
            System.out.println("");
            System.out.println("New " + dato + ": ");
            String nuevoDato = sc.next();
            System.out.println("");

            Statement st = conexion().createStatement();
            st.executeUpdate("update team set " + dato + "='" + nuevoDato + "' where id_team=" + id + "");


            conexion().setAutoCommit(true);
            st.close();

        } catch (Exception e2) {
            System.out.println("");
            System.out.println("The data entered is not correct, do you want to try another one?");
            System.out.println("Yes(y) or not(n)");
            String j = sc.next();
            System.out.println("");
            if (j.equals("y")) {
                System.out.println("");
                teamModify();
            }
        }
    }

    static void sportModify() {
        Scanner sc = new Scanner(System.in);
        try {
            Statement st = conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `sport` WHERE `id_sport` like '%'");

            System.out.println("-------------------------------------------------------");
            System.out.printf("| %-5s | %-20s | %-20s |", "Id", "name", "category_name");
            System.out.println("");
            System.out.println("-------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-5s | %-20s | %-20s |", rs.getString("id_sport"), rs.getString("name"), rs.getString("category_name"));
                System.out.println("");
            }
            System.out.println("-------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Id of the sport: ");
        String id = sc.next();
        System.out.println("");

        System.out.println("What data do you want to change?");
        String dato = sc.next();
        dato = dato.toLowerCase();
        try {
            System.out.println("");
            System.out.println("New " + dato + ": ");
            String nuevoDato = sc.next();
            System.out.println("");

            String sql = "update sport set " + dato + "='" + nuevoDato + "' where id_sport=" + id + "";
            Statement st = conexion().createStatement();

            st.executeUpdate(sql);
            conexion().setAutoCommit(true);
            st.close();

        } catch (Exception e2) {
            System.out.println("");
            System.out.println("The data entered is not correct, do you want to try another one?");
            System.out.println("Yes(y) or not(n)");
            String j = sc.next();
            System.out.println("");
            if (j.equals("y")) {
                System.out.println("");
                teamModify();
            }
        }
    }


    // <--------------------------> Delete <-------------------------->
    static void delete() {
        Scanner sc = new Scanner(System.in);

        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "Select an option");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.printf("| %-19s |", "1. Participant");
        System.out.println("");
        System.out.printf("| %-19s |", "2. Team");
        System.out.println("");
        System.out.printf("| %-19s |", "3. Sport");
        System.out.println("");
        System.out.printf("| %-19s |", "4. Menu");
        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("");

        String input = sc.next();
        System.out.println("");
        if (!(isNumeric(input))) {
            System.out.println("This is not a choice, only numbers");
            delete();
        }
        int select = Integer.parseInt(input);
        if (select < 5) {
            switch (select) {
                case 1:
                    partDel();
                    break;
                case 2:
                    teamDel();
                    break;
                case 3:
                    sportDel();
                    break;
                case 4:
                    menu();
            }
        } else {
            System.out.println("It only works with numbers from 1 to 3");
            delete();
        }

        System.out.println("Do you want to check something else?");
        System.out.println("Yes(y) or not(n)");
        if (sc.next().equals("y")) {
            System.out.println("");
            menu();
        }
    }


    static void partDel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------");
        System.out.printf("| %-19s |", "National document Number");
        System.out.println("");
        System.out.println("----------------------------");
        String dni = sc.next();
        if (compDNI(dni)) {
            try {
                Statement st = conexion().createStatement();
                ResultSet rs = st.executeQuery("select *,p.name as nombrepersona, t.name as team,pt.dni as dni2 from participant p inner join participant_have_team pt on p.dni=pt.dni inner join team t on pt.id_team = t.id_team where p.dni like '" + dni + "'");

                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("| %-11s | %-27s | %-11s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-20s | ", "dni", "Name", "Nationality", "Age", "Birth date", "Gender", "Height", "Weight", "Type", "Is active", "back number", "Team");
                System.out.println("");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while (rs.next()) {
                    System.out.printf("| %-11s | %-27s | %-11s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-11s | %-20s | ", rs.getString("dni"), rs.getString("nombrepersona") + " " + rs.getString("last_name1") + " " + rs.getString("last_name2"), rs.getString("nationality"), rs.getString("age"), rs.getString("birth_date"), rs.getString("gender"), rs.getString("height"), rs.getString("weight"), rs.getString("type"), rs.getString("is_active"), rs.getString("back_number"), rs.getString("team"));
                    System.out.println("");
                }
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            } catch (Exception e) {
                System.err.println("Error");
            }
        } else {
            System.out.println("This dni does not exist.");
            partDel();
        }


        System.out.println("Surely you want to delete the participant with dni: " + dni + " ?");
        System.out.println("Yes(y) or not(n)");
        if (sc.next().equals("y")) {
            try {
                Statement st = conexion().createStatement();
                st.executeUpdate("delete from participant where dni='" + dni + "'");
                System.out.println("The participant has been deleted successfully");
                System.out.println("");
                menu();

            } catch (Exception e) {
                System.out.println("Unable to delete the record due to an error");
                e.printStackTrace();
            }
        }

    }

    static void teamDel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Id team: ");
        System.out.println("");
        String id = sc.next();

        try {
            Statement st = conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `team` WHERE `id_team` like '" + id + "'");

            System.out.println("------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s |", "Id", "Name", "Country");
            System.out.println("");
            System.out.println("------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-10s | %-20s | %-20s |", rs.getString("id_team"), rs.getString("name"), rs.getString("country"));
                System.out.println("");
            }
            System.out.println("------------------------------------------------------------");
            st.close();
            rs.close();

        } catch (Exception e) {
            System.err.println("Fail to connect.");
            System.out.println("Yes(y) or not(n)");
            String i = sc.next();
            System.out.println("");
            if (i.equals("y")) {
                System.out.println("");
                teamCon();
            }

        }

        System.out.println("Surely you want to delete the participant with dni: " + id + " ?");
        System.out.println("Yes(y) or not(n)");
        if (sc.next().equals("y")) {
            try {
                Statement st = conexion().createStatement();
                st.executeUpdate("delete from team where id_team='" + id + "'");
                System.out.println("The team has been deleted successfully");
                System.out.println("");
                menu();

            } catch (Exception e) {
                System.out.println("Unable to delete the record due to an error");
                e.printStackTrace();
            }
        }
    }

    static void sportDel() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------");
        System.out.printf("| %-10s |", "Id of the sport");
        System.out.println("");
        System.out.println("-------------------");
        String id = sc.next();

        try {
            Statement st = conexion().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `sport` where id_sport=" + id + "");


            System.out.println("------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s |", "Id", "Name", "Category");
            System.out.println("");
            System.out.println("------------------------------------------------------------");
            while (rs.next()) {
                System.out.printf("| %-10s | %-20s | %-20s |", rs.getString("id_sport"), rs.getString("name"), rs.getString("category_name"));
                System.out.println("");
            }
            System.out.println("------------------------------------------------------------");
            st.close();
            rs.close();

        } catch (Exception e) {
            System.err.println("Fail to charge the Sport.");
        }

        System.out.println("Surely you want to delete the team with id: " + id + " ?");
        System.out.println("Yes(y) or not(n)");
        if (sc.next().equals("y")) {
            try {
                Statement st = conexion().createStatement();
                st.executeUpdate("delete from sport where id_sport='" + id + "'");
                System.out.println("The participant has been deleted successfully");
                System.out.println("");
                menu();

            } catch (Exception e) {
                System.out.println("Unable to delete the record due to an error");
                e.printStackTrace();
            }
        }
    }

    // <--------------------------> General <------------------------->
    private static boolean isNumeric(@NotNull String str) {
        connector.str = str;
        return (str.matches("[+-]?\\d*(\\.\\d+)?") && !str.equals(""));
    }

    static boolean compColumn(String date) {

        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "admin", "Nochelarga123-");
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

    static boolean compDNI(String dni) {
        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "admin", "Nochelarga123-");

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("select dni from participant");

            while (rs.next()) {
                if (dni.equals(rs.getString("dni")) || dni.equals("%")) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    static Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sports";
        return DriverManager.getConnection(url, "admin", "Nochelarga123-");
    }


}


