package model.dao;

import java.sql.*;

import utilities.*;

public class ComprasDelLiderDao {
    public static ResultSet consulta(String banco) {
        Statement stmt = null;
        ResultSet rs = null;
        String csql = "SELECT Lider.Nombre || ' ' || Primer_Apellido || ' ' || Segundo_Apellido as LIDER, sum( Cantidad * Precio_Unidad ) as VALOR FROM Lider JOIN Proyecto ON Proyecto.ID_Lider = Lider.ID_Lider JOIN Compra ON Proyecto.ID_Proyecto = Compra.ID_Proyecto JOIN MaterialConstruccion ON Compra.ID_MaterialConstruccion = MaterialConstruccion.ID_MaterialConstruccion GROUP BY Lider ORDER BY VALOR DESC LIMIT 10  ";

        try {
            var conn = JDBCUtilities.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(csql);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        return rs;
    }
}
