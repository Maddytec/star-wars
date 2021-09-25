package br.com.maddytec.starwars.controller

import br.com.maddytec.starwars.model.Planeta
import br.com.maddytec.starwars.service.PlanetaService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.websocket.server.PathParam

@RestController
@RequestMapping("planeta")
class PlanetaController(val planetaService: PlanetaService) {

    @GetMapping
    fun get() = planetaService.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = planetaService.findById(id)
        .orElseThrow{ throw ResponseStatusException(HttpStatus.NOT_FOUND, "Planeta não encontrado.") }

    @GetMapping("/filtro")
    fun getPlanetaFiltro(    @RequestParam(required = false, defaultValue = "") nome: String,
                @RequestParam(required = false, defaultValue = "") clima: String,
                @RequestParam(required = false, defaultValue = "") terreno: String
    ): List<Planeta> {
        val planetaList = planetaService.getPlanetaFiltro(nome, clima, terreno)
        if(planetaList.isNullOrEmpty()) {
             throw ResponseStatusException(HttpStatus.NOT_FOUND, "Planeta não encontrado")
          }
        return planetaList
    }

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