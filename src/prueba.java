

/*import java.sql.*;


public class prueba {
    public static void main(String[] args) throws SQLException {
        // jdbc vale la localizacion de la base de datos.
        String jdbc = "jdbc:mysql://localhost:3306/sports";
        Connection conexion = null;

        // Copiamos el tri cambiando admin por tu user y nochelarga por tu password
        try {
            conexion =  DriverManager.getConnection(jdbc,"admin","Nochelarga123-");
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        // Hacemos la query
        PreparedStatement stmt = conexion.prepareStatement ("SELECT * FROM participant");
        PreparedStatement stmt2 = conexion.prepareStatement("INSERT INTO participant(back_number,name,last_name1,last_name2,age,gender,dni,nationality,height,weight,type,is_active)");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // Mostramos lo que queremos de la query hecha.
            System.out.println(rs.getString("name") + " " + rs.getString("dni"));

        }
        ResultSet rs2 = stmt2.executeQuery();
        rs2.insertRow("1,'Jose', 'Gutiérrez', 'Fernandez', 25, 'Male','99999999A','España',165,70,'Player','Yes'");

    }
}
*/