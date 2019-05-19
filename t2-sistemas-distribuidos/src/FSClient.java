import java.io.File;
import java.rmi.Naming;
import java.util.Scanner;

public class FSClient {

    public static void main(String[] args) {
        if	(args.length != 1)  {
            System.out.println("Uso: java FSClient <maquina>");
            System.exit(1);
        }
        new FSClient(args[0]);
    }

    FSInterface fs = null;

    public FSClient(String host) {
        initCommandLine(host);
    }

    private void initCommandLine(String host) {
        try {
            fs = (FSInterface) Naming.lookup("//" + host + "/FSImpl");
            while (true) {
                System.out.println("ls <path> | mkdir <path> | create <path> | unlink <path> | write <path> | read <path>");
                System.out.print("> ");
                Scanner sc = new Scanner(System.in);
                while (sc.hasNext()) {
                    String input = sc.nextLine();
                    String[] inputData;
                    inputData = input.split(" ");
                    if (inputData.length < 1) {
                        System.out.println("Comando inválido!");
                    }
                    String command = inputData[0];
                    String path = new File(".").getCanonicalPath() + "\\root";
                    String parameter = "";
                    if (inputData.length > 1) {
                        parameter = inputData[1];
                    }
                    switch (command) {
                        case "ls":
                            listFiles(path);
                            break;
                        case "mkdir":
                            createFolder(path, parameter);
                            break;
                        case "create":
                            createFile(path, parameter);
                            break;
                        case "unlink":
                            deleteFile(path, parameter);
                            break;
                        case "write":
                            writeFile(path, parameter);
                            break;
                        case "read":
                            readFile(path, parameter);
                            break;
                        case "exit":
                            System.out.println("bye bye");
                            System.exit(1);
                            break;
                        default:
                            System.out.println("Comando inválido!");
                    }
                    System.out.print("> ");
                }
                sc.close();
            }
        } catch (Exception e) {
            System.out.println("Falha de conexão");
            e.printStackTrace();
        }
    }


    private void readFile(String path, String file) {
        try {
            byte[] fileContent = fs.read(path + "\\" + file);
            for (int i = 0; i < fileContent.length; i++) {
                System.out.print((char)fileContent[i]);
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("falha na leitura do arquivo");
            e.printStackTrace();
        }
    }

    private void writeFile(String path, String parameter) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Insira o texto");
            String text = sc.nextLine();
            if (fs.write(text.getBytes(), path + "\\" + parameter) > 0) {
                System.out.println("arquivo salvo");
            } else {
                System.out.println("falha ao salvar arquivo");
            }
        } catch (Exception e) {
            System.out.println("falha ao salvar arquivo");
            e.printStackTrace();
        }
    }

    private void createFile(String path, String file) {
        try {
            if (fs.create(path + "\\" + file) > 0) {
                System.out.println("Arquivo criado");
            } else {
                System.out.println("Falha ao criar arquivo");
            }
        } catch (Exception e) {
            System.out.println("Falha ao criar arquivo");
            e.printStackTrace();
        }
    }

    private void deleteFile(String path, String file) {
        try {
            if (fs.unlink(path + "\\" + file) > 0) {
                System.out.println("Arquivo deletado");
            } else {
                System.out.println("Falha ao deletar arquivo");
            }
        } catch (Exception e) {
            System.out.println("Falha ao deletar arquivo");
            e.printStackTrace();
        }
    }

    private void listFiles(String path) {
        try {
            String[] contents;
            contents = fs.ls(path);
            if (contents.length == 0) {
                System.out.println("Diretório vazio");
                return;
            }
            for(String content: contents) {
                System.out.println(content);
            }
        } catch (Exception e) {
            System.out.println("Falha ao listar arquivos");
            e.printStackTrace();
        }
    }

    private void createFolder(String path, String folder) {
        try {
            if (fs.mkdir(path + "\\" + folder) > 0) {
                System.out.println("Diretório criado");
            } else {
                System.out.println("Falha ao criar diretório");
            }
        } catch (Exception e) {
            System.out.println("Falha ao criar Diretório");
            e.printStackTrace();
        }
    }
}
