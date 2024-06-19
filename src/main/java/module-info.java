module fes.aragon.formulario {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens fes.aragon.formulario to javafx.fxml;
    exports fes.aragon.formulario;
}