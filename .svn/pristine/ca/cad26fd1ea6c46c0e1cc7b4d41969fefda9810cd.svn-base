package com.sanmina.tk.orm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanmina.tk.orm.model.Po;
import com.sanmina.tk.orm.model.PoLine;

@Repository("poLineDao")
@Transactional("txTicket")
public class PoLineDaoImpl extends AbstractDAO implements PoLineDao {

	@Override
	public void savePoLine(PoLine poLine) {
		persistTicket(poLine);
	}

	@Override
	public void saveOrUpdatePoLine(PoLine poLine) {
		getSessionTicket().saveOrUpdate(poLine);
	}

	@Override
	public void deletePoLine(PoLine poLine) {
		getSessionTicket().delete(poLine);
	}

	@Override
	public void updatePoLine(PoLine poLine) {
		getSessionTicket().update(poLine);
	}

	public PoLine findPoLineByIdPoLine(Integer idPoLine) {
		Criteria criteria = getSessionTicket().createCriteria(PoLine.class);
		criteria.add(Restrictions.eq("idPoLine", idPoLine));
		return (PoLine) criteria.uniqueResult();
	}
	
	public PoLine findPoLineByPoLine(String poLine) {
		Criteria criteria = getSessionTicket().createCriteria(PoLine.class);
		criteria.add(Restrictions.eq("poLine", poLine));
		return (PoLine) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<PoLine> findAllPoLine() {
		Criteria criteria = getSessionTicket().createCriteria(PoLine.class);
		return (List<PoLine>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<PoLine> findPoLinesByPo(Po po) {
		Criteria criteria = getSessionTicket().createCriteria(PoLine.class);
		criteria.add(Restrictions.eq("idPo", po.getIdPo()));
		return (List<PoLine>) criteria.list();
	}

}
