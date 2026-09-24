package com.nomis.app.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.nomis.app.model.NomiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
private val Context.store by preferencesDataStore("nomi_state")
class NomiRepository(private val context:Context){
 private object K{val hatched=booleanPreferencesKey("hatched");val hunger=intPreferencesKey("hunger");val happy=intPreferencesKey("happy");val energy=intPreferencesKey("energy");val bond=intPreferencesKey("bond");val age=longPreferencesKey("age");val last=longPreferencesKey("last");val sleeping=booleanPreferencesKey("sleeping");val care=intPreferencesKey("care");val play=intPreferencesKey("play")}
 private fun from(p:Preferences)=NomiState(hatched=p[K.hatched]?:false,hunger=p[K.hunger]?:82,happiness=p[K.happy]?:76,energy=p[K.energy]?:88,bond=p[K.bond]?:0,ageMinutes=p[K.age]?:0,lastUpdated=p[K.last]?:System.currentTimeMillis(),sleeping=p[K.sleeping]?:false,carePoints=p[K.care]?:0,playPoints=p[K.play]?:0)
 val state:Flow<NomiState> = context.store.data.map{evolve(from(it))}
 private fun evolve(s:NomiState):NomiState{if(!s.hatched)return s;val mins=((System.currentTimeMillis()-s.lastUpdated)/60000).coerceAtLeast(0);if(mins==0L)return s;return s.copy(hunger=(s.hunger-(mins/18).toInt()).coerceIn(0,100),happiness=(s.happiness-(mins/35).toInt()).coerceIn(0,100),energy=if(s.sleeping)(s.energy+(mins/6).toInt()).coerceAtMost(100)else(s.energy-(mins/28).toInt()).coerceAtLeast(0),ageMinutes=s.ageMinutes+mins,lastUpdated=System.currentTimeMillis())}
 suspend fun hatch()=mutate{it.copy(hatched=true,bond=5)}
 suspend fun feed()=mutate{it.copy(hunger=(it.hunger+24).coerceAtMost(100),happiness=(it.happiness+3).coerceAtMost(100),bond=(it.bond+1).coerceAtMost(100),carePoints=it.carePoints+1)}
 suspend fun play()=mutate{if(it.energy<8)it else it.copy(happiness=(it.happiness+18).coerceAtMost(100),energy=(it.energy-8).coerceAtLeast(0),hunger=(it.hunger-4).coerceAtLeast(0),bond=(it.bond+2).coerceAtMost(100),playPoints=it.playPoints+1)}
 suspend fun sleep()=mutate{it.copy(sleeping=!it.sleeping)}
 private suspend fun mutate(block:(NomiState)->NomiState){context.store.edit{p->val n=block(evolve(from(p))).copy(lastUpdated=System.currentTimeMillis());p[K.hatched]=n.hatched;p[K.hunger]=n.hunger;p[K.happy]=n.happiness;p[K.energy]=n.energy;p[K.bond]=n.bond;p[K.age]=n.ageMinutes;p[K.last]=n.lastUpdated;p[K.sleeping]=n.sleeping;p[K.care]=n.carePoints;p[K.play]=n.playPoints}}
}