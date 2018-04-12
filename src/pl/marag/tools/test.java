package pl.marag.tools;

import java.math.BigDecimal;

public class test {

	public static void main(String[] args) {
		BigDecimal kwota = new BigDecimal(521125.456);
		System.out.println( Kwota.slownie(kwota) );
	}

//	private static void KwotaSlownie(BigDecimal kwota) {
//		int lewa;
//		int prawa;
//		
//		kwota = kwota.setScale(2,BigDecimal.ROUND_HALF_DOWN);
//		
//		lewa = kwota.intValue();
//		prawa = kwota
//				.remainder(BigDecimal.ONE)
//				.movePointRight(2)
//				.intValue();
//		
//		System.out.println("Kwota: " + kwota);
//		System.out.println("Lewa: " + lewa);
//		System.out.println("Prawa: " + prawa);
//	}

}
