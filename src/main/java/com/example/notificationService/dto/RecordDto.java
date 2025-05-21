package com.example.notificationService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RecordDto {

    private Long id;

    private String timing;

    //@JsonFormat(shape = JsonFormat.Shape.ARRAY)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String status;

    private ClientDto clientDto;

    private MasterDto masterDto;

    private PlaceDto placeDto;

    private PetServiceDto petServiceDto;

}

