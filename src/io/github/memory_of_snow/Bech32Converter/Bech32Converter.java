package io.github.memory_of_snow.Bech32Converter;

public class Bech32Converter {

    //static private final String publicKeyBech32String = "npub17x6pn22ukq3n5yw5x9prksdyyu6ww9jle2ckpqwdprh3ey8qhe6stnpujh";

    //Bech32における人間の可読部(HRP:human-readable part)とデータ部分(data part)の区切り文字(separator)、常に"1"

    /*
    // 「1bio」は使用しない
    static private final char[] AllowCharacters = {
            '0','2','3','4','5','6','7','8','9',
            'a','c','d','e','f','g','h','i','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z',

            //仮置き、2023.05.26時点では大文字を許容する
            'A','C','D','E','F','G','H','I','J','K','M','N','P','Q','R','S','T','U','V','W','X','Y','Z',
    };

     */


/*
    public String ConvertBech32toHex (String inputString){

        if(isValidBech32NostrKeyOrNote(inputString)){

        }

        return "";
    }
*/

    static public boolean isValidBech32NostrKeyOrNote(String inputString) throws RuntimeException {

        if(inputString == null){
            throw new RuntimeException("input string is null.");
        }

        //許容される最大は90文字
        if(inputString.length() > 90){
            throw new RuntimeException("input string is too long.");

        }

        //接頭語は指定のみ、次のセパレーター1は確定
        //区切り文字1文字・データ部分6文字以上
        String hrp = inputString.substring(0,4);
        String separator = inputString.substring(4,5);
        String dataPart = inputString.substring(5);

        if(!isAllowHumanReadablePart(hrp)){
            throw new RuntimeException("human-readable part is invalid.");
        }

        if(!separator.equals("1")){
            throw new RuntimeException("separator is not 1.");
        }

        if(dataPart.length() < 6){
            throw new RuntimeException("data part is not too short.");
        }

        boolean lowCaseStrings = false;
        boolean upperCaseStrings = false;

        //大文字統一、小文字統一、
        for (int i = 0 ; i < dataPart.length() ; i++){

            char c = dataPart.charAt(i);

            //文字の許される範囲は[33～126]
            if ( c < 33 || c > 126){
                throw new RuntimeException("invalid letter in data part.");
            }

            // "1bio"は使用しない
            if ( c == '1' || c == 'b' || c == 'B' || c == 'i' || c == 'I' || c == 'o' || c == 'O') {
                throw new RuntimeException("invalid letter in data part.");
            }

            if ( Character.isLowerCase(c) && Character.isLetter(c)){
                lowCaseStrings = true;
            }else if ( Character.isUpperCase(c) && Character.isLetter(c)){
                upperCaseStrings = true;
            }

        }

        //大文字か小文字統一が必要
        if(lowCaseStrings == upperCaseStrings){
            throw new RuntimeException("the value contains mixed case.");
        }

        return true;
    }

    private static boolean isAllowHumanReadablePart(String hrp) {
        return hrp.equals("npub") || hrp.equals("nsec") ||hrp.equals("note") ||
                hrp.equals("NPUB") || hrp.equals("NSEC") ||hrp.equals("NOTE");

    }

}
