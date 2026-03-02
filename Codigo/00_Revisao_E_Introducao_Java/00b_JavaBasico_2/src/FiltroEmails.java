static String[] lerEmails(){
    int quantos;
    quantos = Integer.parseInt(IO.readln("Quantos emails? "));
    String[] emails = new String[quantos];
    for (int i = 0; i < emails.length; i++) {
        String prompt = 
            String.format("Digite o %dº email: ",(i+1));
        emails[i] = IO.readln(prompt);   
    }
    return emails;
}
static String lerDominio(){
    return IO.readln("Qual o domínio para filtro? ");
}

static String[] filtrarEmails(String[] emails, String dominio){
    String[] retorno = new String[emails.length];
    int quantidade = 0;
    for (int i = 0; i < emails.length; i++) {
        String[] partesDoEmail = emails[i].split("@");
        if(partesDoEmail[1].equals(dominio)){
            retorno[quantidade] = emails[i];
            quantidade++;
        }
    }
    return Arrays.copyOf(retorno, quantidade);
}

void main(){
    String[] emails = lerEmails();
    String dominio = lerDominio();
    
    String[] filtrados = filtrarEmails(emails, dominio);

    IO.println("Emails do domínio "+dominio+": ");
    for (int i = 0; i < filtrados.length; i++) {
        IO.println(filtrados[i]);
    }
}
