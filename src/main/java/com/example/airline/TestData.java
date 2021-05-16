package com.example.airline;

import com.example.airline.dao.AirRepository;
import com.example.airline.dto.AviaRaceDto;
import com.example.airline.model.AirCompany;
import com.example.airline.model.AirPort;
import com.example.airline.model.AviaRace;
import com.example.airline.service.AviaService;
import com.example.airline.service.CompanyService;
import com.example.airline.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TestData {

    private AviaService service;
    private PortService portService;
    private CompanyService companyService;

    @Autowired
    public TestData(AviaService service, PortService portService, CompanyService companyService) {
        this.service = service;
        this.portService = portService;
        this.companyService = companyService;
    }

    @PostConstruct
    public void populateFlights() {


        AirPort moscow = new AirPort("Moscow");
        AirPort paris = new AirPort("Paris");
        AirPort prague = new AirPort("Prague");
        AirPort london = new AirPort("London");
        AirPort vilnius = new AirPort("Vilnius");
        AirPort berlin = new AirPort("Berlin");
        AirPort amsterdam = new AirPort("Amsterdam");

        portService.addPort(moscow);
        portService.addPort(paris);
        portService.addPort(prague);
        portService.addPort(london);
        portService.addPort(vilnius);
        portService.addPort(berlin);
        portService.addPort(amsterdam);


        AirCompany aeroflot = new AirCompany("Aeroflot");
        AirCompany british = new AirCompany("BritishAir");
        AirCompany wizzAir = new AirCompany("wizzAir");
        AirCompany qatar = new AirCompany("Qatar");

        companyService.addCompany(aeroflot);
        companyService.addCompany(british);
        companyService.addCompany(wizzAir);
        companyService.addCompany(qatar);


        List<AirCompany> aeroflotSet = new ArrayList<>();
        aeroflotSet.add(aeroflot);
        List<AirCompany> britishtSet = new ArrayList<>();
        britishtSet.add(british);
        List<AirCompany> wizzairSet = new ArrayList<>();
        wizzairSet.add(wizzAir);
        List<AirCompany> qatarSet = new ArrayList<>();
        qatarSet.add(qatar);


        AviaRace moscowBerlinAeroflot = new AviaRace(aeroflotSet,portService.findByName(moscow.getName()).get(), portService.findByName(berlin.getName()).get(), 1000, 160, LocalTime.of(11, 0, 0));
        AviaRace berlinPragueAeroflot = new AviaRace(aeroflotSet,portService.findByName(berlin.getName()).get(), portService.findByName(prague.getName()).get(), 300, 140,LocalTime.of(12, 0, 0));
        AviaRace pragueAmsterdamAeroflot = new AviaRace(aeroflotSet,portService.findByName(prague.getName()).get(), portService.findByName(amsterdam.getName()).get(), 180, 95,LocalTime.of(13, 0, 0));

        AviaRace moscowParis = new AviaRace(aeroflotSet,portService.findByName(moscow.getName()).get(), portService.findByName(paris.getName()).get(), 2842, 400, LocalTime.of(11, 0, 0));
        AviaRace moscowVilniusBritish = new AviaRace(britishtSet,portService.findByName(moscow.getName()).get(), portService.findByName(vilnius.getName()).get(), 300, 120,LocalTime.of(12, 0, 0));
        AviaRace parisMoscow = new AviaRace(britishtSet,portService.findByName(paris.getName()).get(), portService.findByName(moscow.getName()).get(), 2842, 382,LocalTime.of(12, 0, 0));

        AviaRace parisPrague = new AviaRace(aeroflotSet,portService.findByName(paris.getName()).get(), portService.findByName(prague.getName()).get(), 800, 140,LocalTime.of(12, 0, 0));
        AviaRace vilniusPragueBritish = new AviaRace(britishtSet,portService.findByName(vilnius.getName()).get(), portService.findByName(prague.getName()).get(), 400, 100,LocalTime.of(13, 0, 0));
        AviaRace pragueLondonBritish = new AviaRace(britishtSet,portService.findByName(prague.getName()).get(), portService.findByName(london.getName()).get(), 2842, 80,LocalTime.of(14, 0, 0));


//        AviaRaceDto parisLondon = new AviaRaceDto("Paris", "London", 476, 70);
        AviaRace londonParis = new AviaRace(britishtSet,portService.findByName(london.getName()).get(), portService.findByName(paris.getName()).get(), 476, 92,LocalTime.of(12, 0, 0));

        AviaRace londonMoscow = new AviaRace(britishtSet,portService.findByName(london.getName()).get(), portService.findByName(moscow.getName()).get(), 2883, 600,LocalTime.of(12, 0, 0));
        AviaRace moscowLondon = new AviaRace(aeroflotSet,portService.findByName(moscow.getName()).get(), portService.findByName(london.getName()).get(), 2883, 200,LocalTime.of(12, 0, 0));

        service.addRace(parisPrague);
        service.addRace(pragueLondonBritish);

        service.addRace(moscowVilniusBritish);
        service.addRace(vilniusPragueBritish);

        service.addRace(moscowBerlinAeroflot);
        service.addRace(berlinPragueAeroflot);
        service.addRace(pragueAmsterdamAeroflot);


        service.addRace(moscowParis);
//        service.addRace(parisLondon);
        service.addRace(londonMoscow);
        service.addRace(londonParis);
        service.addRace(moscowLondon);
        service.addRace(parisMoscow);


    }


}
