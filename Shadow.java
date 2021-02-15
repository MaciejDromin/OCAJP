public class Shadow{
	int x = 4;
	static int y = 2;
	public static void main(String... args){
		int x = 2;
		int y = 3;
		System.out.println(x);
		System.out.println(y);
		Shadow s = new Shadow();
		System.out.println(s.x);
		System.out.println(Shadow.y);
	}
}
