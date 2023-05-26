package io.github.memory_of_snow.Bech32Converter;

import org.apache.commons.codec.binary.Hex;
import org.bitcoinj.core.Bech32;

public class MinimalUse {

    public static void main(String[] args){

        operationConfirmation();

    }

    private static void operationConfirmation() {
        StringBuilder outputStringBuiler = new StringBuilder();

        final byte[] CHARSET_REV = {
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                15, -1, 10, 17, 21, 20, 26, 30, 7, 5, -1, -1, -1, -1, -1, -1,
                -1, 29, -1, 24, 13, 25, 9, 8, 23, -1, 18, 22, 31, 27, 19, -1,
                1, 0, 3, 16, 11, 28, 12, 14, 6, 4, 2, -1, -1, -1, -1, -1,
                -1, 29, -1, 24, 13, 25, 9, 8, 23, -1, 18, 22, 31, 27, 19, -1,
                1, 0, 3, 16, 11, 28, 12, 14, 6, 4, 2, -1, -1, -1, -1, -1
        };

        int boxsize = CHARSET_REV.length;

        byte[] ContrastTable = new byte[boxsize];

        byte target = 0;
        for(int i = 0; i < boxsize ; i++){
            ContrastTable[i]=target;
            target++;
        }


        for(int i = 0 ; i < boxsize ; i++ ){
            outputStringBuiler.append("index:").append(i).append(" ");
            outputStringBuiler.append("byte:").append(ContrastTable[i]).append(" ");
            outputStringBuiler.append("char:").append(String.valueOf((char)ContrastTable[i])).append(" ");
            outputStringBuiler.append("target:").append(CHARSET_REV[i]).append("\n");
        }

        System.out.println(outputStringBuiler);
    }

    private static void convertBech32ToBech32Data() {
        String publicKey = "npub17x6pn22ukq3n5yw5x9prksdyyu6ww9jle2ckpqwdprh3ey8qhe6stnpujh";

        System.out.println("input publicKey = " + publicKey );

        Bech32.Bech32Data decordData = Bech32.decode(publicKey);

        String hrp = decordData.hrp;

        System.out.println("hrp = " + hrp);
        System.out.println("encord = " + decordData.encoding);
        System.out.println("dataPart = " + new String(Hex.encodeHex(decordData.data)));
    }
}
