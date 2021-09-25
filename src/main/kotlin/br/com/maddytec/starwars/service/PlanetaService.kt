package br.com.maddytec.starwars.service

import br.com.maddytec.starwars.model.Planeta
import java.util.*

interface PlanetaService {

    fun save(planeta: Planeta): Planeta

    fun update(id: Long, planeta: Planeta): Planeta?

    fun findAll(): List<Planeta>

    fun findById(id: Long): Optional<Planeta>

    fun getPlanetaFiltro(nome: String, clima: String, terreno: String): List<Planeta>?

    fun delete(id: Long)

}