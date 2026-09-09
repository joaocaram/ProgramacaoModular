import java.util.ArrayList;
import java.util.List;

/**
 * MIT License
 *
 * Copyright(c) 2026 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public class Aluno {
    private static final int QUANT_AVALIACOES = 4;
    private static final double NOTA_APROVACAO = 60d;
    private static final double FREQ_MINIMA = 0.75;

    private static int proxMatricula = 10_000;

    // #region atributos
    private String nome;
    private int matricula;
    private Curso curso;
    private List<Double> notas;
    private int faltas;
    // #endregion

    public Aluno(String nome) {
        this.nome = nome;
        matricula = proxMatricula++;
        ;
        notas = new ArrayList<Double>(QUANT_AVALIACOES);
        faltas = 0;
        curso = null;
    }

    public boolean matricular(Curso curso) {
        boolean matriculou = false;
        if (curso != null) {
            if (this.curso != null) {
                faltas = 0;
                notas = new ArrayList<Double>(QUANT_AVALIACOES);
            }
            this.curso = curso;
            matriculou = true;
        }
        return matriculou;

    }

    public double lancarNota(double valor) {
        if (curso != null && notas.size() < QUANT_AVALIACOES && valor >= 0)
            notas.add(valor);

        return notaFinal();
    }

    public int lancarFalta() {
        if (curso != null && faltas < curso.quantidadeAulas())
            faltas++;

        return faltas;
    }

    public double notaFinal() {
        double soma = 0d;
        for (double nota : notas) {
            soma += nota;
        }
        return soma;
    }

    public double frequencia() {
        double resposta = 0d;
        if (curso != null) {
            resposta = 1 - ((double) faltas / curso.quantidadeAulas());
        }

        return resposta;
    }

    public boolean aprovado() {
        return notaFinal() >= NOTA_APROVACAO && frequencia() >= FREQ_MINIMA;
    }

    public int getMatricula() {
        return matricula;
    }

    public String toString() {

        String situacao = "não aprovado.";

        StringBuilder relat = new StringBuilder(String.format("Aluno %s (%s)\n", nome, matricula));
        if (curso != null) {
            relat.append(String.format("Nota: %.2f\n", notaFinal()));
            relat.append(String.format("Frequência: %.2f\n", frequencia() * 100));
            if (aprovado())
                situacao = "aprovado.";
            relat.append("Situação atual: " + situacao);
        }

        return relat.toString();
    }
}
