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
        System.out.println(path);
        return new File(path).mkdir() ? 1 : -1;
    }

    @Override
    public int create(String path) throws RemoteException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.close();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int unlink(String path) throws RemoteException {
        return new File(path).delete() ? 1 : -1;
    }

    @Override
    public int write(byte[] data, String path) throws RemoteException {
        try {
            OutputStream outputStream = new FileOutputStream(path);
            for (int i = 0; i < data.length ; i++) {
                outputStream.write(data[i]);
            }
            outputStream.close();
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public byte[] read(String path) throws RemoteException {
        try {
            InputStream inputStream = new FileInputStream(path);
            int size = inputStream.available();
            byte[] fileContent = new byte[size];
            for(int i = 0; i < size; i++) {
                fileContent[i] = (byte)inputStream.read();
            }
            inputStream.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
