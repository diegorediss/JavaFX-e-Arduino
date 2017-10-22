package controle;

import dao.daoConexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author diego
 */
public abstract class controleLogin implements Initializable {
  
    @FXML
    private TextField edUsuario;
    @FXML
    private Button btEntraLogin;
    @FXML
    private PasswordField edSenha;
    
    
    public controleLogin() {
    }

    
   
}