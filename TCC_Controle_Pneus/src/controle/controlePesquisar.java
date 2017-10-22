/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 *
 * @author diego
 */
public class controlePesquisar {
    
    @FXML
    private RadioButton rdRadioBuscaCPF;
    @FXML
    private ComboBox<?> cbListNomePfPesq;
    @FXML
    private ComboBox<?> cbListNomePjPesq;
    @FXML
    private RadioButton rdBuscaCNPJ;
    @FXML
    private ComboBox<?> cbListPneus;
    @FXML
    private TextField edResResponsavel;
    @FXML
    private TextField edResDot;
    @FXML
    private TextField edResMarca;
    @FXML
    private TextField edResDescricao;
    @FXML
    private TextField edResMedida;
    @FXML
    private RadioButton rdBuscarNomePF;
    @FXML
    private RadioButton rdBuscarRazaoSocialPJ;
    @FXML
    private ComboBox<?> cbListCpfPesq;
    @FXML
    private ComboBox<?> cbListCnpjPesq;
    @FXML
    private TextField edResData;
    @FXML
    private TextField edResTemperatura;
    @FXML
    private TextField edResCustoTotal;
    @FXML
    private TextField edResTempo;
    @FXML
    private DatePicker dpDataPesquisa;
    
}
