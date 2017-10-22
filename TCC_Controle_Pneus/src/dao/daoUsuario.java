
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import model.Usuario;

/**
 *
 * @author marcelosiedler
 */
public class daoUsuario {
    EntityManager em;
    daoConexao conexao = new daoConexao();
    
    public daoUsuario() throws Exception {
        EntityManagerFactory emf;
        emf = conexao.getConexao();
        em = emf.createEntityManager();
    }
    
    public void incluir(Usuario obj) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
            
        }
        
    }

    public List<Usuario> listar() throws Exception {
        return em.createNamedQuery("usuario.findAll").getResultList();
    }
    
    public List<Usuario> listar(String idUsuario) throws Exception {
        //passar o parâmetro pra query
         TypedQuery<Usuario> query = 
                 em.createNamedQuery("usuario.findByIdUsuario", Usuario.class);
         
         //Seto o parâmetro
         query.setParameter("id_usuario", '%' + idUsuario + '%');
         //retorno minha lista
         return query.getResultList();
    }
    
    public void alterar(Usuario obj) throws Exception {
        
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public void excluir(Usuario obj) throws Exception {
        
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public Usuario buscarPorChavePrimaria(String chave)
    {
        return em.find(Usuario.class, chave);
    }

    public void fechaEmf() {
        conexao.closeConexao();
    }
    

}