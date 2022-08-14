package model.vo;

import java.sql.*;
import model.dao.*;

public class DeudasPorProyectoVo {
    public static void valores(Double limiteInferior) {
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
    }
}
