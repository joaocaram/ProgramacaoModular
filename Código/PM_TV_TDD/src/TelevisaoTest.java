import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/** 
 * MIT License
 *
 * Copyright(c) 2022-23 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
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



/**
 * TESTES:
 * 	Só pode alterar volume ou canais quando estiver ligada;
        - A DEFINIR 📋
        - A DEFINIR 📋

    Tem canais numerados de 1 a 83, de modo circular;
        - posso colocar no canal 1 📋
        - não posso colocar em canal acima de 83 ✅
        - do 83 vai para 1 📋
        - do 1 vai para 83 📋

    Tem volume controlado entre 0 e 100.
        - subir volume ✅
        - baixar volume ✅
        - tentar subir volume acima do 100 ✅
        - tentar baixar abaixo do 0 📋
        
 * 
 * 
 */

 /**
  * Demonstração de TDD com teste+classe Televisão. Feita de forma parcial durante a aula.
  * Sugere-se que o aluno complete os testes, e portanto a classe, como exercício.
  */
public class TelevisaoTest {
    Televisao minhaTV;

    @BeforeEach
    public void init(){
        minhaTV = new Televisao();
        minhaTV.subirVolume();
    }

    @Test
    public void deveSubirVolumeEmUmaUnidade(){
        assertEquals(1, minhaTV.getVolume());
    }

    @Test
    public void deveBaixarVolumeEmUmaUnidade(){
        minhaTV.subirVolume();
        minhaTV.baixarVolume();
        assertEquals(1, minhaTV.getVolume());
    }

    @Test
    public void naoPodePassarVolumeDe100(){
        int volumeMaximo = 100;
        for (int i = 0; i < volumeMaximo; i++) {
            minhaTV.subirVolume();
        }
        minhaTV.subirVolume();
        assertEquals(volumeMaximo, minhaTV.getVolume());
    }

    @Test
    public void naoDeveColocarEmCanalAcimaDe83(){
        minhaTV.mudarCanal(85);
        assertEquals(1, minhaTV.canalAtual());
    }
}
