package odk.kalanso.stock.dto;

import odk.kalanso.stock.entities.BonEntre;
import odk.kalanso.stock.entities.DetailsEntre;

import java.util.List;
public class EntreDto {
    private BonEntre bonEntre;
    private List<DetailsEntre> detailsEntres;

    // Getters and setters
    public BonEntre getBonEntre() {
        return bonEntre;
    }

    public void setBonEntre(BonEntre bonEntre) {
        this.bonEntre = bonEntre;
    }

    public List<DetailsEntre> getDetailsEntres() {
        return detailsEntres;
    }

    public void setDetailsEntres(List<DetailsEntre> detailsEntres) {
        this.detailsEntres = detailsEntres;
    }
}
