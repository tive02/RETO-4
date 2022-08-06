import Utilidades.*;
import java.sql.*;

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

    public void totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior) {
        System.out.println(repitaCaracter('=', 1) + " TOTAL DEUDAS POR PROYECTO "
                + repitaCaracter('=', 1));
        if (limiteInferior != null) {
            System.out.println(String.format("%3s %15s", "ID", "VALOR "));
            System.out.println(repitaCaracter('-', 29));
            // TODO Imprimir en pantalla la información del total adeudado
            try {
                var conn = JDBCUtilities.getConnection();
                Statement stmt = null;
                ResultSet rs = null;

                String csql = "SELECT Proyecto.ID_Proyecto, sum (Cantidad*Precio_Unidad ) as VALOR FROM Proyecto JOIN MaterialConstruccion ON Compra.ID_MaterialConstruccion = MaterialConstruccion.ID_MaterialConstruccion JOIN Compra ON Proyecto.ID_Proyecto = Compra.ID_Proyecto WHERE Pagado = 'No' GROUP by Proyecto.ID_Proyecto HAVING sum (Cantidad*Precio_Unidad ) >"
                        + limiteInferior + " ORDER by VALOR DESC; ";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(csql);

                while (rs.next()) {
                    int id = rs.getInt("ID_Proyecto");
                    float valor = rs.getFloat("VALOR");
                    System.out.println(String.format("%3d %,15.1f", id, valor));
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

    public void lideresQueMasGastan() {
        System.out.println(repitaCaracter('=', 6) + " 10 LIDERES MAS COMPRADORES "
                + repitaCaracter('=', 7));
        System.out.println(String.format("%-25s %15s", "LIDER", "VALOR "));
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
