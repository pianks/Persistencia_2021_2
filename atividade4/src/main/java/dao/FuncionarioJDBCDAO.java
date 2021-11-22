package dao;

import model.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioJDBCDAO implements FuncionarioDAO {
    @Override
    public void insertion(Funcionario funcionario) throws SQLException {
        Connection connect = ConnectionFactory.getConnection();
        String inserir = "insert into funcionarios (cpf, matricula, nome, email, telefone) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = connect.prepareStatement(inserir);
        preparedStatement.setString(1, funcionario.getCpf());
        preparedStatement.setString(2, funcionario.getMatricula());
        preparedStatement.setString(3, funcionario.getNome());
        preparedStatement.setString(4, funcionario.getEmail());
        preparedStatement.setString(5, funcionario.getTelefone());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connect.close();
    }

    public List<Funcionario> buscar() throws SQLException{
        List<Funcionario> funcionarios = new ArrayList<>();
        Connection connect = ConnectionFactory.getConnection();
        String buscar = "select * from funcionarios";
        PreparedStatement preparedStatement = connect.prepareStatement(buscar);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Funcionario funcionario = map(resultSet);
            funcionarios.add(funcionario);
        }
        preparedStatement.close();

        return funcionarios;
    }

    public void delete(int id) throws SQLException{
        Connection connect = ConnectionFactory.getConnection();
        String deletar = "delete from funcionarios where id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(deletar);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connect.close();
    }

    public void update(Funcionario funcionario) throws SQLException{
        Connection connect = ConnectionFactory.getConnection();
        String upar = "update funcionarios set cpf = ?, matricula = ?, nome = ?, email = ?, telefone = ? where id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(upar);
        preparedStatement.setString(1, funcionario.getCpf());
        preparedStatement.setString(2, funcionario.getMatricula());
        preparedStatement.setString(3, funcionario.getNome());
        preparedStatement.setString(4, funcionario.getEmail());
        preparedStatement.setString(5, funcionario.getTelefone());
        preparedStatement.setInt(6, funcionario.getId());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connect.close();

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

    public Funcionario selecionarFuncionario(int id) throws SQLException{
        Connection connect = ConnectionFactory.getConnection();
        String buscar = "select * from funcionarios where id = ?";
        PreparedStatement preparedStatement = connect.prepareStatement(buscar);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        Funcionario funcionario = map(resultSet);
        preparedStatement.close();
        connect.close();
        return funcionario;
    }
}
