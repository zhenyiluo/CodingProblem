public class Solution{
    public static void main(String[] args){
        String s = "4aaa5bbbbccccc5";
        System.out.println("Original string: " + s);
        System.out.println("Original string length: " + s.length());
        String encoded = encode(s);
        System.out.println("Encoded String: " + encoded);
        System.out.println("Encoded String length: " + encoded.length());
        String decoded = decode(encoded);
        System.out.println("Decoded String: " + decoded);
        System.out.println("Decoded String length: " + decoded.length());
    }


    public static String encode(String s){
        StringBuilder sb = new StringBuilder();
        int countSameChars = 0;
        char prev = '#';
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                if(countSameChars > 3){
                    sb.append("" + countSameChars + 'x' + prev);
                }else if(countSameChars != 0){
                    for(int j = 0; j < countSameChars; j++){
                        sb.append(prev);
                    }
                }
                countSameChars = 0;
                sb.append("" + c + '\\');
            }else if( c != prev){
                if(countSameChars > 3){
                    sb.append("" + countSameChars + 'x' + prev);
                }else{
                    for(int j = 0; j < countSameChars; j++){
                        sb.append(prev);
                    }
                }
                countSameChars = 1;

            }else{
                countSameChars ++;
            }

            prev = c;
        }

        if(countSameChars > 3){
            sb.append("" + countSameChars + 'x' + prev);
        }else{
            for(int i = 0; i < countSameChars; i++){
                sb.append(prev);
            }
        }
        return sb.toString();
    }

    public static String decode(String s){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                if(s.charAt(i+1) == '\\'){
                    sb.append(c);
                    i = i+2;
                }else{
                    int val = s.charAt(i) - '0';
                    while(s.charAt(i+1) >= '0' && s.charAt(i+1) <= '9'){
                        i++;
                        val = val * 10 + s.charAt(i) - '0';
                    }
                    i++;
                    if(i >= s.length() || s.charAt(i) != 'x'){
                        return "Something is Wrong";
                    }

                    i++;

                    if(i>= s.length() || !( s.charAt(i) >= 'a' && s.charAt(i) <= 'z')){
                        return "Something is Wrong";
                    }
                    for(int j = 0; j < val; j++){
                        sb.append(s.charAt(i));
                    }
                    i++;
                }
            }else{
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }
}
