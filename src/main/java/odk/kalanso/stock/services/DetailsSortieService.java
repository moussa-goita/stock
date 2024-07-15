package odk.kalanso.stock.services;

import odk.kalanso.stock.entities.BonSortie;
import odk.kalanso.stock.entities.DetailsSortie;
import odk.kalanso.stock.repositories.BonSortieRepository;
import odk.kalanso.stock.repositories.DetailsSortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailsSortieService {
    @Autowired
    private DetailsSortieRepository detailsSortieRepository;
    @Autowired
    private BonSortieRepository bonSortieRepository;

    public List<DetailsSortie> getAllDetailsSortie() {
        return detailsSortieRepository.findAll();
    }
    public Optional<DetailsSortie> getDetailsSortieById(Long id) {
        return detailsSortieRepository.findById(id);
    }
    public DetailsSortie createDetailsSortie(DetailsSortie detailsSortie, BonSortie bonSortie) {
        BonSortie sortie = bonSortieRepository.findById(bonSortie.getId()).get();
        if (sortie != null){
            detailsSortie.setBonSortie(sortie);
        }
        return detailsSortieRepository.save(detailsSortie);
    }
}
