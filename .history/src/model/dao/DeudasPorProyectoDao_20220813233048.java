package model.dao;

public class DeudasPorProyectoDao {
    public static ResultSet consulta(String banco) {
        Statement stmt = null;
        ResultSet rs = null;
        String csql = "SELECT Proyecto.ID_Proyecto as ID , Proyecto.Constructora, Proyecto.Ciudad, Proyecto.Clasificacion, tipo.Estrato, Lider.Nombre || ' ' || Primer_Apellido || ' ' || Segundo_Apellido as LIDER FROM Proyecto JOIN Tipo on Proyecto.ID_Tipo = TIPO.ID_Tipo JOIN Lider on Proyecto.ID_Lider = Lider.ID_Lider WHERE Banco_Vinculado = '"
                + banco + "' ORDER by Fecha_Inicio DESC, Ciudad, Constructora ";

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