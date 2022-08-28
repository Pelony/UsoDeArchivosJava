import java.io.Serializable;

public class Alumno implements Serializable {
    private String nombre;
    private Integer noControl,Semestre;
    private Float promedio;

    public Alumno(String nombre, Integer noControl, Integer semestre, Float promedio) {
        this.nombre = nombre;
        this.noControl = noControl;
        Semestre = semestre;
        this.promedio = promedio;
    }
    public void mostrarAlumno(){
        System.out.println("noControl: "+ noControl);
        System.out.println("Nombre: "+ nombre);
        System.out.println("Semestre: "+ Semestre);
        System.out.println("Promedio: "+ promedio);
    }

}
