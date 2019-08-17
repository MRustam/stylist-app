package live.evsianna.stylist.service;

import live.evsianna.stylist.model.Favor;
import live.evsianna.stylist.model.projection.FavorProjection;
import live.evsianna.stylist.repository.FavorRepository;
import live.evsianna.stylist.service.interfaces.IFavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FavorService implements IFavorService {

    private final FavorRepository favorRepository;

    @Autowired
    public FavorService(FavorRepository favorRepository) {
        this.favorRepository = favorRepository;
    }

    @Override
    public Set<FavorProjection> findAll() {
        return favorRepository.findAllByOrderByCreatedDesc();
    }

    @Override
    public Favor findById(final String id) {
        throw new RuntimeException("Method not implemented.");
    }

    @Override
    public Favor save(final Favor favor) {
        return favorRepository.save(favor);
    }

    @Override
    public void deleteById(final String id) {
        throw new RuntimeException("Method not implemented.");
    }

    @Override
    public void deleteByUserId(final String id) {
        throw new RuntimeException("Method not implemented.");
    }

    @Override
    public Favor update(final Favor favor) {
        throw new RuntimeException("Method not implemented.");
    }

}
