import java.util.ArrayList;
import java.util.LinkedList;

/** 
 * MIT License
 *
 * Copyright(c) 2024-26 João Caram <caram@pucminas.br>
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

static int lerNumero(String mensagem){
        int valor;
        valor = Integer.parseInt( IO.readln("\t"+mensagem+": "));
        return valor;
    }

static List<String> lerEmails(){
    int quantos;
    quantos = lerNumero("Quantidade de emails");
    List<String> emails = new ArrayList<>(quantos);
    
    for (int i = 0; i < emails.size(); i++) {
        String prompt = String.format("Digite o %dº email: ",(i+1));
        emails.add(IO.readln(prompt));   
    }
    return emails;
}

static String lerDominio(){
    return IO.readln("Qual o domínio para filtro? ");
}

static List<String> filtrarEmails(List<String> emails, String dominio){
    List<String> resposta = new LinkedList<>();
    
    int quantidade = 0;
    for (int i = 0; i < emails.size(); i++) {
        String[] partesDoEmail = emails.get(i).split("@");
        if(partesDoEmail[1].equals(dominio)){
            resposta.add(emails.get(i));
            quantidade++;
        }
    }
    return resposta.subList(0, quantidade);
}

void main(){
    List<String> emails = lerEmails();
    String dominio = lerDominio();
    
    List<String> filtrados = filtrarEmails(emails, dominio);

    IO.println("Emails do domínio "+dominio+": ");
    for (int i = 0; i < filtrados.size(); i++) {
        IO.println(filtrados.get(i));
    }
}
