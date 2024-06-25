package fes.aragon.controller;

import fes.aragon.conexion.Conexion;
import fes.aragon.modelo.*;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.IsoChronology;
import java.util.Date;
import java.util.ResourceBundle;

public class ApplicationController extends BaseController implements Initializable {
    private String mensajes = "";
    @FXML
    private Button btnVerificar;

    @FXML
    private ChoiceBox<String> cbbEstado;

    @FXML
    private ChoiceBox<String> cbbGradoEstudios;

    @FXML
    private ChoiceBox<String> cbbNacionalidad;

    @FXML
    private ChoiceBox<String> cbbPaisNacimiento;

    @FXML
    private ChoiceBox<Integer> cbbPlazoMeses;

    @FXML
    private DatePicker dtpFechaNacimiento;

    @FXML
    private DatePicker dtpFechaSolicitud;

    @FXML
    private ToggleGroup estadoCivil;

    @FXML
    private ToggleGroup grupoSexo;

    @FXML
    private ToggleGroup propiedad;

    @FXML
    private RadioButton rdbAsalariado;

    @FXML
    private RadioButton rdbCasado;

    @FXML
    private RadioButton rdbDivorciado;

    @FXML
    private RadioButton rdbFamiliares;

    @FXML
    private RadioButton rdbFemenino;

    @FXML
    private RadioButton rdbHipotecada;

    @FXML
    private RadioButton rdbHonorarios;

    @FXML
    private RadioButton rdbMasculino;

    @FXML
    private RadioButton rdbPropia;

    @FXML
    private RadioButton rdbRentada;

    @FXML
    private RadioButton rdbSeparacionBienes;

    @FXML
    private RadioButton rdbSociedadConyugal;

    @FXML
    private RadioButton rdbSoltero;

    @FXML
    private RadioButton rdbUnionLibre;

    @FXML
    private ToggleGroup regimen;

    @FXML
    private TextField txtAnosResidencia;

    @FXML
    private TextField txtApellidoMaterno;

    @FXML
    private TextField txtApellidoPaterno;

    @FXML
    private TextField txtCP;

    @FXML
    private TextField txtCURP;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtColMunDem;

    @FXML
    private TextField txtColonia;

    @FXML
    private TextField txtDomicilio;

    @FXML
    private TextField txtMontoSolicitar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNumCliente;

    @FXML
    private TextField txtNumFolio;

    @FXML
    private TextField txtNumTrabajador;

    @FXML
    private TextField txtRFX;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefonoCelular;

    @FXML
    void accionVerificar(ActionEvent event) {
        if(this.verificar() && verificacion) {
            System.out.println("Valido");
            this.almacenar(); //Conexion a la BD
        }else {
            this.mensajes+="Ningun campo debe estar en rojo";
            this.ventanaEmergente("Datos no validos", "Por favor valida la información,", mensajes);
            this.mensajes="";
        }
    }
    @SuppressWarnings("unused")
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.cbbPlazoMeses.getItems().addAll(12, 18, 24, 32);
        this.cbbPaisNacimiento.getItems().addAll("México", "Canada", "Estados Unidos");
        this.cbbNacionalidad.getItems().addAll("Mexicana", "Canadiense", "Estadounidense");
        this.cbbGradoEstudios.getItems().addAll("Primaria", "Secundaria", "Nivel Medio Superior", "Nivel Superior");
        this.cbbEstado.getItems().addAll("Aguascalientes", "Baja California", "Baja California Sur",
                "Campeche", "Coahuila");

