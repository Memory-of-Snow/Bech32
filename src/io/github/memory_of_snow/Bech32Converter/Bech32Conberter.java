package io.github.memory_of_snow.Bech32Converter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bech32Conberter {

    static private Logger log = Logger.getLogger("Bech32Conberter");
    static private final String publicKeyBech32String = "npub17x6pn22ukq3n5yw5x9prksdyyu6ww9jle2ckpqwdprh3ey8qhe6stnpujh";

    //Bech32における人間の可読部(HRP:human-readable part)とデータ部分(data part)の区切り文字(separator)、常に"1"
    static private final String HRP_NPUB_LOWER_CASE = "npub";
    static private final String HRP_NSEC_LOWER_CASE = "nsec";
    static private final String HRP_NOTE_LOWER_CASE = "note";
    static public String BECH32SEPARETOR = "1";

    public String ConvertBech32toHex (String inputString){

        if(isValidBech32NostrKeyOrNote(inputString)){

        }

        return "";
    }

    static public boolean isValidBech32NostrKeyOrNote(String inputString ){

        if(inputString == null){
            log.log(Level.FINE,"input is null.");
            return false;
        };

        //許容される最大は90文字
        if(inputString.length() > 90){
            log.log(Level.FINE,"input is too long.");
            return false;
        }

        //接頭語は指定のみ、次のセパレーター1は確定
        //区切り文字1文字・データ部分6文字以上
        String hrp = inputString.substring(0,4);
        String separator = inputString.substring(4,5);
        String dataPart = inputString.substring(5);

        if(!hrp.equals(HRP_NOTE_LOWER_CASE) && !hrp.equals(HRP_NPUB_LOWER_CASE) && !hrp.equals(HRP_NSEC_LOWER_CASE)
                && !hrp.equals(HRP_NOTE_LOWER_CASE.toUpperCase()) && !hrp.equals(HRP_NPUB_LOWER_CASE.toUpperCase()) && !hrp.equals(HRP_NSEC_LOWER_CASE.toUpperCase())){
            log.log(Level.FINE,"hrp is wrong.");
            return false;
        }

        if(!separator.equals(BECH32SEPARETOR)){
            log.log(Level.FINE,"separator is wrong.");
            return false;
        }

        if(dataPart.length() < 6){
            log.log(Level.FINE,"dataPart is too short.");
            return false;
        }

        boolean lowCaseStrings = false;
        boolean upperCaseStrings = false;

        //大文字統一、小文字統一、
        for (int i = 0 ; i < dataPart.length() ; i++){

            char c = dataPart.charAt(i);

            if ( c < 33 || c > 126){
                log.log(Level.FINE,"the value contains an invalid character.");
                return false;
            }

            // "1bio"は使用しない
            if ( c == '1' || c == 'b' || c == 'B' || c == 'i' || c == 'I' || c == 'o' || c == 'O') {
                log.log(Level.FINE,"the value contains an invalid character.");
                return false;
            }

            if ( Character.isLowerCase(c) && Character.isLetter(c)){
                lowCaseStrings = true;
            }else if ( Character.isUpperCase(c) && Character.isLetter(c)){
                upperCaseStrings = true;
            }

        }

        if(lowCaseStrings == upperCaseStrings){
            log.log(Level.FINE,"the value contains mixed case.");
            return false;
        }

        return true;
    }

}
