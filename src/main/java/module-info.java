module fes.aragon.formulario {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires static lombok;
    requires org.mariadb.jdbc;
    requires org.checkerframework.checker.qual;

    exports fes.aragon.inicio;
    opens fes.aragon.inicio to javafx.fxml;
    exports fes.aragon.controller;
    opens fes.aragon.controller to javafx.fxml;
    exports fes.aragon.modelo;
    opens fes.aragon.modelo to javafx.fxml;


}