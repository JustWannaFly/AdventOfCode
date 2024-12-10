package com.github.justwannafly.datastructures

class TwoDMap<E>(data: List<List<E>>) {

    private val data: List<List<E>>

    init {
        if (data.isEmpty()) {
            error("TwoDMap data is empty")
        }
        val width = data[0].size
        data.forEach { if (it.size != width) {
            error("TwoDMap data is not constant width")
            }
        }
        this.data = data
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
    fun getNeighbor(x: Int, y: Int, direction: Direction, step: Int): E? {
        if (!hasNeighbor(x, y, direction, step)) {
            return null
        }
        return when (direction) {
            Direction.North -> get(x, y-step)
            Direction.NorthEast -> get(x+step, y-step)
            Direction.East -> get(x+step, y)
            Direction.SouthEast -> get(x+step, y+step)
            Direction.South -> get(x, y+step)
            Direction.SouthWest -> get(x-step, y+step)
            Direction.West -> get(x-step, y)
            Direction.NorthWest -> get(x-step,y-step)
        }
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