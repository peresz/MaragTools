package pl.marag.tools;

import java.math.BigDecimal;

public class Kwota {

	public static String slownie(BigDecimal kwota) {
		int lewa;
		int prawa;
		String slownie;

		kwota = kwota.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		lewa = kwota.intValue();
		prawa = kwota.remainder(BigDecimal.ONE).movePointRight(2).intValue();

		System.out.println("Kwota: " + kwota);
		System.out.println("Lewa: " + lewa);
		System.out.println("Prawa: " + prawa);

		slownie = lewa == 0 ? "zero złotych" : getText(lewa, true);
		slownie += " ";
		slownie += prawa == 0 ? "zero złotych" : getText(prawa, false);
		
		return slownie;
	}

	private static String getText(int wart, boolean leftOrRight) {
		String[][] t2 = new String[6][5];

		String[] jeden = { "grosz", "złoty", "tysiąc", "milion", "bilion" };
		String[] dwa = { "grosze", "złote", "tysiące", "miliony", "biliony" };
		String[] piec = { "groszy", "złotych", "tysięcy", "milionów", "bilionów" };

		t2[1] = jeden;
		t2[2] = dwa;
		t2[5] = piec;
		
		int alen;
		int przedzial;
		int wykl;
		int liczba;
		String liczbaTxt;
		int ostatnia;
		int dwieOstatnie;
		int ot = -1;

		StringBuilder tekst = new StringBuilder();

		while (true) {
			alen = Integer.toString(wart).length();
			System.out.println("alen = " + alen);

			przedzial = (alen + 2) / 3;
			wykl = (przedzial * 3) - 3;
			liczba = wart / (int) Math.pow(10., (double) wykl);
			liczbaTxt = String.valueOf(liczba);
			
			tekst.append( trzy(liczba) );
			
			ostatnia = Integer.parseInt( liczbaTxt.substring( liczbaTxt.length() - 1 ) );
			dwieOstatnie = Integer.parseInt( liczbaTxt.substring( liczbaTxt.length() - 2 ) );
			
			if ( (ostatnia <= 1) || (ostatnia >= 5) )  ot = 5;
			if ( (ostatnia == 1) && (liczba == 1) )  ot = 1;
			if ( (ostatnia >= 2) && (ostatnia <= 4) )  ot = 2;
			if ( (dwieOstatnie > 10) && (dwieOstatnie < 20) )  ot = 5;
			if ( przedzial > 1 )  tekst.append( t2[ot][przedzial] + " " );
			
			wart = wart - (liczba * (int) Math.pow(10., (double) wykl));
			if (wart == 0) {
				przedzial = leftOrRight ? 1 : 0;
				if (liczba == 1 && wykl > 0) {
					ot = 5;
				}
				tekst.append( t2[ot][przedzial] + " " );
				
				System.out.println("przedzial = " + przedzial);
				System.out.println("wykladnik: " + wykl);
				System.out.println("lizba: " + liczba);
				System.out.println("ostatnia: " + ostatnia);
				System.out.println("dwieOstatnie: " + dwieOstatnie);
				System.out.println(tekst.toString());
				break;
			}
		}
		return tekst.toString();
	}

	private static String trzy(int val) {
		int liczbaSetek;
		int liczbaDziesiatek;
		int liczbaJednosci;
		StringBuilder slowo = new StringBuilder();
	
		String[][] t1 =
			{ 	{"jeden", null, "sto"},
				{"dwa", "dwadzieścia", "dwieście"},
				{"trzy", "trzydzieści", "trzysta"},
				{"cztery", "czterdzieści", "czterysta"},
				{"pięć", "pięćdziesiąt", "pięćset"},
				{"sześć", "sześćdziesiąt", "sześćset"},
				{"siedem", "siedemdziesiąt", "siedemset"},
				{"osiem", "osiemdziesiąt", "osiemset"},
				{"dziewięć", "dziewięćdziesiąt", "dziewięćset"},
				{"dziesięć"},
				{"jedenaście"},
				{"dwanaście"},
				{"trzynaście"},
				{"czternaście"},
				{"piętnaście"},
				{"szesnaście"},
				{"siedemnaście"},
				{"osiemnaście"},
				{"dziewiętnaście"}
			};
		
		if (val >= 100) {
			liczbaSetek = val / 100;
			slowo.append( t1[liczbaSetek - 1][2] + " " );       //3 znaczy setki
	        val = val - liczbaSetek * 100;
		}
		if (val < 100 && val > 0) {
			if (val < 20) {
				slowo.append( t1[val-1][0] + " " );			// 0 znaczy jedności
			} else {
				liczbaDziesiatek = val / 10;
				liczbaJednosci = val - liczbaDziesiatek * 10;
				slowo.append( t1[liczbaDziesiatek - 1][1] + " " );
				if (liczbaJednosci > 0) {
					slowo.append( t1[liczbaJednosci - 1][0] + " " );
				}
			}
		}
		
		return slowo.toString();
	}
}
