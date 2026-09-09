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

public class Turma {
    private static final int MAX_ALUNOS = 20;

    private List<Aluno> alunos;
    private String nome;
    private Curso curso;

    public Turma(String nome, Curso curso) {
        this.nome = nome;
        this.curso = curso;
        alunos = new ArrayList<Aluno>(MAX_ALUNOS);
        this.curso.addTurma(this);
    }

    public boolean matricular(Aluno aluno) {
        boolean resposta = false;
        boolean alunoExiste = aluno != null && getAluno(aluno.getMatricula()) != null;
        if (temVagas() && !alunoExiste) {
            resposta = aluno.matricular(curso);
            if (resposta)
                alunos.add(aluno);
        }
        return resposta;
    }

    public boolean temVagas() {
        return alunos.size() < MAX_ALUNOS;
    }

    public Aluno getAluno(int matricula) {
        Aluno aluno = null;
        for (int i = 0; i < alunos.size() && aluno == null; i++) {
            Aluno candidato = alunos.get(i);
            if (candidato.getMatricula() == matricula)
                aluno = candidato;
        }
        return aluno;
    }

    public double porcentagemAprovados() {
        int totalAprovados = 0;
        for (Aluno aluno : alunos) {
            if (aluno.aprovado())
                totalAprovados++;
        }
        return (double) totalAprovados / alunos.size();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder(String.format("Turma %s -", nome));
        builder.append(curso.toString() + "\n");
        builder.append(String.format("Alunos matriculados: %d", alunos.size()));
        builder.append(String.format("Porcentagem de aprovados: %.2f", porcentagemAprovados() * 100));
        return builder.toString();

    }
}
