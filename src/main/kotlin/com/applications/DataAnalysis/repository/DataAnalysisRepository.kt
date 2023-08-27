package com.applications.DataAnalysis.repository

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import com.applications.DataAnalysis.model.Data


interface DataAnalysisRepository : ReactiveCassandraRepository<Data, String>