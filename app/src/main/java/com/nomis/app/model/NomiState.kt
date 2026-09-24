package com.nomis.app.model

data class NomiState(
 val id:String="lumi-001", val species:String="Lumi", val nickname:String="Lumi", val hatched:Boolean=false,
 val hunger:Int=82, val happiness:Int=76, val energy:Int=88, val bond:Int=0, val ageMinutes:Long=0,
 val lastUpdated:Long=System.currentTimeMillis(), val sleeping:Boolean=false, val carePoints:Int=0, val playPoints:Int=0
){
 val mood:String get()=when{!hatched->"Waiting to hatch";sleeping->"Dreaming ✦";hunger<25->"Hungry";energy<20->"Sleepy";happiness<30->"Needs you";happiness>85&&hunger>60->"Glowing";else->"Content"}
 val stage:String get()=when{ageMinutes<60->"Newborn";ageMinutes<1440->"Baby";ageMinutes<4320->"Young Nomi";else->"Growing"}
}