package com.example.atividade06.dao;

import com.example.atividade06.model.Funcionario;

import java.sql.SQLException;
import java.util.List;

public interface FuncionarioDAO {

     void insertion(Funcionario funcionario) throws SQLException;
     List<Funcionario> buscar() throws SQLException;
     void delete(int id) throws SQLException;
     void update(Funcionario funcionario) throws SQLException;
     Funcionario getFuncionario(int id);

}
