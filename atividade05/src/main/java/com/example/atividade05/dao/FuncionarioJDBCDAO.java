package com.example.atividade05.dao;

import com.example.atividade05.model.Funcionario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class FuncionarioJDBCDAO implements FuncionarioDAO{

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void insertion(Funcionario funcionario) throws SQLException {
        String inserir_sql = "Insert into funcionarios (cpf, nome, email, matricula, telefone) " +
                "values (:cpf, :nome, :email, :matricula, :telefone)";
        MapSqlParameterSource parametros = new MapSqlParameterSource()
                .addValue("cpf", funcionario.getCpf())
                .addValue("nome", funcionario.getNome())
                .addValue("email", funcionario.getEmail())
                .addValue("matricula", funcionario.getMatricula())
                .addValue("telefone", funcionario.getTelefone());

        jdbcTemplate.update(inserir_sql, parametros);
    }

    @Override
    public List<Funcionario> buscar() throws SQLException {
        String sql = "select * from funcionarios";
        return jdbcTemplate.query(sql, (rs, rowNum) -> map(rs));
    }

    private Funcionario map(ResultSet rs) throws SQLException {
        Funcionario cl = new Funcionario();
        cl.setCpf(rs.getString("cpf"));
        cl.setMatricula(rs.getString("matricula"));
        cl.setNome(rs.getString("nome"));
        cl.setEmail(rs.getString("email"));
        cl.setTelefone(rs.getString("telefone"));
        cl.setId(rs.getInt("id"));

        return cl;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "delete from clientes where id = :?*";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("?*",  id);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public void update(Funcionario funcionario) throws SQLException {
        String upar = "Update funcionarios set cpf = :cpf, nome = :nome, email = :email," +
                "matricula = :matricula, telefone = :telefone where id = :id";
        MapSqlParameterSource parametros = new MapSqlParameterSource()
                .addValue("cpf", funcionario.getCpf())
                .addValue("nome", funcionario.getNome())
                .addValue("email", funcionario.getEmail())
                .addValue("matricula", funcionario.getMatricula())
                .addValue("telefone", funcionario.getTelefone())
                .addValue("id", funcionario.getId());

        jdbcTemplate.update(upar, parametros);

    }
}

