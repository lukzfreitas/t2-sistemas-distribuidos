import java.rmi.Naming;
import java.rmi.RemoteException;

public class FSServer {

    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099);
            System.out.println("RMI registry ready");
        } catch (RemoteException e) {
            System.out.println("RMI registry already running");
        }
        try {
            Naming.rebind("FSImpl", new FSImpl());
            System.out.println("Server running");
        } catch (Exception e) {
            System.out.println("Server failed");
        }
    }
}
