package fes.aragon.controller;

import fes.aragon.modelo.*;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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
    private TextField txtTelefonoCasa;

    @FXML
    private TextField txtTelefonoCelular;

    @FXML
    void accionVerificar(ActionEvent event) {
        if(this.verificar() && verificacion) {

            System.out.println("Valido");
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
// verificando cada campo
        this.verificarEntrada(txtNumFolio, TipoError.FOLIO);
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
    }
    private boolean verificar() {
        boolean valido = true;
        if (this.txtNumFolio.getText() == null || this.txtNumFolio.getText().trim().isEmpty()) {
            valido = false;
            this.mensajes+="Número de folio no valido\n";
        }
        if(this.cbbPlazoMeses.getSelectionModel().getSelectedIndex()==-1) {
            valido = false;
            this.mensajes+="Seleccione un plazo\n";
        }
        if(!this.rdbFemenino.isSelected() && !this.rdbMasculino.isSelected()) {
            valido = false;
            this.mensajes+="Seleccione un sexo\n";
        }

        return valido;
    }
}

