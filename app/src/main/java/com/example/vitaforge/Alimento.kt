package com.example.vitaforge

import kotlin.math.ceil

data class Alimento(
    // Variables de los alimentos
    val nombre: String,
    val porcionesProteinas: Double,
    val porcionesCarbohidratos: Double,
    val porcionesGrasas: Double,
    val comidasAdecuadas: Set<String> // Variable para el desayuno, comida o cena
) {
    companion object {
        // Lista de alimentos con sus respectivas porciones de macronutrientes divididas según sea para desayuno, comida o cena
        val alimentos = listOf(
            Alimento("Arroz cocido (100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Tortilla de maíz (1 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Amaranto (4 cucharadas/25 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Tofu (125 gramos)", 0.5, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Cereales de avena (30 gramos)", 0.0, 0.5, 0.0, setOf("desayuno")),
            Alimento("Bolillo (1/2 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("desayuno", "comida", "cena")),
            Alimento("Bollo para hamburguesa (1/2 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("cena")),
            Alimento("Batata (100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Cereales (100 gramos)", 0.0, 1.0, 0.0, setOf("desayuno")),
            Alimento("Magdalenas (30 gramos)", 0.0, 0.5, 0.0, setOf("desayuno")),
            Alimento("Croisant (30 gramos)", 0.0, 0.5, 0.0, setOf("desayuno")),
            Alimento("Bollo de leche (35 gramos)", 0.0, 0.5, 0.0, setOf("desayuno")),
            Alimento("Bizcocho (45 gramos)", 0.0, 1.0, 0.0, setOf("desayuno")),
            Alimento("Maíz cocido (1/2 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Maíz en grano (1/2 taza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Galletas Marías, (5 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("desayuno")),
            Alimento("Medias noches (1/2 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("desayuno", "comida", "cena")),
            Alimento("Palomitas naturales (3 tazas/100 gramos)", 0.0, 1.0, 0.0, setOf("cena")),
            Alimento("Pan árabe (1/2 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("desayuno", "comida", "cena")),
            Alimento("Pan integral (1 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("desayuno", "comida", "cena")),
            Alimento("Pan blanco (1/2 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("desayuno", "comida", "cena")),
            Alimento("Pan de centeno (1/2 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("desayuno", "comida", "cena")),
            Alimento("Patata mediana (1 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Verduras (100 gramos)", 0.0, 0.5, 0.0, setOf("comida", "cena")),
            Alimento("Boniato (100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Pan de molde (1 rebanada/35 gramos)", 0.0, 0.5, 0.0, setOf("cena")),
            Alimento("Quinoa (100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Sopa de pasta (1/2 taza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Tortilla de nopal (2 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Tostada horneada (2 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("desayuno")),
            Alimento("Barra de cereal (1/2 pieza/75 gramos)", 0.0, 1.0, 1.0, setOf("desayuno")),
            Alimento("Galletas de avena (2 piezas/75 gramos)", 0.0, 1.0, 1.0, setOf("desayuno")),
            Alimento("Granola (3 cucharadas/25 gramos)", 0.0, 1.0, 1.0, setOf("desayuno")),
            Alimento("Hot cake/waffle (1 pieza mediana/50 gramos)", 0.0, 1.0, 1.0, setOf("desayuno")),
            Alimento("Pan dulce (1/2 pieza/100 gramos)", 0.0, 1.0, 1.0, setOf("desayuno", "comida", "cena")),
            Alimento("Tortilla de harina (1/2 pieza/100 gramos)", 0.0, 1.0, 1.0, setOf("comida", "cena")),
            Alimento("Arándano (150 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Damasco (4 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Nispero (1/2 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Ciruela pasa (7 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Ciruela roja/amarilla (2 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Fresas (18 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Fruta deshidratada (5 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Granada (1 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Guanábana (1 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Guayaba (3 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Higos (3 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Kiwi (2 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Lima (3 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Mandarina (2 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Mandarina grande (1 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Mango (1 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Manzana mediana (1 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Melón (1 taza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Naranja (3 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Papaya (1 taza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Pasas (2 cucharadas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Pera (1/2 pieza/50 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Piña (1 taza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Plátano (1/2 pieza/75 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Sandía (1 taza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Pomelo (1 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Higos chumbo (3 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Cerezas (100 gramos)", 0.0, 0.5, 0.0, setOf("comida", "cena")),
            Alimento("Piña (225 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Sandia (350 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Melocoton (1 pieza mediana/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Melon (100 gramos)", 0.0, 0.5, 0.0, setOf("comida", "cena")),
            Alimento("Membrillo (200 gramos)", 0.0, 0.5, 0.0, setOf("comida", "cena")),
            Alimento("Uva (18 piezas/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Zapote (1/2 pieza/100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Leche de soja (1 vaso/250ml)", 0.5, 0.0, 0.5, setOf("desayuno")),
            Alimento("Leche evaporada entera (1 lata/200 gramos)", 0.5, 0.5, 0.5, setOf("desayuno")),
            Alimento("Leche en polvo entera (4 cucharadas/20 gramos)", 0.5, 0.0, 0.5, setOf("desayuno")),
            Alimento("Leche en polvo desnatada (4 cucharadas/20gramos)", 0.5, 0.0, 0.0, setOf("desayuno")),
            Alimento("Leche entera de vaca (1 vaso/250ml)", 0.5, 0.5, 0.5, setOf("desayuno")),
            Alimento("Leche de cabra (1 vaso/250ml)", 0.5, 0.5, 0.5, setOf("desayuno")),
            Alimento("Leche de oveja (1 vaso/250ml)", 0.5, 0.5, 1.0, setOf("desayuno")),
            Alimento("Leche de soja (1 vaso/250ml)", 0.5, 0.5, 0.0, setOf("desayuno")),
            Alimento("Yoghurt (1 envase/125 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Arroz con leche (170 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Flan de huevo (1 envase/125gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Natilla (1 envase/125 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Aceite (2 cucharaditas/10 gramos)", 0.0, 0.0, 1.0, setOf("desayuno")),
            Alimento("Jocoque (3/4 taza/100 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Aderezo (1 cucharada/10 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Aguacate (1/3 pieza/100 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Ajonjolí (1 cucharadita/10 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Mantequilla (1 cucharadita/10 gramos)", 0.0, 0.0, 1.0, setOf("desayuno")),
            Alimento("Manteca de cerdo (1 cucharadita/10 gramos)", 0.0, 0.0, 0.5, setOf("desayuno")),
            Alimento("Almendras (10 piezas/50 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Avellana (10 piezas/50 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Cacahuetes (14 piezas/50 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Nuez de la India (7 piezas/50 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Nuez castilla (3 piezas/50 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Piñon (1 cucharada/10 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Pistachos (18 piezas/50 gramos)", 0.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Espaguetis (100 gramos)", 0.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Judias (1/2 taza/100 gramos)", 0.5, 1.0, 0.0, setOf("comida")),
            Alimento("Garbanzos (1/2 taza/100 gramos)", 0.5, 1.0, 0.0, setOf("comida")),
            Alimento("Habas (1/2 taza/100 gramos)", 0.5, 1.0, 0.0, setOf("comida")),
            Alimento("Alubias (1/2 taza/100 gramos)", 0.5, 1.0, 0.0, setOf("comida")),
            Alimento("Lentejas (1/2 taza/100 gramos)", 0.5, 1.0, 0.0, setOf("comida")),
            Alimento("Soja (1/2 taza/100 gramos)", 0.5, 1.0, 0.0, setOf("comida")),
            Alimento("Guisantes (100 gramos)", 1.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Habas (100 gramos)", 1.0, 1.0, 0.0, setOf("comida", "cena")),
            Alimento("Atun (1 lata/125 gramos)", 1.0, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Carne de cerdo (100 gramos)", 1.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Carne de ternera (100 gramos)", 1.0, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Clara de huevo (5 claras/100 gramos)", 1.0, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Huevo entero (2 huevos/125 gramos)", 1.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Pechuga de pavo (4 rebanadas/100 gramos)", 1.0, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Pechuga de pollo asada (80 gramos)", 1.0, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Pechuga de pavo (100 gramos)", 1.0, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Carne de pato (100 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Chuleta de cordero (100 gramos)", 0.5, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Pescado blanco (100 gramos)", 1.0, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Pescado azul (100 gramos)", 1.0, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Queso cottage (9 cucharadas/50 gramos)", 1.0, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Queso fresco de cabra (50 gramos)", 0.5, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Queso mozzarella (30 gramos)", 0.5, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Queso Oaxaca (30 gramos)", 0.5, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Queso panela (50 gramos)", 0.5, 0.0, 1.0, setOf("comida", "cena")),
            Alimento("Queso cheddar (50 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Queso de burgos (75 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Queso de cabra curado (50 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Queso gouda (50 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Queso manchego curado (50 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Queso parmesano (50 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Requesón (4 cucharadas)", 0.5, 0.0, 0.0, setOf("comida", "cena")),
            Alimento("Salchicha de pavo (1 pieza/50 gramos)", 0.5, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Salmón (100 gramos)", 1.0, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Sardina (100 gramos)", 1.0, 0.0, 0.5, setOf("comida", "cena")),
            Alimento("Proteína en polvo (1 toma/25 gramos)", 1.0, 0.0, 0.0, setOf("desayuno", "comida", "cena"))
        )

        // Función generarMenu, puede mostrar los alimentos agrupados según sean adecuados para desayuno, comida o cena.
        fun generarMenu(
            sexo: Int,
            peso: Double,
            altura: Double,
            edad: Int,
            objetivo: String,
            nivelActividad: String
        ): Map<String, List<Alimento>> {
            // Agrupar alimentos por comida
            val alimentosPorComida = agruparAlimentosPorComida(alimentos)

            // Variables para acumular las porciones seleccionadas
            var proteinasAcumuladas = 0.0
            var grasasAcumuladas = 0.0
            var carbohidratosAcumulados = 0.0

            // Función para seleccionar los alimentos sin que excedan las porciones recomendadas
            fun seleccionarAlimentos(
                alimentosDisponibles: List<Alimento>,
                tipoPorcion: String, // Para proteinas, carbohidratos o grasas
                limitePorciones: Double // Máximo de porciones permitidas
            ): List<Alimento> {
                val alimentosSeleccionados = mutableListOf<Alimento>() // Almacena los alimentos
                var acumulado = 0.0 // Para llevar la suma acumulada de las porciones

                for (alimento in alimentosDisponibles.shuffled().sortedByDescending { //Los alimentos se mezclan y se ordenan
                    when (tipoPorcion) { // Según el tipo de porción, se selecciona que atributo usar
                        "proteinas" -> it.porcionesProteinas
                        "grasas" -> it.porcionesGrasas
                        "carbohidratos" -> it.porcionesCarbohidratos
                        else -> 0.0
                    }
                }) {
                    val porcion = when (tipoPorcion) {
                        "proteinas" -> alimento.porcionesProteinas
                        "grasas" -> alimento.porcionesGrasas
                        "carbohidratos" -> alimento.porcionesCarbohidratos
                        else -> 0.0
                    }

                    if (acumulado + porcion <= limitePorciones) { // Si cuando se agrega el alimento, su supera el límite
                        alimentosSeleccionados.add(alimento) // Se añade a la lista
                        acumulado += porcion // Se actualiza
                    }
                }
                //Se retorna la lista de alimentos que no excedan el límite
                return alimentosSeleccionados
            }

            val rmr = calcularRMR(sexo, peso, altura, edad) // Tasa metabólica en reposo
            val energia = when (objetivo.lowercase()) { // Cálculo de la energía según el objetivo
                "perder peso" -> rmr * calcularFactorActividad(objetivo, nivelActividad) * 0.67
                "mantener" -> rmr * calcularFactorActividad(objetivo, nivelActividad) * 0.8
                "ganar musculo" -> rmr * calcularFactorActividad(objetivo, nivelActividad) * 1.0
                else -> rmr * calcularFactorActividad(objetivo, nivelActividad)
            }

            // Parámetros nutricionales y estructura de datos para generar el menú
            val factorProteina = calcularFactorProteina(objetivo, nivelActividad) // Cálculo de los factores nutricionales
            val factorGrasas = calcularFactorGrasas(objetivo, nivelActividad)
            val proteinas = calcularProteinas(peso, factorProteina).toDouble() // Cálculo de los macronutientes
            val grasas = calcularGrasas(peso, factorGrasas).toDouble()
            val carbohidratos = calcularCarbohidratos(energia, proteinas, grasas).toDouble()
            val (porcionesProteinas, porcionesGrasas, porcionesCarbohidratos) = calcularPorciones(proteinas, grasas, carbohidratos) // Conversión de porciones

            val menuDiario = mutableMapOf<String, List<Alimento>>() // Almacena los alimentos organizados por comida

            // Mostrar sugerencias para cada comida con límites de distribución
            alimentosPorComida.forEach { (comida, alimentosComida) ->

                // Repartir las porciones de macronutrientes en 30%, 40%, 30% para desayuno, comida y cena
                val limiteProteinas: Double
                val limiteCarbohidratos: Double
                val limiteGrasas: Double

                when (comida) {
                    "desayuno" -> {
                        limiteProteinas = porcionesProteinas * 0.30
                        limiteCarbohidratos = porcionesCarbohidratos * 0.30
                        limiteGrasas = porcionesGrasas * 0.30
                    }
                    "comida" -> {
                        limiteProteinas = porcionesProteinas * 0.40
                        limiteCarbohidratos = porcionesCarbohidratos * 0.40
                        limiteGrasas = porcionesGrasas * 0.40
                    }
                    "cena" -> {
                        limiteProteinas = porcionesProteinas * 0.30
                        limiteCarbohidratos = porcionesCarbohidratos * 0.30
                        limiteGrasas = porcionesGrasas * 0.30
                    }
                    else -> {
                        limiteProteinas = 0.0
                        limiteCarbohidratos = 0.0
                        limiteGrasas = 0.0
                    }
                }

                // Seleccionar alimentos para esta comida
                val seleccionadosProteinas = seleccionarAlimentos(
                    alimentosComida.filter { it.porcionesProteinas > 0 },
                    "proteinas",
                    limiteProteinas
                )
                val seleccionadosCarbohidratos = seleccionarAlimentos(
                    alimentosComida.filter { it.porcionesCarbohidratos > 0 },
                    "carbohidratos",
                    limiteCarbohidratos
                )
                val seleccionadosGrasas = seleccionarAlimentos(
                    alimentosComida.filter { it.porcionesGrasas > 0 },
                    "grasas",
                    limiteGrasas
                )

                // Combinar todas las selecciones para la comida
                val  alimentosComida =
                    (seleccionadosProteinas + seleccionadosCarbohidratos + seleccionadosGrasas)
                        .distinct()
                        .sortedByDescending { it.porcionesProteinas + it.porcionesCarbohidratos + it.porcionesGrasas }

                menuDiario[comida] = alimentosComida

                // Mostrar sugerencias

                seleccionadosProteinas.forEach {
                    proteinasAcumuladas += it.porcionesProteinas
                }

                seleccionadosCarbohidratos.forEach {
                    carbohidratosAcumulados += it.porcionesCarbohidratos
                }

                seleccionadosGrasas.forEach {
                    grasasAcumuladas += it.porcionesGrasas
                }
            }

            return menuDiario
        }

        // Función para agrupar alimentos por comida
        fun agruparAlimentosPorComida(alimentos: List<Alimento>): Map<String, List<Alimento>> {
            val comidas = listOf("desayuno", "comida", "cena")
            return comidas.associateWith { comida ->
                alimentos.filter { it.comidasAdecuadas.contains(comida) }
            }
        }

        // Función para obtener el RMR (Tasa Metabólica en Reposo)
        fun calcularRMR(sexo: Int, peso: Double, altura: Double, edad: Int): Double {
            return if (sexo == 0) {
                (10 * peso) + (6.25 * altura) - (5 * edad) + 5
            } else {
                (10 * peso) + (6.25 * altura) - (5 * edad) - 161
            }
        }

        // Función para obtener el factor de actividad basado en el objetivo y el nivel de actividad física
        fun calcularFactorActividad(objetivo: String, nivelActividad: String): Double {
            val factoresActividad = mapOf(
                "perder peso" to mapOf(
                    "sedentaria" to 1.00,
                    "leve" to 1.20,
                    "moderada" to 1.40,
                    "intensa" to 1.50
                ),
                "mantener" to mapOf(
                    "sedentaria" to 1.08,
                    "leve" to 1.20,
                    "moderada" to 1.40,
                    "intensa" to 1.60
                ),
                "ganar musculo" to mapOf(
                    "sedentaria" to 1.34,
                    "leve" to 1.45,
                    "moderada" to 1.53,
                    "intensa" to 1.70
                )
            )
            return factoresActividad[objetivo.lowercase()]?.get(nivelActividad.lowercase()) ?: 1.0
        }

        // Función para obtener el factor de proteína según el objetivo y nivel de actividad física
        fun calcularFactorProteina(objetivo: String, nivelActividad: String): Double {
            val factoresProteina = mapOf(
                "perder peso" to mapOf(
                    "sedentaria" to 0.75,
                    "leve" to 0.85,
                    "moderada" to 0.95,
                    "intensa" to 1.0
                ),
                "mantener" to mapOf(
                    "sedentaria" to 0.8,
                    "leve" to 0.86,
                    "moderada" to 1.0,
                    "intensa" to 1.05
                ),
                "ganar musculo" to mapOf(
                    "sedentaria" to 1.06,
                    "leve" to 1.08,
                    "moderada" to 1.1,
                    "intensa" to 1.12
                )
            )
            return factoresProteina[objetivo.lowercase()]?.get(nivelActividad.lowercase()) ?: 1.0
        }

        // Función para obtener el factor de grasas según el objetivo y nivel de actividad física
        fun calcularFactorGrasas(objetivo: String, nivelActividad: String): Double {
            val factoresGrasas = mapOf(
                "perder peso" to mapOf(
                    "sedentaria" to 0.38,
                    "leve" to 0.39,
                    "moderada" to 0.43,
                    "intensa" to 0.48
                ),
                "mantener" to mapOf(
                    "sedentaria" to 0.38,
                    "leve" to 0.39,
                    "moderada" to 0.43,
                    "intensa" to 0.49
                ),
                "ganar musculo" to mapOf(
                    "sedentaria" to 0.40,
                    "leve" to 0.45,
                    "moderada" to 0.48,
                    "intensa" to 0.5
                )
            )
            return factoresGrasas[objetivo.lowercase()]?.get(nivelActividad.lowercase()) ?: 1.0
        }

        // Función para calcular las proteínas recomendadas
        fun calcularProteinas(peso: Double, factorProteina: Double): Int {
            return ceil(peso * 2.2 * factorProteina).toInt()
        }

        // Función para calcular las grasas recomendadas
        fun calcularGrasas(peso: Double, factorGrasas: Double): Int {
            return ceil(peso * 2.2 * factorGrasas).toInt()
        }

        // Función para calcular los carbohidratos recomendados
        fun calcularCarbohidratos(energia: Double, proteinas: Double, grasas: Double): Int {
            val caloriasProteinas = proteinas * 4  // 4 calorías por cada gramo de proteína
            val caloriasGrasas = grasas * 9       // 9 calorías por cada gramo de grasa
            return ceil((energia - caloriasProteinas - caloriasGrasas) / 4).toInt()  // 4 calorías por cada gramo de carbohidrato
        }

        // Función para calcular las porciones necesarias de cada macronutriente
        fun calcularPorciones(
            proteinas: Double,
            grasas: Double,
            carbohidratos: Double
        ): Triple<Int, Int, Int> {
            return Triple(
                ceil(proteinas / 25).toInt(), // 1 porción = 25g de proteinas, el valor es estandar
                ceil(grasas / 15).toInt(), // 1 porción = 15g de grasas, el valor es estandar
                ceil(carbohidratos / 25).toInt() // 1 porción = 25g de carbohidratos, el valor es estandar
            )
        }
    }
}//class





