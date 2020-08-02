package com.alerouge.kyivent.utility;

// Lascia perdere sta roba usa le funzioni di apache oppure quelle di spring
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