
import java.sql.*;

import utilities.*;
import model.vo.DeudasPorProyectoVo;
import model.vo.ProyectoBandoVo;

public class ReportesView {
    private String repitaCaracter(Character caracter, Integer veces) {
        String respuesta = "";
        for (int i = 0; i < veces; i++) {
            respuesta += caracter;
        }
        return respuesta;
    }

    public void proyectosFinanciadosPorBanco(String banco) {
        System.out.println(repitaCaracter('=', 36) + " LISTADO DE PROYECTOS POR BANCO "
                + repitaCaracter('=', 37));
        if (banco != null && !banco.isBlank()) {
            System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s",
                    "ID", "CONSTRUCTORA", "CIUDAD", "CLASIFICACION", "ESTRATO", "LIDER"));
            System.out.println(repitaCaracter('-', 105));
            // TODO Imprimir en pantalla la información del proyecto
            ProyectoBandoVo.valores(banco);
        }
    }

    public void totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior) {
        System.out.println(repitaCaracter('=', 1) + " TOTAL DEUDAS POR PROYECTO "
                + repitaCaracter('=', 1));
        if (limiteInferior != null) {
            System.out.println(String.format("%3s %14s", "ID", "VALOR "));
            System.out.println(repitaCaracter('-', 29));
            // TODO Imprimir en pantalla la información del total adeudado
            DeudasPorProyectoVo.valores(limiteInferior);
        }
    }

    public void lideresQueMasGastan() {
        System.out.println(repitaCaracter('=', 6) + " 10 LIDERES MAS COMPRADORES "
                + repitaCaracter('=', 7));
        System.out.println(String.format("%-25s %14s", "LIDER", "VALOR "));
        System.out.println(repitaCaracter('-', 41));
        // TODO Imprimir en pantalla la información de los líderes
        try {
            var conn = JDBCUtilities.getConnection();
            Statement stmt = null;
            ResultSet rs = null;

            String csql = "SELECT Lider.Nombre || ' ' || Primer_Apellido || ' ' || Segundo_Apellido as LIDER, sum( Cantidad * Precio_Unidad ) as VALOR FROM Lider JOIN Proyecto ON Proyecto.ID_Lider = Lider.ID_Lider JOIN Compra ON Proyecto.ID_Proyecto = Compra.ID_Proyecto JOIN MaterialConstruccion ON Compra.ID_MaterialConstruccion = MaterialConstruccion.ID_MaterialConstruccion GROUP BY Lider ORDER BY VALOR DESC LIMIT 10   ";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(csql);

            while (rs.next()) {
                String lider = rs.getString("Lider");
                float valor = rs.getFloat("VALOR");
                System.out.println(String.format("%-25s %,15.1f", lider, valor));
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
