package com.data.migrate.datamigratesis.controller;

import com.data.migrate.datamigratesis.entity.Persion;
import com.data.migrate.datamigratesis.repository.PersionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : ChinhLV
 * @date : 2025/02/11
 **/
@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class PersionController {
    @Autowired
    private PersionRepository persionRepository;
    @GetMapping("/get-all")
    public ResponseEntity<List<Persion>> getAllPersion() {
        return ResponseEntity.ok(persionRepository.findAll());
    }

}
