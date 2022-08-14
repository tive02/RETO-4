package model.dao;

import java.sql.*;

import utilities.*;

public class ProyectoBancoDao {
    public static ResultSet consulta(String banco) {
        try {
            var conn = JDBCUtilities.getConnection();
            String csql = "SELECT Proyecto.ID_Proyecto as ID , Proyecto.Constructora, Proyecto.Ciudad, Proyecto.Clasificacion, tipo.Estrato, Lider.Nombre || ' ' || Primer_Apellido || ' ' || Segundo_Apellido as LIDER FROM Proyecto JOIN Tipo on Proyecto.ID_Tipo = TIPO.ID_Tipo JOIN Lider on Proyecto.ID_Lider = Lider.ID_Lider WHERE Banco_Vinculado = '"
                    + banco + "' ORDER by Fecha_Inicio DESC, Ciudad, Constructora ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(csql);

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return rs;
    }

}
