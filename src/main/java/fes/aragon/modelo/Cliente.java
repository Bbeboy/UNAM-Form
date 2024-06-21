package fes.aragon.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    private Integer id_clt;
    private String folio;
    private String cliente;
    private Date fecha_solicitud;
    private Double montoSolicitado;
    private Integer cuentaCliente;
    private Integer plazo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private String estadoNacimiento;
    private String nacionalidad;
    private boolean sexo;
    private boolean regimen_fiscal;
    private String rfc;
    private String curp;
    private int gradoEstudio;
    private String correo;
    private String telefono;
    private int tipoPropiedad;
    private String domicilio;
    private String colMunDem;
    private String ciudad;
    private String estado;
    private String cp;
    private int recidenciaAnos;
}
