import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DataTeste {
    
    @Test
    public void diffDiasDentroMes(){
        Data um = new Data("26/12/2022");
        Data dois = new Data("31/12/2022");
        assertEquals(5, um.diffDias(dois));
    }

    @Test
    public void diffDiasDentroDoAno(){
        Data um = new Data("05/03/2022");
        Data dois = new Data("26/12/2022");
        assertEquals(296, um.diffDias(dois));
    }

    @Test
    public void diffDiasAtravessandoAno(){
        Data um = new Data("05/03/2022");
        Data dois = new Data("01/07/2023");
        assertEquals(483, um.diffDias(dois));
    }

    @Test
    public void diffDiasDentroDoAnoBissexto(){
        Data um = new Data("02/02/2020");
        Data dois = new Data("26/12/2020");
        assertEquals(328, um.diffDias(dois));
    }

    @Test
    public void diffDiasAtravessandoAnoBissexto(){
        Data um = new Data("25/09/2019");
        Data dois = new Data("01/05/2020");
        assertEquals(219, um.diffDias(dois));
    }

    @Test
    public void diffDiasAtravessandoVariosAnos(){
        Data um = new Data("25/09/2016");
        Data dois = new Data("01/05/2019");
        assertEquals(948, um.diffDias(dois));
    }

    @Test
    public void diffDiasAtravessandoVariosAnosComBissexto(){
        Data um = new Data("25/09/2016");
        Data dois = new Data("01/05/2020");
        assertEquals(1314, um.diffDias(dois));
    }

}
