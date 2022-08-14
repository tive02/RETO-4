package model.dao;

import java.sql.*;

import utilities.*;

public class DeudasPorProyectoDao {
    public static ResultSet consulta(String banco) {
        Statement stmt = null;
        ResultSet rs = null;
        String csql = "SELECT Proyecto.ID_Proyecto, sum (Cantidad*Precio_Unidad ) as VALOR FROM Proyecto JOIN MaterialConstruccion ON Compra.ID_MaterialConstruccion = MaterialConstruccion.ID_MaterialConstruccion JOIN Compra ON Proyecto.ID_Proyecto = Compra.ID_Proyecto WHERE Pagado = 'No' GROUP by Proyecto.ID_Proyecto HAVING sum (Cantidad*Precio_Unidad ) >"
                + limiteInferior + " ORDER by VALOR DESC; ";
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
