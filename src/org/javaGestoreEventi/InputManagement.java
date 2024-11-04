package org.javaGestoreEventi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputManagement {
	
	/**
	 * Funzione che prende in input un array di Stringhe di scelta
	 * e restisuisce la scelta validata. Se si inserisce una scelta errata verrà chiesto di inserire nuovamente una scelta
	 * 
	 * @param scelteValide Array String di scelte valide
	 * @return String scelta
	 */
	public static String getScelta(String[] scelteValide, Scanner scan, String tipologia) {
		String scelta;

		// Verifica se la scelta è valida
        boolean sceltaCorretta = false;
		
		do {
			System.out.println("Seleziona una scelta:");
			System.out.println("1) Vuoi prenotare dei posti per questo " + tipologia);
			System.out.println("2) Vuoi disdire dei posti per questo " + tipologia);
			System.out.println("3) Esci");
			
		 	scelta = scan.nextLine();
			 
            for (String s : scelteValide) {
                if (s.equals(scelta)) {
                    sceltaCorretta = true;
                    break;
                }
            }
            
            if (!sceltaCorretta) {
                System.out.println("Scelta non valida. Riprova.");
            }
			 
		} while (!sceltaCorretta);
		
		return scelta;
	}
	
	public static double validaPrezzo(Scanner scan, String tipologia) throws IllegalArgumentException, NumberFormatException {
		double prezzo = 0;
		boolean error;
		// Ripeto l'inserimento dei PostiTotali finché l'utente non inserisce un dato corretto (senza errori)
		do {
			error = false;
			try {	
				// Controllo e gestione errore inserimento PostiTotali
				System.out.println("Inserisci il prezzo del " + tipologia + " (usa il formato ##.##):");
				String inputPrezzo=scan.nextLine();
				prezzo = Double.parseDouble(inputPrezzo);
				
				if (prezzo < 0) {
					throw new IllegalArgumentException("Il prezzo non può essere minore di zero!");
				}
				
			} catch (NumberFormatException errore) {
				error = true;
				System.out.println("Hai inserito un Formato Prezzo Errato! Riprova !!");
				System.out.println("*****\n");
				
			} catch (IllegalArgumentException errore) {
				error = true;
				System.out.println("Errore!");
				System.out.println(errore.getMessage() + " Riprova!!");
				System.out.println("*****\n");
			}
		} while(error);
		
		return prezzo;
	}
	
	public static LocalTime validaOra(Scanner scan, String tipologia) throws DateTimeParseException {
		boolean error;
		LocalTime ora = null;
		// Ripeto l'inserimento della Data finché l'utente non inserisce un dato corretto (senza errori)
		do {
			error = false;
			try {
				// Controllo e gestione errore inserimento Data
				System.out.println("Inserisci l'ora del " + tipologia + ": (usa il formato HH:mm)");
				String oraScelta=scan.nextLine();
				ora = LocalTime.parse(oraScelta);
				
			} catch (DateTimeParseException errore) {
				error = true;
				System.out.println("Hai inserito un formato Ora sbagliato! Riprova!!");
				System.out.println("*****\n");
			}
		}while(error);
		
		return ora;
	}
	
	public static int validaPostiTotali(Scanner scan, int maxPosti, String tipologia) throws IllegalArgumentException, NumberFormatException  {
		int postiTotali = 0;
		boolean error;
		// Ripeto l'inserimento dei PostiTotali finché l'utente non inserisce un dato corretto (senza errori)
		do {
			error = false;
			try {	
				// Controllo e gestione errore inserimento PostiTotali
				System.out.println("Inserisci i posti totali del " + tipologia + ": (maxPosti " + maxPosti + ")");
				String inputPosti=scan.nextLine();
				postiTotali = Integer.parseInt(inputPosti);
				if (postiTotali < 0) {
					throw new IllegalArgumentException("I posti totali non posso essere minori di zero!");
				}
				if (postiTotali > maxPosti) {
					throw new IllegalArgumentException("I posti totali non posso essere maggiori di " + maxPosti + "!");
				}
				
			} catch (NumberFormatException errore) {
				error = true;
				System.out.println("Hai inserito un Formato Errato! Riprova inserendo un numero!!");
				System.out.println("*****\n");
				
			} catch (IllegalArgumentException errore) {
				error = true;
				System.out.println("Hai inserito un numero posti Errato!");
				System.out.println(errore.getMessage() + " Riprova!!");
				System.out.println("*****\n");
			}
		} while(error);
		
		return postiTotali;
	}
	
	public static LocalDate validaData(Scanner scan, String tipologia) throws IllegalArgumentException, DateTimeParseException {
		boolean error;
		LocalDate data = null;
		// Ripeto l'inserimento della Data finché l'utente non inserisce un dato corretto (senza errori)
		do {
			error = false;
			try {
				// Controllo e gestione errore inserimento Data
				System.out.println("Inserisci la data del " + tipologia + ": (usa il formato yyyy-mm-dd)");
				String dataScelta=scan.nextLine();
				data = LocalDate.parse(dataScelta);
				if (data.isBefore(LocalDate.now())) {
					// genero manualmente un eccezzione
					throw new IllegalArgumentException("Hai inserito una DATA passata!");
		        }
				
			} catch (DateTimeParseException errore) {
				error = true;
				System.out.println("Hai inserito un formato Data sbagliato! Riprova!!");
				System.out.println("*****\n");
			} catch (IllegalArgumentException errore) {
				error = true;
				System.out.println("Data Errata!");
				System.out.println(errore.getMessage() + " Riprova!!");
				System.out.println("*****\n");
			}
		}while(error);
		
		return data;
	}
}
