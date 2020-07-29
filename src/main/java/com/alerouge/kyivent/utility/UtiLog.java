package com.alerouge.kyivent.utility;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

/**
 * Gestione utility per log
 */
public class UtiLog {
	
	private UtiLog(){
		/* construttore privato */
	}

	/**
	 * Gestione eccezione. Ricava echotoken errore da visualizzare a video e lo aggiunge nello stack trace dei log
	 */
	public static String gestioneEccezione(Logger log, Exception e, long idUtente) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String echoTokenError = String.valueOf(idUtente)+"-"+String.valueOf(UtiData.timeStamp());
		log.error("\nCodice errore: " + echoTokenError + " :\n" + sw.toString());
		return echoTokenError;
	}

}
