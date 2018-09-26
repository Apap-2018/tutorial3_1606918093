package com.apap.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial3.model.PilotModel;
import com.apap.tutorial3.service.PilotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/pilot/add")
	public String add(@RequestParam(value = "id", required = true) String id,
					  @RequestParam(value = "licenseNumber", required = true) String licenseNumber,
					  @RequestParam(value = "name", required = true) String name,
					  @RequestParam(value = "flyHour", required = true) int flyHour) {
			PilotModel pilot = new PilotModel(id, licenseNumber, name, flyHour);
			pilotService.addPilot(pilot);
			return "add";
	}
	
	@RequestMapping("/pilot/view")
	public String view(@RequestParam("licenseNumber") String licenseNumber, Model model) {
		PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		
		model.addAttribute("pilot", archive);
		return "view-pilot";
	}
	
	@RequestMapping("/pilot/viewall")
	public String viewall(Model model) {
		List<PilotModel> archive = pilotService.getPilotList();
		
		model.addAttribute("listPilot", archive);
		return "viewall-pilot";
	}
	
	@RequestMapping(value= {"/pilot/view/license-number","/pilot/view/license-number/{licenseNumber}"})
	public String viewWithPathVariable(@PathVariable Optional<String> licenseNumber, Model model) {
		if(licenseNumber.isPresent()) {
			PilotModel archive = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
			if(archive!=null) {
				model.addAttribute("licenseNumber", licenseNumber.get());
				return "viewWithPathVariable";
			} else {
				model.addAttribute("errorType", "ID Not Found!");
			}
		} else {
			model.addAttribute("errorType", "Forbidden Empty ID");
		} 
		return "error-display";
	}
	
	@RequestMapping(value = {"/pilot/update/license-number", "/pilot/update/license-number/{licenseNumber}/fly-hour/{flyHour}"})
	public String updateFlyHour(@PathVariable Optional<String> licenseNumber, 
								@PathVariable Optional<Integer> flyHour, Model model) {
		if(licenseNumber.isPresent()) {
			model.addAttribute("message", null);
			PilotModel pilots = pilotService.getPilotDetailByLicenseNumber(licenseNumber.get());
			if (pilots != null) {
				pilots.setFlyHour(flyHour.get());
				model.addAttribute("message", "Data Saved");
				model.addAttribute("pilot", pilots);
				return "updateFlyHour";
			} else {
				model.addAttribute("errorType","ID Not Found, Update Failed");
			}
		} else {
			model.addAttribute("errorType", "Forbidden Empty ID");
		}
		return "error-display";
	}
	
	@RequestMapping(value = {"/pilot/delete","pilot/delete/id/{id}"})
	public String deletePilot(@PathVariable Optional<String> id, Model model) {
		if (id.isPresent()) {
			String pilots = pilotService.deletePilot(id.get());
			if (pilots!= null) {
				model.addAttribute("message", "Data Deleted");
				model.addAttribute("pilotList", pilotService.getPilotList());
				return "deletePilot";
			} else {
				model.addAttribute("errorType","ID Not Found, Delete Failed");
			} 
		} else {
			model.addAttribute("errorType", "Forbidden Empty ID");
		}
		return "error-display";
	}
	

}
