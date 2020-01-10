import java.sql.*;

public class connection {
    public static void main(String[] args) {

        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion =  DriverManager.getConnection(url,"admin","Nochelarga123-");

            String query = "SELECT * FROM participant";

            // Creamos java attement
            Statement st = conexion.createStatement();

            //Execute the query and get a java resulset
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                // Mostramos lo que queremos de la query hecha.
                System.out.println(rs.getString("name") + " " + rs.getString("dni"));

            }
            st.close();
            rs.close();

        } catch(Exception e) {
            System.err.println(e.getMessage());
        }


    }
}
