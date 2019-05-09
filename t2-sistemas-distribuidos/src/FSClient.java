import java.rmi.Naming;
import java.util.Scanner;

public class FSClient {

    public static void main(String[] args) {

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Insira uma opção:");
            while(sc.hasNext()){
                String opcao = sc.nextLine();
                switch (opcao) {
                    case "ls":
                        listFiles();
                    case "mkdir":
                        createFolder();
                    case "unlink":
                        deleteFile();
                    case "write":
                        writeFile();
                    case "read":
                        readFile();
                }
            }
            sc.close();
        }

    }

    private static void readFile() {

    }

    private static void writeFile() {
    }

    private static void deleteFile() {
    }

    private static void listFiles() {
    }

    private static void createFolder() {
        try {
            FSInterface fs = (FSInterface) Naming.lookup("//localhost/FSImpl");
            Scanner sc = new Scanner(System.in);
            System.out.println("Insira o nome da pasta");
            String nameFolder = sc.nextLine();
            fs.create(nameFolder);
        } catch (Exception e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }
}
