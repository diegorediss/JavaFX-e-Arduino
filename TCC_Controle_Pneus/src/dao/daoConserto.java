
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import model.Conserto;

/**
 *
 * @author marcelosiedler
 */
public class daoConserto {
    EntityManager em;
    daoConexao conexao = new daoConexao();
    
    public daoConserto() throws Exception {
        EntityManagerFactory emf;
        emf = conexao.getConexao();
        em = emf.createEntityManager();
    }
    
    public void incluir(Conserto obj) throws Exception {
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

    public List<Conserto> listar() throws Exception {
        return em.createNamedQuery("conserto.findAll").getResultList();
    }
    
    public List<Conserto> listar(String idConserto) throws Exception {
        //passar o parâmetro pra query
         TypedQuery<Conserto> query = 
                 em.createNamedQuery("conserto.findByIdConserto", Conserto.class);
         
         //Seto o parâmetro
         query.setParameter("id_conserto", '%' + idConserto + '%');
         //retorno minha lista
         return query.getResultList();
    }
    
    public void alterar(Conserto obj) throws Exception {
        
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
    
    public void excluir(Conserto obj) throws Exception {
        
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
    
    public Conserto buscarPorChavePrimaria(String chave)
    {
        return em.find(Conserto.class, chave);
    }

    public void fechaEmf() {
        conexao.closeConexao();
    }
    

}