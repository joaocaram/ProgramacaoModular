import java.time.DayOfWeek;
import java.util.LinkedList;
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

public class Curso {
    private String nome;
    private String codigo;
    private int quantidadeAulas;
    private List<Turma> turmas;
    private DayOfWeek diaSemana;

    public Curso(String nome, int quantidadeAulas, DayOfWeek dia) {
        this.nome = nome;
        this.quantidadeAulas = quantidadeAulas;
        diaSemana = dia;
        if (quantidadeAulas <= 0)
            quantidadeAulas = 1;
        turmas = new LinkedList<>();
        codigo = criarCodigo();

    }

    private String criarCodigo() {
        StringBuilder cod = new StringBuilder();
        String[] palavras = nome.split(" ");
        for (String palavra : palavras)
            cod.append(palavra.charAt(0) + "\n");

        cod.append(diaSemana.getValue() % 7 + 1);
        return cod.toString();
    }

    public int addTurma(Turma turma) {
        if (turma != null)
            turmas.add(turma);
        return turmas.size();
    }

    public int quantidadeAulas() {
        return quantidadeAulas;
    }

    public String relatorioTurmas() {
        StringBuilder relat = new StringBuilder(toString() + "\n");
        for (Turma turma : turmas) {
            relat.append(turma.toString());
            relat.append("\n=====================\n");
        }
        return relat.toString();

    }

    public String toString() {
        return String.format("%s (%s) com total de {%d} aulas.", nome, codigo, quantidadeAulas);
    }

}
