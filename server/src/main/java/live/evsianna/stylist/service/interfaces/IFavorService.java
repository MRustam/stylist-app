package live.evsianna.stylist.service.interfaces;

import live.evsianna.stylist.model.Favor;
import live.evsianna.stylist.model.projection.FavorProjection;

import java.util.Set;

public interface IFavorService {

    Set<FavorProjection> findAll();

    Favor findById(final String id);

    Favor save(final Favor favor);

    void deleteById(final String id);

    void deleteByUserId(final String id);

    Favor update(final Favor favor);

}
