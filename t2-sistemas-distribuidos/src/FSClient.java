import java.io.File;
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
                System.out.println("ls <path> | mkdir <path> | create <path> | unlink <path> | write <path> | read <path>");
                System.out.print("> ");
                Scanner sc = new Scanner(System.in);
                while (sc.hasNext()) {
                    String input = sc.nextLine();
                    String[] inputData;
                    inputData = input.split(" ");
                    if (inputData.length > 2 || inputData.length < 1) {
                        System.out.println("Command invalid!");
                    }
                    String command = inputData[0];
                    String path = ".";
                    if (inputData.length > 1) {
                        path = inputData[1];
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
                            writeFile(path);
                            break;
                        case "read":
                            readFile(path);
                            break;
                        case "exit":
                            System.out.println("bye bye");
                            System.exit(1);
                            break;
                        default:
                            System.out.println("Command invalid!");
                    }
                    System.out.print("> ");
                }
                sc.close();
            }
        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }


    private void readFile(String path) {
        try {
            byte[] fileContent = fs.read(path);
            for (int i = 0; i < fileContent.length; i++) {
                System.out.print((char)fileContent[i]);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("read file failed");
            e.printStackTrace();
        }
    }

    private void writeFile(String path) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Insert text");
            String text = sc.nextLine();
            if (fs.write(text.getBytes(), path) > 0) {
                System.out.println("file wrote");
            } else {
                System.out.println("write file failed");
            }
        } catch (Exception e) {
            System.out.println("write file failed");
            e.printStackTrace();
        }
    }

    private void createFile(String path) {
        try {
            if (fs.create(path) > 0) {
                System.out.println("create file");
            } else {
                System.out.println("create file failed");
            }
        } catch (Exception e) {
            System.out.println("create file failed");
            e.printStackTrace();
        }
    }

    private void deleteFile(String path) {
        try {
            if (fs.unlink(path) > 0) {
                System.out.println("deleted");
            } else {
                System.out.println("delete file failed");
            }
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
            if (fs.mkdir(path) > 0) {
                System.out.println("create folder!!!");
            } else {
                System.out.println("create folder failed");
            }
        } catch (Exception e) {
            System.out.println("create folder failed");
            e.printStackTrace();
        }
    }
}
