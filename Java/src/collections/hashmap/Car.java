package collections.hashmap;

public class Car {
	private int horsePower;
	private String manufacturer;
	private String licensePlate;
	private int seats;
	private String chassisCode;
	private String model;
	public static int vezesEquals=0;
	
	

	public Car(String manufacturer) {
		super();
		this.manufacturer = manufacturer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chassisCode == null) ? 0 : chassisCode.hashCode());
		result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		vezesEquals++;
		if (this == obj)
			return true;
		if (!(obj instanceof Car))
			return false;
		Car other = (Car) obj;
		if (chassisCode == null) {
			if (other.chassisCode != null)
				return false;
		} else if (!chassisCode.equals(other.chassisCode))
			return false;
		if (licensePlate == null) {
			if (other.licensePlate != null)
				return false;
		} else if (!licensePlate.equals(other.licensePlate))
			return false;
		return true;
	}
	


	@Override
	public String toString() {
		return "Car [manufacturer=" + manufacturer + ", license plate=" + licensePlate + ", chassis code=" + chassisCode + ", model="
				+ model + "]";
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getChassisCode() {
		return chassisCode;
	}

	public void setChassisCode(String chassisCode) {
		this.chassisCode = chassisCode;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
}
