/* Actualizado a 21/01/26 por Leire Trabado*/
package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utilities {
	
	public static String fechaToString(LocalDate fecha) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String wfecha;
		
		wfecha = fecha.format(formateador);
		
		return wfecha;
	}
	public static LocalDate leerFechaDMA() {
		boolean error;
		LocalDate date = null;
		String dateString;
		DateTimeFormatter formateador=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		do{
			error=false;
			dateString=introducirCadena();
			try{
				date=LocalDate.parse(dateString, formateador);
			}catch(DateTimeParseException e){
				System.out.println("Error: please enter a date in dd/mm/yyyy format ");
				error=true;
			}
		}while (error);
		return date;
	}
	
	

	public static LocalDate leerFechaAMD() {
		boolean error;
		LocalDate date = null;
		String dateString;
		DateTimeFormatter formateador=DateTimeFormatter.ofPattern("yyyy/MM/dd");
		do{
			error=false;
			dateString=introducirCadena();
			try{
				date=LocalDate.parse(dateString, formateador);
			}catch(DateTimeParseException e){
				System.out.println("Error: please enter a date in the format yyyy/MM/dd");
				error=true;
			}
		}while (error);
		return date;
	}
	
	
	public static char leerChar(char opt1, char opt2) {
		char letra=' ';
		String cadena;
		boolean error;
		do{
			error=false;
			cadena=introducirCadena();
			if (cadena.length()!=1){
				System.out.println("Error, please enter a single character: ");
				error=true;
			}
			else{
				letra=cadena.charAt(0);
				letra=Character.toUpperCase(letra);
				if (letra!=opt1 && letra!=opt2){
					System.out.println("Error: the option you entered is incorrect. Please enter "+ opt1+ " or "+ opt2);
					error=true;
				}
			}
		}while (error);
			
		return letra;
	}

	public static char leerChar() {
		char letra=' ';
		String cadena;
		boolean error;
		do{
			error=false;
			cadena=introducirCadena();
			if (cadena.length()!=1){
				System.out.println("Error: please enter a single character: ");
				error=true;
			}
		}while (error);
		letra=cadena.charAt(0);
		return letra;
	}

	public static char leerChar(String mensaje) {
		char letra=' ';
		String cadena;
		boolean error;
		do{
			error=false;
			System.out.println(mensaje);
			cadena=introducirCadena();
			if (cadena.length()!=1){
				System.out.println("Error: please enter a single character: ");
				error=true;
			}
		}while (error);
		letra=cadena.charAt(0);
		return letra;
	}

	public static float leerFloat() {
		float num = 0;
		boolean error;
		do{
			error=false;
			try{
				num=Float.parseFloat(introducirCadena());
			}catch (NumberFormatException e){
				System.out.println("Non-numeric value. Please enter again: ");
				error=true;
			}
		}while (error);
		return num;
	}

	public static float leerFloat(String message, float min, float max) {
		float num = 0;
		boolean error;
		System.out.println(message);
		do{
			error=false;
			try{
				num=Float.parseFloat(introducirCadena());
				
			}catch (NumberFormatException e){
				System.out.println("Non-numeric value. Please enter again: ");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.println("Number outside the range. Please enter a number between "+ min+ " and "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}

	public static float leerFloat(float min, float max) {
		float num = 0;
		boolean error;
		do{
			error=false;
			try{
				num=Float.parseFloat(introducirCadena());
				
			}catch (NumberFormatException e){
				System.out.println("Non-numeric value. Please enter again: ");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.println("Number out of range. Please enter a number between "+ min+ " and "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}

	public static int leerInt(String message, int min, int max) {
		int num = 0;
		boolean error;
		System.out.println(message);
		do{
			error=false;
			try{
				num=Integer.parseInt(introducirCadena());
				
			}catch (NumberFormatException e){
				System.out.println("Non-numeric value. Please enter again: ");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.println("Number outside the range; please enter a number between "+ min+ " and "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}

	public static int leerInt(int min, int max) {
		int num = 0;
		boolean error;
		do{
			error=false;
			try{
				num=Integer.parseInt(introducirCadena());
				
			}catch (NumberFormatException e){
				System.out.println("Non numeric value. Try again:");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.println("Number out of bounds, try a number between "+ min+ " and "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}

	public static int leerInt() {
		int num = 0;
		boolean error;
		do{
			error=false;
			try{
				num=Integer.parseInt(introducirCadena());
			}catch (NumberFormatException e){
				System.out.println("Valor no numérico. Introduce de nuevo:");
				error=true;
			}
		}while (error);
		return num;
	}

	public static int leerInt(String mensaje) {
		int num = 0;
		boolean error;
		do{
			error=false;
			try{
				System.out.println(mensaje);
				num=Integer.parseInt(introducirCadena());
			}catch (NumberFormatException e){
				System.out.println("Non-numeric value. Please enter again: ");
				error=true;
			}
		}while (error);
		return num;
	}
	public static String introducirCadena() {
		String cadena = "";
		boolean error;
		InputStreamReader entrada =new InputStreamReader(System.in);
		BufferedReader teclado= new BufferedReader(entrada);
		do{
			error=false;
			try {
				cadena=teclado.readLine();
			} catch (IOException e) {
				System.out.println("Data entry error");
				error=true;
			}
		}while (error);
		return cadena;
	}
	public static String introducirCadena(String mensaje) {
		String cadena = "";
		boolean error;
		InputStreamReader entrada =new InputStreamReader(System.in);
		BufferedReader teclado= new BufferedReader(entrada);
		do{
			error=false;
			try {
				System.out.println(mensaje);
				cadena=teclado.readLine();
			} catch (IOException e) {
				System.out.println("Data entry error");
				error=true;
			}
		}while (error);
		return cadena;
	}
	public static double leerDouble(String mensaje) { 
		double num = 0;
		boolean error;

		do{
			error=false;
			try{
				System.out.println(mensaje);
				num=Double.parseDouble(introducirCadena());
			}catch (NumberFormatException e){
				System.out.print("[ERROR] Non-numeric value.\nPlease enter again: ");
				error=true;
			}
		}while (error);
		return num;
	}

	public static double leerDouble() { 
		double num = 0;
		boolean error;

		do{
			error=false;
			try{
				num=Double.parseDouble(introducirCadena());
			}catch (NumberFormatException e){
				System.out.print("[ERROR] Non-numeric value.\nPlease enter again: ");
				error=true;
			}
		}while (error);
		return num;
	}
	
	//Lee un numero(double) entre el rango dado y lo devuelve
	public static double leerDouble(double min, double max) { //
		double num = 0;
		boolean error;

		do{
			error=false;
			try{
				num=Double.parseDouble(introducirCadena());
			}catch (NumberFormatException e){
				System.out.print("[ERROR] Non-numeric value. Please enter again: ");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.print("[ERROR] Number out of range.\nPlease enter a number between "+ min+ " and "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}
	
	//Muestra el mensaje y luego lee el numero(double) introducido si es entre los valores y lo devuelve
	public static double leerDouble(String message, double min, double max) { 
		double num = 0;
		boolean error;

		System.out.println(message);
		do{
			error=false;
			try{
				num=Double.parseDouble(introducirCadena());

			}catch (NumberFormatException e){
				System.out.print("[ERROR] Non-numeric value. Please enter again: ");
				error=true;
				num=min;
			}
			if(num<min || num>max){
				System.out.print("[ERROR] Number out of range.\nPlease enter a number between "+ min+ " and "+ max+": ");
				error=true;
			}
		}while (error);
		return num;
	}
	public static String introducirCadena(String palabra1,String palabra2) {

		String cadena = "";
		boolean error;
		InputStreamReader entrada =new InputStreamReader(System.in);
		BufferedReader teclado= new BufferedReader(entrada);
		do{
			error=false;
			try {
				System.out.println("Enter a choice (" + palabra1 + " or " + palabra2 + "):");
				cadena=teclado.readLine();
				cadena = cadena.trim().toUpperCase();
				if (!cadena.equalsIgnoreCase(palabra1) && !cadena.equalsIgnoreCase(palabra2)){
					System.out.println("Error: the option you entered is incorrect. Please enter "+ palabra1+ " or "+ palabra2);
					error=true;
				}

			} catch (IOException e) {
				System.out.println("Data entry error");
				error=true;
			}

		}while (error);
		return cadena;

	}

	public static String introducirCadena(String palabra1,String palabra2,String palabra3) {

		String cadena = "";
		boolean error;
		InputStreamReader entrada =new InputStreamReader(System.in);
		BufferedReader teclado= new BufferedReader(entrada);
		do{
			error=false;
			try {
				System.out.println("Enter a choice (" + palabra1 + " or " + palabra2 + " or " + palabra3 + "):");
				cadena=teclado.readLine();
				cadena = cadena.trim().toUpperCase();
				if (!cadena.equalsIgnoreCase(palabra1) && !cadena.equalsIgnoreCase(palabra2) && !cadena.equalsIgnoreCase(palabra3)){
					System.out.println("Error: the option you entered is incorrect. Please enter "+ palabra1+ " o "+ palabra2 + " or " + palabra3);
					error=true;
				}

			} catch (IOException e) {
				System.out.println("Data entry error");
				error=true;
			}

		}while (error);
		return cadena;

	}
//Lee un character y asigna true o false dependiendo de si es S o N

	public static boolean leerBoolean()

	{

	char respuesta;
	boolean booleanDevuelto;

	System.out.println("Enter S for true and N for false");
	respuesta = leerChar('S', 'N');

	if(respuesta=='S'){
		booleanDevuelto = true;
	}else{
		booleanDevuelto = false;}
	return booleanDevuelto;

	}

}
