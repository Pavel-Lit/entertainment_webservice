package ru.geekbrains.coreservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dem")
@RequiredArgsConstructor
public class DemotivatorController {

    @Value("${upload.path}")
    private String uploadPath;
}
