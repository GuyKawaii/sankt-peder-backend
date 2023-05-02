package com.example.sanktpederbackend.service;

import com.example.sanktpederbackend.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FotoService {

    @Autowired
    FotoRepository fotoRepository;
}
