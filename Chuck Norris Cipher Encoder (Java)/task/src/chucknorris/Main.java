package chucknorris;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while(running){
            System.out.println("Please input operation (encode/decode/exit):");
            String input = scanner.nextLine();
            if(input.equals("encode")){
                encode();
            }else if(input.equals("decode")){
                decode();
            }else if(input.equals("exit")){
                System.out.println("Bye!");
                running = false;
            }else{
                System.out.println("There is no '" + input + "' operation");
            }
        }
    }

    public static void encode (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        String binary = "";
        for(int i = 0; i<input.length(); i++) {
            String binaryChar = Integer.toBinaryString(input.charAt(i));
            int digBinaryChar = Integer.parseInt(binaryChar);
             String binaryIteration = String.format("%07d", digBinaryChar);
             binary += binaryIteration;
        }
        char startingChar = binary.charAt(0);
        String encoded = "";
        if(startingChar== '0'){
            encoded += "00 ";
        }if(startingChar=='1'){
            encoded += "0 ";
        }
        int counter = 1;
        for(int i = 1; i< binary.length(); i++){
            if(binary.charAt(i-1) == binary.charAt(i)){
                counter++;
            }else{
                //startingChar= binary.charAt(i);
                for(int j = 0; j<counter; j++){
                    encoded += "0";
                }
                if(binary.charAt(i) == '0'){
                    encoded += " 00 ";
                }else if(binary.charAt(i)=='1'){
                    encoded += " 0 ";
                }

                counter = 1;
            }
        }
        for(int j = 0; j<counter; j++){
            encoded += "0";
        }
        System.out.println("Encoded string:");
        System.out.println(encoded);
    }

    public static void decode(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input encoded string:");
        String input = scanner.nextLine();
        String[] inputArr = input.split(" ");
        if(validate(input, inputArr)){
            String decodedMessageBinary = "";
            String bitToken = "";
            if(inputArr[0].equals("0")){
                bitToken = "1";
            }else{
                bitToken = "0";
            }
            for(int i = 1; i<inputArr.length; i++){
                if(i%2==1){
                    String amountToken = inputArr[i];
                    for(int j = 0; j<amountToken.length(); j++){
                        decodedMessageBinary+=bitToken;
                    }
                }else{
                    bitToken = bitToken.equals("0") ? "1" : "0";
                }
            }
            if(decodedMessageBinary.length()%7 != 0){
                System.out.println("Encoded string is not valid.");
            }else{
                String decodedMessage = "";
                for(int i = 6; i<decodedMessageBinary.length(); i=i+7){
                    String substring = decodedMessageBinary.substring(i-6, i+1);
                    int decimalChar = 0;
                    for(int j=0; j<substring.length(); j++){
                        if(substring.charAt(j) == '1'){
                            decimalChar += Math.pow(2, substring.length()-1-j);
                        }
                    }
                    char c = (char)decimalChar;
                    decodedMessage += c;
                }
                System.out.println("Decoded string:");
                System.out.println(decodedMessage);
            }

        }else{
            System.out.println("Encoded string is not valid.");
        }

    }

    public static boolean validate(String input, String[] arr){
        boolean check1 = true;
        for(int i = 0; i<input.length(); i++){
            if (input.charAt(i) != '0' && input.charAt(i) != ' ') {
                check1 = false;
                break;
            }
        }
        boolean check2 = true;
        for(int i = 0; i<arr.length; i+=2){
            if(!arr[i].equals("0") && !arr[i].equals("00")){
                check2 = false;
                break;
            }
        }
        boolean check3 = false;
        if(arr.length%2==0){
            check3 = true;
        }
        return check1 && check2 && check3;
    }
}