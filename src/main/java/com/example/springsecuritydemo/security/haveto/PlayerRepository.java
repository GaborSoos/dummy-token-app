package com.example.springsecuritydemo.security.haveto;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    List<Player> findByUsername(String username);
}
