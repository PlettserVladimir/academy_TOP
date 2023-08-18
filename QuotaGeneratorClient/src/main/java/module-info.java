module com.top.quotageneratorclient {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;
            
    opens com.top.quotageneratorclient to javafx.fxml;
    exports com.top.quotageneratorclient;
}