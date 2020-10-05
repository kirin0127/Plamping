import com.google.gson.*;

public class JavaTest {
	public static void main(String[] args) {
		Car car01 = new Car("BMW", "sedan", 1000);
		Car car02 = new Car();
		String json = new Gson().toJson(car01);
		JsonObject jobj = JsonParser.parseString(json).getAsJsonObject();
		System.out.println(jobj.get("brand"));
		System.out.println(car01.brand.isEmpty());
		System.out.println(car02.brand == null);
		int[] arr = new int[6];
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
}

class Car{
	String brand;
	String type;
	int price;
	Car(){}
	Car(String bb, String tt, int pp){
		this.brand = bb;
		this.type = tt;
		this.price = pp;
	}
}
