package starter.pack.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import starter.pack.restapi.model.dto.date.DateDTO;
import starter.pack.shared.interfaces.IDateUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("date")
public class DateController {
    private IDateUtil dateUtil;

    @Autowired
    public DateController(IDateUtil dateUtil) {
        this.dateUtil = dateUtil;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<DateDTO> getAll() {
        return new ResponseEntity<>(getDates(), HttpStatus.OK);
    }

    @RequestMapping(value = "/utclocal", method = RequestMethod.GET)
    public ResponseEntity<LocalDateTime> getUtcLocal() {
        return new ResponseEntity<>(getDates().getUtcLocal(), HttpStatus.OK);
    }

    @RequestMapping(value = "/utczoned", method = RequestMethod.GET)
    public ResponseEntity<ZonedDateTime> getUtcZoned() {
        return new ResponseEntity<>(getDates().getUtcZoned(), HttpStatus.OK);
    }

    @RequestMapping(value = "/copenhagenlocal", method = RequestMethod.GET)
    public ResponseEntity<LocalDateTime> getCopenhagenLocal() {
        return new ResponseEntity<>(getDates().getCopenhagenLocal(), HttpStatus.OK);
    }

    @RequestMapping(value = "/copenhagenzoned", method = RequestMethod.GET)
    public ResponseEntity<ZonedDateTime> getCopenhagenZoned() {
        return new ResponseEntity<>(getDates().getCopenhagenZoned(), HttpStatus.OK);
    }

    private DateDTO getDates() {
        DateDTO date = new DateDTO();
        date.setUtcLocal(dateUtil.getNow());
        date.setUtcZoned(ZonedDateTime.now(dateUtil.getTimezoneUtc().toZoneId()));
        date.setCopenhagenLocal(LocalDateTime.now(ZoneId.of("Europe/Copenhagen")));
        date.setCopenhagenZoned(ZonedDateTime.now(ZoneId.of("Europe/Copenhagen")));
        return date;
    }
}
