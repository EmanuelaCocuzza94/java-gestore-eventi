package org.javaGestoreEventi;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.LocalDate;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;



public class Concerto extends Evento {
	
	private LocalTime ora;
	private double prezzo;
	
	
	public Concerto(String titolo, LocalDate data, int postiTotale, LocalTime ora, double prezzo) {
		super(titolo, data, postiTotale);
		this.setOra(ora);
		this.setPrezzo(prezzo);
		this.setTipologia("Concerto");
		
	}
	
	public LocalTime getOra() {
		return this.ora;
	}



	public void setOra(LocalTime ora) {
		this.ora = ora;
	}
	
	public double getPrezzo() {
		return this.prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	
	
	
	public String getOraFormattata(LocalTime orario) {
		 
		DateTimeFormatter formatoOra = DateTimeFormatter.ofPattern("HH:mm");// Modifica il pattern come desideri
        
		return orario.format(formatoOra);
	}
	
	public String getPrezzoFormattato(double prezzo) {
		
		NumberFormat formatoEuro = NumberFormat.getCurrencyInstance(Locale.ITALY);
		
        return formatoEuro.format(prezzo) ;
	}
	
	@Override
    public String toString() {
		return super.toString() +
                "orario='" + this.getOraFormattata(this.getOra()) + '\'' + '\n' +
				"prezzo='" + this.getPrezzoFormattato(this.getPrezzo()) + '\'' + '\n';
    }

}
