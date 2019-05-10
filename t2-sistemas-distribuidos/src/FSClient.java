import java.rmi.Naming;
import java.util.Scanner;

public class FSClient {

    public static void main(String[] args) {
        new FSClient();
    }

    FSInterface fs = null;

    public FSClient() {
        initCommandLine();
    }

    private void initCommandLine() {
        try {
            fs = (FSInterface) Naming.lookup("//localhost/FSImpl");
            while (true) {
                Scanner sc = new Scanner(System.in);
                while (sc.hasNext()) {
                    String input = sc.nextLine();
                    String[] inputData;
                    inputData = input.split(" ");
                    if (inputData.length < 2 || inputData.length > 3) {
                        System.out.println("Command invalid!");
                    }
                    String command = inputData[0];
                    String path = inputData[1];
                    String data = "";
                    if (inputData.length > 3) {
                        data = inputData[2];
                    }
                    switch (command) {
                        case "ls":
                            listFiles(path);
                            break;
                        case "mkdir":
                            createFolder(path);
                            break;
                        case "create":
                            createFile(path);
                            break;
                        case "unlink":
                            deleteFile(path);
                            break;
                        case "write":
                            writeFile(data, path);
                            break;
                        case "read":
                            readFile(path);
                            break;
                        default:
                            System.out.println("Command invalid!");
                    }
                }
                sc.close();
            }
        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }


    private void readFile(String path) {
        //TODO: Implement read file
    }

    private void writeFile(String text, String path) {
        try {
            fs.write(text.getBytes(), path);
        } catch (Exception e) {
            System.out.println("write file failed");
            e.printStackTrace();
        }
    }

    private void createFile(String path) {
        try {
            fs.create(path);
        } catch (Exception e) {
            System.out.println("create file failed");
            e.printStackTrace();
        }
    }

    private void deleteFile(String path) {
        try {
            fs.unlink(path);
        } catch (Exception e) {
            System.out.println("delete file failed");
            e.printStackTrace();
        }
    }

    private void listFiles(String path) {
        try {
            String[] contents;
            contents = fs.ls(path);
            if (contents.length == 0) {
                System.out.println("folder empty!!!");
                return;
            }
            for(String content: contents) {
                System.out.println(content);
            }
        } catch (Exception e) {
            System.out.println("list contents failed");
            e.printStackTrace();
        }
    }

    private void createFolder(String path) {
        try {
            fs.mkdir(path);
            System.out.println("create folder!!!");
        } catch (Exception e) {
            System.out.println("create folder failed");
            e.printStackTrace();
        }
    }
}
