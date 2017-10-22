/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import model.Cliente;



public class controleCliente {
    @FXML
    private Tab tabCliente;
    @FXML
    private TextField edNomePj;
    @FXML
    private TextField edCelular;
    @FXML
    private TextField edFixo;
    @FXML
    private ComboBox<?> cbListNomePj;
    @FXML
    private TextField edResponsavelPJ;
    @FXML
    private TextField edEmail;
    @FXML
    private Button btSalvarCliente;
    @FXML
    private ComboBox<?> cbListNomePf;
    @FXML
    private TextField edNomePf;
    @FXML
    private RadioButton rdPf;
    @FXML
    private RadioButton rdPj;
    @FXML
    private ComboBox<?> cbListCpf;
    @FXML
    private TextField edCnpj;
    @FXML
    private ComboBox<?> cbListCnpj;
    @FXML
    private TextField edCpf;
    
    private List<Cliente> listClientes;
    private ObservableList<Cliente> observableListClientes;
    
    
    public controleCliente() {

    }
    
    
        @FXML
    private void popularTabCliente(ActionEvent event) {
            Cliente cliente = new Cliente();
         if(rdPf.isSelected()) {
            cbListNomePf.setItems((ObservableList) cliente.getNomeClientes());
            cbListCpf.setItems((ObservableList) cliente.getCpfs());

         }
         if(rdPj.isSelected()) {
            cbListNomePj.setItems((ObservableList) cliente.getRazaoClientes());
            cbListCnpj.setItems((ObservableList) cliente.getCnpjs());
         }
    }
    
    @FXML
    private void popularTabCliente(Event event) {
    }
    

    @FXML
    public void salvarCliente(ActionEvent evento) throws NoSuchAlgorithmException, UnsupportedEncodingException, ParseException {
        if (edCelular.getText().isEmpty() || edCnpj.getText().isEmpty()
                || edCpf.getText().isEmpty() || edEmail.getText().isEmpty()
                || edFixo.getText().isEmpty() || edNomePf.getText().isEmpty()
                || edNomePj.getText().isEmpty() || edResponsavelPJ.getText().isEmpty()
                || cbListCnpj.getValue().equals(null) || cbListCpf.getValue().equals(null)
                || cbListNomePf.getValue().equals(null) || cbListNomePj.getValue().equals(null)
                || rdPf.isSelected() && rdPj.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos!");
            alert.showAndWait();
        } else {
            Cliente cliente = new Cliente();
            cliente.setNomeCliente(edNomePf.getText());
            cliente.setRazaoCliente(edNomePj.getText());
            cliente.setCpf(edCpf.getText());
            cliente.setCnpj(edCnpj.getText());
            cliente.setCelular(edCelular.getText());
            cliente.setEmail(edEmail.getText());
            cliente.setFixo(edFixo.getText());
            cliente.setResponsavelPj(edResponsavelPJ.getText());
            //cliente.setCnpj((Cliente) cbListCnpj.getItems());
        }
    }
    
    
}
