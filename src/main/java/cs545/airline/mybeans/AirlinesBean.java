package cs545.airline.mybeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.sound.midi.Soundbank;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

@ManagedBean(name="airlinesBean")
@RequestScoped
public class AirlinesBean implements Serializable {

	private static final long serialVersionUID = -6259680001930071718L;
	
	 private boolean showDo=true;
	 private boolean showUndo=false;
	
	@Inject
	private AirlineService airlineService;
	
	public String createAirline(String name) {
		System.out.println(">>>>>>>Creating new airline with name: ");
		Airline airline = new Airline();
		airline.setName(name);
		airlineService.create(airline);
		return "airlineslist?faces-redirect=true";
		//undoAction();
	}
	
	public List<Airline> findAll() {
		return airlineService.findAll();
	}
	
	public void doAction() {
		showUndo = true;
		showDo=false;
	}

	public void undoAction() {
		showUndo = false;
		showDo= true;
	}

	public void deleteAirline(long id){
		Airline airline=new Airline();
		airline.setId(id);
		airlineService.delete(airlineService.find(airline));
	}

	public boolean isShowDo() {
		return showDo;
	}

	public void setShowDo(boolean showDo) {
		this.showDo = showDo;
	}

	public boolean isShowUndo() {
		return showUndo;
	}

	public void setShowUndo(boolean showUndo) {
		this.showUndo = showUndo;
	}
	
	

}
