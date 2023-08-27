package com.applications.DataAnalysis.controller

import com.applications.DataAnalysis.model.Data
import com.applications.DataAnalysis.service.DataAnalysisService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/data-analysis")
class DataAnalysisController(private val dataAnalysisService: DataAnalysisService) {

    @PostMapping("/save")
    fun saveDataToCassandra(@RequestBody data: Data): Mono<ResponseEntity<Data>> {
        return dataAnalysisService.saveDataToCassandra(data)
            .map { ResponseEntity(it, HttpStatus.CREATED) }
            .defaultIfEmpty(ResponseEntity<Data>(HttpStatus.BAD_REQUEST))
    }

    @PostMapping("/send")
    fun sendDataToPulsar(@RequestBody data: Data): Mono<ResponseEntity<Void>> {
        return dataAnalysisService.sendDataToPulsar(data)
            .thenReturn(ResponseEntity<Void>(HttpStatus.OK))
            .defaultIfEmpty(ResponseEntity<Void>(HttpStatus.BAD_REQUEST))
    }

    @PostMapping("/analyze-and-send")
    fun analyzeAndSendData(@RequestBody data: Data): Mono<ResponseEntity<Data>> {
        return dataAnalysisService.analyzeAndSendData(data)
            .map { ResponseEntity(it, HttpStatus.CREATED) }
            .defaultIfEmpty(ResponseEntity<Data>(HttpStatus.BAD_REQUEST))
    }

    @GetMapping("/{id}")
    fun getDataById(@PathVariable id: String): Mono<ResponseEntity<Data>> {
        return dataAnalysisService.getDataById(id)
            .map { ResponseEntity(it, HttpStatus.OK) }
            .defaultIfEmpty(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    @PutMapping("/{id}")
    fun updateData(@PathVariable id: String, @RequestBody data: Data): Mono<ResponseEntity<Data>> {
        return dataAnalysisService.updateData(id, data)
            .map { ResponseEntity(it, HttpStatus.OK) }
            .defaultIfEmpty(ResponseEntity(HttpStatus.NOT_FOUND))
    }

    @DeleteMapping("/{id}")
    fun deleteData(@PathVariable id: String): Mono<ResponseEntity<Void>> {
        return dataAnalysisService.deleteData(id)
            .thenReturn(ResponseEntity<Void>(HttpStatus.NO_CONTENT))
            .defaultIfEmpty(ResponseEntity<Void>(HttpStatus.NOT_FOUND))
    }

}
