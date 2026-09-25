package sca;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WSSCA {

    // Métodos delegados ao AlunoService
    @WebMethod
    Aluno buscarAlunoPorMatricula(String matricula);

    // @WebMethod
    // List<Aluno> listarAlunos();

    // // Métodos delegados ao TurmaService
    // @WebMethod
    // Turma buscarTurmaPorCodigo(String codigo);

    // @WebMethod
    // List<Turma> listarTurmas();

    // // Métodos delegados ao SecretariaService
    // @WebMethod
    // boolean matricularAluno(int matriculaAluno, String codigoTurma);
}