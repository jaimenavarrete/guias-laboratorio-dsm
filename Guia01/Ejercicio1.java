// Guia 1 - Ejercicio 1 - Comprobar si dos numeros son divisibles entre si
 
import java.util.*;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("Escriba el primer numero:");
    int num1 = in.nextInt();

    System.out.println("\nEscriba el segundo numero:");
    int num2 = in.nextInt();

    // Comprobar si son divisibles
    if(num1 % num2 == 0) {
      System.out.println(num1 + " y " + num2 + " SI son divisibles entre si.");
    }
    else {
      System.out.println(num1 + " y " + num2 + " NO son divisibles entre si.");
    }
  }
}