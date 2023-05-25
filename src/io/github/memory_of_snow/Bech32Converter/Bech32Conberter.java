package io.github.memory_of_snow.Bech32Converter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bech32Conberter {

    static private Logger log = Logger.getLogger("Bech32Conberter");
    static private String publicKeyBech32String = "npub17x6pn22ukq3n5yw5x9prksdyyu6ww9jle2ckpqwdprh3ey8qhe6stnpujh";

    //Bech32における人間の可読部(HRP:human-readable part)とデータ部分(data part)の区切り文字(separator)、常に"1"
    static private String HRP_NPUB_LOWER_CASE = "npub";
    static private String HRP_NSEC_LOWER_CASE = "nsec";
    static private String HRP_NOTE_LOWER_CASE = "note";
    static public String BECH32SEPARETOR = "1";

    public String ConvertBech32toHex (String inputString){

        if(isBech32Valid(inputString)){

        }

        return "";
    }

    private boolean isBech32Valid(String inputString ){

        if(inputString == null){
            log.log(Level.fine,"input is null.");
            return false;
        };

        //許容される最大は90文字、区切り文字1文字・データ部分6文字以上
        if(inputString.length() > 90 || inputString.length() <7){
            log.log(Level.FINE,"input is too long or small.");
            return false;
        }

        return true;
    }

}
