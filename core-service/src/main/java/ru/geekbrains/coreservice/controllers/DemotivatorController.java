package ru.geekbrains.coreservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.coreservice.services.StorageService;

@RestController
@RequestMapping("/api/v1/dem")
@RequiredArgsConstructor
public class DemotivatorController {

//    private final StorageService service;
//
//    @Value("${upload.path}")
//    private String uploadPath;
//    @GetMapping("/img")

//    public ResponseEntity<Resource> getImg(){
//        Resource file = service.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"").body()
//    }
}
