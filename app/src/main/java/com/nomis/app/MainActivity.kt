package com.nomis.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nomis.app.data.NomiRepository
import kotlinx.coroutines.launch

class MainActivity:ComponentActivity(){override fun onCreate(b:Bundle?){super.onCreate(b);val repo=NomiRepository(this);setContent{NomisApp(repo)}}}
@Composable fun NomisApp(repo:NomiRepository){
 val s by repo.state.collectAsState(initial=com.nomis.app.model.NomiState());val scope=rememberCoroutineScope()
 MaterialTheme(colorScheme=darkColorScheme()){Box(Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(androidx.compose.ui.graphics.Color(0xFF070A12),androidx.compose.ui.graphics.Color(0xFF111B2E))))){Column(Modifier.fillMaxSize().padding(22.dp),horizontalAlignment=Alignment.CenterHorizontally){
 Spacer(Modifier.height(20.dp));Text("NOMIS",fontSize=28.sp,fontWeight=FontWeight.Black);Text("SMALL CREATURES. BIG MOMENTS.",fontSize=10.sp);Spacer(Modifier.height(28.dp))
 Card(colors=CardDefaults.cardColors(containerColor=androidx.compose.ui.graphics.Color(0x171FFFFFF)),shape=RoundedCornerShape(30.dp),modifier=Modifier.fillMaxWidth().weight(1f)){Column(Modifier.fillMaxSize().padding(20.dp),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.Center){
 Image(painterResource(R.drawable.lumi),"Lumi",Modifier.fillMaxWidth().height(260.dp).clip(RoundedCornerShape(24.dp)));Spacer(Modifier.height(14.dp));Text(if(s.hatched)s.nickname else "A mysterious egg…",fontSize=30.sp,fontWeight=FontWeight.Bold);Text(if(s.hatched)"#001 LUMI • ${s.stage} • ${s.mood}" else "Your first Nomi is ready.");Spacer(Modifier.height(18.dp))
 if(!s.hatched)Button({scope.launch{repo.hatch()}},Modifier.fillMaxWidth()){Text("HATCH LUMI ✦")}else{Stat("Hunger",s.hunger);Stat("Happiness",s.happiness);Stat("Energy",s.energy);Stat("Bond",s.bond);Spacer(Modifier.height(18.dp));Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.spacedBy(8.dp)){Button({scope.launch{repo.feed()}},Modifier.weight(1f)){Text("Feed")};Button({scope.launch{repo.play()}},Modifier.weight(1f)){Text("Play")};OutlinedButton({scope.launch{repo.sleep()}},Modifier.weight(1f)){Text(if(s.sleeping)"Wake" else "Sleep")}}}
 }};Spacer(Modifier.height(12.dp));Text("FOUNDING NOMI • PROTOTYPE 0.1",fontSize=10.sp)}}}}
@Composable fun Stat(label:String,value:Int){Row(Modifier.fillMaxWidth().padding(vertical=4.dp),verticalAlignment=Alignment.CenterVertically){Text(label,Modifier.width(90.dp));LinearProgressIndicator(progress={value/100f},modifier=Modifier.weight(1f).height(9.dp).clip(RoundedCornerShape(9.dp)));Text(" $value",fontSize=12.sp)}}