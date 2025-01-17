package com.grupo1.ganaderiagrupo1.Controlador;

import com.grupo1.ganaderiagrupo1.Dto.Area.AreaExisteDto;
import com.grupo1.ganaderiagrupo1.Dto.Area.AreaNuevoDto;
import com.grupo1.ganaderiagrupo1.Excepciones.ApiError;


import com.grupo1.ganaderiagrupo1.Excepciones.MensajeExito;

import com.grupo1.ganaderiagrupo1.Servicios.AreaServicio;
import com.grupo1.ganaderiagrupo1.Servicios.GanadoServicio;

import com.grupo1.ganaderiagrupo1.Servicios.impl.AreaServiceImpl;
import com.grupo1.ganaderiagrupo1.Servicios.impl.GanadoServicioImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/areas")
public class AreaController {
    @Autowired
    private AreaServicio areaService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAllAreas() {
       try {
         return ResponseEntity.ok(areaService.getAllAreas());
        } catch (RuntimeException e) {
           return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-234",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }
    @GetMapping("/estados/{estado}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAreasByEstado(@PathVariable String estado) {
        try {
            return ResponseEntity.ok(areaService.getAreasByEstado(estado));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-234",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAreaById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(areaService.getAreaById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-234",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

   @PutMapping("/{id}/{estado}")
   @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAreaByEstado(@PathVariable int id, @PathVariable String estado) {
        try {
            areaService.acutualizarEstadoArea(id,estado);
            return ResponseEntity.ok(new MensajeExito(new Date(),"Estado actualizado", HttpStatus.OK));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-234",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> addArea(@RequestBody AreaNuevoDto area) {
        try {
            areaService.addArea(area);
            return ResponseEntity.ok(new MensajeExito(new Date(),"Area creada", HttpStatus.OK));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-234",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> updateArea(@RequestBody AreaExisteDto updatedArea) {
        try {
            areaService.updateArea(updatedArea);
            return ResponseEntity.ok(areaService.getAreaById(updatedArea.getAreaId()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-234",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> deleteArea(@PathVariable int id) {
       try {
            areaService.deleteArea(id);
            return ResponseEntity.ok(new MensajeExito(new Date(),"Area eliminada", HttpStatus.OK));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-234",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }


    // Endpoint para obtener la superficie total de todas las áreas
    @GetMapping("/superficie-total")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> calcularSuperficieTotal() {
        try {
            return ResponseEntity.ok(areaService.calcularSuperficieTotal());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ApiError(new Date(),"p-234",e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }



}
