import java.util.Scanner;
import java.util.function.DoubleUnaryOperator;

import javax.swing.text.html.HTMLDocument.BlockElement;

public class CalculadoraDeRiemann {
    
    static Scanner sc = new Scanner(System.in);

    public static boolean isFim(String string){
        return(!(string.length() == 1 && string.charAt(0) == '0'));
    }
    public static void main(String[] args){
        String a, b, n, menu, function;

        do{

            System.out.println("Digite o intervalo da função:");
            System.out.println("numero mais baixo:");
            a = sc.nextLine();

            System.out.println("numero mais alto:");
            b = sc.nextLine();

            System.out.println("Digite sua função:");
            
            function = sc.nextLine();

            parseFunction(a, b, function);

            System.out.println("Menu:");
            System.out.println("Pressione 1 para continuar calculando integrais");
            System.out.println("Pressione 0 para sair do programa");
            menu = sc.nextLine();
        }while(isFim(menu));
    }

    /*****************************************************   Parsing a e b    ************************************************************************************************/

    public static float parseAB(String ab){
        int num = 0, numD = 0;
        float result = 0;
        boolean boolDivision = false;

        for(int i=0; i<ab.length(); i++){
            if(ab.charAt(i) == '/'){
                boolDivision = true;
            }
        }

        for(int i=0; i<ab.length(); i++){
            if(ab.charAt(i) == 'l'){                                                    //ln 
                for(int j=i+3; j<ab.length(); j++){
                    num = (num * 10) + Character.getNumericValue(ab.charAt(j));
                }
                double ln = Math.log(num);
                result = (float) ln;
            }
            else if(ab.charAt(i) == '-'){
                if(boolDivision == false){                                              //numero inteiro
                    for(int j=i+1; j<ab.length(); j++){
                        num = (num * 10) + Character.getNumericValue(ab.charAt(j));
                    }

                    result = -num;
                }
                else{                                                                   //divisao
                    for(int j=i+1; ab.charAt(j) != '/'; j++){
                        num = (num * 10) + Character.getNumericValue(ab.charAt(j));
                        i++;
                    }
                    for(int j=i+1; j<ab.length(); j++){
                        numD = (numD * 10) + Character.getNumericValue(ab.charAt(j));
                    }   
                    result = -num / numD;
                }
            }
            else{
                if(boolDivision == false){                                              //numero inteiro
                    for(int j=i+1; j<ab.length(); j++){
                        num = (num * 10) + Character.getNumericValue(ab.charAt(j));
                    }
                    result = num;
                }
                else{                                                                   //divisao
                    for(int j=i+1; ab.charAt(j) != '/'; j++){
                        num = (num * 10) + Character.getNumericValue(ab.charAt(j));
                        i++;
                    }
                    for(int j=i+1; j<ab.length(); j++){
                        numD = (numD * 10) + Character.getNumericValue(ab.charAt(j));
                    }   
                    result = -num / numD;
                }
            }
        }

        return result;
    }


    /*****************************************************   Parsing função    ************************************************************************************************/

    public static void parseFunction(String aS, String bS, String func){
        int num, numD, numOps = 0;
        float a, b; 
        boolean boolDivision = false, firstMinus = true;

        a = parseAB(aS);

        b = parseAB(bS);

        for(int i=0; i<func.length(); i++){
            if(func.charAt(i) == '+' && func.charAt(i) == '-' && func.charAt(i) == '/' && func.charAt(i) == '*'){
                if( func.charAt(i) == '-' &&  firstMinus == true){
                    firstMinus = false;
                }
                else{
                    numOps++;
                }
            }
        }
        
        for(int i=0; i<func.length(); i++){                                 
            if(func.charAt(i) == '^'){                                      //exponencial
                num = Character.getNumericValue(func.charAt(i - 1));        
                int n = Character.getNumericValue(func.charAt(i + 1));

                for(int j = 1; j < n; j++){
                    num = num * num;
                }
                
            }
            else if(){
                if(boolDivision == false){                                              //numero inteiro
                    for(int j=i+1; j<ab.length(); j++){
                        num = (num * 10) + Character.getNumericValue(ab.charAt(j));
                    }

                    result = -num;
                }
                else{                                                                   //divisao
                    for(int j=i+1; ab.charAt(j) != '/'; j++){
                        num = (num * 10) + Character.getNumericValue(ab.charAt(j));
                        i++;
                    }
                    for(int j=i+1; j<ab.length(); j++){
                        numD = (numD * 10) + Character.getNumericValue(ab.charAt(j));
                    }   
                    result = -num / numD;
                }
            }
        }

        riemann(a, b, numOps);
    }


    /*****************************************************   calculando integral    ************************************************************************************************/

    public static float sum(float x, float y){
        return x + y;
    }

    public static float minus(float x, float y){
        return x - y;
    }

    public static float mult(float x, float y){
        return x * y;
    }

    public static float div(float x, float y){
        return x / y;
    }

    public static void riemann(float a, float b, int numOps, float[] arrNum ){

        int n = 10000;

        float base = (b - a)/n;

        for(int i=1; i<n; i++){
            base * func();
        }
    }

}

