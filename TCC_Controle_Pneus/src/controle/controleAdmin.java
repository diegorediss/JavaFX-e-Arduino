/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import app.App;
import dao.daoUsuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Usuario;

/**
 *
 * @author diego
 */

public abstract  class controleAdmin extends App implements Initializable, EventHandler<ActionEvent>{
    
    @FXML
    private TextField edUsuarioNovo;
    @FXML
    private TextField edSenhaAtualizada;
    @FXML
    private Button btAddUsuario;
    @FXML
    private ComboBox<?> cbListUsuarios;
    @FXML
    private Button btLoginAtulaizar;
    @FXML
    private TextField edSenhaNova;
    @FXML
    private RadioButton rdTipoAdmin;
    @FXML
    private RadioButton rdTipoFuncionario;

    
    public controleAdmin (){
    
}
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        // Vazio, por enquanto...
    }

    
    @FXML 
    protected void addUsuario(ActionEvent event)  throws SQLException, Exception {
    if (edUsuarioNovo.getText().isEmpty() || edSenhaNova.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
            alert.showAndWait();
        } else {
                Usuario usuario = new Usuario();
            usuario.setNomeUsuario(edUsuarioNovo.getText());
            usuario.setSenhaUsuario(edSenhaNova.getText());
                daoUsuario dao = new daoUsuario();              
            try {
                dao.incluir(usuario);
            } catch (SQLException ex) {
                Logger.getLogger(controleAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Alert alert = new Alert(Alert.AlertType.WARNING, "OK");
            alert.showAndWait();
    }
    
    
    
}


