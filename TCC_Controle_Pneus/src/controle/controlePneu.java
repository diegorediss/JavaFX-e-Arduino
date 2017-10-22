package controle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseDragEvent;
import model.Pneu;

/**
 * FXML Controller class
 *
 * @author diego
 */
public abstract class controlePneu implements Initializable {

    @FXML
    private Tab tabPneu;
    @FXML
    private ComboBox<?> cbListDescricao;
    @FXML
    private ComboBox<?> cbListMedida;
    @FXML
    private ComboBox<?> cbListMarca;
    @FXML
    private TextArea edObs;
    @FXML
    private TextField edDescricao;
    @FXML
    private TextField edMedida;
    @FXML
    private TextField edMarca;
    @FXML
    private TextField edSerie;
    @FXML
    private TextField edDot;
    @FXML
    private Button btLimparDescricao;
    
    Pneu pneu = new Pneu();

    public controlePneu() {

    }
    
 //@FXML
    //private void popularTabServico(ActionEvent event) {
    //    cbListDescricao.setItems((ObservableList) pneu.getDescricao());
    //    cbListMarca.setItems((ObservableList) pneu.getMarca());
    //    cbListMedida.setItems((ObservableList<?>) cbListMedida) pneu.getMedida();


   // }    


}