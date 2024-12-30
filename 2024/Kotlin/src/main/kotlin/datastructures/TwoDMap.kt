package com.github.justwannafly.datastructures

class TwoDMap<E>(data: List<List<E>>) {

    private val data: MutableList<MutableList<E>>

    companion object {
        val cardinals = listOf(Direction.North, Direction.East, Direction.South, Direction.West)
    }

    init {
        if (data.isEmpty()) {
            error("TwoDMap data is empty")
        }
        val width = data[0].size
        data.forEach { if (it.size != width) {
            error("TwoDMap data is not constant width")
            }
        }
        this.data = data.map{ it.toMutableList() }.toMutableList()
    }

    fun set(coords: Pair<Int, Int>, thing: E) {
        set(coords.first, coords.second, thing)
    }
    fun set(x: Int, y: Int, thing: E) {
        data[y][x] = thing
    }
    fun getWidth(): Int {
        return data[0].size
    }
    fun getHeight(): Int {
        return data.size
    }
    fun get(x: Int, y: Int): E {
        return data[y][x]
    }
    fun getOrNull(y: Int, x: Int): E? {
        if (y < 0 || y >= getHeight() || x < 0 || x >= getWidth()) {
            return null
        }
        return get(x, y)
    }
    fun getOrNull(coordinates: Pair<Int, Int>): E? {
        return getOrNull(coordinates.first, coordinates.second)
    }
    fun get(coords: Pair<Int, Int>): E {
        return get(coords.first, coords.second)
    }
    fun getNeighborCoords(coords: Pair<Int, Int>, dir: Direction): Pair<Int, Int> {
        return getNeighborCoords(coords, dir, 1)
    }
    fun getNeighborCoords(coords: Pair<Int, Int>, direction: Direction, step: Int): Pair<Int, Int> {
        return getNeighborCoords(coords.first, coords.second, direction, step)
    }
    fun getNeighborCoords(x: Int, y: Int, direction: Direction): Pair<Int, Int> {
        return getNeighborCoords(x, y, direction, 1)
    }
    fun getNeighborCoords(x: Int, y: Int, direction: Direction, step: Int): Pair<Int, Int> {
        return when (direction) {
            Direction.North -> Pair(x, y-step)
            Direction.NorthEast -> Pair(x+step, y-step)
            Direction.East -> Pair(x+step, y)
            Direction.SouthEast -> Pair(x+step, y+step)
            Direction.South -> Pair(x, y+step)
            Direction.SouthWest -> Pair(x-step, y+step)
            Direction.West -> Pair(x-step, y)
            Direction.NorthWest -> Pair(x-step,y-step)
        }
    }
    fun getNeighborCoords(x: Int, y: Int, dirs: List<Direction>): List<Pair<Int, Int>> {
        return dirs.mapNotNull { if (hasNeighbor(x, y, it)) getNeighborCoords(x, y, it) else null }
    }
    fun getNeighbor(coords: Pair<Int, Int>, direction: Direction): E? {
        return getNeighbor(coords.first, coords.second, direction)
    }
    fun getNeighbor(coords: Pair<Int, Int>, direction: Direction, step: Int): E? {
        return getNeighbor(coords.first, coords.second, direction, step)
    }
    fun getNeighbor(x: Int, y: Int, direction: Direction): E? {
        return getNeighbor(x, y, direction, 1)
    }
    fun getNeighbor(x: Int, y: Int, direction: Direction, step: Int): E? {
        if (!hasNeighbor(x, y, direction, step)) {
            return null
        }
        val neighborCoords = getNeighborCoords(x, y, direction, step)
        return get(neighborCoords.first, neighborCoords.second)
    }
    fun hasNeighbor(x: Int, y: Int, direction: Direction): Boolean {
        return hasNeighbor(x, y, direction, 1)
    }
    fun hasNeighbor(x: Int, y: Int, direction: Direction, step: Int): Boolean {
        if ((direction == Direction.North
                    || direction == Direction.NorthEast
                    || direction == Direction.NorthWest) && y - step < 0) {
            return false
        }
        if ((direction == Direction.South
                    || direction == Direction.SouthEast
                    || direction == Direction.SouthWest) && y + step >= getHeight()) {
            return false
        }
        if ((direction == Direction.East
                    || direction == Direction.NorthEast
                    || direction == Direction.SouthEast) && x + step >= getWidth()) {
            return false
        }
        if ((direction == Direction.West
                    || direction == Direction.NorthWest
                    || direction == Direction.SouthWest) && x - step < 0) {
            return false
        }
        return true
    }
    fun getNeighbors(x: Int, y: Int, directions: List<Direction>): List<E> {
        return directions.mapNotNull { getNeighbor(x, y, it) }
    }
    fun count(check: (E) -> Boolean): Int {
        return data.sumOf { row -> row.filter { check(it) }.size }
    }
    fun findAll(thing: E): List<Pair<Int, Int>> {
        val locations = mutableListOf<Pair<Int, Int>>()
        for (x: Int in 0 until getWidth()) {
            for (y: Int in 0 until getHeight()) {
                if (thing == get(x, y)) {
                    locations.add(Pair(x, y))
                }
            }
        }
        return locations
    }

    enum class Direction {
        North,
        NorthEast,
        East,
        SouthEast,
        South,
        SouthWest,
        West,
        NorthWest,
    }

}