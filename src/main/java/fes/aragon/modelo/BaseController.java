package fes.aragon.modelo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.css.PseudoClass;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
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
    private String[] expresiones= {"(\\w+)",
            "(\\d+)(\\.\\d{1,2})",
            "(\\w){13}",
            "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            "(\\d){10}",
            "(\\d){8}",
            "(\\d){6}",

            "(\\d){5}"};

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
        //alerta.setX(Screen.getPrimary().getVisualBounds().getMaxX() + 300);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}
