package br.com.uniamerica.pizzaria.pizarria.dto;
import br.com.uniamerica.pizzaria.pizarria.entity.SaboresEntity;
import lombok.Data;

@Data
public class SaboresDTO {
    private Long id;

    private String nomeSabor;

    public SaboresDTO (){}

    public SaboresDTO(Long id, String nomeSabor) {
        this.id = id;
        this.nomeSabor = nomeSabor;
    }

    public SaboresDTO(SaboresEntity sabor) {
        this.id = sabor.getId();
        this.nomeSabor = sabor.getNomeSabor();
    }
}
