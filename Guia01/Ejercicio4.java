import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);

    int i, n, x, y, primerC = 0, segundoC = 0, tercerC = 0, cuartoC = 0;

    System.out.println("Ingrese la cantidad de puntos a colocar:");
    n = teclado.nextInt();

    for(i = 1; i <= n; i++) {
      System.out.println("Ingrese las coordenadas del punto " + i);

      System.out.println("Coordenada X:");
      x = teclado.nextInt();
      System.out.println("Coordenada Y:");
      y = teclado.nextInt();

      if(x > 0 && y > 0) {
        primerC++;
      }
      else if(x < 0 && y > 0) {
        segundoC++;
      }
      else if(x < 0 && y < 0) {
        tercerC++;
      }
      else if(x > 0 && y < 0) {
        cuartoC++;
      }
      else {
        System.out.println("Debe ingresar un valor distinto a cero");
        i--;
      }
    }

    System.out.println("La cantidad de puntos en el primer cuadrante es de: " + primerC);
    System.out.println("La cantidad de puntos en el segundo cuadrante es de: " + segundoC);
    System.out.println("La cantidad de puntos en el tercer cuadrante es de: " + tercerC);
    System.out.println("La cantidad de puntos en el cuarto cuadrante es de: " + cuartoC);
  }
}