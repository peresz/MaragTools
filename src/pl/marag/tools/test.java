package pl.marag.tools;

import java.math.BigDecimal;

public class test {

	public static void main(String[] args) {
		System.out.println( Kwota.slownie( 0 ) );
		System.out.println( Kwota.slownie( 8.08 ) );
		System.out.println( Kwota.slownie( new BigDecimal(34.28) ) );
		System.out.println( Kwota.slownie( 168.32 ) );
		System.out.println( Kwota.slownie( "1248,96" ) );
		System.out.println( Kwota.slownie( "13765,28" ) );
	}

}
