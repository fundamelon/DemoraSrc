package misc;

public class test {
	public static void main(String[] args){
		System.out.println(comma(300023000));
	}
		
	public static String comma(int x) {
		if (x<1000){
			return x+ "";
		} else {
			String out = (x - (int)(x/1000)*1000)+"";
			while(out.length()<3) out = "0"+out;
			return comma(x/1000)+"," + out;
		}
	}

}
