package model.vo;

import java.sql.*;
import model.dao.*;

public class ComprasDelLiderVo {
    public static void valores() {
        try {
            ResultSet rs = ComprasDelLiderDao.consulta();

            while (rs.next()) {
                String lider = rs.getString("Lider");
                float valor = rs.getFloat("VALOR");
                System.out.println(String.format("%-25s %,15.1f", lider, valor));
            }

            rs.close();

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
    }
}
