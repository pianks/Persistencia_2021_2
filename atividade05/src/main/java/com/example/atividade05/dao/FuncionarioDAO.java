package com.example.atividade05.dao;

import com.example.atividade05.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public interface FuncionarioDAO {

    public void insertion(Funcionario funcionario) throws SQLException;
    public List<Funcionario> buscar() throws SQLException;
    public void delete(int id) throws SQLException;
    public void update(Funcionario funcionario) throws SQLException;

}
