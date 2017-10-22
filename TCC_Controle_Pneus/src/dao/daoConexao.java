package dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
    
public  class daoConexao {
    
    public EntityManagerFactory emf;
    
    public daoConexao() {

    }

    public EntityManagerFactory getConexao() throws Exception {
        if ((emf == null) || (!emf.isOpen())) {
             emf = Persistence.createEntityManagerFactory("TCC_Controle_PneusPU");
        }
        return emf;
    }  
    
    
    
    public void closeConexao(){
        if (emf.isOpen()){
           emf.close();
        }
    }

    Object getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}