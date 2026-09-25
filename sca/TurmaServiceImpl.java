import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TurmaServiceImpl extends UnicastRemoteObject implements TurmaService {
    private final Map<String, Turma> turmaDatabase = new ConcurrentHashMap<>();
    private final AlunoService alunoService;

    public TurmaServiceImpl(AlunoService alunoService) throws RemoteException {
        super();
        this.alunoService = alunoService;
    }

    @Override
    public synchronized void cadastrarTurma(Turma turma) throws RemoteException {
        turmaDatabase.put(turma.getCodigo(), turma);
    }

    @Override
    public Turma consultarTurma(String codigo) throws RemoteException {
        return turmaDatabase.get(codigo);
    }

    @Override 
    public List<Turma> listarTurmas() throws RemoteException {
        return new ArrayList<>(turmaDatabase.values());
    }

    @Override 
    public synchronized void removerTurma(String codigo) throws RemoteException {
        turmaDatabase.remove(codigo);
    }

    @Override 
    public synchronized void adicionarAluno(String codigoTurma, String matricula) throws RemoteException {
        Turma turma = turmaDatabase.get(codigoTurma);
        if (turma != null) {
            Aluno aluno = alunoService.consultarAluno(matricula);
            if (aluno != null) {
                if(!turma.getAlunos().contains(aluno)) {
                    turma.adicionarAluno(aluno);
                } else {
                    throw new RemoteException("Aluno com matrícula " + matricula + " já está na turma " + codigoTurma + ".");
                }
            } else {
                throw new RemoteException("Aluno com matrícula " + matricula + " não encontrado.");
            }
        } else {
            throw new RemoteException("Turma com código " + codigoTurma + " não encontrada.");
        }
    }

    @Override 
    public synchronized void removerAluno(String codigoTurma, String matricula) throws RemoteException {
        Turma turma = turmaDatabase.get(codigoTurma);
        if (turma != null) {
            if (!turma.removerAlunoPorMatricula(matricula)) {
                throw new RemoteException("Aluno com matricula " + matricula + " não encontrado na turma " + codigoTurma + ".");
            }
        } else {
            throw new RemoteException("Turma com código " + codigoTurma + " não encontrada.");
        }
    }

    @Override 
    public List<Aluno> listarAlunos(String codigoTurma) throws RemoteException {
        Turma turma = turmaDatabase.get(codigoTurma);
        if (turma != null) {
            return turma.getAlunos();
        } else {
            throw new RemoteException("Turma com código " + codigoTurma + " não encontrada.");
        }
    }
}
