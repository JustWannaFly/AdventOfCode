package com.github.justwannafly.days
import java.io.File
fun main() {
val i= File("5").readLines()
val orderPairs=i.filter{it.contains('|')}.map{ listOf(it.split('|')[0], it.split('|')[1]) }
val updatePages=i.filter{it.contains(',')}.map{it.split(',')}
fun List<String>.before(page: String)=orderPairs.filter{it[1]==page&&it[0]in this}.map{it[0]}
fun isRightOrder(pages: List<String>):Boolean{return orderPairs.all{!(pages.contains(it[0])&&pages.contains(it[1])&&pages.indexOf(it[0])>pages.indexOf(it[1]))}}
println(updatePages.filter{isRightOrder(it)}.sumOf{it[(it.size/2)].toInt()})
println(updatePages.filter{!isRightOrder(it)}.map{it.sortedBy{page->it.before(page).size}}.sumOf{it[(it.size/2)].toInt()})
}