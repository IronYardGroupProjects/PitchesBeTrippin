package com.theironyard.services;

import com.theironyard.entities.Pitch;
import com.theironyard.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by alexanderhughes on 7/15/16.
 */
public interface PitchRepository extends CrudRepository<Pitch, Integer> {
    Pitch findFirstByOwner(User owner);
}
