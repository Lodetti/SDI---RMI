import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            // Locate registry at localhost (or IP address of host)
            Registry registry = LocateRegistry.getRegistry("localhost", 6600);

            // Retrieve stub references from registry
            AlunoService alunoService = (AlunoService) registry.lookup("AlunoService");
            TurmaService turmaService = (TurmaService) registry.lookup("TurmaService");

            // Invoke remote methods
            Aluno novoAluno = new Aluno("João Silva", "joao@email.com", "2026001");
            alunoService.cadastrarAluno(novoAluno);

            Aluno consultado = alunoService.consultarAluno("2026001");
            System.out.println("Retrieved: " + consultado);

            Turma novaTurma = new Turma("SDI");
            turmaService.cadastrarTurma(novaTurma);

            turmaService.adicionarAluno("SDI", "2026001");
            List<Aluno> alunosEmSdi = turmaService.listarAlunos("SDI");
            System.out.println("Alunos na turma de SDI: " + alunosEmSdi);

            turmaService.removerAluno("SDI", "2026001");
            alunosEmSdi = turmaService.listarAlunos("SDI");
            System.out.println("Alunos em SDI após remoção: " + alunosEmSdi);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}