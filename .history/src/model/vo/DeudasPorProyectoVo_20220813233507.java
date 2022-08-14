package model.vo;

import java.sql.*;
import model.dao.*;

public class DeudasPorProyectoVo {
    public static void valores(String limiteInferior) {
        try {
            ResultSet rs = DeudasPorProyectoVo.consulta(limiteInferior);

            while (rs.next()) {
                int id = rs.getInt("ID_Proyecto");
                float valor = rs.getFloat("VALOR");
                System.out.println(String.format("%3d %,15.1f", id, valor));
            }

            rs.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }}

    try{

    var conn = JDBCUtilities.getConnection();
    Statement stmt = null;
    ResultSet rs = null;

    String csql = "SELECT Proyecto.ID_Proyecto, sum (Cantidad*Precio_Unidad ) as VALOR FROM Proyecto JOIN MaterialConstruccion ON Compra.ID_MaterialConstruccion = MaterialConstruccion.ID_MaterialConstruccion JOIN Compra ON Proyecto.ID_Proyecto = Compra.ID_Proyecto WHERE Pagado = 'No' GROUP by Proyecto.ID_Proyecto HAVING sum (Cantidad*Precio_Unidad ) >"
            + limiteInferior + " ORDER by VALOR DESC; ";stmt=conn.createStatement();rs=stmt.executeQuery(csql);

    rs.close();stmt.close();conn.close();
}catch(
Exception e
)
