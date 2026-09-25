import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            // Create RMI registry on port 6600
            Registry registry = LocateRegistry.createRegistry(6600);

            // Instantiate service implementations
            AlunoService alunoService = new AlunoServiceImpl();
            TurmaService turmaService = new TurmaServiceImpl(alunoService);
            SecretariaService secretariaService = new SecretariaServiceImpl(alunoService, turmaService);

            // Bind objects to registry names
            registry.rebind("AlunoService", alunoService);
            registry.rebind("TurmaService", turmaService);
            registry.rebind("SecretariaService", secretariaService);

            System.out.println("Academic System RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}