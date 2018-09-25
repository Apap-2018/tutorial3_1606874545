package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial3.model.CarModel;
import com.apap.tutorial3.service.CarService;

@Controller
public class CarController {
	@Autowired
	private CarService mobilService;
	
	@RequestMapping("/car/add")
	public String add(@RequestParam(value="id", required=true) String id,
			@RequestParam(value="brand", required=true) String brand,
			@RequestParam(value="type", required=true) String type,
			@RequestParam(value="price", required=true) Long price,
			@RequestParam(value="amount", required=true) Integer amount) {
		CarModel car = new CarModel(id,brand,type,price,amount);
		mobilService.addCar(car);
		return "add";
		
	}
	@RequestMapping("/car/view")
	public String view(@RequestParam("id") String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		model.addAttribute("car",archive);
		return "view-car";
	}
	
	@RequestMapping("/car/viewall")
	public String viewall(Model model) {
		List<CarModel> archive = mobilService.getCarList();
		model.addAttribute("listCar",archive);
		model.addAttribute("message", "Daftar mobil: ");
		return "viewall-car";
	}
	
	@RequestMapping(value= {"car/view/{id}"})	 	
	public String viewById(@PathVariable String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		
		if(archive!=null) {
			model.addAttribute("car",archive);
			return "view-car";	
		}
		else {
			model.addAttribute("car",id);
			return "error-page";	
		}	
	}
	
	@RequestMapping(value= {"car/update/{id}/amount/{amountChange}"})	 	
	public String updateAmount(@PathVariable String id, @PathVariable  String amountChange, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		
		
		if(archive!=null) {
			int intAmount = Integer.parseInt(amountChange);
			archive.setAmount(intAmount);
			model.addAttribute("car",archive);
			model.addAttribute("message", "Amount Berhasil Diubah");
			return "view-car";	
		}
		else {
			model.addAttribute("car",id);
			return "error-page";	
		}	
	}
	
	@RequestMapping(value= {"car/delete/{id}"})	 	
	public String deleteCar(@PathVariable String id, Model model) {
		CarModel archive = mobilService.getCarDetail(id);
		
		if(archive!=null) {
			int idxHapus = 0;
			for(CarModel car:mobilService.getCarList()) {	
				if(car.getId().equalsIgnoreCase(id)) {
					break;
				}
				idxHapus++;
			}
			
			mobilService.getCarList().remove(idxHapus);
			List<CarModel> archiveAfterDel = mobilService.getCarList();
			model.addAttribute("listCar",archiveAfterDel);
			model.addAttribute("messagedelete", "Berhasil delete id: ");
			model.addAttribute("message", "Daftar mobil: ");
			model.addAttribute("id",id);
			return "viewall-car";	
		}
		else {
			model.addAttribute("car",id);
			return "error-page";	
		}	
	}
	
	
	
	
	
	
	
	
	
	
	

}
