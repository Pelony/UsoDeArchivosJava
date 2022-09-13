import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        int opcion;
        Scanner sc=new Scanner(System.in);
        Archivo arc = new Archivo();
        boolean salir=false;
        while (!salir) {

            try {
                System.out.println("**********************Menu***********************************");
                System.out.println(" 1. Altas");
                System.out.println(" 2. Modificar");
                System.out.println(" 3. Mostrar");
                System.out.println(" 4. Salir");
                System.out.println("Teclea la opcion que deseas usar");
                opcion=sc.nextInt();
                switch (opcion){
                    case 1:arc.Altas();break;
                    case 2:arc.Modificar();break;
                    case 3:arc.Mostrar();break;
                    case 4:salir=true; break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sc.next();
            }
        }
    }
}