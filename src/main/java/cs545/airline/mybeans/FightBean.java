package cs545.airline.mybeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Flight;
import cs545.airline.service.FlightService;

@ManagedBean(name="fightBean")
@RequestScoped
public class FightBean {
	
	@Inject
	FlightService flightService;
	
//	List<Flight> flights = new ArrayList<>();
	private List<Flight> filteredFlightList;
	
	
	
	public List<Flight> findAllFlights() {
		System.out.println(">>>>>>>>SIZEEEEEEEEE: "+flightService.findAll().size());
		return flightService.findAll();
	}
	
	public  List<Flight> getFilteredFlightList() {
        return filteredFlightList;
    }

    public void setFilteredFlightList(List<Flight> filteredFlightList) {
        this.filteredFlightList = filteredFlightList;
    }

}
