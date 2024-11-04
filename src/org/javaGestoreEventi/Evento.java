package org.javaGestoreEventi;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.lang.NumberFormatException;
import java.util.Scanner;

public class Evento {
	private String titolo;
	private LocalDate data;
	private int postiTotale;
	private int postiPrenotati;
	private String tipologia;
	
	public Evento (String titolo, LocalDate data, int postiTotale) throws IllegalArgumentException {
		this.postiTotale = postiTotale;
		this.setData(data);
		this.postiPrenotati = 0;
		this.setTitolo(titolo);
		this.setTipologia("Evento");
	}
	
	
	public String getTitolo() {
		return this.titolo;
	}
	
	public LocalDate getData() {
		return this.data;
	}
	
	public int getPostiTotlali() {
		return this.postiTotale;
	}
	
	public int getPostiPrenotati() {
		return this.postiPrenotati;
	}
	
	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	
	//* this.titolo è l'attributo di classe al quale viene assegnato lattributo che io passo*/ 
	
	public void setTitolo (String titolo) {
		this.titolo = titolo;
	}
	
	public void setData (LocalDate data) {
		this.data = data;
		
	}
	
	/*Vanno inoltre implementati dei metodi public che svolgono le seguenti funzioni:
	prenota: aggiunge uno ai posti prenotati. Se l’evento è già passato o non ha posti disponibili deve restituire un messaggio di avviso.*/
	
	public String prenota(int posti) {
		if (posti > this.postiTotale){
			return "Impossibile prenotare! Sono presenti massimo " + this.postiTotale + " posti, dunque non puoi prenotare " + posti + " posti!";
		}
		
		if (this.data.isBefore(LocalDate.now())) {
			return "Impossibile prenotare: la data è passata!";
		}
		
		int postiDisponibili = this.postiTotale - this.postiPrenotati;
		
		if(posti > postiDisponibili) {
			return "Impossibile prenotare! Disponibili " + postiDisponibili + " posti";
		}
		
		// Se sono arrivato qui allora non ci sono errori e posso aggiungere i posti tranquillamente
		this.postiPrenotati = this.postiPrenotati + posti;
		
		return "Prenotazione effettuata con successo!";
	}
	
	/*disdici: riduce di uno i posti prenotati. Se l’evento è già passato o non ci sono prenotazioni restituisce un messaggio di avviso.*/
	
	
	public String disdici(int posti) {
		// significa sottrarre ai postiPrenotati = postiPrenotati - posti
		if(this.postiPrenotati == 0){
			return "Impossibile disdire: non risulta nessuna prenotazione!";
		}
		
		if (this.data.isBefore(LocalDate.now())) {
			return "Impossibile disdire: la data è passata!";
		}
		
		if(posti > postiPrenotati) {
			return "Impossibile disdire! I posti che vuoi disdire sono maggiori dei posti prenotati";
		}
		
		// Se arrivo qui allora non ci sono errori e posso sottrarre tranquillamente i posti ai PostiPrenotati
		this.postiPrenotati = this.postiPrenotati - posti;
		
		return "Disdetta effettuata con successo!";
	}
	
	
	
	/*l’override del metodo toString() in modo che venga restituita una stringa contenente: data formattata - titolo*/
	
	public String getDataFormattata() {
		// Definisci il formato desiderato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy"); // Modifica il pattern come desideri
        
        // Formatta la data
        return this.data.format(formatter);
	}
	
	
	public void operazioniEvento(Scanner scan_line) {
		String[] scelteValide = {"1", "2", "3"};
		//Scanner scan_int = new Scanner (System.in);
        boolean ripeti = true;
        do {
        	String sceltacalcolata = InputManagement.getScelta(scelteValide, scan_line, this.getTipologia());
    		int numeroPostiUtente = 0;
    		String sceltaPosti;
    		boolean errore;
    		switch (sceltacalcolata) {
    			case "1":
    				do {
    					errore = false;
    					System.out.println("Quanti posti vuoi prenotare?");
    					sceltaPosti = scan_line.nextLine();
    					
    					try {
    						numeroPostiUtente = Integer.parseInt(sceltaPosti);
    						
    					} catch (NumberFormatException err) {
    						errore = true;
    						System.out.println("Errore! Devi inserire un numero! Riprova... ");
    					}
    					
    				} while(errore);
    				
    				System.out.println(this.prenota(numeroPostiUtente));
    				break;
    				
    			case "2":
    				do {
    					errore = false;
    					System.out.println("Quanti posti vuoi disdire?");
    					sceltaPosti = scan_line.nextLine();
    					try {
    						numeroPostiUtente = Integer.parseInt(sceltaPosti);
    					}catch(NumberFormatException err) {
    						errore = true;
    						System.out.println("Errore! Devi inserire un numero! Riprova... ");
    					}
    				}while(errore);
    				 //Sono sicuro che numeroPostiUtente è un intero
    				System.out.println(this.disdici(numeroPostiUtente));
    				break;
    				
    			case "3":
    				ripeti = false;
    				break;
    		}
    		
    		System.out.println(this.toString());
        } while(ripeti);
        
        //scan_int.close();
        System.out.println("Operazioni terminate, grazie!");
	
	}
	
	
		
	@Override
    public String toString() {
        return this.getTipologia() + ":" + '\n' +
                "titolo='" + this.getTitolo() + '\'' + '\n' +
                "data='" + this.getDataFormattata() + '\'' + '\n' +
                "PostiPrenotati=" + this.getPostiPrenotati() + '\n' +
                "PostiTotali=" + this.getPostiTotlali() + '\n';
    }


}
