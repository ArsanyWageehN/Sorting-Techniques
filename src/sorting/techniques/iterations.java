package sorting.techniques;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class iterations implements Initializable {

    @FXML 
    Label iteration_text,iteration_text11,iteration_text12,title2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title2.setText(SortingTechniques.Title);
        iteration_text.setText(SortingTechniques.test);
        iteration_text11.setText(SortingTechniques.test1);
        iteration_text12.setText(SortingTechniques.test2);
    }

}
