import java.io.*;
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
        return new File(path).list();
    }

    @Override
    public int mkdir(String path) throws RemoteException {
        return Boolean.hashCode(new File(path).mkdir());
    }

    @Override
    public int create(String path) throws RemoteException {
        try {
            new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int unlink(String path) throws RemoteException {
        return Boolean.hashCode(new File(path).delete());
    }

    @Override
    public int write(byte[] data, String path) throws RemoteException {
        try {
            OutputStream outputStream = new FileOutputStream(path);
            for (int x = 0; x < data.length ; x++) {
                outputStream.write(data[x]);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public byte[] read(String path) throws RemoteException {
        return new byte[0];
    }
}
