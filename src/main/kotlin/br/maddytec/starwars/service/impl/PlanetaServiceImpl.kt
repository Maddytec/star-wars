package br.maddytec.starwars.service.impl

import br.maddytec.starwars.model.Planeta
import br.maddytec.starwars.repository.PlanetaRepository
import br.maddytec.starwars.service.PlanetaService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class PlanetaServiceImpl(val planetaRepository: PlanetaRepository): PlanetaService {

    @CacheEvict("planetas", allEntries = true)
    override fun save(planeta: Planeta) = planetaRepository.save(planeta)

    @CacheEvict("planetas", allEntries = true)
    override fun update(id: Long, planeta: Planeta) = planetaRepository.save(planeta.copy(id = id))

    @Cacheable("planetas")
    override fun findAll(): List<Planeta> = planetaRepository.findAll()

    override fun findById(id: Long): Optional<Planeta> = planetaRepository.findById(id)

    @Cacheable("planetas")
    override fun getPlanetaFiltro(nome: String, clima: String, terreno: String): List<Planeta>? {
        return this.findAll()
            .filter {
                it.nome.contains(nome, true)
                && it.clima.contains(clima, true)
                && it.terreno.contains(terreno, true)
            }
    }

    @CacheEvict("planetas", allEntries = true)
    override fun delete(id: Long) = planetaRepository.deleteById(id)



}