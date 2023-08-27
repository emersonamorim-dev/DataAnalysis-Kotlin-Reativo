package com.applications.DataAnalysis.service

import org.apache.pulsar.client.api.*
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

import com.applications.DataAnalysis.repository.DataAnalysisRepository
import com.applications.DataAnalysis.model.Data

@Service
class DataAnalysisService(
    private val dataAnalysisRepository: DataAnalysisRepository,
    private val pulsarClient: PulsarClient
) {

    private val topic = "persistent://sample/standalone/ns1/data-analysis-topic"

    fun saveDataToCassandra(data: Data): Mono<Data> {
        return dataAnalysisRepository.save(data)
    }

    fun sendDataToPulsar(data: Data): Mono<Void> {
        return Mono.fromCallable {
            val producer: Producer<String> = pulsarClient.newProducer(Schema.STRING)
                .topic(topic)
                .create()

            producer.send(data.toString()) // 
            producer.close()

            null
        }
    }

    fun analyzeAndSendData(data: Data): Mono<Data> {
        return saveDataToCassandra(data)
            .flatMap { sendDataToPulsar(it).then(it.toMono()) }
    }

    // Recuperar dados por ID
    fun getDataById(id: String): Mono<Data> {
        return dataAnalysisRepository.findById(id)
    }

    // Atualizar dados por ID
    fun updateData(id: String, data: Data): Mono<Data> {
        return dataAnalysisRepository.findById(id)
            .flatMap { existingData ->
                val updatedData = existingData.copy(
                    productName = data.productName,
                    productPrice = data.productPrice,
                    quantitySold = data.quantitySold
                )
                dataAnalysisRepository.save(updatedData)
            }
    }

    // Excluir dados por ID
    fun deleteData(id: String): Mono<Void> {
        return dataAnalysisRepository.deleteById(id)
    }

    // Opcional: recuperar todos os dados
    fun getAllData(): Flux<Data> {
        return dataAnalysisRepository.findAll()
    }
}

