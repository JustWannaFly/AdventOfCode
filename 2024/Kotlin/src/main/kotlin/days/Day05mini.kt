package com.github.justwannafly.days
import java.io.File
fun main(){val i=File("5").readLines()
val o=i.filter{it.contains('|')}.map{listOf(it.split('|')[0],it.split('|')[1])}
val p=i.filter{it.contains(',')}.map{it.split(',')}
fun List<String>.before(a:String)=o.filter{it[1]==a&&it[0]in this}.map{it[0]}
fun c(a:List<String>):Boolean{return o.all{!(a.indexOf(it[1])>0&&a.indexOf(it[0])>a.indexOf(it[1]))}}
println(p.filter{c(it)}.sumOf{it[(it.size/2)].toInt()})
println(p.filter{!c(it)}.map{it.sortedBy{page->it.before(page).size}}.sumOf{it[(it.size/2)].toInt()})}