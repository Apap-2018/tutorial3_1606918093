package com.apap.tutorial3.service;

import java.util.ArrayList;
import java.util.List;
import com.apap.tutorial3.model.PilotModel;
import org.springframework.stereotype.Service;

@Service 
public class PilotInMemoryService implements PilotService {
	private List<PilotModel> archivePilot;
	
	public PilotInMemoryService() {
		archivePilot = new ArrayList<>();
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		archivePilot.add(pilot);
	}
	
	@Override
	public List<PilotModel> getPilotList(){
		return archivePilot;
	}
	
	//4. implementasi method getDetailPilotByLicenseNumber
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		for (int i=0; i < archivePilot.size() ; i++) {
			PilotModel findPilot = archivePilot.get(i);
			if(findPilot.getLicenseNumber().equals(licenseNumber)) {
				return findPilot;
			}
		}
		return null;
	}

	@Override
	public String deletePilot(String id) {
		// TODO Auto-generated method stub
		int inew = 0;
		for(int x = 0 ; x < archivePilot.size(); x++) {
			PilotModel pilots = archivePilot.get(x);
			if (pilots.getId().equals(id)) {
				inew = x;
				archivePilot.remove(x);
				return id;
			}
		}
		return null;
	}
}
