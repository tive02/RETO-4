package model.dao;

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
    }}try{

    var conn = JDBCUtilities.getConnection();
    Statement stmt = null;
    ResultSet rs = null;

    stmt=conn.createStatement();rs=stmt.executeQuery(csql);

    while(rs.next())
    {
        int id = rs.getInt("ID_Proyecto");
        float valor = rs.getFloat("VALOR");
        System.out.println(String.format("%3d %,15.1f", id, valor));
    }

    rs.close();stmt.close();conn.close();}catch(
    Exception e)
    {
    // TODO: handle exception
    System.out.println(e);
}