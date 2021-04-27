package collections.hashmap;

import java.util.HashMap;
import java.util.Random;

/**
 * Essa classe mostra um jeito de procurar por um objeto com atributos já
 * conhecidas dentro de um hashmap de objetos. Os atributos conhecidos
 * devem ser os que são usados no hashcode e do equals do objeto. Logo,
 * conceitualmente, já sabemos o que distinguiria esse objeto específico dos
 * outros mas não sabemos se ele está presente em uma coleção de objetos.
 * 
 * @author Daniel
 *
 */
public class TesteHashMap {

	private static int GENERATED_CARS = 15;
	private static Random random;

	public static void main(String[] args) {
		
		random = new Random();
		HashMap<Car, Car> hashCarros = geraHashObjetosCarro();
		Car carroProcurado = simpleCarroSample();
		procurarCarro(carroProcurado,hashCarros);

	}

	private static void procurarCarro(Car searchedCar, HashMap<Car, Car> hashCarros) {
		System.out.println("----Carro procurado----");
		System.out.println(searchedCar);
		if(hashCarros.get(searchedCar)!=null){
			System.out.println("Carro existe");
		}else {
			System.out.println("Carro não existe");
		}
		System.out.println("Número de comparações: " + Car.vezesEquals);
		Car.vezesEquals = 0;
	}

	private static HashMap<Car, Car> geraHashObjetosCarro() {
		HashMap<Car, Car> hashCarros = new HashMap<>();
		
		System.out.println("----Carros Gerados----");
		for (int i = 0; i < GENERATED_CARS; i++) {
			Car novoCarro = completeCarroSample();
			System.out.println(novoCarro);
			hashCarros.put(novoCarro, novoCarro);
		}
		
		return hashCarros;
	}

	private static Car simpleCarroSample() {
		String fabricante = getManufactorer();
		int chassi = random.nextInt(5);
		int placa = random.nextInt(5);
		Car newCar = new Car(fabricante);
		newCar.setChassisCode(String.valueOf(chassi));
		newCar.setLicensePlate(String.valueOf(placa));
		return newCar;
	}

	private static Car completeCarroSample() {
		String fabricante = getManufactorer();

		int chassi = random.nextInt(5);
		int placa = random.nextInt(5);
		int horsePower = random.nextInt(7000);
		String modelo = getModel();
		int assentos = random.nextInt(8);
		
		Car newCar = new Car(fabricante);
		newCar.setChassisCode(String.valueOf(chassi));
		newCar.setLicensePlate(String.valueOf(placa));
		newCar.setSeats(assentos);
		newCar.setModel(modelo);
		newCar.setHorsePower(horsePower);
		return newCar;
	}

	private static String getManufactorer() {
		String[] manufactorers = { "Fiat", "Volkswagen", "Ferrari", "Renault", "Volvo", "Mitsubishi", "Toyota", "Hyundai",
				"Ford", "Nissan", "Peugeot", "Honda", "Suzuki" };
		return manufactorers[random.nextInt(manufactorers.length)];
	}
	
	private static String getModel() {
		String[] model = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		return model[random.nextInt(model.length)];
	}
}
