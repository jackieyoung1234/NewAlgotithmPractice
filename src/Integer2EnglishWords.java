public class Integer2EnglishWords {
    private static String[] TW = new String[]{
            "Twenty ",
            "Thirty ",
            "Forty ",
            "Fifty ",
            "Sixty ",
            "Seventy ",
            "Eighty ",
            "Ninety "
    };
    private static String[] TE = new String[]{
            "Ten ",
            "Eleven ",
            "Twelve ",
            "Thirteen ",
            "Fourteen ",
            "Fifteen ",
            "Sixteen ",
            "Seventeen ",
            "Eighteen ",
            "Nineteen "
    };
    private static String[] ONE = new String[]{
            "One ",
            "Two ",
            "Three ",
            "Four ",
            "Five ",
            "Six ",
            "Seven ",
            "Eight ",
            "Nine "
    };
    public String numberToWords(int num) {
        if(num==0) return "Zero";
        StringBuilder res = new StringBuilder();
        while(num>0){
            if(num>=1_000_000_000){
                res.append(numberOf3digits(num/1_000_000_000)+"Billion ");
                num%=1_000_000_000;
            }else if(num>=1_000_000){
                res.append(numberOf3digits(num/1_000_000)+"Million ");
                num%=1_000_000;
            }else if(num>=1_000){
                res.append(numberOf3digits(num/1_000)+"Thousand ");
                num%=1_000;
            }else{//num>0
                res.append(numberOf3digits(num));
                num=0;
            }
        }
        //**remove last space
        if(res.charAt(res.length()-1)==' ')
            res.deleteCharAt(res.length()-1);
        return res.toString();
    }
    private String numberOf3digits(int num){
        StringBuilder res = new StringBuilder();
        int j = 0;
        while(num>0){
            if(num>=100){
                j = num/100-1;
                res.append(ONE[j]+"Hundred ");
                num%=100;
            }else if(num>=20){
                j = num/10-2;
                res.append(TW[j]);
                num%=10;
            }else if(num>=10){
                res.append(TE[num-10]);
                num = 0;
            }else{//>0
                res.append(ONE[num-1]);
                num=0;
            }
        }
        return res.toString();
    }
    public static void main(String[] args){
        Integer2EnglishWords i =  new Integer2EnglishWords();
        String res = i.numberToWords(123);
        res = i.numberToWords(12345);
        res = i.numberToWords(1234567);
        res = i.numberToWords(1234567891);
    }
}
