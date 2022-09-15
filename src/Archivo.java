import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;


public class Archivo {
    Scanner sc=new Scanner(System.in);
    int noControl,Semestre;
    float promedio;
    String nombre,opc="";

    RandomAccessFile salida;
    int a_tamaRegistro= 29, v_numRenglones, pm;  

    
    public void ingresar(){
        String nombre;
        int noControl, semestre;
        float promedio;
                System.out.println("Ingresa el noControl del alumno");
                noControl=sc.nextInt();
                System.out.println("Ingresa el nombre del alumno");
                do{
                    nombre=sc.next();
                    nombre = formatString(15, nombre);
                    if(nombre==null)
                    System.out.println("Ingresa un nombre con 15 caracteres como máximo");
                }while(nombre==null);
                System.out.println("Ingresa el semestre del alumno");
                semestre=sc.nextInt();
                System.out.println("Ingresa el promedio del alumno");
                promedio=sc.nextFloat();
                Alumno alumno = new Alumno(nombre, noControl, semestre, promedio);
                insertStudent(alumno, (promedio<70?"reprobados.dat":"aprobados.dat"));
    }


    
    public void insertStudent(Alumno alumno, String fileName){
         try
         {
             RandomAccessFile input =new RandomAccessFile(fileName,"rw");
             input.seek(input.length()); 
             input.writeInt(alumno.getNoControl());
             input.writeUTF(alumno.getNombre());
             input.writeInt(alumno.getSemestre());
             input.writeFloat(alumno.getPromedio());
             input.close(); 
         }
         catch (IOException e)
         {
             System.out.println("No se logro abrir correctamente el fichero \n"+e.toString());
         }
 }



    public String formatString(int maxLength, String string){
        if(!(string.length()>maxLength)){
            int i = maxLength - string.length();
            for(;i>0;i--)
                string+= " ";
            return string;
            }
        else 
        return null;
    }


    public void show() {  
        String nombre;
        int noControl, semestre;
        float promedio; 
        for(int a =0;a<2;a++){
        try
        {
            RandomAccessFile output =new RandomAccessFile((a==0?"aprobados.dat":"reprobados.dat"),"rw");
            long registros=output.length()/a_tamaRegistro;
            System.out.println("-------------"+(a==0?"Alumnos aprobados":"Alumnos reprobados")+"-----------");
            System.out.println("registros: " + registros);
            for(int i=0; i<registros;i++)
            {   
                output.seek(i*a_tamaRegistro); 
                noControl = output.readInt();
               nombre=output.readUTF();
               semestre = output.readInt();
               promedio = output.readFloat();
                System.out.println(noControl);
                System.out.println(nombre);
                System.out.println(semestre);
                System.out.println(promedio);
                System.out.println("------------------------------------------");
                
            }
            output.close();
        }
        catch (IOException e)
        {
            System.out.println(" No se abrio bien el fichero \n"+e.toString());
        }
    }
    }


    public void modificar() throws IOException{
        System.out.println("Ingresa el noControl del alumno");
        int noControl=sc.nextInt();
        Alumno alumno = buscar(noControl);
        try {
            if(alumno!=null){
                System.out.println("Ingresa el nuevo promedio del alumno");
                Float newPromedio = sc.nextFloat();
                fileTemp((alumno.getPromedio()<70?"reprobados.dat":"aprobados.dat"));
                RandomAccessFile temp=new RandomAccessFile("temp.dat","rw");
                RandomAccessFile file=new RandomAccessFile((alumno.getPromedio()<70?"reprobados.dat":"aprobados.dat"),"rw");
                alumno.setPromedio(newPromedio);
                long registros=temp.length()/a_tamaRegistro;
                int i=0, j=0;
            for(i=0; i<registros;i++)
            {   
                temp.seek(i*a_tamaRegistro);
                int tempNoControl = temp.readInt();
                if(tempNoControl!=alumno.getNoControl()){
                String tempNombre=temp.readUTF();
                int tempSemestre = temp.readInt();
                float tempPromedio = temp.readFloat();
                file.seek(j*a_tamaRegistro);
                file.writeInt(tempNoControl);
                file.writeUTF(tempNombre);
                file.writeInt(tempSemestre);
                file.writeFloat(tempPromedio);
                j++;
                }
            }
            temp.close();
            file.close();
            insertStudent(alumno, (alumno.getPromedio()<70?"reprobados.dat":"aprobados.dat"));
            }
            else System.out.println("No se encontro");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }


    public Alumno buscar(int noControl){
        RandomAccessFile output;
        for(int a =0;a<2;a++){
        try
        {
            output=new RandomAccessFile((a==0?"aprobados.dat":"reprobados.dat"),"rw");
            long registros=output.length()/a_tamaRegistro;
            for(int i=0; i<registros;i++)
            {   
                output.seek(i*a_tamaRegistro);  
               int auxNoControl = output.readInt();
               if(auxNoControl == noControl){
                String nombre = output.readUTF();
                int semestre = output.readInt();
                Float promedio = output.readFloat();
                Alumno alumno = new Alumno(nombre, noControl, semestre, promedio);
                output.close();
                return alumno;
               }
            }
            output.close();
        }
        catch (IOException e)
        {
            System.out.println(" No se abrio bien el fichero \n"+e.toString());
        }
    }
        return null;
    }

    public void fileTemp(String fileName){
        Path origenPath = FileSystems.getDefault().getPath(fileName);
        Path destinoPath = FileSystems.getDefault().getPath("temp.dat");

        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    }