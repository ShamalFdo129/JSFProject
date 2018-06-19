package edu.mum.cs545.ws;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;
import edu.mum.cs545.dto.FlightData;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import java.util.List;

@Named
@Path("flight")
public class FlightRest {

    @Inject
    private FlightService flightService;
    @Inject
    private AirportService airportService;
    @Inject
    private AirlineService airlineService;
    @Inject
    private AirplaneService airplaneService;

    @GET
    public List<Flight> get() {
        return flightService.findAll();
    }

    @GET
    @Path("{id}")
    public Flight get(@PathParam("id") long id) {
        Flight flight = new Flight();
        flight.setId(id);
        return flightService.find(flight);
    }

    @DELETE
    @Path("{id}")
    public void Delete(@PathParam("id") long id){
        Flight flight = new Flight();
        flight.setId(id);
        flight = flightService.find(flight);
        flightService.delete(flight);
    }

    @PUT
    @Path("{id}")
    public Flight put(@PathParam("id") long id, FlightData flightData){
        Flight flight = new Flight();
        flight.setId(id);
        flight = flightService.find(flight);

        Airplane airplane = new Airplane();
        airplane.setId(flightData.getAirplaneId());
        flight.setAirplane(airplaneService.find(airplane));

        Airline airline = new Airline();
        airline.setId(flightData.getAirlineId());
        flight.setAirline(airlineService.find(airline));

        Airport origin = new Airport();
        origin.setId(flightData.getOriginId());
        flight.setOrigin(airportService.find(origin));

        Airport destination = new Airport();
        destination.setId(flightData.getDestinationId());
        flight.setDestination(airportService.find(destination));

        flight.setArrivalTime(flightData.getArrivalTime());
        flight.setArrivalDate(flightData.getArrivalDate());
        flight.setDepartureDate(flightData.getDepartureDate());
        flight.setDepartureTime(flightData.getDepartureTime());
        flight.setFlightnr(flightData.getFlightnr());

        return flightService.update(flight);
    }

    @POST
    @Path("/create")
    public Flight postFlight( FlightData flightData) {


        Flight flight = new Flight(
                flightData.getFlightnr(),
                flightData.getDepartureDate(),
                flightData.getDepartureTime(),
                flightData.getArrivalDate(),
                flightData.getArrivalTime()
        );

        Airport origin = new Airport();
        origin.setId(flightData.getOriginId());
        flight.setOrigin(airportService.find(origin));

        Airport destination = new Airport();
        destination.setId(flightData.getDestinationId());
        flight.setDestination(airportService.find(destination));

        Airplane airplane = new Airplane();
        airplane.setId(flightData.getAirplaneId());
        flight.setAirplane(airplaneService.find(airplane));

        Airline airline=new Airline();
        airline.setId(flightData.getAirlineId());
        flight.setAirline(airlineService.find(airline));

        flightService.create(flight);
        return flight;
    }
}
