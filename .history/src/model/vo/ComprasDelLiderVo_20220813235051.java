package model.vo;

import java.sql.*;
import model.dao.*;

public class ComprasDelLiderVo {
    public static void valores() {
        try {
            ResultSet rs = ComprasDelLiderDao.consulta();

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

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
