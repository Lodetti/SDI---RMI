package sca;

import java.rmi.Naming;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "sca.WSSCA")
public class WSSCAImpl implements WSSCA {

    private AlunoService alunoRmi;
    private TurmaService turmaRmi;
    private SecretariaService secretariaRmi;

    public WSSCAImpl() {
        try {
            // Conecta ao RMI Registry existente sem alterar as interfaces
            alunoRmi = (AlunoService) Naming.lookup("rmi://localhost:1099/AlunoService");
            turmaRmi = (TurmaService) Naming.lookup("rmi://localhost:1099/TurmaService");
            secretariaRmi = (SecretariaService) Naming.lookup("rmi://localhost:1099/SecretariaService");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Aluno buscarAlunoPorMatricula(String matricula) {
        try {
            return alunoRmi.consultarAluno(matricula);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // @Override
    // public List<Aluno> listarAlunos() {
    //     try {
    //         return alunoRmi.listarTodos();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    // @Override
    // public Turma buscarTurmaPorCodigo(String codigo) {
    //     try {
    //         return turmaRmi.buscarPorCodigo(codigo);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    // @Override
    // public List<Turma> listarTurmas() {
    //     try {
    //         return turmaRmi.listarTodas();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }

    // @Override
    // public boolean matricularAluno(int matriculaAluno, String codigoTurma) {
    //     try {
    //         return secretariaRmi.matricular(matriculaAluno, codigoTurma);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return false;
    //     }
    // }
}