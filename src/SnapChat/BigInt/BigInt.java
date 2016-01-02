public class BigInt {
   
    String num;
    public BigInt(String num){
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public BigInt add(BigInt big) {
        String num1 = num;
        String num2 = big.num;
        int len1 = num1.length();
        int len2 = num2.length();
        int minLen = Math.min(len1, len2);
        int maxLen = Math.max(len1, len2);
        StringBuilder sb1 = new StringBuilder(num1);
        StringBuilder sb2 = new StringBuilder(num2);
        sb1.reverse();
        sb2.reverse();
        StringBuilder sb = new StringBuilder();
        int carryOver = 0;
        for(int i = 0; i < minLen; i++) {
            int sum = carryOver;
            sum += sb1.charAt(i) - '0';
            sum += sb2.charAt(i) - '0';
            sb.append(sum % 10);
            carryOver = sum / 10;
        }
        for(int i = minLen; i < maxLen; i++) {
            int sum = carryOver;
            if(i < len1) {
                sum += sb1.charAt(i) - '0';
            }

            if(i < len2) {
                sum += sb2.charAt(i) - '0';
            }
            sb.append(sum % 10);
            carryOver = sum / 10;
        }
        if(carryOver != 0) {
            sb.append(carryOver);
        }
        sb.reverse();
        return new BigInt(sb.toString());
    }

    public static void main(String args[]){
        BigInt num1 = new BigInt("123");
        BigInt num2 = new BigInt("99");

        System.out.println(num1.add(num2).getNum());


        BigInt num3 = new BigInt("7");
        BigInt num4 = new BigInt("2");

        System.out.println(num4.add(num3).getNum());



    }
}
