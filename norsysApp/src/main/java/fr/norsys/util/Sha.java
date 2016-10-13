package fr.norsys.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha {

    public static String hash256( final String data ) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance( "SHA-256" );
        md.update( data.getBytes() );
        return bytesToHex( md.digest() );
    }

    public static String bytesToHex( final byte[] bytes ) {
        final StringBuffer result = new StringBuffer();
        for ( final byte byt : bytes )
            result.append( Integer.toString( ( byt & 0xff ) + 0x100, 16 ).substring( 1 ) );
        return result.toString();
    }
}
