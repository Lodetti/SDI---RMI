import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SecretariaServiceImpl extends UnicastRemoteObject implements SecretariaService {
    private final AlunoService alunoService;
    private final TurmaService turmaService;

    public SecretariaServiceImpl(AlunoService alunoService, TurmaService turmaService) throws RemoteException {
        super();
        this.alunoService = alunoService;
        this.turmaService = turmaService;
    }

    // Talvez n precise de todos esses ifs aqui, mas já fiz né ent fds

    @Override
    public void matricularAluno(String matricula, String codigoTurma) throws RemoteException {
        Aluno aluno = alunoService.consultarAluno(matricula);
        if (aluno == null) {
            throw new RemoteException("Aluno com matrícula " + matricula + " não encontrado.");
        }

        Turma turma = turmaService.consultarTurma(codigoTurma);
        if (turma == null) {
            throw new RemoteException("Turma com código " + codigoTurma + " não encontrada.");
        }

        turmaService.adicionarAluno(codigoTurma, matricula);
    }

    @Override
    public void cancelarMatricula(String matricula, String codigoTurma) throws RemoteException {
        Aluno aluno = alunoService.consultarAluno(matricula);
        if (aluno == null) {
            throw new RemoteException("Aluno com matrícula " + matricula + " não encontrado.");
        }

        Turma turma = turmaService.consultarTurma(codigoTurma);
        if (turma == null) {
            throw new RemoteException("Turma com código " + codigoTurma + " não encontrada.");
        }

        turmaService.removerAluno(codigoTurma, matricula);
    }
}
