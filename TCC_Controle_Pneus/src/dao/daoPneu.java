
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import model.Pneu;

/**
 *
 * @author marcelosiedler
 */
public class daoPneu {
    EntityManager em;
    daoConexao conexao = new daoConexao();
    
    public daoPneu() throws Exception {
        EntityManagerFactory emf;
        emf = conexao.getConexao();
        em = emf.createEntityManager();
    }
    
    public void incluir(Pneu obj) throws Exception {
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

    public List<Pneu> listar() throws Exception {
        return em.createNamedQuery("pneu.findAll").getResultList();
    }
    
    public List<Pneu> listar(String idPneu) throws Exception {
        //passar o parâmetro pra query
         TypedQuery<Pneu> query = 
                 em.createNamedQuery("pneu.findByIdPneu", Pneu.class);
         
         //Seto o parâmetro
         query.setParameter("id_pneu", '%' + idPneu + '%');
         //retorno minha lista
         return query.getResultList();
    }
    
    public void alterar(Pneu obj) throws Exception {
        
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
    
    public void excluir(Pneu obj) throws Exception {
        
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
    
    public Pneu buscarPorChavePrimaria(String chave)
    {
        return em.find(Pneu.class, chave);
    }

    public void fechaEmf() {
        conexao.closeConexao();
    }
    

}