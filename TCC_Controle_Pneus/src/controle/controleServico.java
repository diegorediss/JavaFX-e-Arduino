package controle;

import dao.daoConserto;
import dao.daoUsuario;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import model.Conserto;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author diego
 */
public abstract class controleServico  implements Initializable, EventHandler<ActionEvent>  {

    @FXML
    private Tab tabServico;
    @FXML
    private TextField edTempoPreparo;
    @FXML
    private TextField edTempoVulcanizacao;
    @FXML
    private TextField edTemperatura;
    @FXML
    private TextField edCustoTotal;
    @FXML
    private DatePicker dpDataConserto;
    @FXML
    private TextField edTempoReal;
    @FXML
    private TextField edTemperaturaReal;
    @FXML
    private ComboBox<?> cbListResponsavel;
    @FXML
    private RadioButton rdTipoI;
    @FXML
    private RadioButton rdTipoII;
    @FXML
    private RadioButton rdLigar;
    @FXML
    private RadioButton rdDesligar;
    @FXML
    private Button btSalvarConserto;
    @FXML
    private Button btUnoLigar;
    

    private List<Conserto> listConsertos;
    private ObservableList<Conserto> observableListConsertos;
    
    Conserto conserto = new Conserto();
    
    public controleServico() {

    }

    @FXML
    private void popularTabServico(ActionEvent event) {
        cbListResponsavel.setItems((ObservableList) conserto.getResponsavel());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpDataConserto.setValue(LocalDate.now());
        // Inicia o datepicker com a data atual
    }
    
    
    @FXML
    public void salvarConserto(ActionEvent event) throws SQLException, Exception {
        if (edTempoPreparo.getText().isEmpty() || edTemperatura.getText().isEmpty()
                || edTempoVulcanizacao.getText().isEmpty() || edCustoTotal.getText().isEmpty()
                //|| cbListResponsavel.getValue().equals(null) 
              //  || !rdTipoI.isSelected() && !rdTipoII.isSelected()
                || dpDataConserto.getValue().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
            alert.showAndWait();
        } else {
            Usuario usuario = new Usuario();
            Conserto conserto = new Conserto();
            conserto.setTemperaturaVulcanizacao(edTemperatura.getText());
            conserto.setTempoPreparo(edTempoPreparo.getText());
            conserto.setTempoVulcanizacao(edTempoVulcanizacao.getText());
            conserto.setCustoTotal(edCustoTotal.getText());
           // conserto.setResponsavel((Usuario) cbListResponsavel.getItems());
            //conserto.setResponsavel((Usuario) cbResponsavel.getProperties());
            Date dataConserto = new Date();
            conserto.setDataConserto(java.sql.Date.valueOf(dpDataConserto.getValue()));
            
            daoConserto dao = new daoConserto();
            try {
                dao.incluir(conserto);
            } catch (SQLException ex) {
                Logger.getLogger(controleServico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert alert = new Alert(Alert.AlertType.WARNING, "ok");
            alert.showAndWait();
    }

}
