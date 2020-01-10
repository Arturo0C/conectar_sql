import java.sql.*;
import java.util.Scanner;

public class connection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Numero de DNI: ");
        String dni = sc.next();

        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion =  DriverManager.getConnection(url,"root","tuenti1997");

            String query = "SELECT * FROM `participant` WHERE `dni`='"+dni+"'";

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
                String gende = rs.getString("gender");
                int height = rs.getInt("heigth");
                double weight = rs.getDouble("weight");
                String type = rs.getString("type");
                String active = rs.getString("is_active");
                int back_number = rs.getInt("back_number");

                System.out.println("Name: "+name+" "+last_name1+" "+last_name2+".");



            }
            st.close();
            rs.close();

        } catch(Exception e) {
            System.err.println("El programa no funciona.");
        }


    }
}

