package com.springboot.restaubarcordano.Modelo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restaubarcordano.Modelo.Entidades.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{
    List<Menu> findByCategoria(String categoria);
}