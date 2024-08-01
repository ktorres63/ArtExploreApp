import kotlin.math.pow
import kotlin.math.sqrt

// Clase para representar un punto en el plano 2D
data class Point(val x: Double, val y: Double)

// Función de trilateración que calcula la posición del receptor dados tres puntos y sus respectivas distancias al receptor
fun trilateration(P1: Point, P2: Point, P3: Point, R1: Double, R2: Double, R3: Double): Point? {
    // Calcula la distancia entre P1 y P2
    val disP1P2 = distance(P1, P2)

    // Calcula el vector unitario en la dirección de P1 a P2
    val ex = Point(
        (P2.x - P1.x) / disP1P2,
        (P2.y - P1.y) / disP1P2
    )

    // Proyección del vector P1->P3 en la dirección de ex
    val i = dotProduct(ex, Point(P3.x - P1.x, P3.y - P1.y))

    // Calcula el vector unitario en la dirección perpendicular a ex
    val ey = Point(
        (P3.x - P1.x - i * ex.x) / distance(Point(P3.x - P1.x - i * ex.x, P3.y - P1.y - i * ex.y), Point(0.0, 0.0)),
        (P3.y - P1.y - i * ex.y) / distance(Point(P3.x - P1.x - i * ex.x, P3.y - P1.y - i * ex.y), Point(0.0, 0.0))
    )

    // Calcula la distancia entre P1 y P2
    val d = distance(P1, P2)

    // Proyección del vector P1->P3 en la dirección de ey
    val j = dotProduct(ey, Point(P3.x - P1.x, P3.y - P1.y))

    // Calcula la coordenada x del receptor en el sistema de coordenadas de P1 y P2
    val x = (R1.pow(2) - R2.pow(2) + d.pow(2)) / (2 * d)

    // Calcula la coordenada y del receptor en el sistema de coordenadas de P1 y P2
    val y = (R1.pow(2) - R3.pow(2) + i.pow(2) + j.pow(2)) / (2 * j) - (i / j) * x

    // Calcula las coordenadas del receptor en el sistema de coordenadas original
    val receptorX = P1.x + x * ex.x + y * ey.x
    val receptorY = P1.y + x * ex.y + y * ey.y

    return Point(receptorX, receptorY)
}

// Función para calcular la distancia entre dos puntos en el plano 2D
fun distance(P1: Point, P2: Point): Double {
    return sqrt((P2.x - P1.x).pow(2) + (P2.y - P1.y).pow(2))
}

// Función para calcular el producto punto entre dos vectores
fun dotProduct(P1: Point, P2: Point): Double {
    return P1.x * P2.x + P1.y * P2.y
}

// Ejemplo de uso:
fun main() {
    // Definimos tres puntos conocidos
    val P1 = Point(800.0, 350.0)
    val P2 = Point(0.0, 700.0)
    val P3 = Point(800.0, 700.0)

    // Definimos las distancias desde el receptor a cada uno de los tres puntos
    val R1 = 400.0
    val R2 = 500.0
    val R3 = 500.0

    // Calculamos la posición del receptor utilizando la trilateración
    val receptorPosition = trilateration(P1, P2, P3, R1, R2, R3)

    // Imprimimos la posición del receptor si se pudo calcular
    if (receptorPosition != null) {
        println("Receiver located at: (${receptorPosition.x}, ${receptorPosition.y})")
    } else {
        println("Receiver could not be located.")
    }
}
