package com.example.airline.service;

import com.example.airline.dao.AirRepository;
import com.example.airline.dao.CompanyRepository;
import com.example.airline.dao.PortRepository;
import com.example.airline.dto.AviaRaceDto;
import com.example.airline.dto.BestRouteDto;
import com.example.airline.mapper.AviaRaceMapper;
import com.example.airline.mapper.PortMapper;
import com.example.airline.model.AirPort;
import com.example.airline.model.AviaRace;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.KShortestSimplePaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AviaService {

    private final AirRepository repository;
    private final PortRepository portRepository;
    private final CompanyRepository companyRepository;
    private final AviaRaceMapper mapper;
    private final PortMapper portMapper;


    @Autowired
    public AviaService(AirRepository repository, AviaRaceMapper mapper, PortRepository portRepository, CompanyRepository companyRepository, PortMapper portMapper) {
        this.mapper = mapper;
        this.repository = repository;
        this.portRepository = portRepository;
        this.companyRepository = companyRepository;
        this.portMapper = portMapper;
    }

    public List<BestRouteDto> findBestFlight(String departure, String destination, List<String> companySet) {

        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph =
                new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>
                        (DefaultWeightedEdge.class);

        List<AviaRaceDto> races = findAllByCompanyNameIn(companySet);
        //Construct graph from filtered collection
        for (int i = 0; i < races.size(); i++) {
            String vertex1 = races.get(i).getDeparturePoint();
            String vertex2 = races.get(i).getDestinationPoint();

            graph.addVertex(vertex1);
            graph.addVertex(vertex2);

            DefaultWeightedEdge edge = graph.addEdge(vertex1, vertex2);
            graph.setEdgeWeight(edge, races.get(i).getPrice());
        }
        System.out.println(graph);
        System.out.println("Shortest path from A to B:");

        // Finds K shortest paths from constructed Flight Graph
        KShortestSimplePaths paths = new KShortestSimplePaths(graph);
        List<GraphPath<String, DefaultWeightedEdge>> shortestPath = paths.getPaths(departure, destination, 5);
        System.out.println(shortestPath);

        List<BestRouteDto> aviaRaceDtos = populateFlightListFromGraphPath(shortestPath);

        List<BestRouteDto> bestRouteDtoList = checkFligthTimes(aviaRaceDtos);


        return bestRouteDtoList;
    }

    private List<BestRouteDto> populateFlightListFromGraphPath(List<GraphPath<String, DefaultWeightedEdge>> shortestPath) {
        List<BestRouteDto> bestRouteDtoList = new ArrayList<>();

        for (int i = 0; i < shortestPath.size(); i++) {
            List<String> vertexList = shortestPath.get(i).getVertexList();
            BestRouteDto bestRouteDto = new BestRouteDto();
            for (int j = 0; j < vertexList.size() - 1; j++) {
                Optional<AirPort> dept = portRepository.findByName(vertexList.get(j));
                Optional<AirPort> dest = portRepository.findByName(vertexList.get(j + 1));
                bestRouteDto.addRoute(mapper.toDto(repository.findByDeparturePointAndDestinationPoint(dept.get(), dest.get())));
            }
            bestRouteDtoList.add(bestRouteDto);
        }
        return bestRouteDtoList;
    }

    private List<BestRouteDto> checkFligthTimes(List<BestRouteDto> aviaRaceDtoList) {
        List<BestRouteDto> bestRouteDtoList = new ArrayList<>();
        for (int i = 0; i < aviaRaceDtoList.size(); i++) {
            BestRouteDto iterationList = aviaRaceDtoList.get(i);
            boolean flag = false;
            for (int j = 0; j < iterationList.getDtoList().size(); j++) {
                AviaRaceDto aviaRaceDto1 = iterationList.getDtoList().get(j);
                if (j == iterationList.getDtoList().size() - 1) {
                    flag = true;
                    break;
                }
                AviaRaceDto aviaRaceDto2 = iterationList.getDtoList().get(j + 1);
                if (!aviaRaceDto1.getTime().isBefore(aviaRaceDto2.getTime())) {
                    break;
                }
            }
            if (flag) {
                bestRouteDtoList.add(iterationList);
            }
        }
        return bestRouteDtoList;
    }

    public List<AviaRaceDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AviaRaceDto> findAllByCompanyNameIn(List<String> company) {
        return repository
                .findAllByCompanyNameIn(company)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AviaRaceDto> findAllByDeparturePointOrDestinationPoint(AirPort departurePoint, AirPort destinationPoint) {
        return repository
                .findAllByDeparturePointOrDestinationPoint(departurePoint, destinationPoint)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<AviaRaceDto> findAllByDeparturePoint(AirPort departurePoint) {
        return repository
                .findAllByDeparturePoint(departurePoint)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public AviaRace findByDeparturePointAndDestinationPoint(AirPort depPoint, AirPort destPoint) {

        return repository.findByDeparturePointAndDestinationPoint(depPoint, destPoint);
    }

    public AviaRaceDto addRace(AviaRace aviaRace) {
        return mapper.toDto(repository.save(aviaRace));
    }

    public AviaRaceDto findOne(Long id) {
        return mapper.toDto(repository.getOne(id));
    }

    public Optional<AviaRace> findById(Long id) {
        return repository.findById(id);
    }

    public List<AviaRace> findByDestinationPointNameLikeOrDeparturePointNameLike(String dPoint, String desPoint) {
        return repository.findByDestinationPointNameLikeOrDeparturePointNameLike(dPoint, desPoint);
    }

    public Optional<AviaRace> findByDistance(int distance) {
        return repository.findByDistance(distance);
    }

    public Optional<AviaRace> findByPrice(int price) {
        return repository.findByPrice(price);
    }
}
