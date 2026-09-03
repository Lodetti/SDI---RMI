import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            // Create RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Instantiate service implementations
            AlunoService alunoService = new AlunoServiceImpl();
            // TurmaService turmaService = new TurmaServiceImpl();
            // SecretariaService secretariaService = new SecretariaServiceImpl(alunoService, turmaService);

            // Bind objects to registry names
            registry.rebind("AlunoService", alunoService);
            // registry.rebind("TurmaService", turmaService);
            // registry.rebind("SecretariaService", secretariaService);

            System.out.println("Academic System RMI Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}