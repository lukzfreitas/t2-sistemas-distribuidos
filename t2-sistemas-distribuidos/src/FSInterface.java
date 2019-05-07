import java.rmi.RemoteException;

public interface FSInterface {
    public String[] ls(String path) throws RemoteException;
    public int mkdir(String path) throws RemoteException;
    public int create(String path) throws RemoteException;
    public int unlink(String path) throws RemoteException;
    public int write(byte[] data, String path) throws RemoteException;
    public byte[] read(String path) throws RemoteException;
}
