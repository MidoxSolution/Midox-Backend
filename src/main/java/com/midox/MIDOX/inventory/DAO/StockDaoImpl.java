package com.midox.MIDOX.inventory.DAO;

import com.midox.MIDOX.inventory.entity.Stock;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class StockDaoImpl extends AbstractDaoJpa<Stock, Integer> implements StockDao{

    String stockListQuery = "SELECT stock_no, material.sub_category, measurement_type,\n" +
            " quantity, supplier FROM public.stock \n" +
            " inner join material on stock.material = material.material_id \n" +
            " ORDER BY stock_no ASC";

    public StockDaoImpl() {
        super(Stock.class);
    }

    @Override
    public List<Stock> getStockList() {
        Query query = getEntityManager().createNativeQuery(stockListQuery);
        return query.getResultList();
    }
}
