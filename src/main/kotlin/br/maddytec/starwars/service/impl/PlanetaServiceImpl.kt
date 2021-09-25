package br.maddytec.starwars.service.impl

import br.maddytec.starwars.model.Planeta
import br.maddytec.starwars.repository.PlanetaRepository
import br.maddytec.starwars.service.PlanetaService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class PlanetaServiceImpl(val planetaRepository: PlanetaRepository): PlanetaService {

    override fun save(planeta: Planeta) = planetaRepository.save(planeta)

    override fun update(id: Long, planeta: Planeta) = planetaRepository.save(planeta.copy(id = id))

    override fun findAll(): List<Planeta> = planetaRepository.findAll()

    override fun findById(id: Long): Optional<Planeta> = planetaRepository.findById(id)

    override fun getPlanetaFiltro(nome: String, clima: String, terreno: String): List<Planeta>? {
        return planetaRepository.findAll()
            .filter {
                it.nome.contains(nome, true)
                && it.clima.contains(clima, true)
                && it.terreno.contains(terreno, true)
            }
    }

    override fun delete(id: Long) = planetaRepository.deleteById(id)



}