        // verificando longitud de cada campo
        txtNumFolio.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 9 ? c : null;}));
        txtNumTrabajador.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 7 ? c : null;}));
        txtNumCliente.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 6 ? c : null;}));
        txtMontoSolicitar.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 6 ? c : null;}));
        txtNombre.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 31 ? c : null;}));
        txtApellidoMaterno.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 31 ? c : null;}));
        txtApellidoPaterno.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 31 ? c : null;}));
        txtRFX.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 14 ? c : null;}));
        txtCURP.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 18 ? c : null;}));
        txtCorreo.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 71 ? c : null;}));
        txtTelefonoCelular.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 11 ? c : null;}));
        txtDomicilio.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 121 ? c : null;}));
        txtColMunDem.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 41 ? c : null;}));
        txtCiudad.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 61 ? c : null;}));
        txtCP.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 6 ? c : null;}));
        txtAnosResidencia.setTextFormatter(new TextFormatter<>(c ->  {return c.getControlNewText().length() < 3 ? c : null;}));

        //Verificando campos
        this.verificarEntrada(txtNumFolio, TipoError.FOLIO);
        this.verificarEntrada(txtNumTrabajador, TipoError.TRABAJADOR);
        //this.verificarEntrada(dtpFechaSolicitud, TipoError.FOLIO);
        this.verificarEntrada(txtMontoSolicitar, TipoError.MONTO);
        this.verificarEntrada(txtNumCliente, TipoError.CLIENTE);
        this.verificarEntrada(txtNombre, TipoError.NOMBRE);
        this.verificarEntrada(txtApellidoPaterno, TipoError.APELLIDO);
        this.verificarEntrada(txtApellidoMaterno, TipoError.APELLIDOMATERNO);
        this.verificarEntrada(txtRFX, TipoError.RFC);
        this.verificarEntrada(txtCURP, TipoError.CURP);
        this.verificarEntrada(txtCorreo, TipoError.CORREO);
        this.verificarEntrada(txtTelefonoCelular, TipoError.TELEFONO);
        this.verificarEntrada(txtDomicilio, TipoError.DOMICILIO);
        this.verificarEntrada(txtColMunDem, TipoError.COLMUNDEM);
        this.verificarEntrada(txtCP, TipoError.CP);
        this.verificarEntrada(txtCiudad, TipoError.CIUDAD);
        this.verificarEntrada(txtAnosResidencia, TipoError.ANOSRESIDENCIA);

        //Un detector de cambios para rastrear la selección en el grupo
        this.grupoSexo.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                                     observable,
                                                             Toggle oldBtn,
                                                             Toggle newBtn)->{
            String etiqueta="";
            if(newBtn!=null) {
                etiqueta=((Labeled)newBtn).getText();
                System.out.println(etiqueta);
            }
        });
        this.regimen.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>

                                                                   observable,
                                                           Toggle oldBtn,
                                                           Toggle newBtn)->{
            String etiqueta="";
            if(newBtn!=null) {
                etiqueta=((Labeled)newBtn).getText();
                System.out.println(etiqueta);
            }
        });

        this.estadoCivil.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>

                                                                       observable,
                                                               Toggle oldBtn,
                                                               Toggle newBtn)->{
            String etiqueta="";
            if(newBtn!=null) {
                etiqueta=((Labeled)newBtn).getText();
                System.out.println(etiqueta);
            }
        });

        this.propiedad.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>

                                                                     observable,
                                                             Toggle oldBtn,
                                                             Toggle newBtn)->{
            String etiqueta="";
            if(newBtn!=null) {
                etiqueta=((Labeled)newBtn).getText();
                System.out.println(etiqueta);
            }
        });
    }
    private boolean verificar() {
        boolean valido = true;
        LocalDate dateNow = LocalDate.now();
        if (this.dtpFechaNacimiento.getValue() == null ||
                (this.dtpFechaNacimiento.getValue().until(IsoChronology.INSTANCE.dateNow()).getYears() < 18) ||
                (this.dtpFechaNacimiento.getValue().until(IsoChronology.INSTANCE.dateNow()).getYears() > 100)) {
            valido = false;
            this.mensajes+="Edad minima 18 años y maxima 99\n";
        }
        if ((this.dtpFechaSolicitud.getValue() != null && !dtpFechaSolicitud.getValue().equals(dateNow)) ||
                (this.dtpFechaSolicitud.getValue() == null)){
            valido = false;
            this.mensajes+="El dia debe ser el actual\n";
        }
        if (this.txtNumFolio.getText() == null || this.txtNumFolio.getText().trim().isEmpty()) {
            valido = false;
            this.mensajes+="Número de folio no valido\n";
        }
        if(this.cbbPlazoMeses.getSelectionModel().getSelectedIndex()==-1) {
            valido = false;
            this.mensajes+="Seleccione un plazo\n";
        }
        if(this.cbbEstado.getSelectionModel().getSelectedIndex()==-1) {
            valido = false;
            this.mensajes+="Seleccione un Estado\n";
        }
        if(this.cbbGradoEstudios.getSelectionModel().getSelectedIndex()==-1) {
            valido = false;
            this.mensajes+="Seleccione un grado de estudios\n";
        }
        if(this.cbbNacionalidad.getSelectionModel().getSelectedIndex()==-1) {
            valido = false;
            this.mensajes+="Seleccione una nacionalidad\n";
        }
        if(this.cbbPaisNacimiento.getSelectionModel().getSelectedIndex()==-1) {
            valido = false;
            this.mensajes+="Seleccione un pais de nacimiento\n";
        }
        if(!this.rdbFemenino.isSelected() && !this.rdbMasculino.isSelected()) {
            valido = false;
            this.mensajes+="Seleccione un sexo\n";
        }
        if(!this.rdbAsalariado.isSelected() && !this.rdbHonorarios.isSelected()) {
            valido = false;
            this.mensajes+="Seleccione un regimen\n";
        }
        if (!this.rdbSoltero.isSelected() && !this.rdbCasado.isSelected() && !this.rdbUnionLibre.isSelected() &&
                !this.rdbDivorciado.isSelected() && !this.rdbSociedadConyugal.isSelected() && !this.rdbSeparacionBienes.isSelected()) {
            valido = false;
            this.mensajes+="Selecciona tu Estado Civil\n";
        }
        if (!this.rdbPropia.isSelected() && !this.rdbRentada.isSelected() && !this.rdbHipotecada.isSelected()
                && !this.rdbHipotecada.isSelected()){
            valido = false;
            this.mensajes+="No has seleccionado tu tipo de vivienda\n";
        }

        return valido;
    }

    private Cliente almacenar(){
        Conexion con= null;
        Cliente cl=null;
        try {
            con = new Conexion();
            cl= Cliente.builder()
                    .folio(txtNumFolio.getText())
                    .cliente(txtNumTrabajador.getText())
                    .fecha_solicitud(new Date())
                    .montoSolicitado(Double.parseDouble(txtMontoSolicitar.getText()))
                    .cuentaCliente(Integer.parseInt(txtNumCliente.getText()))
                    .plazo(cbbPlazoMeses.getValue())
                    .nombre(txtNombre.getText())
                    .apellidoPaterno(txtApellidoPaterno.getText())
                    .apellidoMaterno(txtApellidoMaterno.getText())
                    .fechaNacimiento(new Date())
                    .estadoNacimiento(cbbPaisNacimiento.getValue())
                    .nacionalidad(cbbNacionalidad.getValue())
                    .sexo(false)
                    .regimen_fiscal(false)
                    .rfc(txtRFX.getText())
                    .curp(txtCURP.getText())
                    .gradoEstudio(cbbGradoEstudios.getSelectionModel().getSelectedIndex())
                    .correo(txtCorreo.getText())
                    .telefono(txtTelefonoCelular.getText())
                    .tipoPropiedad(Integer.parseInt(propiedad.getSelectedToggle().toString()))
                    .domicilio(txtDomicilio.getText())
                    .colMunDem(txtColMunDem.getText())
                    .ciudad(txtCiudad.getText())
                    .estado(cbbEstado.getSelectionModel().toString())
                    .cp(txtCP.getText())
                    .recidenciaAnos(Integer.parseInt(txtAnosResidencia.getText()))
                    .build();
            con.insertar(cl);
            con.cerrarConexion();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cl;
    }
}

