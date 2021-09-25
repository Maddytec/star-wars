package br.maddytec.starwars.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Planeta(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 1,
    var nome: String = "",
    var clima: String = "",
    var terreno: String = ""
)
