package Client;
import EstructurasDeDatos.*;
import java.util.Scanner;
public class Vista {
    public static int prioridad(String ch){
        switch(ch){
            case "^":
                return 5;
            case "*":
                return 4;
            case "/":
                return 3;
            case "+":
                return 2;
            case "-":return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GenericIterableResizingStack<String> pila;
        GenericResizingQueue<String> colaEntrada;
        GenericResizingQueue<String> colaSalida;
        System.out.println("Ingrese la expresion a evaluar");
        String expresion = sc.nextLine();
        pila = new GenericIterableResizingStack<>();
        colaEntrada = new GenericResizingQueue<>();
        colaSalida = new GenericResizingQueue<>();
        String atomo;

        for (int i = 0; i < expresion.length(); i++) {
            colaEntrada.enqueue(expresion.charAt(i)+"");
            atomo = colaEntrada.dequeue();
            if((atomo.equals("+") || atomo.equals("-") || atomo.equals("*") || atomo.equals("/"))){
                System.out.println("Entre");
                while(!pila.isEmpty() && prioridad(atomo) <= prioridad(pila.peak())){
                    colaSalida.enqueue(pila.pop());
                    System.out.println("Ando revisando la prioridad");
                }
                pila.push(atomo);
                System.out.println("Me pille algo");
            }else{
                System.out.println("Meti un operando");
                colaSalida.enqueue(atomo);
            }
        }

        while(!pila.isEmpty()){
            System.out.println("Metiendo los operadores");
            colaSalida.enqueue(pila.pop());
        }

        do {
            atomo = colaSalida.dequeue();
            if(atomo.equals("+") || atomo.equals("-") || atomo.equals("*") || atomo.equals("/")){
                if(atomo.equals("+")){
                    String operando2 = pila.pop();
                    String operando1 = pila.pop();
                    int resultado = Integer.parseInt(operando1) + Integer.parseInt(operando2);
                    pila.push(String.valueOf(resultado));
                }/*else if(atomo.equals("-")){
                    String operando2 = pila.pop();
                    String operando1 = pila.pop();
                    int resultado = Integer.parseInt(operando1) - Integer.parseInt(operando2);
                    pila.push(String.valueOf(resultado));
                } else if (atomo.equals("*")) {
                    String operando2 = pila.pop();
                    String operando1 = pila.pop();
                    int resultado = Integer.parseInt(operando1) * Integer.parseInt(operando2);
                    pila.push(String.valueOf(resultado));
                } else if (atomo.equals("/")) {
                    String operando2 = pila.pop();
                    String operando1 = pila.pop();
                    int resultado = Integer.parseInt(operando1) / Integer.parseInt(operando2);
                    pila.push(String.valueOf(resultado));
                }*/
            }else{
                pila.push(atomo);
            }
        }while(!colaSalida.isEmpty());

        String resultado = pila.pop();
        System.out.println(resultado);

    }

}

