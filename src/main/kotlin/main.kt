// файл main.kt
// Сделано на основе примера работы с графиками

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.*
import jetbrains.letsPlot.letsPlot
import kotlin.math.pow

fun monteKrloVolume(dim: Int, numberOfPoints: Int): Double {
    var inSpherePoints: Int = 0
    var vec = Vector()
    for (i in 1..numberOfPoints) {
        vec.getRandomVector(dim)
        if (vec.vecLenght() <= 1.0)
            inSpherePoints++
    }

    return 2.0.pow(dim) * inSpherePoints / numberOfPoints
}

fun makeGraph(xVals: List<Double>, yVals: List<Double>, name: String){
    val data = mapOf<String, Any>("xvals" to xVals, "yvals" to yVals)

    val fig = letsPlot(data) +
            geomPoint( color = "blue", size = 3.0) { x = "xvals"; y = "yvals" } +
            geomLine() { x = "xvals"; y = "yvals" }

    ggsave(fig, name+".png")
}

fun sampleMean(sample: List<Double>): Double{
    var summ = 0.0
    for (i in 0..sample.size-1)
        summ += sample[i]
    return(summ/sample.size)
}

fun sampleDisp(sample: List<Double>): Double{
    var summOfSquares = 0.0
    val mean = sampleMean(sample)
    for (i in 0..sample.size-1)
        summOfSquares += (sample[i] - mean) * (sample[i] - mean)

    return(summOfSquares/sample.size)
}

fun sampleDispD2(sample: List<Double>): Double{
    var summOfSquares = 0.0
    val mean = 3.14159265
    for (i in 0..sample.size-1)
        summOfSquares += (sample[i] - mean) * (sample[i] - mean)

    return(summOfSquares/sample.size)
}

fun sampleDispD3(sample: List<Double>): Double {
    var summOfSquares = 0.0
    val mean = 3.14159265 * 4 / 3
    for (i in 0..sample.size - 1)
        summOfSquares += (sample[i] - mean) * (sample[i] - mean)

    return (summOfSquares / sample.size)
}


fun main() {

    var v1 = Vector()
    val pi = 3.14159265
    val numberOfPoints = 1000000
    var volsDim = mutableListOf<Double>()

    // Получим объемы всех гипер сфер при заданном выше числе точек
    for (i in 1..10) {
        volsDim.add(i-1, monteKrloVolume(i, numberOfPoints))
        print("Объем\t")
        print(i)
        print("\t мерного  шара = ")
        println(monteKrloVolume(i, numberOfPoints))
    }
    makeGraph(mutableListOf(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0), volsDim, "Vols(Dim)")

    // Теперь будем сканировать по числу точек чтобы построить дисперсию
    // Мы знаем аналитическую формулу для объема шара и площади окружности
    // Но если задуматься sigma(eps) = sigma(Vol) так что не будем ничего вычитать

    val sampleSize = 1000
    val scaningStep = 100
    val numberOfGraphsPoints = 20
    var tmpSample = mutableListOf<Double>()
    var disp = mutableListOf<Double>()
    var n = mutableListOf<Double>()

    for (i in 0..sampleSize-1)
        tmpSample.add(i,0.0)
    for (i in 0..numberOfGraphsPoints - 1)
        disp.add(i,0.0)
    for (i in 0..numberOfGraphsPoints - 1)
        n.add(i,0.0)

    for (i in 0..numberOfGraphsPoints - 1) {
        for (s in 0..sampleSize-1)
            tmpSample[s] = monteKrloVolume(2,(i+1)*scaningStep)
        disp[i] = sampleDispD2(tmpSample)
        n[i] = (i+1.0)*scaningStep
    }

    makeGraph(n, disp, "disp2")

    // Линеаризуем функцию в предположении что sigma~1/sqrt(n)

    for (i in 0..numberOfGraphsPoints - 1)
        disp[i] = 1/disp[i]/disp[i]

    makeGraph(n, disp, "dispLin2")

    for (i in 0..numberOfGraphsPoints - 1) {
        for (s in 0..sampleSize-1)
            tmpSample[s] = monteKrloVolume(3,(i+1)*scaningStep)
        disp[i] = sampleDispD3(tmpSample)
        n[i] = (i+1.0)*scaningStep
    }

    makeGraph(n, disp, "disp3")

    // Линеаризуем функцию в предположении что sigma~1/sqrt(n)

    for (i in 0..numberOfGraphsPoints - 1)
        disp[i] = 1/disp[i]/disp[i]

    makeGraph(n, disp, "dispLin3")

    println("bye!")
}
