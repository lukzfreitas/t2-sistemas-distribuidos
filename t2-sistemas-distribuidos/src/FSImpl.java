import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class FSImpl extends UnicastRemoteObject implements FSInterface{

    private static final long serialVersionUID = -513804057617910473L;

    protected FSImpl() throws RemoteException {
    }

    protected FSImpl(int port) throws RemoteException {
        super(port);
    }

    protected FSImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf) throws RemoteException {
        super(port, csf, ssf);
    }

    @Override
    public String[] ls(String path) throws RemoteException {
        return new String[0];
    }

    @Override
    public int mkdir(String path) throws RemoteException {
        File d = new File(path);
        d.mkdirs();
        return 1;
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
