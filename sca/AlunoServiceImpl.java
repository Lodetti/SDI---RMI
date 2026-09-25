package sca;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AlunoServiceImpl extends UnicastRemoteObject implements AlunoService {
    private final Map<String, Aluno> alunoDatabase = new ConcurrentHashMap<>();

    public AlunoServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void cadastrarAluno(Aluno aluno) throws RemoteException {
        alunoDatabase.put(aluno.getMatricula(), aluno);
    }

    @Override
    public Aluno consultarAluno(String matricula) throws RemoteException {
        return alunoDatabase.get(matricula);
    }

    @Override
    public List<Aluno> listarAlunos() throws RemoteException {
        return new ArrayList<>(alunoDatabase.values());
    }

    @Override
    public synchronized void removerAluno(String matricula) throws RemoteException {
        alunoDatabase.remove(matricula);
    }
}