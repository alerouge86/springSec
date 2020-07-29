package com.alerouge.kyivent.utility;

/**
 * Gestione utility per la gestione stringhe
 * @author Alessandro Rossi
 * @since 04 nov 2019
 * @version 2.0
 */
public class UtiStringa {

	private UtiStringa(){
		super();
	}

	public static String trimNotNull( String str ){
		if ( str !=null ){
			str = str.trim();
		}else{
			str = "";
		}
		return str;
	}

	public static boolean strVoid( String str){
		boolean strNulla = true;
		if(str!=null && !str.equals("")){
			strNulla = false;
		}
		return strNulla;
	}

}