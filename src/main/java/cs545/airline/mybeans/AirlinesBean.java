package cs545.airline.mybeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

@ManagedBean(name="airlinesBean")
@RequestScoped
public class AirlinesBean implements Serializable {

	private static final long serialVersionUID = -6259680001930071718L;
	
	@Inject
	private AirlineService airlineService;
	
	public String createAirline(String name) {
		System.out.println(">>>>>>>Creating new airline with name: ");
		Airline airline = new Airline();
		airline.setName(name);
		airlineService.create(airline);
		return "airlineslist?faces-redirect=true";

	}
	
	public List<Airline> findAll() {
		return airlineService.findAll();
	}

	public void deleteAirline(Airline airline){
		airlineService.delete(airline);
	}

	public void changeEditStatus(Airline airline) {
		airline.setEditable(!airline.isEditable());
		airlineService.update(airline);
	}

}
