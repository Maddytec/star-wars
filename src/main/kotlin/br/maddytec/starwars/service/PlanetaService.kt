package br.maddytec.starwars.service

import br.maddytec.starwars.model.Planeta
import java.util.*

interface PlanetaService {

    fun save(planeta: Planeta): Planeta

    fun update(id: Long, planeta: Planeta): Planeta?

    fun findAll(): List<Planeta>

    fun findById(id: Long): Optional<Planeta>

    fun delete(id: Long)
}