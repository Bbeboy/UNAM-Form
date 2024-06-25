package fes.aragon.modelo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.css.PseudoClass;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Screen;

public class BaseController {
    protected boolean verificacion=false;
    /*
     * EXPRESIONES REGULARES
     * 0 palabras sin espacio
     * 1 solo números
     * 2 validar RFC
     * 3 validar correo
     * 4 validar teléfono
     * 5 folio
     * 6 trabajador
     * 7 cliente
     */
    private String[] expresiones= {"(\\w+)", //0Palabras
            "(\\d+)(\\.\\d{1,2})", //1Numeros
            "(\\w){13}", //2RFC
            "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", //3 Correo
            "(\\d){10}", //4 Telefono
            "(\\d){8}", //5Folio
            "(\\d){6}", //6 Trabajador
            "(\\d){5}", //7Cliente
            "(\\d){5}", //8 Monto
            "^[a-zA-Z]{1,30}$", //9 Nombre
            "^[a-zA-Z]{1,30}$", //10Apellido patero
            "^[a-zA-Z]{1,30}$", //11Apellido materno
            "^[a-zA-Z]{1,120}$", //12Domicilio
            "^[a-zA-Z]{1,40}$", //13ColMunDem
            "(\\d){5}", //14 CP
            "^[a-zA-Z]{1,60}$", //15Ciudad
            "(\\d){2}", //16TiempoResidencia
            "^[a-zA-Z]{1,18}$", //17CURP


                    };

    public void verificarEntrada(TextField caja,TipoError error) {
        caja.textProperty().addListener(evento -> {
            String text = caja.getText();
            if(text ==null) {
                text="";
            }
            String patron = expresiones[error.ordinal()];
            Pattern pt = Pattern.compile(patron);
            Matcher match = pt.matcher(text);
            caja.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), !match.matches());
            this.verificacion=match.matches();
        });
    }
    public void ventanaEmergente(String titulo, String encabezado, String contenido) {
        Alert alerta;
        alerta = new Alert(AlertType.INFORMATION);
        alerta.setX(Screen.getPrimary().getVisualBounds().getMaxX() + 300);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}
