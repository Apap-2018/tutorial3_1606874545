package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;

import com.apap.tutorial3.model.CarModel;

import org.springframework.stereotype.Service;

@Service
public class CarInMemoryService implements CarService {
	private List<CarModel> archiveCar;
	
	
	public CarInMemoryService() {
		archiveCar = new ArrayList<>();
		//DATA DUMMY
		CarModel car=new CarModel("1", "honda", "sedan", 10000, 100000);
		archiveCar.add(car);
		
		CarModel car2=new CarModel("2", "mitsubishi", "SUV", 9999, 90000000);
		archiveCar.add(car2);
		
		CarModel car3=new CarModel("3", "mercedes", "coupe", 88888, 88888000);
		archiveCar.add(car3);
		
		//
		
	}
	
	@Override
	public void addCar(CarModel car) {
		// TODO Auto-generated method stub
		archiveCar.add(car);
		
	}

	@Override
	public List<CarModel> getCarList() {
		// TODO Auto-generated method stub
		return archiveCar;
	}

	@Override
	public CarModel getCarDetail(String id) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<archiveCar.size(); i++) {
			String loopCar = archiveCar.get(i).getId();
			if (loopCar.equalsIgnoreCase(id)) {
				return archiveCar.get(i);
			}
		}
		return null;
	}

}
