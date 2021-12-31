package model.carManagement;

public class carManagementDTO {
     private String carNumber;
     private String carSort;
     private String carName;
     private int rentPerDay;
     
	public carManagementDTO(String carNumber, String carSort, String carName, int rentPerDay) {
		this.carNumber = carNumber;
		this.carSort = carSort;
		this.carName = carName;
		this.rentPerDay = rentPerDay;
	}

	public String getCarNumber() { return carNumber; }

	public void setCarNumber(String carNumber) { this.carNumber = carNumber; }

	public String getCarSort() { return carSort; }

	public void setCarSort(String carSort) { this.carSort = carSort; }

	public String getCarName() { return carName; }

	public void setCarName(String carName) { this.carName = carName; }

	public int getRentPerDay() { return rentPerDay; }

	public void setRentPerDay(int rentPerDay) { this.rentPerDay = rentPerDay; }
	
}
