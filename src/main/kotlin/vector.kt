import kotlin.math.*

class Vector(x1: Double, x2: Double, x3: Double, x4: Double, x5: Double, x6: Double, x7: Double, x8: Double, x9: Double, x10: Double) {
    var coords = mutableListOf<Double>(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10)

    constructor() :
            this(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

    constructor(x1: Double) :
            this(x1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

    constructor(x1: Double, x2: Double) :
            this(x1, x2, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

    constructor(x1: Double, x2: Double, x3: Double) :
            this(x1, x2, x3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

    constructor(x1: Double, x2: Double, x3: Double, x4: Double) :
            this(x1, x2, x3, x4, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

    constructor(x1: Double, x2: Double, x3: Double, x4: Double, x5: Double) :
            this(x1, x2, x3, x4, x5, 0.0, 0.0, 0.0, 0.0, 0.0)

    constructor(x1: Double, x2: Double, x3: Double, x4: Double, x5: Double, x6: Double) :
            this(x1, x2, x3, x4, x5, x6, 0.0, 0.0, 0.0, 0.0)

    constructor(x1: Double, x2: Double, x3: Double, x4: Double, x5: Double, x6: Double, x7: Double) :
            this(x1, x2, x3, x4, x5, x6, x7, 0.0, 0.0, 0.0)

    constructor(x1: Double, x2: Double, x3: Double, x4: Double, x5: Double, x6: Double, x7: Double, x8: Double) :
            this(x1, x2, x3, x4, x5, x6, x7, x8, 0.0, 0.0)

    constructor(x1: Double, x2: Double, x3: Double, x4: Double, x5: Double, x6: Double, x7: Double, x8: Double, x9: Double) :
            this(x1, x2, x3, x4, x5, x6, x7, x8, x9, 0.0)

    fun vecLenght(): Double
    {
        var lenght = 0.0
        for(i in 0..9)
            lenght+=coords[i]*coords[i]
        return(sqrt(lenght))
    }

    fun getRandomVector(dim: Int)
    {
        for(i in 0..dim-1)
            coords[i] = getRandom()
    }
}

fun getRandom():Double
{
    return 1.0*(-Int.MAX_VALUE..Int.MAX_VALUE).random()/Int.MAX_VALUE
}