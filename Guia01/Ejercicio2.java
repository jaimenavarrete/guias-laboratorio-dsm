import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);

    int i, aprobados, desaprobados;
    float nota;

    aprobados = 0;
    desaprobados = 0;

    for(i = 1; i <= 10; i++) {
      System.out.print("Ingrese la nota del alumno " + i + ":\n");
      nota = teclado.nextFloat();

      if(nota >= 7.0) {
        aprobados++;
      }
      else {
        desaprobados++;
      }
    }

    System.out.println("La cantidad de alumnos con notas mayores o iguales a 7 es de: " + aprobados);
    System.out.println("La cantidad de alumnos con notas menores a 7 es de: " + desaprobados);
  }
}