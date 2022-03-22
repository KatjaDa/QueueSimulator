package simu.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author Dahlman, Laamo, Lappi
 *
 */
public class TietokantaRaporttiAO implements ITietokantaRaporttiDAO {
	
		SessionFactory sessionFactory = null;

		public TietokantaRaporttiAO() {
			try {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			} catch (Exception e) {
				System.err.println("SessionFactoryn luominen ei onnistunut. " + e);
				System.exit(-1);
			}
		}

		public void finalize() {
			try {
				if (sessionFactory != null) {
					sessionFactory.close();
				}
			} catch (Exception e) {
				System.err.println("SessionFactoryn sulkeminen epäonnistui: " + e);
			}
		}
		
		@Override
		public boolean createRaportti(TietokantaRaportti raportti) {
			Transaction transaktio = null;
			try (Session session = sessionFactory.openSession()) {
				transaktio = session.beginTransaction();
				session.save(raportti);
				transaktio.commit();
				return true;
			} catch (Exception e) {
				if (transaktio != null)
					transaktio.rollback();
				return false;
			}
		}

		@Override
		public TietokantaRaportti[] readRaportit() {
			List<TietokantaRaportti> result;
			try (Session session = sessionFactory.openSession()) {
				result = session.createQuery("from TietokantaRaportti").getResultList();
			} catch (Exception e) {
				throw e;
			}
			TietokantaRaportti[] returnArray = new TietokantaRaportti[result.size()];
			return (TietokantaRaportti[]) result.toArray(returnArray);
		}
		
		@Override
		public boolean deleteRaportti(int id) {
			return false;
		}

}
