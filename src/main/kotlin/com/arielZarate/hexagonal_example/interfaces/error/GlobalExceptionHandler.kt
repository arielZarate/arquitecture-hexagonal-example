package com.arielZarate.hexagonal_example.interfaces.error

import com.arielZarate.hexagonal_example.interfaces.error.exception.CustomException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class GlobalExceptionHandler {



    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud incorrecta: ${e.message}")
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e: NoSuchElementException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos: ${e.message}")
    }

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("${e.message}")
    }

   /* @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(e: DataIntegrityViolationException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error de integridad de datos: ${e.message}")
    }*/

    @ExceptionHandler(Exception::class)
    fun handleGeneralException(e: Exception): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: ${e.message}")
    }
}