package com.nomis.app.model

data class NomiDefinition(val number:Int,val name:String,val rarity:String,val element:String,val personality:String)

object FoundingNomis {
 val all=listOf(
  NomiDefinition(1,"Lumi","Common","Light","Curious"),
  NomiDefinition(2,"Ember","Common","Fire","Energetic"),
  NomiDefinition(3,"Aqua","Common","Water","Explorer"),
  NomiDefinition(4,"Pebb","Common","Rock","Brave"),
  NomiDefinition(5,"Leafi","Common","Nature","Caring"),
  NomiDefinition(6,"Zeph","Uncommon","Air","Playful"),
  NomiDefinition(7,"Shady","Uncommon","Shadow","Quiet"),
  NomiDefinition(8,"Sprout","Uncommon","Plant","Positive"),
  NomiDefinition(9,"Bubo","Uncommon","Bubble","Silly"),
  NomiDefinition(10,"Spark","Uncommon","Electric","Mischievous"),
  NomiDefinition(11,"Frosti","Common","Ice","Cool"),
  NomiDefinition(12,"Volti","Rare","Electric","Charged"),
  NomiDefinition(13,"Bloomi","Rare","Floral","Gentle"),
  NomiDefinition(14,"Flare","Rare","Fire","Fearless"),
  NomiDefinition(15,"Misty","Rare","Cloud","Dreamy"),
  NomiDefinition(16,"Terra","Epic","Forest","Protective"),
  NomiDefinition(17,"Luna","Epic","Cosmic","Thoughtful"),
  NomiDefinition(18,"Ori","Epic","Celestial","Kind"),
  NomiDefinition(19,"Nox","Legendary","Dark Cosmic","Mysterious"),
  NomiDefinition(20,"Solar","Legendary","Celestial","Radiant")
 )
 val lumi=all.first()
}