import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Insert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion =  DriverManager.getConnection(url,"root","tuenti1997");

            String query = "SELECT dni FROM `participant`";

            // Creamos java attement
            Statement st = conexion.createStatement();

            //Execute the query and get a java resulset
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString("dni"));

            }
            st.close();
            rs.close();

        } catch(Exception e) {
            System.err.println(e.getMessage());
        }


    }
}

