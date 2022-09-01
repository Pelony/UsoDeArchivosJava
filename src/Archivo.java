import java.io.*;
import java.util.Scanner;

public class Archivo {
    Scanner sc=new Scanner(System.in);
    int noControl,Semestre;
    float promedio;
    String nombre;
    public void Altas(){
        System.out.println("Altas");
        System.out.println("Ingresa el Numero de control del alumno");
        noControl=sc.nextInt();
        System.out.println("Ingresa el Nombre del alumno");
        nombre=sc.next();
        System.out.println("Ingresa el semestre del alumno");
        Semestre=sc.nextInt();
        System.out.println("Ingresa el promedio del alumno");
        promedio=sc.nextFloat();
        Alumno AltaAlumno= new Alumno(nombre,noControl,Semestre,promedio);
        FileOutputStream fichero1=null;
        FileOutputStream fichero2=null;
        if(promedio<=6.9){
            try {
                fichero1 = new FileOutputStream("1.txt");
                ObjectOutputStream pipe = new ObjectOutputStream(fichero1);
                pipe.writeObject(AltaAlumno);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }finally {
                try{
                    fichero1.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }else{
            try {
                fichero2 = new FileOutputStream("2.txt");
                ObjectOutputStream pipe = new ObjectOutputStream(fichero2);
                pipe.writeObject(AltaAlumno);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }catch (IOException ex){
                ex.printStackTrace();
            }finally {
                try{
                    fichero2.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
    }
    public void Modificar(){
        System.out.println("Modificar");

    }
    public void Mostrar(){
            FileInputStream ficheroEntrada=null;
            FileInputStream ficheroEntrada2=null;
            Alumno alu;
            try{
                ficheroEntrada=new FileInputStream("1.txt");
                ObjectInputStream PipeIn=new ObjectInputStream(ficheroEntrada);
                alu=(Alumno) PipeIn.readObject();
                alu.mostrarAlumno();
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }catch(IOException ex){
                ex.printStackTrace();
            }catch (ClassNotFoundException ex){
                ex.printStackTrace();
            }
            try{
            ficheroEntrada2=new FileInputStream("2.txt");
            ObjectInputStream PipeIn=new ObjectInputStream(ficheroEntrada2);
            alu=(Alumno) PipeIn.readObject();
            alu.mostrarAlumno();
            }catch(FileNotFoundException ex){
            ex.printStackTrace();
            }catch(IOException ex){
            ex.printStackTrace();
            }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        }
    }


