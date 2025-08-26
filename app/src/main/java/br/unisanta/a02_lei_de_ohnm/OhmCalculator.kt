package br.unisanta.a02_lei_de_ohnm

class OhmCalculator {
    fun calcularCorrente(tensao: Double, resistencia: Double): Double {
        return tensao / resistencia
    }

    fun calcularResistencia(tensao: Double, corrente: Double): Double {
        return tensao / corrente
    }

    fun calcularTensao(resistencia: Double, corrente: Double): Double {
        return resistencia * corrente
    }
}
