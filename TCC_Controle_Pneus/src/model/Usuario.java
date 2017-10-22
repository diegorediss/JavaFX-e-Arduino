
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
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "usuario.findAll",           
            query = "SELECT u FROM usuario u")
    , @NamedQuery(name = "usuario.findIdUsuario",   
            query = "SELECT u FROM usuario u WHERE u.id_usuario = :idVaca")
    , @NamedQuery(name = "usuario.findNome",        
            query = "SELECT u FROM usuario u WHERE u.nome_usuario = :nomeUsuario")
    , @NamedQuery(name = "usuario.findSenha",       
            query = "SELECT u FROM usuario u WHERE u.senha_usuario = :senhaUsuario")
    , @NamedQuery(name = "usuario.findTipo",        
            query = "SELECT u FROM usuario u WHERE u.tipo_usuario = :tipoUsuario")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    
    @Basic(optional = true)
    @Column(name = "nome_usuario")
    public String nomeUsuario;
    
    @Basic(optional = true)
    @Column(name = "senha_usuario")
    private String senhaUsuario;
    
    @Basic(optional = true)
    @Column(name = "tipo_usuario")
    private String tipoUsuario;
    
    @OneToMany(mappedBy = "id_usuario")
    private List<Conserto> consertoList;
    
    
    public Usuario() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Conserto> getConsertos() {
        return consertoList;
    }

    public void setConsertos(List<Conserto> consertos) {
        this.consertoList = consertoList;
    }

  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idUsuario);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.idUsuario, other.idUsuario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomeUsuario;
    }
    
   
    
    
}