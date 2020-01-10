import java.sql.*;
import java.util.Scanner;

public class connection2 {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Select an option: ");
        System.out.println("-----------------------");
        System.out.println("1. Participants");
        System.out.println("2. Teams");
        System.out.println("3. Sports listing");
        System.out.println("-----------------------");
        System.out.println("");
        int select = sc.nextInt();
        System.out.println("");

        // Conexión a base de datos.
        String url = "jdbc:mysql://localhost:3306/sports";
        Connection conexion = DriverManager.getConnection(url, "admin", "Nochelarga123-");
        Statement st = conexion.createStatement();

    }

    static void partici(String dni) {

    }

    static boolean comp_dni(String dni) {

        try {
            String url = "jdbc:mysql://localhost:3306/sports";
            Connection conexion = DriverManager.getConnection(url, "admin", "Nochelarga123-");
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

}
