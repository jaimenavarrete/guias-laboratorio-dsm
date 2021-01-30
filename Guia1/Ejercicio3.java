import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);

    int i, n, num, pares, impares;

    pares = 0;
    impares = 0;

    System.out.println("Coloque la cantidad de numeros que quiere ingresar:");
    n = teclado.nextInt();

    for(i = 1; i <= n; i++) {
      System.out.println("Ingrese el numero " + i + ":");
      num = teclado.nextInt();

      if(num % 2 == 0) {
        pares++;
      }
      else {
        impares++;
      }
    }

    System.out.println("La cantidad de numeros pares es de: " + pares);
    System.out.println("La cantidad de numeros impares es de: " + impares);
  }
}