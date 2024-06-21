package fes.aragon.conexion;

import fes.aragon.modelo.Cliente;
import java.sql.*;

public class Conexion {
    private String url="jdbc:mariadb://localhost:3306/javafx?useSSL=false&serverTimezone=UTC";
    private String usuario="root";
    private String clave=""; //Agregar la clave para ingresar a la DB
    private Connection cnn=null;

    public Conexion()throws  ClassNotFoundException, SQLException{
        Class.forName("org.mariadb.jdbc.Driver");
        cnn = DriverManager.getConnection(url,usuario,clave);

    }
    public void cerrarConexion() throws SQLException {
        cnn.close();
    }
    public Cliente insertar(Cliente cliente) throws SQLException {
        String query="insert into cliente"+
                "(folio,cliente,fecha_solicitud,monto_solicitado,\n" +
                "cuenta_cliente,plazo,nombre,apellidoPaterno,\n" +
                "apellidoMaterno,fecha_nacimiento,estado_nacimiento,\n" +
                "nacionalidad,sexo,regimen_fiscal,rfc,curp,\n" +
                "grado_estudio,correo,telefono,tipo_propiedad,\n" +
                "domicilio,col_mun_dem,ciudad,estado,cp,recidemcia_anos)"+
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement solicitud=cnn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                solicitud.setString(1, cliente.getFolio());
                solicitud.setString(2, cliente.getCliente());
                solicitud.setDate(3,new java.sql.Date(cliente.getFecha_solicitud().getTime()));
                solicitud.setDouble(4,cliente.getMontoSolicitado());
                solicitud.setInt(5,cliente.getCuentaCliente());
                solicitud.setInt(6,cliente.getPlazo());
                solicitud.setString(7,cliente.getNombre());
                solicitud.setString(8,cliente.getApellidoPaterno());
                solicitud.setString(9,cliente.getApellidoMaterno());
                solicitud.setDate(10,new java.sql.Date(cliente.getFechaNacimiento().getTime()));
                solicitud.setString(11,cliente.getEstadoNacimiento());
                solicitud.setString(12,cliente.getNacionalidad());
                solicitud.setBoolean(13,cliente.isSexo());
                solicitud.setBoolean(14,cliente.isRegimen_fiscal());
                solicitud.setString(15,cliente.getRfc());
                solicitud.setString(16,cliente.getCurp());
                solicitud.setInt(17,cliente.getGradoEstudio());
                solicitud.setString(18,cliente.getCorreo());
                solicitud.setString(19,cliente.getTelefono());
                solicitud.setInt(20,cliente.getTipoPropiedad());
                solicitud.setString(21,cliente.getDomicilio());
                solicitud.setString(22,cliente.getColMunDem());
                solicitud.setString(23,cliente.getCiudad());
                solicitud.setString(24,cliente.getEstado());
                solicitud.setString(25,cliente.getCp());
                solicitud.setInt(26,cliente.getRecidenciaAnos());
                solicitud.executeUpdate();
                ResultSet resultado=solicitud.getGeneratedKeys();
            if(resultado.next()) {
            cliente.setId_clt(resultado.getInt(1));
            }
            solicitud.close();
            resultado.close();
            return cliente;
    }
}
