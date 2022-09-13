import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Archivo {
    Scanner sc=new Scanner(System.in);
    int noControl,Semestre;
    float promedio;
    String nombre,opc="";

    public void Altas() throws ClassNotFoundException, IOException{
        do{
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
            if(promedio<=69){
                insertOneStudent("1.txt", AltaAlumno);
              
            }else{
                insertOneStudent("2.txt", AltaAlumno);
            }
            System.out.println("Si ya no quieres ingresar mas alumnos teclea si");
            opc=sc.next();
        }while(opc.contains("s"));
    }
    
   
    public void Mostrar() throws ClassNotFoundException, IOException{
            System.out.println("-------------Alumnos Reprobados-----------");
            ArrayList<Alumno> studentsFailed = getAllStudents("1.txt");
            if(studentsFailed!=null)
            studentsFailed.forEach((n) -> n.mostrarAlumno());
            System.out.println("------------------------------------------\n");

            System.out.println("-------------Alumnos Aprobados-----------");
            ArrayList<Alumno> studentsApproved = getAllStudents("2.txt");
            if(studentsApproved!=null)
            studentsApproved.forEach((n) -> n.mostrarAlumno());
            System.out.println("------------------------------------------\n");
        }




        public ArrayList<Alumno> getAllStudents(String nameFile)throws ClassNotFoundException, IOException{
            ArrayList<Alumno> listStudents = new ArrayList<Alumno>();
            try{
            FileInputStream fileIn = new FileInputStream(nameFile);
            ObjectInputStream input=null; 
                input = new ObjectInputStream(fileIn);
                listStudents = (ArrayList<Alumno>) input.readObject();
                fileIn.close();
               
            }catch(EOFException  ex){
                
            }
            catch (FileNotFoundException e){
              return null;
            }
        return listStudents;
    }



        public void Modificar()throws ClassNotFoundException, IOException{
            Scanner sc=new Scanner(System.in);
            System.out.println("Ingresa el numero de control del alumno a modificar");
            int noControl =sc.nextInt();
           Alumno a = getIdStudent(noControl);
            if (a!=null){
                float promedio;
                System.out.println("Editar al alumno con el numero de control: " + a.getNoControl());
                System.out.println("Ingresa el nuevo promedio del alumno");
                promedio=sc.nextFloat();
                Alumno alumnoedit = new Alumno(a.getNombre(),a.getNoControl(), a.getSemestre(), promedio);
                if(a.getPromedio()<70){
                    dropOneStudent("1.txt", a.getNoControl());
                }
                else {
                    dropOneStudent("2.txt", a.getNoControl());
                }
                if(promedio<70){
                    insertOneStudent("1.txt", alumnoedit);
                }
                else{
                    insertOneStudent("2.txt", alumnoedit);
                }
            }
            else
            System.out.println("El alumno no existe");
        }

        public void dropOneStudent(String nameFile, int noControl)throws ClassNotFoundException, IOException{
            ArrayList<Alumno> students = getAllStudents(nameFile);
            for(int i=0;students.size()>i;i++){
                if (students.get(i).getNoControl() == noControl){
                    students.remove(i);
                    i=students.size()+1; //para salir del for xD
                }
            }
            
            FileOutputStream fichero1 = new FileOutputStream(nameFile);
            ObjectOutputStream pipe = new ObjectOutputStream(fichero1);
            pipe.writeObject(students);
              pipe.close();
              pipe.flush();
              fichero1.close();
              fichero1.flush();
            //
        }

        public Alumno getIdStudent (int noControl)throws ClassNotFoundException, IOException{
            ArrayList<Alumno> students;
            for(int i=0;i<2;i++){
                students = getAllStudents((i+1)+".txt");
            for (Alumno a: students) {
                if(a.getNoControl() == noControl)
                    return a;
              }
            }
             return null; 
        }

        public void insertOneStudent (String nameFile, Alumno a)throws ClassNotFoundException, IOException{
            ArrayList<Alumno> list =  getAllStudents(nameFile);
            if (list==null){
                list = new  ArrayList<Alumno>();
                
            }
            FileOutputStream fichero = new FileOutputStream(nameFile);
            ObjectOutputStream pipe = new ObjectOutputStream(fichero);
            list.add(a);
            pipe.writeObject(list);
            pipe.close();
            pipe.flush();
            fichero.close();
            fichero.flush();
        }
    }