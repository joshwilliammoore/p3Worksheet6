/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

module GUI1 {
    requires javafx.baseEmpty;
    requires javafx.base;
    requires javafx.fxmlEmpty;
    requires javafx.fxml;
    requires javafx.graphicsEmpty;
    requires javafx.graphics;
    requires javafx.controlsEmpty;
    requires javafx.controls;
    
    requires BL1;
    
    exports ku.piii2019.gui1;
    opens ku.piii2019.gui1;
}
