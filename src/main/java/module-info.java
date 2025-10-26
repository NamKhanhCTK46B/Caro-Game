module com.kthp.tro_choi_caro {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.kthp.tro_choi_caro to javafx.fxml;
    opens com.kthp.tro_choi_caro.controller to javafx.fxml;
    
    exports com.kthp.tro_choi_caro;
    exports com.kthp.tro_choi_caro.controller;
    exports com.kthp.tro_choi_caro.model;
    exports com.kthp.tro_choi_caro.view;
    exports com.kthp.tro_choi_caro.strategy;
}
