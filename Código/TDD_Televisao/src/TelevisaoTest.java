import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TelevisaoTest{

    Televisao minhaTV;
    Televisao tvDesligada;

    @BeforeEach
    public void prepare(){
        minhaTV = new Televisao();
        minhaTV.botaoPower();
        tvDesligada = new Televisao();
        
    }

    @Test
    public void deveVoltarAoPrimeiroCanalDepoisDoUltimo(){
        minhaTV.mudarCanal(83);
        minhaTV.subirCanal();
        assertEquals(1, minhaTV.getCanal());
    }

    @Test
    public void deveSubirAoUltimoCanalDepoisDoPrimeiro(){
        minhaTV.mudarCanal(1);
        minhaTV.baixarCanal();
        assertEquals(83, minhaTV.getCanal());
    }

    @Test
    public void naoDeveAceitarCanaisAbaixoDoMinimo(){
        minhaTV.mudarCanal(0);
        assertEquals(1, minhaTV.getCanal());
    }

    @Test
    public void naoDeveAceitarCanaisAcimaDoMaximo(){
        minhaTV.mudarCanal(1000);
        assertEquals(1, minhaTV.getCanal());
    }

    @Test
    public void deveSubirOVolumeValido(){
        minhaTV.subirVolume();
        assertEquals(1, minhaTV.getVolume());
    }

    @Test
    public void deveBaixarOVolumeValido(){
        minhaTV.subirVolume();    
        
        minhaTV.baixarVolume();
        assertEquals(0, minhaTV.getVolume());
    }

    @Test
    public void naoPodeUltrapassarVolumeMaximo(){
        for (int i = 0; i < 1000; i++) {
            minhaTV.subirVolume();     
        }   
        assertEquals(100, minhaTV.getVolume());
    }

    @Test
    public void naoPodeUltrapassarVolumeMinimo(){
        for (int i = 0; i < 1000; i++) {
            minhaTV.baixarVolume();     
        }   
        assertEquals(0, minhaTV.getVolume());
    }

    @Test
    public void naoPodeSubirCanalDesligada(){
        tvDesligada.subirCanal();
        assertEquals(1, tvDesligada.getCanal());
    }

    @Test
    public void naoPodeBaixarCanalDesligada(){
        tvDesligada.baixarCanal();
        assertEquals(1, tvDesligada.getCanal());
    }

    @Test
    public void naoPodeSubirVolumeDesligada(){
        tvDesligada.subirVolume();
        assertEquals(0, tvDesligada.getVolume());
    }


    @Test
    public void naoPodeBaixarVolumeDesligada(){
        tvDesligada.botaoPower();
        tvDesligada.subirVolume();
        tvDesligada.botaoPower();
        tvDesligada.baixarVolume();
        assertEquals(1, tvDesligada.getVolume());
    }

    @Test
    public void naoPodeMudarCanalDesligada(){
        tvDesligada.mudarCanal(83);
        assertEquals(1, tvDesligada.getCanal());
    }

    @Test
    public void vaiParaZeroNoBotaoMudo(){
        minhaTV.aplicarMute();
        assertEquals(0, minhaTV.getVolume());
    }

    @Test
    public void deveVoltarAoVolumeAnteriorDepoisDoMute(){
        for (int i = 0; i < 10; i++) {
            minhaTV.subirVolume();    
        }
        minhaTV.aplicarMute();
        minhaTV.tirarMute();
        assertEquals(10, minhaTV.getVolume());

        
    }















}