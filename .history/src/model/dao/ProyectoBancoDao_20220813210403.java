package model.dao;

import java.sql.*;

import utilities.*;

public class ProyectoBancoDao {
    public static ResultSet consulta(String banco) {
        try {
            var conn = JDBCUtilities.getConnection();
            Statement stmt = null;
            ResultSet rs = null;
            String csql = "SELECT Proyecto.ID_Proyecto as ID , Proyecto.Constructora, Proyecto.Ciudad, Proyecto.Clasificacion, tipo.Estrato, Lider.Nombre || ' ' || Primer_Apellido || ' ' || Segundo_Apellido as LIDER FROM Proyecto JOIN Tipo on Proyecto.ID_Tipo = TIPO.ID_Tipo JOIN Lider on Proyecto.ID_Lider = Lider.ID_Lider WHERE Banco_Vinculado = '"
                    + banco + "' ORDER by Fecha_Inicio DESC, Ciudad, Constructora ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(csql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String constructora = rs.getString("Constructora");
                String ciudad = rs.getString("Ciudad");
                String clasificacion = rs.getString("Clasificacion");
                int estrato = rs.getInt("Estrato");
                String lider = rs.getString("Lider");
                System.out.println(String.format("%3d %-25s %-20s %-15s %7d %-30s", id, constructora, ciudad,
                        clasificacion, estrato, lider));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }

}
