package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Book;
import net.javaguides.springboot.model.Hotel;
import net.javaguides.springboot.model.Season;
import net.javaguides.springboot.payload.request.BookRequest;
import net.javaguides.springboot.payload.request.SeasonRequest;
import net.javaguides.springboot.repository.HotelRepository;
import net.javaguides.springboot.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/season")
public class SeasonController {
    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createSeason(@Valid @RequestBody SeasonRequest seasonRequest) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(seasonRequest.getHotelId());
        Hotel hotel = hotelOptional.get();
        Season season = new Season(seasonRequest.getSeasonName(), seasonRequest.getStartDate(),
                seasonRequest.getEndDate(), seasonRequest.getCoefficient());

        List<Season> seasons = hotel.getSeasons();
        seasons.add(season);
        seasonRepository.save(season);
        hotelRepository.save(hotel);
        return ResponseEntity.ok().body("You have created a season!");
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteSeason(@Valid @RequestBody SeasonRequest seasonRequest) {
        Optional<Season> optionalSeason = seasonRepository.findById(seasonRequest.getSeasonId());
        Season season = optionalSeason.get();

        seasonRepository.delete(season);
        return ResponseEntity.ok().body("You have deleted the season!");
    }

}