
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import model.Custo;

/**
 *
 * @author marcelosiedler
 */
public class daoCusto {
    EntityManager em;
    daoConexao conexao = new daoConexao();
    
    public daoCusto() throws Exception {
        EntityManagerFactory emf;
        emf = conexao.getConexao();
        em = emf.createEntityManager();
    }
    
    public void incluir(Custo obj) throws Exception {
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

    public List<Custo> listar() throws Exception {
        return em.createNamedQuery("custo.findAll").getResultList();
    }
    
    public List<Custo> listar(String idCusto) throws Exception {
        //passar o parâmetro pra query
         TypedQuery<Custo> query = 
                 em.createNamedQuery("custo.findByIdCusto", Custo.class);
         
         //Seto o parâmetro
         query.setParameter("id_custo", '%' + idCusto + '%');
         //retorno minha lista
         return query.getResultList();
    }
    
    public void alterar(Custo obj) throws Exception {
        
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
    
    public void excluir(Custo obj) throws Exception {
        
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
    
    public Custo buscarPorChavePrimaria(String chave)
    {
        return em.find(Custo.class, chave);
    }

    public void fechaEmf() {
        conexao.closeConexao();
    }
    

}