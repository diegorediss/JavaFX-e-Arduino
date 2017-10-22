/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author diego
 */

@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "cliente.findAll",               
            query = "SELECT c FROM cliente c")
    , @NamedQuery(name = "cliente.findByIdCliente",     
            query = "SELECT c FROM cliente c WHERE c.id_cliente = :idCliente")
    , @NamedQuery(name = "cliente.findByNomeCliente",   
            query = "SELECT c FROM cliente c WHERE c.nome_cliente = :nomeCliente")
    , @NamedQuery(name = "cliente.findByCpf",           
            query = "SELECT c FROM cliente c WHERE c.cpf_cliente = :cpf")
    , @NamedQuery(name = "cliente.findByResponsavelj",  
            query = "SELECT c FROM cliente c WHERE c.responsavel_pj = :responsavelPj")
    , @NamedQuery(name = "cliente.findByCnpj",          
            query = "SELECT c FROM cliente c WHERE c.cnpj_cliente = :cnpj")
    , @NamedQuery(name = "cliente.findByRazaoCliente",  
            query = "SELECT c FROM cliente c WHERE c.razao_cliente = :razaoCliente")})

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cliente")
    private Integer idCliente;
    
    @Basic(optional = true)
    //@NotNull
    @Column(name = "nome_cliente", length=50)
    private String nomeCliente;
    private List<Cliente> nomeClientes;

    
    @Basic(optional = true)
    //@NotNull
    @Column(name = "razao_cliente", length=50)
    private String razaoCliente;
    private List<Cliente> razaoClientes;
    
    @Basic(optional = true)
    //@NotNull
    @Column(name = "cpf", length=11)
    private String cpf;
    private List<Cliente> cpfs;
        
    @Basic(optional = true)
    //@NotNull
    @Column(name = "cnpj", length=14)
    private String cnpj;
    private List<Cliente> cnpjs;
    
    @Basic(optional = true)
    //@NotNull
    @Column(name = "responsavel_pj", length=50)
    private String responsavelPj;
    
    @Basic(optional = true)
    //@NotNull
    @Column(name = "celular", length=50)
    private String celular;
    
    @Basic(optional = true)
    //@NotNull
    @Column(name = "fixo", length=50)
    private String fixo;
    
    @Basic(optional = true)
    //@NotNull
    @Column(name = "email", length=50)
    private String email;
    
    
    @OneToMany(mappedBy = "id_cliente")
    private List<Pneu> pneuList;



    
    
    
    public Cliente() {
    }
    
    

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<Cliente> getNomeClientes() {
        return nomeClientes;
    }

    public void setNomeClientes(List<Cliente> nomeClientes) {
        this.nomeClientes = nomeClientes;
    }

    public String getRazaoCliente() {
        return razaoCliente;
    }

    public void setRazaoCliente(String razaoCliente) {
        this.razaoCliente = razaoCliente;
    }

    public List<Cliente> getRazaoClientes() {
        return razaoClientes;
    }

    public void setRazaoClientes(List<Cliente> razaoClientes) {
        this.razaoClientes = razaoClientes;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Cliente> getCpfs() {
        return cpfs;
    }

    public void setCpfs(List<Cliente> cpfs) {
        this.cpfs = cpfs;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Cliente> getCnpjs() {
        return cnpjs;
    }

    public void setCnpjs(List<Cliente> cnpjs) {
        this.cnpjs = cnpjs;
    }

    public String getResponsavelPj() {
        return responsavelPj;
    }

    public void setResponsavelPj(String responsavelPj) {
        this.responsavelPj = responsavelPj;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFixo() {
        return fixo;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Pneu> getPneus() {
        return pneuList;
    }

    public void setPneus(List<Pneu> pneus) {
        this.pneuList = pneuList;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idCliente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + '}';
    }

   

  
    
}