import com.google.gson.*;

public class JavaTest {
	public static void main(String[] args) {
		Car car01 = new Car("BMW", "sedan", 1000);
		String json = new Gson().toJson(car01);
		JsonObject jobj = JsonParser.parseString(json).getAsJsonObject();
		System.out.println(jobj.get("brand"));
		
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
