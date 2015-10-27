public class Solution{
	public static void main(String[] args){
	String s = “aabc”
	System.out.println(new Solution().existPalindrome(s));
}
	public boolean existPalindrome(String s){
	if(s == null || s.length() == 0){
	return false;
}

HashMap<Character, Integer> hm = new HashMap<>();
for(int i = 0; i < s.length(); i++){
	char c = s.charAt(i);
	if(hm.contains(c)){
	hm.put(c, hm.get(c)+1);
}else{
	hm.put(c, 1);
}
}
int count = 0;
for(String key : hm.keySet()){
	if(hm.get(key) % 2 == 1){
	count ++;
}
}
if(count >= 2){
	return false;
}else{
	return true;
}
}
}
