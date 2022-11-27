package ru.geekbrains.coreservice.services;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface StorageService {

    Resource loadAsResource(String filename);
}
