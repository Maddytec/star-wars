package br.com.maddytec.starwars.repository

import br.com.maddytec.starwars.model.Planeta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlanetaRepository: JpaRepository<Planeta, Long> {
}