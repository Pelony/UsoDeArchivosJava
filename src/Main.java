import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        /*while (!salir) {

            System.out.println("1. Opcion 1");
            System.out.println("2. Opcion 2");
            System.out.println("3. Opcion 3");
            System.out.println("4. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion 1");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3");
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }*/
        int opcion;
        Scanner sc=new Scanner(System.in);
        Archivo arc = new Archivo();
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
            case 4:System.exit(1); break;
        }
    }
}