module com.aungmyatmoe.studentmanagement {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.aungmyatmoe.studentmanagement to javafx.fxml;
    exports com.aungmyatmoe.studentmanagement;
}