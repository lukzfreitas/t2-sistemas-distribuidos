import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FSImpl extends UnicastRemoteObject implements FSInterface {

    @Override
    public String[] ls(String path) throws RemoteException {
        return new String[0];
    }

    @Override
    public int mkdir(String path) throws RemoteException {
        return 0;
    }

    @Override
    public int create(String path) throws RemoteException {
        return 0;
    }

    @Override
    public int unlink(String path) throws RemoteException {
        return 0;
    }

    @Override
    public int write(byte[] data, String path) throws RemoteException {
        return 0;
    }

    @Override
    public byte[] read(String path) throws RemoteException {
        return new byte[0];
    }
}
