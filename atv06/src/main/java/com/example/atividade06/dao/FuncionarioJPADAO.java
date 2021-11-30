package com.example.atividade06.dao;


import com.example.atividade06.model.Funcionario;
import com.example.atividade06.util.JPAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FuncionarioJPADAO implements FuncionarioDAO {
    @Autowired
    EntityManager em;

    @Override
    public void insertion(Funcionario funcionario){
        em = JPAUtil.getEntityManager();
        try {
            JPAUtil.beginTransaction();
            em.persist(funcionario);
            JPAUtil.commit();
        } catch (Exception e){
            JPAUtil.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Funcionario> buscar() throws SQLException {
        em = JPAUtil.getEntityManager();
        List<Funcionario> funcionarios = em.createQuery("select f from Funcionario as f", Funcionario.class).getResultList();
        return funcionarios;
    }

    @Override
    public void delete(int id) throws SQLException {
        em = JPAUtil.getEntityManager();
        Funcionario funcionario = getFuncionario(id);
        JPAUtil.beginTransaction();
        em.remove(funcionario);
        JPAUtil.commit();
    }

    @Override
    public void update(Funcionario funcionario) throws SQLException {
        em = JPAUtil.getEntityManager();
        JPAUtil.beginTransaction();
        em.merge(funcionario);
        JPAUtil.commit();
    }

    @Override
    public Funcionario getFuncionario(int id) {
        em = JPAUtil.getEntityManager();
        return em.find(Funcionario.class, id);
    }
}
