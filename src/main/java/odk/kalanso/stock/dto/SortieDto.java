package odk.kalanso.stock.dto;

import lombok.Data;
import odk.kalanso.stock.entities.BonSortie;
import odk.kalanso.stock.entities.DetailsSortie;

import java.util.List;

@Data
public class SortieDto {
    private BonSortie bonSortie;
    private List<DetailsSortie> detailsSorties;
}
