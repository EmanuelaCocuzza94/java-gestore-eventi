package org.javaGestoreEventi;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.lang.NumberFormatException;

public class Main {
	
	/* Class Main START */
	
	public static void main (String[] args) {	
		
		System.out.println("Inizio Programma");
		
		Scanner scan_line = new Scanner (System.in);
		String tipologia = null;
		
		String scelta;
        String[] scelteValide = {"1", "2", "3"};
        boolean ripeti = true;
        
		do {
			System.out.println("Seleziona una scelta:");
			System.out.println("1) crea EVENTO (generico)");
			System.out.println("2) crea CONCERTO");
			System.out.println("3) Esci");
			
			 scelta = scan_line.nextLine();
			 
            for (String s : scelteValide) {
                if (s.equals(scelta)) {
                	ripeti = false;
                	
                	if(Integer.parseInt(scelta) == 1) {
                		tipologia = "Evento";
                	}
                	
                	if(Integer.parseInt(scelta) == 2) {
                		tipologia = "Concerto";
                	}
                    break;
                }
            }
            
            if (ripeti) {
                System.out.println("Scelta non valida. Riprova.");
            }
			 
		} while (ripeti);
		
		if (Integer.parseInt(scelta) != 3) {
			Main.creaEvento(tipologia, scan_line);
		}
		
		System.out.println("Programma Terminato! Arrivederci!");	
		scan_line.close();
	}
	
	
	public static void creaEvento(String tipologia, Scanner scan_line) throws IllegalArgumentException {
	
		int maxPosti = 1000;
		
		System.out.println("Inserisci il nome del " + tipologia + ":");
		String eventoInserito = scan_line.nextLine();
		
		LocalDate dataEvento = InputManagement.validaData(scan_line, tipologia);
		int postiTotali = InputManagement.validaPostiTotali(scan_line, maxPosti, tipologia);
		
		if (tipologia == "Concerto") {
			
			LocalTime oraEvento = InputManagement.validaOra(scan_line, tipologia);
			double prezzoEvento = InputManagement.validaPrezzo(scan_line, tipologia);
			Concerto concerto1 = new Concerto(eventoInserito, dataEvento, postiTotali, oraEvento, prezzoEvento);
			System.out.println(concerto1.toString());
			concerto1.operazioniEvento(scan_line);
			
			
		}else if(tipologia == "Evento") {
			
			Evento evento1 = new Evento(eventoInserito, dataEvento, postiTotali);
			System.out.println(evento1.toString());
			evento1.operazioniEvento(scan_line);
		}

	}
	
	
	/* Class Main END */
}