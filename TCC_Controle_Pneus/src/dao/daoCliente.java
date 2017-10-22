
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import model.Cliente;

/**
 *
 * @author marcelosiedler
 */
public class daoCliente {
    EntityManager em;
    daoConexao conexao = new daoConexao();
    
    public daoCliente() throws Exception {
        EntityManagerFactory emf;
        emf = conexao.getConexao();
        em = emf.createEntityManager();
    }
    
    public void incluir(Cliente obj) throws Exception {
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

    public List<Cliente> listar() throws Exception {
        return em.createNamedQuery("cliente.findAll").getResultList();
    }
    
    public List<Cliente> listar(String idCliente) throws Exception {
        //passar o parâmetro pra query
         TypedQuery<Cliente> query = 
                 em.createNamedQuery("cliente.findByIdCliente", Cliente.class);
         
         //Seto o parâmetro
         query.setParameter("id_cliente", '%' + idCliente + '%');
         //retorno minha lista
         return query.getResultList();
    }
    
    public void alterar(Cliente obj) throws Exception {
        
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
    
    public void excluir(Cliente obj) throws Exception {
        
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
    
    public Cliente buscarPorChavePrimaria(String chave)
    {
        return em.find(Cliente.class, chave);
    }

    public void fechaEmf() {
        conexao.closeConexao();
    }
    

}