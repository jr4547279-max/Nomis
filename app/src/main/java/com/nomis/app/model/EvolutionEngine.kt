package com.nomis.app.model

enum class EvolutionPath { UNDECIDED, NURTURER, ADVENTURER, DREAMER }

object EvolutionEngine {
 fun path(state:NomiState):EvolutionPath = when {
  state.ageMinutes < 4320 -> EvolutionPath.UNDECIDED
  state.playPoints > state.carePoints * 2 -> EvolutionPath.ADVENTURER
  state.carePoints > state.playPoints * 2 -> EvolutionPath.NURTURER
  else -> EvolutionPath.DREAMER
 }
 fun hint(state:NomiState):String = when(path(state)){
  EvolutionPath.UNDECIDED -> "Its future is still unwritten."
  EvolutionPath.NURTURER -> "It seems drawn to warmth and care."
  EvolutionPath.ADVENTURER -> "It keeps dreaming of somewhere beyond."
  EvolutionPath.DREAMER -> "Something unusual is awakening."
 }
}