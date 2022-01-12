package com.example.Trabalho_02.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import com.example.Trabalho_02.dao.AlunoDAO;
import com.example.Trabalho_02.dao.DisciplinaDAO;
import com.example.Trabalho_02.dao.RelacaoDAO;
import com.example.Trabalho_02.entity.Aluno;
import com.example.Trabalho_02.entity.Associacao;
import com.example.Trabalho_02.entity.Disciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example.Trabalho_02")
@EntityScan("com.example.Trabalho_02.entity")
@EnableJpaRepositories("com.example.Trabalho_02.dao")

public class CrudAluno implements CommandLineRunner {
    @Autowired
    private AlunoDAO baseAlunos;
    @Autowired
    private DisciplinaDAO disciplina;
    @Autowired
    private RelacaoDAO relacaoDAO;

    public static void main(String[] args) {
        //SpringApplication.run(Principal.class, args);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(CrudAluno.class);
        builder.headless(false).run(args);
    }

    public void associarAlunoDisciplina( ){
        int id_aluno = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do Aluno que deseja associar"));
        int id_disciplina = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da Disciplina que deseja associar"));
        Aluno a;
        Disciplina d;
        a = baseAlunos.findFistByid(id_aluno);
        d = disciplina.findFistByid(id_disciplina);

        Associacao ass = new Associacao();
        ass.setAluno(a);
        ass.setDisciplina(d);

        relacaoDAO.save(ass);
    }

    public void obterCliente(Aluno cl) throws ParseException {
        String nome = JOptionPane.showInputDialog("Nome", cl.getNome());
        String cpf = JOptionPane.showInputDialog("CPF", cl.getCpf());
        String email = JOptionPane.showInputDialog("email", cl.getEmail());
        String matricula = JOptionPane.showInputDialog("Matrícula", cl.getMatricula());
        String data = JOptionPane.showInputDialog("Digite a data de nascimento do aluno", cl.getDatanascimento());
        java.util.Date dd = new SimpleDateFormat("dd/MM/yyyy").parse(data);

        cl.setDatanascimento(Alterar_data(dd));
        cl.setMatricula(matricula);
        cl.setNome(nome);
        cl.setCpf(cpf);
        cl.setEmail(email);
    }

    public void Procurar_codigo(){
        String codigo = JOptionPane.showInputDialog("Codigo da Disciplina");

        Disciplina d = disciplina.findFistByCodigo(codigo);
        if(d!= null){

            List<Associacao> relacoes = relacaoDAO.findAll();
            List<Aluno> integrantes = new ArrayList<>();
            for (Associacao i: relacoes ){
                if(i.getDisciplina().getId() == d.getId()){
                    Aluno a = baseAlunos.findFistByid(i.getAluno().getId());
                    integrantes.add(a);
                }
            }
            ListaAlunos(integrantes);
        }
    }

    public  java.sql.Date Alterar_data(java.util.Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(date);
        return java.sql.Date.valueOf(formattedDate);

    }

    public static void ListaAlunos(List<Aluno> alunos) {
        StringBuilder listagem = new StringBuilder();
        for(Aluno cl : alunos) {
            listagem.append(cl).append("\n");
        }
        JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum aluno encontrado" : listagem);
    }

    public static void ListaAlunos(Aluno cl) {
        JOptionPane.showMessageDialog(null, cl == null ? "Nenhum aluno encontrado" : cl);
    }

    @Override
    public void run(String... args) throws Exception {

        String menu = "Escolha uma opção:\n" +
                "1 - Inserir aluno\n" +
                "2 - Atualizar por id\n" +
                "3 - Remover por id\n" +
                "4 - Exibir por id\n" +
                "5 - Exibir todos\n" +
                "6 - Exibir nome e email por matrícula\n"+
                "7 - Buscar aluno a partir de uma data\n"+
                "8 - Relacionar disciplina e aluno\n"+
                "9 - Listar alunos da disciplina por código\n";
        char opcao;
        do {
            Aluno cl;
            int id;
            opcao = JOptionPane.showInputDialog(menu).charAt(0);
            switch (opcao) {
                case '1':  // Inserir alunos
                    cl = new Aluno();
                    obterCliente(cl);
                    baseAlunos.save(cl);
                    break;
                case '2':     // Atualizar alunos por ID
                    id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do aluno a ser alterado"));
                    cl = baseAlunos.findFistByid(id);
                    obterCliente(cl);
                    baseAlunos.save(cl);
                    break;
                case '3':     // Remover alunos por ID
                    id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do aluno a ser alterado"));
                    cl = baseAlunos.findFistByid(id);
                    if (cl != null) {
                        baseAlunos.deleteById(cl.getId());
                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o aluno não encontrado.");
                    }
                    break;
                case '4':     // Exibir alunos por ID
                    id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
                    cl = baseAlunos.findById(id).orElse(null);
                    ListaAlunos(cl);
                    break;
                case '5':     // Exibir todos os alunos
                    ListaAlunos(baseAlunos.findAll());
                    break;
                case '6': // Buscar aluno por matrícula
                    String matricula = JOptionPane.showInputDialog("Digite a Matricula do aluno a ser buscada");
                    cl = baseAlunos.findFistByMatricula(matricula);
                    JOptionPane.showMessageDialog(null,"Nome: "+ cl.getNome()+
                            "\nEmail :" + cl.getEmail());
                    break;
                case '7': // Buscar aluno por data de nascimento
                    String data = JOptionPane.showInputDialog("Digite a data de nascimento do aluno a ser buscada");
                    Date dd = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                    ListaAlunos(baseAlunos.findAlunosByDatanascimento(Alterar_data(dd)));
                    break;
                case '8':  // Associar alunos as disciplinas
                    associarAlunoDisciplina();
                    break;
                case '9':  // Exibir alunos cadastrados na disciplina por codigo
                    Procurar_codigo();
                case '0':
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção Inválida");
                    break;
            }
        } while(opcao != '0');

        // finalmente deu certo

    }
}
