package com.theironyard.controllers;

import com.theironyard.entities.Pitch;
import com.theironyard.entities.User;
import com.theironyard.services.PitchRepository;
import com.theironyard.services.UserRepository;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Created by alexanderhughes on 7/15/16.
 */
@RestController
public class PitchesRestController {

    Server dbui;
    @Autowired
    PitchRepository pitchRepo;

    @Autowired
    UserRepository userRepo;

    @PostConstruct
    public void init() throws SQLException {
        dbui.createWebServer().start();
    }
    @PreDestroy
    public void destroy() {
        dbui.stop();
    }

    //  pitches/owner
    @RequestMapping(path = "/pitches/owner", method = RequestMethod.GET)
    public ResponseEntity<Object> getMyPitches(HttpSession session) {
        User user = userRepo.findOne(Integer.valueOf((String) session.getAttribute("id")));
        return new ResponseEntity<Object>(pitchRepo.findFirstByOwner(user), HttpStatus.OK);
    }
    //  pitches - get
    @RequestMapping(path = "/pitches", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllPitches() {
        return new ResponseEntity<Object>(pitchRepo.findAll(), HttpStatus.OK);
    }
    //  pitches/interest - get
    @RequestMapping(path = "/pitches/interest", method = RequestMethod.GET)
    public ResponseEntity<Object> getInterestPitches(HttpSession session) {
        int id = Integer.valueOf((String) session.getAttribute("id"));
        ArrayList<Pitch> pitches = (ArrayList<Pitch>) pitchRepo.findAll();
        pitches = pitches.stream()
                .filter(pitch -> pitch.getOwner().getId() == id)
                .collect(Collectors.toCollection(ArrayList<Pitch>::new));
        return new ResponseEntity<Object>(pitches, HttpStatus.OK);
    }
    //  pitches - post, put, delete
    @RequestMapping(path = "/pitches", method = RequestMethod.POST)
    public ResponseEntity<Object> createPitch(@RequestBody Pitch pitch, HttpSession session) {
        User user = userRepo.findOne(Integer.valueOf((String) session.getAttribute("id")));
        pitch = new Pitch(pitch.getTitle(), pitch.getDescription(), user);
        pitchRepo.save(pitch);
        return ResponseEntity<Object>(HttpStatus.OK);
    }
    //  pitches/{id}/interest - put
    @RequestMapping(path = "/pitches", method = RequestMethod.PUT)
    public ResponseEntity<Object> updatePitch(HttpSession session, @RequestBody Pitch pitchUpdate) {
        User user = userRepo.findOne(Integer.valueOf((String) session.getAttribute("id")));
        Pitch pitch = pitchRepo.findOne(pitchUpdate.getId());
        if (pitchUpdate.getTitle() != null) pitch.setTitle(pitchUpdate.getTitle());
        if (pitchUpdate.getDescription() != null) pitch.setDescription(pitchUpdate.getDescription());
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    @RequestMapping(path = "/pitches", method = RequestMethod.DELETE)
    public ResponseEntity<Object> updatePitch(HttpSession session) {
        User user = userRepo.findOne(Integer.valueOf((String) session.getAttribute("id")));
        Pitch pitch = pitchRepo.findFirstByOwner(user);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
    //  login -post
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<Object> login(HttpSession session, @RequestBody User loginUser) {
        User user = userRepo.findFirstByUsername(loginUser.getUsername());
        if (user )
        session.setAttribute("id", user.getId());
    }

    //  logout - post

    //  users/create - post username, password, firstName, lastName
}