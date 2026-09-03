import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Locate registry at localhost (or IP address of host)
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Retrieve stub references from registry
            AlunoService alunoService = (AlunoService) registry.lookup("AlunoService");

            // Invoke remote methods
            Aluno novoAluno = new Aluno("João Silva", "joao@email.com", "2026001");
            alunoService.cadastrarAluno(novoAluno);

            Aluno consultado = alunoService.consultarAluno("2026001");
            System.out.println("Retrieved: " + consultado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}