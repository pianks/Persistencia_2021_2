package com.example.atividade05;

import com.example.atividade05.dao.FuncionarioDAO;
import com.example.atividade05.dao.FuncionarioJDBCDAO;
import com.example.atividade05.model.Funcionario;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner{
	@Autowired
	FuncionarioDAO funcionarioDAO;

	public void mostrarFuncionario(List<Funcionario> funcionarios) throws SQLException {
		List<String> strings = new ArrayList<>();
		for(Funcionario f: funcionarios){
			String aux =
						"\nId: "+ f.getId()+
						"\nNome: "+ f.getNome()+
						"\nMatrícula: "+ f.getMatricula()+
						"\nCpf: " + f.getCpf()+
						"\nEmail: "+ f.getEmail() +
						"\nTelefone: "+f.getTelefone()+"\n";

			strings.add(aux);
		}
		JOptionPane.showMessageDialog(null, strings);
	}

	public void preencherDados(Funcionario funcionario){
		String nome = JOptionPane.showInputDialog("nome", funcionario.getNome());
		funcionario.setNome(nome);
		String matricula = JOptionPane.showInputDialog("matricula", funcionario.getMatricula());
		funcionario.setMatricula(matricula);
		String cpf = JOptionPane.showInputDialog("cpf", funcionario.getCpf());
		funcionario.setCpf(cpf);
		String email = JOptionPane.showInputDialog("email", funcionario.getEmail());
		funcionario.setEmail(email);
		String telefone = JOptionPane.showInputDialog("telefone", funcionario.getTelefone());
		funcionario.setTelefone(telefone);
	}



	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		builder.headless(false).run(args);
	}

	public void run(String... args) throws Exception{
		String tela = "Digite a opção que desejar:\n"
				+"1: Cadastrar funcionário\n"
				+"2: Atualizar funcionário\n"
				+"3: Deletar funcionário\n"
				+"4: Visualizar funcionários\n"
				+"5: Sair";
		char menu = '0';
		while(menu != '5') {
			menu = JOptionPane.showInputDialog(tela).charAt(0);

			switch (menu){
				case '1'->{
					Funcionario funcionario = new Funcionario();
					preencherDados(funcionario);
					funcionarioDAO.insertion(funcionario);
				}

				case '2' -> {
					Funcionario funcionario1 = new Funcionario();
					int id = Integer.parseInt(JOptionPane.showInputDialog("id"));
					funcionario1.setId(id);
					preencherDados(funcionario1);
					funcionarioDAO.update(funcionario1);
				}

				case '3' -> {
					int id = Integer.parseInt(JOptionPane.showInputDialog("id"));
					funcionarioDAO.delete(id);
				}
				case '4' -> {
					mostrarFuncionario(funcionarioDAO.buscar());

				}

			}
		}
	}

}
