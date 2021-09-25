package br.maddytec.starwars.controller

import br.maddytec.starwars.model.Planeta
import br.maddytec.starwars.service.PlanetaService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("planeta")
class PlanetaController(val planetaService: PlanetaService) {

    @GetMapping
    fun get() = planetaService.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = planetaService.findById(id)
        .orElseThrow{ throw ResponseStatusException(HttpStatus.NOT_FOUND, "Planeta não encontrado.") }

    @PostMapping
    fun save(@RequestBody planeta: Planeta) = planetaService.save(planeta)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Long, @RequestBody planeta: Planeta) {
        planetaService.findById(id).orElseThrow{ throw ResponseStatusException(HttpStatus.NOT_FOUND, "Planeta não encontrado.")}
            .let { planetaService.save(it.copy(id = id, nome = planeta.nome ,clima=planeta.clima, terreno = planeta.terreno ))}
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long){
        planetaService.findById(id).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND, "Planeta não encontrado.")}
            .let { planetaService.delete(id)}
    }
}