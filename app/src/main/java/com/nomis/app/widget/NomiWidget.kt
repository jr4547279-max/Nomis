package com.nomis.app.widget
import android.content.Context
import androidx.glance.*
import androidx.glance.appwidget.*
import androidx.glance.layout.*
import androidx.glance.text.*
import androidx.glance.unit.ColorProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nomis.app.R
class NomiWidget:GlanceAppWidget(){override suspend fun provideGlance(context:Context,id:GlanceId){provideContent{Column(GlanceModifier.fillMaxSize().background(ColorProvider(Color(0xFF101827))).padding(14.dp),horizontalAlignment=Alignment.CenterHorizontally){Image(ImageProvider(R.drawable.lumi),"Lumi",GlanceModifier.fillMaxWidth().height(110.dp));Text("Lumi  #001",style=TextStyle(color=ColorProvider(Color.White),fontWeight=FontWeight.Bold));Text("Tap into Nomis to care for me ✦",style=TextStyle(color=ColorProvider(Color(0xFFCAD6FF))))}}}}
class NomiWidgetReceiver:GlanceAppWidgetReceiver(){override val glanceAppWidget:GlanceAppWidget=NomiWidget()}